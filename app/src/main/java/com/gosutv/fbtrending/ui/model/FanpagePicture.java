package com.gosutv.fbtrending.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Trung on 11/24/2015.
 */
public class FanpagePicture
{
    @SerializedName("data")
    private FanpagePictureData fanpagePictureData;

    public FanpagePictureData getFanpagePictureData()
    {
        return fanpagePictureData;
    }

    public void setFanpagePictureData(FanpagePictureData fanpagePictureData)
    {
        this.fanpagePictureData = fanpagePictureData;
    }
}
