package com.yuriikonovalov.caesarcipher.domain.usecases;

import com.yuriikonovalov.caesarcipher.domain.CaesarCipher;
import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;
import com.yuriikonovalov.caesarcipher.utils.result.Error;
import com.yuriikonovalov.caesarcipher.utils.result.Result;
import com.yuriikonovalov.caesarcipher.utils.result.Success;

public class DecryptMessage {
    private final CaesarCipher caesarCipher;
    private final ValidateKey validateKey;

    public DecryptMessage(CaesarCipher caesarCipher, ValidateKey validateKey) {
        this.caesarCipher = caesarCipher;
        this.validateKey = validateKey;
    }

    public Result<String> execute(int key, String message, Alphabet alphabet) {
        if (validateKey.execute(key) instanceof Error)
            return new Error<>();

        String decryptedMessage = caesarCipher.decrypt(key, message, alphabet);
        return new Success<>(decryptedMessage);
    }
}
