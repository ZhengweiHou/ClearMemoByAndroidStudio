package com.example.houzw.clearmemobyandroidstudio.service;

import java.util.List;

import com.example.houzw.clearmemobyandroidstudio.domain.po.Type;

public interface ITypeService {
	
	//查找所有分类type
	public List<Type> selectType();

}
