package com.kermit.modelprojectapp.service;

import com.kermit.modelprojectapp.model.Phone;
import com.kermit.modelprojectapp.utils.Contants;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
* @author:Kermit
* @createTime:2016年2月2日 下午2:43:40
* 类说明
*/
public interface RetrofitInterface {
	
	@Headers("apikey:" + Contants.APIKEY)
	@GET(Contants.PHONE_NUM_ATTR)
	Observable<String> CheckPhoneNum(@Query("phone") String phone);
	
	
}
