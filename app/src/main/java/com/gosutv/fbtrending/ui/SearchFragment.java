package com.gosutv.fbtrending.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.gosutv.fbtrending.R;
import com.gosutv.fbtrending.common.BaseActivity;
import com.gosutv.fbtrending.common.BaseFragment;
import com.gosutv.fbtrending.common.StringUtils;
import com.gosutv.fbtrending.ui.asynctask.AsyncTaksListener;
import com.gosutv.fbtrending.ui.asynctask.EndlessScrollListener;
import com.gosutv.fbtrending.ui.asynctask.FacebookAsyncTask;
import com.gosutv.fbtrending.ui.model.Fanpages;

/**
 * Created by Trung on 11/24/2015.
 */
public class SearchFragment extends BaseFragment implements TextView.OnEditorActionListener, AsyncTaksListener
{
    @Bind(R.id.search_fragment_edtSearch)
    EditText edtSearch;
    @Bind(R.id.listview)
    ListView listView;
    private FanpageAdapter adapter;
    private String nextPages = "";

    @Override
    public int getLayout()
    {
        return R.layout.search_fragment;
    }

    @Override
    public void setDataToView(Bundle savedInstanceState)
    {
        edtSearch.setOnEditorActionListener(this);
        listView.setOnScrollListener(new EndlessScrollListener()
        {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount)
            {
                if (StringUtils.isNotEmpty(nextPages))
                {
                    search(edtSearch.getText().toString());
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
        switch (v.getId())
        {
            case R.id.search_fragment_edtSearch:
                search(edtSearch.getText().toString());
                break;
        }
        return false;
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

    private void search(String keyword)
    {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name, description,likes,picture{url,is_silhouette,width,height},cover,members,band_members");
        parameters.putInt("limit", 5);
        parameters.putString("after", nextPages);
        parameters.putString("q", keyword);
        parameters.putString("type", "page");
        GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), "search", parameters, HttpMethod.GET);
        FacebookAsyncTask asyncTask = new FacebookAsyncTask(request);
        asyncTask.setListener(this);
        asyncTask.execute();
    }
}
