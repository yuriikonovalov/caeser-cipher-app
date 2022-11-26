package com.yuriikonovalov.caesarcipher.utils.result;

public final class Success<T> extends Result<T> {

    public Success() {
        super(null);
    }

    public Success(T data) {
        super(data);
    }

}
