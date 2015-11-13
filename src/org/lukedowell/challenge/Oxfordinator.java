package org.lukedowell.challenge;

/**
 * Created by ldowell on 11/12/15.
 */
public class Oxfordinator {

    private static final String[] CONJUNCTIONS = {
            "FOR",
            "AND",
            "NOR",
            "BUT",
            "OR",
            "YET",
            "SO"
    };

    private static final String KEEP_SEMICOLON_DELIMITER = "(?<=;)";
    private static final String KEEP_COLON_DELIMITER = "(?<=:)";

    public String addOxfordCommas(String input) {
        //Setup our string builder and split our input into chunks
        StringBuilder output = new StringBuilder();
        String[] chunks = getChunks(input);

        //Process each chunk
        for (String chunk : chunks) {
            //If the chunk contains a qualifying series
            if (containsQualifyingSeries(chunk, 1)) {
                //Find the index of that last conjunction in the chunk
                int conjIndex = getIndexOfLastConjunction(chunk);

                //Add a comma at one index before the conjunction
                String formattedChunk = chunk.substring(0, conjIndex - 1) + ", " +
                        chunk.substring(conjIndex, chunk.length());
                output.append(formattedChunk);
            } else {
                output.append(chunk);
            }
        }
        return output.toString();
    }

    public String removeOxfordCommas(String input) {
        //Setup our string builder and split our input into chunks
        StringBuilder output = new StringBuilder();
        String[] chunks = getChunks(input);

        //Process each chunk
        for (String chunk : chunks) {
            //If the chunk contains a qualifying series
            if (containsQualifyingSeries(chunk, 2)) {
                //Find the index of that last conjunction in the chunk
                int conjIndex = getIndexOfLastConjunction(chunk);

                //Remove the preceding comma
                String formattedChunk = chunk.substring(0, conjIndex-2) + " " + (chunk.substring(conjIndex, chunk.length()));
                output.append(formattedChunk);
            } else {
                output.append(chunk);
            }
        }
        return output.toString();
    }

    /**
     * Splits the input into chunks in case the input
     * is split with a semicolon or a colon. Regex is
     * used to keep the delimiter in the chunk.
     *
     * @param input
     *      The input to split
     * @return
     *      A array of strings.
     */
    public String[] getChunks(String input) {
        String[] chunks = {input};

        //Check to see if our input contains a ; or a :
        //Split if so
        if(input.contains(";") && input.contains(":")) {

            //\_(ツ)_/¯

        } else if (input.contains(";")) {

            chunks = input.split(Oxfordinator.KEEP_SEMICOLON_DELIMITER);

        } else if (input.contains(":")) {

            chunks = input.split(Oxfordinator.KEEP_COLON_DELIMITER);

        }

        return chunks;
    }
    /**
     * Processes a sentence chunk and tries to determine whether or not
     * it is a qualifying series. A qualifying series is defined by a
     * list of three or more objects. For series addition, there only
     * needs to be one comma. For removal, there should be two.
     *
     * Example : Apples, bananas, and grapes.
     *
     * A non qualifying list: Pears and peaches.
     *
     *
     * @param chunk
     *      The sentence chunk to analyze. It is a chunk because sentences
     *      can be split with ; or : and contain separate lists.
     * @return
     *      True if the method determines the chunk is likely a qualifying
     *      series. False otherwise.
     */
    public boolean containsQualifyingSeries(String chunk, int length) {
        if(countOccurrences(chunk, ",") >= length) {
            return getIndexOfLastConjunction(chunk) > 0;
        }
        return false;
    }

    /**
     * Returns the index of the last conjunction in a sentence
     * @param chunk
     *      The sentence chunk that will be processed
     * @return
     *      The index of the conjunction closest to the end of
     *      the input string. 0 if none found.
     */
    public int getIndexOfLastConjunction(String chunk) {
        int lastIndex = 0;
        //Set to lower case
        String lwrChunk = chunk.toLowerCase();
        for(String conjunction : Oxfordinator.CONJUNCTIONS) {
            //Set conjunction to lower case
            String lwrConjunction = conjunction.toLowerCase();

            //If the sentence contains the conjunction
            if(lwrChunk.contains(lwrConjunction)) {
                //Save the index of the conjunction
                int conjunctionIndex = lwrChunk.lastIndexOf(lwrConjunction);

                //If the index is higher than the last highest conjunction
                if(conjunctionIndex > lastIndex) {
                    //Save it
                    lastIndex = conjunctionIndex;
                }
            }
        }
        return lastIndex;
    }

    /**
     * Counts the number of times a given character occurs in a string
     * @param input
     *      The input string to process
     * @param target
     *      The target string to count
     * @return
     *      Number of times target string occurs in input string.
     */
    public int countOccurrences(String input, String target) {
        return input.length() - input.replace(target, "").length();
    }
}
