package com.beber.bdd;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class Repository<T> implements IRepository<T>{
	//BDD
	protected SQLiteDatabase mBDD;
	protected SQLiteOpenHelper mSQLOH;
	
	public Repository() {
		
	}
	
	public void Open(){
		mBDD = mSQLOH.getWritableDatabase();
	}
	
	public void Close(){
		mBDD.close();
	}
}
