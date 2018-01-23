/**
 * 
 */
package br.issgc.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.issgc.bowling.Player;
import ca.issgc.bowling.frame.NormalPlay;

/**
 * Starting the tests with the {@link Player}
 * 
 * @author dinhego
 *
 */
class AddPlaysToPlayer {

    private Player player;

    @BeforeEach
    void setUp() {

	player = new Player("teste");
    }

    /**
     * knocking down 3 pins
     */
    @Test
    void withoutBonus() {
	player.addPlay(1, new NormalPlay(0, 3));
	assertEquals(3, player.getCurrentScore());

	player.addPlay(2, new NormalPlay(4, 5));
	assertEquals(12, player.getCurrentScore());
    }

    // all the test above was taken from
    // https://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring

    @Test
    void firstStrikeAndThenANoBonusPlay() {
	// strike
	player.addPlay(1, new NormalPlay(10, 0));
	// 9 pins, no bonus
	player.addPlay(2, new NormalPlay(3, 6));

	assertEquals(28, player.getCurrentScore());
    }

    /**
     * two consecutive strikes is a rhino
     */
    @Test
    void aRhino() {

	// strike
	player.addPlay(1, new NormalPlay(10, 0));
	// strike again
	player.addPlay(2, new NormalPlay(10, 0));
	// 9 pins and a dash
	player.addPlay(3, new NormalPlay(9, 0));

	assertEquals(57, player.getCurrentScore());
    }

    /**
     * three consecutive strikes is a turkey
     */
    @Test
    void aTurkey() {

	// strike
	player.addPlay(1, new NormalPlay(10, 0));
	// strike again
	player.addPlay(2, new NormalPlay(10, 0));
	// the third strike
	player.addPlay(3, new NormalPlay(10, 0));
	// a spare
	player.addPlay(4, new NormalPlay(8, 2));
	// 8 pins and a dash
	player.addPlay(5, new NormalPlay(8, 0));

	assertEquals(104, player.getCurrentScore());

    }

    /**
     * five consecutive strikes is a goose
     */
    @Test
    void aChicken() {

	// strike
	player.addPlay(1, new NormalPlay(10, 0));
	// strike again
	player.addPlay(2, new NormalPlay(10, 0));
	// the rhino
	player.addPlay(3, new NormalPlay(10, 0));
	// the goose
	player.addPlay(4, new NormalPlay(10, 0));
	// the chicken
	player.addPlay(5, new NormalPlay(10, 0));
	// 7 and a 2
	player.addPlay(6, new NormalPlay(7, 2));

	assertEquals(145, player.getCurrentScore());
    }

}
