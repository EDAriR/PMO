package com.syntrontech.pmo.cip.service;

import java.util.List;

import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;
import com.syntrontech.pmo.cip.model.EmergencyContact;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.cip.model.to.EmergencyContactTO;
import com.syntrontech.pmo.cip.model.vo.EmergencyContactsVO;
import com.syntrontech.pmo.solr.SolrException;

public interface EmergencyContactService {

	EmergencyContact create(Subject subject, EmergencyContactsVO contact) throws SolrException, ParamFormatErrorException;

	List<EmergencyContactTO> findBySubjectIdAndTenantId(String SubjectId, String TenantId) throws SolrException, ParamFormatErrorException;

	void deleteBySubjectIdAndTenantId(String SubjectId, String TenantId) throws ParamFormatErrorException, SolrException;
	
}
