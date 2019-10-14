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
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	// ����ؼ�
	private SlidingMenu mMenu;
	private ListView mainadaptermemolistView;
	private ImageView slimenutypeimg, main_imgv_userphone;
	private TextView slimenutypecontent, main_useraccount;
	public static ImageView showSlid;
	boolean isChanged = false;

	// ����¼��¼
	private List<Memo> memolist;
	private List<Map<String, ?>> adaptermemolist;
	// ѡ�����͵��±�
	private int selectedmemotypeIndex = 0;
	// ��������
	private List<Type> memotypes;
	// service
	private ITypeService typeService;
	private IMemoService memoService;

	@Override
	protected void onRestart() {

		super.onRestart();

		main_useraccount.setText(BaseActivity.logingUser.getNicheng());

		this.updateListViewContent();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// ��ȡ�ؼ�
		this.findView();

		typeService = new TypeServiceImp(this);
		memoService = new MemoServiceImp(this);

		main_useraccount.setText(BaseActivity.logingUser.getNicheng());

		// ��ȡ�������ݿ�����
		this.setneeddbdate();

		// ��������������Դ
		this.updateListViewContent();

		mainadaptermemolistView.setOnItemClickListener(new ListItemOCL());
		mainadaptermemolistView.setOnItemLongClickListener(new ListItemOCL());

	}

	// ��ȡ�ؼ�
	private void findView() {
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
		mainadaptermemolistView = (ListView) findViewById(R.id.main_listview_memoitem);
		slimenutypeimg = (ImageView) findViewById(R.id.main_imgv_slimenutypeimg);
		slimenutypecontent = (TextView) findViewById(R.id.main_txt_slimenutypecontent);
		main_imgv_userphone = (ImageView) findViewById(R.id.main_imgv_userphone);
		main_useraccount = (TextView) findViewById(R.id.main_useraccount);
		showSlid = (ImageView) findViewById(R.id.main_showsolidmenu);
	}

	// ��ȡ�������ݿ�����
	private void setneeddbdate() {
		// ��ȡ����
		memotypes = typeService.selectType();
		// ��ȡ��ǰ�û������б���¼
		memolist = memoService.selectMemo(BaseActivity.logingUser.getId());

	}

	// ͨ���ƶ�memo��������ListView������
	private void setadaptermemolist(List<Memo> memolist) {
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

		mainadaptermemolistView.setAdapter(adapter);
	}

	// ����Ĭ�ϵ����¼�
	public void onclick(View view) {

		switch (view.getId()) {
		case R.id.main_showsolidmenu:

			if (isChanged) {
				mMenu.toggle();
				showSlid.setImageResource(R.drawable.slipmenu2);
			} else {
				mMenu.toggle();
				showSlid.setImageResource(R.drawable.slipmenu1);
			}

			isChanged = !isChanged;

			break;

		// �˻�
		case R.id.main_usermessage:
			Intent intent = new Intent(MainActivity.this,
					AdminManagerActivity.class);
			startActivity(intent);
			break;
		case R.id.main_selecttype:
			// ѡ���¼����
			selectType();
			break;
		case R.id.main_search:
			// ����
			startActivity(new Intent(MainActivity.this, SearchActivity.class));
			break;
		case R.id.main_aboutuse:
			// ��������
			startActivity(new Intent(MainActivity.this, AboutActivity.class));
			break;
		case R.id.main_zhuxiao:
			// ע��
			this.logout();
			break;
		case R.id.main_exit:
			// �˳�
			this.finish();
			break;

		case R.id.main_imgbtn_addmemo:

			// ��ӱ���
			// ////////////////////////////////////////////
			intent = new Intent(MainActivity.this, AddActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("typeindex", selectedmemotypeIndex - 1);
			intent.putExtras(bundle);

			// ////////////////////////////////////
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	/**
	 * ע������
	 */
	private void logout() {
		BaseActivity.logingUser = null;
		startActivity(new Intent(MainActivity.this, LoginActivity.class));
		this.finish();

	}

	// ѡ�����ͷ���
	private void selectType() {
		// ////////////////��ʼ������
		// final String[] arrayType = new String[] { "All", "ѧϰ", "����", "����" };

		String[] arrayType = new String[memotypes.size() + 1];
		arrayType[0] = "All";

		for (int i = 1; i < arrayType.length; i++) {
			arrayType[i] = memotypes.get(i - 1).getName();
		}

		Dialog dialog = new AlertDialog.Builder(this)
				.setTitle("ѡ��������")
				.setSingleChoiceItems(arrayType, selectedmemotypeIndex,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								selectedmemotypeIndex = which;
								updateListViewContent();
							}
						}).setPositiveButton("ȷ��", null).create();

		if (dialog != null) {
			dialog.show();
			dialog = null;
		}
	}

	/**
	 * ���µ�ǰ��������ʾ��memo����
	 */
	private void updateListViewContent() {

		if (selectedmemotypeIndex == 0) {
			memolist = memoService.selectMemo(BaseActivity.logingUser.getId());
			slimenutypeimg.setImageResource(R.drawable.allmemo);
			slimenutypecontent.setText("All");

		} else {
			memolist = memoService.selectMemobyuidandtype(
					BaseActivity.logingUser.getId(),
					memotypes.get(selectedmemotypeIndex - 1).getId());

			slimenutypeimg.setImageResource(memotypes.get(
					selectedmemotypeIndex - 1).getIcon());
			slimenutypecontent.setText(memotypes.get(selectedmemotypeIndex - 1)
					.getName());

		}
		// ����ListView������
		setadaptermemolist(memolist);
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

						updateListViewContent();
					}

				}).setNegativeButton("ȡ��", null).show();
	}

	// ��ת�޸Ľ��棬���뵱ǰѡ����Ŀ
	private void itemEdit(int itemindex) {

		// ��ת���༭���棬Я��ѡ���memo����
		this.jumpToActivity(itemindex, UpdateActivity.class);

	}

	/**
	 * ��������ת����
	 * 
	 * @author HZW_922
	 * 
	 * @param itemindex
	 * @param toActivity
	 */
	private void jumpToActivity(int itemindex, Class toActivity) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("memoitem", memolist.get(itemindex));
		Intent intent = new Intent(MainActivity.this, toActivity);
		intent.putExtras(bundle);
		startActivity(intent);
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

	public void toggleMenu(View view) {
		mMenu.toggle();
	}
}
