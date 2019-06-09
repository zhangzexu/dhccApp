package com.dhcc.DbUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dhcc.Entity.UserInfoEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String TAG ="UserDBHelper";
    private static final String DB_NAME="dhcc.db";
    private static final int DB_VERSION = 1;
    private static UserDBHelper mHelper = null;
    private SQLiteDatabase mDB = null;
    private static final String TABLE_NAME="user_info";

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public UserDBHelper(Context context,  int version) {
        super(context, DB_NAME, null, version);
    }

   public static UserDBHelper getInstance(Context context,int version){
        if(version>0&&mHelper==null){
            mHelper = new UserDBHelper(context,version);
        }else if(mHelper == null){
            mHelper = new UserDBHelper(context);
        }
        return mHelper;
   }

   public SQLiteDatabase openReadLink(){
        if(mDB==null||mDB.isOpen()!=true){
            mDB = mHelper.getReadableDatabase();
        }
        return mDB;
   }
    public SQLiteDatabase openWriteLink(){
        if(mDB==null||mDB.isOpen()!=true){
            mDB = mHelper.getWritableDatabase();
        }
        return mDB;
    }

    public String getDbName() {
        if(mHelper!=null){
            return mHelper.getDatabaseName();
        }else {
            return DB_NAME;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate");
        String drop_sql = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
        db.execSQL(drop_sql);
        StringBuffer create_sql = new StringBuffer();
        create_sql.append("CREATE TABLE IF NOT EXISTS ");
        create_sql.append(TABLE_NAME);
        create_sql.append("(");
        create_sql.append("_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,");
        create_sql.append("_name VARCHAR NOT NULL,");
        create_sql.append("_phone VARCHAR NOT NULL,");
        create_sql.append("_password VARCHAR,");
        create_sql.append("_bank_card VARCHAR NOT NULL");
        create_sql.append(")");
        Log.d(TAG,"create_sql:"+create_sql);
        db.execSQL(create_sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"onUpgrade oldVersion="+oldVersion+",newVersion="+newVersion);
        if(newVersion>1){
            String alter_sql="ALTER TABLE "+TABLE_NAME +" ADD COLUMN "+"_phone VARCHAR;";
            Log.d(TAG,"alter_sql:"+alter_sql);
            db.execSQL(alter_sql);
            alter_sql="ALTER TABLE "+TABLE_NAME +" ADD COLUMN "+"_password VARCHAR;";
            Log.d(TAG,"alter_sql:"+alter_sql);
            db.execSQL(alter_sql);
        }
    }
    public int delete(String condition){
        int count = mDB.delete(TABLE_NAME,condition,null);
        return count;
    }
    public int deleteAll(){
        int count = mDB.delete(TABLE_NAME,"1=1",null);
        return count;
    }

    public long insert(UserInfoEntity userInfoEntity){
        ArrayList<UserInfoEntity> userInfoEntities = new ArrayList<>();
        userInfoEntities.add(userInfoEntity);
        return insert(userInfoEntities);
    }

    public long insert(ArrayList<UserInfoEntity> userInfoEntities){
        long result = -1;
        for (UserInfoEntity us: userInfoEntities) {
//            ArrayList<UserInfoEntity> tempArray = new ArrayList<>();
//            if(us.get_name()!=null&&us.get_name().length()>0){
//                String condition = String.format("name='%s'",us.get_name());
//                tempArray =qurey(condition);
//                if(tempArray.size()>0){
//                    update(us,condition);
//                    result = tempArray.get(0).get_id();
//                    continue;
//                }
//            }
            ContentValues cv = new ContentValues();
            cv.put("_name",us.get_name());
            cv.put("_phone",us.get_phone());
            cv.put("_password",us.get_password());
            cv.put("_bank_card",us.get_bank_card());
            result = mDB.insert(TABLE_NAME,"",cv);
            if(result!=-1){
                return result;
            }
        }
        return result;

    }
    public  int update(UserInfoEntity info,String condition){
        ContentValues cv=new ContentValues();
        cv.put("_name",info.get_name());
        cv.put("_bank_card",info.get_bank_card());
        cv.put("_phone",info.get_phone());
        cv.put("_password",info.get_password());
        int count=mDB.update(TABLE_NAME,cv,condition,null);
        return count;
    }
    public  int update(UserInfoEntity info){
        return update(info,"_id="+info.get_id());
    }
    public  ArrayList<UserInfoEntity> qurey(String condition){
        String sql=String.format("select _id,_name,_bank_card,"+
                "_phone,_password from %s where %s;",TABLE_NAME,condition);
        Log.d(TAG,"query sql:"+sql);
        ArrayList<UserInfoEntity> infoEntityArrayList =new ArrayList<UserInfoEntity>();
        Cursor cursor=mDB.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            for(;;cursor.moveToNext()){
                UserInfoEntity infoEntity=new UserInfoEntity();
                infoEntity.set_id(cursor.getLong(0));
                infoEntity.set_name(cursor.getString(1));
                infoEntity.set_phone(cursor.getString(3));
                infoEntity.set_password(cursor.getString(4));
                infoEntity.set_bank_card(cursor.getString(2));
                infoEntityArrayList.add(infoEntity);
                if(cursor.isLast()==true){
                    break;
                }
            }
        }
        cursor.close();
        return infoEntityArrayList;
    }

    public List<UserInfoEntity> queryByName(String name){
        return qurey(String.format("_name='%s'",name));
    }


    @Override
    public synchronized void close() {
        if(mDB!=null&&mDB.isOpen()==true){
            mDB.close();
            mDB = null;
        }
    }
}
