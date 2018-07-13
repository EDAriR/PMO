package com.syntrontech.pmo.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.syntrontech.pmo.auth.model.redis.RedisUnit;
import com.syntrontech.pmo.cip.exception.client.ObjectHasExistedException;
import com.syntrontech.pmo.cip.exception.client.ObjectNotExistedException;
import com.syntrontech.pmo.cip.exception.server.InternalServiceException;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.cip.model.SubjectUnitLog;
import com.syntrontech.pmo.cip.model.redis.RedisSubject;
import com.syntrontech.pmo.cip.model.solr.SolrSubject;
import com.syntrontech.pmo.cip.model.to.EmergencyContactTO;
import com.syntrontech.pmo.cip.model.to.MeasuredSubjectTO;
import com.syntrontech.pmo.cip.model.to.SearchTO;
import com.syntrontech.pmo.cip.model.to.SubjectTO;
import com.syntrontech.pmo.cip.model.vo.CreateSubjectVO;
import com.syntrontech.pmo.cip.model.vo.SearchVO;
import com.syntrontech.pmo.cip.model.vo.UpdateSubjectVO;
import com.syntrontech.pmo.cip.repository.SubjectRepository;
import com.syntrontech.pmo.cip.repository.SubjectUnitLogRepository;
import com.syntrontech.pmo.cip.service.EmergencyContactService;
import com.syntrontech.pmo.cip.service.RedisSubjectService;
import com.syntrontech.pmo.cip.service.RedisUnitService;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.service.SubjectService;
import com.syntrontech.pmo.solr.Solr;
import com.syntrontech.pmo.solr.SolrException;
import com.syntrontech.pmo.solr.SolrFilterNameConverter;
import com.syntrontech.pmo.solr.SolrSearchModel;
import com.syntrontech.pmo.util.RedisUser;
import com.syntrontech.pmo.util.RedisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntrontech.autoTool.exception.client.ClientException;
import com.syntrontech.autoTool.exception.client.NotFoundException;
import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;
import com.syntrontech.autoTool.exception.client.ParamIsNullException;
import com.syntrontech.autoTool.exception.server.ServerException;

@Service
public class SubjectServiceImp implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private RedisSubjectService redisSubjectService;
	
	@Autowired
	private SubjectUnitLogRepository subjectUnitLogRepository;
	
	@Autowired
	private RedisUnitService redisUnitService;
	
	@Autowired
	private Solr solrService;
	
	@Autowired
	private RedisUserService redisUserService;
	
	@Autowired
	private EmergencyContactService emergencyContactService;
	
	@Override
	public Object convertFromModel(Subject model) {
		SubjectTO to = new SubjectTO();
		to.setSubjectId(model.getId());
		to.setSubjectName(model.getName());
		to.setGender(model.getGender());
		to.setBirthday(model.getBirthday());
		to.setHomePhone(model.getHomePhone());
		to.setAddress(model.getAddress());
		to.setEthnicity(model.getEthnicity());
		to.setPersonalHistory(model.getPersonalHistory());
		to.setFamilyHistory(model.getFamilyHistory());
		to.setSmoke(model.getSmoke());
		to.setDrink(model.getDrink());
		to.setChewingAreca(model.getChewingAreca());
		to.setUnitId(model.getUnitId());
		to.setUnitName(model.getUnitName());
		return to;
	}

	@Override
	public void deleteById(String id, String tenantId, String updateBy, Date updateTime) 
			throws ClientException, ServerException {
		Subject oldSubject = findSubjectByIdAndUserIdAndTenantId(id, updateBy, tenantId)
									 .orElseThrow(() -> new ObjectNotExistedException("subjectid"));
		oldSubject.setStatus(ModelStatus.DELETED);
		if(Objects.nonNull(updateBy)){
			oldSubject.setUpdateBy(updateBy);
		}
		if(Objects.nonNull(updateTime)){
			oldSubject.setUpdateTime(updateTime);
		}
		subjectRepository.save(oldSubject);
		
		redisSubjectService.delete(id, updateBy, tenantId);
		
//		SolrSubject solrSubject = (SolrSubject) oldSubject.convertToTO(new SolrSubject());
//		solrService.deleteJsonDoc(solrSubject.getId());
	}

	@Override
	public void deleteByIds(List<String> ids, String tenantId, String updateBy, Date updateTime)
			throws ClientException, ServerException {
		for(String id : ids){
			deleteById(id, tenantId, updateBy, updateTime);
		}
	}

	@Override
	public Subject get(String id, String tenantId) {
		// no used method
		return null;
	}

	@Override
	public Subject create(CreateSubjectVO vo, String tenantId, String createBy, Date createTime)
			throws ClientException, ServerException {
		if(findSubjectByIdAndUserIdAndTenantId(vo.getSubjectId(), createBy, tenantId).isPresent()){
			throw new ObjectHasExistedException("subjectId");
		}
		
		RedisUnit unit = null;
//		if(!projectSetting.isTTSHB()) {
//			unit = findRedisUnitByIdAndTenantId(vo.getUnitId(), tenantId);
//		}
		
		Subject subject = new Subject();
		subject.setId(vo.getSubjectId());
		subject.setName(vo.getSubjectName());
		subject.setGender(vo.getGender());
		subject.setBirthday(vo.getBirthday());
		subject.setHomePhone(vo.getHomePhone());
		subject.setAddress(vo.getAddress());
		subject.setEthnicity(vo.getEthnicity());
		subject.setPersonalHistory(vo.getPersonalHistory());
		subject.setFamilyHistory(vo.getFamilyHistory());
		subject.setSmoke(vo.getSmoke());
		subject.setDrink(vo.getDrink());
		subject.setChewingAreca(vo.getChewingAreca());
		subject.setStatus(ModelStatus.ENABLED);
		subject.setUserId(createBy);
		if(unit != null) {
			subject.setUnitId(unit.getId());
			subject.setUnitName(unit.getName());
		}
//		if(projectSetting.isTTSHB()) {
//			unit = findRedisUnitByIdAndTenantId("100140102310", tenantId);
//			subject.setUnitId(unit.getId());
//			subject.setUnitName(unit.getName());
//		}
		subject.setTenantId(tenantId);
		subject.setCreateBy(createBy);
		subject.setCreateTime(createTime);
		subject.setUpdateBy(createBy);
		subject.setUpdateTime(createTime);
		
		Subject newSubject = _save(subject);
		
//		if(projectSetting.isTTSHB()) {
//
//			List<EmergencyContactsVO> emergencyContactsVOs = vo.getEmergencyContacts();
//			for(EmergencyContactsVO contact:emergencyContactsVOs) {
//
//				if(contact.getEmergencyContactName() == null || contact.getEmergencyContactName().equals("")) {
//					throw new ParamIsNullException("EmergencyContactName");
//				}
//
//				if(contact.getEmergencyContactMobilePhone() == null || contact.getEmergencyContactMobilePhone().equals("")) {
//					throw new ParamIsNullException("EmergencyContactMobilePhone");
//				}
//				emergencyContactService.create(subject, contact);
//			}
//		}
		
		//儲存一開始受測者(subject)是選在哪個unit下 台東預設為"100140102310其他"第一次量測後才儲存
//		if(!projectSetting.isTTSHB()) {
//			saveSubjectUnitLog(createBy, tenantId, vo.getSubjectId(), vo.getSubjectName(), unit, createTime);
//		}
		
		return newSubject;
	}

	private void saveSubjectUnitLog(String createBy, String tenantId, String subjectId, String subjectName,
									RedisUnit unit, Date createTime) {
		SubjectUnitLog sUnitLog = new SubjectUnitLog();
		sUnitLog.setUserId(createBy);
		sUnitLog.setTenantId(tenantId);
		sUnitLog.setSubjectId(subjectId);
		sUnitLog.setSubjectName(subjectName);
		sUnitLog.setUnitId(unit.getId());
		sUnitLog.setUnitName(unit.getName());
		sUnitLog.setCreateTime(createTime);
		subjectUnitLogRepository.save(sUnitLog);
		
	}

	@Override
	public Subject update(String id, UpdateSubjectVO vo, String tenantId, String updateBy, Date updateTime)
			throws ClientException, ServerException, NotFoundException {
		Subject oldSubject = findSubjectByIdAndUserIdAndTenantId(id, updateBy, tenantId)
				 .orElseThrow(() -> new NotFoundException());

		RedisUnit unit = null;
//		if(!projectSetting.isTTSHB()) {
//			unit = findRedisUnitByIdAndTenantId(vo.getUnitId(), tenantId);
//		}
		
		oldSubject.setName(vo.getSubjectName());
		oldSubject.setGender(vo.getGender());
		oldSubject.setBirthday(vo.getBirthday());
		oldSubject.setHomePhone(vo.getHomePhone());
		oldSubject.setAddress(vo.getAddress());
		oldSubject.setEthnicity(vo.getEthnicity());
		oldSubject.setPersonalHistory(vo.getPersonalHistory());
		oldSubject.setFamilyHistory(vo.getFamilyHistory());
		oldSubject.setSmoke(vo.getSmoke());
		oldSubject.setDrink(vo.getDrink());
		oldSubject.setChewingAreca(vo.getChewingAreca());
		if(unit != null) {
			oldSubject.setUnitId(unit.getId());
			oldSubject.setUnitName(unit.getName());
		}
		oldSubject.setUpdateBy(updateBy);
		oldSubject.setUpdateTime(updateTime);
		
		Subject subject = _save(oldSubject);
		
		// 更新緊急聯絡人時如果未填入任何資料時清空原有緊急聯絡人
//		if(projectSetting.isTTSHB()) {
//			emergencyContactService.deleteBySubjectIdAndTenantId(subject.getId(), subject.getTenantId());
//
//			List<EmergencyContactsVO> emergencyContactsVOs = vo.getEmergencyContacts();
//			for(EmergencyContactsVO contact:emergencyContactsVOs) {
//				emergencyContactService.create(subject, contact);
//			}
//		}
		
		return subject;
	}

	private RedisUnit findRedisUnitByIdAndTenantId(String unitId, String tenantId) throws ObjectNotExistedException {
		return redisUnitService.findRedisUnitByIdAndTenantId(unitId, tenantId)
				.orElseThrow(() -> new ObjectNotExistedException("unitid"));
	}

	@Override
	public Subject _save(Subject subject) throws SolrException, ParamFormatErrorException {
		Subject newSubject = subjectRepository.save(subject);
		
		redisSubjectService.update(newSubject);
		
//		SolrSubject solrSubject = (SolrSubject) newSubject.convertToTO(new SolrSubject());
//		solrService.saveJsonDoc(solrSubject);
		
		return newSubject;
	}

	@Override
	public Optional<Subject> findSubjectByIdAndUserIdAndTenantId(String id, String userId, String tenantId) {
		return subjectRepository.findByIdAndUserIdAndTenantIdAndStatusNot(id, userId, tenantId, ModelStatus.DELETED);
	}

	@Override
	public SearchTO<SubjectTO> searchSubject(SearchVO searchVO, String userId, String tenantId)
			throws InternalServiceException, SolrException, ParamFormatErrorException {
		String keyword = Optional.ofNullable(searchVO.getKeyword())
				 				 .orElse("*");
		
		String tenantIdFilter = "tenantId"+":"+tenantId;
		String userIdFilter = "userId"+":"+userId;
		List<String> filters = new ArrayList<>();
		filters.add(tenantIdFilter);
		filters.add(userIdFilter);
		if(Objects.nonNull(searchVO.getFilter())){
			filters.add(searchVO.getFilter());
		}
		filters = SolrFilterNameConverter.convertFilterNames(filters, new SolrSubject());
		
		String orderBy = new SolrSubject().getFieldNameformTO(searchVO.getOrderBy());

		SolrSearchModel<SolrSubject> searchModel = solrService.searchJsonDoc(keyword, searchVO.getIndex(), searchVO.getRows()
				, orderBy, searchVO.getOrder(), filters, SolrSubject.class);

		SearchTO<SubjectTO> searchTO = new SearchTO<>();
		ArrayList<SubjectTO> subjectTOs = new ArrayList<SubjectTO>();
		for(SolrSubject solrSubject : searchModel.getContent()){
//			subjectTOs.add( (SubjectTO) solrSubject.convertToTO(new SubjectTO()) );
		}
		searchTO.setContent(subjectTOs);
		searchTO.setTotalElements(searchModel.getTotalnumFound());

		return searchTO;
	}

	@Override
	public List<Subject> findSubjectsByUserId(String userId) {
		return subjectRepository.findByUserIdAndStatusNot(userId, ModelStatus.DELETED);
	}

	@Override
	public List<Subject> findSubjectsByUnitIdAndTenantId(String unitId, String tenantId) {
		return subjectRepository.findByUnitIdAndTenantIdAndStatusNot(unitId, tenantId, ModelStatus.DELETED);
	}

	@Override
	public SearchTO<MeasuredSubjectTO> findBySearchModel(SearchVO searchVO, String tenantId) throws SolrException, InternalServiceException, ParamFormatErrorException {

		String keyword = Optional.ofNullable(searchVO.getKeyword()).orElse("*");
		List<String> filters = new ArrayList<>();
		if(Objects.nonNull(searchVO.getFilter())){
			filters.add(searchVO.getFilter());
		}
		filters.add("tenantId:" + tenantId);
		filters = SolrFilterNameConverter.convertFilterNames(filters, new SolrSubject());
		String orderBy = new SolrSubject().getFieldNameformTO(searchVO.getOrderBy());

		SolrSearchModel<SolrSubject> searchModel = solrService.searchJsonDoc(keyword, searchVO.getIndex(), searchVO.getRows()
				, orderBy, searchVO.getOrder(), filters, SolrSubject.class);
		
		List<SolrSubject> subjectList = searchModel.getContent();
		List<MeasuredSubjectTO> list = turnSolrSubjectsToMeasuredSubjectTOs(subjectList);
		
		SearchTO<MeasuredSubjectTO> searchTO = new SearchTO<>();
		
		searchTO.setContent(list);
		searchTO.setTotalElements(searchModel.getTotalnumFound());
		return searchTO;
	}
	
	private List<MeasuredSubjectTO> turnSolrSubjectsToMeasuredSubjectTOs(List<SolrSubject> subjectList) {
		return subjectList.stream()
				.map(s -> {
//					if(projectSetting.isTTSHB()) {
//						List<EmergencyContactTO> emergencyContacts = new ArrayList<EmergencyContactTO>();
//						try {
//							emergencyContacts =
//									emergencyContactService.findBySubjectIdAndTenantId(s.getSubjectId_s(), s.getSubjectTenantId_s());
//						} catch (SolrException | ParamFormatErrorException e) {
//							e.printStackTrace();
//						}
//						return new MeasuredSubjectTO().setMeasuredSubjectTO(s, emergencyContacts);
//					}else {
						return new MeasuredSubjectTO().setMeasuredSubjectTO(s);
//					}
				})
//				.filter(ms -> setUserName(ms, ms.getUserId()))
				.collect(Collectors.toList());
	}

	private boolean setUserName(MeasuredSubjectTO ms, String userId) {
//
//		Optional<RedisUser> redisUser = redisUserService.findRedisUserByUserId(userId);
//		if(redisUser.isPresent()) {
//			ms.setUserName(redisUser.get().getName());
			return true;
//		}else {
//			return false;
//		}
	}
	
	@Override
	public SubjectTO turnSubjectToTO(Subject model, boolean isTTSHB) throws SolrException, ParamFormatErrorException {
		SubjectTO to = new SubjectTO();

		to.setSubjectId(model.getId());
		to.setSubjectName(model.getName());
		to.setGender(model.getGender());
		to.setBirthday(model.getBirthday());
		to.setHomePhone(model.getHomePhone());
		to.setAddress(model.getAddress());
		to.setEthnicity(model.getEthnicity());
		to.setPersonalHistory(model.getPersonalHistory());
		to.setFamilyHistory(model.getFamilyHistory());
		to.setSmoke(model.getSmoke());
		to.setDrink(model.getDrink());
		to.setChewingAreca(model.getChewingAreca());
		to.setUnitId(model.getUnitId());
		to.setUnitName(model.getUnitName());
		
		if(isTTSHB) {
			List<EmergencyContactTO> emergencyContacts =
					emergencyContactService.findBySubjectIdAndTenantId(model.getId(), model.getTenantId());
			
			to.setEmergencyContacts(emergencyContacts);
		}
		return to;
	}

	@Override
	public Subject changeUnit(RedisUser validateUser, RedisSubject redisSubject, String unitId, String tenantId) throws ObjectNotExistedException, SolrException, ParamFormatErrorException {
		
		String userId = validateUser.getUserId();
		String subjectId = redisSubject.getId();
		String subjectName = redisSubject.getName();
		Date createTime = new Date();
		
		RedisUnit unit = redisUnitService.findRedisUnitByIdAndTenantId(unitId, tenantId)
				.orElseThrow(() -> new ObjectNotExistedException("unitId"));
		
		Subject subject = subjectRepository.findByIdAndUserIdAndTenantIdAndStatusNot(subjectId, userId, tenantId, ModelStatus.DELETED)
				.orElseThrow(() -> new ObjectNotExistedException("subjectId"));
		subject.setUnitId(unit.getId());
		subject.setUnitName(unit.getName());
		Subject newSubject = _save(subject);
		
		saveSubjectUnitLog(userId, tenantId, subjectId, subjectName, unit, createTime);
		
		return newSubject;
	}

}
