package com.iiitd.prince.assignmentfour;

/**
 * Created by Prince on 31-10-2016.
 */

public class TodoTask {

    private String title;
    private String detail;

    public TodoTask() {
    }

    public TodoTask(String title) {
        this.title = title;
    }
    public TodoTask(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
