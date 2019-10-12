package com.dius.tennis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Match class to run a simple Tennis Match Simulation
 * 
 * @author rodeltalampas
 *
 */
public class Match {

	// Holds players and game score
	private Map<String, Integer> players = null;
	
	private boolean gameStarted = false;
	private boolean isDeuced = false;
	private boolean isDeuceBreaker = false;
	private boolean tieBreakStarted = false;
	//Holds player's set scores
	private Map<String, Integer> playersSet = null;
	//Holds player's game scores
	private List<Map<String,Integer>> playersGame = null;
	
	/**
	 * Simple constructor to add two players in a match
	 * @param playerOne
	 * @param playerTwo
	 */
	public Match(String playerOne, String playerTwo) {
		setScore(playerOne, 0);
		setScore(playerTwo, 0);
	}
	
	private void setScore(String player, int score) {
		getPlayers().put(player, score);
	}
	
	public Map<String, Integer> getPlayers() {
		if (this.players == null) {
			this.players = new HashMap<String, Integer>();
		}
		return this.players;
	}
	
	public Map<String, Integer> getPlayersSet() {
		if (this.playersSet == null) {
			this.playersSet = new HashMap<String, Integer>();
		}
		return this.playersSet;
	}
	
	public String score() {
		String playerOne = (String)getPlayers().keySet().toArray()[1];
		String playerTwo = (String)getPlayers().keySet().toArray()[0];
		String initScore = "0-0";
		if (!gameStarted)
			return initScore;
		else if (!isDeuced && !isDeuceBreaker) {
			return String.format("%s, %d-%d",initScore,
					players.get(playerOne),players.get(playerTwo));
		} else  if (isDeuced && !isDeuceBreaker){
			return String.format("%s, %s",initScore,
					"Deuce");
		} else {
			return ""; // advantage
		}
	}
	
	private int getScore(String player) {
		return ((Integer)getPlayers().get(player)).intValue();
	}
	
	private boolean isDeuced() {
		String playerOne = (String)getPlayers().keySet().toArray()[1];
		String playerTwo = (String)getPlayers().keySet().toArray()[0];
		int playerOneScore = getScore(playerOne);
		int playerTwoScore = getScore(playerTwo);
		
		if (playerOneScore >= 40 && playerTwoScore>=40 && playerOneScore == playerTwoScore) {
			// if deuce, reset score to 40 ALL
			setScore(playerOne, 40);
			setScore(playerTwo, 40);
			return true;
		}
		return false;
	}
	
	public void pointWonBy(String player) {
		if (!gameStarted)
			gameStarted = true;
		
		int points = getPlayers().get(player).intValue();
		if (!isDeuced && !isDeuceBreaker) {
			points = points==30?40:points+15;
		} else {
			isDeuceBreaker = true;
			points += 1; // just add a point (this is just telling player is an advantage)
		}
		players.put(player, points);
		isDeuced = isDeuced();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
