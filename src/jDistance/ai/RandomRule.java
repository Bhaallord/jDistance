package jDistance.ai;

import jDistance.model.Token;
import jDistance.model.TokenMove;

import java.util.List;

/**
 * Gibt jeden Zug eine Zufällige Wertung
 * 
 *
 */
public class RandomRule extends AiRule {
    /**
     * Konstruktor
     * @param assessment Wichtung der Regel
     */
	public RandomRule(int assessment){
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
			move.setScore(move.getScore()+(int)(Math.round(this.getAssessment()*Math.random())));
		}

	}

}
