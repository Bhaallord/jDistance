package jDistance.gui;

import jDistance.gui.ImageHandler.imageType;
import jDistance.model.Field;
import jDistance.model.Slave;
import jDistance.model.Token;

import java.awt.Component;
import java.awt.Point;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Die GuiToken Klasse repräsentiert die Spielfigur und ist abgeleitet von JLabel.
 * Sie beinhaltet ein Abbild der zugehörigen Token Instanz
 */
final class GuiToken extends JLabel {
	/*
	 * Generierte Serial UID
	 */
	private static final long serialVersionUID = -4418522005040374492L;
	private final Distance2D parent;
	private Token token;
	/**
	 * Der GuiToken Konstruktor, welcher das Label vollständig initialisiert und
	 * die logische Repräsentation zur Spielfigur herstellt.
	 * 
	 * @param token
	 *            Repräsentiert die Spielfigur.
	 */
	public GuiToken(Token token, Distance2D parent) {
		// Konstruktor von JLabel
		super();

		this.parent = parent;
		this.token = token;

		Point pos = this.parent.fieldToPoint(token.getPos());
		
		// Position der Spielfigur, sowie deren Breite und Höhe.
		this.setBounds(pos.x, pos.y, this.parent.getFieldWidth(),this.parent.getFieldWidth());
		
		// Das ImageIcon zentriert ausgerichtet.
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);

		this.draw();

		this.parent.getTokens().add(this);
		this.parent.getLayer().add(this, this.parent.getTokenLayerPos());
	}

	/**
	 * Vergleich zweier Spielfiguren.
	 * 
	 * @param token
	 *            Token mit welchem verglichen werden soll.
	 * @return true wenn gleich sonst false.
	 */
	public boolean compareToToken(Token token) {
		return this.token.equals(token);
	}

	/**
	 * Zeichnen der Spielfiguren, je nach dem welcher Status gesetzt ist.
	 */
	public void draw() {
	
		try {
			if (this.token instanceof Slave) {
				if (token.getPlayer() == 1) {
					this.setIcon(new ImageIcon(
							(this.token.isLocked()) 
									? ImageHandler.getImage(imageType.SLAVEWHLOCKED)
									: ImageHandler.getImage(imageType.SLAVEWH)));
				} else {
					this.setIcon(new ImageIcon(
							(this.token.isLocked()) 
									? ImageHandler.getImage(imageType.SLAVEBLLOCKED)
									: ImageHandler.getImage(imageType.SLAVEBL)));
				}
			} else {
				// Token is king
				this.setIcon(new ImageIcon(
						(this.token.getPlayer() == 1) 
									? ImageHandler.getImage(imageType.KINGWH) 
									: ImageHandler.getImage(imageType.KINGBL)));
			}
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * Getter Funktion für das mit dieser Spielfigur verbundene Token
	 * 
	 * @return Token token
	 */
	synchronized public Token getToken() {
		return (this.token);
	}

	/**
	 * Bewegt eine Spielfigur durch verändern der List<Token> tokens und
	 * zeichnet gleichzeitig diesen Spielzug auf der Oberfläche.
	 * 
	 * @param targetPos Ziel
	 */
	public void move(Field targetPos) {
		
		Component me = this.parent.getLayer().getComponentAt(this.getLocation());
		
		try { 
			this.parent.getLayer().moveToFront(me);
		} catch (Exception e) {
			System.out.println("DEBUG: "+e.toString());
			System.out.println("DEBUG: "+me.toString());
			System.exit(1);
		}
		
		Point oldPos = this.parent.fieldToPoint(this.token.getPos());
		Point newPos = this.parent.fieldToPoint(targetPos);
		
		// Update token's position
		synchronized (this.token) {
			this.token.move(targetPos);
		}
		
		long linearAnimationTime = 6;
		int	 linearStepSize		 = 4;

		for (int x = oldPos.x; x < newPos.x; x += linearStepSize) {
			this.setLocation(x, oldPos.y);
			try {
				Thread.sleep(linearAnimationTime);
			} catch (Exception e) {	}
		}

		for (int x = oldPos.x; x > newPos.x; x -= linearStepSize) {
			this.setLocation(x, oldPos.y);
			try {
				Thread.sleep(linearAnimationTime);
			} catch (Exception e) {	}
		}

		for (int y = oldPos.y; y < newPos.y; y += linearStepSize) {
			this.setLocation(newPos.x, y);
			try {
				Thread.sleep(linearAnimationTime);
			} catch (Exception e) {	}
		}

		for (int y = oldPos.y; y > newPos.y; y -= linearStepSize) {
			this.setLocation(newPos.x, y);
			try {
				Thread.sleep(linearAnimationTime);
			} catch (Exception e) {	}
		}
			
		// Set token to final position / Fix token's position
		this.setLocation(newPos);
		
		this.parent.getLayer().validate();
		this.parent.getLayer().repaint();
	}

	/**
	 * Markiert eine Spielfigur als gefangen, dies wird durch den Status locked
	 * repräsentiert.
	 * 
	 * @param lockedStatus true -> Figur gefangen, false -> Figur nicht gefangen 
	 */
	synchronized public void setLocked(boolean lockedStatus) {
		this.token.setLocked(lockedStatus);
		// TODO: Animation / Icon-Änderung
	}
}