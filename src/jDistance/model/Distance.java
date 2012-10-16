package jDistance.model;
/**
 * Klasse die denn Abstand zwischen zwei Tokens angibt
 * 
 */
public class Distance {

    private final int dx;
    private final int dy;

    /**
     * @brief Standartkonstruktor
     *
     * @param dx Differenz in x Richtung
     * @param dy Differenz in y Richtung
     */
    public Distance(int dx, int dy) {
        this.dx = java.lang.Math.abs(dx);
        this.dy = java.lang.Math.abs(dy);
    }

    /**
     * @brief Kopierkonstruktor
     *
     * @param other Zu kopierendes Objekt
     */
    public Distance(Distance other) {
        this.dx = other.getX();
        this.dy = other.getY();
    }

    /**
     * Gibt die aktuelle Position in X-Richtung zurück
     *
     * @return X Position
     */
    public int getX() {
        return this.dx;
    }

    /**
     * Gibt die aktuelle Position in Y-Richtung zurück
     *
     * @return Y Position
     */
    public int getY() {
        return this.dy;
    }

    /**
     * @brief Vergleichsoperation
     *
     * @param obj Zu vergleichende Distanz
     * @return Gibt true zurück wenn die Distanzen gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Distance)) {
            return false;
        }
        final Distance other = (Distance) obj;
        if ((this.getX() == other.getX()) && (this.getY() == other.getY())) {
            return true;
        }
        if ((this.getY() == other.getX()) && (this.getX() == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<Distance>{ dx:" + this.getX() + " dy:" + this.getY()+" } ";
    }


    /**
     * Berechnet den hashCode des Objectes
     * @return hashCode des Objects
     */
    @Override
    public int hashCode() {
        int hash = 7;
        int dx=0;
        int dy=0;
        if(this.dx>this.dy){
            dx=this.dx;
            dy=this.dy;
        }
        else{
            dx=this.dy;
            dy=this.dx;
        }
        
        hash = 67 * hash + dx;
        
        hash = 67 * hash + dy;
        return hash;
    }
}
