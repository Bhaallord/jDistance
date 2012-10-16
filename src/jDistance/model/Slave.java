package jDistance.model;


/**
 * Slave speichert die Daten einer Bauer-Spielfigur
 * 
 */
public class Slave extends Token implements Cloneable {

   /**
     * Erzweugt einen neuen Bauer 
     * @param playerId Farbe des Königs
     * @param position Position des Königs
     */
    public Slave(int playerId, Field position) {
        super(playerId, position);
    }

    /**
     * Kopierkonstruktor 
     * @param other Object von den eine Kopie erstellt werden soll
     */
    public Slave(Slave other) {
        super(other);
    }

    /**
     * Vergleicht zwei Objecte miteinander
     * 
     * @param obj Objekt, mit dem das aktuelle Objekt verglichen werden soll
     * @return gibt true zurück wenn die Objekte gleich sind
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Slave)) {
            return false;
        }
        return super.equals((Token) other);
    }

    
    /**
     * Erzeugt eine Kopie des aktuellen Objekt und gibt es zurück
     * @return Kopie des Objektes
     */
    @Override
    public Slave clone() {
        return (Slave) super.clone();
    }

    /**
     * Berechnet den hashCode des Objektes
     * @return hashCode des Objekts
     */
    @Override
    public int hashCode() {
        return super.hashCode() + 200;
    }
    
    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<Slave>{ " + super.toString() + " } ";
    }
    
    
}
