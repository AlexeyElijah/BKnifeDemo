package com.zwc.inject;

import com.zwc.inject.provider.Provider;

public interface IBind<T> {

    void inject(T host, Object tagert, Provider provider);
}
