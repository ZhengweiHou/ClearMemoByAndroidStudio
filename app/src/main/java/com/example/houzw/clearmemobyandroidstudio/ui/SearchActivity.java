package com.example.houzw.clearmemobyandroidstudio.ui;

import java.util.ArrayList;
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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class SearchActivity extends Activity {

	private EditText searchinput;
	private ListView searchadaptermemolistview;

	private List<Memo> memolist;
	private List<Map<String, ?>> adaptermemolist;
	private List<Type> memotypes;
	private IMemoService memoService;
	private ITypeService typeService;

	private String searchinputcontent;

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		setadaptermemolist();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);

		typeService = new TypeServiceImp(this);
		memoService = new MemoServiceImp(this);

		searchinput = (EditText) findViewById(R.id.search_edt_searchinput);
		searchadaptermemolistview = (ListView) findViewById(R.id.search_listview_searchmemo);

		searchinput.addTextChangedListener(new SearchTextChangeListener());

		// ��ȡ�������ݿ�����
		this.setneeddbdate();

		searchadaptermemolistview.setOnItemClickListener(new ListItemOCL());
		searchadaptermemolistview.setOnItemLongClickListener(new ListItemOCL());

		setadaptermemolist();
	}

	// ��ȡ�������ݿ�����
	private void setneeddbdate() {
		// ��ȡ����
		memotypes = typeService.selectType();
		// ��ȡ��ǰ�û������б���¼
		memolist = memoService.selectMemo(BaseActivity.logingUser.getId());

	}

	// ��������ť����¼�
	public void titleonclick(View view) {
		switch (view.getId()) {
		case R.id.search_imgb_searchbreak:
			// ����
			this.finish();

			break;
		case R.id.search_imgb_searchinputdelet:
			// ɾ������
			searchinput.setText("");

			break;

		default:
			break;
		}
	}

	// search����ı����
	class SearchTextChangeListener implements TextWatcher {

		@Override
		public void afterTextChanged(Editable arg0) {

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			searchinputcontent = searchinput.getText().toString().trim();

			setadaptermemolist();

		}

	}

	// ListView memo��Ŀ�������
	class ListItemOCL implements OnItemClickListener, OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {

			memoitemOnItemLongClick(arg2);

			return true;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			// ��ת��չʾ���棬Я��ѡ���memo����

			jumpToActivity(arg2, DetailActivity.class);

		}

	}

	// memoitem������������
	private void memoitemOnItemLongClick(final int itemindex) {

		Map<String, ?> item = adaptermemolist.get(itemindex);

		// ///////////
		new AlertDialog.Builder(this)
				.setTitle("ѡ���" + item.get("title") + "�Ĳ���")

				.setItems(new String[] { "ɾ��", "�޸�" },
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									// ȷ��ɾ��
									itemdeletensure(itemindex);

									break;
								case 1:
									// �޸�
									itemEdit(itemindex);
									break;
								default:
									break;
								}

							}
						}).setNegativeButton("ȡ��", null).show();
	}

	// ȷ��ɾ��memo
	private void itemdeletensure(final int itemindex) {

		new AlertDialog.Builder(this).setTitle("ȷ��ɾ����")
				.setMessage("��ȷ��ɾ��������Ϣ��").setIcon(R.drawable.ic_launcher)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						memoService.deleteMemo(memolist.get(itemindex).getId());

						setadaptermemolist();
					}

				}).setNegativeButton("ȡ��", null).show();
	}

	// ��ת�޸Ľ��棬���뵱ǰѡ����Ŀ
	private void itemEdit(int itemindex) {

		// ��ת���༭���棬Я��ѡ���memo����
		this.jumpToActivity(itemindex, UpdateActivity.class);

	}

	/**
	 * ��ת����
	 * 
	 * @author HZW_922
	 * 
	 * @param itemindex
	 * @param toActivity
	 */
	private void jumpToActivity(int itemindex, Class toActivity) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("memoitem", memolist.get(itemindex));
		Intent intent = new Intent(SearchActivity.this, toActivity);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	// ���ݲ�����������ListView������
	private void setadaptermemolist() {

		memolist = memoService.selectMemoBySearchContent(searchinputcontent);

		adaptermemolist = new ArrayList<Map<String, ?>>();
		for (int i = 0; i < memolist.size(); i++) {
			Type type = null;

			for (Type typetemp : memotypes) {
				if (typetemp.getId() == memolist.get(i).getType_id()) {
					type = typetemp;
					break;
				}
			}
			String title = null;
			if (memolist.get(i).getTitle().length() > 7) {
				title = memolist.get(i).getTitle().substring(0, 6) + "...";
			} else {
				title = memolist.get(i).getTitle();
			}

			Map<String, Object> item = new HashMap<String, Object>();
			item.put("type", type.getIcon());
			item.put("title", title);
			item.put("createdate", memolist.get(i).getCreatedate());
			item.put("content", memolist.get(i).getContentsummary());

			adaptermemolist.add(item);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, adaptermemolist,
				R.layout.listitem_style1, new String[] { "type", "title",
						"createdate", "content" }, new int[] {
						R.id.memoitem_img_memotype,
						R.id.txtview_memoitem_title,
						R.id.txtview_memoitem_date,
						R.id.txtview_memoitem_context });

		searchadaptermemolistview.setAdapter(adapter);
	}
}
