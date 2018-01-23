/**
 * 
 */
package ca.issgc.bowling.frame;

import ca.issgc.bowling.rules.AmericanTenPinScoringSystem;

/**
 * Represents a single play in a frame, except the last frame
 * 
 * @author dinhego
 *
 */
public class NormalPlay implements AmericanTenPinScoringSystem, Play {


    private final int firstAttempt;

    private final int secondAttempt;

    private final BonusWon bonus;

    private volatile int currentPointsEarned;

    private volatile boolean pendingPointsToEvaluate;

    /**
     * Constructor with full information needed
     * 
     * @param firstAttempt
     * @param secondAttempt
     */
    public NormalPlay(int firstAttempt, int secondAttempt) {
	super();
	this.firstAttempt = firstAttempt;
	this.secondAttempt = secondAttempt;

	validate();

	bonus = BonusWon.evaluateAPlay(this);

    }

    /**
     * provides simple validation on the pins knocked down
     */
    private void validate() {
	int totalScored = getPinsKnockedDown();

	// simple validation
	if (totalScored < 0 || totalScored > DEFAULT_NUMBER_PINS) {
	    // FIXME localize this message
	    throw new IllegalArgumentException(String.format("Invalid amount of pins knocked down: %d. Max pins is %d.",
		    getPinsKnockedDown(), DEFAULT_NUMBER_PINS));
	}
    }

    /**
     * just a simple flag to indicate that this play needs another play to be fulled
     * evaluated
     */
    @Override
    public void flagPendingPointsToEvaluate() {
	this.pendingPointsToEvaluate = true;
    }

    @Override
    public boolean isPendingPointsToEvaluate() {
	return this.pendingPointsToEvaluate;
    }

    /**
     * set the points earned by play
     * 
     * @param currentPoints
     */
    @Override
    public void setCurrentPointsPerPlay(int currentPoints) {

	if (currentPoints < 0 || currentPoints > MAX_POINTS_EARNED_PER_PLAY) {
	    throw new IllegalArgumentException(
		    String.format("Current points informed [%d] is less than zero or bigger than %d.", currentPoints,
			    MAX_POINTS_EARNED_PER_PLAY));
	}

	this.currentPointsEarned = currentPoints;
    }

    @Override
    public int getCurrentPointsEarned() {
	return currentPointsEarned;
    }

    // getters

    /*
     * (non-Javadoc)
     * 
     * @see ca.issgc.bowling.frame.Play#getFirstAttempt()
     */
    @Override
    public int getFirstAttempt() {
	return firstAttempt;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ca.issgc.bowling.frame.Play#getSecondAttempt()
     */
    @Override
    public int getSecondAttempt() {
	return secondAttempt;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ca.issgc.bowling.frame.Play#getBonus()
     */
    @Override
    public BonusWon getBonus() {
	return bonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ca.issgc.bowling.frame.Play#getPinsKnockedDown()
     */
    @Override
    public int getPinsKnockedDown() {
	return this.firstAttempt + this.secondAttempt;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Play [firstAttempt=").append(firstAttempt).append(", secondAttempt=").append(secondAttempt)
		.append(", ").append("bonus=").append(bonus).append("]");
	return builder.toString();
    }

}
