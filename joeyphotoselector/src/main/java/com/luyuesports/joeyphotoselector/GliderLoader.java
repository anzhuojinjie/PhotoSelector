package com.luyuesports.joeyphotoselector;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * @author：yang
 * @date:2016/11/18 0018
 */
public class GliderLoader implements ImageLoader {

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.imageselector_photo)
                .centerCrop()
                .into(imageView);
    }

}