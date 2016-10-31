package com.kermit.modelprojectapp;

import com.kermit.modelprojectapp.model.Phone;
import com.kermit.modelprojectapp.service.RetrofitService;

import android.util.Log;
import android.widget.TextView;
import rx.Subscriber;

public class MainActivity extends BaseActivity {

	TextView hello;
	
	@Override
	protected int getLayoutResId() {
		return R.layout.activity_main;
	}

	@Override
	protected void getIntentData() {

	}

	@Override
	protected void initView() {
		hello = (TextView) findViewById(R.id.hello);
	}

	@Override
	protected void initTitle() {

	}

	@Override
	protected void initData() {
		Subscriber<String> mySubscriber = new Subscriber<String>() {
			@Override
			public void onCompleted() {
				Log.e("onCompleted", "Over");
			}

			@Override
			public void onError(Throwable throwable) {
				Log.e("onError", "Over");
			}

			@Override
			public void onNext(String s) {
//				hello.setText(s.getRetData().getProvince() + s.getRetData().getCity());
				Log.e("onNext:", s);
			}
		};
		mCompositeSubscription.add(mySubscriber);
		RetrofitService.getInstance().callIndexSynchronousObservable("15335612154", mySubscriber);
	}

	@Override
	protected void RefershData() {

	}
}
