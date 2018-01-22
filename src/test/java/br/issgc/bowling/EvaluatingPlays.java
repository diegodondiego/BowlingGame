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
import ca.issgc.bowling.frame.NormalPlay;
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

	lastPlayWasAStrike = new NormalPlay(10, 0);
	assertTrue(BonusWon.STRIKE.equals(lastPlayWasAStrike.getBonus()));

	lastPlayWasASpare = new NormalPlay(6, 4);
	assertTrue(BonusWon.SPARE.equals(lastPlayWasASpare.getBonus()));
    }

    // checking bonus

    @Test
    void playWithoutBonus() {
	assertEquals(BonusWon.NO_BONUS, BonusWon.evaluateAPlay(new NormalPlay(0, 2)));
	assertEquals(BonusWon.NO_BONUS, BonusWon.evaluateAPlay(new NormalPlay(1, 8)));
    }

    @Test
    void playWithAStrike() {
	assertEquals(BonusWon.STRIKE, BonusWon.evaluateAPlay(new NormalPlay(10, 0)));
    }

    @Test
    void playWithASpare() {
	assertEquals(BonusWon.SPARE, BonusWon.evaluateAPlay(new NormalPlay(1, 9)));
	assertEquals(BonusWon.SPARE, BonusWon.evaluateAPlay(new NormalPlay(0, 10)));
	assertEquals(BonusWon.SPARE, BonusWon.evaluateAPlay(new NormalPlay(2, 8)));
    }

    // check the values of bonuses to be applied to a current play
    @Test
    void noBonusAtCurrentPlay() {
	// three pins knocked down
	Play evaluatedPlay = new NormalPlay(0, 3);

	// the current play is a strike, but without a bonus
	Play nextPlay = new NormalPlay(10, 0);

	assertTrue(3 == new AmericanTenPinScoringSystem() {
	}.calculateOrUpdateAPlayPointsAndBonus(evaluatedPlay, nextPlay, null));
    }

    /**
     * this test is based on the strike from the example of this
     * <a href="http://www.topendsports.com/sport/tenpin/scoring.htm">link</a>.
     */
    @Test
    void playAfterAStrikeFromTheDoc() {

	Play currentPlay = new NormalPlay(7, 1);

	assertTrue(18 == new AmericanTenPinScoringSystem() {
	}.calculateOrUpdateAPlayPointsAndBonus(lastPlayWasAStrike, currentPlay, null));
    }

    /**
     * this test is based on the spare from the example of this
     * <a href="http://www.topendsports.com/sport/tenpin/scoring.htm">link</a>.
     */
    @Test
    void playAfterASpareFromTheDoc() {

	Play currentPlay = new NormalPlay(8, 1);

	assertTrue(18 == new AmericanTenPinScoringSystem() {
	}.calculateOrUpdateAPlayPointsAndBonus(lastPlayWasASpare, currentPlay, null));
    }

}
