package jDistance.ai;

import jDistance.model.Token;
import jDistance.model.TokenMove;
import jDistance.model.*;

import java.util.List;


/**
 * Verhindert Züge, die denn König in gefahr bringt würden
 * 
 *
 */

public class PreventKingMoveRule extends AiRule {
    /**
     * Konstruktor
     * @param assessment Wichtung der Regel
     */
	public PreventKingMoveRule(int assessment){
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
			if(move.getToken() instanceof King){
			Field destination= move.getDestination();
			for(TokenMove oppositeMove:otherPlayerMoves){
				if(oppositeMove.getDestination().equals(destination)){
					//System.out.println("RreventKingMoveRule match");
					move.setScore(move.getScore()-this.getAssessment());
				}
			}
			}
		}
	}

}
