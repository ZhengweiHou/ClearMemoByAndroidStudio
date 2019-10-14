package com.example.houzw.clearmemobyandroidstudio.ui;

import com.example.houzw.clearmemobyandroidstudio.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AboutActivity extends Activity {

	private ImageView about_us_button, about_return_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		about_return_btn = (ImageView) findViewById(R.id.about_return_btn);
		about_us_button = (ImageView) findViewById(R.id.about_button);
		about_return_btn.setOnClickListener(new ViewOCL());
		about_us_button.setOnClickListener(new ViewOCL());
	}

	class ViewOCL implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub"
			switch (view.getId()) {
			case R.id.about_return_btn:
				AboutActivity.this.finish();

				break;
			case R.id.about_button:
				final EditText edtName = new EditText(AboutActivity.this);
				new AlertDialog.Builder(AboutActivity.this)
						.setTitle("�������������˵�Ļ���")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setView(edtName)
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Toast.makeText(
												getApplicationContext(),
												"�ύ�ɹ�",
												Toast.LENGTH_LONG).show();
									}
								}).setNegativeButton("ȡ��", null).show();
				break;
			}

		}
	}
}
