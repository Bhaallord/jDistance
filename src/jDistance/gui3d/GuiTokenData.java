package jDistance.gui3d;

import javax.media.j3d.TransformGroup;
import jDistance.model.*;
import javax.media.j3d.BranchGroup;

/**
 * Speichert die Daten für eine Spielfigur Shape
 * 
 */
public class GuiTokenData {
	private Token token;
	private TransformGroup transGroup;
        private BranchGroup branchGroup;
        private TransformGroup cageTransGroup;
        private boolean highlight;
	/**
         * Konstruktor
         * @param token Daten die das Shape speichert
         * @param transGroup TransformGroup des Shape
         * @param branchGroup BranchGroup des Shape
         */
	public GuiTokenData(Token token,TransformGroup transGroup, BranchGroup branchGroup){
		this.token=token;
		this.transGroup=transGroup;
                this.branchGroup=branchGroup;
                this.cageTransGroup=null;
                this.highlight=false;
	}
	/**
         * Gibt die Token-Daten des Shape zurück
         * @return Token-Daten
         */
	public Token getToken(){
		return this.token;
	}
	
        /**
         * Gibt die TransformGroup des Shape zurück
         * @return TransformGroup
         */
	public TransformGroup getTransformGroup(){
		return this.transGroup;
	}
        /**
         * Gibt die Branchgroup des Shape zurück
         * @return Branchgroup
         */
	public BranchGroup getBranchGroup(){
		return this.branchGroup;
	}
        
        /**
         * Gibt die Käfig TransformGroup des Shape zurück
         * @return Käfig TransformGroup
         */
        public TransformGroup getCageTransformGroup(){
		return this.cageTransGroup;
	}
        
        /**
         * Setzt die Käfig TransformGroup des Shape auf group
         * @param group TransformGroup des Käfigs
         */
        public void setCageTransformGroup(TransformGroup group){
		this.cageTransGroup=group;
	}
        /**
         * Gibt true zurück wenn die Spielfigur markiert ist
         * @return true wenn markiert
         */
        public boolean isHighlighted(){
		return this.highlight;
	}
        
        /**
         * Setzt ob die Spielfigur(Shape) markiert ist
         * @param activate true wenn markiert
         */
        public void setHighlight(boolean activate){
		
            this.highlight=activate;
	}
}