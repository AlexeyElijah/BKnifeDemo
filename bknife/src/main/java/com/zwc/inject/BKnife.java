package com.zwc.inject;


import android.app.Activity;
import android.support.v4.util.ArrayMap;

import com.zwc.inject.provider.ActivityProvider;
import com.zwc.inject.provider.Provider;
import com.zwc.inject.provider.ViewProvider;

import javax.swing.text.View;

public class BKnife {
    private static final ActivityProvider activityProvider = new ActivityProvider();

    private static final ViewProvider viewProvider = new ViewProvider();
    private static final ArrayMap<String, Bind> injectMap = new ArrayMap<>();

    public static void inject(Activity activity) {
        inject(activity, activity, activityProvider);
    }

    public static void inject(View view) {
        inject(view, view);
    }

    private static void inject(Object host, View view) {
        inject(host, view, viewProvider);
    }

    private static void inject(Object host, Object object, Provider provider) {
        String className = host.getClass().getName();
        try {
            Bind inject = injectMap.get(className);

            if (inject == null) {
                Class<?> aClass = Class.forName(className + "$$ViewInject");
                inject = (Bind) aClass.newInstance();
                injectMap.put(className, inject);
            }
            inject.inject(host, object, provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
