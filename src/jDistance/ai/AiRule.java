package jDistance.ai;

import java.util.*;
import jDistance.model.*;

/**
 * Basisklasse aller AiRules
 * 
 */
public abstract class AiRule {

    private final int assessment;

    /**
     * Konstruktor
     * @param assessment Wichtung der Regel
     */
    public AiRule(int assessment) {
        this.assessment=assessment;
    }

    /**
     * Gibt die Gewichtung der Regel zurück
     * @return 
     */
    public int getAssessment() {
        return this.assessment;
    }

    /**
     * Bewertet die übergeben AiMoves
     * @param moves möglichen Züge
     * @param otherPlayerMoves gegnerische Züge
     * @param tokens Spielerfiguren auf denn Spielfeld
     */
    public abstract void run(List<AiMove> moves, List<TokenMove> otherPlayerMoves, List<Token> tokens);

}
