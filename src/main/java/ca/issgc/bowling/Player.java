/**
 * 
 */
package ca.issgc.bowling;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ca.issgc.bowling.frame.NormalPlay;
import ca.issgc.bowling.rules.AmericanTenPinScoringSystem;

/**
 * This class represents a single player on a game and his/her results per frame
 * 
 * @author dinhego
 *
 */
public class Player implements AmericanTenPinScoringSystem {

    /**
     * non-empty and non-blank
     */
    private final String name;

    /**
     * the frame is identified by a number
     */
    private final Map<Integer, NormalPlay> playByFrame;

    /**
     * 
     */
    private int currentScore;

    /**
     * Constructor!
     * 
     * @param playerName
     */
    public Player(String playerName) {

	if (StringUtils.isEmpty(StringUtils.trimToEmpty(playerName))) {
	    throw new IllegalArgumentException("Invalid player name!");
	}

	this.name = playerName;
	this.playByFrame = new HashMap<>();
    }

    // modifiers

    /**
     * add a new play for a given frame
     * 
     * @param frameId
     *            the unique identifier of a frame
     * @param play
     */
    public void addPlay(Integer frameId, NormalPlay play) {

	// TODO improve this validation
	if (frameId == null || frameId < 1 || frameId > DEFAULT_FRAMES || play == null) {
	    throw new IllegalArgumentException("Invalid parameters!");
	}

	if (this.playByFrame.containsKey(frameId)) {
	    throw new IllegalArgumentException(
		    String.format("Frame with id [%s] already played: %s.", frameId, play.toString()));
	}

	this.playByFrame.put(frameId, play);

	// update players score
	this.currentScore += calculateOrUpdateAPlayPointsAndBonus(this.playByFrame.get(frameId - 1), play, null);
    }

    // getters

    public String getName() {
	return this.name;
    }

    public Map<Integer, NormalPlay> getPlays() {
	return this.playByFrame;
    }

    public int getCurrentScore() {
	return this.currentScore;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	// unique identifier
	return this.name.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Player)) {
	    return false;
	}
	Player other = (Player) obj;

	return other.hashCode() == this.hashCode();
    }

}
