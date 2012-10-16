package jDistance.ai.test;

import static org.junit.Assert.*;

import java.io.PrintStream;

import jDistance.ai.EasyAiController;
import jDistance.ai.MediumAiController;
import jDistance.ai.ExpertAiController;
import jDistance.ai.RandomAiController;

import org.junit.Test;

public class AiCompareTest {

	@Test
	public void testRandomVsBasic() {
            System.out.println("testRandomVsBasic");
		AiTestController controller = new AiTestController(new RandomAiController(1),
				new EasyAiController(2));
		PrintStream out = System.out;
		System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
			public void write(int b) {
			}
		}));
		controller.run(100);
		System.setOut(out);
		System.out.println(controller.getScoreAi1());
		System.out.println(controller.getScoreAi2());
		System.out.println(controller.getRemie());
		assertTrue(0 < new Integer(controller.getScoreAi2()).compareTo(Integer.valueOf(controller.getScoreAi1())));
	}

	@Test
	public void testBasicVsDevelop() {
            System.out.println("testBasicVsDevelop");
		AiTestController controller = new AiTestController(new EasyAiController(1),
				new MediumAiController(2));
		PrintStream out = System.out;
		System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
			public void write(int b) {
			}
		}));
		controller.run(100);
		System.setOut(out);
		System.out.println(controller.getScoreAi1());
		System.out.println(controller.getScoreAi2());
		System.out.println(controller.getRemie());
		assertTrue(0 < new Integer(controller.getScoreAi2()).compareTo(Integer.valueOf(controller.getScoreAi1())));
	}
        
        @Test
	public void testDevelopVsExpert() {
            System.out.println("testDevelopVsExpert");
		AiTestController controller = new AiTestController(new MediumAiController(1),
				new ExpertAiController(2));
		PrintStream out = System.out;
		System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
			public void write(int b) {
			}
		}));
		controller.run(100);
		System.setOut(out);
		System.out.println(controller.getScoreAi1());
		System.out.println(controller.getScoreAi2());
		System.out.println(controller.getRemie());
		assertTrue(0 < new Integer(controller.getScoreAi2()).compareTo(Integer.valueOf(controller.getScoreAi1())));
	}
        
}
