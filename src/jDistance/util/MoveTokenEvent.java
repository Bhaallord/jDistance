package jDistance.util;

import jDistance.model.TokenMove;

/**
 * Repräsentiert eine Bewegung einer Spielfigur zum GameController
 * 
 */
public class MoveTokenEvent extends jDistance.model.TokenMove implements Event {

    private final int player;

    /**
     * Konstruktor
     * @param player Spieler der den Zug ausführt
     * @param token Spielfigur die bewegt wird
     * @param destination Ziel der Spielfigur
     */
    public MoveTokenEvent(int player, jDistance.model.Token token, jDistance.model.Field destination) {
        super(token, destination);
        this.player = player;
    }

    /**
     * Konstruktor
     * @param player Spieler der den Zug ausführt
     * @param move Bewegung die ausgeführt werden soll
     */
    public MoveTokenEvent(int player, TokenMove move) {
        super(move);
        this.player = player;
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
        if (!(obj instanceof MoveTokenEvent)) {
            return false;
        }
        final MoveTokenEvent other = (MoveTokenEvent) obj;
        if (this.player != other.player) {
            return false;
        }
        return super.equals(obj);
                
    }

    /**
     * Berechnet den hashCode des Objectes
     * @return hashCode des Objects
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.player;
        hash = 11 * hash +super.hashCode();
        return hash;
    }
    
    
/**
 * Gibt denn Spieler zurück der bewegt werden soll
 * @return Spieler
 */
    public int getPlayer() {
        return this.player;
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<MoveTokenEvent>{ player:" + this.getPlayer() + " super:" + super.toString() + " } ";
    }
}
