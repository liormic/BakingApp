package com.ely.bakingapp.idilingResouce;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Lior on 5/7/2018.
 */

public class SimpleIdilingResource implements IdlingResource {
    @Nullable
    private volatile ResourceCallback mCallback;
    private AtomicBoolean isIdileNow = new AtomicBoolean(true);
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return  isIdileNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    public void setIdleState(boolean isIdleNow) {
        isIdileNow.set(isIdleNow);
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
}
