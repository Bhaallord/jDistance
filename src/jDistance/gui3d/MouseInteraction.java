package jDistance.gui3d;

import jDistance.model.TokenMove;

import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Shape3D;

import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.behaviors.PickMouseBehavior;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

/**
 * Mause Interaktionsklasse
 * 
 */
public class MouseInteraction extends PickMouseBehavior {

    private Distance3D gui;
    private Shape3D last;

    /**
     * Konstruktor
     * @param canvas Canvas3D worauf das MouseInteraction wirkt
     * @param bg BranchGroup auf welches das MouseInteraction reagiert 
     * @param bs Wirkungsbereich des MouseInteraction
     * @param gui Hauptclasse von JDistance3D
     */
    public MouseInteraction(Canvas3D canvas, BranchGroup bg, Bounds bs, Distance3D gui) {
        super(canvas, bg, bs);
        setSchedulingBounds(bs);
        this.gui = gui;
        last = null;
        this.setTolerance(0);
    }

    /**
     * Wird immer aufgerufen wenn der Spieler in die Scene klickt
     * @param xpos x-Positions des Klicks
     * @param ypos y-Positions des Klicks
     */
    @Override
    public void updateScene(int xpos, int ypos) {
        if (!gui.isMyTurn()) {
            return;
        }
        //Ausgewähltes Object Suchen
        Shape3D pickedShape = null;
        pickCanvas.setShapeLocation(xpos, ypos);
        PickResult pResult = pickCanvas.pickClosest();
        if (pResult != null) {
            pickedShape = (Shape3D) pResult.getNode(PickResult.SHAPE3D);
        }
        //Spieler hat nicht ins leere geklickt
        if (pickedShape != null) {

            if (pickedShape.getUserData() instanceof GuiTokenData) {
                GuiTokenData tokenData = (GuiTokenData) pickedShape.getUserData();
                if (tokenData.getToken().getPlayer() == gui.getCurrentPlayer() && last == null) {
                    //Erste eigenen Figure angeklickt
                    last = pickedShape;
                    gui.showPossibleMove(((GuiTokenData) last.getUserData()).getToken());
                    gui.setHighlightToken(last, true);

                }
                if (last != null) {
                    //Zum zweiten mal eine Mögliche Spielfigur ausgewählt
                    GuiTokenData lastData = (GuiTokenData) last.getUserData();
                    boolean valid = false;
                    for (TokenMove move : gui.getPossibleMove()) {
                        if (move.equals(new TokenMove(lastData.getToken(), tokenData.getToken().getPos()))) {
                            //Möglicher Zug gefunden
                            gui.sendTokenMoveEvent(move);
                            valid = true;
                            gui.setCurrentPlayer(-1);

                            gui.hidePossibleMove();
                            gui.setHighlightToken(last, false);

                            last = null;

                            break;
                        }
                    }
                    if (!valid && tokenData.getToken().getPlayer() == gui.getCurrentPlayer()) {
                        //Kein gültiger Zug gefunden. Neue Figure wird hervorgehoben
                        gui.setHighlightToken(last, false);

                        last = pickedShape;

                        gui.showPossibleMove(((GuiTokenData) last.getUserData()).getToken());
                        gui.setHighlightToken(last, true);

                    }
                }

            }
            if (pickedShape.getUserData() instanceof GuiFieldData) {
                //Spielfeld wurde ausgewählt
                GuiFieldData fieldData = (GuiFieldData) pickedShape.getUserData();

                if (last != null) {

                    gui.hidePossibleMove();
                    gui.setHighlightToken(last, false);
                    GuiTokenData tokenData = (GuiTokenData) last.getUserData();
                    for (TokenMove move : gui.getPossibleMove()) {
                        if (move.equals(new TokenMove(tokenData.getToken(), fieldData.getField()))) {
                            //Gültiger Zug gefunden
                            gui.sendTokenMoveEvent(move);
                            //Verhindert das nochmallige auswählen
                            gui.setCurrentPlayer(-1);

                            gui.setHighlightToken(last, false);
                            last = null;


                            break;
                        }
                    }
                    last = null;
                }

            }
        } else {
            //Spiele drückt daneben und highlight token wird zurückgesetzt
            if (last != null) {
                gui.hidePossibleMove();
                gui.setHighlightToken(last, false);
            }
            last = null;
            //TODO: animation entfernen

        }

    }
}
