package jDistance.util;

/**
 * Event was ein Lockänderung einer Spielfigur anzeigt
 * 
 */
public class TokenLockedEvent implements Event {

    private final jDistance.model.Token token;

    /**
     * Konstruktor
     * @param token Spielfigur die gelockt wird
     */
    public TokenLockedEvent(jDistance.model.Token token) {
        this.token = token;
    }

    /**
     * Gibt die Spielfigur zurück, die gelockt werden wird(Token hat noch den alten Lock status) 
     * @return Token der gelockt werden
     */
    public jDistance.model.Token getToken() {
        return this.token;
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
        if (!(obj instanceof TokenLockedEvent)) {
            return false;
        }
        final TokenLockedEvent other = (TokenLockedEvent) obj;
        if (!this.token.equals(other.token)) {
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
        hash = 41 * hash + this.token.hashCode();
        return hash;
    }

    
    
    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<TokenLockedEvent>{ token:" + this.token.toString() + " } ";
    }
}
