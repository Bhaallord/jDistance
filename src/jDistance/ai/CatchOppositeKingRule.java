package jDistance.ai;

import jDistance.model.PlayingGround;
import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.util.*;

import java.util.List;

/**
 * Fängt den gegnerischen König, wenn möglich
 * 
 *
 */

public class CatchOppositeKingRule extends AiRule {

    /**
     * Konstruktor
     * @param assessment Wichtung der Regel
     */
	public CatchOppositeKingRule(int assessment) {
		super(assessment);
	}

        /**
         * Bewertet eine Liste nach AiMoves
         * @param moves Liste der möglichen Züge
         * @param otherPlayerMoves Gegnerischen Züge
         * @param tokens Spielfiguren auf den Spielfeld
         */
	@Override
	public void run(List<AiMove> moves,List<TokenMove> otherPlayerMoves, List<Token> tokens) {
		for(AiMove move:moves){
			PlayingGround playground = new PlayingGround(tokens);
			List<Event> events=playground.moveToken(move);
			if(events.contains(new WinEvent(move.getToken().getPlayer()))){
				move.setScore(move.getScore()+this.getAssessment());
				//System.out.println("CatchOppositeKingRule Match"+move.getScore());
			}
		}

	}

}
