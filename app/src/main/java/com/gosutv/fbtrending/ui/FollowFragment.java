package com.gosutv.fbtrending.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import butterknife.Bind;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.gosutv.fbtrending.R;
import com.gosutv.fbtrending.common.BaseFragment;
import com.gosutv.fbtrending.ui.asynctask.AsyncTaksListener;
import com.gosutv.fbtrending.ui.asynctask.FacebookAsyncTask;
import com.gosutv.fbtrending.ui.model.Fanpages;

/**
 * Created by Trung on 11/24/2015.
 */
public class FollowFragment extends BaseFragment implements AsyncTaksListener
{
    @Bind(R.id.listview)
    ListView listView;
    FanpageAdapter adapter;
    String nextPages ="";

    @Override
    public int getLayout()
    {
        return R.layout.follow_fragment;
    }

    @Override
    public void setDataToView(Bundle savedInstanceState)
    {
        getFollowRequest();
    }

    @Override
    public void prepare()
    {

    }

    @Override
    public void complete(Object obj)
    {
        String result = ((GraphResponse) obj).getRawResponse();
        if (result != null)
        {
            Fanpages fanpages = new Gson().fromJson(result, Fanpages.class);
            if (fanpages != null && fanpages.getFacebookFanpageList().size() > 0)
            {
                if (null == adapter)
                {
                    adapter = new FanpageAdapter(fanpages.getFacebookFanpageList(), getActivity());
                    adapter.setFragment(getParentFragment());
                    listView.setAdapter(adapter);
                }
                else
                {
                    adapter.getFanpages().addAll(fanpages.getFacebookFanpageList());
                    adapter.notifyDataSetChanged();
                }
                nextPages = fanpages.getPaging().getCursors().getAfter();
            }
        }
    }

    private void getFollowRequest()
    {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name, description,likes,picture{url,is_silhouette,width,height},cover,members,band_members");
        parameters.putInt("limit", 5);
        parameters.putString("after", nextPages);
        GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/likes", parameters, HttpMethod.GET);
        FacebookAsyncTask asyncTask = new FacebookAsyncTask(request);
        asyncTask.setListener(this);
        asyncTask.execute();
    }
}
