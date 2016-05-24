package com.example.clement.testormlite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by macremote on 16/5/24.
 */
@DatabaseTable(tableName = "tb_article")
public class Article {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(canBeNull = true,foreign = true,columnName = "user_id",foreignAutoRefresh = true)
    private User user;    //表示这个article属于这个User

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString(){
        return "Article [id=" + id + ", title=" + title + ", user=" + user
                + "]";
    }
}
