package com.dius.tennis;

import java.util.ArrayList;
import java.util.List;

/**
 * Match class to run a simple Tennis Match Simulation
 * The Object Structure is not perfect. It can be REFACTOR'ed more and better.
 * The attributes are setup in a rush.
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
	
	/**
	 * This resets the game and prepares for a new game
	 */
	public void gameSet() {
		// reset game
		gameStarted = false;
	}

	/**
	 * Initialize Game variables and Game Scores
	 */
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
	
	/**
	 * Set Player Score
	 * @param player
	 * @param score
	 */
	private void setScore(Player player, int score) {
		player.setScore(score);
	}
	
	/**
	 * Set Player Point
	 * @param player
	 * @param score
	 */
	private void setPoint(Player player, int score) {
		Player playerOne = ((Player)getPlayers().get(0));
		if (player.equals(playerOne))
			getPlayersSet().setPlayerOnePoints(score);
		else
			getPlayersSet().setPlayerTwoPoints(score);
	}
	
	/**
	 * Get a list of players
	 * @return
	 */
	public List<Player> getPlayers() {
		if (this.players == null) {
			this.players = new ArrayList<Player>();
		}
		return this.players;
	}
	
	/**
	 * Return the Tennis Set
	 */
	public Set getPlayersSet() {
		return this.playersSet;
	}
	
	/**
	 * Get Player Score
	 * @param player
	 * @return
	 */
	private int getScore(Player player) {
		return player.getScore();
	}
	
	/**
	 * Get Player Points
	 * @param player
	 * @return
	 */
	private int getPoint(Player player) {
		Player playerOne = ((Player)getPlayers().get(0));
		if (player.equals(playerOne))
			return getPlayersSet().getPlayerOnePoints();
		else
			return getPlayersSet().getPlayerTwoPoints();
	}
	
	/**
	 * Returns the current Points, Score and Game Status
	 * @return
	 */
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
		} else if (playersSet.getWinner()!=null)  {
			// there is a winner
			return String.format("%s, Winner %s", initScore, playersSet.getWinner().getName());
		} else {
			return String.format("%s",initScore); // winner set
		}
	}
	
	/**
	 * Validates the score
	 */
	private void validateScore() {
		Player playerOne = ((Player)getPlayers().get(0));
		Player playerTwo = ((Player)getPlayers().get(1));
		int playerOneScore = getScore(playerOne);
		int playerTwoScore = getScore(playerTwo);
		
		int playerOnePoint = getPoint(playerOne);
		int playerTwoPoint = getPoint(playerTwo);
		
		isDeuced = false;
		playersSet.setTieBreak(false);
		
		validateGameWinner(playerOne, playerTwo, playerOneScore, playerTwoScore, playerOnePoint, playerTwoPoint );
		validateDeuce(playerOne, playerTwo, playerOneScore, playerTwoScore); 
		validateSetWinner(playerOne, playerTwo);

	}

	/**
	 * @param playerOne
	 * @param playerTwo
	 */
	private void validateSetWinner(Player playerOne, Player playerTwo) {
		int playerOnePoint;
		int playerTwoPoint;
		// get current points
		playerOnePoint = getPoint(playerOne);
	 	playerTwoPoint = getPoint(playerTwo);
	 	if (playerOnePoint==playerTwoPoint && playerOnePoint == 6) {
	 		playersSet.setTieBreak(true);
	 	}else if (playerOnePoint>=7 || playerTwoPoint>=7){
	 		if (playerOnePoint-playerTwoPoint>=1)
	 			playersSet.setWinner(playerOne);
	 		else if (playerTwoPoint-playerOnePoint>=1)
	 			playersSet.setWinner(playerTwo);
	 	}
	}

	/**
	 * Checks if a player has won a game
	 * @param playerOne
	 * @param playerTwo
	 * @param playerOneScore
	 * @param playerTwoScore
	 */
	private void validateGameWinner(Player playerOne, Player playerTwo, int playerOneScore, int playerTwoScore,
			int playerOnePoint, int playerTwoPoint) {
		if (!playersSet.isTieBreakStarted()) {
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
		} else {
			if (playerOneScore==12 || playerTwoScore==12) {
				if (playerOneScore>playerTwoScore) {
					winGamePlayer = playerOne;
				}else if (playerTwoScore>playerOneScore) {
					winGamePlayer = playerTwo;
				}
			} else if (playerOneScore>=7 || playerTwoScore>=7) {
				if (playerOneScore-playerTwoScore>=2) {
					winGamePlayer = playerOne;
				}else if (playerTwoScore-playerOneScore>=2) {
					winGamePlayer = playerTwo;
				}
			}
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
	 * Checks whether the game is a Deuce
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
	
	/**
	 * Mark a Score for a Player
	 * @param player
	 */
	public void pointWonBy(Player player) {
		if (!gameStarted) 
			inititializeGame();
		
		int score = getScore(player);
		if (!isDeuced && !isDeuceBreaker && !playersSet.isTieBreak()) {
			score = score==30?40:score+15;
		} else if (!playersSet.isTieBreak()){
			isDeuceBreaker = true;
			score += 1; // just add a point (this is just telling player is an advantage)
		} else {
			playersSet.setTieBreakStarted(true); 
			score += 1; // scoring is really by 1's for tie breaks
		}
		player.setScore(score);
		validateScore();
		
	}

}
