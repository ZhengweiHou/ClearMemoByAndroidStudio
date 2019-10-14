package com.example.houzw.clearmemobyandroidstudio.service.impl;

import java.util.List;

import android.content.Context;

import com.example.houzw.clearmemobyandroidstudio.dao.ITypeDao;
import com.example.houzw.clearmemobyandroidstudio.dao.impl.TypeDaoImp;
import com.example.houzw.clearmemobyandroidstudio.domain.po.Type;
import com.example.houzw.clearmemobyandroidstudio.service.ITypeService;

public class TypeServiceImp implements ITypeService {
	
	ITypeDao typeDao;

	public TypeServiceImp(Context context) {
		super();
		typeDao = new TypeDaoImp(context);
	}


	@Override
	public List<Type> selectType() {

		
		return typeDao.selectTypeAll();
	}
	

}
