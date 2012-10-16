package jDistance.model;

/**
 * Repräsentiert eine Bewegung einer Spielfigur
 * 
 */
public class TokenMove implements Cloneable {

    private final Token token;
    private final Field destination;

    /**
     * @brief Standartkonstruktor
     *
     * Diese Klasse speichert einen möglichen Spielzug einer Spielfigur 
     *
     * @param token Spielfigur die eine Bewegung machen kann
     * @param desitnation Ziel der Bewegung
     */
    public TokenMove(Token token, Field desitnation) {
        this.token = token;
        this.destination = desitnation;

    }

    /**
     * @brief Kopierkonstruktor
     *
     * Diese Klasse speichert einen möglichen Spielzug einer Spielfigur 
     *
     * @param move TokenMove der kopiert werden soll
     */
    public TokenMove(TokenMove move) {
        this.token = move.getToken();
        this.destination = move.getDestination();

    }

    /**
     * @brief Gibt die Spielfigur zurück
     *
     * @return Spielfigur      */
    public final Token getToken() {
        return this.token;
    }

    /**
     * @brief Gibt das Zielfeld der Bewegung zurück
     *
     * @return Zielfeld
     */
    public final Field getDestination() {
        return this.destination;
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<TokenMove>{ token:" + this.getToken() + " destination:" + this.getDestination() + " } ";
    }

    /**
     * Erzeugt eine Kopie des aktuellen Object und gibt es zurück
     * @return Kopie des Objectes
     */
    @Override
    public TokenMove clone() {
        try {

            return (TokenMove) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
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
        if (!(obj instanceof TokenMove)) {
            return false;
        }
        final TokenMove other = (TokenMove) obj;
        if (!this.token.equals(other.token)) {
            return false;
        }
        if (!this.destination.equals(other.destination)) {
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
        int hash = 3;
        hash = 71 * hash + this.token.hashCode();
        hash = 71 * hash + this.destination.hashCode();
        return hash;
    }
    
    
}
