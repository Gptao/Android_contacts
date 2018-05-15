package com.example.mx.task1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MX on 2018/5/12.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="Txl.db";
    private static final String TBL_NAME="TxlTbl";
    //姓名、手机、电话、邮箱、地址（或更多）
    private static final String CREATE_TBL="CREATE TABLE TxlTbl (_id INTEGER DEFAULT '1' NOT NULL PRIMARY KEY AUTOINCREMENT,Name TEXT,MobileNum TEXT,PhoneNum TEXT,Email TEXT,Address TEXT)";
    private static final String TEST="";
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 2);
        System.out.printf("创建数据库");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     this.db=db;
        db.execSQL(CREATE_TBL);
        //db.execSQL("INSERT INTO TxlTbl VALUES(1,'tgp','18322773806','1996611','taoguangpin@qq.com','NK')");
        //db.execSQL(TEST);
        System.out.printf("创建数据库");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //Insert
    public void Insert(ContentValues values)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TBL_NAME,null,values);
        db.close();
        System.out.printf("数据库插入操作");
    }
    //Search
    public Cursor query()
    {
    System.out.printf("数据库查询方法");
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.query(TBL_NAME,null,null,null,null,null,null);
        return c;
    }
    //Delete
    public void delete(int id)
    {
        System.out.printf("数据库删除方法");
        if(db==null){
            SQLiteDatabase db=getWritableDatabase();
        db.delete(TBL_NAME,"_id=?",new String[]{String.valueOf(id)});
        }
    }
    //Close
    public void close()
    {
        System.out.printf("数据库关闭方法");
        if(db!=null)
            db.close();
    }
}
