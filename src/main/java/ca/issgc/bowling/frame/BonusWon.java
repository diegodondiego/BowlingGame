/**
 * 
 */
package ca.issgc.bowling.frame;

import ca.issgc.bowling.rules.AmericanTenPinScoring;
import ca.issgc.bowling.rules.Basics;

/**
 * Possible types of bonuses for a play
 * 
 * @author dinhego
 *
 */
public enum BonusWon implements AmericanTenPinScoring {

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

    /**
     * Evaluate the bonus points to be applied to a current play based on the last
     * play played
     * 
     * @param lastPlay
     *            the play to be evaluated
     * @param currentPlay
     *            the play to have the bonus applied
     * @return the amount of points earned in the current play (check
     *         {@link Basics#POINTS_PER_PIN_KNOCKED_DOWN} to get the value earned by
     *         pin knocked down)
     */
    public static int calculatePlayPointsAndBonus(Play lastPlay, Play currentPlay) {

	if (lastPlay == null || currentPlay == null) {
	    throw new IllegalArgumentException("Invalid plays informed!");
	}

	// evaluate
	switch (lastPlay.getBonus()) {

	    case NO_BONUS:
		// no bonus applied
		return currentPlay.getPinsKnockedDown() * POINTS_PER_PIN_KNOCKED_DOWN;
	    case STRIKE:
		// double the value of the pins knocked on this play
		return currentPlay.getPinsKnockedDown() * STRIKE_BONUS_MULTIPLIER * POINTS_PER_PIN_KNOCKED_DOWN;
	    case SPARE:
		// double the value of the first attempt after the spare
		return (currentPlay.getPinsKnockedDown() + currentPlay.getFirstAttempt()) * POINTS_PER_PIN_KNOCKED_DOWN;
	    default:
		throw new UnsupportedOperationException("Bonus not implemented: " + lastPlay.getBonus());
	}
    }

}
