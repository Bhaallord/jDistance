package jDistance.gui3d;

import java.util.*;

import jDistance.gui.GUI;
import jDistance.model.*;
import jDistance.settings.Settings;
import jDistance.util.Event;
import jDistance.util.ExpectTokenMoveEvent;
import jDistance.util.GameStartedEvent;
import jDistance.util.TokenLockedEvent;
import jDistance.util.TokenMovedEvent;
import jDistance.util.TokenSwapedEvent;

import java.awt.GraphicsEnvironment;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.PositionPathInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;
import jDistance.util.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Distance3D extends GUI {
    
    private Canvas3D m_canvas3d;
    private SimpleUniverse m_universe;
    private List<Shape3D> m_tokenList;
    private List<BranchGroup> m_mark;
    private List<Shape3D> m_fieldList;
    private BranchGroup m_scene;
    private BranchGroup m_tokenGroup;
    private long animationTime;
    private List<TokenMove> m_possibleMove;
    private List<PointLight> m_lights;
    private int currentPlayer;
    private int currentShowPlayer;

    /**
     * Gibt den aktuellen Spieler zurück
     *
     * @return Spieler der jetzt dran ist
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Setzt den aktuellen Spieler
     *
     * @param player Spieler der jetzt dran ist
     */
    public void setCurrentPlayer(int player) {
        this.currentPlayer = player;
    }

    /**
     * Standartkonstruktor
     *
     * Legt intern ein neues Canvas3D Object an und zeichnet das Spielbrett,
     * platziert die Beleuchtung und positioniert die Kamara.
     */
    public Distance3D() {
        currentShowPlayer = 2;
        animationTime = 0;
        m_scene = new BranchGroup();
        m_tokenList = new ArrayList<Shape3D>();
        m_fieldList = new ArrayList<Shape3D>();
        m_lights = new ArrayList<PointLight>();
        m_mark = new ArrayList<BranchGroup>();
        m_scene.setCapability(BranchGroup.ALLOW_DETACH);
        m_scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        m_scene.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        m_scene.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        
        GraphicsConfigTemplate3D template = new GraphicsConfigTemplate3D();
        
        Settings settings = new Settings();
        
        if (settings.antialiasing) {
            System.setProperty("j3d.implicitAntialiasing", "true");
            template.setSceneAntialiasing(2);
        } else {
            System.setProperty("j3d.implicitAntialiasing", "false");
        }
        
        m_canvas3d = new Canvas3D(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getBestConfiguration(template));
        
        m_canvas3d.setDoubleBufferEnable(true);
        m_universe = new SimpleUniverse(m_canvas3d, 4);
        m_universe.getViewingPlatform().setNominalViewingTransform();
        
        m_canvas3d.getView().setSceneAntialiasingEnable(settings.antialiasing);
        
        setDefaultCamera(m_universe);
        draftBattelfield(m_universe);
        light(m_universe);
        
    }

    /**
     * Gibt die möglichen Züge des aktuellen Spielers zurück
     *
     * @return Liste mit möglichen Zügen
     */
    public List<TokenMove> getPossibleMove() {
        return Collections.unmodifiableList(this.m_possibleMove);
    }

    /**
     * Gibt true zurück wenn es gerade ein eigener Zug ist
     *
     * @return true wenn es ein eigener Zug ist
     */
    public boolean isMyTurn() {
        return this.isMyTurn(currentPlayer);
    }

    /**
     * Zeigt die möglichen Züge einer Spielfigur an
     * @param token Spielfigur von denn die Züge angezeigt werden soll
     */
    public void showPossibleMove(Token token) {
        //Vorher noch alle entfernen
        hidePossibleMove();
        
        for (TokenMove move : this.m_possibleMove) {
            if (move.getToken().equals(token)) {
                BranchGroup markBranchGroup = new BranchGroup();
                Transform3D transform = new Transform3D();
                transform.setTranslation(this.getFieldVector(move.getDestination()));
                Transform3D transform2 = new Transform3D();
                transform2.setTranslation(new Vector3d(0, -0.99d, 0));
                Transform3D transform3 = new Transform3D();
                transform3.setScale(0.9f);
                transform.mul(transform2);
                transform.mul(transform3);
                Shape3D model = ModelLoader.loadMark();
                
                model.setPickable(false);
                
                Appearance modelAppearence = new Appearance();
                
                TransparencyAttributes ta = new TransparencyAttributes();
                ta.setTransparencyMode(TransparencyAttributes.NICEST);
                ta.setTransparency(0.5f);
                modelAppearence.setTransparencyAttributes(ta);
                
                modelAppearence.setMaterial(new Material(new Color3f(0.3f, 0.2f, 0.2f),
                        new Color3f(0.3f, 0.2f, 0.2f), new Color3f(0.4f, 0.3f, 0.3f), new Color3f(0.4f, 0.3f, 0.3f), 60));
                
                model.setAppearance(modelAppearence);
                
                
                
                TransformGroup markTransformGroup = new TransformGroup(transform);
                markTransformGroup.addChild(model);
                markBranchGroup.addChild(markTransformGroup);
                markBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
                markBranchGroup.compile();
                this.m_scene.addChild(markBranchGroup);
                this.m_mark.add(markBranchGroup);
                
                
            }
        }
    }

    
    /**
     * Versteckt alle Marken 
     */
    public void hidePossibleMove(){
        
        for (BranchGroup group : this.m_mark) {
            m_scene.removeChild(group);
        }
    }
    
    /**
     * Gibt das Canvas3D Object zurück auf dem die 3D Scene dargestelt wird
     *
     * @return Canvas3D
     */
    public Canvas3D getCanvas3D() {
        return (this.m_canvas3d);
    }

    /**
     * Sendet ein TokenMoveEvent an alle die mit diesen MessageHandler verbunden
     * sind
     *
     * @param token Spielfigur die bewegt werden soll
     * @param field Zielfeld der Figur
     */
    public void sendTokenMoveEvent(Token token, Field field) {
        this.sendMessage(new MoveTokenEvent(this.currentPlayer, token, field));
    }

    /**
     * Sendet ein TokenMoveEvent an alle die mit diesen MessageHandler verbunden
     * sind
     *
     * @param move Bewegung die gesendet werden soll
     */
    public void sendTokenMoveEvent(TokenMove move) {
        this.sendMessage(new MoveTokenEvent(this.currentPlayer, move));
    }

    /**
     * Färbt ein Token ein damit es als markiert gilt
     * @param shape Token der markiert werden soll
     * @param activate ob Highlight hinzugefügt oder entfernt werden soll
     */
    public void setHighlightToken(Shape3D shape, boolean activate) {
        if (!(shape.getUserData() instanceof GuiTokenData)) {
            return;
        }
        GuiTokenData data = (GuiTokenData) shape.getUserData();
        if (data.isHighlighted() == activate) {
            return;
        }
        if (activate) {
            Appearance appearence = shape.getAppearance();
            Material material = appearence.getMaterial();
            Color3f ambiantColor = new Color3f();
            material.getAmbientColor(ambiantColor);
            ambiantColor.add(new Color3f(0.5f, 0.1f, 0.1f));
            shape.getAppearance().getMaterial().setAmbientColor(ambiantColor);
        } else {
            
            Appearance appearence = shape.getAppearance();
            Material material = appearence.getMaterial();
            Color3f ambiantColor = new Color3f();
            material.getAmbientColor(ambiantColor);
            ambiantColor.sub(new Color3f(0.5f, 0.1f, 0.1f));
            shape.getAppearance().getMaterial().setAmbientColor(ambiantColor);
        }
        data.setHighlight(activate);
    }

    /**
     * Setzt die Kamara Transformation, damit die Kamara schräg auf das Zentrum des Spielbrettes zeigt
     * @param universe auf welchen das Spielbrett dargestellt werden soll
     */
    private void setDefaultCamera(SimpleUniverse universe) {
        
        TransformGroup tg = ((SimpleUniverse) universe).getViewingPlatform().getMultiTransformGroup().getTransformGroup(0);
        TransformGroup tg2 = ((SimpleUniverse) universe).getViewingPlatform().getMultiTransformGroup().getTransformGroup(2);
        TransformGroup tg3 = ((SimpleUniverse) universe).getViewingPlatform().getMultiTransformGroup().getTransformGroup(3);
        Transform3D t3da = new Transform3D();
        Transform3D t3db = new Transform3D();
        Transform3D t3dc = new Transform3D();
        Transform3D t3dd = new Transform3D();
        tg.getTransform(t3da);
        t3db.setTranslation(new Vector3f(7, 30, 7));
        t3dd.setTranslation(new Vector3f(0, 0, 17.5f));
        
        t3da.mul(t3db);
        t3dc.rotX(java.lang.Math.PI * -0.33f);
        tg2.setTransform(t3dd);
        tg3.setTransform(t3dc);
        tg.setTransform(t3da);
    }

    /**
     * Zeichnet das Spielbrett, die Hintergrundfarbe und fügt den
     * MouseInteraction zur Scene hinzu
     *
     * @param universe auf welchen das Spielbrett dargestellt werden soll
     */
    private void draftBattelfield(SimpleUniverse universe) {
        
        Shape3D field = ModelLoader.loadField();
        if (field == null) {
            return;
        }
        
        TransformGroup tgBattlefield = new TransformGroup();
        int indexX = 0;
        while (indexX < 8) {
            int indexY = 0;
            while (indexY < 8) {
                
                Transform3D tfField = new Transform3D();
                tfField.setTranslation(new Vector3f(indexX * 2f, 0f, indexY * 2f));
                TransformGroup tgField = new TransformGroup(tfField);
                
                Shape3D fieldCopy = (Shape3D) field.cloneTree();
                
                this.m_fieldList.add(fieldCopy);
                fieldCopy.setUserData(new GuiFieldData(new Field(indexX, indexY), tgField));
                
                Appearance fieldAppearance = new Appearance();
                Texture2D texture = null;
                if ((indexX + indexY) % 2 == 1) {
                    fieldAppearance.setMaterial(new Material(new Color3f(0.3f, 0.3f, 0.3f),
                            new Color3f(0.5f, 0.5f, 0.5f), new Color3f(0.3f, 0.3f, 0.3f),
                            new Color3f(0.5f, 0.5f, 0.5f), 60.0f));
                    
                    texture = GuiTextureLoader.loadWhiteFieldTexture();
                    
                } else {
                    fieldAppearance.setMaterial(new Material(new Color3f(0.01f, 0.01f, 0.01f),
                            new Color3f(0.01f, 0.01f, 0.01f), new Color3f(0.01f, 0.01f, 0.01f),
                            new Color3f(0.5f, 0.5f, 0.5f), 60.0f));
                    texture = GuiTextureLoader.loadBlackFieldTexture();
                    
                }

                //Texturen hinzufügen
                fieldAppearance.setTexture(texture);
                TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR,
                        TexCoordGeneration.TEXTURE_COORDINATE_2, new Vector4f(0.0f, 0.0f, 1.0f, 0.0f));
                fieldAppearance.setTexCoordGeneration(tcg);
                fieldCopy.setAppearance(fieldAppearance);
                
                tgField.addChild(fieldCopy);
                tgBattlefield.addChild(tgField);
                
                indexY++;
            }
            indexX++;
        }
        
        Background bg = new Background(new Color3f(0.3f, 0.3f, 0.3f));
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
        bg.setApplicationBounds(bounds);
        m_scene.addChild(bg);
        
        
        Shape3D brett = ModelLoader.loadBrett();
        if (brett == null) {
            return;
        }
        
        Appearance brettAppearences = new Appearance();
        Texture2D brettTexture = GuiTextureLoader.loadBorderTexture();
        brettAppearences.setTexture(brettTexture);
        TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_2,
                new Vector4f(0.5f, 2.46f, 0f, 0f), new Vector4f(0f, 0f, 0.5f, 0.5f));
        brettAppearences.setTexCoordGeneration(tcg);
        Transform3D transBrett = new Transform3D();
        transBrett.setTranslation(new Vector3d(7, -0.2, 7));
        transBrett.setScale(new Vector3d(9.5, 1, 9.5));
        //transBrett.rotX(java.lang.Math.PI);

        TransformGroup transGroupBrett = new TransformGroup(transBrett);
        brett.setAppearance(brettAppearences);
        transGroupBrett.addChild(brett);
        m_scene.addChild(transGroupBrett);
        
        m_scene.addChild(tgBattlefield);
        BoundingSphere bs = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
        MouseInteraction sp = new MouseInteraction(m_canvas3d, m_scene, bs, this);
        m_scene.addChild(sp);
        m_scene.compile();
        universe.addBranchGraph(m_scene);
    }

    /**
     * Fügt 4 Lichtquellen zum übergeben Universe hinzu
     *
     * @param universe zu welchen die Lichtquellen hinzugefügt werden soll
     */
    private void light(SimpleUniverse universe) {
        
        PointLight light1 = new PointLight(new Color3f(0.8f, 0.8f, 0.8f), new Point3f(-8f, 5f, -8f),
                new Point3f(0.001f, 0.01f, 0.001f));
        light1.setInfluencingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f), Double.MAX_VALUE));
        
        PointLight light2 = new PointLight(new Color3f(0.8f, 0.8f, 0.8f), new Point3f(-8, 5, 24),
                new Point3f(0.001f, 0.01f, 0.001f));
        light2.setInfluencingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f), Double.MAX_VALUE));
        
        
        
        PointLight light3 = new PointLight(new Color3f(0.8f, 0.8f, 0.8f), new Point3f(24f, 5, 24),
                new Point3f(0.001f, 0.01f, 0.001f));
        light3.setInfluencingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f), Double.MAX_VALUE));
        
        PointLight light4 = new PointLight(new Color3f(0.8f, 0.8f, 0.8f), new Point3f(24f, 5f, -8f),
                new Point3f(0.001f, 0.01f, 0.001f));
        light4.setInfluencingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f), Double.MAX_VALUE));
        
        AmbientLight light5 = new AmbientLight(new Color3f(0.3f, 0.3f, 0.3f));
        light5.setInfluencingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f), Double.MAX_VALUE));
        
        
        
        BranchGroup scene = new BranchGroup();
        scene.addChild(light5);
        scene.addChild(light4);
        scene.addChild(light3);
        scene.addChild(light2);
        scene.addChild(light1);
        m_lights.add(light1);
        m_lights.add(light2);
        m_lights.add(light3);
        m_lights.add(light4);
        scene.compile();
        universe.addBranchGraph(scene);
    }

    /**
     * Gibt die Zielvektor eines Feldes zurück
     *
     * @param field Feld was in 3 Dimenisionale Koordinaten umgewandelt werden
     * soll
     * @return 3 Dimenisionale Koordinaten
     */
    private Vector3f getFieldVector(Field field) {
        return new Vector3f(field.getX() * 2f, 1.2f, field.getY() * 2f);
        
    }

    /**
     * Gibt die Zielpunkt eines Feldes zurück
     *
     * @param field Feld was in 3 Dimenisionale Koordinaten umgewandelt werden
     * soll
     * @return 3 Dimenisionale Koordinaten
     */
    private Point3f getFieldPoint(Field field) {
        return new Point3f(field.getX() * 2f, 1.2f, field.getY() * 2f);
        
    }

    /**
     * Erstellt die 3D Modelle zu begin eine
     *
     * @param event welches Animiert werden soll
     */
    private void draftFirstPlayingGround(GameStartedEvent event) {
        
        //Marken entfernen die ich vergessen habe:-)
        this.hidePossibleMove();
        
        
        Shape3D kingModel = ModelLoader.loadKing();
        Shape3D slaveModel = ModelLoader.loadSlave();
        Shape3D shadeModel = ModelLoader.loadShade();
        Texture2D shadeTexture = GuiTextureLoader.loadShadeTexture();
        if (m_tokenGroup != null) {
            m_scene.removeChild(m_tokenGroup);
        }
        m_tokenGroup = new BranchGroup();
        m_tokenGroup.setCapability(BranchGroup.ALLOW_DETACH);
        m_tokenGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        m_tokenGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        m_tokenGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        
        for (Token token : event.getToken()) {
            
            Transform3D translation = new Transform3D();
            translation.setTranslation(getFieldVector(token.getPos()));
            Shape3D model = null;
            Transform3D transking = null;
            if (token instanceof Slave) {
                model = (Shape3D) slaveModel.cloneTree();
                
            } else {
                model = (Shape3D) kingModel.cloneTree();
                
            }
            
            model.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
            model.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
            model.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_READ);
            model.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
            
            
            
            Appearance tokenAppearance = new Appearance();
            
            tokenAppearance.setCapability(Appearance.ALLOW_MATERIAL_READ);
            tokenAppearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);

            //Wer weis was es bringt
            PolygonAttributes pa = new PolygonAttributes();
            pa.setCullFace(PolygonAttributes.CULL_BACK);
            tokenAppearance.setPolygonAttributes(pa);
            
            if (token.getPlayer() == 1) {
                tokenAppearance.setMaterial(new Material(new Color3f(0.1f, 0.1f, 0.1f),
                        new Color3f(0.25f, 0.25f, 0.25f), new Color3f(0.38f, 0.36f, 0.36f),
                        new Color3f(0.5f, 0.5f, 0.5f), 60.0f));
                
            } else {
                tokenAppearance.setMaterial(new Material(new Color3f(0.01f, 0.01f, 0.01f),
                        new Color3f(0.1f, 0.1f, 0.1f), new Color3f(0.05f, 0.05f, 0.05f), new Color3f(
                        0.5f, 0.5f, 0.5f), 60.0f));
                
            }
            
            tokenAppearance.getMaterial().setCapability(Material.ALLOW_COMPONENT_READ);
            tokenAppearance.getMaterial().setCapability(Material.ALLOW_COMPONENT_WRITE);
            
            model.setAppearance(tokenAppearance);
            
            TransformGroup tokenGroup = new TransformGroup(translation);
            tokenGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            tokenGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
            tokenGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
            tokenGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
            tokenGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
            
            BranchGroup tokenBranchGroup = new BranchGroup();
            tokenBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
            tokenBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
            tokenBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
            tokenBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

            //Schatten hinzufügen
            Appearance shadeAppearance = new Appearance();
            Shape3D shade = (Shape3D) shadeModel.cloneTree();

            //Transparents

            shadeAppearance.setTexture(shadeTexture);
            TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR,
                    TexCoordGeneration.TEXTURE_COORDINATE_2, new Vector4f(0.5f, 0f, 0f, 0.49f), new Vector4f(0f, 0f, 0.5f, 0.5f));
            shadeAppearance.setTexCoordGeneration(tcg);
            
            TextureAttributes texAtt = new TextureAttributes();
            texAtt.setTextureMode(TextureAttributes.MODULATE);
            shadeAppearance.setTextureAttributes(texAtt);
            
            TransparencyAttributes tAtt = new TransparencyAttributes(TransparencyAttributes.BLENDED, 0.0f);
            shadeAppearance.setTransparencyAttributes(tAtt);
            
            shade.setAppearance(shadeAppearance);
            Transform3D transShade = new Transform3D();
            transShade.setTranslation(new Vector3f(0, -0.97f, 0));
            transShade.setScale(1.1f);
            TransformGroup transShadeGroup = new TransformGroup(transShade);
            transShadeGroup.addChild(shade);
            
            tokenBranchGroup.addChild(model);
            tokenBranchGroup.addChild(transShadeGroup);
            
            model.setCapability(Shape3D.ALLOW_PICKABLE_WRITE);
            model.setUserData(new GuiTokenData(token.clone(), tokenGroup, tokenBranchGroup));
            if (token instanceof King) {
                
                transking = new Transform3D();
                
                transking.setTranslation(new Vector3f(0f, 0.6f, 0f));
                transking.setScale(1.6f);
                TransformGroup tgKing = new TransformGroup(transking);
                
                
                tgKing.addChild(tokenBranchGroup);
                tokenGroup.addChild(tgKing);
            } else {
                tokenGroup.addChild(tokenBranchGroup);
            }
            
            tokenBranchGroup.compile();
            m_tokenGroup.addChild(tokenGroup);
            model.setPickable(true);
            this.m_tokenList.add(model);
        }
        
        m_tokenGroup.compile();
        
        
        m_scene.addChild(m_tokenGroup);
    }

    /**
     * Animiert die Bewegung einer Spielfigur      
     * @param event welches Animiert werden soll
     */
    private void moveToken(TokenMovedEvent event) {
        for (Shape3D shape : this.m_tokenList) {
            GuiTokenData data = ((GuiTokenData) shape.getUserData());
            
            if (data.getToken().equals(event.getToken())) {
                
                Alpha a = new Alpha(1, // loop count
                        Alpha.INCREASING_ENABLE, // mode
                        0, // triggerTime
                        50, // phaseDelayDuration,
                        1000, // increasingAlphaDuration
                        1000, // increasingAlphaRampDuration
                        1000, // alphaAtOneDuration
                        0, // decreasingAlphaDuration
                        0, // decreasingAlphaRampDuration,
                        0// alphaAtZeroDuration
                        );
                a.setStartTime(System.currentTimeMillis());
                
                PositionPathInterpolator ppi = new PositionPathInterpolator(a,
                        data.getTransformGroup(), new Transform3D(), new float[]{0, 1},
                        new Point3f[]{getFieldPoint(data.getToken().getPos()), // Ausgangspunkt
                            getFieldPoint(event.getDestination()) // Endpunkt
                        });
                ppi.setSchedulingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f),
                        Double.MAX_VALUE)); // Bounds

                BranchGroup branch = new BranchGroup();
                branch.addChild(ppi);
                data.getTransformGroup().addChild(branch);
                
                data.getToken().move(event.getDestination());
                animationTime = a.getIncreasingAlphaDuration() + a.getIncreasingAlphaDuration()
                        + a.getStartTime();
            }
        }
    }

    /**
     * Animiert das vertauschen zweier Spielfiguren
     *
     * @param event welches Animiert werden soll
     */
    private void swapToken(TokenSwapedEvent event) {
        GuiTokenData first = null;
        GuiTokenData second = null;
        
        for (Shape3D shape : this.m_tokenList) {
            GuiTokenData data = ((GuiTokenData) shape.getUserData());
            
            if (data.getToken().equals(event.getFirstTokenMove().getToken())) {
                first = data;
            }
            if (data.getToken().equals(event.getSecondTokenMove().getToken())) {
                second = data;
            }
        }
        
        if (first != null && second != null) {
            Alpha a = new Alpha(1, // loop count
                    Alpha.INCREASING_ENABLE, // mode
                    0, // triggerTime
                    50, // phaseDelayDuration,
                    1000, // increasingAlphaDuration
                    1000, // increasingAlphaRampDuration
                    1000, // alphaAtOneDuration
                    0, // decreasingAlphaDuration
                    0, // decreasingAlphaRampDuration,
                    0// alphaAtZeroDuration
                    );
            a.setStartTime(System.currentTimeMillis());
            
            PositionPathInterpolator ppi = new PositionPathInterpolator(a,
                    first.getTransformGroup(), new Transform3D(), new float[]{0, 1},
                    new Point3f[]{getFieldPoint(first.getToken().getPos()), // Ausgangspunkt
                        getFieldPoint(event.getFirstTokenMove().getDestination()) // Endpunkt
                    });
            ppi.setSchedulingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f), Double.MAX_VALUE)); // Bounds

            BranchGroup branch = new BranchGroup();
            branch.addChild(ppi);
            first.getTransformGroup().addChild(branch);
            
            PositionPathInterpolator ppi2 = new PositionPathInterpolator(a,
                    second.getTransformGroup(), new Transform3D(), new float[]{0, 1},
                    new Point3f[]{getFieldPoint(second.getToken().getPos()), // Ausgangspunkt
                        getFieldPoint(event.getSecondTokenMove().getDestination()) // Endpunkt
                    });
            ppi2.setSchedulingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f), Double.MAX_VALUE)); // Bounds

            BranchGroup branch2 = new BranchGroup();
            branch2.addChild(ppi2);
            second.getTransformGroup().addChild(branch2);
            
            first.getToken().move(event.getFirstTokenMove().getDestination());
            second.getToken().move(event.getSecondTokenMove().getDestination());
            animationTime = a.getIncreasingAlphaDuration() + a.getIncreasingAlphaDuration()
                    + a.getStartTime();
            
        } else {
            System.out.println("Token nicht gefunden");
        }
    }

    /**
     * Animiert das gefangennehmen oder freilassen einer Spielfigur und ändert die Eigenschaft des Shapes
     * @param event welches Animiert werden soll
     */
    private void lockToken(TokenLockedEvent event) {
        
        for (Shape3D shape : this.m_tokenList) {
            GuiTokenData data = ((GuiTokenData) shape.getUserData());
            
            if (data.getToken().equals(event.getToken())) {
                
                data.getToken().setLocked(!event.getToken().isLocked());
                //Token wird gelockt animation
                if (data.getCageTransformGroup() == null) {
                    Shape3D model = ModelLoader.loadCage();
                    model.setPickable(false);
                    Transform3D transform = new Transform3D();
                    transform.setScale(new Vector3d(0f, 0f, 1f));
                    TransformGroup transformGroup = new TransformGroup(transform);
                    transformGroup.addChild(model);
                    
                    
                    Shape3D model2 = ModelLoader.loadCage();
                    model2.setPickable(false);
                    Transform3D transform2 = new Transform3D();
                    transform2.rotY(java.lang.Math.PI / 4);
                    transform2.setScale(new Vector3d(0.7f, 1f, 0.7f));
                    TransformGroup transformGroup2 = new TransformGroup(transform2);
                    transformGroup2.addChild(model2);
                    
                    transformGroup.addChild(transformGroup2);
                    
                    data.setCageTransformGroup(transformGroup);
                    
                    Appearance modelAppearance = new Appearance();
                    
                    modelAppearance.setMaterial(new Material(new Color3f(0.1f, 0.1f, 0.1f),
                            new Color3f(0.1f, 0.1f, 0.1f), new Color3f(0.1f, 0.1f, 0.1f),
                            new Color3f(0.5f, 0.5f, 0.5f), 60.0f));
                    
                    model.setAppearance(modelAppearance);
                    model2.setAppearance(modelAppearance);
                    
                    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
                    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
                    transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
                    transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
                    transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
                    
                    
                    BranchGroup branchGroup = new BranchGroup();
                    
                    branchGroup.setCapability(BranchGroup.ALLOW_DETACH);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
                    branchGroup.addChild(transformGroup);
                    data.getBranchGroup().addChild(branchGroup);
                }
                if (!event.getToken().isLocked()) {
                    
                    
                    @SuppressWarnings("rawtypes")
                    Enumeration datas = data.getBranchGroup().getAllChildren();
                    
                    BranchGroup branchGroup = new BranchGroup();
                    
                    branchGroup.setCapability(BranchGroup.ALLOW_DETACH);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
                    while (datas.hasMoreElements()) {
                        Object element = datas.nextElement();
                        if (element instanceof BranchGroup) {
                            Alpha a = new Alpha(1, // loop count
                                    Alpha.INCREASING_ENABLE, // mode
                                    0, // triggerTime
                                    50, // phaseDelayDuration,
                                    500, // increasingAlphaDuration
                                    500, // increasingAlphaRampDuration
                                    500, // alphaAtOneDuration
                                    0, // decreasingAlphaDuration
                                    0, // decreasingAlphaRampDuration,
                                    0// alphaAtZeroDuration
                                    );
                            a.setStartTime(System.currentTimeMillis());
                            
                            Transform3D animationAxis = new Transform3D();
                            animationAxis.rotY(java.lang.Math.PI / 2);
                            
                            ScaleInterpolator ppi = new ScaleInterpolator(a, data.getCageTransformGroup(), animationAxis, 0, 1f);
                            ppi.setSchedulingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f),
                                    Double.MAX_VALUE)); // Bounds
                            branchGroup.addChild(ppi);
                            ((BranchGroup) element).addChild(branchGroup);
                            //data.getBranchGroup().removeChild((BranchGroup)element);
                            animationTime = a.getIncreasingAlphaDuration() + a.getIncreasingAlphaDuration()
                                    + a.getStartTime();
                        }
                    }
                    
                } //Token wird entlockt animation
                else {
                    @SuppressWarnings("rawtypes")
                    Enumeration datas = data.getBranchGroup().getAllChildren();
                    
                    BranchGroup branchGroup = new BranchGroup();
                    
                    branchGroup.setCapability(BranchGroup.ALLOW_DETACH);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
                    branchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
                    while (datas.hasMoreElements()) {
                        Object element = datas.nextElement();
                        if (element instanceof BranchGroup) {
                            Alpha a = new Alpha(1, // loop count
                                    Alpha.INCREASING_ENABLE, // mode
                                    0, // triggerTime
                                    50, // phaseDelayDuration,
                                    500, // increasingAlphaDuration
                                    500, // increasingAlphaRampDuration
                                    500, // alphaAtOneDuration
                                    0, // decreasingAlphaDuration
                                    0, // decreasingAlphaRampDuration,
                                    0// alphaAtZeroDuration
                                    );
                            a.setStartTime(System.currentTimeMillis());
                            
                            Transform3D animationAxis = new Transform3D();
                            animationAxis.rotY(java.lang.Math.PI / 2);
                            
                            ScaleInterpolator ppi = new ScaleInterpolator(a, data.getCageTransformGroup(), animationAxis, 0.1f, 0);
                            ppi.setSchedulingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f),
                                    Double.MAX_VALUE)); // Bounds
                            branchGroup.addChild(ppi);
                            ((BranchGroup) element).addChild(branchGroup);
                            //data.getBranchGroup().removeChild((BranchGroup)element);
                            animationTime = a.getIncreasingAlphaDuration() + a.getIncreasingAlphaDuration()
                                    + a.getStartTime();
                        }
                    }
                }
            }
        }
    }

    /**
     * Ereigniss Methode die die Abarbeitung von Events sicherstellt
     *
     */
    @Override
    public void run() {
        
        while (true) {
            
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            jDistance.util.Event event = getNextMessage();
            if (event instanceof ExpectTokenMoveEvent) {
                
                ExpectTokenMoveEvent expectTokenMoveEvent = (ExpectTokenMoveEvent) event;
                
                if (this.isMyTurn(expectTokenMoveEvent.getPlayer())) {
                    currentPlayer = expectTokenMoveEvent.getPlayer();
                    this.m_possibleMove = expectTokenMoveEvent.getTokenMove();
                }                
                if (this.getPlayerMode().equals(GUI.PlayerMode.WHITE_ONLY) && this.currentShowPlayer == 2) {
                    rotateScreen();
                }
                if (this.getPlayerMode().equals(GUI.PlayerMode.BLACK_ONLY) && this.currentShowPlayer == 1) {
                    rotateScreen();
                }
                
                
            }
            
            if (event instanceof WinEvent) {
                this.currentPlayer = 0;
            }
            
            if (event instanceof CloseEvent) {
                return;
            }
            runMove(event);
        }
    }

    /**
     * Dreht die Kamera um 180° zum aktuellen Spieler
     *
     */
    private synchronized void rotateScreen() {
        float start = 0;
        float end = 0;
        if (currentShowPlayer == 2) {
            end = (float) Math.PI;
        } else {
            start = (float) Math.PI;
            end = (float) Math.PI * 2;
            
        }
        BranchGroup branch = new BranchGroup();
        Alpha a = new Alpha(1, // loop count
                Alpha.INCREASING_ENABLE, // mode
                0, // triggerTime
                50, // phaseDelayDuration,
                2000, // increasingAlphaDuration
                500, // increasingAlphaRampDuration
                500, // alphaAtOneDuration
                0, // decreasingAlphaDuration
                0, // decreasingAlphaRampDuration,
                0// alphaAtZeroDuration
                );
        a.setStartTime(System.currentTimeMillis());
        Transform3D trans = new Transform3D();
        trans.rotY(1);
        
        TransformGroup tg3 = m_universe.getViewingPlatform().getMultiTransformGroup().getTransformGroup(1);
        
        RotationInterpolator rotation = new RotationInterpolator(a, tg3, trans, start, end);
        rotation.setSchedulingBounds(new BoundingSphere(new Point3d(0f, 0f, 0f), Double.MAX_VALUE));
        branch.addChild(rotation);
        this.m_scene.addChild(branch);
        animationTime = a.getIncreasingAlphaDuration() + a.getIncreasingAlphaDuration()
                + a.getStartTime();
        this.currentShowPlayer = this.currentShowPlayer % 2 + 1;
        
    }

    /**
     * Führt eine animierte Bewegung auf denn Spielfeld aus, je nachdem welches
     * Event übergeben wird
     *
     * Die Methode sorgt dafür das es nicht zu einer Überlagerung von
     * Animationen kommt
     *
     * @param event welches Animiert werden soll
     */
    @SuppressWarnings("static-access")
    private synchronized void runMove(Event event) {
        
        while (animationTime > System.currentTimeMillis()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupted();
            }
        }
        if (event instanceof jDistance.util.ExpectTokenMoveEvent) {
            ExpectTokenMoveEvent expectEvent= (ExpectTokenMoveEvent) event;
            if (expectEvent.getPlayer()!=currentShowPlayer&&this.getPlayerMode().equals(GUI.PlayerMode.BOTH)) {
                this.rotateScreen();
            }
            
        } else if (event instanceof jDistance.util.GameStartedEvent) {
            this.draftFirstPlayingGround((GameStartedEvent) event);
        } else if (event instanceof jDistance.util.TokenMovedEvent) {
            this.moveToken((jDistance.util.TokenMovedEvent) event);
        } else if (event instanceof jDistance.util.TokenSwapedEvent) {
            this.swapToken((jDistance.util.TokenSwapedEvent) event);
        } else if (event instanceof jDistance.util.TokenLockedEvent) {
            this.lockToken((jDistance.util.TokenLockedEvent) event);
        }
    }
}
