package com.example.root.demoapp.presentation.util.ImageManager;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.root.demoapp.R;

/**
 * Created by root on 26/07/2017.
 */

public class GlideLib implements ImageManager{
    @Override
    public void load(Context context, String source, ImageView target) {
        Glide.with(context)
                .load(source)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher)
                .into(target);
    }
}
