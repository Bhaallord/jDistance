package jDistance.ai.test;

import static org.junit.Assert.*;
import jDistance.ai.AiMove;
import jDistance.model.Field;
import jDistance.model.Token;
import jDistance.model.TokenMove;

import org.junit.Before;
import org.junit.Test;

public class AiMoveTest {

	private AiMove testMe = null; 
	private Token token = null;
	private TokenMove tokenMove = null;
	private Field destination = null;
	private int score = Integer.valueOf(50);
	
	
	@Before
	public void setUp() {
		//Zug mit einem Token auf [0,0] testen
		this.token = new Token(Integer.valueOf(1), new Field(Integer.valueOf(0), Integer.valueOf(0)));
		//der Zug geht nach [7,7]
		this.destination = new Field(Integer.valueOf(7), Integer.valueOf(7));
		//TokenMove für den toString Test
		this.tokenMove = new TokenMove(this.token, this.destination);
		//Zug mit einer Wertigkeit von 50, wird beim secondConstructorTest() überschrieben
		this.testMe = new AiMove(this.score, this.token, this.destination);
	}

	
	@Test
	public void firstConstructorTest() {
		//Zug vergleichen
		assertEquals(this.testMe.getDestination(), this.destination);
		assertEquals(this.testMe.getScore(), this.score);
		assertEquals(this.testMe.getToken(), this.token);
	}
	
	
	@Test
	public void secondConstructorTest() {
		//Zug mit einer Wertigkeit von 50
		this.testMe = new AiMove(this.score, this.tokenMove);
		//Zug vergleichen
		assertEquals(this.testMe.getDestination(), this.destination);
		assertEquals(this.testMe.getScore(), this.score);
		assertEquals(this.testMe.getToken(), this.token);
	}

	
	@Test
	public void getScoreTest(){
		assertEquals(this.testMe.getScore(), this.score);		
	}
	
	
	@Test
	public void setScoreTest(){
		//Prüfen auf gleichheit
		assertEquals(this.testMe.getScore(), this.score);
		//setzen des neuen scores
		int score = (int)(Math.random()*100);
		this.testMe.setScore(score);
		//...ungleich...
		assertNotSame(this.testMe.getScore(), this.score);
		//...gleich!
		assertEquals(this.testMe.getScore(), score);
	}
	
	
	@Test
	public void toStringTest(){
		//String Repräsentation erstellen
		String expected = String.valueOf("<AiMove>{ score:" + this.testMe.getScore() + " super:" + this.tokenMove.toString() + " } ");
		assertEquals(expected, this.testMe.toString());
	}
}