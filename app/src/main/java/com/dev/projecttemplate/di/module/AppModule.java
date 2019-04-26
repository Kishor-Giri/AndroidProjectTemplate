package com.dev.projecttemplate.di.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.dev.projecttemplate.R;
import com.dev.projecttemplate.data.AppDataManager;
import com.dev.projecttemplate.data.DataManager;
import com.dev.projecttemplate.data.local.database.AppDatabaseHelper;
import com.dev.projecttemplate.data.local.database.DbHelper;
import com.dev.projecttemplate.data.local.preference.AppPreferenceHelper;
import com.dev.projecttemplate.data.local.preference.PreferenceHelper;
import com.dev.projecttemplate.di.DatabaseInfo;
import com.dev.projecttemplate.di.PreferenceInfo;
import com.dev.projecttemplate.ui.custom.AnnotationExclusionStrategy;
import com.dev.projecttemplate.utils.AppConstants;
import com.dev.projecttemplate.utils.DoubleTypeAdapter;
import com.dev.projecttemplate.utils.rx.AppSchedulerProvider;
import com.dev.projecttemplate.utils.rx.SchedulerProvider;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    Resources provideResources(Application application) {
        return application.getResources();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies( new AnnotationExclusionStrategy() );
        gsonBuilder.setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES );
        gsonBuilder.registerTypeAdapter( double.class, new DoubleTypeAdapter() );
        gsonBuilder.registerTypeAdapter( Double.class, new DoubleTypeAdapter() );
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel( HttpLoggingInterceptor.Level.BODY );
        client.cache( cache );
        client.addNetworkInterceptor( logging );
        client.readTimeout( 120, TimeUnit.SECONDS );
        client.writeTimeout( 120, TimeUnit.SECONDS );
        client.connectTimeout( 240, TimeUnit.SECONDS );
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create( gson ) )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .baseUrl("http://example.com")
                .client( okHttpClient )
                .build();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath( "fonts/roboto/Roboto-Regular.ttf" )
                .setFontAttrId( R.attr.fontPath )
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDatabaseHelper appDbHelper) {
        return appDbHelper;
    }
    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(AppPreferenceHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
