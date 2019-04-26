package com.dev.projecttemplate.ui.base;

import android.databinding.ObservableField;

public interface ToolbarViewModel {


    default ObservableField<String> getTitle() {
        return new ObservableField<String>("Title") {
        };
    }


    default void onCloseButtonClicked() {
    }


}
