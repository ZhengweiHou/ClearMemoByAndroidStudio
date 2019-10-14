package com.example.houzw.clearmemobyandroidstudio.ui;


import com.example.houzw.clearmemobyandroidstudio.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class TimeActivity extends Activity {

	private DatePicker datePicker;
	private TimePicker timePicker;
	private Button btnOk,btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_time);

		datePicker = (DatePicker) findViewById(R.id.datepicker);
		timePicker = (TimePicker) findViewById(R.id.timepicker);
		btnOk = (Button) findViewById(R.id.time_btn_ok);
		btnCancel = (Button) findViewById(R.id.time_btn_cancel);

		// ����12Сʱ��24Сʱ����ʾ��ʽ
		timePicker.setIs24HourView(true);
		
		btnOk.setOnClickListener(new ViewOCL());
		btnCancel.setOnClickListener(new ViewOCL());
		
			
			
	}
	
	class ViewOCL implements View.OnClickListener{

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.time_btn_cancel:
				TimeActivity.this.finish();
				break;
			case R.id.time_btn_ok:
				//����ʱ��
				StringBuffer strbuffer = new StringBuffer();
				
				strbuffer.append(datePicker.getYear()+"-");
				strbuffer.append((datePicker.getMonth()+1)+"-");
				strbuffer.append(datePicker.getDayOfMonth()+" ");
				strbuffer.append(timePicker.getCurrentHour()+":");
				strbuffer.append(timePicker.getCurrentMinute());
				
				
				SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferences(
						"clearmemo_message", MODE_APPEND).edit();

				sharedPreferencesEditor.putString("selectdate", strbuffer.toString());
				
				sharedPreferencesEditor.commit();
				
				TimeActivity.this.finish();
				break;
			}
		}
		
	}

	


}
