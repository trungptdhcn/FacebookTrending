package com.gosutv.fbtrending.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trung on 11/24/2015.
 */
public class Paging
{
    private String next;
    @SerializedName("cursors")
    private PagingCursor cursors;

    public String getNext()
    {
        return next;
    }

    public void setNext(String next)
    {
        this.next = next;
    }

    public PagingCursor getCursors()
    {
        return cursors;
    }

    public void setCursors(PagingCursor cursors)
    {
        this.cursors = cursors;
    }
}
