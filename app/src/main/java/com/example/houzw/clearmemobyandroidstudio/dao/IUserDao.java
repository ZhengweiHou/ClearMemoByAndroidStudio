package com.example.houzw.clearmemobyandroidstudio.dao;

import com.example.houzw.clearmemobyandroidstudio.domain.po.User;

public interface IUserDao {
	// ����û�
	public void addUser(String account, String password, String nicheng);

	// ��ѯ�û�
	public User findUser(String account);

	// �޸��û��ǳ�
	public void updatenicheng(User user);

	// �޸��û�����
	public void updatepassword(User user);

	// �޸��û��绰
	public void updatetel(User user);

	// �޸��û��Ա�
	public void uppdatesex(User user);

	// �޸��û�����
	public void updateaddress(User user);

	// �޸��û�����ǩ��
	public void updategexingqianming(User user);
}
