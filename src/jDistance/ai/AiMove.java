package jDistance.ai;

import jDistance.model.TokenMove;

/**
 * Repräsentiert einen möglichen Spielzug einer Spielfigur
 * 
 */
public class AiMove extends TokenMove {

    private int score;

    /**
     * Konstruktor
     * @param score Basispunktzahl
     * @param token Spielfigur die bewegt wird
     * @param destination Ziel der Spielfigur
     */
    public AiMove(int score, jDistance.model.Token token, jDistance.model.Field destination) {
        super(token, destination);
        this.score = score;
    }

    /**
     * Konstruktor
     * @param score Basispunktzahl
     * @param move Zug einer Spielfigur
     */
    public AiMove(int score, TokenMove move) {
        super(move);
        this.score = score;
    }

    /**
     * Gibt die Punktzahl des Zuges zurück
     * @return Punktzahl
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setzt die neue Punktzahl
     * @param score  Punktzahl
     */
    public void setScore(int score) {
        this.score = score;
    }

     /**
     * Gibt alle Daten als String formatiert zurück
     *
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<AiMove>{ score:" + this.getScore() + " super:" + super.toString() + " } ";
    }
    
    /**
     * Erzeugt eine Kopie des aktuellen Object und gibt es zurück
     *
     * @return Kopie des Objectes
     */
    @Override
    public AiMove clone() {
        return (AiMove) super.clone();
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
        if (!(obj instanceof AiMove)) {
            return false;
        }
        final AiMove other = (AiMove) obj;
        if (this.score != other.score) {
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
        hash = 79 * hash + this.score;
        return hash;
    }
}
