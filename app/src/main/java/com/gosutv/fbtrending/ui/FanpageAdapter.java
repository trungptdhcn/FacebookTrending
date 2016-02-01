package com.gosutv.fbtrending.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.gosutv.fbtrending.R;
import com.gosutv.fbtrending.ui.model.FanpageDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 11/24/2015.
 */
public class FanpageAdapter extends BaseAdapter
{

    private List<FanpageDetail> fanpages = new ArrayList<>();
    private FragmentActivity activity;
    private Fragment fragment;

    public FanpageAdapter(List<FanpageDetail> fanpages, FragmentActivity activity)
    {
        this.fanpages = fanpages;
        this.activity = activity;
    }

    @Override
    public int getCount()
    {
        return fanpages.size();
    }

    @Override
    public Object getItem(int position)
    {
        return fanpages.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        final FanpageDetail fanpage = fanpages.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        if (convertView != null)
        {
            holder = (ViewHolder) convertView.getTag();
        }
        else
        {
            convertView = inflater.inflate(R.layout.list_view_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.tvName.setText(fanpage.getName());
        holder.tvDes.setText(fanpage.getDescription());
        Glide.with(activity)
                .load(fanpage.getFanpagePicture().getFanpagePictureData().getUrl())
                .centerCrop()
                .placeholder(R.drawable.person_image_empty)
                .crossFade()
                .into(holder.ivAvatar);
        return convertView;
    }

    public List<FanpageDetail> getFanpages()
    {
        return fanpages;
    }

    public void setFanpages(List<FanpageDetail> facebookFanpages)
    {
        this.fanpages = facebookFanpages;
    }

    public Fragment getFragment()
    {
        return fragment;
    }

    public void setFragment(Fragment fragment)
    {
        this.fragment = fragment;
    }

    static class ViewHolder
    {
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvLike)
        TextView tvDes;
        @Bind(R.id.ivAvatar)
        ImageView ivAvatar;
        public ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }

}
