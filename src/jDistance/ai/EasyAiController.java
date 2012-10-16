package jDistance.ai;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Einfacher Ki-Controller
 *
 * 
 */
public class EasyAiController extends AiController {

    /**
     * Konstruktor
     *
     * @param player Spieler welche die Ki benutzt
     */
    public EasyAiController(int player) {
        super(player, new ArrayList<AiRule>(Arrays.asList(new RandomRule(20), new CatchOppositeKingRule(50), new PreventKingMoveRule(50))));
    }
}
