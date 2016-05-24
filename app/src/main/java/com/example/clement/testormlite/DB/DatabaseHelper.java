package com.example.clement.testormlite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.clement.testormlite.Bean.Article;
import com.example.clement.testormlite.Bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by macremote on 16/5/23.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME="sqlite_test.db";
    private Map<String,Dao> daos = new HashMap<String,Dao>();
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context){
        //用全局的context
        context = context.getApplicationContext();
        if(instance==null){
            synchronized (DatabaseHelper.class){
                if(instance==null){
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }
    //每张表一个
    private Dao<User,Integer> userDao;

    private DatabaseHelper(Context context){
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource) {
        try{
            //创建User表
            TableUtils.createTable(connectionSource,User.class);
            TableUtils.createTable(connectionSource, Article.class);
        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource, int i, int i1) {

    }

    public synchronized Dao getDao(Class clazz) throws SQLException{
        Dao dao = null;
        String className = clazz.getSimpleName();
        if(daos.containsKey(className)){
            dao=daos.get(className);
        }
        if(dao==null){
            dao = super.getDao(clazz);
            daos.put(className,dao);
        }
        return dao;
    }

    public void close(){
        super.close();
        for(String key:daos.keySet()){
            Dao dao = daos.get(key);
            dao = null ;
        }
    }
}
