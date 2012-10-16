package jDistance.ai;

import jDistance.model.PlayingGround;
import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.util.Event;
import jDistance.util.WinEvent;

import java.util.List;

/**
 * Berechnet den nächsten gegnerischen Zug und wertet dessen Chancen zu gewinnen aus
 * 
 */
public class ComputeNextTurnRuleRreventKing extends AiRule {
    /**
     * Konstruktor
     * @param assessment Wichtung der Regel
     */
    public ComputeNextTurnRuleRreventKing(int assessment) {
        super(assessment);
    }

        /**
         * Bewertet eine Liste nach AiMoves
         * @param moves Liste der möglichen Züge
         * @param otherPlayerMoves Gegnerischen Züge
         * @param tokens Spielfiguren auf den Spielfeld
         */
    @Override
    public void run(List<AiMove> moves, List<TokenMove> otherPlayerMoves, List<Token> tokens) {
        for (AiMove move : moves) {
            PlayingGround ground = new PlayingGround(tokens);
            List<Event> events = ground.moveToken(move);
            boolean containsWinEvent = false;
            for (Event event : events) {
                if (event instanceof WinEvent) {
                    containsWinEvent = true;
                }
            }
            if (!containsWinEvent) {
                List<TokenMove> oppositeMoves = ground.getPossibleMove(move.getToken().getPlayer() % 2 + 1);
                for (TokenMove oppositeMove : oppositeMoves) {
                    PlayingGround oppositeState = new PlayingGround(ground.getTokens());
                    List<Event> oppositeEvents = oppositeState.moveToken(oppositeMove);
                    for (Event oppositeEvent : oppositeEvents) {
                        if (oppositeEvent instanceof WinEvent) {
                            if (((WinEvent) oppositeEvent).getPlayer() != move.getToken().getPlayer()) {
                                move.setScore(move.getScore() - this.getAssessment());
                                System.out.println("ComputeNextTurnRuleRreventKing Match"+move.getScore());
                            }
                        }
                    }


                }
            }
        }
    }
}
