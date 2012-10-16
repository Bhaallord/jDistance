package jDistance;

import jDistance.settings.Settings;
import jDistance.settings.Settings.PlayerType;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Diese Klasse stellt den Optionsdialog für Spieleinstellungen inklusive 
 * Eventbehandlung zur Verfügung
 */
public class OptionDialog extends JDialog implements ActionListener, ComponentListener {

	private static final long serialVersionUID = 1L;
	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;
	private JButton saveButton;
	private JButton closeButton;
	private JPanel grafikOptionPanel;
	private JPanel playerpanel1;
	private JPanel playerpanel2;
	private JPanel confirmPanel;
	private JRadioButton humanRadioButton1;
	private JRadioButton computerEasyRadioButton1;
	private JRadioButton computerMediumRadioButton1;
	private JRadioButton computerHardRadioButton1;
	private JRadioButton humanRadioButton2;
	private JRadioButton computerEasyRadioButton2;
	private JRadioButton computerMediumRadioButton2;
	private JRadioButton computerHardRadioButton2;
	private JRadioButton _2DRadioButton;
	private JRadioButton _3DRadioButton;
	private JCheckBox antialiasingCheckBox;
	
	private JDistance	game;
	private JFrame 		parentFrame;
	private Settings	gameSettings;
	
	/**
	 * Erzeugt den Optionsdialog für das Hauptfenster
	 * @param game Referenz auf das JDistance Objekt
	 * @param parentFrame Referenz auf das Hauptfenster
	 * @param gameSettings Referenz auf die Spieleinstellungen (Model)
	 */
	public OptionDialog (JDistance game, JFrame parentFrame, Settings gameSettings)  {
		
		super(parentFrame, "j3Distance Einstellungen", Dialog.ModalityType.APPLICATION_MODAL);
		
		this.game			= game;
		this.parentFrame 	= parentFrame;
		this.gameSettings 	= gameSettings;
		
		
		//Eigenschaften des JDialog setzen
		this.setResizable(false);
		
		this.addComponentListener(this);
		
		Container optionContainer = this.getContentPane();
		
		optionContainer.setLayout(new BorderLayout());
		
		//------------Grafikoptionen-----------------
		grafikOptionPanel = new JPanel();
		grafikOptionPanel.setLayout(new BoxLayout(grafikOptionPanel, BoxLayout.X_AXIS));

		_2DRadioButton = new JRadioButton("2D Modus  ", gameSettings.is2D);
		_3DRadioButton = new JRadioButton("3D Modus  ", !gameSettings.is2D);
		antialiasingCheckBox = new JCheckBox("Antialiasing",gameSettings.antialiasing);
		
		antialiasingCheckBox.setEnabled((_3DRadioButton.isSelected()));
		
		_3DRadioButton.setName("3DRadioButton");
		_2DRadioButton.setName("2DRadioButton");
		
		buttonGroup1 = new ButtonGroup();
		//fügt die RadioButtons der zuvor erstellten ButtonGroup hinzu.
		buttonGroup1.add(_2DRadioButton);
		buttonGroup1.add(_3DRadioButton);
		
		grafikOptionPanel.add(_2DRadioButton);
		grafikOptionPanel.add(_3DRadioButton);
		grafikOptionPanel.add(antialiasingCheckBox);
		
		//Setzt ein Rahmen um Die ButtonGroup und setzt ein Text
		grafikOptionPanel.setBorder(BorderFactory.createTitledBorder(
		           BorderFactory.createEtchedBorder(), "Grafik"));	
		
		//------------Spieler Weiß option----------
		playerpanel1 = new JPanel();
		playerpanel1.setLayout(new BoxLayout(playerpanel1, BoxLayout.Y_AXIS));
	
		humanRadioButton1 = new JRadioButton("Mensch",  
				(gameSettings.whitePlayerType.equals(PlayerType.HUMAN)) );
		
		computerEasyRadioButton1 = new JRadioButton("Computer - Leicht  ",
				(gameSettings.whitePlayerType.equals(PlayerType.AI_EASY)) ); 
		
		computerMediumRadioButton1 = new JRadioButton("Computer - Mittel  ",
				(gameSettings.whitePlayerType.equals(PlayerType.AI_NORMAL)) ); 
		
		computerHardRadioButton1 = new JRadioButton("Computer - Schwer  ",
				(gameSettings.whitePlayerType.equals(PlayerType.AI_HARD)) ); 

		
		buttonGroup2 = new ButtonGroup();
		//fügt die RadioButtons der zuvor erstellten ButtonGroup hinzu.
		buttonGroup2.add(humanRadioButton1);
		buttonGroup2.add(computerEasyRadioButton1);
		buttonGroup2.add(computerMediumRadioButton1);
		buttonGroup2.add(computerHardRadioButton1);
		
		playerpanel1.add(humanRadioButton1);
		playerpanel1.add(computerEasyRadioButton1);
		playerpanel1.add(computerMediumRadioButton1);
		playerpanel1.add(computerHardRadioButton1);
		//Setzt ein Rahmen um Die ButtonGroup und setzt ein Text
		playerpanel1.setBorder(BorderFactory.createTitledBorder(
		           BorderFactory.createEtchedBorder(), "Spieler Weiß"));	
		
		//------------Spieler Schwarz option----------
		playerpanel2 = new JPanel();
		playerpanel2.setLayout(new BoxLayout(playerpanel2, BoxLayout.Y_AXIS));
		
		humanRadioButton2 = new JRadioButton("Mensch",  
				(gameSettings.blackPlayerType.equals(PlayerType.HUMAN)) );
		
		computerEasyRadioButton2 = new JRadioButton("Computer - Leicht  ",
				(gameSettings.blackPlayerType.equals(PlayerType.AI_EASY)) ); 
		
		computerMediumRadioButton2 = new JRadioButton("Computer - Mittel  ",
				(gameSettings.blackPlayerType.equals(PlayerType.AI_NORMAL)) ); 
		
		computerHardRadioButton2 = new JRadioButton("Computer - Schwer  ",
				(gameSettings.blackPlayerType.equals(PlayerType.AI_HARD)) ); 

		
		buttonGroup2 = new ButtonGroup();
		//fügt die RadioButtons der zuvor erstellten ButtonGroup hinzu.
		buttonGroup2.add(humanRadioButton2);
		buttonGroup2.add(computerEasyRadioButton2);
		buttonGroup2.add(computerMediumRadioButton2);
		buttonGroup2.add(computerHardRadioButton2);
		
		playerpanel2.add(humanRadioButton2);
		playerpanel2.add(computerEasyRadioButton2);
		playerpanel2.add(computerMediumRadioButton2);
		playerpanel2.add(computerHardRadioButton2);
		//Setzt ein Rahmen um Die ButtonGroup und setzt ein Text
		playerpanel2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Spieler Schwarz"));


		//---------Buttons--------------
		confirmPanel = new JPanel();
		saveButton = new JButton("Speichern");
		closeButton = new JButton("Abbrechen");
		
		/**
		 * beim Drücken des "Speichern" Buttons, werden die aktuellen selektierten
		 * Werte in die Varriablen der Settings Klasse übergeben.
		 */
		saveButton.addActionListener(this);	
		
		/**
		 * beim Drücken des "Abbrechen" Buttons, wird das Optionsframe geschlossen.
		 */
		closeButton.addActionListener(this);
		
		_3DRadioButton.addActionListener(this);
		_2DRadioButton.addActionListener(this);
		
		saveButton.setName("saveButton");
		closeButton.setName("closeButton");
		confirmPanel.add(saveButton);
		confirmPanel.add(closeButton);
		
	
		//zu dem JFrame JPannels hinzufügen und ausrichten
		optionContainer.add(BorderLayout.NORTH, grafikOptionPanel);
		//optionContainer.add(BorderLayout.NORTH, difficultySlider);
		optionContainer.add(BorderLayout.WEST, playerpanel1);
		optionContainer.add(BorderLayout.EAST, playerpanel2);
		optionContainer.add(BorderLayout.SOUTH, confirmPanel);
		
		this.validate();
		this.pack();
	}

	/**
	 * Blendet das Optionsfenster zentriert vom Hauptfenster ein
	 */
	public void display() {
		try {
			// Position des JFrames errechnen
			int top  = this.parentFrame.getY() + ((this.parentFrame.getHeight() - this.getHeight()) / 2);
			int left = this.parentFrame.getX() + ((this.parentFrame.getWidth() - this.getWidth()) / 2);
			
			// Position zuordnen und Fenster zeigen
			this.setLocation(left, top);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		this.setVisible(true);
		this.game.refreshWindow();
	}
	
	/**
	 * Verarbeitet Events des Optionsdialog
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source instanceof JButton){
			JButton button = (JButton) source;
			
			if 		(button.getName().equals("saveButton")) saveButtonClicked(e);
			else if (button.getName().equals("closeButton")) closeButtonClicked(e);
		}
		
		if (source instanceof JRadioButton){
			JRadioButton radio = (JRadioButton) source;
			
			if (radio.getName().equals("3DRadioButton") || radio.getName().equals("2DRadioButton")) 
				updateAntialiasingStatus(e);
		}	
	}
	
	/**
	 * Übernimmt die neuen Spieleinstellungen.
	 * 
	 * Löst ggf. einen Spielneustart oder das Neuanlegen der GUI aus
	 *  
	 * @param e ActionEvent
	 */
	private void saveButtonClicked(ActionEvent e) {

		Settings oldSettings = new Settings();
		
		//Optionsdialog entfernen
  		this.setVisible(false);
		
		this.gameSettings.is2D =_2DRadioButton.isSelected();
		this.gameSettings.antialiasing = antialiasingCheckBox.isSelected();
		
  		if (humanRadioButton1.isSelected() == true) {
  			this.gameSettings.whitePlayerType = PlayerType.HUMAN;
  		}
  		else if (computerEasyRadioButton1.isSelected() == true){
  			this.gameSettings.whitePlayerType = PlayerType.AI_EASY;
  		}
  		else if (computerMediumRadioButton1.isSelected() == true){
  			this.gameSettings.whitePlayerType = PlayerType.AI_NORMAL;
  		}
  		else if (computerHardRadioButton1.isSelected() == true){
  			this.gameSettings.whitePlayerType = PlayerType.AI_HARD;
  		}
		
  		if (humanRadioButton2.isSelected() == true) {
  			this.gameSettings.blackPlayerType = PlayerType.HUMAN;
  		}
  		else if (computerEasyRadioButton2.isSelected() == true){
  			this.gameSettings.blackPlayerType = PlayerType.AI_EASY;
  		}
  		else if (computerMediumRadioButton2.isSelected() == true){
  			this.gameSettings.blackPlayerType = PlayerType.AI_NORMAL;
  		}
  		else if (computerHardRadioButton2.isSelected() == true){
  			this.gameSettings.blackPlayerType = PlayerType.AI_HARD;

  		}
  		
  		this.gameSettings.writeSettings();
  		
  		//-- Apply Changes -----------------------------------
  		
  		if ( (oldSettings.is2D != this.gameSettings.is2D) || 
  			 ((!oldSettings.is2D) && (oldSettings.antialiasing != this.gameSettings.antialiasing)))
  		{
  			// Grafik-Einstellungen wurden geändert -> Engine neu starten
  			this.game.createEngine();
  			
  			// Neues Spiel starten
  			this.game.createGame();
  		}
  		
  		if ( (!oldSettings.whitePlayerType.equals(this.gameSettings.whitePlayerType)) ||
  			 (!oldSettings.blackPlayerType.equals(this.gameSettings.blackPlayerType)) )
  		{
  			// Spielereinstellungen wurden geändert -> Neues Spiel starten
  			this.game.createGame();
  		}

	}
	
	private void closeButtonClicked(ActionEvent e) {
    	  this.setVisible(false);
	}
	
	private void updateAntialiasingStatus(ActionEvent evt) {
		antialiasingCheckBox.setEnabled(_3DRadioButton.isSelected());
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
