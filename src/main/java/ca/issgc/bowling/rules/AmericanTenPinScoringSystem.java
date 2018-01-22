/**
 * 
 */
package ca.issgc.bowling.rules;

import ca.issgc.bowling.frame.BonusWon;
import ca.issgc.bowling.frame.Play;

/**
 * Rules used to play the American Ten-Pin bowling
 * 
 * @author dinhego
 *
 */
public interface AmericanTenPinScoringSystem extends Basics {

    /**
     * bonus multiplier to be applied to a play following a strike
     */
    int STRIKE_BONUS_MULTIPLIER = 2;

    /**
     * <<<<<<< HEAD Evaluate the bonus points to be applied to a current play based
     * on the next play played
     * 
     * @param evaluatedPlay
     *            the play to be evaluated. if is informed as <code>null</code>, is
     *            considered a first play
     * @param nextPlay
     *            the play to have the bonus applied
     * @param secondPlayAfter
     *            the next play to be evaluated, in case of a strikes in a row
     * @return the amount of points earned in the evaluated play (check
     *         {@link Basics#POINTS_PER_PIN_KNOCKED_DOWN} to get the value earned by
     *         pin knocked down)
     */
    default int calculateOrUpdateAPlayPointsAndBonus(Play evaluatedPlay, Play nextPlay, Play secondPlayAfter) {

	// simple evaluations
	if (evaluatedPlay == null) {
	    throw new IllegalArgumentException("No play to evaluate!");
	}

	if (nextPlay == null) {
	    // is probably the first play, so return as if there is no bonus to apply
	    return evaluatedPlay.getPinsKnockedDown() * POINTS_PER_PIN_KNOCKED_DOWN;
	}

	// evaluate
	switch (evaluatedPlay.getBonus()) {

	    case NO_BONUS:
		// no bonus applied
		return evaluatedPlay.getPinsKnockedDown() * POINTS_PER_PIN_KNOCKED_DOWN;
	    case STRIKE:
		// add the next two attempts
		int amountOfPinsToAdd = evaluatedPlay.getPinsKnockedDown();
		amountOfPinsToAdd += nextPlay.getFirstAttempt();

		// if the next play was also a strike, add the next attempt from the second play
		if (BonusWon.STRIKE.equals(nextPlay.getBonus())) {
		    // if a second play isn't informed to evaluation, mark this play as pending
		    // evaluation
		    if (secondPlayAfter == null) {
			evaluatedPlay.flagPendingPointsToEvaluate();
		    }
		    else {
			amountOfPinsToAdd += secondPlayAfter.getFirstAttempt();
		    }
		}
		else {
		    amountOfPinsToAdd += nextPlay.getSecondAttempt();
		}

		return amountOfPinsToAdd * POINTS_PER_PIN_KNOCKED_DOWN;
	    case SPARE:
		// double the value of the first attempt after the spare
		return (evaluatedPlay.getPinsKnockedDown() + nextPlay.getFirstAttempt()) * POINTS_PER_PIN_KNOCKED_DOWN;
	    default:
		throw new UnsupportedOperationException("Bonus not implemented: " + evaluatedPlay.getBonus());

	}
    }
}
