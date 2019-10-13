package com.dius.tennis;

import java.util.ArrayList;
import java.util.List;

/**
 * Match class to run a simple Tennis Match Simulation
 * The Object Structure is not perfect. It can be REFACTOR'ed more and better.
 * 
 * @author rodeltalampas
 *
 */
public class Match {

	// Holds players and game score
	private List<Player> players = null;
	
	private boolean gameStarted = false;
	private boolean isDeuced = false;
	private boolean isDeuceBreaker = false;
	private Player advantagePlayer = null;
	private Player winGamePlayer = null;
	//Holds player's set scores
	private Set playersSet = null;
	
	/**
	 * Simple constructor to add two players in a match. 
	 * @param playerOne
	 * @param playerTwo
	 */
	public Match(Player playerOne, Player playerTwo) {
		getPlayers().add(playerOne);
		getPlayers().add(playerTwo);
		playersSet = new Set(playerOne, playerTwo);
	}
	
	private void inititializeGame() {
		gameStarted = true;
		advantagePlayer = null;
		winGamePlayer = null;
		isDeuceBreaker = false;
		isDeuced = false;
		Player playerOne = ((Player)getPlayers().get(0));
		Player playerTwo = ((Player)getPlayers().get(1));
		setScore(playerOne, 0);
		setScore(playerTwo, 0);
	}
	
	private void setScore(Player player, int score) {
		player.setScore(score);
	}
	
	private void setPoint(Player player, int score) {
		Player playerOne = ((Player)getPlayers().get(0));
		if (player.equals(playerOne))
			getPlayersSet().setPlayerOnePoints(score);
		else
			getPlayersSet().setPlayerTwoPoints(score);
	}
	
	public List<Player> getPlayers() {
		if (this.players == null) {
			this.players = new ArrayList<Player>();
		}
		return this.players;
	}
	
	public Set getPlayersSet() {
		return this.playersSet;
	}
	
	private int getScore(Player player) {
		return player.getScore();
	}
	
	private int getPoint(Player player) {
		Player playerOne = ((Player)getPlayers().get(0));
		if (player.equals(playerOne))
			return getPlayersSet().getPlayerOnePoints();
		else
			return getPlayersSet().getPlayerTwoPoints();
	}
	
	public String score() {
		Player playerOne = ((Player)getPlayers().get(0));
		Player playerTwo = ((Player)getPlayers().get(1));
		String initScore = String.format("%d-%d", playersSet.getPlayerOnePoints(), playersSet.getPlayerTwoPoints());
		if (!gameStarted)
			return initScore;
		else if (!isDeuced && !isDeuceBreaker && winGamePlayer==null) {
			return String.format("%s, %d-%d",initScore,
					getScore(playerOne),getScore(playerTwo));
		} else  if (isDeuced && !isDeuceBreaker){
			return String.format("%s, %s",initScore,
					"Deuce");
		} else if (winGamePlayer==null){
			return String.format("%s, Advantage %s",initScore,
					advantagePlayer.getName()); // advantage
		} else {
			return String.format("%s, Set %s",initScore,
					winGamePlayer.getName()); // winner set
		}
	}
	
	
	private void validateScore() {
		Player playerOne = ((Player)getPlayers().get(0));
		Player playerTwo = ((Player)getPlayers().get(1));
		int playerOneScore = getScore(playerOne);
		int playerTwoScore = getScore(playerTwo);
		
		int playerOnePoint = getPoint(playerOne);
		int playerTwoPoint = getPoint(playerTwo);
		
		isDeuced = false;
		playersSet.setTieBreak(false);
		
		validateSetWin(playerOne, playerTwo, playerOneScore, playerTwoScore, playerOnePoint, playerTwoPoint );
		validateDeuce(playerOne, playerTwo, playerOneScore, playerTwoScore); 

	}

	/**
	 * @param playerOne
	 * @param playerTwo
	 * @param playerOneScore
	 * @param playerTwoScore
	 */
	private void validateSetWin(Player playerOne, Player playerTwo, int playerOneScore, int playerTwoScore,
			int playerOnePoint, int playerTwoPoint) {
		if (playerOneScore >= 40 && playerTwoScore>=40) {
			if (playerOneScore - playerTwoScore >= 2)
				winGamePlayer = playerOne;
			else  if (playerTwoScore - playerOneScore >= 2)
				winGamePlayer = playerTwo;
		} else if (playerOneScore > 40 && playerTwoScore < 40) {
			winGamePlayer = playerOne;
		} else if (playerOneScore < 40 && playerTwoScore > 40) {
			winGamePlayer = playerTwo;
		}
		
		if (winGamePlayer!=null) {
			int points = 0;
			if (winGamePlayer.equals(playerOne)) 
				points=getPlayersSet().getPlayerOnePoints();
			else 
				points=getPlayersSet().getPlayerTwoPoints();
			setPoint(winGamePlayer,points+1);
			
		}
	}
	
	/**
	 * This resets the game and prepares for a new game
	 */
	public void gameSet() {
		// reset game
		gameStarted = false;
	}

	/**
	 * @param playerOne
	 * @param playerTwo
	 * @param playerOneScore
	 * @param playerTwoScore
	 */
	private void validateDeuce(Player playerOne, Player playerTwo, int playerOneScore, int playerTwoScore) {
		if (playerOneScore >= 40 && playerTwoScore>=40 && playerOneScore == playerTwoScore) {
			// if deuce, reset score to 40 ALL
			setScore(playerOne, 40);
			setScore(playerTwo, 40);
			isDeuced = true;
			isDeuceBreaker = false;
			advantagePlayer = null;
		} else if (playerOneScore >= 40 && playerTwoScore>=40 && playerOneScore != playerTwoScore) {
			if (playerOneScore>playerTwoScore)
				advantagePlayer = playerOne;
			else 
				advantagePlayer = playerTwo;
		}
	}
	
	public void pointWonBy(Player player) {
		if (!gameStarted) 
			inititializeGame();
		
		int score = getScore(player);
		if (!isDeuced && !isDeuceBreaker) {
			score = score==30?40:score+15;
		} else if (!playersSet.isTieBreak() && !playersSet.isTieBreakStarted()){
			isDeuceBreaker = true;
			score += 1; // just add a point (this is just telling player is an advantage)
		}
		player.setScore(score);
		validateScore();
		
	}
	
	public static void main(String[] args) {
		

	}

}
