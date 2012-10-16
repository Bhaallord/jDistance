package jDistance.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Die imageHandler Klasse stellt Bilder, welche vom Dateisystem geladen werden zur Verfügung.
 * Zur eindeutigen Klassifizierung dieser Bilder wird der Aufzählungstyp imageType verwendet.
 */
final class ImageHandler {

	/**
	 * Enum, welcher Art und Inhalt des Bildes repräsentiert
	 */
	public enum imageType {
		SLAVEWH, SLAVEWHLOCKED, SLAVEBL, SLAVEBLLOCKED, KINGWH, KINGBL, BORDER, FIELDMARKERSELECTEDTOKEN, FIELDMARKERSELECTEDMOVE, DARK, BRIGHT
	};

	/**
	 * Statische Funktion, welche je nach Art des übergebenen imageType ein BufferedImage zurückliefert.
	 * Wird dieses nicht im Dateisystem gefunden, wird ein Fehler geworfen.
	 * 
	 * @param imageType image
	 * @return BufferedImage picture
	 * @throws IOException
	 */
	static BufferedImage getImage(imageType image) throws IOException {
		switch (image) {
			case SLAVEWH:
				return(javax.imageio.ImageIO.read(new java.io.File("img/slaveWh.gif")));
			case SLAVEWHLOCKED:
				return(javax.imageio.ImageIO.read(new java.io.File("img/slaveWhLocked.gif")));
			case SLAVEBL:
				return(javax.imageio.ImageIO.read(new java.io.File("img/slaveBl.gif")));
			case SLAVEBLLOCKED:
				return(javax.imageio.ImageIO.read(new java.io.File("img/slaveBlLocked.gif")));
			case KINGWH:
				return(javax.imageio.ImageIO.read(new java.io.File("img/kingWh.gif")));
			case KINGBL:
				return(javax.imageio.ImageIO.read(new java.io.File("img/kingBl.gif")));
			case DARK:
				return(javax.imageio.ImageIO.read(new java.io.File("img/black.gif")));
			case BRIGHT:
				return(javax.imageio.ImageIO.read(new java.io.File("img/white.gif")));
			case BORDER:
				return(javax.imageio.ImageIO.read(new java.io.File("img/border.gif")));
			case FIELDMARKERSELECTEDTOKEN:
				return(javax.imageio.ImageIO.read(new java.io.File("img/fieldMarkerSelectedToken.png")));
			case FIELDMARKERSELECTEDMOVE:
				return(javax.imageio.ImageIO.read(new java.io.File("img/fieldMarkerPossibleMove.png")));
			default:
				throw new IOException("Fehler beim Laden des Bildes " + image.toString());
		}
	}
}