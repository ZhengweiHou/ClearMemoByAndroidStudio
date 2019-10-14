package com.example.houzw.clearmemobyandroidstudio.dao;

import java.util.List;

import com.example.houzw.clearmemobyandroidstudio.domain.po.Memo;

public interface IMemoDao {

	// ��ӱ���¼memo
	void insertmemo(Memo memo);

	// ���������û�memo
	List<Memo> selectMemoByUid(int uid);

	// �޸�memo
	void updatememo(Memo memo);

	// ɾ��memo
	void deleteMemoByid(int id);

	// ���������û�ĳ����memo
	List<Memo> selectMemoByUidAndTypeid(int uid, int typeid);

	/**
	 * ������������ģ������memo
	 * @param searchinputcontent
	 * @return
	 */
	List<Memo> selectMemoSearchContent(String searchinputcontent);

	Memo selectMemoByid(int id);
}
