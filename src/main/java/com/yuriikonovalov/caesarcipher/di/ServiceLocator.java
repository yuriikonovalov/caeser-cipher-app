package com.yuriikonovalov.caesarcipher.di;

import com.yuriikonovalov.caesarcipher.adapter.AlphabetSourceImpl;
import com.yuriikonovalov.caesarcipher.domain.AlphabetSource;
import com.yuriikonovalov.caesarcipher.domain.CaesarCipher;
import com.yuriikonovalov.caesarcipher.adapter.CaesarCipherImpl;
import com.yuriikonovalov.caesarcipher.domain.usecases.*;

public class ServiceLocator {
    private static AlphabetSource getAlphabetSource() {
        return new AlphabetSourceImpl();
    }

    private static CaesarCipher getCaesarCipher() {
        return new CaesarCipherImpl();
    }

    private static ValidateKey getValidateKey() {
        return new ValidateKey();
    }

    public static GetAlphabets getGetAlphabets() {
        return new GetAlphabets(getAlphabetSource());
    }

    public static DecryptMessage getDecryptMessage() {
        return new DecryptMessage(
                getCaesarCipher(),
                getValidateKey()
        );
    }

    public static EncryptMessage getEncryptMessage() {
        return new EncryptMessage(
                getCaesarCipher(),
                getValidateKey()
        );
    }

    public static CopyToClipboard getCopyToClipboard() {
        return new CopyToClipboard();
    }
}
