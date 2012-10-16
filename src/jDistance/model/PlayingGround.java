package jDistance.model;

import jDistance.util.TokenLockedEvent;
import jDistance.util.TokenMovedEvent;
import jDistance.util.TokenSwapedEvent;
import jDistance.util.WinEvent;

import java.util.*;

/**
 * Zentrale Model Klasse. Speichert die aktuelle Aufstellung der Spielfiguren und berechnet mögliche Züge.
 * 
 */
public class PlayingGround {
	private List<Token> tokens = new ArrayList<Token>();

    

	/**
	 * @brief Standartkonstruktor
	 * 
	 *        Legt ein neues Spielfeld an und stellt die Figuren auf ihre
	 *        Ausgangspositionen.
	 */
	public PlayingGround() {
		this.resetGame();
	}
	
	/**
	 * @brief PlayingGround mit definierten Tokens
	 * 
	 *        Legt ein neues Spielfeld an und fügt die übergebene Spielfigurn zum Spielfeld hinzu
	 */
	public PlayingGround(java.util.List<Token> tokens) {
		for (Token token : tokens) {
			this.tokens.add(token.clone());
		}
	}

	/**
	 * @brief Gibt eine Liste aller Spielfigurn zurück
	 * 
	 *        Diese Methode gibt eine Kopie aller Figuren zurück
	 * @return Spielfigurn Liste
	 */
	public List<Token> getTokens() {
		List<Token> returnList = new java.util.ArrayList<Token>();
		for (Token token : this.tokens) {
			returnList.add(token.clone());
		}
		return returnList;
	}

	/**
	 * @brief Gibt eine Liste aller Spielfigurn eines Spielers zurück
	 * 
	 *        Diese Methode gibt eine Kopie aller Figuren eines Spielers zurück
	 * @return Spielfigurn Liste
	 */
	public List<Token> getTokens(int player) {
		List<Token> returnList = new java.util.ArrayList<Token>();
		for (Token token : this.tokens) {
			if (token.getPlayer() == player) {
				returnList.add(token.clone());
			}
		}
		return returnList;
	}

	/**
	 * @brief Setzt das Spielfeld zurück
	 * 
	 *        Alle Figuren werden auf ihre Startposition zurückgesetzt und sind
	 *        nicht mehr gefangen
	 */
	private void resetGame() {
		// Delete all tokens
		this.tokens.clear();

		// Create first player's tokens at their initial positions...
		this.tokens.add(new Slave(1, new Field(1, 0)));
		this.tokens.add(new King(1, new Field(2, 0)));
		this.tokens.add(new Slave(1, new Field(4, 0)));
		this.tokens.add(new Slave(1, new Field(5, 0)));

		// Create second player's tokens at their initial positions...
		this.tokens.add(new Slave(2, new Field(6, 7)));
		this.tokens.add(new King(2, new Field(5, 7)));
		this.tokens.add(new Slave(2, new Field(3, 7)));
		this.tokens.add(new Slave(2, new Field(2, 7)));
	}

	/**
	 * @brief Berechnet alle möglichen Distancen einer Spielfigur
	 * 
	 *        Berechnet alle möglichen Distancen einer Spielfigur. Diese
	 *        Funktion filtert alle gegnerischen Distancen und ignoriert wenn
	 *        die Spielfigur token mit in other Liste steht.
	 * 
	 * @return Liste aller möglichen Distancen
	 */
	public static java.util.List<Distance> getDistance(Token token, java.util.List<Token> other) {
		java.util.List<Distance> distances = new ArrayList<Distance>();

		if (token.isLocked()) {
			return distances;
		}

		int indexDistance = 0;
		while (indexDistance < other.size()) {
			if (other.get(indexDistance).equals(token)) {
				indexDistance++;
				continue;
			} else if (other.get(indexDistance).getPlayer() != token.getPlayer()) {
				indexDistance++;
				continue;
			}
			int indexDistance2 = indexDistance + 1;
			while (indexDistance2 < other.size()) {
				if (other.get(indexDistance2).equals(token)) {
					indexDistance2++;
					continue;
				} else if (other.get(indexDistance2).getPlayer() != token.getPlayer()) {
					indexDistance2++;
					continue;
				}
				// Verhindere doppelte Distancen
				Distance currentDistance = other.get(indexDistance).getPos()
						.distanceTo(other.get(indexDistance2).getPos());
				boolean exist = false;
				for (Distance lastDistance : distances) {
					if (currentDistance.equals(lastDistance)) {
						
						exist = true;
						break;
					}
				}
				if (!exist) {
					// Neuer Abstand hinzufügen
					distances.add(currentDistance);
				}
				indexDistance2++;
			}
			indexDistance++;
		}
		return distances;
	}

	/**
	 * @brief Berechnet alle möglichen Züge einer Spielfigur
	 * @return Liste aller möglichen Züge
	 */
	public static java.util.List<TokenMove> getPossibleMove(Token token, java.util.List<Token> other) {
		java.util.Set<TokenMove> result = new HashSet<TokenMove>();

		if (token.isLocked()) {
			return new java.util.ArrayList<TokenMove>(result);
		}
		// mögliche Distancen berechnene
		java.util.List<Distance> distances = getDistance(token, other);
		// Liste der Züge berechnen

		int indexDistanceList = 0;
		while (indexDistanceList < distances.size()) {
			Distance distance = distances.get(indexDistanceList);
			int indexX = 1;
			while (indexX > -2) {
				int indexY = 1;
				while (indexY > -2) {
					// Normal
					Field newField = new Field(token.getPos().getX() + distance.getX() * indexX,
							token.getPos().getY() + distance.getY() * indexY);

					if (PlayingGround.isFieldPosible(newField)) {
						result.add(new TokenMove(token.clone(), newField));
					}
					// Umgekehrte Distance
					Field newField2 = new Field(token.getPos().getX() + distance.getY() * indexX,
							token.getPos().getY() + distance.getX() * indexY);

					if (PlayingGround.isFieldPosible(newField2)) {
						result.add(new TokenMove(token.clone(), newField2));
					}

					indexY = indexY - 2;
				}
				indexX = indexX - 2;
			}
			indexDistanceList++;
		}

		return new java.util.ArrayList<TokenMove>(result);
	}

	/**
	 * @brief Gibt alle möglichen Züge des Spielfeldes zurück
	 * @return Liste aller möglichen Züge
	 */
	public java.util.List<TokenMove> getPossibleMove() {
		java.util.List<TokenMove> result = new ArrayList<TokenMove>();

		int maxPlayerNummer = 0;
		for (Token token : this.tokens) {
			if (token.getPlayer() > maxPlayerNummer) {
				maxPlayerNummer = token.getPlayer();
			}
		}

		int index = 0;
		while (index < maxPlayerNummer) {
			result.addAll(getPossibleMove(index + 1));
			index++;
		}

		return result;
	}

	/**
	 * @brief Gibt alle möglichen Züge eines Spielers zurück
	 * @return Liste aller möglichen Züge
	 */
	public java.util.List<TokenMove> getPossibleMove(int player) {
		java.util.List<TokenMove> result = new ArrayList<TokenMove>();

		java.util.List<Token> tokens = getTokens(player);
		for (Token token : tokens) {
			result.addAll(PlayingGround.getPossibleMove(token, tokens));
		}

		return result;
	}

	/**
	 * @brief Überprüft ob ein Feld noch im Spielfeld liegt
	 * @return gibt true zurück wenn ein Feld noch im Spielfeld liegt
	 */
	public static boolean isFieldPosible(Field field) {
		if (field.getX() > 7 || field.getX() < 0) {
			return false;
		}
		if (field.getY() > 7 || field.getY() < 0) {
			return false;
		}
		return true;
	}

	/**
	 * @brief Gibt die Spielfigur zurück die auf den aktuellen field zurück
	 * @return gibt die Spielfigur zurück die auf den Feld field steht oder
	 *         null wenn nichts gefunden worden ist
	 */
	public Token getTokenOnField(Field field) {
		return getTokenOnField(field, this.tokens);
	}

	/**
	 * @brief Gibt die Spielfigur zurück die auf den aktuellen field zurück
	 * @return gibt die Spielfigur zurück die auf den Feld field steht oder
	 *         null wenn nichts gefunden worden ist
	 */
	public static Token getTokenOnField(Field field, java.util.List<Token> tokens) {
		for (Token token : tokens) {
			if (token.getPos().equals(field)) {
				return token.clone();
			}
		}
		return null;
	}
	
	/**
	 * @brief Bewegt eine Spielfigur auf das übergebene Feld
	 * @return gibt eine Liste von Ereignissen zurück die bei der Bewegung aufgetretten sind
	 */
	public java.util.List<jDistance.util.Event> moveToken(Token token,Field destination) {
		return this.moveToken(new TokenMove(token,destination));
	}
	
	
	/**
	 * @brief Bewegt eine Spielfigur auf das übergebene Feld
	 * @return gibt eine Liste von Ereignissen zurück die bei der Bewegung aufgetreten sind
	 */
	public java.util.List<jDistance.util.Event> moveToken(TokenMove move) {
		java.util.List<jDistance.util.Event> result= new ArrayList<jDistance.util.Event>();
		for (Token token : this.tokens) {
			if (token.equals(move.getToken())) {
				// Zug validieren
				for (TokenMove possibleMove : getPossibleMove(token, this.tokens)) {
					if (possibleMove.equals(move)) {
						//Wenn ich hier bin ist der Zug validiert denn die Spielfigur in TokenMove machen will
						
						//Jetzt hole ich mir eine Reference auf die Spielfigur die auf dem Zielfeld steht(wenn vorhanden)
						final Token destinationTokenOld=this.getTokenOnField(move.getDestination());
						Token destinationTokenReference=null;
						for(Token destinationTokenReferenceSearch:this.tokens){
							if(destinationTokenReferenceSearch.equals(destinationTokenOld)){
								destinationTokenReference=destinationTokenReferenceSearch;
							}
						}
						
						//Zielfeld ist leer
						if(destinationTokenReference==null){
							result.add(new TokenMovedEvent(move.getToken(), move.getDestination()));
							token.move(move.getDestination());
						}
						//Zielfeld steht eine eigene Figure
						else if(destinationTokenOld.getPlayer()==token.getPlayer()){
							result.add(new TokenSwapedEvent(move, new TokenMove(destinationTokenOld,move.getToken().getPos())));
							//Zielfigure ist gefangen und wird befreit
							if(token instanceof King&&destinationTokenOld.isLocked()){
								Token newToken=destinationTokenOld.clone();
								newToken.move(move.getToken().getPos());
								result.add(new TokenLockedEvent(newToken));
								destinationTokenReference.setLocked(false);
							}
							destinationTokenReference.move(token.getPos());
							token.move(move.getDestination());
							
						}
						//Zielfeld steht eine gegnerische Figure
						else if(destinationTokenOld.getPlayer()!=token.getPlayer()){
							result.add(new TokenSwapedEvent(move, new TokenMove(destinationTokenOld,move.getToken().getPos())));	
							//Zielfigure ist frei und wird gefange
							if(!destinationTokenOld.isLocked()){
								Token newToken=destinationTokenOld.clone();
								newToken.move(move.getToken().getPos());
								result.add(new TokenLockedEvent(newToken));
								destinationTokenReference.setLocked(true);
							}						
							//Zielfigure ist der gegnerische König
							if(destinationTokenReference instanceof King){
								result.add(new WinEvent(token.getPlayer()));
							}
							destinationTokenReference.move(token.getPos());
							token.move(move.getDestination());
							
						}
						else{
							System.err.println("Modell Berechnungsfehler");
						}
						
						//Um Nebeneffekte zu vermeiden
						return result;
					}
				}
			}
		}
		return result;
	}
        
    /**
     * Gibt alle Daten als String formatiert zurück
     *
     * @return Datenstring
     */
    @Override
    public String toString() {
        return "PlayingGround{" + "tokens=" + tokens + '}';
    }
    
    

}
