package jDistance.model.test;

import static org.junit.Assert.*;
import jDistance.model.Field;
import jDistance.model.Token;

import org.junit.Test;

public class TokenTest {

	@Test
	public void testTokenIntField() {
		Token token1=new Token(1,new Field(2,3));
		assertEquals(1,token1.getPlayer());
		assertEquals(true, token1.getPos().equals(new Field(2,3)));
		assertEquals(false, token1.isLocked());
		
	}

	@Test
	public void testTokenToken() {
		Token token1=new Token(1,new Field(2,3));
		assertEquals(1,new Token(token1).getPlayer());
		assertEquals(true, new Token(token1).getPos().equals(new Field(2,3)));
		assertEquals(false, new Token(token1).isLocked());
		
		//Testen ob eine Harte Kopie gemacht wird
		Token token2=new Token(token1);
		token2.move(new Field(5,4));
		token2.setLocked(true);
		assertEquals(false, token1.getPos().equals(token2.getPos()));
		assertEquals(false,token1.isLocked()==token2.isLocked());
	}

	@Test
	public void testGetPos() {
		Token token1=new Token(1,new Field(7,1));
		assertEquals(true,token1.getPos().equals(new Field(7,1)));
	}

	@Test
	public void testGetPlayer() {
		Token token1=new Token(3,new Field(2,3));
		assertEquals(3,token1.getPlayer());
	}

	@Test
	public void testIsLooked() {
		Token token1=new Token(3,new Field(2,3));
		assertEquals(false,token1.isLocked());
	}

	@Test
	public void testSetLooked() {
		Token token1=new Token(3,new Field(2,3));
		assertEquals(false,token1.isLocked());
		token1.setLocked(true);
		assertEquals(true,token1.isLocked());
		token1.setLocked(false);
		assertEquals(false,token1.isLocked());
	}
        
        	@Test
	public void testMoveField() {
		Token token1=new Token(3,new Field(2,3));
		assertEquals(true,token1.getPos().equals(new Field(2,3)));
                token1.move(new Field(8,8));
		assertEquals(true,token1.getPos().equals(new Field(8,8)));
	}

	@Test
	public void testEqualsToken() {
		Token token1=new Token(3,new Field(2,3));
		Token token2=new Token(token1);
		assertEquals(true,token1.equals(token2));
		token2.setLocked(true);
		assertEquals(false,token1.equals(token2));
		token2.setLocked(false);
		assertEquals(true,token1.equals(token2));
		token2.move(new Field(4,1));
		assertEquals(false,token1.equals(token2));
		assertEquals(false,token1.equals(null));
		assertEquals(false,token1.equals(new Object()));
	}
        
        @Test
	public void testToString() {
		Token token1=new Token(1,new Field(2,3));
                assertEquals(true, token1.toString().toString().contains("1"));
                assertEquals(true, token1.toString().toString().contains("2"));
                assertEquals(true, token1.toString().toString().contains("3"));
	}
        
        @Test
	public void testClone() {
		Token token1=new Token(1,new Field(2,3));
                Token token2= token1.clone();
                assertEquals(true, token1.equals(token2));
                token2.setLocked(true);
                assertEquals(false, token1.equals(token2));
                
	}
        
        @Test
	public void testHashCode() {
		Token token1=new Token(1,new Field(2,3));
                assertEquals(63739412,token1.hashCode());
	}
        


}
