package com.dius.tennis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import com.dius.tennis.Match;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPlay {
	
	Match tennis = null;

	@Test
    public void matchPlay01() {
		tennis = new Match("Rodel", "Dhey");
		assertEquals("0-0", tennis.score());
		assertEquals("Rodel", (String)tennis.getPlayers().keySet().toArray()[1]);
		assertEquals("Dhey", (String)tennis.getPlayers().keySet().toArray()[0]);
	}
	
	@Test
    public void matchPlay02() {
		tennis = new Match("Rodel", "Dhey");
		assertEquals("0-0", tennis.score());
		tennis.pointWonBy("Rodel");
		assertEquals("0-0, 15-0", tennis.score());
	}
	
	@Test
    public void matchPlay03() {
		tennis = new Match("Rodel", "Dhey");
		assertEquals("0-0", tennis.score());
		tennis.pointWonBy("Dhey");
		assertNotEquals("0-0, 15-0", tennis.score());
	}
	
	@Test
    public void matchPlay04() {
		tennis = new Match("Rodel", "Dhey");
		assertEquals("0-0", tennis.score());
		tennis.pointWonBy("Dhey");
		tennis.pointWonBy("Rodel");
		assertEquals("0-0, 15-15", tennis.score());
	}
	
	@Test
    public void matchPlay05_Duece() {
		tennis = new Match("Rodel", "Dhey");
		assertEquals("0-0", tennis.score());
		tennis.pointWonBy("Dhey");tennis.pointWonBy("Rodel");
		tennis.pointWonBy("Rodel");tennis.pointWonBy("Rodel");
		tennis.pointWonBy("Dhey");
		assertEquals("0-0, 40-30", tennis.score());
		tennis.pointWonBy("Dhey");
		assertEquals("0-0, Deuce", tennis.score());
	}
	
	@Test
    public void matchPlay06_PlayerOneAdvantage() {
		tennis = new Match("Rodel", "Dhey");
		assertEquals("0-0", tennis.score());
		tennis.pointWonBy("Dhey");tennis.pointWonBy("Rodel");
		tennis.pointWonBy("Rodel");tennis.pointWonBy("Rodel");
		tennis.pointWonBy("Dhey");
		assertEquals("0-0, 40-30", tennis.score());
		tennis.pointWonBy("Dhey");
		assertEquals("0-0, Deuce", tennis.score());
		tennis.pointWonBy("Rodel");
		assertEquals("0-0, Advantage Rodel", tennis.score());
	}
	
	@Test
    public void matchPlay07_PlayerTwoSet() {
		tennis = new Match("Rodel", "Dhey");
		assertEquals("0-0", tennis.score());
		tennis.pointWonBy("Dhey");
		tennis.pointWonBy("Dhey");
		assertEquals("0-0, 0-30", tennis.score());
		tennis.pointWonBy("Dhey");
		tennis.pointWonBy("Rodel");
		assertEquals("0-0, 15-40", tennis.score());
		tennis.pointWonBy("Dhey");
		assertEquals("0-0, Set Dhey", tennis.score());
	}
	
	@Test
    public void matchPlay08_PlayerOneDeuceWin() {
		tennis = new Match("Rodel", "Dhey");
		assertEquals("0-0", tennis.score());
		tennis.pointWonBy("Dhey");tennis.pointWonBy("Rodel");
		tennis.pointWonBy("Rodel");tennis.pointWonBy("Rodel");
		tennis.pointWonBy("Dhey");
		assertEquals("0-0, 40-30", tennis.score());
		tennis.pointWonBy("Dhey");
		assertEquals("0-0, Deuce", tennis.score());
		tennis.pointWonBy("Rodel");
		assertEquals("0-0, Advantage Rodel", tennis.score());
		tennis.pointWonBy("Rodel");
		assertEquals("0-0, Set Rodel", tennis.score());
	}
}
