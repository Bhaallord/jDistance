package jDistance.model.test;

import static org.junit.Assert.*;
import jDistance.model.TokenMove;
import jDistance.model.King;
import jDistance.model.Field;
import jDistance.model.Token;

import org.junit.Test;

public class TokenMoveTest {

	@Test
	public void testTokenMove() {
		TokenMove move=new TokenMove(new King(2,new Field(2,3)), new Field(3,4) );
		assertEquals(true,move.getToken().equals(new King(2,new Field(2,3))));
		assertEquals(true,move.getDestination().equals(new Field(3,4)));
	}

	@Test
	public void testGetToken() {
		//Felder zum abfragen
		Field origin = new Field(new Integer(1), new Integer(1));
		Field destination = new Field(new Integer(7), new Integer(7));
		//Figur welche bewegt wird
		King moveMe = new King(new Integer(1), origin);
		//Zug
		TokenMove move = new TokenMove(moveMe, destination);
		//Vergleich
		assertEquals(true, move.getToken().equals(moveMe));
	}

	@Test
	public void testGetDestination() {
		//Felder zum abfragen
		Field origin = new Field(new Integer(1), new Integer(1));
		Field destination = new Field(new Integer(7), new Integer(7));
		//Figur welche bewegt wird
		King moveMe = new King(new Integer(1), origin);
		//Zug
		TokenMove move = new TokenMove(moveMe, destination);
		//Vergleich
		assertEquals(true, move.getDestination().equals(destination));
	}

	@Test
	public void testEqualsObject() {
		//Felder zum abfragen
		Field origin = new Field(new Integer(1), new Integer(1));
		Field destination = new Field(new Integer(7), new Integer(7));
		//Figur welche bewegt wird
		King moveMe = new King(new Integer(1), origin);
		//Zug
		TokenMove move = new TokenMove(moveMe, destination);
		//Vergleich
		assertEquals(true, move.equals(new TokenMove(new King(new Integer(1),new Field(1,1)), new Field(7,7) )));
                
                TokenMove move2= new TokenMove(new Token(1,new Field(3,4)),new Field(2,4));
		assertEquals(false, move2.equals(new TokenMove(new Token(1,new Field(3,4)),new Field(1,4))));
		assertEquals(false, move2.equals(new TokenMove(new Token(1,new Field(2,4)),new Field(2,4))));
		assertEquals(false, move2.equals(null));
		assertEquals(false, move2.equals(new Object()));
	}
        
        @Test
	public void testClone() {
                TokenMove move1= new TokenMove(new Token(2,new Field(3,4)),new Field(2,4));
                TokenMove move2 = move1.clone();
		assertEquals(true, move2.equals(move2));
	}
        
         
        @Test
	public void testToString() {
                TokenMove move1= new TokenMove(new Token(2,new Field(3,4)),new Field(2,4));
		assertEquals(true, move1.toString().contains(move1.getToken().toString()));
		assertEquals(true, move1.toString().contains(move1.getDestination().toString()));
	}
               

}