package jDistance.util;


/**
 * Event was ein Zug von einen Spieler anfordert
 * 
 */
public class ExpectTokenMoveEvent implements Event {

    private final int player;
    private final java.util.List<jDistance.model.Token> tokens;
    private final java.util.List<jDistance.model.TokenMove> tokenMoves;

    /**
     * Konstruktor der ein neuen ExpectTokenMoveEvent erzeugt
     * @param player Spieler von den ein Zug erwartet wird
     * @param tokens Position aller Spielfiguren auf den Spielfedl
     * @param tokenMoves Alle möglichen Zuge, aller Spielfiguren
     */
    public ExpectTokenMoveEvent(int player, java.util.List<jDistance.model.Token> tokens, java.util.List<jDistance.model.TokenMove> tokenMoves) {
        this.player = player;
        this.tokens = tokens;
        this.tokenMoves = tokenMoves;
    }

    /**
     * Gibt den Spieler zurück von denn ein Zug gefordert wird.
     * @return Spieler Nummer 
     */
    public int getPlayer() {
        return this.player;
    }

    /**
     * Gibt alle Tokens zurück die sich auf den aktuellen Spielfeld befinden
     * @return Tokens auf den Spielfeld
     */
    public java.util.List<jDistance.model.Token> getToken() {
        return java.util.Collections.unmodifiableList(tokens);
    }

    /**
     * Gibt alle möglichen Spielzüge, aller Tokens auf dem Spielfeld zurück
     * @return Mögliche Züge der Spielfiguren
     */
    public java.util.List<jDistance.model.TokenMove> getTokenMove() {
        return java.util.Collections.unmodifiableList(tokenMoves);
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
        if (!(obj instanceof ExpectTokenMoveEvent)) {
            return false;
        }
        final ExpectTokenMoveEvent other = (ExpectTokenMoveEvent) obj;
        if (this.player != other.player) {
            return false;
        }
        if (!this.tokens.equals(other.tokens)) {
            return false;
        }
        if (!this.tokenMoves.equals(other.tokenMoves)) {
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
        hash = 59 * hash + this.player;
        hash = 59 * hash + this.tokens.hashCode();
        hash = 59 * hash + this.tokenMoves.hashCode();
        return hash;
    }
    
    

    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<ExpectTokenMoveEvent>{ player:" + this.getPlayer() + " token:" + this.tokens.toString() + " tokenMove:" + this.tokenMoves.toString() + " } ";
    }
}
