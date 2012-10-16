package jDistance.gui;

//Häufig benutzte packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import jDistance.model.*;
import jDistance.util.*;
import jDistance.gui.GuiToken;
import jDistance.gui.ImageHandler.imageType;

public class Distance2D extends GUI implements java.awt.event.ActionListener, java.awt.event.MouseListener {

	/**
	 * Generierte Serial UID
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 5271858235461690667L;

	// ID des Spielers, der aktuell an der Reihe ist
	private int currentPlayerId = 0;
	
	private boolean myTurn = false;
	private List<TokenMove> possibleMoves;
	private GuiToken selectedGuiToken = null;

	private Integer fieldLayerPos = Integer.valueOf(0);
	private Integer markerLayerPos = Integer.valueOf(3);
	private Integer tokenLayerPos = Integer.valueOf(5);

	// Spielfeldbreite
	private int fieldWidth = 65;

	private int layerOffsetX = 90;
	private int layerOffsetY = 90;

	// LayeredPane
	private javax.swing.JLayeredPane layer;
	private List<GuiToken> tokens = new ArrayList<GuiToken>();
	
	// Weitere Komponenten
	private List<JLabel> fieldMarkers = new ArrayList<JLabel>();
	private List<String> messageQueue = new ArrayList<String>();
	private JPanel messageArea;
	

	// Das Hauptpanel
	private javax.swing.JPanel panel = null;

	private String currentColor;

	/**
	 * Konstruktor der GUI Klasse; erstellt ein neues Spielfeld
	 */
	public Distance2D() {
		this.createAndShowGUI();
	}
	
	/**
	 * Erstellt die Spielfiguren (GuiToken) anhand der übergebenen Token Liste
	 * @param tokenList Liste aller Token, für die ein GuiToken erstellt werden soll
	 */
	private void createFigures(List<Token> tokenList) {
		
		// Alle alten Spielfiguren entfernen
		for (GuiToken token : this.tokens) layer.remove((Component)token);
		this.tokens.clear();
		
		this.layer.repaint();
		
		// Spielfiguren neu anlegen
		for (Token token : tokenList)
			new GuiToken(token, this);
		
		this.layer.validate();
		this.layer.repaint();
	}

	/**
	 * Zeichnet das Spielfeld und zugehörige Komponenten
	 */
	private void createAndShowGUI() {

		 /* Erzeugt das Spielfeld, verwendet wird hierfür ein
		 * javax.swing.JLayerdPane. Die Spielfelder werden Durch die interne
		 * Klasse guiToken repräsentiert, welche von javax.swing.JLabel
		 * abgeleitet ist.
		 */
		boolean odd = true;
		// beinhaltet das Spielfeld und die angezeigten Figuren
		this.layer = new javax.swing.JLayeredPane();

		this.layer.setSize(this.fieldWidth * 8, this.fieldWidth * 8);

		int x = Distance2D.this.layerOffsetX;
		int y = Distance2D.this.layerOffsetY;

		for (int i = 0; i < 8; i++, y += this.fieldWidth) {
			for (int j = 0; j < 8; j++, x += this.fieldWidth) {
				Image field = null;
				if (odd == true) {
					try{
						field = ImageHandler.getImage(imageType.BRIGHT);
					} catch(IOException e){
						System.err.println(e.toString());
					}
					odd = false;
				} else {
					try{
						field = ImageHandler.getImage(imageType.DARK);
					} catch(IOException e){
						System.err.println(e.toString());
					}
					odd = true;
				}
				JLabel addMe = new JLabel(); // neues Label
				addMe.setBounds(x, y, this.fieldWidth, this.fieldWidth); // rechteckig,
																			// nach
																			// den
																			// system
																			// Vorgaben
				addMe.setIcon(new ImageIcon(field)); // Oberfläche des
														// Spielfeldes
				addMe.setDoubleBuffered(true); // verhindert Flimmern beim
												// neuzeichnen
				addMe.setName("field");
				addMe.setOpaque(true);
				this.layer.add(addMe, this.fieldLayerPos);

				addMe.addMouseListener(this);

			}
			odd = !odd;
			x = Distance2D.this.layerOffsetX;
		}

		this.layer.validate();
		/*
		 * Das Oberste Panel. Es beinhaltet das Spielfeld (JLayeredPane) und
		 * stellt für eventuelle Änderungen weitere Panel zur Verfügung
		 * (BorderLayout).
		 */
		this.panel = new javax.swing.JPanel();

		// Zeichne Rahmen/Spielbrett um die Felder...
		try{
			JLabel borderLabel = new JLabel(new ImageIcon(ImageHandler.getImage(imageType.BORDER)));
			borderLabel.setBounds(0, 0, 700, 700);

			borderLabel.setOpaque(true);
			borderLabel.validate();
			this.layer.add(borderLabel, this.fieldLayerPos);
		}catch(IOException e){
			System.err.println(e.toString());
		}

		// Füge Message-Area hinzu...
		this.messageArea = new JPanel(new GridLayout(3, 1));
		this.messageArea.setBounds(40, 635, 620, 60);
		this.messageArea.setOpaque(false);
		this.layer.add(messageArea, JLayeredPane.DRAG_LAYER);

		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(700, 700));
		panel.add(this.layer);

	}

	/**
	 * Gibt eine Textmeldung im unteren Bereich des Spielfeldes aus.
	 * 
	 * Bereits bestehende Meldungen werden nach unten verschoben und 
	 * stufenweise ausgegraut.
	 * 
	 * @param message Hinzuzufügende Textmeldung
	 */
	private void printMessage(String message) {
		this.messageQueue.add(message);
		this.messageArea.removeAll();

		ListIterator<String> it = this.messageQueue
				.listIterator((this.messageQueue.size() > 0 ? this.messageQueue
						.size() : 0));

		int i = 0;
		while (it.hasPrevious() && i < 3) {
			JLabel line = new JLabel();
			line.setHorizontalAlignment(JLabel.CENTER);
			line.setVerticalAlignment(JLabel.TOP);
			line.setForeground(new Color(255 - 70 * i, 255 - 70 * i,
					255 - 70 * i));
			line.setFont(new Font("Dialog", Font.BOLD, 14));
			line.setText(it.previous());

			this.messageArea.add(line);

			i++;
		}

		this.messageArea.validate();
	}

	/**
	 * Durchsucht die interne Replikation der aufgestellten Spielfiguren nach
	 * einem dazugehörigen GuiToken
	 * 
	 * @param token Das zum gesuchten GuiToken dazugehörige Token
	 * @return GuiToken Das dazugehörige GuiToken (grafische Spielfigur)
	 * @throws NoSuchElementException
	 */
	private synchronized GuiToken findToken(Token token)
			throws NoSuchElementException {
		for (GuiToken guiToken : this.tokens) {
			if (guiToken.compareToToken(token) == true)
				return (guiToken);
		}
		throw new NoSuchElementException("Token nicht vorhanden!");
	}

	private void updateFieldMarkers() {
		for (JLabel marker : this.fieldMarkers) {
			this.layer.add(marker);
			this.layer.setLayer(marker, this.markerLayerPos);
			this.layer.moveToFront(marker);

		}

		this.layer.repaint();
	}

	private void removeFieldMarkers() {
		for (JLabel marker : this.fieldMarkers) {
			this.layer.remove(marker);
		}

		this.layer.repaint();

		this.fieldMarkers.clear();
	}
	
	/**
	 * Markiert ein GuiToken als ausgewählt / Löscht markierungen
	 * @param guiToken Zu markierendes Token, 'null' wenn kein Token ausgewählt
	 */
	private void setSelectedToken(GuiToken guiToken) {
		this.selectedGuiToken = guiToken;

		this.removeFieldMarkers();

		if (guiToken != null) {

			JLabel activeMarker = null;
			try{
				activeMarker = new JLabel(new ImageIcon(ImageHandler.getImage(imageType.FIELDMARKERSELECTEDTOKEN)));
			}catch(IOException e){
				System.err.println(e.toString());
				}
			
			activeMarker.setSize(guiToken.getSize());
			activeMarker.setLocation(guiToken.getLocation());

			this.fieldMarkers.add(activeMarker);

			for (TokenMove move : this.possibleMoves) {
				if (this.selectedGuiToken.compareToToken(move.getToken())) {
					try{
						JLabel targetMarker = new JLabel(new ImageIcon(ImageHandler.getImage(imageType.FIELDMARKERSELECTEDMOVE)));
						targetMarker.setSize(guiToken.getSize());
						targetMarker.setLocation(this.fieldToPoint(move.getDestination()));
						this.fieldMarkers.add(targetMarker);
					} catch (IOException e){
						System.err.println(e.toString());
					}
				}
			}

			this.updateFieldMarkers();
			log("Token "+guiToken.getToken().toString()+" is now marked as selected.");
		}
	}

	/**
	 * Setzt den Status eines Tokens auf gefangen (locked). Wird kein
	 * vergleichbares Token in der Klasseninternen repräsentation gefunden, wird
	 * dies über die MessageQueue mitgeteilt.
	 * 
	 * @param event TokenLockedEvent
	 */
	synchronized private void processTokenLockedEvent(TokenLockedEvent event) {
		log("processTokenLockedEvent: "+event.toString());
		try {
			GuiToken token = this.findToken(event.getToken());
			token.setLocked(!event.getToken().isLocked());
			token.draw();
		} catch (NoSuchElementException e) {
			log("FEHLER: processTokenLockedEvent: Token nicht gefunden.");
			log(e.toString());
		}
	}

	/**
	 * Bewegt ein Token (Spielfigur) auf dem Spielfeld
	 * @param event TokenMovedEvent
	 */
	synchronized private void processTokenMovedEvent(TokenMovedEvent event) {
		log("processTokenMovedEvent: "+event.toString());
		try {
			GuiToken token = this.findToken(event.getToken());
			token.move(event.getDestination());
		} catch (NoSuchElementException e) {
			log("FEHLER: processTokenMovedEvent: Token nicht gefunden.");
			log(e.toString());
		}
	}

	/**
	 * Tauscht zwei Token miteinander
	 * @param event TokenSwapedEvent
	 */
	synchronized private void processTokenSwappedEvent(TokenSwapedEvent event) {
		log("processTokenSwappedEvent: "+event.toString());
		try {
			GuiToken firstToken = null;
			GuiToken secondToken = null;
			
			firstToken = this.findToken(event.getFirstTokenMove().getToken());
			secondToken = this.findToken(event.getSecondTokenMove().getToken());
			
			// Setzen der Spielfiguren
			firstToken.move(event.getFirstTokenMove().getDestination());
			
			if ((secondToken.getToken().getPlayer() != firstToken.getToken().getPlayer()) && secondToken.getToken() instanceof King) {
				// Gegnerischer König geschlagen -> König nicht wegbewegen, nur aktualisieren
				secondToken.getToken().move(event.getSecondTokenMove().getDestination());
			} else {
				secondToken.move(event.getSecondTokenMove().getDestination());
			}
		} catch (NoSuchElementException e) {
			log("FEHLER: processTokenSwappedEvent: Token nicht gefunden.");
			log(e.toString());
		}
	}

	/**
	 * Verlangt einen Spielzug vom Spieler, falls dieser an der Reihe ist
	 * @param event ExpectTokenMoveEvent
	 */
	synchronized private void processExpectTokenMoveEvent(ExpectTokenMoveEvent event) {
		
		this.currentPlayerId = event.getPlayer();
		this.currentColor = ((event.getPlayer() == 1) ? "Weiß" : "Schwarz");
		
		this.myTurn = this.isMyTurn(this.currentPlayerId);
			
		if (this.myTurn) {
				this.log("Erwarte Zug für Spieler "+ this.currentPlayerId);
				this.possibleMoves = event.getTokenMove();
				this.printMessage("Sie sind dran! (" + this.currentColor + ")");
		} else {
			this.printMessage("Spieler " + this.currentColor + " ist dran...");
		}
	}
	
	/**
	 * Teilt über den Spielausgang mit
	 * @param event WinEvent
	 */
	synchronized private void processWinEvent (WinEvent event) {
	
		if (this.getPlayerMode().equals(PlayerMode.BOTH) || this.getPlayerMode().equals(PlayerMode.NONE)) {
			printMessage("Spieler "+this.currentColor+" hat gewonnen...");
		} else {
			if ( (event.getPlayer() == 1 && this.playerMode.equals(PlayerMode.WHITE_ONLY)) ||
				 (event.getPlayer() == 2 && this.playerMode.equals(PlayerMode.BLACK_ONLY)) ) {
				printMessage("Sie haben gewonnen! :-)");
			} else {
				printMessage("Sie haben verloren... :-(");
			}
		}
		
		this.myTurn = false;
	}
	
	/**
	 * Bereitet das Spielfeld für ein neues Spiel vor
	 * @param event GameStartedEvent
	 */
	synchronized private void processGameStartedEvent (GameStartedEvent event) {
		
		this.createFigures(((jDistance.util.GameStartedEvent) event).getToken());
		this.messageQueue.clear();
		
		this.layer.validate();
		this.layer.repaint();
		
		log("Neues Spiel wurde gestartet.");
	}

	/**
	 * Die run() Methode wird vom Thread aufgerufen und verarbeitet eingehende Events
	 */
	@Override
	public void run() {
		while ( (!Thread.currentThread().isInterrupted()) )  {
			jDistance.util.Event event = getNextMessage();
			
			if (event instanceof CloseEvent) {
				return;
			}
			
			else if (event instanceof GameStartedEvent) {
				processGameStartedEvent((GameStartedEvent) event);
			}

			else if (event instanceof TokenMovedEvent) {
				processTokenMovedEvent((TokenMovedEvent) event);
			}

			else if (event instanceof TokenSwapedEvent) {
				processTokenSwappedEvent((TokenSwapedEvent) event);
			}

			else if (event instanceof TokenLockedEvent) {
				processTokenLockedEvent((TokenLockedEvent) event);
			}

			else if (event instanceof ExpectTokenMoveEvent) {
				processExpectTokenMoveEvent((ExpectTokenMoveEvent) event);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					return;
				}
			}
			
			else if (event instanceof WinEvent) {
				processWinEvent((WinEvent) event);
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	/**
	 * Generiert aus einem existenten jDistance.model.Field Objekt ein
	 * java.awt.Point Objekt.
	 * 
	 * @param field Zu wandelndes Field Objekt
	 * @return point Point Objekt
	 */
	public java.awt.Point fieldToPoint(jDistance.model.Field field) {
		return (new java.awt.Point(field.getX() * this.fieldWidth
				+ this.layerOffsetX, field.getY() * this.fieldWidth
				+ this.layerOffsetY));
	}

	/**
	 * Rückgabe der aktuellen Spielfeldbreite
	 * @return int Breite eines Spielfeldes in px
	 */
	public int getFieldWidth() {
		return(this.fieldWidth);
	}

	/**
	 * Gibt das Hauptpanel zurück 
	 * @return JPanel
	 */
	public JPanel getPanel (){
		return (this.panel);
	}
	
	
	/**
	 * Rückgabe des Hauptcontainers (JLayeredPane) für Spielfelder und -figuren
	 * @return JLayeredPane 
	 */
	public JLayeredPane getLayer(){
		return(this.layer);
	}
	
	/**
	 * Rückgabe des Layers (Z-Orientierung) der Spielfiguren
	 * @return Integer Z-Ebene der Spielfiguren
	 */
	public Integer getTokenLayerPos(){
		return(this.tokenLayerPos);
	}

	/**
	 * Rückgabe aller Spielfiguren (GuiToken) als Liste
	 * @return List<GuiToken> Liste aller GuiTokens
	 */
	public List<GuiToken> getTokens(){
		return(this.tokens);
	}
	
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {

	}

	/**
	 * Verarbeitet Mausklick Events (Spielfiguren-Auswahl, Spielzüge)
	 */
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

		if (!this.myTurn)
			return; // Ignoriere Klick, wenn nicht an der Reihe...

		JLabel clickedField = (JLabel) e.getSource();
		GuiToken clickedToken = null;

		// Teste ob Token angeklickt wurde...
		for (GuiToken guiToken : this.tokens) {
			if (guiToken.getLocation().equals(clickedField.getLocation())
					&& guiToken.getToken().getPlayer() == this.currentPlayerId) {
				clickedToken = guiToken;
			}
		}
		
		if (this.selectedGuiToken != null) {
			// Benutzer an der Reihe + Token ausgewählt + Feld angeklickt

			if (clickedField.getLocation().equals(
					this.selectedGuiToken.getLocation())) {
				// Ausgewählte Figur wieder angeklickt -> tue nichts.
				return;
			}

			// Teste ob Zielfeld ein valider Spielzug...
			boolean validMove = false;
			for (TokenMove move : this.possibleMoves) {
				if (move.getToken().equals(this.selectedGuiToken.getToken())
						&& clickedField.getLocation().equals(
								this.fieldToPoint(move.getDestination()))) {
					// Valider Spielzug -> Benachrichtige GameController...
					validMove = true;
					this.sendMessage(new MoveTokenEvent(this.currentPlayerId, move));
					break;
				}
			}

			if (!validMove && clickedToken != null
					&& clickedToken.getToken().getPlayer() == this.currentPlayerId) {
				// Kein gültiger Spielzug, jedoch anderes Token gewählt...
				this.setSelectedToken(clickedToken);

			} else {
				// Auf ungütiges Feld geklickt -> Hebe Spielfiguren-Auswahl
				// auf...
				this.setSelectedToken(null);
			}

		} else {
			// Benutzer an der Reihe, jedoch keine Spielfigur ausgewählt...
			if (clickedToken != null && clickedToken.getToken().getPlayer() == this.currentPlayerId) {
				// Eigenes Token wurde angeklickt -> Setze als ausgewählt...
				Distance2D.this.setSelectedToken(clickedToken);
				return;
			}
		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}
}
