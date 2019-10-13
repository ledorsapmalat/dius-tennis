package com.dius.tennis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import com.dius.tennis.Match;
import com.dius.tennis.Player;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPlay {
	
	Match tennis = null;
	Player playerOne = null;
	Player playerTwo = null;
	
	/**
	 * Setup players and assert exists
	 */
	private void setupAssertPlayers() {
		playerOne = new Player();playerOne.setName("Rodel");
		playerTwo = new Player();playerTwo.setName("Dhey");
		tennis = new Match(playerOne, playerTwo);
		assertEquals("0-0", tennis.score());
		assertEquals(playerOne.getName(), ((Player)tennis.getPlayers().get(0)).getName());
		assertEquals(playerTwo.getName(), ((Player)tennis.getPlayers().get(1)).getName());
	}

	/**
	 * Simple points and assertion of a Deuce
	 */
	private void assertDeuce(String points) {
		tennis.pointWonBy(playerTwo);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerTwo);
		assertEquals(String.format("%s, 40-30",points), tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals(String.format("%s, Deuce",points), tennis.score());
	}
	
	/**
	 * New points and assertion of a Second Deuce
	 */
	private void assertSecondDeuce(String points) {
		tennis.pointWonBy(playerOne);
		assertEquals(String.format("%s, Advantage Rodel",points), tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals(String.format("%s, Deuce",points), tennis.score());
	}

	@Test
    public void matchPlay01_PlayerCheck() {
		setupAssertPlayers();
	}
	
	@Test
    public void matchPlay02_PlayerOneScore() {
		setupAssertPlayers();
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, 15-0", tennis.score());
	}
	
	@Test
    public void matchPlay03_PlayerTwoScore() {
		setupAssertPlayers();
		tennis.pointWonBy(playerTwo);
		assertNotEquals("0-0, 15-0", tennis.score());
	}
	
	@Test
    public void matchPlay04_BothPlayerScore() {
		setupAssertPlayers();
		tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, 15-15", tennis.score());
	}
	
	@Test
    public void matchPlay05_Duece() {
		setupAssertPlayers();
		assertDeuce("0-0");
	}
	
	@Test
    public void matchPlay06_PlayerOneAdvantage() {
		setupAssertPlayers();
		assertDeuce("0-0");
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, Advantage Rodel", tennis.score());
	}
	
	@Test
    public void matchPlay07_PlayerTwoSet() {
		setupAssertPlayers();
		tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerTwo);
		assertEquals("0-0, 0-30", tennis.score());
		tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, 15-40", tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals("0-1, Set Dhey", tennis.score());
	}
	
	@Test
    public void matchPlay08_PlayerOneDeuceWin() {
		setupAssertPlayers();
		assertDeuce("0-0");
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, Advantage Rodel", tennis.score());
		tennis.pointWonBy(playerOne);
		assertEquals("1-0, Set Rodel", tennis.score());
	}

	@Test
    public void matchPlay09_DoubleDeuce() {
		setupAssertPlayers();
		assertDeuce("0-0");
		assertSecondDeuce("0-0");
	}
	
	@Test
    public void matchPlay10_DoubleDeucePlayerTwoWin() {
		setupAssertPlayers();
		assertDeuce("0-0");
		assertSecondDeuce("0-0");
		tennis.pointWonBy(playerTwo);
		assertEquals("0-0, Advantage Dhey", tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals("0-1, Set Dhey", tennis.score());
	}
	
	@Test
    public void matchPlay11_DoubleDeucePlayerTwoWin() {
		setupAssertPlayers();
		assertDeuce("0-0");
		assertSecondDeuce("0-0");
		tennis.pointWonBy(playerTwo);
		assertEquals("0-0, Advantage Dhey", tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals("0-1, Set Dhey", tennis.score());
	}
	
	@Test
    public void matchPlay12_DoubleDeuceTiedOneOne() {
		setupAssertPlayers();
		assertDeuce("0-0");
		assertSecondDeuce("0-0");
		tennis.pointWonBy(playerTwo);
		assertEquals("0-0, Advantage Dhey", tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals("0-1, Set Dhey", tennis.score());
		tennis.gameSet();
		assertDeuce("0-1");
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		assertEquals("1-1, Set Rodel", tennis.score());
		
	}

}
