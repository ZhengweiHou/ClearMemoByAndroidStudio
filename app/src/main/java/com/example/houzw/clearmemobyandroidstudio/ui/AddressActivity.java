package com.example.houzw.clearmemobyandroidstudio.ui;

import com.example.houzw.clearmemobyandroidstudio.R;
import com.example.houzw.clearmemobyandroidstudio.BaseActivity;
import com.example.houzw.clearmemobyandroidstudio.service.IUpdateUserMessage;
import com.example.houzw.clearmemobyandroidstudio.service.impl.UserServiceImpl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class AddressActivity extends Activity {

	private ImageView address_return_btn;
	private EditText address_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);

		address_return_btn = (ImageView) findViewById(R.id.address_return_btn);
		address_edit = (EditText) findViewById(R.id.address_content);
		address_return_btn.setOnClickListener(new ViewOCL());

		Intent intent = getIntent();
		String address1 = intent.getStringExtra("address1");
		address_edit.setText(address1);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// TODO Auto-generated method stub
			String address = address_edit.getText().toString().trim();
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					AddressActivity.this);
			BaseActivity.logingUser.setAddress(address);
			iManagerService.updateAddress(BaseActivity.logingUser);
			// �ӵ�ǰҳ���з��ص��û����������ȥ����ҳ���е�ֵ���ص�AdminManagerActivity��
			Intent intent = new Intent(AddressActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("address", address);
			startActivity(intent);
			AddressActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);

	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String address = address_edit.getText().toString().trim();
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					AddressActivity.this);
			BaseActivity.logingUser.setAddress(address);
			iManagerService.updateAddress(BaseActivity.logingUser);
			// �ӵ�ǰҳ���з��ص��û����������ȥ����ҳ���е�ֵ���ص�AdminManagerActivity��
			Intent intent = new Intent(AddressActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("address", address);
			startActivity(intent);
			AddressActivity.this.finish();
		}
	}
}
