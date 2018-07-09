package com.syntrontech.pmo.pmo;

import com.syntrontech.syncare.exception.ServiceException;
import com.syntrontech.syncare.external.pmo.model.*;
import com.syntrontech.syncare.external.pmo.vitalsign.UploadVitalRecordResponse;
import com.syntrontech.syncare.utils.JSON;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PmoWebServiceBaseLocal implements PmoWebService {
	@Override
	public Future<?> uploadVitalRecord(VitalRecord record, AsyncHandler<UploadVitalRecordResponse> asyncHandler) {
		VitalRecords records = new VitalRecords();
		records.setRecords(Arrays.asList(record));
		String json = JSON.toJson(records);
		System.out.println(json);
		if(asyncHandler==null){
			return null;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				long sleep = System.currentTimeMillis()+2000;
				while (true) {
					long now = System.currentTimeMillis();
					if(now>sleep){
						break;
					}
				}
				
				asyncHandler.handleResponse(new Response<UploadVitalRecordResponse>() {
					@Override
					public boolean isDone() {
						return true;
					}
					
					@Override
					public boolean isCancelled() {
						return false;
					}
					
					@Override
					public UploadVitalRecordResponse get(long timeout, TimeUnit unit)
							throws InterruptedException, ExecutionException, TimeoutException {
						UploadVitalRecordResponse response = new UploadVitalRecordResponse();
						return response;
					}
					
					@Override
					public UploadVitalRecordResponse get() throws InterruptedException,
                            ExecutionException {
						UploadVitalRecordResponse response = new UploadVitalRecordResponse();
						response.setUploadVitalRecordResult("{\"MessageCode\" : \"A01\",\"MessageDescription\" : \"成功\"}");
						return response;
					}
					
					@Override
					public boolean cancel(boolean mayInterruptIfRunning) {
						return false;
					}
					
					@Override
					public Map<String, Object> getContext() {
						return null;
					}
				});
			}
		}).start();
		return null;
	}

	@Override
	public ResultMessage uploadVitalRecord(VitalRecord record) {
		VitalRecords records = new VitalRecords();
		records.setRecords(Arrays.asList(record));
		String json = JSON.toJson(records);
		long sleep = System.currentTimeMillis()+2000;
		while (true) {
			long now = System.currentTimeMillis();
			if(now>sleep){
				break;
			}
		}
		System.out.println(json);
		String result = "{\"MessageCode\" : \"A01\",\"MessageDescription\":\"成功\"}";
		Optional<ResultMessage> pmoResult = JSON.toObject(result, ResultMessage.class);
		return pmoResult.orElseThrow(() -> new ServiceException("PMO result parse error, src: "+result));
	}

	@Override
	public ResultMessage registerUserByVendor(UserData user) {
		UserDatas users = new UserDatas();
		users.setUsers(Arrays.asList(user));
		String json = JSON.toJson(users);
		System.out.println(json);
		
		String result = "{\"MessageCode\" : \"A01\",\"MessageDescription\":\"成功\"}";
		Optional<ResultMessage> pmoResult = JSON.toObject(result, ResultMessage.class);
		return pmoResult.orElseThrow(() -> new ServiceException("PMO result parse error, src: "+result));
	}
	
	
	//
	@Override
	public ResultMessage uploadUserData(UserData user) {
		UserDatas users = new UserDatas();
		users.setUsers(Arrays.asList(user));
		String json = JSON.toJson(users);
		System.out.println(json);
		
		String result = "{\"MessageCode\" : \"A01\",\"MessageDescription\":\"成功\"}";
		Optional<ResultMessage> pmoResult = JSON.toObject(result, ResultMessage.class);
		return pmoResult.orElseThrow(() -> new ServiceException("PMO result parse error, src: "+result));
	}

	@Override
	public String getDefaultAreaCode() {
		return "66";
	}
}
