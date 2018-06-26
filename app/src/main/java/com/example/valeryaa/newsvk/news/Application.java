package com.example.valeryaa.newsvk.news;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        VKSdk.initialize(this);
    }
}