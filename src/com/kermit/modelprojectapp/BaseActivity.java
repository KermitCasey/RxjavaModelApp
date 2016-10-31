package com.kermit.modelprojectapp;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by liuyuliang on 15-11-19.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import rx.subscriptions.CompositeSubscription;

/**
 * 此类是所有activity的基类，填写其共有的函数及变量
 * <p/>
 * 例如： umeng统计分析代码 上下文对象Context
 */
public abstract class BaseActivity extends Activity {

	protected Context mContext;
	CompositeSubscription mCompositeSubscription = new CompositeSubscription();
	private View rootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		setContentView(getLayoutResId());
	}

	@Override
	public void setContentView(int layoutResID) {
		rootView = View.inflate(this, layoutResID, null);
		super.setContentView(rootView);
		getIntentData();
		initView();
		initTitle();
		initData();
	}

	public View getRootView() {
		return rootView;
	}

	/**
	 * 获取layout id
	 *
	 * @return
	 */
	protected abstract int getLayoutResId();

	/**
	 * 获取Intent中传递的数据
	 */
	protected abstract void getIntentData();

	/**
	 * 初始化各类view对象
	 */
	protected abstract void initView();

	/**
	 * 初始化Title layout及title对象
	 */
	protected abstract void initTitle();

	/**
	 * 初始化其他数据Data
	 */
	protected abstract void initData();

	/**
	 * Activity重启时刷新数据Data
	 */
	protected abstract void RefershData();

	@Override
	protected void onPause() {
		MobclickAgent.onPause(this);
		super.onPause();
	}

	@Override
	protected void onResume() {
		MobclickAgent.onResume(this);
		super.onResume();
		RefershData();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mCompositeSubscription.unsubscribe();
	}

}
