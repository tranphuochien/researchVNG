package com.example.root.demoapp.presentation.util.ImageManager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by root on 26/07/2017.
 */

public class PicassoLib implements ImageManager {
    @Override
    public void load(@NonNull Context context, @NonNull String source, @NonNull ImageView target) {
        Picasso.with(context)
                .load(source)
                .resize(200, 200)
                .into(target);

    }
}
