package jDistance.gui3d;

import jDistance.model.Field;
import javax.media.j3d.TransformGroup;

/**
 * Speichert die TransformGroup und die Daten für ein Field Shape
 * 
 */
public class GuiFieldData {
	private Field field;
	private TransformGroup group;
	/**
         * Standartkonstruktor
         * @param field PlayingGround Field  
         * @param group TransformationGroup
         */
	public GuiFieldData(Field field,TransformGroup group){
		this.field=field;
		this.group=group;
	}
	/**
         * Gibt das gespeicherte Field zurück
         * @return Field des Shape
         */
	public Field getField(){
		return this.field;
	}
	
	/**
         * Gibt die gespeicherte TransformGroup zurück
         * @return TransformGroup des Shape
         */
	public TransformGroup getTransformGroup(){
		return this.group;
	}

}