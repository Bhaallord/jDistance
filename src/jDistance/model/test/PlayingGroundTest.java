package jDistance.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jDistance.model.Field;
import jDistance.model.PlayingGround;
import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.model.Distance;
import jDistance.model.King;
import jDistance.model.Slave;
import jDistance.util.*;
import java.util.*;



import org.junit.Test;


public class PlayingGroundTest {

	@Test
	public void testPlayingGround() {
		PlayingGround playingGround = new PlayingGround();
		assertEquals(8, playingGround.getTokens().size());
	}

	@Test
	public void testGetTokens() {
		PlayingGround playingGround = new PlayingGround();
		assertEquals(8, playingGround.getTokens().size());
	}

	@Test
	public void testGetTokensInt() {
		PlayingGround playingGround = new PlayingGround();
		assertEquals(4, playingGround.getTokens(1).size());
	}

	@Test
	public void testGetPossibleMoveTokenListOfToken() {
		Token token1 = new Token(1, new Field(4, 4));
		java.util.List<Token> other = new ArrayList<Token>();
		other.add(new Token(1, new Field(5, 1)));
		other.add(new Token(1, new Field(2, 0)));
		java.util.List<TokenMove> expectedMove1 = new ArrayList<TokenMove>();
		expectedMove1.add(new TokenMove(new Token(token1), new Field(3, 1)));
		expectedMove1.add(new TokenMove(new Token(token1), new Field(5, 1)));
		expectedMove1.add(new TokenMove(new Token(token1), new Field(3, 7)));
		expectedMove1.add(new TokenMove(new Token(token1), new Field(5, 7)));
		expectedMove1.add(new TokenMove(new Token(token1), new Field(7, 3)));
		expectedMove1.add(new TokenMove(new Token(token1), new Field(7, 5)));
		expectedMove1.add(new TokenMove(new Token(token1), new Field(1, 5)));
		expectedMove1.add(new TokenMove(new Token(token1), new Field(1, 3)));

		java.util.List<TokenMove> actual = PlayingGround.getPossibleMove(
				token1, other);
		assertEquals(expectedMove1.size(), actual.size());
		assertEquals(true, actual.containsAll(expectedMove1));

	}

	@Test
	public void testGetPossibleMove() {
		PlayingGround playingGround = new PlayingGround();
		assertEquals(playingGround.getPossibleMove().size(), playingGround
				.getPossibleMove(2).size()
				+ playingGround.getPossibleMove(1).size());
	}

	@Test
	public void testGetPossibleMoveInt() {
		PlayingGround playingGround = new PlayingGround();
		assertEquals(playingGround.getPossibleMove(1).size(),new PlayingGround().getPossibleMove(2).size());
		assertEquals(playingGround.getPossibleMove().size()/2,new PlayingGround().getPossibleMove(2).size());
	}

	@Test
	public void testIsFieldPosible() {
		assertEquals(true, PlayingGround.isFieldPosible(new Field(7, 0)));
		assertEquals(true, PlayingGround.isFieldPosible(new Field(0, 7)));
		assertEquals(false, PlayingGround.isFieldPosible(new Field(-1, 0)));
		assertEquals(false, PlayingGround.isFieldPosible(new Field(0, 8)));
		assertEquals(false, PlayingGround.isFieldPosible(new Field(7, -1)));
		assertEquals(false, PlayingGround.isFieldPosible(new Field(8, 0)));
	}

	@Test
	public void testGetDistance() {
		Token token1 = new Token(1, new Field(4, 4));
		java.util.List<Token> other = new ArrayList<Token>();
		other.add(new Token(1, new Field(5, 1)));
		other.add(new Token(1, new Field(2, 0)));
		java.util.List<Distance> expectedDistance = new ArrayList<Distance>();
		expectedDistance.add(new Distance(3,1));
		
		//Erster Test mit zwei Tokens
		java.util.List<Distance> actual = PlayingGround.getDistance(token1, other);
		assertEquals(expectedDistance.size(), actual.size());
		assertEquals(true, actual.containsAll(expectedDistance));
		
		//Zweiter Test mit drei Tokens
		other.add(new Token(1, new Field(3, 7)));
		expectedDistance.add(new Distance(2,6));
		expectedDistance.add(new Distance(1,7));
		actual = PlayingGround.getDistance(token1, other);
		assertEquals(expectedDistance.size(), actual.size());
		assertEquals(true, actual.containsAll(expectedDistance));
		
		//Zweiter Test mit vier Tokens
		other.add(new Token(1, new Field(3, 1)));
		expectedDistance.add(new Distance(0,6));
		expectedDistance.add(new Distance(1,1));
		expectedDistance.add(new Distance(2,0));
		actual = PlayingGround.getDistance(token1, other);
		assertEquals(expectedDistance.size(), actual.size());
		assertEquals(true, actual.containsAll(expectedDistance));
		
		
	}

	@Test
	public void testGetTokenOnFieldField() {
		PlayingGround playground=new PlayingGround();
		assertEquals(null, playground.getTokenOnField(new Field(5,5)));
		assertEquals(true, playground.getTokenOnField(new Field(5,7)).equals(new King(2, new Field(5, 7))));
		playground.moveToken(new King(2, new Field(5, 7)),new Field(5,6));
		assertEquals(true, playground.getTokenOnField(new Field(5,6)).equals(new King(2, new Field(5, 6))));
	}

	@Test
	public void testGetTokenOnFieldFieldListOfToken() {
		java.util.List<Token> other = new ArrayList<Token>();
		other.add(new Token(1, new Field(5, 1)));
		other.add(new Token(1, new Field(2, 0)));
		assertEquals(null, PlayingGround.getTokenOnField(new Field(3,5),other));
		assertEquals(true, PlayingGround.getTokenOnField(new Field(5,1),other).equals(new Token(1, new Field(5, 1))));
	}

	@Test
	public void testMoveTokenTokenField() {
	}

	@Test
	public void testMoveTokenTokenMove() {
		
		
		//Test1 
		java.util.List<Token> tokens=new ArrayList<Token>();
		tokens.add(new Slave(1,new Field(4,1)));
		tokens.add(new Slave(1,new Field(5,3)));
		tokens.add(new King(1,new Field(3,3)));
		tokens.add(new King(2,new Field(2,5)));
		PlayingGround playingGround1=new PlayingGround(tokens);
		List<Event> result=playingGround1.moveToken(new King(1,new Field(3,3)), new Field(2,5));
		assertEquals(3,result.size());
		assertEquals(true,result.contains(new WinEvent(1)));
		assertEquals(true,result.contains(new TokenSwapedEvent(new TokenMove(new King(1,new Field(3,3)),new Field(2,5)), new TokenMove(new King(2,new Field(2,5)),new Field(3,3)))));
		assertEquals(true,result.contains(new TokenLockedEvent(new King(2,new Field(3,3)))));
		
		//Test2
		java.util.List<Token> tokens2=new ArrayList<Token>();
		tokens2.add(new Slave(1,new Field(4,1)));
		tokens2.add(new Slave(1,new Field(5,3)));
		tokens2.add(new King(1,new Field(3,3)));
		tokens2.add(new King(2,new Field(2,5)));
		PlayingGround playingGround2=new PlayingGround(tokens2);
		List<Event> result2=playingGround2.moveToken(new King(1,new Field(3,3)), new Field(4,1));
		assertEquals(true,result2.contains(new TokenSwapedEvent(new TokenMove(new King(1,new Field(3,3)),new Field(4,1)), new TokenMove(new Slave(1,new Field(4,1)),new Field(3,3)))));
		assertEquals(1,result2.size());
		
		//Test3
		java.util.List<Token> tokens3=new ArrayList<Token>();
		tokens3.add(new Slave(1,new Field(4,1)));
		tokens3.add(new Slave(1,new Field(5,3)));
		tokens3.add(new King(1,new Field(3,3)));
		tokens3.add(new King(2,new Field(2,5)));
		tokens3.add(new Slave(2,new Field(1,2)));
		PlayingGround playingGround3=new PlayingGround(tokens3);
		List<Event> result3=playingGround3.moveToken(new King(1,new Field(3,3)), new Field(1,2));
		assertEquals(true,result3.contains(new TokenSwapedEvent(new TokenMove(new King(1,new Field(3,3)),new Field(1,2)), new TokenMove(new Slave(2,new Field(1,2)),new Field(3,3)))));
		assertEquals(true,result3.contains(new TokenLockedEvent(new Slave(2,new Field(3,3)))));
		assertEquals(2,result3.size());
		
		
		//Test4
		java.util.List<Token> tokens4=new ArrayList<Token>();
		tokens4.add(new Slave(1,new Field(4,1)));
		tokens4.add(new Slave(1,new Field(5,3)));
		tokens4.add(new King(1,new Field(3,3)));
		tokens4.add(new King(2,new Field(2,5)));
		tokens4.add(new Slave(2,new Field(1,2)));
		tokens4.add(new Slave(2,new Field(2,1)));
		PlayingGround playingGround4=new PlayingGround(tokens4);
		List<Event> result4=playingGround4.moveToken(new King(2,new Field(2,5)), new Field(1,6));
		assertEquals(true,result4.contains(new TokenMovedEvent(new King(2,new Field(2,5)), new Field(1,6))));
		assertEquals(1,result4.size());
		
		
		//Test5
		java.util.List<Token> tokens5=new ArrayList<Token>();
		tokens5.add(new Slave(1,new Field(4,1)));
		tokens5.add(new Slave(1,new Field(5,3)));
		tokens5.add(new King(1,new Field(3,3)));
		tokens5.add(new King(2,new Field(2,5)));
		tokens5.add(new Slave(2,new Field(1,2)));
		tokens5.add(new Slave(2,new Field(2,1)));
		PlayingGround playingGround5=new PlayingGround(tokens4);
		
		List<Event> result5=playingGround5.moveToken(new King(2,new Field(2,5)), new Field(1,6));
		assertEquals(true,result5.contains(new TokenMovedEvent(new King(2,new Field(2,5)), new Field(1,6))));
		assertEquals(1,result5.size());
		
		List<Event> result52=playingGround5.moveToken(new Slave(2,new Field(1,2)), new Field(6,3));
		assertEquals(true,result52.contains(new TokenMovedEvent(new Slave(2,new Field(1,2)), new Field(6,3))));
		assertEquals(1,result52.size());
		

		List<Event> result53=playingGround5.moveToken(new King(2,new Field(1,6)), new Field(3,2));
		assertEquals(true,result53.contains(new TokenMovedEvent(new King(2,new Field(1,6)), new Field(3,2))));
		assertEquals(1,result53.size());
		
	}


}
