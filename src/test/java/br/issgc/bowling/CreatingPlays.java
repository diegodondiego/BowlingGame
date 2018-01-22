/**
 * 
 */
package br.issgc.bowling;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ca.issgc.bowling.frame.Play;

/**
 * First brainstorm about the game itself
 * 
 * @author dinhego
 *
 */
public class CreatingPlays {

	/**
	 * just a first simple play
	 */
	@Test
	void simplePlay() {

		Play simplePlay = new Play(3, 7);

		assertTrue(simplePlay.getTotalScored() == 10);

	}

}
