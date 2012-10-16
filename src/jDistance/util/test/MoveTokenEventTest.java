/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.model.Field;
import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.util.MoveTokenEvent;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * 
 */
public class MoveTokenEventTest {
    
    @Test
    public void testMoveTokenEventTest() {
        MoveTokenEvent move1= new MoveTokenEvent(3,new TokenMove(new Token(2,new Field(4,5)),new Field(6,7)));
        MoveTokenEvent move2= new MoveTokenEvent(3,new Token(2,new Field(4,5)),new Field(6,7));
        assertEquals(new Field(6,7), move1.getDestination());
        assertEquals(new Token(2,new Field(4,5)), move1.getToken());
        assertEquals(3, move2.getPlayer());
        assertEquals(move1, move2);
    }
    
    @Test
    public void testToString() {
        assertEquals(true,new MoveTokenEvent(3,new Token(2,new Field(4,5)),new Field(6,7)).toString().contains("MoveTokenEvent"));
        assertEquals(true,new MoveTokenEvent(3,new Token(2,new Field(4,5)),new Field(6,7)).toString().contains(new Token(2,new Field(4,5)).toString()));
        
    }
    
    @Test
    public void testEqualsObject() {
        MoveTokenEvent move1= new MoveTokenEvent(3,new TokenMove(new Token(2,new Field(4,5)),new Field(6,7)));
        MoveTokenEvent move2= new MoveTokenEvent(3,new Token(2,new Field(4,5)),new Field(6,7));
        //assertEquals(move1, move2);
        assertEquals(false, move1.equals(new MoveTokenEvent(3,new Token(2,new Field(3,5)),new Field(6,7))));
        assertEquals(false, move1.equals(new MoveTokenEvent(3,new Token(2,new Field(3,5)),new Field(3,7))));
        assertEquals(false, move1.equals(new MoveTokenEvent(1,new Token(2,new Field(3,5)),new Field(6,7))));
        assertEquals(false, move2.equals(new Object()));
        assertEquals(false, move1.equals(null));
    }
    
    @Test
    public void testHashCode(){
        assertEquals(247843225,new MoveTokenEvent(3,new Token(2,new Field(3,5)),new Field(6,7)).hashCode());
    }
    
    @Test
    public void testGetPlayer(){
        assertEquals(3,new MoveTokenEvent(3,new Token(2,new Field(3,5)),new Field(6,7)).getPlayer());
        assertEquals(2,new MoveTokenEvent(2,new Token(2,new Field(3,5)),new Field(6,7)).getPlayer());
    }
    
}


    
