package com.yuriikonovalov.caesarcipher.domain.usecases;

import com.yuriikonovalov.caesarcipher.domain.CaesarCipher;
import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;
import com.yuriikonovalov.caesarcipher.utils.result.Error;
import com.yuriikonovalov.caesarcipher.utils.result.Result;
import com.yuriikonovalov.caesarcipher.utils.result.Success;

public class EncryptMessage {
    private final CaesarCipher caesarCipher;
    private final ValidateKey validateKey;

    public EncryptMessage(CaesarCipher caesarCipher, ValidateKey validateKey) {
        this.caesarCipher = caesarCipher;
        this.validateKey = validateKey;
    }

    public Result<String> execute(int key, String message, Alphabet alphabet) {
        if (validateKey.execute(key) instanceof Error)
            return new Error<>();

        String encryptedMessage = caesarCipher.encrypt(key, message, alphabet);
        return new Success<>(encryptedMessage);
    }
}
