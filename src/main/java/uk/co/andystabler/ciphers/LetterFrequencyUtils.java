package uk.co.andystabler.ciphers;

/**
 * Utility class to perform frequency operations on text
 */
public class LetterFrequencyUtils {

    public static int ALPHABET_COUNT = 26;

    /* The index of coincidence bounds text should have if it is in the english language */
    public static double ENG_IC_LOWER_BOUND = 0.064;
    public static double ENG_IC_UPPER_BOUND = 0.069;
    public static double ENG_IC = 0.0686;

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
     * Compares inFreq with frequencies of english language and returns the total offset
     *
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
    public static double[] calculateFrequencies(String message) {
        // only interested in the alphabet
        message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();

        double[] frequencies = new double[ALPHABET_COUNT];

        // update the frequency of this character using the ASCII character value
        for (char c : message.toCharArray())
            frequencies[c - 'A'] += (100.0 / message.length());

        return frequencies;
    }

    public static int[] countCharacters(String message) {
        message = message.replaceAll("[^a-zA-Z]", "").toUpperCase();
        int[] counts = new int[ALPHABET_COUNT];
        for (char c : message.toCharArray())
            counts[c - 'A']++;
        return counts;
    }

    /**
     * calculates the expected number of times each character in the alphabet should occur based on the values in {@link LetterFrequencyUtils#FREQUENCIES}
     *
     * @param length the length of the ciphertext
     * @return the expected number of total of occurrences for each character
     */
    public static double[] expectedCharacterCounts(int length) {
        double[] expected = new double[ALPHABET_COUNT];
        for (int i = 0; i < ALPHABET_COUNT; i++) {
            // length * P(i)
            expected[i] = (length * (FREQUENCIES[i] / 100));
        }
        return expected;
    }

    /**
     * Determines whether the passed index of coincidence is within a tolerance value of the Index of Coincidence of the
     * english language
     * <p>
     * The passed index of coincidence must be greater than {@link LetterFrequencyUtils#ENG_IC_LOWER_BOUND} and lower than
     * {@link LetterFrequencyUtils#ENG_IC_UPPER_BOUND} to be considered "close" to the english language.
     */
    public static boolean closeToEng(double indexOfCoincidence) {
        return ENG_IC_LOWER_BOUND < indexOfCoincidence && indexOfCoincidence < ENG_IC_UPPER_BOUND;
    }

    /**
     * Index of Coincidence is {@code Σ((Pi)*(Pi*N-1)/(N-1))}, where Pi is the probability of the {@code ith} character occurring in the text and N is the length of the input.
     * <p>
     * The probability of characters occurring in the text is calculated by counting occurrences of characters in the text, done
     * in the {@link LetterFrequencyUtils#calculateFrequencies(String)} method.
     *
     * @param text the text to analyse
     * @return index of coincidence
     */
    public static double indexOfCoincidence(String text) {
        double indexCo = 0.0;
        // ignore anything other than the alphabet
        text = text.replaceAll("[^a-zA-Z]", "");

        // get the probability of each character occuring in the text
        double[] inStringFreq = LetterFrequencyUtils.calculateFrequencies(text);

        for (int i = 0; i < inStringFreq.length; i++) {
            // ICi = Pi * (Pi * (N - 1))/(N - 1)
            double pi = inStringFreq[i] / 100;
            indexCo += pi * ((pi * text.length() - 1) / (text.length() - 1));
        }

        return indexCo;
    }

    public static double chiSquaredAgainstEnglish(String ciphertext) {
        ciphertext.replaceAll("[^a-zA-Z]", "");
        // an array containing the total number of times each character occurred in the ciphertext
        int[] characterCounts = LetterFrequencyUtils.countCharacters(ciphertext);
        // an array containing the expected number of times each character should occur in text the length of the
        // ciphertext
        double[] expectedCharacterCounts = LetterFrequencyUtils.expectedCharacterCounts(ciphertext.length());

        double fitness = 0.0;
        for (int i = 0; i < LetterFrequencyUtils.ALPHABET_COUNT; i++) {
            //((Ci - Ei)^2)/Ei
            fitness += Math.pow(characterCounts[i] - expectedCharacterCounts[i], 2) / expectedCharacterCounts[i];
        }
        return fitness;
    }
}
