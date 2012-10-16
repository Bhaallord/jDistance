package jDistance.ai.test;

import jDistance.ai.EasyAiController;

public class BasicAiControllerTest extends AiControllerTest {

	/*
	 * Der Konstruktor wird mittels JUnit über Vererbung getestet und tauch somit
	 * im Testbericht als testAiController() auf.
	 */
	public BasicAiControllerTest(){
		this.testMe = new EasyAiController(this.player);
	}
	
}