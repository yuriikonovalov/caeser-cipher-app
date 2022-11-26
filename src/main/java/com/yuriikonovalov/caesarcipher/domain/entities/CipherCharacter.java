package com.yuriikonovalov.caesarcipher.domain.entities;


public class CipherCharacter {
    // A position of a character in an alphabet.
    private final int position;
    // A character for the corresponding position.
    // Used for situations when a character is not found in an alphabet so that it's possible to pass it as it's into
    // an encrypted/decrypted message.
    private final Character originalCharacter;
    // The flag indicates whether a character in a message is uppercase or not.
    private final boolean isUpperCase;

    public CipherCharacter(int position, Character originalCharacter) {
        this.position = position;
        this.originalCharacter = originalCharacter;
        this.isUpperCase = Character.isUpperCase(originalCharacter);
    }

    public int getPosition() {
        return position;
    }

    public Character getOriginalCharacter() {
        return originalCharacter;
    }

    public boolean isUpperCase() {
        return isUpperCase;
    }
}
