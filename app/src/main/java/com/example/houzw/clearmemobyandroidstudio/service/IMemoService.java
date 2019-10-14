package com.example.houzw.clearmemobyandroidstudio.service;

import java.util.List;

import com.example.houzw.clearmemobyandroidstudio.domain.po.Memo;

public interface IMemoService {
	// ��ӱ�����Ŀ
	public void addMemo(Memo memo);

	// �����û�memo
	public List<Memo> selectMemo(int uid);

	// �޸�memo
	public void updateMemo(Memo memo);

	// �޸�memo
	public void deleteMemo(int id);

	// ��ȡĿ��memo������
	public String getmemocontent(Memo memo);

	// �����û���ĳ����memo
	public List<Memo> selectMemobyuidandtype(int uid, int typeid);


	/**
	 * ������������ģ������memo
	 * @param searchinputcontent
	 * @return 
	 */
	public List<Memo> selectMemoBySearchContent(String searchinputcontent);

	public Memo selectMemobyid(int id);
	
}
