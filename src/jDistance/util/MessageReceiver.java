package jDistance.util;


/**
 *
 * 
 */
public interface MessageReceiver {
        public void addMessage(Event event);
    	public Event getMessage(long maxTime);
    	public Event getMessage();
}