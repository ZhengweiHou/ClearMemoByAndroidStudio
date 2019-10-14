package com.example.houzw.clearmemobyandroidstudio.dao;

import java.util.List;

import com.example.houzw.clearmemobyandroidstudio.domain.po.Memo;

public interface IMemoDao {

	// 添加备忘录memo
	void insertmemo(Memo memo);

	// 查找所有用户memo
	List<Memo> selectMemoByUid(int uid);

	// 修改memo
	void updatememo(Memo memo);

	// 删除memo
	void deleteMemoByid(int id);

	// 查找所有用户某类型memo
	List<Memo> selectMemoByUidAndTypeid(int uid, int typeid);

	/**
	 * 根据搜索内容模糊查找memo
	 * @param searchinputcontent
	 * @return
	 */
	List<Memo> selectMemoSearchContent(String searchinputcontent);

	Memo selectMemoByid(int id);
}
