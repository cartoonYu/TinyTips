package com.cartoon.tinytips.data.TablePlan;

import java.util.Date;

/**
 * Created by cartoon on 2018/2/1.
 * 计划类
 */

public class Plan {
    //计划类
    private String content;      //计划的内容
    private Date date;           //计划的时间
    private String author;       //计划人
    private boolean isFinish;     //是否完成

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setFinish(boolean finish) {
        this.isFinish = finish;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isFinish() {
        return isFinish;
    }
}
