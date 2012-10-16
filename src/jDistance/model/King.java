package jDistance.model;

/**
 * King speichert die Daten einer König-Spielfigur
 * 
 */
public class King extends Token implements Cloneable {
    /**
     * Erzweugt einen neuen König 
     * @param playerId Farbe des Königs
     * @param position Position des Königs
     */
    public King(int playerId, Field position) {
        super(playerId, position);
    }
    
    /**
     * Kopierkonstruktor 
     * @param other Object von den eine Kopie erstellt werden soll
     */
    public King(King other) {
        super(other);
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     *
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<King>{ " + super.toString() + " } ";
    }

    /**
     * Vergleicht zwei Objecte miteinander
     *
     * @param obj Object mit dem das aktuelle Object verglichen werden soll
     * @return gibt true zurück wenn die Objekte gleich sind
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof King)) {
            return false;
        }
        return super.equals((Token) other);
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     *
     * @return Datenstring
     */
    @Override
    public King clone() {
        return (King) super.clone();
    }

    /**
     * Berechnet den hashCode des Objectes
     *
     * @return hashCode des Objects
     */
    @Override
    public int hashCode() {
        return super.hashCode() + 2000;
    }
}
