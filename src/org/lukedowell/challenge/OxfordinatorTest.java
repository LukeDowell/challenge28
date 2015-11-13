package org.lukedowell.challenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ldowell on 11/12/15.
 */
public class OxfordinatorTest {

    private Oxfordinator oxfordinator;

    @Before
    public void init() {
        oxfordinator = new Oxfordinator();
    }

    @Test
    public void testLastIndex() throws Exception {
        int index = oxfordinator.getIndexOfLastConjunction("and for nor yet or but so");
        assertEquals(23, index);
    }

    @Test
    public void testOccurrences() throws Exception {
        int numOccurrences = oxfordinator.countOccurrences("Hi, My, Name, Is, Luke.", ",");
        assertEquals(4, numOccurrences);
    }

    @Test
    public void testAddOxfordCommas() throws Exception {
        String output = oxfordinator.addOxfordCommas("I like flour, sugar and chocolate in my brownies.");
        assertEquals("I like flour, sugar, and chocolate in my brownies.", output);
    }

    @Test
    public void testAddOxfordCommasTrick() throws Exception {
        //TODO: better trick...
        String output = oxfordinator.addOxfordCommas("horses, mules and cattle; but horses and mules and cattle");
        assertEquals("horses, mules, and cattle; but horses and mules and cattle", output);
    }

    @Test
    public void testAddOxfordCommasAppositive() throws Exception {
        String input = "Genette's bedroom desk, the biggest disaster area in the house, is a collection of overdue " +
                "library books, dirty plates, computer components, old mail, cat hair and empty potato chip bags.";
        String output = oxfordinator.addOxfordCommas(input);
        assertEquals("Genette's bedroom desk, the biggest disaster area in the house, is a collection of overdue " +
                "library books, dirty plates, computer components, old mail, cat hair, and empty potato chip bags.", output);
    }

    @Test
    public void testRemoveOxfordCommas() throws Exception {
        String output = oxfordinator.removeOxfordCommas("I like flour, sugar, and chocolate in my brownies.");
        assertEquals("I like flour, sugar and chocolate in my brownies.", output);
    }

    @Test
    public void testRemoveOxfordCommasTrick() throws Exception {
        String output = oxfordinator.removeOxfordCommas("His plan will fail, and did you really think it would succeed?");
        assertEquals("His plan will fail, and did you really think it would succeed?", output);
    }
}