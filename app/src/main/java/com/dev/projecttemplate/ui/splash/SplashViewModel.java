package com.dev.projecttemplate.ui.splash;

import com.dev.projecttemplate.data.DataManager;
import com.dev.projecttemplate.ui.base.BaseViewModel;
import com.dev.projecttemplate.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    public String name = "Hello Kishor";
    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
