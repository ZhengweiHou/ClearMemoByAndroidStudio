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

public class TelActivity extends Activity {

	private ImageView tel_return_btn;
	private EditText tel_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tel);

		tel_return_btn = (ImageView) findViewById(R.id.tel_return_btn);
		tel_edit = (EditText) findViewById(R.id.tel_content);
		tel_return_btn.setOnClickListener(new ViewOCL());

		Intent intent = getIntent();
		String tel1 = intent.getStringExtra("tel1");
		tel_edit.setText(tel1);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			String tel = tel_edit.getText().toString().trim();
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					TelActivity.this);
			BaseActivity.logingUser.setTel(tel);
			iManagerService.updateTel(BaseActivity.logingUser);
			// �ӵ�ǰҳ���з��ص��û����������ȥ����ҳ���е�ֵ���ص�AdminManagerActivity��
			Intent intent = new Intent(TelActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("tel", tel);
			startActivity(intent);
			TelActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);

	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			String tel = tel_edit.getText().toString().trim();
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					TelActivity.this);
			BaseActivity.logingUser.setTel(tel);
			iManagerService.updateTel(BaseActivity.logingUser);
			// �ӵ�ǰҳ���з��ص��û����������ȥ����ҳ���е�ֵ���ص�AdminManagerActivity��
			Intent intent = new Intent(TelActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("tel", tel);
			startActivity(intent);
			TelActivity.this.finish();
		}
	}
}
