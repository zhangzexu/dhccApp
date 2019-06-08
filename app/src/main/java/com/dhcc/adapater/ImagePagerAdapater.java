package com.dhcc.adapater;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImagePagerAdapater extends PagerAdapter {
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private ArrayList<Integer> imageId = new ArrayList<>();
    private Context context;
    public ImagePagerAdapater() {
    }

    public ImagePagerAdapater(Context context,ArrayList<Integer> imageId) {
        this.context = context;
        this.imageId = imageId;
        for (Integer id:imageId) {
            ImageView view = new ImageView(context);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setImageResource(id);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageViews.add(view);
        }

    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       container.addView(imageViews.get(position));
       return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }

}
