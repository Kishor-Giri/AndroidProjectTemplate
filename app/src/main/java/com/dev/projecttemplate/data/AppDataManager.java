package com.dev.projecttemplate.data;


import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDataManager  implements DataManager{

    private final Context mContext;
    private final Gson mGson;

    @Inject
    public AppDataManager(Context context,Gson gson) {
        mContext = context;
        mGson = gson;
    }
}
