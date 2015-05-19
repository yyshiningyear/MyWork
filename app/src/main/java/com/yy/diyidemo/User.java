package com.yy.diyidemo;

import java.util.List;

/**
 * Created by Administrator on 2015/5/16.
 */
public class User {
    private String ID;  //用户id
    private List<Integer> DiaryID;  //日记列表
    private List<String> Classes;   //分类列表
    private List<Integer> Drafted;  //草稿箱

    public User() {

    }

    public String getID() {
        return ID;
    }

    public List<Integer> getDiaryID() {
        return DiaryID;
    }

    public void setDiaryID(List<Integer> diaryID) {
        this.DiaryID = diaryID;
    }

    public List<String> getClasses() {
        return Classes;
    }

    public void setClasses(List<String> classes) {
        this.Classes = classes;
    }

    public List<Integer> getDrafted() {
        return Drafted;
    }

    public void setDrafted(List<Integer> drafted) {
        this.Drafted = drafted;
    }
}
