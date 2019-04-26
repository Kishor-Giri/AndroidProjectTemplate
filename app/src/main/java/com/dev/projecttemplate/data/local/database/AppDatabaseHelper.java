package com.dev.projecttemplate.data.local.database;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDatabaseHelper implements DbHelper {
    private final AppDatabase mAppDatabase;

    @Inject
    public AppDatabaseHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

}
