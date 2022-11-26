package com.yuriikonovalov.caesarcipher.utils.result;

public abstract sealed class Result<T> permits Error, Success {
    private final T data;

    public Result(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
