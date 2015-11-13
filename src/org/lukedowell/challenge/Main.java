package org.lukedowell.challenge;

/**
 * Created by ldowell on 11/12/15.
 */
public class Main {

    public static final String ADDITION_INPUT = "";

    public static final String REMOVAL_INPUT = "";

    public static void main(String[] args) {
        Oxfordinator oxfordinator = new Oxfordinator();

        System.out.println("----ADDITION----" + "\n" +
                            "INPUT: " + Main.ADDITION_INPUT + "\n" +
                            "OUTPUT: " + oxfordinator.addOxfordCommas(Main.ADDITION_INPUT));

        System.out.println("----REMOVAL----" + "\n" +
                            "INPUT: " + Main.REMOVAL_INPUT + "\n" +
                            "OUTPUT: " + oxfordinator.removeOxfordCommas(Main.REMOVAL_INPUT));
    }
}
