package com.yuriikonovalov.caesarcipher.domain.usecases;

import com.yuriikonovalov.caesarcipher.utils.result.Error;
import com.yuriikonovalov.caesarcipher.utils.result.Result;
import com.yuriikonovalov.caesarcipher.utils.result.Success;

public class ValidateKey {
    private static final int MINIMUM_KEY = 1;

    public Result<Void> execute(int key) {
        if (key < MINIMUM_KEY) {
            return new Error<>(null);
        } else {
            return new Success<>(null);
        }
    }
}
