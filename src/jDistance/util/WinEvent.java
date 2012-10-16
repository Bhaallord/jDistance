package jDistance.util;

/**
 * Repräsentiert ein Event wenn ein Spieler gewonnen hat vom GameController
 *
 * 
 */
public class WinEvent implements Event {

    private final int player;

    /**
     * Konstruktor
     *
     * @param player Spieler der gewonnen hat
     */
    public WinEvent(int player) {
        this.player = player;
    }

    /**
     * Gibt den Spieler zurück der gewonnen hat
     *
     * @return Gewinner
     */
    public int getPlayer() {
        return this.player;
    }

    /**
     * Vergleicht zwei Objecte miteinander
     *
     * @param obj Object mit dem das aktuelle Object verglichen werden soll
     * @return gibt true zurück wenn du Objecte gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WinEvent)) {
            return false;
        }
        final WinEvent other = (WinEvent) obj;
        if (this.player != other.player) {
            return false;
        }
        return true;
    }

    /**
     * Berechnet den hashCode des Objectes
     *
     * @return hashCode des Objects
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.player;
        return hash;
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     *
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<WinEvent>{ player:" + this.getPlayer() + " } ";
    }
}
