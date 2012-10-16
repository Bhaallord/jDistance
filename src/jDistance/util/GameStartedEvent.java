package jDistance.util;

/**
 * Event für ein neu gestartetes Spiel
 * 
 */
public class GameStartedEvent implements Event {

    private final java.util.List<jDistance.model.Token> tokens;

    
    /**
     * Konstruktor
     * @param tokens Startaufstellung
     */
    public GameStartedEvent(java.util.List<jDistance.model.Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Gibt die Startaufstellung für ein neues Spiel zurück
     * @return Startaufstellung
     */
    public java.util.List<jDistance.model.Token> getToken() {
        return java.util.Collections.unmodifiableList(tokens);
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
        if (!(obj instanceof GameStartedEvent)) {
            return false;
        }
        final GameStartedEvent other = (GameStartedEvent) obj;
        if (!this.tokens.equals(other.tokens)) {
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
        int hash = 5;
        hash = 23 * hash + this.tokens.hashCode();
        return hash;
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     *
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<StartGameEvent>{ tokens:" + this.getToken() + " } ";
    }
}
