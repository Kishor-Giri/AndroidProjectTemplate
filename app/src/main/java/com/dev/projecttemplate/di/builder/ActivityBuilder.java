package com.dev.projecttemplate.di.builder;

import com.dev.projecttemplate.ui.splash.SplashActivity;
import com.dev.projecttemplate.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();
}
