package jDistance.ai;

import jDistance.model.Token;
import jDistance.util.ExpectTokenMoveEvent;
import jDistance.util.MessageHandler;
import jDistance.util.MoveTokenEvent;
import jDistance.model.TokenMove;

import java.util.*;

/**
 * KI Spieler Controller, kann mit eine GameController verbunden werden 
 * 
 */
public class AiController extends MessageHandler implements Runnable {

    private final int player;
    private final List<AiRule> rules;

    /**
     * Konstruktor
     * @param player welcher Spieler spielt die KI
     * @param rules regeln der KI
     */
    public AiController(int player, List<AiRule> rules) {
        this.player = player;
        this.rules = rules;
    }

    /**
     * Gibt eine Liste aller Regeln der KI zurück
     * @return 
     */
    synchronized public List<AiRule> getRules() {
        //Ausreichend da die AiRule nur final methoden enthält
    	if(this.rules==null){
    		return null;
    	}
        return Collections.unmodifiableList(this.rules);
    }

    /**
     * Gibt den Spieler zurück den die KI spielt
     * @return Spieler
     */
    public int getPlayer() {
        return this.player;
    }
    
    /**
     * Berechnet aus einen Event die gegnerischen Züge
     * @param event ExpectTokenMoveEvent
     * @return Liste von möglichen Zügen
     */
    protected List<TokenMove> getOtherPlayerMoves(ExpectTokenMoveEvent event) {

        List<TokenMove> otherPlayerMoves = new ArrayList<TokenMove>();
        for (TokenMove move : event.getTokenMove()) {
            if (move.getToken().getPlayer() != this.getPlayer()) {
                otherPlayerMoves.add(move);
            }
        }
        return otherPlayerMoves;
    }

    /**
     * Berechnet aus einen Event die eigenen Züge
     * @param event ExpectTokenMoveEvent
     * @return Liste von möglichen Zügen
     */
    protected List<AiMove> getPlayerMoves(ExpectTokenMoveEvent event) {

        List<AiMove> moves = new ArrayList<AiMove>();
        for (TokenMove move : event.getTokenMove()) {
            if (move.getToken().getPlayer() == this.getPlayer()) {
                moves.add(new AiMove(100, move));
            }
        }
        System.out.println("\n\n"+moves+"\n\n");
        return moves;
    }

    
    /**
     * Bewertet die Züge
     * @param moves Alle möglichen KI Bewegungen
     * @param otherPlayerMoves Gegnerische Züge
     * @param tokens Spielefiguren des PlayingGround
     * @param rules KI Regeln
     * @return Liste der bewerteten Züge
     */
    static protected List<AiMove> assessMove(List<AiMove> moves, List<TokenMove> otherPlayerMoves, List<Token> tokens, List<AiRule> rules) {

        List<AiMove> result = new ArrayList<AiMove>();

        //Zur Sicherheit das es keine Referencen sind
        for (AiMove move : moves) {
            result.add(move.clone());
        }
        
        if (rules != null) {
            for (AiRule rule : rules) {
                rule.run(result, otherPlayerMoves, tokens);
                // Sortiert immer denn aktuellen Stand
                Collections.sort(result, new Comparator<AiMove>() {

                    @Override
                    public int compare(AiMove o1, AiMove o2) {
                        return (o2.getScore() - o1.getScore());
                    }
                });
                // System.out.println("	"+moves);
            }
        }
        return result;
    }

    /**
     * Haupteventmethode. Wertet die ExpectMoveTokenEvent aus und gibt einen MoveTokenEvent zurück.
     */
    @Override
    public void run() {
        while (true) {
            
            if(Thread.currentThread().isInterrupted()){
                return;
            }
            
            jDistance.util.Event event = getNextMessage();
            // MoveTokenEvent Behandlung
            if (event instanceof jDistance.util.ExpectTokenMoveEvent) {
                ExpectTokenMoveEvent expectTokenMoveEvent = (ExpectTokenMoveEvent) event;
                if (expectTokenMoveEvent.getPlayer() != this.getPlayer()) {
                    continue;
                }
                List<AiMove> moves=AiController.assessMove(getPlayerMoves(expectTokenMoveEvent), getOtherPlayerMoves(expectTokenMoveEvent), expectTokenMoveEvent.getToken(), this.getRules());
                System.out.println(moves);
                this.sendMessage(new MoveTokenEvent(this.getPlayer(), moves.get(0)));

            } else if (event instanceof jDistance.util.CloseEvent) {
                //Verläßt die run() Methode
                return;
            }
        }
    }
}
