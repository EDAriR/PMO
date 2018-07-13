package com.syntrontech.pmo.service;

import java.util.List;
import java.util.Optional;

import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;
import com.syntrontech.autoTool.service.ModelService;
import com.syntrontech.pmo.cip.exception.client.ObjectNotExistedException;
import com.syntrontech.pmo.cip.exception.server.InternalServiceException;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.cip.model.redis.RedisSubject;
import com.syntrontech.pmo.cip.model.to.MeasuredSubjectTO;
import com.syntrontech.pmo.cip.model.to.SearchTO;
import com.syntrontech.pmo.cip.model.to.SubjectTO;
import com.syntrontech.pmo.cip.model.vo.CreateSubjectVO;
import com.syntrontech.pmo.cip.model.vo.SearchVO;
import com.syntrontech.pmo.cip.model.vo.UpdateSubjectVO;
import com.syntrontech.pmo.solr.SolrException;
import com.syntrontech.pmo.util.RedisUser;

public interface SubjectService extends ModelService<Subject, CreateSubjectVO, UpdateSubjectVO>{
	Subject _save(Subject subject)  throws SolrException, ParamFormatErrorException;
	Optional<Subject> findSubjectByIdAndUserIdAndTenantId(String id, String userId, String tenantId);
	SearchTO<SubjectTO> searchSubject(SearchVO searchVO, String userId, String tenantId)throws InternalServiceException, SolrException, ParamFormatErrorException;
	List<Subject> findSubjectsByUserId(String userId);
	List<Subject> findSubjectsByUnitIdAndTenantId(String unitId, String tenantId);
	SearchTO<MeasuredSubjectTO> findBySearchModel(SearchVO searchVO, String tenantId) throws SolrException, InternalServiceException, ParamFormatErrorException;
	SubjectTO turnSubjectToTO(Subject subject, boolean isTTSHB) throws SolrException, ParamFormatErrorException;
	Subject changeUnit(RedisUser validateUser, RedisSubject redisSubject, String unitId, String tenantId) throws ObjectNotExistedException, SolrException, ParamFormatErrorException;
}
