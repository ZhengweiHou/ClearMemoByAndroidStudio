package com.example.houzw.clearmemobyandroidstudio.service;

import com.example.houzw.clearmemobyandroidstudio.domain.po.User;

public interface IUpdateUserMessage {
	// �ǳƵ��޸�
	public User updateNicheng(User user);

	// �绰���޸�
	public User updateTel(User user);

	// �Ա���޸�
	public User updateSex(User user);

	// �������޸�
	public User updateAddress(User user);

	// ����ǩ�����޸�
	public User updateGexingqianming(User user);
}
