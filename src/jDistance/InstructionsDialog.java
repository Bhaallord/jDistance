package jDistance;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * Diese Klasse stellt den Dialog für die Spielanleitung zur Verfügung 
 */
public class InstructionsDialog extends JDialog implements ComponentListener {
	
	private static final long serialVersionUID = 1L;

	private JDistance	game;
	private JFrame 		parentFrame;
	
	/**
	 * Erzeugt den Dialog für die Spielanleitung
	 * @param parentFrame Referenz auf das Hauptfenster
	 */
	public InstructionsDialog(JDistance game, JFrame parentFrame) {
		super(parentFrame, "j3Distance Spielanleitung", Dialog.ModalityType.APPLICATION_MODAL);
		
		this.game			= game;
		this.parentFrame 	= parentFrame;
		
		this.setSize(600,600);
		this.setResizable(false);
		
		this.addComponentListener(this);
		
		// Variablen anlegen
	  	JScrollPane jScrollPane = new JScrollPane();
	  	JTextPane jTextPane = new JTextPane();
        	  	
	  	// Eigenschaften dem TextPane zuordnen
		jTextPane.setEditable(true);
		jTextPane.setBackground(Color.white);
		jTextPane.setContentType("text/html");
		jTextPane.setText("<html>" +
				"<h1 align=center>JDistance – Spielanleitung</h1>" +
				"<br>" +
				"<table width=100% border=0 cellpadding=7 cellspacing=2>" +
					"<tr>" +
						"<td colspan=2 valign=top>DISTANZ ist ein Zwei-Personen-Spiel, " +
						"das wie Schach auf einem Spielbrett mit 8x8 Feldern gespielt wird. " +
						"Jeder Spieler besitzt vier Spielfiguren - einen König und drei Helfer. " +
						"Spielfiguren können nicht geschlagen, aber gefangen genommen werden. </td>" +
					"</tr>" +
					"<tr>" +
						"<td valign=top><b>Ziel des Spiels:</b></td>" +
						"<td valign=top>Ziel des Spiel ist es, den gegnerischen König gefangen " +
						"zu nehmen oder den Gegner in eine Situation zu bringen, in der er keinen " +
						"legalen Zug mehr ausführen kann. Der Spieler, der dieses Ziel erreicht, " +
						"hat gewonnen.</td>" +
					"</tr>" +
					"<tr>" +
						"<td valign=top><b>Spieler:</b></td>" +
						"<td valign=top>DISTANZ ist ein Spiel für zwei Spieler. Spieler A (Weiß) " +
						"spielt mit weißen Spielfiguren von unten nach oben. Spieler B (Schwarz) " +
						"spielt mit schwarzen Spielfiguren von oben nach unten.</td>" +
					"</tr>" +
					"<tr>" +
						"<td valign=top><b>Spielbrett:</b></td>" +
						"<td valign=top>Gespielt wird auf einem Schachbrett mit 8x8 Feldern.</td>" +
					"</tr>" +
					"<tr>" +
						"<td valign=top><b>Spielfiguren:</b></td>" +
						"<td valign=top>Es gibt zwei Typen von Spielfiguren: Könige und Helfer. Was " +
						"Spielzüge betrifft, unterscheiden sie sich nicht. Ein Unterschied besteht " +
						"darin, dass wenn ein König gefangen genommen wurde, das Spiel beendet ist. " +
						"Bei der Gefangennahme eines Helfers ist dies nicht der Fall. Der andere " +
						"Unterschied besteht darin, dass Könige gefangene Helfer wieder befreien können.</td>" +
					"</tr>" +
					"<tr>" +
						"<td valign=top><b>Spielablauf:</b></td>" +
						"<td valign=top>Es wird immer abwechselnd gezogen, wobei Spieler Weiß beginnt. " +
					"Es besteht Zugzwang.</td>" +
					"</tr>" +
					"<tr>" +
						"<td valign=top><b>Verschieben von Spielfiguren:</b></td>" +
						"<td valign=top>Jeder Spieler hat zu jeder Zeit vier Spielfiguren " +
						"auf dem Spielbrett. Um die möglichen Züge jeder seiner vier Spielfiguren (im " +
						" Folgenden X genannt) zu bestimmen, müssen jeweils die anderen drei " +
						"Spielfiguren (A, B, C) miteinander verglichen werden. Die Figur X kann nur " +
						"dann verschoben werden, wenn sie nicht gefangen ist; X kann auf solche undn" +
						"nur solche Felder verschoben werden, deren Distanz zu X’s alter Position " +
						"gleich einer Distanz zwischen seinen jeweils anderen Spielfiguren ist, d.h. " +
						"gleich der Distanz zwischen A und B bzw. A und C bzw. B und C ist. Steht auf " +
						"der neuen Position von X bereits eine Spielfigur (eigene oder fremde, " +
						"gefangen oder nicht gefangen), so wird diese auf die alte Position von X " +
						"verschoben.</td>" +
					"</tr>" +
					"<tr>" +
						"<td valign=top><b>Gefangennahme:</b></td>" +
						"<td valign=top>Falls X auf ein Feld verschoben wird, auf dem eine gegnerische " +
						"Spielfigur steht, wird diese gefangen genommen. Bei der Gefangennahme des " +
						" gegnerischen Königs ist das Spiel beendet.</td>" +
					"</tr>" +
				"</table>" +
			"</html>");
	  	
	  	// Größe des Scrollpanes bestimmen
	  	jScrollPane.setBounds(new Rectangle(40, 30, 150, 50));
	  	// JTExtPane dem Scrollpane hinzufügen
	  	jScrollPane.getViewport().add(jTextPane, null);
	  	  	
	  	// ScrollPane dem contentPane hinzufügen
	  	this.add(jScrollPane, null);	
	}
	
	/**
	 * Blendet die Spielanleitung zentriert vom Hauptfenster ein
	 */
	public void display() {
		// Position des JFrames errechnen
		int top  = this.parentFrame.getY() + ((this.parentFrame.getHeight() - this.getHeight()) / 2);
		int left = this.parentFrame.getX() + ((this.parentFrame.getWidth() - this.getWidth()) / 2);
			
		// Position zuordnen und Fenster zeigen
		this.setLocation(left, top);
		
		this.setVisible(true);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		this.game.refreshWindow();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		this.game.refreshWindow();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		this.game.refreshWindow();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		this.game.refreshWindow();
	}
	
}
