/**
 * 
 */
package ca.issgc.bowling.frame;

import ca.issgc.bowling.rules.AmericanTenPinScoring;

/**
 * Represents a single play in a frame
 * 
 * @author dinhego
 *
 */
public class Play implements AmericanTenPinScoring {

	private int firstAttempt;
	
	private int secondAttempt;

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
	}

	public int getFirstAttempt() {
		return firstAttempt;
	}

	public int getSecondAttempt() {
		return secondAttempt;
	}
	
	/**
	 * 
	 * @return the amount, validated, of all attempts
	 */
	public int getTotalScored() {
		return this.firstAttempt + this.secondAttempt;
	}
}
