/**
 * 
 */
package ca.issgc.bowling.rules;

/**
 * Basic rules to play bowling
 * 
 * @author dinhego
 *
 */
public interface Basics {

    int DEFAULT_NUMBER_PINS = 10;

    int DEFAULT_AMOUNT_FRAMES = 10;

    int ATTEMPTS_PER_NORMAL_FRAME = 2;

    int ATTEMPTS_PER_FINAL_FRAME = 3;

    int POINTS_PER_PIN_KNOCKED_DOWN = 1;

    int MAX_POINTS_EARNED_PER_PLAY = 30;

}
