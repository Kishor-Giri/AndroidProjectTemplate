package com.dev.projecttemplate.ui.splash;

import android.databinding.BindingAdapter;
import android.widget.TextView;

public class SplashBindingAdapter {
    SplashBindingAdapter()
    {

    }

    @BindingAdapter("txt")
    public static void setText(TextView textView, String text)
    {
        textView.setText(text);
    }

}
