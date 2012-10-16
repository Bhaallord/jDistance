package jDistance.gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import jDistance.gui.ImageHandler.imageType;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Testklasse, welche alle Referenzen der aktuell verwendeten Bilder prüft.
 * Neu verwendete Bilder müssen hier in je einem neuen Testfall münden.
 * 
 * Stand 25.12.2011
 */
public class ImageHandlerTest {
	
	// Dient zum vergleichen beider Bilder
	private Image testImage = null;
	private Image compareMe = null;

	/**
	 * Private Methode welche zwei Objekte vom Typ Image vergleicht.
	 * 
	 * @param expected erwartetes Bild
	 * @param actual zu testendes Bild
	 * @return true, wenn gleich
	 */
	private Boolean compare(Image expected, Image actual){
		//zwei PixelGrabber
		PixelGrabber expectedGrabber = new PixelGrabber(expected, Integer.valueOf(0), Integer.valueOf(0), expected.getWidth(null), expected.getHeight(null), Boolean.FALSE);
		PixelGrabber actualGrabber = new PixelGrabber(actual, Integer.valueOf(0), Integer.valueOf(0), actual.getWidth(null), actual.getHeight(null), Boolean.FALSE);
		//Vergleichen der Bilder
		return(java.util.Arrays.equals((Byte[])expectedGrabber.getPixels(), (Byte[])actualGrabber.getPixels()));
	}
	
	
	@Before
	public void init(){
		//setzt die Referenzen der Image Variablen vor jedem Test auf NULL zurück, um sie vergleichen zu können
		this.testImage = null;
		this.compareMe = null;
	}
	
	
	@Test
	public void borderTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/border.gif"));
			this.compareMe = ImageHandler.getImage(imageType.BORDER);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));			
		}
	}

	
	@Test
	public void whiteTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/white.gif"));
			this.compareMe = ImageHandler.getImage(imageType.BRIGHT);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void darkTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/black.gif"));
			this.compareMe = ImageHandler.getImage(imageType.DARK);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void fieldMarkerSelectedMoveTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/fieldMarkerPossibleMove.png"));
			this.compareMe = ImageHandler.getImage(imageType.FIELDMARKERSELECTEDMOVE);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void fieldMarkerSelectedTokenTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/fieldMarkerSelectedToken.png"));
			this.compareMe = ImageHandler.getImage(imageType.FIELDMARKERSELECTEDTOKEN);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void kingBlackTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/kingBl.gif"));
			this.compareMe = ImageHandler.getImage(imageType.KINGBL);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void kingWhiteTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/kingWh.gif"));
			this.compareMe = ImageHandler.getImage(imageType.KINGWH);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void slaveBlackTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/slaveBl.gif"));
			this.compareMe = ImageHandler.getImage(imageType.SLAVEBL);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void slaveBlackLockedTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/slaveBlLocked.gif"));
			this.compareMe = ImageHandler.getImage(imageType.SLAVEBLLOCKED);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void slaveWhiteTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/slaveWh.gif"));
			this.compareMe = ImageHandler.getImage(imageType.SLAVEWH);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}
	
	
	@Test
	public void slaveWhiteLockedTest() {
		try {
			// Bilder einlesen
			this.testImage = javax.imageio.ImageIO.read(new java.io.File("img/slaveWhLocked.gif"));
			this.compareMe = ImageHandler.getImage(imageType.SLAVEWHLOCKED);
		} catch (IOException e) {
			assertFalse(e instanceof IOException);	//Der Fehler soll nur bekannt gemacht werden
		} finally {
			if (this.testImage != null && this.compareMe != null) assertTrue(compare(this.testImage, this.compareMe));
		}
	}	
}
