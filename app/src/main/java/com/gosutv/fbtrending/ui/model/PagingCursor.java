package com.gosutv.fbtrending.ui.model;

/**
 * Created by Trung on 11/24/2015.
 */
public class PagingCursor
{
    private String after;
    private String before;

    public String getAfter()
    {
        return after;
    }

    public void setAfter(String after)
    {
        this.after = after;
    }

    public String getBefore()
    {
        return before;
    }

    public void setBefore(String before)
    {
        this.before = before;
    }
}
