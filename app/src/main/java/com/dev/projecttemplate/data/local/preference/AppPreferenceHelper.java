package com.dev.projecttemplate.data.local.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.dev.projecttemplate.di.PreferenceInfo;

import javax.inject.Inject;

public class AppPreferenceHelper implements PreferenceHelper {
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferenceHelper(Context context, @PreferenceInfo String preferenceFileName) {
        mPrefs = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);

    }
}
