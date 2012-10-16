/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.gui3d;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import javax.media.j3d.Shape3D;

/**
 * Hilfsklasse zum laden von Modellen
 * 
 */
public class ModelLoader {

    /**
     * Standartkonstruktor
     */
    private ModelLoader() {
    }

    /**
     * Lädt das 3D Model für den König
     * @return Model des Königs
     */
    public static Shape3D loadKing() {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        try {
            // TODO: Resourcen System für Java googlen
            scene = file.load("model/king2.obj");
        } catch (Exception e) {
            System.err.println("king2.obj konnte nich geladen werden: " + e.getMessage());
            return null;
        }
        java.util.Hashtable<String, Shape3D> objects = scene.getNamedObjects();

        return (Shape3D) ((Shape3D) objects.get("default")).cloneTree();
    }

    /**
     * Lädt das 3D Model für den Spielbrett
     * @return Model des Spielbrett
     */
    public static Shape3D loadBrett() {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        try {
            // TODO: Resourcen System für Java googlen
            scene = file.load("model/brett.obj");
        } catch (Exception e) {
            System.err.println("brett.obj konnte nich geladen werden: " + e.getMessage());
            return null;
        }
        java.util.Hashtable<String, Shape3D> objects = scene.getNamedObjects();

        return (Shape3D) ((Shape3D) objects.get("default")).cloneTree();
    }

    
    /**
     * Lädt das 3D Model für ein Feld
     * @return Model des Feldes
     */
    public static Shape3D loadField() {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        try {
            // TODO: Resourcen System für Java googlen
            scene = file.load("model/field.obj");
        } catch (Exception e) {
            System.err.println("field.obj konnte nich geladen werden: " + e.getMessage());
            return null;
        }
        java.util.Hashtable<String, Shape3D> objects = scene.getNamedObjects();

        return (Shape3D) ((Shape3D) objects.get("default")).cloneTree();
    }

    /**
     * Lädt das 3D Model für den Käfig
     * @return Model des Käfigs
     */
    public static Shape3D loadCage() {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        try {
            // TODO: Resourcen System für Java googlen
            scene = file.load("model/cage.obj");
        } catch (Exception e) {
            System.err.println("cage.obj konnte nich geladen werden: " + e.getMessage());
            return null;
        }
        java.util.Hashtable<String, Shape3D> objects = scene.getNamedObjects();

        return (Shape3D) ((Shape3D) objects.get("default")).cloneTree();
    }

    /**
     * Lädt das 3D Model für den Bauer
     * @return Model des Bauer
     */
    public static Shape3D loadSlave() {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        try {
            // TODO: Resourcen System für Java googlen
            scene = file.load("model/slave.obj");
        } catch (Exception e) {
            System.err.println("slave.obj konnte nich geladen werden: " + e.getMessage());
            return null;
        }

        java.util.Hashtable<String, Shape3D> objects = scene.getNamedObjects();

        return (Shape3D) ((Shape3D) objects.get("default")).cloneTree();
    }
    
    /**
     * Lädt das 3D Model für die Markierung 
     * @return Model der Markierung
     */
        public static Shape3D loadMark() {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        try {
            // TODO: Resourcen System für Java googlen
            scene = file.load("model/feldhighlight.obj");
        } catch (Exception e) {
            System.err.println("feldhighlight.obj konnte nich geladen werden: " + e.getMessage());
            return null;
        }

        java.util.Hashtable<String, Shape3D> objects = scene.getNamedObjects();

        return (Shape3D) ((Shape3D) objects.get("default")).cloneTree();
    }
            
    /**
     * Lädt das 3D Model für den Schatten der Spielfigur
     * @return Model des Schatten
     */
        public static Shape3D loadShade() {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        try {
            // TODO: Resourcen System für Java googlen
            scene = file.load("model/shade.obj");
        } catch (Exception e) {
            System.err.println("shade.obj konnte nich geladen werden: " + e.getMessage());
            return null;
        }

        java.util.Hashtable<String, Shape3D> objects = scene.getNamedObjects();

        return (Shape3D) ((Shape3D) objects.get("default")).cloneTree();
    }
}
