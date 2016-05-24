package com.example.clement.testormlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.clement.testormlite.Bean.Article;
import com.example.clement.testormlite.Bean.User;
import com.example.clement.testormlite.DB.ArticleDao;
import com.example.clement.testormlite.DB.UserDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        addUser();
        getArticleWithUser();
    }
    private void addUser(){
        User user = new User();
        user.setName("Clement2222");
        //插入数据
        new UserDao(this).add(user);

        Article article = new Article();
        article.setTitle("77777");
        article.setUser(user);
        new ArticleDao(this).add(article);
    }

    public void getArticleWithUser(){
        Article article = new ArticleDao(this).getArticleWithUser(1);
        User user = article.getUser();
        Toast.makeText(MainActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
    }
}
