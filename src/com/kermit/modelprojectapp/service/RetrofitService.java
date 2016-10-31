package com.kermit.modelprojectapp.service;
/**
* @author:Kermit
* @createTime:2016年2月2日 下午2:42:34
* 类说明
*/

import com.kermit.modelprojectapp.model.Phone;
import com.kermit.modelprojectapp.utils.Contants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitService {
	private static RetrofitService retrofitService = new RetrofitService();
	private RetrofitInterface retrofitInterface;

	private RetrofitService() {
		initRetrofit();
	}

	public static RetrofitService getInstance() {
		if (retrofitService == null) {
			retrofitService = new RetrofitService();
		}
		return retrofitService;
	}

	private void initRetrofit() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(Contants.BASE_URL).addConverterFactory(ScalarsConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
		retrofitInterface = retrofit.create(RetrofitInterface.class);
	}

	public void callIndexSynchronousObservable(final String phone, final Observer<String> callback) {
		Observable<String> result = retrofitInterface.CheckPhoneNum(phone).cache();
		result.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback);
	}

}
