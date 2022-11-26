package com.yuriikonovalov.caesarcipher.adapter;

import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;
import com.yuriikonovalov.caesarcipher.domain.entities.CipherCharacter;
import com.yuriikonovalov.caesarcipher.domain.CaesarCipher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CaesarCipherImpl implements CaesarCipher {
    /**
     * An {@link Integer} that is returned by the Collection's indexOf(Object o) method if there's no such object in a collection.
     */
    private static final int POSITION_NOT_IN_ALPHABET = -1;

    /**
     * @param key      an integer used for shifting the position of a character.
     * @param message  text to be decrypted.
     * @param alphabet an Alphabet used for decryption.
     * @return {@link String} of a decrypted message.
     */
    @Override
    public String decrypt(int key, String message, Alphabet alphabet) {
        List<CipherCharacter> cipherCharacters = getCipherCharacters(message, key, alphabet.characters(), Process.DECRYPTION);
        return mapCharactersToString(cipherCharacters, alphabet.characters());
    }

    /**
     * @param key      an integer used for shifting the position of a character.
     * @param message  text to be encrypted.
     * @param alphabet an Alphabet used for encryption.
     * @return {@link String} of an encrypted message.
     */
    @Override
    public String encrypt(int key, String message, Alphabet alphabet) {
        List<CipherCharacter> cipherCharacters = getCipherCharacters(message, key, alphabet.characters(), Process.ENCRYPTION);
        return mapCharactersToString(cipherCharacters, alphabet.characters());
    }

    /**
     * @param cipherCharacters a list of {@link CipherCharacter}.
     * @param alphabet         a list of {@link Character} that represent characters of an alphabet used for encryption/decryption.
     * @return {@link String} of an encrypted/decrypted message.
     */
    private String mapCharactersToString(List<CipherCharacter> cipherCharacters, List<Character> alphabet) {
        Function<CipherCharacter, Character> characterMapper = cipherCharacter -> {
            int position = cipherCharacter.getPosition();
            boolean shouldBeUpperCase = cipherCharacter.isUpperCase();
            // If a character of the message is not part of the selected alphabet then we pass that character as it is.
            if (position == POSITION_NOT_IN_ALPHABET) {
                return cipherCharacter.getOriginalCharacter();
            }
            // Get a character from the alphabet characters for the corresponding position.
            char character = alphabet.get(position);
            if (shouldBeUpperCase) {
                return Character.toUpperCase(character);
            } else {
                return Character.toLowerCase(character);
            }
        };
        return cipherCharacters.stream().map(characterMapper).map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * @param key      an integer used for shifting the position of a character.
     * @param message  text to be encrypted.
     * @param alphabet an Alphabet used for encryption.
     * @param process  a flag to perform encryption or decryption.
     * @return a list of {@link CipherCharacter} that are mapped from the message.
     */
    private ArrayList<CipherCharacter> getCipherCharacters(String message, int key, List<Character> alphabet, Process process) {
        ArrayList<CipherCharacter> cipherCharacters = new ArrayList<>();
        for (Character character : message.toCharArray()) {
            CipherCharacter cipherCharacter = getCipherCharacter(alphabet, character, key, process);
            cipherCharacters.add(cipherCharacter);
        }
        return cipherCharacters;
    }

    /**
     * @param key       an integer used for shifting the position of a character.
     * @param alphabet  an Alphabet used for encryption.
     * @param process   a flag to perform encryption or decryption.
     * @param character a single character of a message.
     * @return {@link CipherCharacter} based on the provided character.
     */
    private CipherCharacter getCipherCharacter(List<Character> alphabet, Character character, int key, Process process) {
        int positionInAlphabet = getPositionInAlphabet(alphabet, character);
        // For characters that are not in the alphabet.
        if (positionInAlphabet == POSITION_NOT_IN_ALPHABET) {
            return new CipherCharacter(positionInAlphabet, character);
        }

        int position;
        if (process == Process.DECRYPTION) {
            position = calculateDecryptionPosition(positionInAlphabet, key, alphabet.size());
        } else {
            position = calculateEncryptionPosition(positionInAlphabet, key, alphabet.size());
        }
        return new CipherCharacter(position, character);
    }

    /**
     * @param positionInAlphabet an index of a character in an alphabet.
     * @param key                an integer that is used for shifting positionInAlphabet.
     * @param alphabetSize       a size of the alphabet.
     * @return a position after shifting the positionInAlphabet with the key.
     */
    private int calculateDecryptionPosition(int positionInAlphabet, int key, int alphabetSize) {
        int decryptedPosition = positionInAlphabet - key;
        while (decryptedPosition < 0) {
            decryptedPosition += alphabetSize;
        }
        return decryptedPosition;
        /*
         Another way:
         Due to the cyclic nature of the cipher it's possible to apply the given key(shift) in the opposite direction.
                 int oppositeKey = alphabetSize - key % alphabetSize;
                 return (positionInAlphabet + oppositeKey) % alphabetSize;
        */
    }

    /**
     * @param positionInAlphabet an index of a character in an alphabet.
     * @param key                an integer that is used for shifting positionInAlphabet.
     * @param alphabetSize       a size of the alphabet.
     * @return a position after shifting the positionInAlphabet with the key.
     */
    private int calculateEncryptionPosition(int positionInAlphabet, int key, int alphabetSize) {
        int encryptedPosition = positionInAlphabet + key;
        while (encryptedPosition >= alphabetSize) {
            encryptedPosition -= alphabetSize;
        }
        return encryptedPosition;
        /*
         Another way:
         return (positionInAlphabet + key) % alphabetSize;
        */
    }

    /**
     * @param alphabet  an array of the characters of the given alphabet.
     * @param character a character of the alphabet position of which need to return.
     * @return position of the given character in the given alphabet or -1 if there's no matching characters.
     */
    private int getPositionInAlphabet(List<Character> alphabet, Character character) {
        return alphabet.indexOf(Character.toUpperCase(character));
    }

    private enum Process {
        ENCRYPTION, DECRYPTION
    }
}
