/**
 * 
 */
package ca.issgc.bowling.frame;

import ca.issgc.bowling.rules.AmericanTenPinScoringSystem;

/**
 * Represents a single play in a frame
 * 
 * @author dinhego
 *
 */
public class Play implements AmericanTenPinScoringSystem {

    private final int firstAttempt;

    private final int secondAttempt;

    private final BonusWon bonus;

    /**
     * Constructor with full information needed
     * 
     * @param firstAttempt
     * @param secondAttempt
     */
    public Play(int firstAttempt, int secondAttempt) {
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

    // getters

    public int getFirstAttempt() {
	return firstAttempt;
    }

    public int getSecondAttempt() {
	return secondAttempt;
    }

    public BonusWon getBonus() {
	return bonus;
    }

    /**
     * 
     * @return the amount, validated, of pins knocked down in the two attempts
     */
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
