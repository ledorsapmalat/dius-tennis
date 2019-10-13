/**
 * 
 */
package com.dius.tennis;

/**
 * @author rodeltalampas
 *
 */
public class Set {
	
	Player playerOne;
	Player playerTwo;
	Player winner;
	int playerOnePoints;
	int playerTwoPoints;
	boolean tieBreak;
	boolean tieBreakStarted;
	
	public Set(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	/**
	 * @return the playerOne
	 */
	public Player getPlayerOne() {
		return playerOne;
	}
	/**
	 * @param playerOne the playerOne to set
	 */
	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}
	/**
	 * @return the playerTwo
	 */
	public Player getPlayerTwo() {
		return playerTwo;
	}
	/**
	 * @param playerTwo the playerTwo to set
	 */
	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}
	/**
	 * @return the playerOnePoints
	 */
	public int getPlayerOnePoints() {
		return playerOnePoints;
	}
	/**
	 * @param playerOnePoints the playerOnePoints to set
	 */
	public void setPlayerOnePoints(int playerOnePoints) {
		this.playerOnePoints = playerOnePoints;
	}
	/**
	 * @return the playerTwoPoints
	 */
	public int getPlayerTwoPoints() {
		return playerTwoPoints;
	}
	/**
	 * @param playerTwoPoints the playerTwoPoints to set
	 */
	public void setPlayerTwoPoints(int playerTwoPoints) {
		this.playerTwoPoints = playerTwoPoints;
	}
	/**
	 * @return the tieBreak
	 */
	public boolean isTieBreak() {
		return tieBreak;
	}
	/**
	 * @param tieBreak the tieBreak to set
	 */
	public void setTieBreak(boolean tieBreak) {
		this.tieBreak = tieBreak;
	}
	/**
	 * @return the tieBreakStarted
	 */
	public boolean isTieBreakStarted() {
		return tieBreakStarted;
	}
	/**
	 * @param tieBreakStarted the tieBreakStarted to set
	 */
	public void setTieBreakStarted(boolean tieBreakStarted) {
		this.tieBreakStarted = tieBreakStarted;
	}
	
	
	/**
	 * @return the winner
	 */
	public Player getWinner() {
		return winner;
	}
	/**
	 * @param winner the winner to set
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	@Override
	public String toString() {
		return String.format("Object Set Score: %d - %d", this.playerOnePoints, this.playerTwoPoints);
	}

}
