# dius-tennis
A Tennis Simulation Program for Dius

## Test Class
There is only 1 Test Class (`TestPlay.java`) created that caters for most of the relevant scenarios. It is assumed that scores are already validated hence, there are no validations required.

```
public class TestPlay {
...
...
}
```

## Main Class
The main class is called `Match.java`. It is an a simulation of a Tennis Set Play. To score, one must implicitly tell a `pointsWonBy('Player')`. If a game has been won by a player, one must tell the game to be reset `gameReset()`. To view the Game Score and Status, one must call `score()`.

```
Player playerOne = new Player();
playerOne.setName("Rodel");
Player playerTwo = new Player();
playerTwo.setName("Dhey");

Match tennis = new Match(playerOne, playerTwo);
tennis.pointWonBy(playerTwo);
tennis.pointWonBy(playerOne); 
tennis.score(); // will return '0-0, 1-1'
...
...
tennis.gameReset(); // will reset the game and score to 0

```