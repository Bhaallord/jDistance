package jDistance.util;

/**
 * MessageHandler sorgt für die bidirektionale Kommunikation zweier Thread 
 * 
 */
public class MessageHandler {

    private jDistance.util.MessageSender sender;
    private jDistance.util.MessageQueue receiver;

    /**
     * Konstruktor
     */
    protected MessageHandler() {
        sender = new jDistance.util.MessageSender();
        receiver = new jDistance.util.MessageQueue();
    }

    /**
     * Registriert einen neuen Empfänger
     * @param receiver Empfänger
     */
    private void registerReceiver(jDistance.util.MessageReceiver receiver) {
        if(receiver==null){
            return;
        }
        this.sender.addMessageReceiver(receiver);
    }

    /**
     * Entfernt einen Empfänger aus der Liste
     * @param receiver Empfänger
     */
    private void removeReceiver(jDistance.util.MessageReceiver receiver) {
        if(receiver==null){
            return;
        }
        this.sender.removeMessageReceiver(receiver);
    }

    /**
     * Gibt den internen Empfänger zurück
     * @return Empfänger
     */
    private jDistance.util.MessageReceiver getReceiver() {
        return receiver;
    }

    /**
     * Gibt das nächste Event zurück was in der MessageQueue anliegt zurück und wartet maximal waitTime milisekunden
     * @param waitTime Maximalzeit zum warten auf das nächste Event
     * @return Event
     */
    protected Event getNextMessage(int waitTime) {
        return receiver.getMessage(waitTime);
    }

    /**
     * Gibt das nächste Event zurück was in der MessageQueue anliegt zurück
     * @return Event
     */
    protected Event getNextMessage() {
        return getNextMessage(0);
    }

    /**
     * Sendet eine Event an alle Empfänger
     * @param event welches gesendet werden soll
     */
    protected void sendMessage(Event event) {
        this.sender.sendMessage(event);
    }

    /**
     * Verbindet zwei MessageHandler miteinander
     * @param other Andere MessageHandler
     */
    public void connect(MessageHandler other) {
        if(other==null){
            return;
        }
        other.registerReceiver(receiver);
        this.registerReceiver(other.getReceiver());
    }

    /**
     * Trennt zwei MesageHandler voneinander
     * @param other Andere MessageHandler
     */
    public void disconnect(MessageHandler other) {
        if(other==null){
            return;
        }
        other.removeReceiver(receiver);
        this.removeReceiver(other.getReceiver());
    }

    /**
     * Gibt die anzahl der Empfänger zurück
     * @return Anzahl Empfänger
     */
    public int sizeOfReceivers() {
        return sender.sizeOfReceivers();
    }
    
    /**
     * Gibt die anzahl der Nachrichten in der MessageQueue
     * @return Anzahl Empfänger
     */
    protected int size() {
        return receiver.size();
    }
    
    /**
     * Gibt die anzahl der Nachrichten in der MessageQueue zurück
     * @return Anzahl der Nachrichten
     */
    public void addEvent(Event event) {
        if(event==null){
            return;
        }
        this.receiver.addMessage(event);
    }
    
    /**
     * Leert die MessageQueue
     */
    public void clearQueue() {
    	this.receiver.clearQueue();
    }
}
