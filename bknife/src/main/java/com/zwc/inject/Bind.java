package com.zwc.inject;

import com.zwc.inject.provider.Provider;

/**
 * Created by JokAr on 16/8/6.
 */
public interface Bind<T> {

    void inject(T host, Object object, Provider provider);
}
