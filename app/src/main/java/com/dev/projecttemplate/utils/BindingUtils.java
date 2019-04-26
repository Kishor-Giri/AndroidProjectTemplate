package com.dev.projecttemplate.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.projecttemplate.R;

public class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    //Load image in image view using glide
    @BindingAdapter("bind:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with( context ).load( url ).into( imageView );
    }

    //load image with place holder
    @BindingAdapter({"bind:imageUrlWithPlaceHolder"})
    public static void loadImage(ImageView view, String imageUrl) {
        Context context = view.getContext();
        Glide.with( context ).load( imageUrl )
                .diskCacheStrategy( DiskCacheStrategy.ALL ).placeholder( R.drawable.ic_launcher_background ).dontAnimate().into( view );
    }
}
