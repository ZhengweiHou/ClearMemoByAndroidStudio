package com.example.houzw.clearmemobyandroidstudio.service.impl;

import android.content.Context;

import com.example.houzw.clearmemobyandroidstudio.BaseActivity;
import com.example.houzw.clearmemobyandroidstudio.dao.IUserDao;
import com.example.houzw.clearmemobyandroidstudio.dao.impl.UserDaoImpl;
import com.example.houzw.clearmemobyandroidstudio.domain.po.User;
import com.example.houzw.clearmemobyandroidstudio.service.ILoginService;
import com.example.houzw.clearmemobyandroidstudio.service.IManagerService;
import com.example.houzw.clearmemobyandroidstudio.service.IRegisterServie;
import com.example.houzw.clearmemobyandroidstudio.service.IUpdateUserMessage;

public class UserServiceImpl implements ILoginService, IManagerService,
		IRegisterServie, IUpdateUserMessage {
	private User user;
	IUserDao iUserDao;
	Context context;

	public UserServiceImpl(Context context) {
		super();
		iUserDao = new UserDaoImpl(context);
		this.context = context;
	}

	@Override
	public User updateNicheng(User user) {
		// TODO Auto-generated method stub
		iUserDao.updatenicheng(user);
		BaseActivity.logingUser = iUserDao.findUser(user.getAccount());
		return BaseActivity.logingUser;
	}

	@Override
	public User updateTel(User user) {
		// TODO Auto-generated method stub
		iUserDao.updatetel(user);
		BaseActivity.logingUser = iUserDao.findUser(user.getAccount());
		return null;
	}

	@Override
	public User updateSex(User user) {
		// TODO Auto-generated method stub
		iUserDao.uppdatesex(user);
		BaseActivity.logingUser = iUserDao.findUser(user.getAccount());
		return null;
	}

	@Override
	public User updateAddress(User user) {
		// TODO Auto-generated method stub
		iUserDao.updateaddress(user);
		BaseActivity.logingUser = iUserDao.findUser(user.getAccount());
		return null;
	}

	@Override
	public User updateGexingqianming(User user) {
		// TODO Auto-generated method stub
		iUserDao.updategexingqianming(user);
		BaseActivity.logingUser = iUserDao.findUser(user.getAccount());
		return null;
	}

//	@Override
//	public User select(User user) {
//		// TODO Auto-generated method stub
//
//		return null;
//	}
	@Override
	public User select(User user){

		return null;
	}

	@Override
	public int register(String account, String password, String nicheng) {
		// TODO Auto-generated method stub
		user = iUserDao.findUser(account);
		if (account.length() > 9 && account.length() < 10) {
			return 1;// 账号长度必须在9到10位
		} else {
			if (password.length() > 6 && password.length() < 16) {
				if (user == null) {
					iUserDao.addUser(account, password, nicheng);
					return 0;// 账号登陆成功
				} else {
					return 2;// 账号已经存在了
				}
			} else {
				return 3;// 密码长度必须是6到16位
			}
		}
	}

	@Override
	public boolean Login(String account, String password) {
		// TODO Auto-generated method stub
		user = iUserDao.findUser(account);
		if (user != null) {
			BaseActivity.logingUser = user;
			if (BaseActivity.logingUser.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
