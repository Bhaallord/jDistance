package jDistance.gui;

/**
 * @brief Abstrakte GUI Hauptklasse
 *
 * Diese Klasse stellt notwendige MessageHandler- sowie
 * Spielermodi-Funktionalitäten zur Verfügung
 *
 */
public abstract class GUI extends jDistance.util.MessageHandler implements Runnable {

	
	/**
	 * PlayerMode gibt die Art der GUI Beteiligung bekannt.
	 * 
	 * Mögliche Werte sind:
	 * 
	 * WHITE_ONLY -> Nur Spieler Weiß wird von GUI gesteuert
	 * BLACK_ONLY -> Nur Spieler Schwarz wird von GUI gesteuert
	 * BOTH -> Beide Spieler werden von GUI gesteuert
	 * NONE -> Kein Spieler wird von GUI gesteuert (KI vs KI) 
	 */
	public static enum PlayerMode {WHITE_ONLY, BLACK_ONLY, BOTH, NONE}
	
	
	protected PlayerMode playerMode = PlayerMode.NONE;

	public PlayerMode getPlayerMode() {
		return playerMode;
	}

	public void setPlayerMode(PlayerMode mode) {
		this.playerMode = mode;
	}
	
	/**
	 * Ermittelt ob die GUI für den aktuellen Spielzug verantwortlich ist
	 * @param currentPlayerId ID des aktuellen Spielers ( 1 | 2 )
	 * @return boolean Angabe ob GUI an der Reihe
	 */
	public boolean isMyTurn(int currentPlayerId) {
		
		if (playerMode.equals(PlayerMode.BOTH)) return true;
		if (playerMode.equals(PlayerMode.NONE)) return false;
		
		int myId = playerMode.equals(PlayerMode.WHITE_ONLY) ? 1 : 2;
		
		if ( myId == currentPlayerId) return true;
		
		return false;
	}
	
	/**
	 * Methode zum Loggen von Meldungen.
	 * 
	 * Diese Methode ergänzt automatisch die verwendete GUI.
	 * 
	 * @param msg Zu loggende Nachricht
	 */
	protected void log(String msg) {
		System.out.println("LOG: "+this.getClass().getName()+": "+msg);
	}
	
}