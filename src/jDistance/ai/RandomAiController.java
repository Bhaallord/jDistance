package jDistance.ai;
import java.util.*;

/**
 * Zufällige Ki Controller
 * 
 */
public class RandomAiController extends AiController {
        /**
     * Konstruktor
     *
     * @param player Spieler welche die Ki benutzt
     */
	public RandomAiController(int player){
		super(player,new ArrayList<AiRule>( Arrays.asList( new RandomRule(100) ) ));
	}
}
