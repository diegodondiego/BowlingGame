/**
 * 
 */
package ca.issgc.bowling.rules;

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
     * Evaluate the bonus points to be applied to a current play based on the last
     * play played
     * 
     * @param lastPlay
     *            the play to be evaluated. if is informed as <code>null</code>, is
     *            considered a first play
     * @param currentPlay
     *            the play to have the bonus applied
     * @return the amount of points earned in the current play (check
     *         {@link Basics#POINTS_PER_PIN_KNOCKED_DOWN} to get the value earned by
     *         pin knocked down)
     */
    default int calculatePlayPointsAndBonus(Play lastPlay, Play currentPlay) {

	if (currentPlay == null) {
	    throw new IllegalArgumentException("No current play informed!");
	}

	if (lastPlay == null) {
	    // is probably the first play, so return as if there is no bonus to apply
	    return currentPlay.getPinsKnockedDown() * POINTS_PER_PIN_KNOCKED_DOWN;
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
