package jDistance.util;

/**
 * Repräsentiert ein Vertauschung zweier Spielfiguren vom GameController
 * 
 */
public class TokenSwapedEvent implements Event {

    private final jDistance.model.TokenMove firstTokenMove;
    private final jDistance.model.TokenMove secondTokenMove;

    /**
     * Konstruktor
     * @param firstTokenMove Erste Spielfigur
     * @param secondTokenMove  Zweite Spielfigur
     */
    public TokenSwapedEvent(jDistance.model.TokenMove firstTokenMove, jDistance.model.TokenMove secondTokenMove) {
        this.firstTokenMove = firstTokenMove;
        this.secondTokenMove = secondTokenMove;
    }

    /**
     * Gibt die erste Spielfigur zurück
     * @return erste Spielfigur
     */
    public jDistance.model.TokenMove getFirstTokenMove() {
        return this.firstTokenMove;
    }

    /**
     * Gibt die zweite Spielfigur zurück
     * @return zweite Spielfigur
     */
    public jDistance.model.TokenMove getSecondTokenMove() {
        return this.secondTokenMove;
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
        if (!(obj instanceof TokenSwapedEvent)) {
            return false;
        }
        final TokenSwapedEvent other = (TokenSwapedEvent) obj;
        if (!this.firstTokenMove.equals(other.firstTokenMove)) {
            return false;
        }
        if (!this.secondTokenMove.equals(other.secondTokenMove)) {
            return false;
        }
        return true;
    }

    /**
     * Berechnet den hashCode des Objectes
     * @return hashCode des Objects
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.firstTokenMove.hashCode();
        hash = 17 * hash + this.secondTokenMove.hashCode();
        return hash;
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<TokenSwapedEvent>{ firstTokenMove:" + this.getFirstTokenMove() + " secondTokenMove:" + this.getSecondTokenMove() + " } ";
    }
}
