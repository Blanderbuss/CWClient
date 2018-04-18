package com.cw.entities;

import java.io.Serializable;

public class Tuple<T1 extends Serializable, T2 extends Serializable> implements Serializable {
    public final T1 val1;
    public final T2 val2;

    public Tuple(T1 accessToken, T2 user) {
        this.val1 = accessToken;
        this.val2 = user;
    }
}
