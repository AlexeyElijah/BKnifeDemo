package com.zwc.inject;


import android.app.Activity;
import android.view.View;

import com.zwc.inject.processor.BindViewProcessor;
import com.zwc.inject.provider.ActivityProvider;
import com.zwc.inject.provider.Provider;
import com.zwc.inject.provider.ViewProvider;

import java.util.HashMap;


public class BKnife {
    private static final ActivityProvider activityProvider = new ActivityProvider();

    private static final ViewProvider viewProvider = new ViewProvider();
    private static final HashMap<String, IBind> injectMap = new HashMap<>();

    public static void inject(Activity activity) {
        inject(activity, activity, activityProvider);
    }

    public static void inject(View view) {
        inject(view, view);
    }

    public static void inject(Object host, View view) {
        inject(host, view, viewProvider);
    }

    private static void inject(Object host, Object object, Provider provider) {
        String className = host.getClass().getName();
        try {
            IBind inject = injectMap.get(className);

            if (inject == null) {
                Class<?> aClass = Class.forName(className + BindViewProcessor.SUFFIX,false,host.getClass().getClassLoader());
                inject = (IBind) aClass.newInstance();
                injectMap.put(className, inject);
            }
            inject.inject(host, object, provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
