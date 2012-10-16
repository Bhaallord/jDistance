/**
 * 
 */
package jDistance.ai;

import jDistance.model.King;
import jDistance.model.Token;
import jDistance.model.TokenMove;

import java.util.List;

/**
 * Befreit eigene Bauer wenn möglich
 * 
 *
 */
public class ReleaseSlaveRule extends AiRule {

	    /**
     * Konstruktor
     * @param assessment Wichtung der Regel
     */
	public ReleaseSlaveRule(int assessment) {
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
			for(Token token: tokens){
				if(token.getPos().equals(move.getDestination())&& move.getToken().getPlayer()==token.getPlayer()&&token.isLocked()){
					move.setScore(move.getScore()+this.getAssessment());
					System.out.println("ReleaseSlaveRule match");
				}
			}
			}
		}
	}

}
