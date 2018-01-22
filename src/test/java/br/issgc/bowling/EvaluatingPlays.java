/**
 * 
 */
package br.issgc.bowling;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ca.issgc.bowling.frame.BonusWon;
import ca.issgc.bowling.frame.Play;
import ca.issgc.bowling.rules.AmericanTenPinScoringSystem;

/**
 * Evaluating plays for {@link BonusWon} types and the calculation of the score
 * 
 * @author dinhego
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
class EvaluatingPlays {

    // basic configurations for the test

    private Play lastPlayWasAStrike;

    private Play lastPlayWasASpare;

    @BeforeAll
    void setup() {

	lastPlayWasAStrike = new Play(10, 0);
	assertTrue(BonusWon.STRIKE.equals(lastPlayWasAStrike.getBonus()));

	lastPlayWasASpare = new Play(6, 4);
	assertTrue(BonusWon.SPARE.equals(lastPlayWasASpare.getBonus()));
    }

    // checking bonus

    @Test
    void playWithoutBonus() {
	assertEquals(BonusWon.NO_BONUS, BonusWon.evaluateAPlay(new Play(0, 2)));
	assertEquals(BonusWon.NO_BONUS, BonusWon.evaluateAPlay(new Play(1, 8)));
    }

    @Test
    void playWithAStrike() {
	assertEquals(BonusWon.STRIKE, BonusWon.evaluateAPlay(new Play(10, 0)));
    }

    @Test
    void playWithASpare() {
	assertEquals(BonusWon.SPARE, BonusWon.evaluateAPlay(new Play(1, 9)));
	assertEquals(BonusWon.SPARE, BonusWon.evaluateAPlay(new Play(0, 10)));
	assertEquals(BonusWon.SPARE, BonusWon.evaluateAPlay(new Play(2, 8)));
    }

    // check the values of bonuses to be applied to a current play
    @Test
    void noBonusAtCurrentPlay() {
	// three pins knocked down
	Play lastPlay = new Play(0, 3);

	// the current play is a strike, but without a bonus
	Play current = new Play(10, 0);

	assertTrue(10 == new AmericanTenPinScoringSystem() {
	}.calculatePlayPointsAndBonus(lastPlay, current));
    }

    /**
     * this test is based on the strike from the example of this
     * <a href="http://www.topendsports.com/sport/tenpin/scoring.htm">link</a>.
     */
    @Test
    void playAfterAStrikeFromTheDoc() {

	Play currentPlay = new Play(7, 1);

	assertTrue(16 == new AmericanTenPinScoringSystem() {
	}.calculatePlayPointsAndBonus(lastPlayWasAStrike, currentPlay));
    }

    /**
     * this test is based on the spare from the example of this
     * <a href="http://www.topendsports.com/sport/tenpin/scoring.htm">link</a>.
     */
    @Test
    void playAfterASpareFromTheDoc() {

	Play currentPlay = new Play(8, 1);

	assertTrue(17 == new AmericanTenPinScoringSystem() {
	}.calculatePlayPointsAndBonus(lastPlayWasASpare, currentPlay));
    }

}
