package com.gosutv.fbtrending.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 11/24/2015.
 */
public class Fanpages
{
    @SerializedName("data")
    private List<FanpageDetail> facebookFanpageList = new ArrayList<>();
    private Paging paging;
    public List<FanpageDetail> getFacebookFanpageList()
    {
        return facebookFanpageList;
    }

    public void setFacebookFanpageList(List<FanpageDetail> facebookFanpageList)
    {
        this.facebookFanpageList = facebookFanpageList;
    }

    public Paging getPaging()
    {
        return paging;
    }

    public void setPaging(Paging paging)
    {
        this.paging = paging;
    }
}
