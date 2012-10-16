package jDistance.util;

/**
 * MessageQueue ist ein Empfänger für ein MessageSender, der die Empfangen Nachrichten in einer FIFO speichert
 * 
 */
public class MessageQueue implements MessageReceiver {

    java.util.Queue<Event> t_messages;

    /**
     * Konstruktor
     */
    public MessageQueue() {
        t_messages = new java.util.LinkedList<Event>();
    }

    /**
     *  Fügt eine Nachricht zum FIFO hinzu und weckt die warteten Thread
     * @param event Event fas hinzugefügt werden soll
     */
    @Override
    public synchronized void addMessage(Event event) {
        this.t_messages.offer(event);
        notifyAll();
    }

    /**
     * Hohlt eine Nachricht aus dem FIFO wenn vorhanden
     * @return Event aus dem FIFO oder null
     */
    public synchronized Event receiveMessage() {
        if (t_messages.size() > 0) {
            return t_messages.poll();
        }
        return null;
    }

    /**
     * Hohlt eine Nachricht aus dem FIFO (Wartet maximale maxTime)
     * @return Event aus dem FIFO oder null
     */
    @Override
    public synchronized Event getMessage(long maxTime) {
        if (t_messages.isEmpty()) {
            try {
                wait(maxTime);
            } catch (InterruptedException exception) {
                System.out.println("Exception in MessageQueue geworfen");
            }
        }
        return receiveMessage();
    }
    
    /**
     * Hohlt eine Nachricht aus dem FIFO (Wartet unbegrenzt)
     * @return Event aus dem FIFO oder null
     */
    @Override
    public synchronized Event getMessage() {
        return getMessage(0);
    }
    
    /**
     * Gibt die größe des FIFO Speichers zurück
     * @return Anzahl der Event in der MessageQueue
     */
    public synchronized int size() {
        return this.t_messages.size();
    }

    /**
     * Leert den FIFO Speicher und löscht alle Nachrichten
     */
    public synchronized void clearQueue() {
        this.t_messages.clear();
    }
}
