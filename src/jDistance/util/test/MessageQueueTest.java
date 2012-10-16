/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.util.CloseEvent;
import jDistance.util.MessageQueue;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class MessageQueueTest {

    @Test
    public void testMessageQueue() {
        MessageQueue message = new MessageQueue();
        assertEquals(0, message.size());
        assertEquals(null, message.getMessage(10));
    }

    @Test
    public void testAddMessage() {
        MessageQueue message = new MessageQueue();
        message.addMessage(new CloseEvent());
        assertEquals(1, message.size());
        assertEquals(new CloseEvent(), message.getMessage());
    }

    @Test
    public void testReceiceMessage() {
        MessageQueue message = new MessageQueue();
        message.addMessage(new CloseEvent());
        assertEquals(1, message.size());
        assertEquals(new CloseEvent(), message.getMessage());
        assertEquals(0, message.size());

    }

    @Test
    public void testGetMessageLong() {
        MessageQueue message = new MessageQueue();
        message.addMessage(new CloseEvent());
        assertEquals(1, message.size());
        assertEquals(new CloseEvent(), message.getMessage(100));
        assertEquals(null, message.getMessage(100));

    }

    @Test
    public void testGetMessage() {
        MessageQueue message = new MessageQueue();
        message.addMessage(new CloseEvent());
        assertEquals(1, message.size());
        assertEquals(new CloseEvent(), message.getMessage());
    }

    @Test
    public void testSize() {
        MessageQueue message = new MessageQueue();
        message.addMessage(new CloseEvent());
        assertEquals(1, message.size());
        message.addMessage(new CloseEvent());
        assertEquals(2, message.size());
        message.addMessage(new CloseEvent());
        assertEquals(3, message.size());
    }

    @Test
    public void testClearQueue() {
        MessageQueue message = new MessageQueue();
        message.addMessage(new CloseEvent());
        message.addMessage(new CloseEvent());
        message.addMessage(new CloseEvent());
        assertEquals(3, message.size());
        message.clearQueue();
        assertEquals(0, message.size());
    }
}
