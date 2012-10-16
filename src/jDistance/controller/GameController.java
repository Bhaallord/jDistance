package jDistance.controller;

import jDistance.model.PlayingGround;
import jDistance.model.TokenMove;
import java.util.*;
import jDistance.util.*;

/**
 * Spiel Controller. Wertet Züge der Spieler aus und wertet die auftretteten
 * Event aus und gibt sie an die Spieler zurück. Sorgt für die Einhaltung der
 * Rheienfolge der Spieler.
 *
 * 
 */
public class GameController extends jDistance.util.MessageHandler implements Runnable {

    private int currentPlayer = 0;
    private PlayingGround playingGround = null;
    private boolean gameRun;
    private int time = 0;

    /**
     * Konstruktor
     */
    public GameController() {
        gameRun = false;

    }

    /**
     * Haupt Eventschleife. Empfängt Züge von den Spieler und versendet
     * aufforderungen für neue Züge.
     */
    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            jDistance.util.Event event = getNextMessage();
            if (event instanceof jDistance.util.CloseEvent) {
                this.sendMessage(event);
                return;
            }
            if (event instanceof jDistance.util.StartGameEvent) {
                jDistance.util.StartGameEvent startGameEvent = (StartGameEvent) event;
                this.playingGround = startGameEvent.getPlayingGround();
                if (this.playingGround == null) {
                    continue;
                }
                currentPlayer = startGameEvent.getStartPlayer();
                gameRun = true;
                this.sendMessage(new GameStartedEvent(this.playingGround.getTokens()));
                this.sendMessage(new ExpectTokenMoveEvent(currentPlayer, this.playingGround.getTokens(), this.playingGround.getPossibleMove()));
            }
            // MoveTokenEvent Behandlung
            if (event instanceof jDistance.util.MoveTokenEvent) {
                MoveTokenEvent moveTokenEvent = (MoveTokenEvent) event;
                if (moveTokenEvent.getPlayer() != currentPlayer) {
                    System.out.println("Spieler ist nicht an der Reihe!");
                    continue;
                }
                if (moveTokenEvent.getToken().getPlayer() != currentPlayer) {
                    System.out.println("Falscher Spielstein soll bewegt werden!");
                    continue;

                }
                System.out.println("-- " + (TokenMove) moveTokenEvent);
                List<Event> moveEvents = playingGround.moveToken(moveTokenEvent);
                // Fehlerhaften Zug überprüfen
                if (moveEvents.size() < 1) {
                    System.out.println("Fehlerhafte Zug von " + currentPlayer);
                }

                // Nach Win Event suchen
                for (Event moveEvent : moveEvents) {
                    // Nur WinEvent senden oder alles
                    if (moveEvent instanceof WinEvent) {
                        this.gameRun = false;
                        WinEvent winEvent = (WinEvent) moveEvent;
                        System.out.println("Spieler " + winEvent.getPlayer() + " hat gewonnen");
                        // this.sendMessage(winEvent);
                        // continue;
                    }
                    this.sendMessage(moveEvent);

                }


                int nextPlayer = this.currentPlayer + 1;
                if (nextPlayer > 2) {
                    nextPlayer = 1;
                }

                List<TokenMove> moves = this.playingGround.getPossibleMove(nextPlayer);
                if (moves.isEmpty() && gameRun) {
                    this.gameRun = false;
                    System.out.println("Spieler " + currentPlayer + " hat gewonnen");
                    this.sendMessage(new WinEvent(currentPlayer));

                }
                if (gameRun) {

                    this.currentPlayer++;
                    if (currentPlayer > 2) {
                        currentPlayer = 1;
                    }

                    if (this.time > 0) {
                        try {
                            Thread.sleep(time);
                        } catch (Exception e) {
                        }
                    }
                    this.sendMessage(new ExpectTokenMoveEvent(currentPlayer, this.playingGround.getTokens(), this.playingGround.getPossibleMove()));
                }
            }
        }
    }
}
