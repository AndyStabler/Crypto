package uk.co.andystabler.ciphers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * Created by Andy Stabler on 19/04/15.
 */
@RunWith(Enclosed.class)
public class LetterFrequencyUtilsTests {

    public static class indexOfCoincidence {

        @Test
        public void shortStringCorrectnessTest1() {
            String text = "THETRUTHISRARELYPUREANDNEVERSIMPLE";
            double ic = 0.06417112299465241;
            Assert.assertEquals(ic, LetterFrequencyUtils.indexOfCoincidence(text), 0);
        }

        @Test
        public void shortStringCorrectnessTest2() {
            String text = "CAKECAKE";
            double ic = 0.14285714285714285;
            Assert.assertEquals(ic, LetterFrequencyUtils.indexOfCoincidence(text), 0);
        }

        @Test
        public void emptyString_Returns0() {
            String text = "";
            double ic = 0.0;
            Assert.assertEquals(ic, LetterFrequencyUtils.indexOfCoincidence(text), 0);
        }

        @Test
        public void longTextCorrectnessTest() {
            String text = "Far out in the uncharted backwaters of the unfashionable  end  of\n" +
                    "the  western  spiral  arm  of  the Galaxy lies a small unregarded\n" +
                    "yellow sun.\n" +
                    "\n" +
                    "Orbiting this at a distance of roughly ninety-two  million  miles\n" +
                    "is  an  utterly insignificant little blue green planet whose ape-\n" +
                    "descended life forms are so amazingly primitive that  they  still\n" +
                    "think digital watches are a pretty neat idea.\n" +
                    "\n" +
                    "This planet has - or rather had - a problem, which was this: most\n" +
                    "of  the  people  on  it were unhappy for pretty much of the time.\n" +
                    "Many solutions were suggested for this problem, but most of these\n" +
                    "were  largely  concerned with the movements of small green pieces\n" +
                    "of paper, which is odd because on the whole it wasn't  the  small\n" +
                    "green pieces of paper that were unhappy.\n" +
                    "\n" +
                    "And so the problem remained; lots of the people  were  mean,  and\n" +
                    "most of them were miserable, even the ones with digital watches.\n" +
                    "\n" +
                    "Many were increasingly of the opinion that they'd all made a  big\n" +
                    "mistake  in  coming  down  from the trees in the first place. And\n" +
                    "some said that even the trees had been a bad move,  and  that  no\n" +
                    "one should ever have left the oceans.\n" +
                    "\n" +
                    "And then, one Thursday, nearly two thousand years after  one  man\n" +
                    "had  been nailed to a tree for saying how great it would be to be\n" +
                    "nice to people for a change, one girl sitting on  her  own  in  a\n" +
                    "small  cafe  in  Rickmansworth suddenly realized what it was that\n" +
                    "had been going wrong all this time, and she finally knew how  the\n" +
                    "world  could  be  made  a  good and happy place. This time it was\n" +
                    "right, it would work, and no one would  have  to  get  nailed  to\n" +
                    "anything.";
            double ic = 0.066700912423777;
            Assert.assertEquals(ic, LetterFrequencyUtils.indexOfCoincidence(text), 1e-6);
        }
    }

    public static class closeToEnglish {
        @Test
        public void validEnglish_True() {
            String text = "False facts are highly injurious to the progress of science, for they often endure long; but " +
                    "false views, if supported by some evidence, do little harm, for every one takes a salutary pleasure " +
                    "in proving their falseness.";
            double ic = LetterFrequencyUtils.indexOfCoincidence(text);
            Assert.assertTrue(LetterFrequencyUtils.closeToEng(ic));
        }

        @Test
        public void longEnglish_True() {
            String text = "Far out in the uncharted backwaters of the unfashionable  end  of\n" +
                    "the  western  spiral  arm  of  the Galaxy lies a small unregarded\n" +
                    "yellow sun.\n" +
                    "\n" +
                    "Orbiting this at a distance of roughly ninety-two  million  miles\n" +
                    "is  an  utterly insignificant little blue green planet whose ape-\n" +
                    "descended life forms are so amazingly primitive that  they  still\n" +
                    "think digital watches are a pretty neat idea.\n" +
                    "\n" +
                    "This planet has - or rather had - a problem, which was this: most\n" +
                    "of  the  people  on  it were unhappy for pretty much of the time.\n" +
                    "Many solutions were suggested for this problem, but most of these\n" +
                    "were  largely  concerned with the movements of small green pieces\n" +
                    "of paper, which is odd because on the whole it wasn't  the  small\n" +
                    "green pieces of paper that were unhappy.\n" +
                    "\n" +
                    "And so the problem remained; lots of the people  were  mean,  and\n" +
                    "most of them were miserable, even the ones with digital watches.\n" +
                    "\n" +
                    "Many were increasingly of the opinion that they'd all made a  big\n" +
                    "mistake  in  coming  down  from the trees in the first place. And\n" +
                    "some said that even the trees had been a bad move,  and  that  no\n" +
                    "one should ever have left the oceans.\n" +
                    "\n" +
                    "And then, one Thursday, nearly two thousand years after  one  man\n" +
                    "had  been nailed to a tree for saying how great it would be to be\n" +
                    "nice to people for a change, one girl sitting on  her  own  in  a\n" +
                    "small  cafe  in  Rickmansworth suddenly realized what it was that\n" +
                    "had been going wrong all this time, and she finally knew how  the\n" +
                    "world  could  be  made  a  good and happy place. This time it was\n" +
                    "right, it would work, and no one would  have  to  get  nailed  to\n" +
                    "anything.";
            double ic = LetterFrequencyUtils.indexOfCoincidence(text);
            Assert.assertTrue(LetterFrequencyUtils.closeToEng(ic));
        }

        @Test
        public void germanText_False() {
            String text = "Das Leben ist eine Fremdsprache. Alle Menschen sprechen es falsch aus.";
            double ic = LetterFrequencyUtils.indexOfCoincidence(text);
            Assert.assertFalse(LetterFrequencyUtils.closeToEng(ic));
        }

        @Test
        public void randomText_Fals() {
            String text = "qwertyuioplkjhgfdsazxcvbnmmnbvcxzlkjhgfdsapoiuytrewq";
            double ic = LetterFrequencyUtils.indexOfCoincidence(text);
            Assert.assertFalse(LetterFrequencyUtils.closeToEng(ic));
        }
    }
}