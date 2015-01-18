package uk.co.andystabler.ciphers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class used for operations concerning the Caesar cipher - encryption, decryption, cracking
 */
public class CaesarCipher {

    public static int ASCII_START_POS = 65;
    public static int ALPHABET_COUNT = 26;

    public static void main(String[] args) throws Exception{

        String plaintext = "ILOVECAKE";
        int shift = 3;
        String cipherText = CaesarCipher.encrypt(3, plaintext);

        System.out.println("Plaintex: " + plaintext);
        System.out.println("Shift: " + shift);
        System.out.println("Ciphertext: " + cipherText);

        String decryptedText = CaesarCipher.decrypt(3, cipherText);
        System.out.println("Decryption result: " + decryptedText);

        System.out.println("\nBrute Force Attack");
        CaesarCipher.bruteForceAttack(cipherText);

        System.out.println("FREQUENCY ANALYSIS");
        System.out.println("Encrypting The Restaurant at the End of the Universe...");
        String bigText = CaesarCipher.readFile(CaesarCipher.class.getResource("/hitch2.txt"));
        String encryptedBigText = CaesarCipher.encrypt(3, bigText);
        System.out.println(encryptedBigText.substring(0, encryptedBigText.length() > 200 ? 200 : encryptedBigText.length() - 1) + "...");

        System.out.println("Performing frequency analysis...");
        String bigCipherText = CaesarCipher.frequencyAnalysis(encryptedBigText);
        System.out.println(bigCipherText.substring(0, bigCipherText.length() > 200 ? 200 : bigCipherText.length() - 1) + "...");
    }

    /**
     * boring stuff - returns the string of data held in the file
     */
    public static String readFile(URL url) throws IOException, URISyntaxException {
        byte[] encoded = Files.readAllBytes(Paths.get(url.toURI()));
        return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();
    }

    /**
     * Encrypts the plaintext by shifting each character by the value of shift
     * @param shift the value to shift each character
     * @param plaintext the message to be encrypted
     * @return the encyrpted string
     */
    public static String encrypt(int shift, String plaintext) {
        // only interested in the alphabet
        plaintext = plaintext.replaceAll("[^a-zA-Z]", "").toUpperCase();
        StringBuilder ciphertext = new StringBuilder();
        /*
        Subtract the starting value of ascii chars we're working with (65) from each character, this should
        give us values in the range of 0-25. Then we can add the shift and perform the modulo operation,
        which again results in a value in the range of 0-25.
        Finally, add the ascii starting value (65) back to each character to get the respective uppercase character.
         */
        for (char c : plaintext.toCharArray())
            ciphertext.append((char) (((c - ASCII_START_POS + shift) % ALPHABET_COUNT) + ASCII_START_POS));

        return ciphertext.toString();
    }

    /**
     * Decrypts the ciphertext by shifting each character in the ciphertext by the negated value of shift.
     *
     * e.g. if the shift value used to encrypt was 3, to decrypt the shift value must be -3 (3 + -3 = 0)
     * @param shift
     * @param ciphertext
     * @return
     */
    public static String decrypt(int shift, String ciphertext) {
        // only interested in the alphabet
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "").toUpperCase();

        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray())
            plaintext.append((char) (((c - ASCII_START_POS - shift) % ALPHABET_COUNT) + ASCII_START_POS));
        return plaintext.toString();
    }

    /**
     * Applies all possible shift values to the ciphertext and prints result
     * <p>
     * Requires human to check results
     * @param ciphertext the ciphertext to decrypt
     */
    public static void bruteForceAttack(String ciphertext) {
        // shift of 0 or 26 would result in no change
        for (int shift = 1; shift < ALPHABET_COUNT; shift++)
            System.out.println("shift: " + shift + ", " + decrypt(shift, ciphertext));
    }

    /**
     * Looks at the frequency of characters in the ciphertext and compares them with the frequencies of characters in the
     * english language.
     * <p>
     * It then calculates the most likely shift value by finding the shift that results in similar frequencies to
     * the english language.
     *
     * @param ciphertext the encrypted message
     * @return best guess at plaintext
     */
    public static String frequencyAnalysis(String ciphertext)
    {
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]","");
        int bestShift = calcBestShft(LetterFrequencyUtils.calculateFrequencies(ciphertext));
        return decrypt(bestShift, ciphertext);
    }

    /**
     * Determines which shift value would produce character frequencies closest to the alphabet
     * @param frequencies the frequencies of characters in the ciphertext
     * @return the best guess shift value
     */
    public static int calcBestShft(double[] frequencies)
    {
        int shift = 0;
        int lowestDiff = Integer.MAX_VALUE;

        // for every letter in the alphabet
        for (int i = 0; i < frequencies.length; i++) {
            // the total difference between frequency of characters in the encrypted message and english language
            int tempDiff = LetterFrequencyUtils.calculateFrequencyOffset(frequencies);
            // keep note of the shift that produces character frequencies similar to the english language
            if (tempDiff < lowestDiff)
            {
                lowestDiff = tempDiff;
                shift = i;
            }
            // decrement the position of each frequency in the array
            shiftFrequencies(frequencies);
        }
        return lowestDiff != Integer.MAX_VALUE ? shift : -1;
    }

    /**
     * shift each element in the frequency array by -1
     * @param messageFrequencies
     */
    public static void shiftFrequencies(double[] messageFrequencies) {
        if (messageFrequencies.length <= 1)
            return;
        double temp = messageFrequencies[0];
        for (int i = 0; i < messageFrequencies.length - 1; i++) {
            messageFrequencies[i] = messageFrequencies[i+1];
        }
        messageFrequencies[messageFrequencies.length-1] = temp;
    }
}