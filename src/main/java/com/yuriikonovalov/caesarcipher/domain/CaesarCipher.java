package com.yuriikonovalov.caesarcipher.domain;

import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;

public interface CaesarCipher {
    String decrypt(int key, String message, Alphabet alphabet);

    String encrypt(int key, String message, Alphabet alphabet);
}
