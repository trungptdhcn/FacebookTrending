package com.gosutv.fbtrending.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trung on 11/24/2015.
 */
public class FanpagePictureData
{
    @SerializedName("is_silhouette")
    private boolean isSilhouette;
    private String url;

    public void setSilhouette(boolean isSilhouette)
    {
        this.isSilhouette = isSilhouette;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }

    public boolean isSilhouette()
    {
        return isSilhouette;
    }
}
