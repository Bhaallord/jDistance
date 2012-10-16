package jDistance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 * Diese Klasse stellt die Menüleiste für das Hauptfenster inklusive Eventbehandlung
 * zur Verfügung
 */
public class MenuBar extends JMenuBar implements ActionListener, MouseListener  {

	private static final long serialVersionUID = 5467054303299570641L;
	
	private JDistance	game;
	private JFrame		parentFrame;
	
	/**
	 * Erzeugt die Menüleiste des Hauptfensters
	 * @param game Referenz auf das JDistance Objekt
	 * @param parentFrame Referenz auf das Hauptfenster
	 */
	public MenuBar (JDistance game, JFrame parentFrame) {
	    super();
	
	    this.game			= game;
	    this.parentFrame	= parentFrame;
	    
		// Menüs anlegen
		JMenu gameMenu = new JMenu ("Spiel");
		JMenu helpMenu = new JMenu ("Hilfe");
	
		// Menüeinträge anlegen
		JMenuItem newGameItem = new JMenuItem ("Neues Spiel starten");
		JMenuItem settingsItem = new JMenuItem ("Einstellungen");
		JMenuItem exitItem = new JMenuItem ("Schließen");
		
		JMenuItem instructionsItem = new JMenuItem ("Anleitung");
		JMenuItem aboutItem = new JMenuItem ("Über");
		

		//MenuItems Namen geben, um sie in der ActionPerformed Methode zuordnen zu können
		newGameItem.setName("newGameItem");
		settingsItem.setName("settingsItem");
		exitItem.setName("exitItem");
		
		instructionsItem.setName("instructionsItem");
		aboutItem.setName("aboutItem");
		
		//Hotkeys hinzufügen
		newGameItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		settingsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		exitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
        instructionsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		
        
		// Den Einträgen dem Menü hinzufügen
		gameMenu.add (newGameItem);
		gameMenu.add (settingsItem);
		gameMenu.add(new JSeparator());
		gameMenu.add (exitItem);
		
		helpMenu.add(instructionsItem);
		helpMenu.add(aboutItem);
		
		// Die Menüs der Leiste hinzufügen
		this.add(gameMenu);
		this.add(helpMenu);
		
		// ActionListener setzen
		newGameItem.addActionListener(this);
		settingsItem.addActionListener(this);
		exitItem.addActionListener(this);
		instructionsItem.addActionListener(this);
		aboutItem.addActionListener(this);
		
		gameMenu.addMouseListener(this);
		helpMenu.addMouseListener(this);
		
		newGameItem.addMouseListener(this);
		settingsItem.addMouseListener(this);
		exitItem.addMouseListener(this);
		instructionsItem.addMouseListener(this);
		aboutItem.addMouseListener(this);
		
		
	}

	/**
	 * Verarbeitet Events auf die Menüeinträge
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if (source instanceof JMenuItem){
			JMenuItem item = (JMenuItem) source;
			
			if (item.getName().equals("newGameItem")) 
				newGameItemClicked(event);
			else if (item.getName().equals("settingsItem"))
				settingsItemClicked(event);
			else if (item.getName().equals("exitItem"))
				exitItemClicked(event);
			else if (item.getName().equals("instructionsItem"))
				instructionsItemClicked(event);
			else if (item.getName().equals("aboutItem"))
				aboutItemClicked(event);
		}
	}
	
	private void exitItemClicked(ActionEvent evt) {                                           
		System.exit(0);
	} 
	
	private void newGameItemClicked(ActionEvent evt) {
		this.game.createGame();
    }
	
	private void settingsItemClicked(ActionEvent evt) {
		this.game.showOptions();
	}
	
	private void instructionsItemClicked(ActionEvent evt) {
    	this.game.showInstructions();
	}
	
	private void aboutItemClicked(ActionEvent evt) {
		String msg;
		
		msg  = "j3Distance Version 0.0.0.1 pre-Alpha\n";
		msg += "Informatik IIb APL, SS11\n\n";
		msg += "Erstellt von ET/TI Set 12\n\n";
		msg += "Ludwig, Marc\n";
		msg += "Hohlfeld, Marvin\n";
		msg += "Bukovsky, Matthias\n";
		msg += "Springstein, Matthias\n";
		
		JOptionPane.showMessageDialog(this.parentFrame,
			    msg, "Über j3Distance...", JOptionPane.INFORMATION_MESSAGE);
		
		this.game.refreshWindow();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.game.refreshWindow();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.game.refreshWindow();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.game.refreshWindow();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}

