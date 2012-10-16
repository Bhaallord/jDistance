package jDistance.ai.test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import jDistance.ai.AiController;
import jDistance.ai.AiRule;
import jDistance.ai.CatchOppositeKingRule;
import jDistance.ai.PreventKingMoveRule;
import jDistance.ai.RandomRule;
import jDistance.model.Field;
import jDistance.model.King;
import jDistance.model.Slave;
import jDistance.model.Token;
import jDistance.util.CloseEvent;
import jDistance.util.ExpectTokenMoveEvent;
import jDistance.util.MessageHandler;
import jDistance.util.MoveTokenEvent;

import org.junit.Before;
import org.junit.Test;

public class AiControllerTest extends MessageHandler {

	protected AiController testMe = null;
	protected final Integer player = Integer.valueOf(1);	//Spieler hart auf Eins gesetzt
	private ArrayList<AiRule> rules = null;
	private Thread iamRunning = null;
	private ArrayList<Token> tokens = null;

	
	public AiControllerTest(){
		this.testMe = new AiController(this.player, this.rules);
	}
	
	
	@Before
	public void setUp() {
		// Initialisieren
		this.rules = new ArrayList<AiRule>(Arrays.asList(new RandomRule(100),
				new CatchOppositeKingRule(50), new PreventKingMoveRule(50)));
		//Token Liste initialisieren
		this.tokens = new ArrayList<Token>();
		this.tokens.add(new King(this.player, new Field(Integer.valueOf(1),Integer.valueOf(1))));
		this.tokens.add(new Slave(this.player, new Field(Integer.valueOf(2),Integer.valueOf(2))));
		this.tokens.add(new Slave(this.player, new Field(Integer.valueOf(3),Integer.valueOf(3))));
		this.tokens.add(new Slave(this.player, new Field(Integer.valueOf(4),Integer.valueOf(4))));
	}

	@Test
	public void controllerTest() {
		// Ist die Referenz null, ist der Konstruktor fehlgeschlagen
		assertNotSame(null, this.testMe);
	}

	@Test
	public void testRun() {
		try {
			this.connect(this.testMe);
			// Starten der run() Methode
			this.iamRunning = new Thread(this.testMe);
			this.iamRunning.start();
			// 100ms warten
			Thread.sleep(100);
			// beenden
			this.sendMessage(new CloseEvent());
			assertTrue(true);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	@Test
	public void testSendMessage() {
		this.connect(this.testMe);
		// Starten der run() Methode
		this.iamRunning = new Thread(this.testMe);
		this.iamRunning.start();
		//Ein ExpectedTokenMove Event
		this.sendMessage(new ExpectTokenMoveEvent(this.player, this.tokens,jDistance.model.PlayingGround.getPossibleMove(this.tokens.get(this.tokens.size() -1), this.tokens)));
		//abfragen der Antwort mit 100ms Timeout
		assertTrue(this.getNextMessage(100) instanceof MoveTokenEvent);
	}

}
