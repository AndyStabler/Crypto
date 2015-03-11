package uk.co.andystabler.ciphers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class VigenereCipherTests {

    public static class RepeatKey {
        @Test
        public void lenMultipleOfKey_KeyRepeatedByWholeNumber() {
            String key = "CAKE";
            int len = 12;
            String expected = "CAKECAKECAKE";
            Assert.assertEquals(expected, VigenereCipher.repeatKey(key, len));
        }

        @Test
        public void lenLTKeyLen_KeySubtr() throws Exception {
            String key = "CAKE";
            int len = 2;
            String expected = "CA";
            Assert.assertEquals(expected, VigenereCipher.repeatKey(key, len));
        }

        @Test
        public void lenGTKeyAndNotMultiple_KeySubtrAtEnd() {
            String key = "CAKE";
            int len = 10;
            String expected = "CAKECAKECA";
            Assert.assertEquals(expected, VigenereCipher.repeatKey(key, len));
        }

        @Test
        public void lenEQKey_KeyNotRepeated() {
            String key = "CAKE";
            int len = 4;
            String expected = "CAKE";
            Assert.assertEquals(expected, VigenereCipher.repeatKey(key, len));
        }
    }

    public static class encrypt {

        @Test
        public void emptyString_ReturnsEmptyString() {
            String plaintext = "";
            String key = "WILDE";
            String expected = "";
            Assert.assertEquals(expected, VigenereCipher.encrypt(plaintext, key));
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyKey_IllegalArgumentException() {
            String plaintext = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            String key = "";
            VigenereCipher.encrypt(plaintext, key);
        }

        @Test
        public void validKey_encrypts() {
            String plaintext = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            String key = "WILDE";
            String expected = "PPPWVQBSLWNICHPUXFUIWVOQIRMCVMIXWH";
            Assert.assertEquals(expected, VigenereCipher.encrypt(plaintext, key));
        }

        @Test
        public void mixedCasePlaintext_EncryptCorrect() {
            String plaintext = "THeTRuTHISRARELYPUREANDneverSImPLE";
            String key = "WILDE";
            String expected = "PPPWVQBSLWNICHPUXFUIWVOQIRMCVMIXWH";
            Assert.assertEquals(expected, VigenereCipher.encrypt(plaintext, key));
        }
    }

    public static class decrypt {

        @Test
        public void emptyString_ReturnsEmptyString() {
            String ciphertext = "";
            String key = "WILDE";
            String expected = "";
            Assert.assertEquals(expected, VigenereCipher.encrypt(ciphertext, key));
        }

        @Test(expected = IllegalArgumentException.class)
        public void emptyKey_IllegalArgumentException() {
            String plaintext = "PPPWVQBSLWNICHPUXFUIWVOQIRMCVMIXWH";
            String key = "";
            VigenereCipher.encrypt(plaintext, key);
        }

        @Test
        public void validKey_decrypts() {
            String plaintext = "PPPWVQBSLWNICHPUXFUIWVOQIRMCVMIXWH";
            String key = "WILDE";
            String expected = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            Assert.assertEquals(expected, VigenereCipher.decrypt(plaintext, key));
        }

        @Test
        public void mixedCaseCiphertext_DecryptCorrect() {
            String ciphertext = "PPPWVqBSLWNIcHPUXFUIWVOQIRMCVMIXWH";
            String key = "WILDE";
            String expected = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            Assert.assertEquals(expected, VigenereCipher.decrypt(ciphertext, key));
        }
    }

}