package uk.co.andystabler.ciphers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andy Stabler on 21/04/15.
 */
@RunWith(Enclosed.class)
public class StringUtilsTests {

    public static class RepeatKey {
        @Test
        public void lenMultipleOfKey_KeyRepeatedByWholeNumber() {
            String key = "CAKE";
            int len = 12;
            String expected = "CAKECAKECAKE";
            Assert.assertEquals(expected, StringUtils.repeatString(key, len));
        }

        @Test
        public void lenLTKeyLen_KeySubtr() throws Exception {
            String key = "CAKE";
            int len = 2;
            String expected = "CA";
            Assert.assertEquals(expected, StringUtils.repeatString(key, len));
        }

        @Test
        public void lenGTKeyAndNotMultiple_KeySubtrAtEnd() {
            String key = "CAKE";
            int len = 10;
            String expected = "CAKECAKECA";
            Assert.assertEquals(expected, StringUtils.repeatString(key, len));
        }

        @Test
        public void lenEQKey_KeyNotRepeated() {
            String key = "CAKE";
            int len = 4;
            String expected = "CAKE";
            Assert.assertEquals(expected, StringUtils.repeatString(key, len));
        }
    }

    public static class splitStringAtInterval {

        @Test
        public void emptyString_ReturnsEmptyString() {
            String text = "";
            int interval = 3;
            String expected = "";
            Assert.assertEquals(expected, StringUtils.splitStringAtInterval(text, interval, 0));
        }

        @Test
        public void intervalof1_everyChar() {
            String text = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int interval = 1;
            String expected = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            Assert.assertEquals(expected, StringUtils.splitStringAtInterval(text, interval, 0));
        }

        @Test
        public void intervalof3_every3rdChar() {
            String text = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int interval = 3;
            String expected = "TTTSRYRNERME";
            Assert.assertEquals(expected, StringUtils.splitStringAtInterval(text, interval, 0));
        }

        @Test
        public void intervalGreaterThanStringLength_firstCharacter() {
            String text = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int interval = 50;
            String expected = "T";
            Assert.assertEquals(expected, StringUtils.splitStringAtInterval(text, interval, 0));
        }

        @Test
        public void negativeInterval_originalStringValue() {
            String text = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int interval = -3;
            String expected = text;
            Assert.assertEquals(expected, StringUtils.splitStringAtInterval(text, interval, 0));
        }
    }

    public static class getAllStringsAtInterval {

        @Test
        public void interval3_allStringsAtInterval3() {
            String text = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int interval = 3;
            List<String> expected = Arrays.asList("TTTSRYRNERME", "HRHREPEDVSP", "EUIALUANEIL");
            Assert.assertEquals(expected, StringUtils.getAllStringsAtInterval(text, interval));
        }

        @Test
        public void interval0_emptyList() {
            String text = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int interval = 0;
            List<String> expected = new ArrayList<>();
            Assert.assertEquals(expected, StringUtils.getAllStringsAtInterval(text, interval));
        }

        @Test
        public void intervalMinus1_emptyList() {
            String text = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            int interval = -1;
            List<String> expected = new ArrayList<>();
            Assert.assertEquals(expected, StringUtils.getAllStringsAtInterval(text, interval));
        }

        @Test
        public void intervalLongerThanString_ListOfSingleCharStringsUpToTextLength() {
            String text = "CAKE";
            int interval = 10;
            List<String> expected = Arrays.asList("C", "A", "K", "E");
            Assert.assertEquals(expected, StringUtils.getAllStringsAtInterval(text, interval));
        }
    }
}