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

public class GexingqianmingActivity extends Activity {

	private ImageView gxqm_return_btn;
	private EditText gxqm_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gexingqianming);

		gxqm_return_btn = (ImageView) findViewById(R.id.gexingqianming_return_btn);
		gxqm_edit = (EditText) findViewById(R.id.gexingqianming_content);
		gxqm_return_btn.setOnClickListener(new ViewOCL());

		Intent intent = getIntent();
		String gexingqianming1 = intent.getStringExtra("gexingqianming1");
		gxqm_edit.setText(gexingqianming1);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			String gexingqianming = gxqm_edit.getText().toString().trim();
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					GexingqianmingActivity.this);
			BaseActivity.logingUser.setGexingqianming(gexingqianming);
			iManagerService.updateGexingqianming(BaseActivity.logingUser);
			// �ӵ�ǰҳ���з��ص��û����������ȥ����ҳ���е�ֵ���ص�AdminManagerActivity��
			Intent intent = new Intent(GexingqianmingActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("gxqm", gexingqianming);
			startActivity(intent);
			GexingqianmingActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);

	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// ��ȡ�ǳ��������ǳƵ�ֵ
			String gexingqianming = gxqm_edit.getText().toString().trim();
			// ������ذ�ť�󣬵��õײ�������ǰҳ���ֵ�浽���ݿ���
			IUpdateUserMessage iManagerService = new UserServiceImpl(
					GexingqianmingActivity.this);
			BaseActivity.logingUser.setGexingqianming(gexingqianming);
			iManagerService.updateGexingqianming(BaseActivity.logingUser);
			// �ӵ�ǰҳ���з��ص��û����������ȥ����ҳ���е�ֵ���ص�AdminManagerActivity��
			Intent intent = new Intent(GexingqianmingActivity.this,
					AdminManagerActivity.class);
			intent.putExtra("gxqm", gexingqianming);
			startActivity(intent);
			GexingqianmingActivity.this.finish();

		}

	}

}
