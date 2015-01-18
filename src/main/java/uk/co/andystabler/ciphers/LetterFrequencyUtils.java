package uk.co.andystabler.ciphers;

/**
 * Utility class to perform frequency operations on text
 */
public class LetterFrequencyUtils {

    public static int ASCII_START_POS = 65;
    public static int ALPHABET_COUNT = 26;

    /*
     * Frequencies of characters in the english alphabet
     */
    public static double A = 8.167;
    public static double B = 1.492;
    public static double C = 2.782;
    public static double D = 4.253;
    public static double E = 12.702;
    public static double F = 2.228;
    public static double G = 2.015;
    public static double H = 6.094;
    public static double I = 6.966;
    public static double J = 0.153;
    public static double K = 0.772;
    public static double L = 4.025;
    public static double M = 2.406;
    public static double N = 6.749;
    public static double O = 7.507;
    public static double P = 1.929;
    public static double Q = 0.095;
    public static double R = 5.987;
    public static double S = 6.327;
    public static double T = 9.056;
    public static double U = 2.758;
    public static double V = 0.978;
    public static double W = 2.360;
    public static double X = 0.150;
    public static double Y = 1.974;
    public static double Z = 0.074;

    public static double[] FREQUENCIES = {
            A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
    };

    /**
     * @return empty array of frequencies
     */
    public static double[] initialiseFrequencies() {
        double[] freq = new double[ALPHABET_COUNT];
        for (int i = 0; i < freq.length; i++)
            freq[i] = 0.0;
        return freq;
    }

    /**
     * Compares inFreq with frequencies of english language and returns the total offset
     * @param inFreq frequencies of characters in ciphertext
     * @return total offset value
     */
    public static int calculateFrequencyOffset(double[] inFreq) {
        int currentDiff = 0;
        for (int i = 0; i < inFreq.length; i++)
            currentDiff += Math.abs(FREQUENCIES[i] - inFreq[i]);
        return currentDiff;
    }

    /**
     * @return the frequency of each alphabetic character in the message
     */
    public static double[] calculateFrequencies(String message)
    {
        // only interested in the alphabet
        message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();

        double[] frequencies = LetterFrequencyUtils.initialiseFrequencies();

        // update the frequency of this character using the ASCII character value
        for (char c : message.toUpperCase().toCharArray())
            frequencies[(c - ASCII_START_POS) % ALPHABET_COUNT] += (100.0 / message.length());

        return frequencies;
    }
}
