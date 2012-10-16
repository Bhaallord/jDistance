package jDistance.util;

/**
*
* 
*/
public class MessageSender {
   private java.util.List<MessageReceiver> t_receivers;

   /**
    * Standartkonstruktor
    */
   public MessageSender() {
       t_receivers=new java.util.ArrayList<MessageReceiver>();
   }
   /**
    * Fügt ein Empfänger zur Empfangsliste hinzu
    * @param receiver Empfänger
    */
   public synchronized void addMessageReceiver(MessageReceiver receiver){
       t_receivers.add(receiver);
   }
   /**
    * Entfernt ein Empfänger aus der Empfangsliste
    * @param receiver Empfänger
    */
   public synchronized void removeMessageReceiver(MessageReceiver receiver){
       t_receivers.remove(receiver);
   }
   
   /**
    * Sendet ein Event an alle Empfänger in der Empfangsliste
    * @param event Nachricht
    */
    public synchronized void sendMessage(Event event){
        for(MessageReceiver receiver:this.t_receivers){
            receiver.addMessage(event);
        }
   }
    
    /**
     * Gibt die anzahl der Empfänger in der Empfangsliste zurück
     * @return Anzahl der Empfänger
     */
    public int sizeOfReceivers(){
    	return this.t_receivers.size();
    }
          
}