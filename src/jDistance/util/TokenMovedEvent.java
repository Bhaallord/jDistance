package jDistance.util;


/**
 * Repräsentiert eine Bewegung einer Spielfigur vom GameController
 * 
 */
public class TokenMovedEvent extends jDistance.model.TokenMove implements Event {

    /**
     * Konstruktor
     * @param token Spielfigur die bewegt wurde
     * @param destination Zielfeld der Bewegung
     */
    public TokenMovedEvent(jDistance.model.Token token, jDistance.model.Field destination) {
        super(token, destination);
    }
    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<TokenMovedEvent>{ " + super.toString() + " } ";
    }
}
