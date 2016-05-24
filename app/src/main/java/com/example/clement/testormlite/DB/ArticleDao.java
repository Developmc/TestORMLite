package com.example.clement.testormlite.DB;

import android.content.Context;

import com.example.clement.testormlite.Bean.Article;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by macremote on 16/5/24.
 */
public class ArticleDao {
    private Dao<Article,Integer> articleDao;
    private DatabaseHelper helper;

    public ArticleDao(Context context){
        try{
            helper = DatabaseHelper.getHelper(context);
            articleDao = helper.getDao(Article.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**添加一个Article
     * @param article
     */
    public void add(Article article){
        try{
            articleDao.create(article);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**通过ID得到article，并对外键User赋值
     * @param id
     * @return
     */
    public Article getArticleWithUser(int id){
        Article article = null;
        try{
            article = articleDao.queryForId(id);
            helper.getDao(Article.class).refresh(article.getUser());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return article;
    }
    /**通过ID得到article
     * @param id
     * @return
     */
    public Article get(int id){
        Article article = null;
        try{
            article = articleDao.queryForId(id);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return article;
    }

    /**通过UserId获取所有的Article
     * @param userId
     * @return
     */
    public List<Article> listByUserId(int userId){
        try{
            return articleDao.queryBuilder().where().eq("user_id",userId).query();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
