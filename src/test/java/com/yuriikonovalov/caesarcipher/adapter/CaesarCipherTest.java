package com.yuriikonovalov.caesarcipher.adapter;

import com.yuriikonovalov.caesarcipher.TestAlphabet;
import com.yuriikonovalov.caesarcipher.domain.CaesarCipher;
import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CaesarCipherTest {

    private final CaesarCipher caesarCipher = new CaesarCipherImpl();

    @ParameterizedTest
    @MethodSource({"enArguments", "uaArguments"})
    void enAlphabet(int key, Alphabet alphabet, String input) {
        String encrypted = caesarCipher.encrypt(key, input, alphabet);
        String decrypted = caesarCipher.decrypt(key, encrypted, alphabet);
        Assertions.assertEquals(input, decrypted);
    }

    private static Stream<Arguments> enArguments() {
        return generateArguments(TestAlphabet.enAlphabet, "Hello - There");
    }

    private static Stream<Arguments> uaArguments() {
        return generateArguments(TestAlphabet.uaAlphabet, "Привіт - Там");
    }

    private static Stream<Arguments> generateArguments(Alphabet alphabet, String input) {
        Stream.Builder<Arguments> args = Stream.builder();
        for (int key = 1; key < 51; key++) {
            args.add(Arguments.of(key, alphabet, input));
        }
        return args.build();
    }
}