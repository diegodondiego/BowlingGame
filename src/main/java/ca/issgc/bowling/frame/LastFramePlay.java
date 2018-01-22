/**
 * 
 */
package ca.issgc.bowling.frame;

import ca.issgc.bowling.rules.AmericanTenPinScoringSystem;

/**
 * @author dinhego
 *
 */
public class LastFramePlay implements AmericanTenPinScoringSystem, Play {


    /*
     * (non-Javadoc)
     * 
     * @see ca.issgc.bowling.frame.Play#getFirstAttempt()
     */
    @Override
    public int getFirstAttempt() {
	// TODO Auto-generated method stub
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ca.issgc.bowling.frame.Play#getSecondAttempt()
     */
    @Override
    public int getSecondAttempt() {
	// TODO Auto-generated method stub
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ca.issgc.bowling.frame.Play#getBonus()
     */
    @Override
    public BonusWon getBonus() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ca.issgc.bowling.frame.Play#getPinsKnockedDown()
     */
    @Override
    public int getPinsKnockedDown() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public boolean isPendingPointsToEvaluate() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void flagPendingPointsToEvaluate() {
	// TODO Auto-generated method stub
	
    }

}
