package com.example.houzw.clearmemobyandroidstudio.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.example.houzw.clearmemobyandroidstudio.dao.IMemoDao;
import com.example.houzw.clearmemobyandroidstudio.dao.impl.MemoDaoImp;
import com.example.houzw.clearmemobyandroidstudio.domain.po.Memo;
import com.example.houzw.clearmemobyandroidstudio.service.IMemoService;

public class MemoServiceImp implements IMemoService {
	IMemoDao memodao;
	Context context;

	public MemoServiceImp(Context context) {
		super();
		this.memodao = new MemoDaoImp(context);
		this.context = context;
	}

	@Override
	public void addMemo(Memo memo) {
		memodao.insertmemo(this.saveMemoContentAndgetDbMemo(memo));

	}

	@Override
	public List<Memo> selectMemo(int uid) {

		return memodao.selectMemoByUid(uid);
	}

	@Override
	public void updateMemo(Memo memo) {

		Memo dbmemo = this.saveMemoContentAndgetDbMemo(memo);
		memodao.updatememo(dbmemo);
	}

	@Override
	public void deleteMemo(int id) {
		memodao.deleteMemoByid(id);

	}

	@Override
	public String getmemocontent(Memo memo) {
		String content = "";
		try {
			FileInputStream fis = context.openFileInput(memo.getPath());

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String buffer;
			while ((buffer = br.readLine()) != null)
				content += buffer + "\n";
			br.close();
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	@Override
	public List<Memo> selectMemobyuidandtype(int uid, int typeid) {
		return memodao.selectMemoByUidAndTypeid(uid, typeid);

	}

	private Memo saveMemoContentAndgetDbMemo(Memo memo) {
		// ��memo���ݴ�ŵ�һ��˽���ļ��У�ȡ���ݵ�ǰʮ���ֶ���Ϊ����ժҪ���滻���ݣ�����dao��洢�����ݿ���
		// ȡ������
		String content = memo.getContentsummary();

		String contentTemp = content.replaceAll("\\s*", "");

		if (contentTemp.length() > 13) {
			// ��ȡ����ժҪ
			memo.setContentsummary(contentTemp.substring(0, 10) + "......");
		} else {
			memo.setContentsummary(contentTemp.toString());
		}

		// ���ļ��浽��Ӧ�ļ���(�ļ����ɵ�ǰʱ������),
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// ����memo�Ĵ���ʱ��
		String createdate = df.format(new Date());

		memo.setCreatedate(createdate);

		if (memo.getPath() == null) {
			// �ļ�λ��
			String path = "memo-" + new Date().toString() + ".txt";
			// ����memo�е�path����
			memo.setPath(path);
		}

		try {
			// ��ȡ�����
			FileOutputStream fos = context.openFileOutput(memo.getPath(),
					Context.MODE_APPEND);
			// ͨ�������д��content����
			fos.write(content.getBytes());
			// flushһ������������������д���ļ�
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memo;
	}

	@Override
	public List<Memo> selectMemoBySearchContent(String searchinputcontent) {

		return memodao.selectMemoSearchContent(searchinputcontent);
	}

	@Override
	public Memo selectMemobyid(int id) {
		// TODO Auto-generated method stub
		return memodao.selectMemoByid(id);
	}

}
