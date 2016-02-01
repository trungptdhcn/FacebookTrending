package com.gosutv.fbtrending.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trung on 11/24/2015.
 */
public class FanpageDetail
{
    private String id;
    private String name;
    private String description;
    private long likes;
    @SerializedName("picture")
    private FanpagePicture fanpagePicture;
    private FanpageCover cover;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public FanpagePicture getFanpagePicture()
    {
        return fanpagePicture;
    }

    public void setFanpagePicture(FanpagePicture fanpagePicture)
    {
        this.fanpagePicture = fanpagePicture;
    }

    public long getLikes()
    {
        return likes;
    }

    public void setLikes(long likes)
    {
        this.likes = likes;
    }

    public FanpageCover getCover()
    {
        return cover;
    }

    public void setCover(FanpageCover cover)
    {
        this.cover = cover;
    }
}
