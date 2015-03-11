package uk.co.andystabler.ciphers;

/**
 * Created by andy on 04/03/15.
 */
public class VigenereCipher {

    public static int ASCII_START_POS = 65;

    /**
     * Encrypts the {@code plaintext} using the Vigenère cipher with the {@code key} provided.
     * <p>See {@link uk.co.andystabler.ciphers.VigenereCipher#repeatKey} for more details on how the key is used.
     * @param plaintext the text to be encrypted
     * @param key the key to encrypt the plaintext- must be characters matching [a-z]
     * @return
     */
    public static String encrypt(String plaintext, String key)
    {
        // key must be an alphabetic strng
        if (!key.matches("[a-zA-Z]+")) throw new IllegalArgumentException("Invalid key - must be one or more characters in range a...z");

        // only interested in the alphabet
        plaintext = plaintext.replaceAll("[^a-zA-Z]", "").toUpperCase();

        // make key same length as plaintext
        key = repeatKey(key, plaintext.length()).toUpperCase();

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++)
            ciphertext.append(CaesarCipher.encrypt(String.valueOf(plaintext.charAt(i)), key.charAt(i) - ASCII_START_POS));
        return ciphertext.toString();
    }

    /**
     * Decrypts the {@code ciphertext} using the Vigenère cipher with the {@code key} provided.
     * <p>See {@link uk.co.andystabler.ciphers.VigenereCipher#repeatKey} for more details on how the key is used.
     * @param ciphertext the text to be decrypted
     * @param key the key to decrypt the ciphertext- must be characters matching [a-z]
     * @return
     */
    public static String decrypt(String ciphertext, String key)
    {
        // key must be an alphabetic strng
        if (!key.matches("[a-zA-Z]+")) throw new IllegalArgumentException("Invalid key - must be one or more characters in range a...z");

        // only interested in the alphabet
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "").toUpperCase();

        // make key same length as plaintext
        key = repeatKey(key, ciphertext.length()).toUpperCase();

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++)
            plaintext.append(CaesarCipher.decrypt(String.valueOf(ciphertext.charAt(i)), key.charAt(i) - ASCII_START_POS));
        return plaintext.toString();
    }

    /**
     * Repeats a given key so its length matches that of the {@code length} argument.
     * <p>Note:
     * <p>if the length of the {@code key} is less than {@code length}, it will be truncated
     * <p>if {@code length} is not divisible by the length of the {@code key}, a substring of the key
     * will appear at the end of the repeated key,
     * <p>e.g.
     * <pre>{@code
     * key = "hello"
     * length = 12
     * returns "hellohellohe"
     * }</pre>
     * @param key the key to be repeated
     * @param length the required length
     * @return the repeated key
     */
    public static String repeatKey(String key, int length)
    {
        if (key == null || length <= 0) return "";
        if (key.length() == length) return key;
        if (key.length() > length) return key.substring(0, length);

        // need to repeat the key
        int keysInLen = length / key.length();
        // if the key doesn't fit into the length exactly, a truncated version will be used at the end
        int remainder = length - (key.length()*keysInLen);

        StringBuilder newKey = new StringBuilder();
        for (int i = 0; i < keysInLen; i++)
            newKey.append(key);
        newKey.append(key.substring(0, remainder));
        return newKey.toString();
    }
}