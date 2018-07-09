package com.syntrontech.pmo.pmo;

import com.syntrontech.syncare.exception.ServiceException;
import com.syntrontech.syncare.external.pmo.member.MemberSoap;
import com.syntrontech.syncare.external.pmo.model.*;
import com.syntrontech.syncare.external.pmo.vitalsign.UploadVitalRecordResponse;
import com.syntrontech.syncare.external.pmo.vitalsign.VitalSignSoap;
import com.syntrontech.syncare.utils.JSON;
import org.apache.commons.lang.StringUtils;

import javax.xml.ws.AsyncHandler;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Future;

public class PmoWebServiceBaseProd implements PmoWebService {
	private PmoWebServiceSettings pmoWsSettings;
	
	public PmoWebServiceSettings getPmoWsSettings() {
		return pmoWsSettings;
	}

	public void setPmoWsSettings(PmoWebServiceSettings pmoWsSettings) {
		this.pmoWsSettings = pmoWsSettings;
	}
	
	@Override
	public ResultMessage registerUserByVendor(UserData user){
		MemberSoap service = getMemberService();
		String id = pmoWsSettings.getPmoAccount();
		String credential = pmoWsSettings.getPmoCredentials();
		if(StringUtils.isBlank(user.getAreaCode())){
			user.setAreaCode(pmoWsSettings.getAreaCode());
		}
		UserDatas users = new UserDatas();
		users.setUsers(Arrays.asList(user));
		String json = JSON.toJson(users);
		String result = service.registerUserByVendor(id, credential, json);
		Optional<ResultMessage> pmoResult = JSON.toObject(result, ResultMessage.class);
		return pmoResult.orElseThrow(() -> new ServiceException("PMO result parse error, src: "+result));
	}
	
	//
	@Override
	public ResultMessage uploadUserData(UserData user){
		MemberSoap service = getMemberService();
		String id = pmoWsSettings.getPmoAccount();
		String credential = pmoWsSettings.getPmoCredentials();
		if(StringUtils.isBlank(user.getAreaCode())){
			user.setAreaCode(pmoWsSettings.getAreaCode());
		}
		UserDatas users = new UserDatas();
		users.setUsers(Arrays.asList(user));
		String json = JSON.toJson(users);
		String result = service.uploadUserData(id, credential, json);
		Optional<ResultMessage> pmoResult = JSON.toObject(result, ResultMessage.class);
		return pmoResult.orElseThrow(() -> new ServiceException("PMO result parse error, src: "+result));
	}
	
	public void getUsers(){
//		MemberSoap service = getMemberService();
//		service.getUserDataByVendor(id, pwd, startDate, endDate)
	}
	
	public void validateUser(){
//		MemberSoap service = getMemberService();
//		service.validateUser(id, pwd)
	}
	
	@Override
	public ResultMessage uploadVitalRecord(VitalRecord record){
		VitalSignSoap service = getVitalSignsService();
		String id = pmoWsSettings.getPmoAccount();
		String credential = pmoWsSettings.getPmoCredentials();
		VitalRecords records = new VitalRecords();
		records.setRecords(Arrays.asList(record));
		String json = JSON.toJson(records);
		String result = service.uploadVitalRecord(id, credential, json);
		Optional<ResultMessage> pmoResult = JSON.toObject(result, ResultMessage.class);
		return pmoResult.orElseThrow(() -> new ServiceException("PMO result parse error, src: "+result));
	}
	
	@Override
	public Future<?> uploadVitalRecord(VitalRecord record, AsyncHandler<UploadVitalRecordResponse> asyncHandler){
		VitalSignSoap service = getVitalSignsService();
		String id = pmoWsSettings.getPmoAccount();
		String credential = pmoWsSettings.getPmoCredentials();
		VitalRecords records = new VitalRecords();
		records.setRecords(Arrays.asList(record));
		String json = JSON.toJson(records);
		if(asyncHandler==null){
			throw new ServiceException("AsyncHandler is null");
		}else{
			return service.uploadVitalRecord(id, credential, json, asyncHandler);
		}
	}
	
	public void getVitalRecord(){
//		VitalSignSoap service = getVitalSignsService();
//		service.getVitalRecord(id, pwd, type, startDate, endDate)
	}
	
	public void changeUserPassword(){
//		MemberSoap service = getMemberService();
//		service.changePassword(id, oldPwd, newPwd)
	}
	
	public void resetUserPassword(){
//		MemberSoap service = getMemberService();
//		service.resetPassword(id)
	}
	
	private MemberSoap getMemberService() {
		return getService(MemberSoap.class, "Member");
	}
	
	private VitalSignSoap getVitalSignsService() {
		return getService(VitalSignSoap.class, "VitalSign");
	}
	
	private <T> T getService(Class<T> clazz, String serviceName){
		return PmoService.service(clazz)
			.url(pmoWsSettings.getUrl())
			.serviceName(serviceName)
			.trustStore(pmoWsSettings.getTrustStore())
			.trustStorePassword(pmoWsSettings.getTrustStorePassword())
			.trustStoreType(pmoWsSettings.getTrustStoreType())
			.build();
	}

	@Override
	public String getDefaultAreaCode() {
		return pmoWsSettings.getAreaCode();
	}
}
