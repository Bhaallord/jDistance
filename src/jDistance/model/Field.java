package jDistance.model;
/**
 * Klasse die ein Feld auf dem Spielfeld repräsentiert
 * 
 */
public class Field {

	private final int x;
	private final int y;

	/**
	 * @brief Standartkonstruktor
	 * @param x
	 *            Position in X Richtung
	 * @param y
	 *            Position in Y Richtung
	 */
	public Field(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @brief Kopierkonstruktor
	 * @param other Zu kopierendes Feld
	 */
	public Field(Field other) {
		this.x = other.getX();
		this.y = other.getY();
	}

	/**
	 * Gibt die aktuelle Position in X-Richtung zurück
	 * 
	 * @return X Position
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Gibt die aktuelle Position in Y-Richtung zurück
	 * 
	 * @return Y Position
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * @brief Berechnet denn Abstand zwischen zwei Felder
	 * @param field
	 *            ist das Feld zwischen denn der Abstand berechnet werden soll
	 * @return Abstand zwischen beiden Feldern
	 */
	public Distance distanceTo(Field field) {
		return new Distance(java.lang.Math.abs(this.getX() - field.getX()),
				java.lang.Math.abs(this.getY() - field.getY()));
	}

     /**
	 * @brief Vergleichsoperation
	 * @param obj Zu vergleichendes Feld
	 * @return Gibt true zurück wenn die Felder gleich sind
	 */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Field)) {
            return false;
        }
        final Field other = (Field) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
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
        int hash = 7;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    /**
     * Gibt alle Daten als String formatiert zurück
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "<Field>{ x:" + this.getX() + " y:" + this.getY()+" } ";
    }


	

}
