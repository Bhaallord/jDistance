/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class MessageSenderTest {

    @Test
    public void testMessageSender() {
        MessageSender sender1= new MessageSender();
        assertEquals(0, sender1.sizeOfReceivers());
    }

    @Test
    public void testAddMessageReceiver() {
        MessageSender sender1= new MessageSender();
        MessageReceiver emp = new MessageQueue();
        sender1.addMessageReceiver(emp);
        assertEquals(1, sender1.sizeOfReceivers());
        sender1.sendMessage(new CloseEvent());
        assertEquals(new CloseEvent(),emp.getMessage(10));
    }

    @Test
    public void testRemoveMessageReceiver() {
        MessageSender sender1= new MessageSender();
        MessageReceiver emp = new MessageQueue();
        sender1.addMessageReceiver(emp);
        assertEquals(1, sender1.sizeOfReceivers());
        sender1.removeMessageReceiver(emp);
        assertEquals(0, sender1.sizeOfReceivers());
    }

    @Test
    public void testSendMessage() {
        MessageSender sender1= new MessageSender();
        MessageReceiver emp = new MessageQueue();
        MessageReceiver emp2 = new MessageQueue();
        sender1.addMessageReceiver(emp);
        sender1.addMessageReceiver(emp2);
        assertEquals(2, sender1.sizeOfReceivers());
        sender1.sendMessage(new CloseEvent());
        assertEquals(new CloseEvent(),emp.getMessage(10));
        sender1.sendMessage(new CloseEvent());
        sender1.sendMessage(new WinEvent(1));
        assertEquals(new CloseEvent(),emp.getMessage(10));
        assertEquals(new WinEvent(1),emp.getMessage(10));
        assertEquals(3, ((MessageQueue)emp2).size());
    }

    @Test
    public void testSizeOfReceivers() {
        MessageSender sender1= new MessageSender();
        MessageReceiver emp = new MessageQueue();
        MessageReceiver emp2 = new MessageQueue();
        sender1.addMessageReceiver(emp);
        assertEquals(1, sender1.sizeOfReceivers());
        sender1.addMessageReceiver(emp2);
        assertEquals(2, sender1.sizeOfReceivers());
        sender1.removeMessageReceiver(emp);
        assertEquals(1, sender1.sizeOfReceivers());
        sender1.removeMessageReceiver(emp2);
        assertEquals(0, sender1.sizeOfReceivers());
        
        
    }
}
