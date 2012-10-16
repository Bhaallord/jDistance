package jDistance.ai;

import jDistance.model.Token;
import jDistance.model.TokenMove;

import java.util.List;
/**
 * Regel zum fangen gegnerischer Bauern
 * 
 */
public class CatchOppositeSlaveRule extends AiRule {
    /**
     * Konstruktor
     * @param assessment Wichtung der Regel
     */
	public CatchOppositeSlaveRule(int assessment){
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
			for(Token token: tokens){
				if(token.getPos().equals(move.getDestination())&& move.getToken().getPlayer()!=token.getPlayer()&&!token.isLocked()){
					move.setScore(move.getScore()+this.getAssessment());
					System.out.println("CatchOppositeSlaveRule match");
				}
			}
		}
	}

}
