package com.dev.projecttemplate.data;

import com.dev.projecttemplate.data.local.database.DbHelper;
import com.dev.projecttemplate.data.local.preference.PreferenceHelper;
import com.dev.projecttemplate.data.remote.ApiHelper;

public interface DataManager extends DbHelper, PreferenceHelper, ApiHelper {
}
