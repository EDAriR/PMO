package com.syntrontech.pmo.cip.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.cip.model.solr.SolrEmergencyContacts;
import com.syntrontech.pmo.cip.model.to.EmergencyContactTO;
import com.syntrontech.pmo.cip.model.vo.EmergencyContactsVO;
import com.syntrontech.pmo.cip.repository.EmergencyContactRepository;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.solr.Solr;
import com.syntrontech.pmo.solr.SolrException;
import com.syntrontech.pmo.solr.SolrFilterNameConverter;
import com.syntrontech.pmo.solr.SolrSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;

@Service
public class EmergencyContactServiceImp implements EmergencyContactService {
	
	@Autowired
	private Solr solrService;
	
	@Autowired
	private EmergencyContactRepository emergencyContactRepository;
	
	@Override
	public EmergencyContact create(Subject subject, EmergencyContactsVO contact) throws SolrException, ParamFormatErrorException {

		EmergencyContact emergencyContact = new EmergencyContact();
		
		emergencyContact.setUserId(subject.getUserId());
		emergencyContact.setSubjectId(subject.getId());
		emergencyContact.setTenantId(subject.getTenantId());
		emergencyContact.setName(contact.getEmergencyContactName());
		emergencyContact.setPhone(contact.getEmergencyContactMobilePhone());
		emergencyContact.setEmail(contact.getEmergencyContactEmail());
		emergencyContact.setStatus(ModelStatus.ENABLED);
		SolrEmergencyContacts solrEmergencyContacts = new SolrEmergencyContacts();
		EmergencyContact newEmergencyContact = emergencyContactRepository.save(emergencyContact);
		solrEmergencyContacts = (SolrEmergencyContacts)solrEmergencyContacts.convertFrom(newEmergencyContact);
		solrService.saveJsonDoc(solrEmergencyContacts);
		return newEmergencyContact;
	}
	
	@Override
	public List<EmergencyContactTO> findBySubjectIdAndTenantId(String subjectId, String tenantId) throws SolrException, ParamFormatErrorException{
		
		String keyword = "*";

		String tenantIdFilter = "tenantId"+":"+tenantId;
		String userIdFilter = "subjectId"+":"+subjectId;

		List<String> filters = new ArrayList<>();
		filters.add(tenantIdFilter);
		filters.add(userIdFilter);

		filters = SolrFilterNameConverter.convertFilterNames(filters, new SolrEmergencyContacts());

		SolrSearchModel<SolrEmergencyContacts> searchModel = solrService.searchJsonDoc(keyword, 1, 2147483647, null, null, filters, SolrEmergencyContacts.class);

		return searchModel.getContent().stream()
				.map(ec -> turnToTO(ec))
				.collect(Collectors.toList());
	}
	
	private EmergencyContactTO turnToTO(SolrEmergencyContacts model) {
		EmergencyContactTO to = new EmergencyContactTO();
		return (EmergencyContactTO)to.convertFrom(model);
	}

	@Override
	public void deleteBySubjectIdAndTenantId(String subjectId, String tenantId) throws ParamFormatErrorException, SolrException {

		String keyword = "*";

		String tenantIdFilter = "tenantId"+":"+tenantId;
		String userIdFilter = "subjectId"+":"+subjectId;

		List<String> filters = new ArrayList<>();
		filters.add(tenantIdFilter);
		filters.add(userIdFilter);

		filters = SolrFilterNameConverter.convertFilterNames(filters, new SolrEmergencyContacts());

		SolrSearchModel<SolrEmergencyContacts> searchModel = solrService.searchJsonDoc(keyword, 1, 2147483647, null, null, filters, SolrEmergencyContacts.class);

		for(SolrEmergencyContacts ec:searchModel.getContent()) {
			EmergencyContact model = emergencyContactRepository.findOne(ec.getEmergencyContactSequence_l());
			emergencyContactRepository.delete(model);
			solrService.deleteJsonDoc(ec.getId());;
		}
	}

}
