/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.util;

import jDistance.model.PlayingGround;

/**
 * Startet ein neues Spiel beim GameController
 * 
 */
public class StartGameEvent implements Event {

    private final int startPlayer;
    private final PlayingGround playingGround;

    /**
     * Konstruktor
     * @param startPlayer Startspieler
     * @param playingGround Startaufstellung
     */
    public StartGameEvent(int startPlayer, PlayingGround playingGround) {
        this.playingGround = playingGround;
        this.startPlayer = startPlayer;

    }

    /**
     * Konstruktor
     * @param startPlayer Startspieler
     */
    public StartGameEvent(int startPlayer) {
        this.playingGround = new PlayingGround();
        this.startPlayer = startPlayer;

    }
/**
 * Gibt den Startspieler zurück
 * @return Startspieler
 */
    public int getStartPlayer() {
        return startPlayer;
    } 

    /**
     * Gibt die Startaufstellung zurück
     * @return Startaufstellung
     */
    public PlayingGround getPlayingGround() {
        return playingGround;
    }
    
    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<StartGameEvent>{ startPlayer:" + this.getStartPlayer() + " playingGround:" + getPlayingGround() + " } ";
    }
}
