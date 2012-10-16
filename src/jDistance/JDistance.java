package jDistance;

import jDistance.settings.Settings;
import jDistance.MenuBar;
import jDistance.ai.*;
import jDistance.settings.Settings.PlayerType;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jDistance.controller.GameController;
import jDistance.gui.Distance2D;
import jDistance.gui.GUI;
import jDistance.gui.GUI.PlayerMode;
import jDistance.gui3d.Distance3D;
import jDistance.util.CloseEvent;
import jDistance.util.StartGameEvent;
import jDistance.util.WinEvent;
import java.awt.*;

public class JDistance extends jDistance.util.MessageHandler implements Runnable, java.awt.event.ActionListener, WindowListener, ComponentListener  {
	
	private OptionDialog optionJDialog;
	private InstructionsDialog instructionsJDialog;
	private JFrame mainJFrame;
	
	private Settings gameSettings = null;
	
	private GameController gameController;
	private Thread gameControllerThread;
	
	private GUI			activeGui;
	private Distance2D	gui2DObject;
	private Distance3D	gui3DObject;
	
	
	private AiController ai1Object;
	private AiController ai2Object;
	
	private SplashScreen splashScreen;
	
	/**
	 * Konstruktor der Hauptklasse
	 * 
	 * Erzeugt Instanzen aller anderen Klassen, verwaltet das Hauptfenster
	 * und übernimmt Steueraufgaben 
	 */
	public JDistance () {
		
		// Splash-Screen anzeigen
		this.splashScreen = new SplashScreen();
        this.splashScreen.display();
		
		// Spieleinstellungen laden...
		this.gameSettings = new Settings();
		
		// Hauptfenster erzeugen
		mainJFrame = new JFrame ("j3Distance v.0.0.1 pre-alpha");
		mainJFrame.setContentPane(new JPanel(new BorderLayout()));
                
		Image icon = Toolkit.getDefaultToolkit().getImage("img/icon.png");
		mainJFrame.setIconImage(icon);
		mainJFrame.setResizable(false);
		
		mainJFrame.addWindowListener(this);
		mainJFrame.addComponentListener(this);
		
		// Optiondialog instanzieren
		this.optionJDialog = new OptionDialog(this, this.mainJFrame, this.gameSettings);	
		
		//Spielanleitung instanzieren
		this.instructionsJDialog = new InstructionsDialog(this, this.mainJFrame);
		// MenüBar laden
		mainJFrame.setJMenuBar( new MenuBar(this, this.mainJFrame) );

		mainJFrame.setResizable(false);
		mainJFrame.setVisible(false);
				
		// GameController instanzieren und diese Instanz als Listener setzen
		this.gameController 		= new GameController();
		this.gameControllerThread 	= new Thread(gameController);
		gameControllerThread.start();
		
		// Diese JDistance-Instanz mit der Event-Queue verbinden...
		this.connect(gameController);
		
		// Grafik-Engine instanzieren
		this.createEngine();
		
		this.run();
	}

	/**
	 * Hauptfunktion von j3Distance
	 * 
	 * Instanziert das JDistance Objekt und startet den Hauptthread
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String... args) throws IOException {
		JDistance game = new JDistance();
		new Thread(game).start();
	}
	
	/**
	 * Zeichnet die Komponenten des Hauptfensters neu
	 */
	public void refreshWindow() {
		if (this.activeGui instanceof Distance3D) {
			this.mainJFrame.getContentPane().paintComponents(this.mainJFrame.getContentPane().getGraphics());
			this.mainJFrame.repaint();
		}
	}

	/**
	 * Blendet den Optionsdialog ein
	 */
	public void showOptions() {
		this.optionJDialog.display();
	}
	
	/**
	 * Blendet die Spielanleitung ein
	 */
	public void showInstructions() {
		this.instructionsJDialog.display();
	}
	
	/**
	 * Erzeugt eine Instanz der ausgewählten GUI und stellt diese im Hauptfenster dar.
	 * 
	 * Diese Methode wird außerdem nach Änderung der GUI in den Optionen aufgerufen,
	 * um alte GUI Instanzen saubere von der MessageQueue zu trennen und zu beenden.
	 * 
	 * Diese Methode setzt außerdem die Hauptfenstergröße und Position abhängig von der GUI.
	 */
	protected void createEngine () {
		
		this.mainJFrame.setVisible(false);
		this.splashScreen.display();
		
		// Alte GUI-Instanzen entfernen
		if (this.gui2DObject != null) {
			this.gui2DObject.disconnect(this.gameController);
			this.gui2DObject.clearQueue();
			this.gui2DObject.addEvent(new CloseEvent());
			this.gui2DObject = null;
		}
		
		if (this.gui3DObject != null) {
			this.gui3DObject.disconnect(this.gameController);
			this.gui3DObject.clearQueue();
			this.gui3DObject.addEvent(new CloseEvent());
			this.gui3DObject = null;
		}
		
		// Hauptcontainer holen und leeren...
		Container mainContainer = mainJFrame.getContentPane();
		mainContainer.removeAll();
		
		int panelWidth = 0;
		int panelHeight = 0;
		
		//ließt die Variable "is2D" der Settings Klasse aus und
		//startet die 2DGui (true) oder 3DGui (false)		
		
		if (gameSettings.is2D == true) {	// 2D GUI starten
			
			panelWidth = 700;
			panelHeight = 700;
			
			this.gui2DObject = new Distance2D();
			this.gui2DObject.connect(this.gameController);
			new Thread(this.gui2DObject).start();
			
			this.gui2DObject.getPanel().setPreferredSize(new Dimension(panelWidth, panelHeight));
			mainContainer.add(this.gui2DObject.getPanel());
			
			this.activeGui = this.gui2DObject;
			
		} else {	// 3D GUI starten
			
			panelWidth = 1000;
			panelHeight = 700;
			
			this.gui3DObject = new Distance3D();
		    this.gui3DObject.connect(this.gameController);
		    new Thread(this.gui3DObject).start();
		    
		    this.gui3DObject.getCanvas3D().setPreferredSize(new Dimension(panelWidth, panelHeight));
		    mainContainer.add(this.gui3DObject.getCanvas3D());
		    
		    this.activeGui = this.gui3DObject;
		}
		
		// Größe des Hauptfenster-Inhalts setzen
		mainContainer.setPreferredSize(new Dimension(panelWidth, panelHeight));
		
		// Fenster und Componenten erneuern
		mainJFrame.validate();
		mainJFrame.pack();
		
		// Größe des Bildschirms ermitteln
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		// Position des JFrames errechnen
		int top = (screenSize.height - mainJFrame.getHeight()) / 2;
		int left = (screenSize.width - mainJFrame.getWidth()) / 2;

		// Position zuordnen
		mainJFrame.setLocation(left, top);
		
		this.splashScreen.close();
		mainJFrame.setVisible(true);
	}
	
	/**
	 * Erzeugt erforderliche AI Instanzen für eingestellte Computergegner abhängig
	 * vom gewählten Schwierigkeitsgrad.
	 * 
	 * Bereits bestehende Instanzen werden sauber von der MessageQueue getrennt und
	 * entfernt.
	 * 
	 */
	private void createKI () {
		// Alte KI-Instanzen entfernen
		if (this.ai1Object != null) {
			this.ai1Object.disconnect(this.gameController);
			this.ai1Object.clearQueue();
			this.ai1Object.addEvent(new CloseEvent());
			this.ai1Object = null;
		}
			
		if (this.ai2Object != null) { 
			this.ai2Object.disconnect(this.gameController);
			this.ai2Object.clearQueue();
			this.ai2Object.addEvent(new CloseEvent());
			this.ai2Object = null;
		}
		
		if (!this.gameSettings.whitePlayerType.equals(PlayerType.HUMAN)) {
			// Spieler 1 wird von KI gesteuert
			switch (this.gameSettings.whitePlayerType) {
			
				case AI_EASY :
					this.ai1Object = new EasyAiController(1);
					break;
					
				case AI_NORMAL :
					this.ai1Object = new MediumAiController(1);
					break;
					
				case AI_HARD :
					this.ai1Object = new ExpertAiController(1);
					break;
			}
			
			// KI starten
			this.ai1Object.connect(this.gameController);
			new Thread(ai1Object).start();
		}
		
		
		if (!this.gameSettings.blackPlayerType.equals(PlayerType.HUMAN)) {
			// Spieler 2 wird von KI gesteuert
			switch (this.gameSettings.blackPlayerType) {
			
				case AI_EASY :
					this.ai2Object = new EasyAiController(2);
					break;
					
				case AI_NORMAL :
					this.ai2Object = new MediumAiController(2);
					break;
					
				case AI_HARD :
					this.ai2Object = new ExpertAiController(2);
					break;
			}
			
			// KI starten
			this.ai2Object.connect(this.gameController);
			new Thread(ai2Object).start();
		}
		
	}
	
	/**
	 * Erzeugt ein neues Spiel anhand der aktuellen Spieleinstellungen
	 */
	protected void createGame() {
		
		this.activeGui.clearQueue();
		
		// KI erzeugen
		this.createKI();
		
		// GUI Spielermodus setzen
		boolean guiIsWhite = gameSettings.whitePlayerType.equals(PlayerType.HUMAN);
		boolean guiIsBlack = gameSettings.blackPlayerType.equals(PlayerType.HUMAN);
		
		PlayerMode mode = PlayerMode.NONE;
		
		if (guiIsWhite && guiIsBlack) {
			mode = PlayerMode.BOTH;
		} else {
			if (guiIsWhite) mode = PlayerMode.WHITE_ONLY;
			if (guiIsBlack) mode = PlayerMode.BLACK_ONLY;
		}
		
		this.activeGui.setPlayerMode(mode);
		
		// Spiel (neu) starten
		this.sendMessage(new StartGameEvent(1));
	}

	/**
	 * Run-Methode für den JDistance Thread; Behandelt eingehende Events
	 */
	public void run () {
		this.createGame();
		 
		while ( (!Thread.currentThread().isInterrupted()) )  {
			
			jDistance.util.Event event = getNextMessage();
			
			if (event instanceof WinEvent) {
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {}
				
				WinEvent e = (WinEvent) event;
				String msg = "";
				
				msg = "Spieler "+((e.getPlayer() == 1) ? "Weiß" : "Schwarz")+" hat gewonnen.";
				
				JOptionPane.showMessageDialog(this.mainJFrame,
					    msg, "Spielende", JOptionPane.PLAIN_MESSAGE);
			}
			
		}
	}
	
	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	/**
	 * Verarbeitet Event 'Fenster schließen' und beendet das Programm
	 */
	@Override
	public void windowClosing(WindowEvent e) {	
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	/**
	 * Neuzeichnen des Fensterinhaltes bei Reaktivierung des Fensters
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		this.refreshWindow();
		try {
			Thread.sleep(100);
			this.refreshWindow();
		} catch (Exception ex) {ex.printStackTrace();}
	}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}

	/**
	 * Neuzeichnen des Fensterinhaltes bei Verschieben des Fensters
	 */
	@Override
	public void componentMoved(ComponentEvent e) {
		this.refreshWindow();
	}

	@Override
	public void componentResized(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {}


}
