package jDistance.util;
/**
 * Diese Event beendet die Ereignissschleifen einer MessageQueue und represäntiert das Ende der Verarbeitung
 * 
 */
public class CloseEvent implements Event {
    /**
     * Vergleicht zwei Objecte miteinander
     * 
     * @param obj Object mit dem das aktuelle Object verglichen werden soll
     * @return gibt true zurück wenn du Objecte gleich sind
     */
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CloseEvent)) {
            return false;
        }
        return true;
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<CloseEvent>{ } ";
    }

    /**
     * Berechnet den hashCode des Objectes
     * @return hashCode des Objects
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    
    
}
