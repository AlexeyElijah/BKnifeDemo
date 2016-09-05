package com.zwc.inject;

import com.zwc.inject.provider.Provider;

public interface Bind<T> {

    void inject(T host, Object object, Provider provider);
}
