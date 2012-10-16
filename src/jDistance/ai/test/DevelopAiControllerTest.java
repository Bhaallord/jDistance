package jDistance.ai.test;

import jDistance.ai.MediumAiController;

public class DevelopAiControllerTest extends AiControllerTest {

	/*
	 * Alle vererbten Methoden werden implizit über JUnit getestet.
	 */
	public DevelopAiControllerTest(){
		this.testMe = new MediumAiController(this.player);
	}
	
}
