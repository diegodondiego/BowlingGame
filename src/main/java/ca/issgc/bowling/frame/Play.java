package ca.issgc.bowling.frame;

/**
 * Represents a simple play and their mandatory methods
 * 
 * @author dinhego
 *
 */
public interface Play {

    int getFirstAttempt();

    int getSecondAttempt();

    BonusWon getBonus();

    /**
     * 
     * @return the amount, validated, of pins knocked down in the two attempts
     */
    int getPinsKnockedDown();

    boolean isPendingPointsToEvaluate();

    void flagPendingPointsToEvaluate();

    void setCurrentPointsPerPlay(int currentPoints);

    int getCurrentPointsEarned();

}