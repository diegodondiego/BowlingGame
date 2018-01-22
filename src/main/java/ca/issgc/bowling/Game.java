/**
 * 
 */
package ca.issgc.bowling;

import java.util.Set;

import ca.issgc.bowling.rules.Basics;

/**
 * A game of bowling is composed by a number of players, 10 frames and final
 * score
 * 
 * @author dinhego
 *
 */
public class Game implements Basics {

    private final Set<Player> players;

    /**
     * Constructor!
     * 
     * @param players
     */
    public Game(Set<Player> players) {

	if (players == null || players.isEmpty()) {
	    throw new IllegalArgumentException("No players provided!");
	}

	this.players = players;
    }

    // getters

    public Set<Player> getPlayers() {
	return this.players;
    }

}
