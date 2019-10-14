package com.example.houzw.clearmemobyandroidstudio.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.houzw.clearmemobyandroidstudio.R;
import com.example.houzw.clearmemobyandroidstudio.BaseActivity;
import com.example.houzw.clearmemobyandroidstudio.domain.po.Memo;
import com.example.houzw.clearmemobyandroidstudio.domain.po.Type;
import com.example.houzw.clearmemobyandroidstudio.service.IMemoService;
import com.example.houzw.clearmemobyandroidstudio.service.ITypeService;
import com.example.houzw.clearmemobyandroidstudio.service.impl.MemoServiceImp;
import com.example.houzw.clearmemobyandroidstudio.service.impl.TypeServiceImp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UpdateActivity extends Activity {

	private LinearLayout llTime;
	private Gallery addGallery;
	private ImageView imgBack, imgSave;
	private EditText update_edtTitle, update_edtContent;
	private TextView update_time01, update_time02;

	private ITypeService typeservice;
	private IMemoService memoservice;

	private Memo creatememo;
	private Memo receivememo;

	// memo���ͼ���
	private List<Type> memotypes;

	// addGallery������Դ
	private List<Map<String, ?>> typelist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		typeservice = new TypeServiceImp(this);
		memoservice = new MemoServiceImp(this);
		creatememo = new Memo();

		// ��ȡ�ؼ�
		llTime = (LinearLayout) findViewById(R.id.update_linear01);
		imgBack = (ImageView) findViewById(R.id.update_imgView_back);
		imgSave = (ImageView) findViewById(R.id.update_imgView_save);
		addGallery = (Gallery) findViewById(R.id.update_type_gallery);
		update_edtTitle = (EditText) findViewById(R.id.update_edtTitle);
		update_edtContent = (EditText) findViewById(R.id.update_edtContent);
		update_time01 = (TextView) findViewById(R.id.update_time01);
		update_time02 = (TextView) findViewById(R.id.update_time02);

		// �󶨿ؼ�����
		llTime.setOnClickListener(new ViewOCL());
		imgBack.setOnClickListener(new ViewOCL());
		imgSave.setOnClickListener(new ViewOCL());

		// addGallery��������
		setaddGalleryAdapter();

		// ��gallery���ü������ڲ���OnitemclickListen��������
		addGallery.setOnItemSelectedListener(new typeGalleryItemSelectListener());

		// ��ȡ����memo�����ý����е�����
		setActivityDate();

		// ��ʼ��creatememo
		creatememo = receivememo;

	}

	@Override
	protected void onRestart() {

		super.onRestart();

		SharedPreferences sharedPreferences = getSharedPreferences(
				"clearmemo_message", MODE_APPEND);

		String selectdate = sharedPreferences.getString("selectdate",
				getnowdate());

		update_time01.setText(getnowdate());
		update_time02.setText(selectdate);

		creatememo.setClockdate(selectdate);
	}

	/**
	 * ��ȡ��ǰʱ��
	 * 
	 * @return YYYY-MM-DD hh��mm
	 */
	private String getnowdate() {

		String nowdate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(new Date());
		return nowdate;

	}

	/**
	 * ��ȡ����memo�����ý����е�����
	 */
	private void setActivityDate() {
		// ��ȡintent��ȡ��memo����
		Intent receiveintent = getIntent();
		// ��ȡ��Intent��Я��������
		Bundle bundle = receiveintent.getExtras();
		// ��bundle���ݰ���ȡ������
		receivememo = (Memo) bundle.getSerializable("memoitem");

		// ���ñ���
		update_edtTitle.setText(receivememo.getTitle());
		addGallery.setSelection(getMemosTypeIndexInmemotypes(receivememo));
		update_edtContent.setText(memoservice.getmemocontent(receivememo));
		update_time01.setText(receivememo.getCreatedate());
		update_time02.setText(receivememo.getClockdate());

	}

	/**
	 * ��ȡmemotype��memotypes�����е�λ��
	 * 
	 * @return
	 */
	private int getMemosTypeIndexInmemotypes(Memo memo) {
		for (int i = 0; i < memotypes.size(); i++) {
			if (memotypes.get(i).getId() == memo.getType_id()) {
				return i;
			}
		}
		return 0;

	}

	/**
	 * ��addGallery���������
	 */
	private void setaddGalleryAdapter() {
		// ����������SimpleAdapter�����һ��������item�����ļ���

		// ��ȡ���ݿ���������
		memotypes = typeservice.selectType();

		typelist = new ArrayList<Map<String, ?>>();

		for (Type type : memotypes) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("typeImg", type.getIcon());
			typelist.add(item);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, typelist,
				R.layout.typegallery_item, new String[] { "typeImg" },
				new int[] { R.id.typeitem_img });

		// ��gallery��������
		addGallery.setAdapter(adapter);

	}

	class ViewOCL implements View.OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub

			switch (view.getId()) {
			case R.id.update_linear01:
				Intent intent = new Intent(UpdateActivity.this,
						TimeActivity.class);
				startActivity(intent);
				break;
			case R.id.update_imgView_back:
				UpdateActivity.this.finish();

				break;
			case R.id.update_imgView_save:
				// ����
				// ͨ���ؼ���ȡmemo��Ϣ�����memo��������Ե����ã�memo�������̨�������ݿ�
				creatememo
						.setTitle(update_edtTitle.getText().toString().trim());
				creatememo.setContentsummary(update_edtContent.getText()
						.toString());
				
				creatememo.setUser_id(BaseActivity.logingUser.getId());
				creatememo.setCreatedate(getnowdate());

				String str = creatememo.getTitle();

				if (creatememo.getTitle().equals("")) {
					Toast.makeText(UpdateActivity.this, "Title����Ϊ��",
							Toast.LENGTH_LONG).show();
				} else if (creatememo.getTitle().length() > 15) {
					Toast.makeText(UpdateActivity.this, "Title���ȳ���15",
							Toast.LENGTH_LONG).show();
				} else {

					memoservice.updateMemo(creatememo);

					UpdateActivity.this.finish();

				}
				break;
			}
		}

	}

	class typeGalleryItemSelectListener implements
			AdapterView.OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			creatememo.setType_id(memotypes.get(arg2).getId());

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}

}
