/**
 * 
 */
package br.issgc.bowling;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

	/**
	 * knocking down more than 10 pins, expecting a exception to be throwed
	 */
	@Test
	void invalidPlay() {

		// TODO these messages should be in a messages file

		// 11
		IllegalArgumentException exceptionThrowed = assertThrows(IllegalArgumentException.class, () -> {
			new Play(10, 1);
		});
		assertTrue("Invalid amount of pins knocked down: 11. Max pins is 10.".equals(exceptionThrowed.getMessage()),
				"Error! Message received: " + exceptionThrowed.getMessage());
		
		// 1002
		exceptionThrowed = assertThrows(IllegalArgumentException.class, () -> {
			new Play(0, 1002);
		});
		assertTrue("Invalid amount of pins knocked down: 1002. Max pins is 10.".equals(exceptionThrowed.getMessage()),
				"Error! Message received: " + exceptionThrowed.getMessage());
		
		// negative
		exceptionThrowed = assertThrows(IllegalArgumentException.class, () -> {
			new Play(-1, 0);
		});
		assertTrue("Invalid amount of pins knocked down: -1. Max pins is 10.".equals(exceptionThrowed.getMessage()),
				"Error! Message received: " + exceptionThrowed.getMessage());
	}

}
