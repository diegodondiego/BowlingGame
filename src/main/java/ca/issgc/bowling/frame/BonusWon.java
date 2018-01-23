/**
 * 
 */
package ca.issgc.bowling.frame;

import ca.issgc.bowling.rules.Basics;

/**
 * Possible types of bonuses for a play
 * 
 * @author dinhego
 *
 */
public enum BonusWon implements Basics {

    NO_BONUS, SPARE, STRIKE;

    /**
     * Evaluate which bonus could be applied to a play
     * 
     * @param play
     * @return the Bonus won at this play
     */
    public static BonusWon evaluateAPlay(Play play) {

	if (play == null) {
	    throw new IllegalArgumentException("Null play to evaluate!");
	}

	// first play all the pins are knocked down, so a strike
	if (play.getFirstAttempt() == DEFAULT_NUMBER_PINS) {
	    return STRIKE;
	}

	// knocked all the pins in the two attempts
	if (play.getPinsKnockedDown() == DEFAULT_NUMBER_PINS) {
	    return SPARE;
	}

	return NO_BONUS;
    }

}
