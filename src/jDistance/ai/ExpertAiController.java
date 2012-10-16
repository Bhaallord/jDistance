package jDistance.ai;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Experten Ki-Controller
 * 
 */
public class ExpertAiController extends AiController {
    /**
     * Konstruktor
     *
     * @param player Spieler welche die Ki benutzt
     */
    public ExpertAiController(int player) {
        super(player, new ArrayList<AiRule>(Arrays.asList(new RandomRule(10),
                new CatchOppositeKingRule(1000), new PreventKingMoveRule(500), new RescueKingRule(
                400), new CatchOppositeSlaveRule(50), new ReleaseSlaveRule(30), new ComputeNextTurnRuleRreventKing(1000),new ComputeNextTurnRulePreventSlave(800))));
    }
}
