package jDistance.ai;

import jDistance.model.Field;
import jDistance.model.King;
import jDistance.model.Token;
import jDistance.model.TokenMove;

import java.util.List;


/**
 * Zieht einen König weg, wenn er auf einem Zielfeld des Gegners steht.
 * 
 *
 */
public class RescueKingRule extends AiRule {
    /**
     * Konstruktor
     * @param assessment Wichtung der Regel
     */
	public RescueKingRule(int assessment) {
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
		for(AiMove move:moves){
			if(move.getToken() instanceof King){
			Field kingPosition= move.getToken().getPos();
			boolean savePosition=true;
			for(TokenMove oppositeMove:otherPlayerMoves){
				if(oppositeMove.getDestination().equals(kingPosition)){
					savePosition=false;
					break;
				}
			}
			if(!savePosition){

				System.out.println("RescueKingRule match");
				move.setScore(move.getScore()+this.getAssessment());
			}
			}
		}
	}

}
