package com.example.houzw.clearmemobyandroidstudio.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.houzw.clearmemobyandroidstudio.dao.ITypeDao;
import com.example.houzw.clearmemobyandroidstudio.domain.po.Type;
import com.example.houzw.clearmemobyandroidstudio.util.SqliteHelpUtil;

public class TypeDaoImp implements ITypeDao {

	SQLiteDatabase db;
	Context context;

	public TypeDaoImp(Context context) {
		super();
		this.context = context;
	}

	@Override
	public List<Type> selectTypeAll() {
		// TODO Auto-generated method stub
		// �����ݿ� ��-��ѯ
		db = SqliteHelpUtil.getSQLiteReadableDatabase(context);

		// sql���
		String sql = "select * from types";

		List<Type> list = new ArrayList<Type>();
		// ִ�в�ѯsql��䣬�������α�����
		Cursor cursor = db.rawQuery(sql, null);
		// �����α�
		while (cursor.moveToNext()) {
			// ��ȡ��һ�е�ֵ
			int id = cursor.getInt(0);
			// ��ȡ�ڶ��е�ֵ
			int icon = cursor.getInt(1);
			// ��ȡ�����е�ֵ
			String name = cursor.getString(2);

			Type type = new Type(id, icon, name);
			list.add(type);
		}

		db.close();
		return list;

	}



}
