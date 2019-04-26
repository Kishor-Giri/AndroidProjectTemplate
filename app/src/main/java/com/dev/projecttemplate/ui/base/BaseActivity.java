package com.dev.projecttemplate.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.job.JobScheduler;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback {


    private final String TAG = this.getClass().getSimpleName();
    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;
    public static boolean isInBackground = false;
    static JobScheduler jobScheduler;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
        mViewModel.getIsLoading().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (mViewModel.getIsLoading().get()) {
                   // showLoading();
                } else hideLoading();
            }
        });
        registerComponentCallbacks(new ComponentCallback());

    }


    static class ComponentCallback implements ComponentCallbacks2 {
        private final String TAG = ComponentCallback.class.getSimpleName();


        @Override
        public void onTrimMemory(int level) {
            if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
                Log.d(TAG, "Application not visible anymore");
                isInBackground = true;
            }
        }

        @Override
        public void onLowMemory() {
            onTrimMemory(TRIM_MEMORY_COMPLETE);
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            //no op
        }
    }
    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    public void openActivityOnTokenExpire() {
//        startActivity(LoginActivity.newIntent(this));
//        finish();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }



    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


    public Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
            return currentFragment;
        }
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isInBackground) {
            Log.d(TAG, "Application in foreground");
            isInBackground = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}

