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

public class NichengActivity extends Activity {

	private ImageView nicheng_return_btn;
	private EditText nicheng_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nicheng);

		nicheng_return_btn = (ImageView) findViewById(R.id.nicheng_return_btn);
		nicheng_content = (EditText) findViewById(R.id.nicheng_content);

		nicheng_return_btn.setOnClickListener(new ViewOCL());

		Intent intent = getIntent();
		String nicheng = intent.getStringExtra("nicheng1");
		nicheng_content.setText(nicheng);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			String nicheng = nicheng_content.getText().toString().trim();
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					NichengActivity.this);
			BaseActivity.logingUser.setNicheng(nicheng);
			iManagerService.updateNicheng(BaseActivity.logingUser);
			// �ӵ�ǰҳ���з��ص��û����������ȥ����ҳ���е�ֵ���ص�AdminManagerActivity��
			Intent intent = new Intent(NichengActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("nicheng", nicheng);
			startActivity(intent);
			NichengActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);

	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// ��ȡ�ǳ��������ǳƵ�ֵ
			String nicheng = nicheng_content.getText().toString().trim();
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					NichengActivity.this);
			BaseActivity.logingUser.setNicheng(nicheng);
			iManagerService.updateNicheng(BaseActivity.logingUser);
			// �ӵ�ǰҳ���з��ص��û����������ȥ����ҳ���е�ֵ���ص�AdminManagerActivity��
			Intent intent = new Intent(NichengActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("nicheng", nicheng);
			startActivity(intent);
			NichengActivity.this.finish();
		}
	}
}
