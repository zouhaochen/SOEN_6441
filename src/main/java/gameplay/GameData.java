package gameplay;

import gameelements.Player;

import java.util.ArrayList;

/**
 * This class is a wrapper class containing the elements and the current states of the game
 */
public class GameData {

	private GamePhase d_currentPhase;
	private int d_TotalPlayer;
	private int d_total_country;
	private ArrayList<String> d_CountryName;
	private ArrayList<String> d_CountryList;
	private ArrayList<String> d_ContinentList;
	private ArrayList<Player> d_PlayerList;


	/**
	 * game data constructor
	 */
	public GameData() {
		this.d_currentPhase = GamePhase.WAITING_TO_TURN;
		this.d_PlayerList = new ArrayList<>();
	}


	public GamePhase getD_currentPhase() {
		return d_currentPhase;
	}

	public void setD_currentPhase(GamePhase d_currentPhase) {
		this.d_currentPhase = d_currentPhase;
	}

	public int getD_TotalPlayer() {
		return d_TotalPlayer;
	}

	public void setD_TotalPlayer(int d_TotalPlayer) {
		this.d_TotalPlayer = d_TotalPlayer;
	}

	public int getD_total_country() {
		return d_total_country;
	}

	public void setD_total_country(int d_total_country) {
		this.d_total_country = d_total_country;
	}

	public ArrayList<String> getD_CountryName() {
		return d_CountryName;
	}

	public void setD_CountryName(ArrayList<String> d_CountryName) {
		this.d_CountryName = d_CountryName;
	}

	public ArrayList<String> getD_CountryList() {
		return d_CountryList;
	}

	public void setD_CountryList(ArrayList<String> d_CountryList) {
		this.d_CountryList = d_CountryList;
	}

	public ArrayList<String> getD_ContinentList() {
		return d_ContinentList;
	}

	public void setD_ContinentList(ArrayList<String> d_ContinentList) {
		this.d_ContinentList = d_ContinentList;
	}

	public ArrayList<Player> getD_PlayerList() {
		return d_PlayerList;
	}

	public void setD_PlayerList(ArrayList<Player> d_PlayerList) {
		// set total player number to list length
		this.d_PlayerList = d_PlayerList;
		d_TotalPlayer=d_PlayerList.size();
	}


	public GamePhase getCurrentPhase() {
		return d_currentPhase;
	}

	public void setCurrentPhase(GamePhase p_currentPhase) {
		d_currentPhase = p_currentPhase;
	}
}
