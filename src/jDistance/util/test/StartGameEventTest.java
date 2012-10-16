/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.model.Field;
import jDistance.model.PlayingGround;
import jDistance.model.Token;
import jDistance.util.StartGameEvent;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class StartGameEventTest {

    @Test
    public void testStartGameEventIntPlayingGround() {
        StartGameEvent event1 = new StartGameEvent(1, null);
        assertEquals(null, event1.getPlayingGround());
        StartGameEvent event2 = new StartGameEvent(1, new PlayingGround());
        assertEquals(8, event2.getPlayingGround().getTokens().size());
        StartGameEvent event3 = new StartGameEvent(1, new PlayingGround(new ArrayList<Token>(Arrays.asList(new Token(2, new Field(3, 4))))));
        assertEquals(1, event3.getPlayingGround().getTokens().size());
        assertEquals(1, event1.getStartPlayer());
    }

    @Test
    public void testStartGameEventInt() {
        StartGameEvent event1 = new StartGameEvent(2);
        assertEquals(true, event1.getPlayingGround()!=null);
        assertEquals(2, event1.getStartPlayer());
    }

    @Test
    public void getStartPlayer() {
        StartGameEvent event1 = new StartGameEvent(1000);
        assertEquals(1000, event1.getStartPlayer());
        
    }

    @Test
    public void getPlayingGround() {
        StartGameEvent event1 = new StartGameEvent(2,new PlayingGround());
        assertEquals(8, event1.getPlayingGround().getTokens().size());
    }

    @Test
    public void testToString() {
        StartGameEvent event1 = new StartGameEvent(2,new PlayingGround());
        assertEquals(true, event1.toString().contains(event1.getPlayingGround().toString()));
    }
}
