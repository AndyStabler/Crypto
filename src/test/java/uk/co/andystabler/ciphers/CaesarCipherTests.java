package uk.co.andystabler.ciphers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class CaesarCipherTests {

    public static class encrypt {
        @Test
        public void emptyPlaintext_ReturnsEmptyString() {
            String plaintext = "";
            int shift = 5;
            String expected = "";
            Assert.assertEquals(expected, CaesarCipher.encrypt(plaintext, shift));
        }

        @Test
        public void shift0_noChange() {
            String plaintext = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int shift = 0;
            String expected = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            Assert.assertEquals(expected, CaesarCipher.encrypt(plaintext, shift));
        }

        @Test
        public void shift5_allShiftedForwards5() {
            String plaintext = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int shift = 5;
            String expected = "YMJYWZYMNXWFWJQDUZWJFSISJAJWXNRUQJ";
            Assert.assertEquals(expected, CaesarCipher.encrypt(plaintext, shift));
        }

        @Test
        public void shiftNeg5_allShiftedBackwards5() {
            String plaintext = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int shift = -5;
            String expected = "OCZOMPOCDNMVMZGTKPMZVIYIZQZMNDHKGZ";
            Assert.assertEquals(expected, CaesarCipher.encrypt(plaintext, shift));
        }
    }

    public static class decrypt {
        @Test
        public void emptyPlaintext_ReturnsEmptyString() {
            String ciphertext = "";
            int shift = 5;
            String expected = "";
            Assert.assertEquals(expected, CaesarCipher.decrypt(ciphertext, shift));
        }

        @Test
        public void shift0_noChange() {
            String ciphertext = "YMJYWZYMNXWFWJQDUZWJFSISJAJWXNRUQJ";
            int shift = 0;
            String expected = "YMJYWZYMNXWFWJQDUZWJFSISJAJWXNRUQJ";
            Assert.assertEquals(expected, CaesarCipher.encrypt(ciphertext, shift));
        }

        @Test
        public void shift5_allShiftedBacwards5() {
            String ciphertext = "YMJYWZYMNXWFWJQDUZWJFSISJAJWXNRUQJ";
            int shift = 5;
            String expected = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            Assert.assertEquals(expected, CaesarCipher.decrypt(ciphertext, shift));
        }

        @Test
        public void shiftNeg5_allShiftedForward5() {
            String ciphertext = "OCZOMPOCDNMVMZGTKPMZVIYIZQZMNDHKGZ";
            int shift = -5;
            String expected = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            Assert.assertEquals(expected, CaesarCipher.decrypt(ciphertext, shift));
        }
    }

    public static class calculateShiftValue {

        @Test
        public void shift1() {
            String ciphertext = "UIFUSVUIJTSBSFMZQVSFBOEOFWFSTJNQMF";
            int expectedShift = 1;
            Assert.assertEquals(expectedShift, CaesarCipher.calculateShift(ciphertext));
        }

        @Test
        public void shift3() {
            String ciphertext = "WKHWUXWKLVUDUHOBSXUHDQGQHYHUVLPSOH";
            int expectedShift = 3;
            Assert.assertEquals(expectedShift, CaesarCipher.calculateShift(ciphertext));
        }

        @Test
        public void shift5() {
            String ciphertext = "YMJYWZYMNXWFWJQDUZWJFSISJAJWXNRUQJ";
            int expectedShift = 5;
            Assert.assertEquals(expectedShift, CaesarCipher.calculateShift(ciphertext));
        }

    }

}