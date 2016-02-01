package com.gosutv.fbtrending.ui.asynctask;

import android.os.AsyncTask;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

/**
 * Created by Trung on 11/24/2015.
 */
public class FacebookAsyncTask extends AsyncTask<Void, GraphResponse, GraphResponse>
{
    private GraphRequest graphRequest;
    AsyncTaksListener listener;

    public FacebookAsyncTask(GraphRequest graphRequest)
    {
        this.graphRequest = graphRequest;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        listener.prepare();
    }

    @Override
    protected GraphResponse doInBackground(Void... params)
    {
        return graphRequest.executeAndWait();
    }

    @Override
    protected void onPostExecute(GraphResponse graphResponse)
    {
        super.onPostExecute(graphResponse);
        listener.complete(graphResponse);
    }

    public AsyncTaksListener getListener()
    {
        return listener;
    }

    public void setListener(AsyncTaksListener listener)
    {
        this.listener = listener;
    }
}
