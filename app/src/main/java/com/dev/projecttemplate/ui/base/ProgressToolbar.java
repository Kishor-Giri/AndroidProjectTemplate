package com.dev.projecttemplate.ui.base;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public interface ProgressToolbar {
    default ObservableField<String> getTitle() {
        return new ObservableField<String>() {
        };
    }

    default ObservableInt getProgress() {
        return new ObservableInt( 1 );
    }

    default ObservableInt getMaxProgress() {
        return new ObservableInt( 7 );
    }

    default void onCloseButtonClicked() {
    }
}
