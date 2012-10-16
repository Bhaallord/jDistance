/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util.test;

import jDistance.util.CloseEvent;
import jDistance.util.MessageHandler;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * 
 */
public class MessageHandlerTest extends MessageHandler{
    public MessageHandlerTest(){
        
    }
    
    public int messageQueueSize(){
        return this.size();
    }
        
    @Test
    public void testMessageHandler() {
        MessageHandler handler= new MessageHandlerTest();
        assertEquals(0, handler.sizeOfReceivers());
    }

    @Test
    public void testConnect() {
        MessageHandler handler= new MessageHandlerTest();
        MessageHandler handler2= new MessageHandlerTest();
        assertEquals(0, handler.sizeOfReceivers());
        assertEquals(0, handler2.sizeOfReceivers());
        handler.connect(handler2);
        assertEquals(1, handler.sizeOfReceivers());
        assertEquals(1, handler2.sizeOfReceivers());
    }

    @Test
    public void testDisconnect() {
        MessageHandler handler= new MessageHandlerTest();
        MessageHandler handler2= new MessageHandlerTest();
        assertEquals(0, handler.sizeOfReceivers());
        assertEquals(0, handler2.sizeOfReceivers());
        handler.connect(handler2);
        assertEquals(1, handler.sizeOfReceivers());
        assertEquals(1, handler2.sizeOfReceivers());
        handler.disconnect(handler2);
        assertEquals(0, handler.sizeOfReceivers());
        assertEquals(0, handler2.sizeOfReceivers());
    }

    @Test
    public void testSizeOfReceivers() {
        MessageHandler handler= new MessageHandlerTest();
        MessageHandler handler2= new MessageHandlerTest();
        assertEquals(0, handler.sizeOfReceivers());
        assertEquals(0, handler2.sizeOfReceivers());
        handler.connect(handler2);
        int index=0;
        while(index<100){
            handler.connect(new MessageHandlerTest());
            index++;
        }
        assertEquals(101, handler.sizeOfReceivers());
        assertEquals(1, handler2.sizeOfReceivers());
    }

    @Test
    public void testAddEvent() {
        MessageHandlerTest handler= new MessageHandlerTest();
        assertEquals(0, handler.messageQueueSize());
        
        int index=0;
        while(index<100){
            handler.addEvent(new CloseEvent());
            index++;
        }
        assertEquals(100, handler.messageQueueSize());
        
    }
    
    @Test
    public void testClearQueue() {
        MessageHandlerTest handler= new MessageHandlerTest();
        assertEquals(0, handler.messageQueueSize());
        
        int index=0;
        while(index<100){
            handler.addEvent(new CloseEvent());
            index++;
        }
        assertEquals(100, handler.messageQueueSize());
        handler.clearQueue();
        assertEquals(0, handler.messageQueueSize());
        
    }
}
