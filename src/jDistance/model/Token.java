package jDistance.model;
/**
 * Spielfiguren Hauptklasse
 * 
 */
public class Token implements Cloneable {

    private Field pos;
    private int player;
    private boolean locked = false;

    /**
     * Standartkonstruktor
     * 
     * @param playerId
     * @param position
     */
    public Token(int playerId, Field position) {
        this.player = playerId;
        this.pos = position;
    }

    /**
     * Kopierkonstruktor
     * 
     * @param other
     */
    public Token(Token other) {
        this.pos = new Field(other.getPos());
        this.player = other.getPlayer();
        this.locked = other.isLocked();
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     *
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<Token>{ pos:" + this.getPos() + " player:" + this.getPlayer() + " locked:" + this.isLocked() + " } ";
    }

    /**
     * Gibt die Position der Spielfigur zurück
     * @return aktuelle Position
     */
    public final Field getPos() {
        return this.pos;
    }

    /**
     * Gibt den Spieler der Spielfigur zurück
     * @return Spieler
     */
    public final int getPlayer() {
        return this.player;
    }

    /**
     * Gibt den Lock-Status der Spielfigur zurück
     * @return Lock-Status
     */
    public final boolean isLocked() {
        return this.locked;
    }

    /**
     * Setzt den Lock-Status der Spielfigur
     * @param locked neuer Status
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Bewegt die Figur zur neuen Position
     * @param newPos Zielfeld
     */
    public void move(Field newPos) {
        this.pos = newPos;
    }

    /**
     * Erzeugt eine Kopie des aktuellen Object und gibt es zurück
     *
     * @return Kopie des Objectes
     */
    @Override
    public Token clone() {
        try {

            return (Token) super.clone();
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
        if (!(obj instanceof Token)) {
            return false;
        }
        final Token other = (Token) obj;
        if (!this.pos.equals(other.pos)) {
            return false;
        }
        if (this.player != other.player) {
            return false;
        }
        if (this.locked != other.locked) {
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
        hash = 79 * hash + this.pos.hashCode();
        hash = 79 * hash + this.player;
        hash = 79 * hash + (this.locked ? 1 : 0);
        return hash;
    }
}
