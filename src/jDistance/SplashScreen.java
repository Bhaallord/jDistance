package jDistance;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Diese Klasse stellt den SplashScreen zur Verfügung, welcher bei Programmstart
 * und bei GUI-Wechsel eingeblendet wird
 */
public class SplashScreen extends JFrame {
    private static final long serialVersionUID = 5654240689518773770L;
 
    private BufferedImage image;
 
    private int w, h, x, y;
 
    /**
     * Konstruktor der SplashScreen-Klasse
     */
    public SplashScreen() {
        super("jDistance Splash");
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
        this.setUndecorated(true);
        
        try {
            image = ImageIO.read(new File("img/splashScreen.png"));
            w = image.getWidth();
            h = image.getHeight();
 
            setSize(w, h);   
        } catch (IOException e) {
            e.printStackTrace();
        }     
    }
    
    /**
     * Zeichnet den SplashScreen Inhalt (Splash Image) in den Frame
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, this);
    }
    
    /**
     * Stellt den SplashScreen auf dem Monitor zentriert dar
     */
    public void display() {
    	
    	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        
    	x = (d.width - w) / 2;
        y = (d.height - h) / 2;
    	
        setLocation(x,y);
        
        this.setVisible(true);
    	this.paintAll(this.getGraphics());
    	
    }
    
    /**
     * Blendet den SplashScreen aus
     */
    public void close() {
        this.setVisible(false);
    }
}