package com.dev.projecttemplate.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppApiHelper implements ApiHelper {
    private final ApiService mApiService;

    @Inject
    public AppApiHelper(ApiService apiService) {
        mApiService = apiService;
    }
}
