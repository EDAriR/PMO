package com.syntrontech.pmo.resource;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.syntrontech.pmo.cip.exception.server.InternalServiceException;
import com.syntrontech.pmo.cip.model.redis.RedisUser;
import com.syntrontech.pmo.cip.model.to.SearchTO;
import com.syntrontech.pmo.cip.model.to.SubjectTO;
import com.syntrontech.pmo.cip.model.vo.SearchVO;
import com.syntrontech.pmo.cip.service.EmergencyContactService;
import com.syntrontech.pmo.service.SubjectService;
import com.syntrontech.pmo.solr.SolrException;
import com.syntrontech.pmo.util.RedisUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.syntrontech.autoTool.annotation.JsonObjRequired;
import com.syntrontech.autoTool.annotation.ValidateSession;
import com.syntrontech.autoTool.exception.client.NotFoundException;
import com.syntrontech.autoTool.exception.client.ParamFormatErrorException;

@Path("/user")
public class UserSubjectResource {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private RedisUserService redisUserService;
	

	@Autowired
	private EmergencyContactService emergencyContactService;


	@GET
	@Path("/{userId}/subject/{subjectId}")
	public Response findShubjectByUserIdAndSubjectId(@PathParam("userId") String userId,
			@PathParam("subjectId") String subjectId, @ValidateSession @Context HttpServletRequest request)
			throws NotFoundException, SolrException, ParamFormatErrorException {

		RedisUser validateUser = (RedisUser) request.getAttribute("validatedUser");

		String tenantId = validateUser.getTenantId();
		SubjectTO to = (SubjectTO)subjectService.findSubjectByIdAndUserIdAndTenantId(subjectId, userId, tenantId)
				.map(subject -> subjectService.convertFromModel(subject)).orElseThrow(() -> new NotFoundException());
		
//		if(projectSetting.isTTSHB()) {
//			to.setEmergencyContacts(emergencyContactService.findBySubjectIdAndTenantId(subjectId, tenantId));
//		}

		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(to).build();
	}

	@GET
	@Path("/{userId}/subject")
	public Response searchSubjectByUserId(@PathParam("userId") String userId,
										  @BeanParam @JsonObjRequired SearchVO searchVO, @ValidateSession @Context HttpServletRequest request)
			throws SolrException, InternalServiceException, NotFoundException, ParamFormatErrorException {

		RedisUser validateUser = (RedisUser) request.getAttribute("validatedUser");
		redisUserService.findRedisUserByUserId(userId).filter(u -> u.getTenantId().equals(validateUser.getTenantId()))
				.orElseThrow(() -> new NotFoundException());

		SearchTO<SubjectTO> subjects = subjectService.searchSubject(searchVO, userId, validateUser.getTenantId());
		
		ArrayList<SubjectTO> list = new ArrayList<>();
//		if(projectSetting.isTTSHB()) {
//			for(SubjectTO s:subjects.getContent()) {
//				s = setEmergencyContacts(s, validateUser.getTenantId());
//				list.add(s);
//			}
//		}
		subjects.setContent(list);
		
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(subjects).build();
	}
	
	private SubjectTO setEmergencyContacts(SubjectTO to, String tenantId) throws SolrException, ParamFormatErrorException {
		to.setEmergencyContacts(emergencyContactService.findBySubjectIdAndTenantId(to.getSubjectId(), tenantId));
		return to;
	}

}
