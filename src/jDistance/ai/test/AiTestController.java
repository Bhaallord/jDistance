/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jDistance.ai.test;

import jDistance.ai.AiController;
import jDistance.controller.GameController;
import jDistance.util.MessageHandler;
import jDistance.util.StartGameEvent;
import jDistance.util.WinEvent;

public class AiTestController extends MessageHandler {

    private AiController ai1;
    private AiController ai2;
    private GameController gameController;
    private int scoreAi1;
    private int scoreAi2;
    private int remie;

    public AiTestController(AiController ai1, AiController ai2) {
        this.ai1 = ai1;
        this.ai2 = ai2;
        this.gameController = new GameController();
        new Thread(this.gameController).start();
        new Thread(this.ai1).start();
        new Thread(this.ai2).start();
        gameController.connect(this.ai1);
        gameController.connect(this.ai2);
        
        scoreAi1 = 0;
        scoreAi2 = 0;
        remie=0;
    }

    void run(int iterations) {
        gameController.connect(this);
        if (iterations < 1) {
            return;
        }
        int index = 0;
        while (index < iterations) {
            this.sendMessage(new StartGameEvent(index % 2+1));
            int maxTurns=0;
            while (true) {
                jDistance.util.Event event = getNextMessage();
                if (event instanceof WinEvent) {
                    WinEvent winEvent= (WinEvent)event;
                    if(winEvent.getPlayer()==1){
                        scoreAi1++;
                    }
                    else{
                        scoreAi2++;
                    }
                    break;

                }
                if(maxTurns>10000){
                    this.ai1.addEvent(new WinEvent(0));
                    this.ai2.addEvent(new WinEvent(0));
                    remie++;
                    break;
                }
                maxTurns++;
            }
            index++;
        }
        gameController.disconnect(this);
    }
    
    public int getScoreAi1(){
        return this.scoreAi1;
    }
    
    public int getRemie(){
        return this.remie;
    }
    
    public int getScoreAi2(){
        return this.scoreAi2;
    }
}
