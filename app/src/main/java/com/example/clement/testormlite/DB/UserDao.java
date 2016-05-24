package com.example.clement.testormlite.DB;

import android.content.Context;

import com.example.clement.testormlite.Bean.User;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by macremote on 16/5/24.
 */
public class UserDao {
    private Context context;
    private Dao<User,Integer> userDao ;
    private DatabaseHelper helper;
    public UserDao(Context context){
        this.context = context;
        try{
            helper = DatabaseHelper.getHelper(context);
            userDao = helper.getDao(User.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //增删查改 操作

    /**插入数据
     * @param user
     */
    public void add(User user){
        try{
            userDao.create(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**根据ID返回查询到的User对象
     * @param id
     * @return
     */
    public User get(int id){
        try{
            return userDao.queryForId(id);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
