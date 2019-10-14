package com.example.houzw.clearmemobyandroidstudio.ui;

import com.example.houzw.clearmemobyandroidstudio.R;
import com.example.houzw.clearmemobyandroidstudio.domain.po.Memo;
import com.example.houzw.clearmemobyandroidstudio.service.IMemoService;
import com.example.houzw.clearmemobyandroidstudio.service.impl.MemoServiceImp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailActivity extends Activity {

	private LinearLayout detial_edit_menu;
	private RelativeLayout detail_relative;
	private TextView detial_update, detial_detele;
	private ImageView detail_img1, detail_img2;
	private TextView detail_content, txtview_showmemotitle;
	private IMemoService memoService;
	private Memo reciveMemo;
	int flag;

	private Intent reciveIntent;

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		showMemoItemDateInActivity(reciveMemo);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detail);
		memoService = new MemoServiceImp(this);

		// ��ȡ�ؼ�
		findView();

		// ����TextView����Ч��
		detail_content.setMovementMethod(ScrollingMovementMethod.getInstance());

		detial_edit_menu.setOnClickListener(new OCLick());
		detail_img1.setOnClickListener(new OCLick());
		detail_img2.setOnClickListener(new OCLick());
		detail_relative.setOnClickListener(new OCLick());
		detail_content.setOnClickListener(new OCLick());
		detial_update.setOnClickListener(new OCLick());
		detial_detele.setOnClickListener(new OCLick());

		flag = 0;

		reciveIntent = getIntent();
		Bundle bundleTemp = reciveIntent.getExtras();
		reciveMemo = (Memo) bundleTemp.getSerializable("memoitem");

		showMemoItemDateInActivity(reciveMemo);

	}

	private void showMemoItemDateInActivity(Memo reciveMemo) {

		reciveMemo = memoService.selectMemobyid(reciveMemo.getId());

		txtview_showmemotitle.setText(reciveMemo.getTitle());

		String content = null;

		content = memoService.getmemocontent(reciveMemo);

		detail_content.setText(content);

	}

	/**
	 * ��ȡ�ؼ�
	 */
	private void findView() {
		detial_edit_menu = (LinearLayout) findViewById(R.id.detail_edit_menu);
		detail_img1 = (ImageView) findViewById(R.id.detail_img1);
		detail_img2 = (ImageView) findViewById(R.id.detail_img2);
		detail_relative = (RelativeLayout) findViewById(R.id.detail_relative);
		detail_content = (TextView) findViewById(R.id.detail_content);
		detial_update = (TextView) findViewById(R.id.detail_update);
		detial_detele = (TextView) findViewById(R.id.detail_detele);

		txtview_showmemotitle = (TextView) findViewById(R.id.detail_title_showmemotitle);
	}

	/**
	 * ɾ����ǰmemo
	 */
	private void itemdeletensure() {
		new AlertDialog.Builder(this).setTitle("ȷ��ɾ����")
				.setMessage("��ȷ��ɾ��������Ϣ��").setIcon(R.drawable.ic_launcher)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						memoService.deleteMemo(reciveMemo.getId());
						startActivity(new Intent(DetailActivity.this,
								MainActivity.class));
					}
				}).setNegativeButton("ȡ��", null).show();

	}

	class OCLick implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.detail_edit_menu:
				view.bringToFront();
				break;
			case R.id.detail_img1:
				Intent intent = new Intent(DetailActivity.this,
						MainActivity.class);
				startActivity(intent);
				break;
			case R.id.detail_img2:
				if (flag == 0) {
					flag = 1;
					detial_edit_menu.setVisibility(View.VISIBLE);
				} else if (flag == 1) {
					flag = 0;
					detial_edit_menu.setVisibility(View.GONE);
				}
				break;
			case R.id.detail_relative:
				detial_edit_menu.setVisibility(View.GONE);
				break;
			case R.id.detail_content:
				detial_edit_menu.setVisibility(View.GONE);
				break;
			case R.id.detail_update:
				// �޸�
				jumpToActivity(UpdateActivity.class);
				break;
			case R.id.detail_detele:
				// ȷ��ɾ��
				itemdeletensure();

				break;
			}
		}

	}

	private void jumpToActivity(Class toActivity) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("memoitem", reciveMemo);
		Intent intent = new Intent(DetailActivity.this, toActivity);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
