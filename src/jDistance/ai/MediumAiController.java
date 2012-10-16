package jDistance.ai;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Mittlere Ki-Controller
 * 
 */
public class MediumAiController extends AiController {    /**
     * Konstruktor
     *
     * @param player Spieler welche die Ki benutzt
     */
	public MediumAiController(int player) {
		super(player, new ArrayList<AiRule>(Arrays.asList(new RandomRule(10),
				new CatchOppositeKingRule(1000), new PreventKingMoveRule(500), new RescueKingRule(
						400), new CatchOppositeSlaveRule(50), new ReleaseSlaveRule(30))));
	}
}
