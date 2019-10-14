package com.example.houzw.clearmemobyandroidstudio.ui;

import com.example.houzw.clearmemobyandroidstudio.R;
import com.example.houzw.clearmemobyandroidstudio.BaseActivity;
import com.example.houzw.clearmemobyandroidstudio.service.IRegisterServie;
import com.example.houzw.clearmemobyandroidstudio.service.impl.UserServiceImpl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText register_victualname, register_usn, register_pwd;
	private ImageView registerBack;
	private Button btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		register_victualname = (EditText) findViewById(R.id.register_victualname);
		register_usn = (EditText) findViewById(R.id.register_usn);
		register_pwd = (EditText) findViewById(R.id.register_pwd);

		btnRegister = (Button) findViewById(R.id.register_surebtn);
		registerBack = (ImageView) findViewById(R.id.register_return_btn);

		btnRegister.setOnClickListener(new ViewOCL1());
		registerBack.setOnClickListener(new ViewOCL());

		BaseActivity.nowActivity = this;
	}

	class ViewOCL implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			RegisterActivity.this.finish();

		}
	}

	class ViewOCL1 implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			Intent intent = new Intent(RegisterActivity.this,
					LoginActivity.class);
			String nicheng = register_victualname.getText().toString().trim();
			String account = register_usn.getText().toString().trim();
			String password = register_pwd.getText().toString().trim();

			if (!nicheng.equals("")) {
				if (!account.equals("")) {
					if (!account.equals("")) {
						IRegisterServie iRegisterServie = new UserServiceImpl(
								RegisterActivity.this);
						int a = iRegisterServie.register(account, password,
								nicheng);
						switch (a) {
						case 0:
							Toast.makeText(RegisterActivity.this, "ע��ɹ�",
									Toast.LENGTH_SHORT).show();

							SharedPreferences.Editor sharedPreferencesEditor = RegisterActivity.this.getSharedPreferences(
									"clearmemo_message", MODE_APPEND).edit();
							sharedPreferencesEditor.putString("account",
									account);
							sharedPreferencesEditor.putString("password",
									password);
							sharedPreferencesEditor.commit();

							startActivity(intent);
							BaseActivity.nowActivity.finish();
							break;
						case 1:
							Toast.makeText(RegisterActivity.this,
									"�˺ų��ȱ�����9��10λ", Toast.LENGTH_SHORT).show();
							break;
						case 2:
							Toast.makeText(RegisterActivity.this, "�����˺��Ѿ�������",
									Toast.LENGTH_SHORT).show();
							break;
						case 3:
							Toast.makeText(RegisterActivity.this,
									"���볤�ȱ�����6��16λ", Toast.LENGTH_SHORT).show();
							break;
						default:
							break;
						}
					} else {
						Toast.makeText(RegisterActivity.this, "�������벻��Ϊ��",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(RegisterActivity.this, "�����˻�����Ϊ��",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(RegisterActivity.this, "�����ǳƲ���Ϊ��",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

}
