package uk.co.andystabler.ciphers;

import java.util.List;

/**
 * Utility class used for operations concerning the Vigenère cipher - encryption, decryption, cracking
 * Created by andy on 04/03/15.
 */
public class VigenereCipher {

    /**
     * Encrypts the {@code plaintext} using the Vigenère cipher with the {@code key} provided.
     * <p>See {@link StringUtils#repeatString} for more details on how the key is used.
     *
     * @param plaintext the text to be encrypted
     * @param key       the key to encrypt the plaintext- must be characters matching [a-z]
     * @return
     */
    public static String encrypt(String plaintext, String key) {
        // key must be an alphabetic string
        if (!key.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Invalid key - must be one or more characters in range a...z");

        // only interested in the alphabet
        plaintext = plaintext.replaceAll("[^a-zA-Z]", "").toUpperCase();

        // make key same length as plaintext
        key = StringUtils.repeatString(key, plaintext.length()).toUpperCase();

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            // get the character at index i
            String toEncrypt = String.valueOf(plaintext.charAt(i));
            // the shift is equal to the char at index i of the key
            // subtracting A (65) to have a value in range 0-25
            int shift = key.charAt(i) - 'A';
            ciphertext.append(CaesarCipher.encrypt(toEncrypt, shift));
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts the {@code ciphertext} using the Vigenère cipher with the {@code key} provided.
     * <p>See {@link StringUtils#repeatString} for more details on how the key is used.
     *
     * @param ciphertext the text to be decrypted
     * @param key        the key to decrypt the ciphertext- must be characters matching [a-z]
     * @return
     */
    public static String decrypt(String ciphertext, String key) {
        // key must be an alphabetic string
        if (key == null || !key.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Invalid key - must be one or more characters in range a...z");

        // only interested in the alphabet
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "").toUpperCase();

        // make key same length as plaintext
        key = StringUtils.repeatString(key, ciphertext.length()).toUpperCase();

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            // get the character at index i
            String toDecrypt = String.valueOf(ciphertext.charAt(i));
            // the shift is equal to the char at index i of the key
            // subtracting 'A' (65) to have a value in range 0-25
            int shift = key.charAt(i) - 'A';
            plaintext.append(CaesarCipher.decrypt(toDecrypt, shift));
        }
        return plaintext.toString();
    }

    /**
     * Calculates a best guess at the length of the key used to encrypt the ciphertext.
     * <p>
     * Done by calculating the index of coincidence for substrings of the ciphertext for increasing lengths and returning
     * the length value if it produces an index of coincidence value "close" to that of english text.
     * <p>
     * If a best guess for the length cannot be determined, -1 is returned.
     *
     * @param ciphertext the text to analyse
     * @return a best guess key length for {@code ciphertext}
     */
    public static int calculateBestGuessKeyLen(String ciphertext) {
        if (ciphertext == null || ciphertext.length() == 0)
            return -1;
        // ciphertext is restricted to A-Z
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "");

        int maxKeyLen = 12;
        maxKeyLen = maxKeyLen > ciphertext.length() ? ciphertext.length() : maxKeyLen;
        int keyLen = -1;
        for (int i = 2; i < maxKeyLen; i++) {
            List<String> stringsAtInterval = StringUtils.getAllStringsAtInterval(ciphertext, i);
            double tempIc = stringsAtInterval.stream()
                    // calculate the index of coincidence for every string
                    .mapToDouble(LetterFrequencyUtils::indexOfCoincidence)
                            // get the average index of coincidence
                    .average().getAsDouble();
            // the key length produces text with character frequencies close to english - this is our best guess at the
            // key length
            if (LetterFrequencyUtils.closeToEng(tempIc))
                return i;
        }

        return keyLen;
    }

    /**
     * Calculates the key used to encrypt plaintext using the vigenère cipher.
     * <p>
     * First determines the length of the key to be N, then solves N caesar ciphers to get the key value
     *
     * @param ciphertext the ciphertext to analyse
     * @return the key used to encrypt the plaintext
     */
    public static String calculateKey(String ciphertext) {
        int length = calculateBestGuessKeyLen(ciphertext);
        // couldn't determine the length - give up
        if (length == -1) return null;

        // we know the length of the key
        // now we need to solve 'length' many caesar ciphers - easy!

        // get every character spaced by key length many characters
        // "abcdefgh" with length 3 would be ["adg", "beh", "cf"]
        List<String> caesarCipherStrings = StringUtils.getAllStringsAtInterval(ciphertext, length);
        StringBuilder key = new StringBuilder();
        for (String caesarCipherTxt : caesarCipherStrings) {
            // crack the caesar cipher text
            int shift = CaesarCipher.calculateShift(caesarCipherTxt);
            // add 'A' (65) to the shift value to get an ASCII character in the range A-Z
            char charVal = (char) (shift + 'A');
            // add the character to the key string
            key.append(charVal);
        }

        return key.toString();
    }
}