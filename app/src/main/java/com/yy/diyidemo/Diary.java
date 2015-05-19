package com.yy.diyidemo;

import java.util.List;

/**
 * Created by Administrator on 2015/5/15.
 */
public class Diary {

    private String title;
    private String data;        //应该是html
    private String time;
    private List<String> comment;
    private int ID;
    private String classify;
    private int readTimes;

    public Diary(String title, String time, String data) {
        this.title = title;
        this.time = time;
        this.data = data;
    }
    public Diary(String title, String time, String data, String classify) {
        this.title = title;
        this.time = time;
        this.data = data;
        this.classify = classify;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }
}
