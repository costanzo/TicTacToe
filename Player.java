/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: This class contain the basic information and 
*              game statistics of one single player.
*/

abstract class Player implements Comparable<Player>{
	//this constant is for the situation that the player does not play any game
	public static final float ZERO_RATIO = 0;

	//when reset the player result, use this as default number
	public static final int DEFAULT_GAME_NUMBER = 0;

	//the static constant is used for comparing player results
	public static final int BETTER = -1;
	public static final int WORSE = 1;

    //the constant for the default username
	public static final String DEFAULT_USERNAME = "username";
	public static final String DEFAULT_FAMILYNAME = "familyname";
	public static final String DEFAULT_GIVENNAME = "givenname";

	//the user basic information variables
	private String userName;

	private String familyName;
	private String givenName;

	//the user game statistics variables
	private int numberOfGamePlayed;
	private int numberOfGameWon;
	private int numberOfGameDrawn;

	public Player(){
		this(DEFAULT_USERNAME, DEFAULT_FAMILYNAME, DEFAULT_GIVENNAME);
	}

	//constructing a instance with given player information
	public Player(String userName, String familyName, String givenName){
		//set the player info with given strings
		this.userName = userName;
		this.familyName = familyName;
		this.givenName = givenName;
		this.numberOfGamePlayed = DEFAULT_GAME_NUMBER;
		this.numberOfGameDrawn = DEFAULT_GAME_NUMBER;
		this.numberOfGameWon = DEFAULT_GAME_NUMBER;
	}

	//constructing an instance with all the necessary information and stats
	public Player(String userName, String familyName, String givenName,
				  int numberOfGamePlayed, int numberOfGameWon, int numberOfGameDrawn){
		this.userName = userName;
		this.familyName = familyName;
		this.givenName = givenName;
		this.numberOfGameDrawn = numberOfGameDrawn;
		this.numberOfGameWon = numberOfGameWon;
		this.numberOfGamePlayed = numberOfGamePlayed;
	}

	//getter of variable userName
	public String getUserName() {
		return userName;
	}

	//get the player's given name
	public String getGivenName(){
		return this.givenName;
	}

	//edit the player's family name
	public void setFamilyName(String familyName){
		this.familyName = familyName;
	}

	//edit the player's given name
	public void setGivenName(String givenName){
		this.givenName = givenName;
	}

	//get the player's total number of game played
	public int getNumberOfGamePlayed(){
		return this.numberOfGamePlayed;
	}

	//reset the player's game statistics
	public void resetStats(){
		//all the game statistics will be set to the default value
		this.numberOfGamePlayed = DEFAULT_GAME_NUMBER;
		this.numberOfGameDrawn = DEFAULT_GAME_NUMBER;
		this.numberOfGameWon = DEFAULT_GAME_NUMBER;
	}

	//add a winning match to the player's stats
	public void win(){
		//total game number and winning game number plus one
		this.numberOfGamePlayed++;
		this.numberOfGameWon++;
	}

	//add a drawn match to player's stats
	public void draw(){
		//total game number and drawn game number plus one
		this.numberOfGamePlayed++;
		this.numberOfGameDrawn++;
	}

	//add a lose match to player's stats
	public void lose(){
		//only the total game number should plus one
		this.numberOfGamePlayed++;
	}

	//try to find if the two player is the same one
	public boolean equals(Player player){
		//if the user names of two player are the same, then they are the same players
		if(this.userName.equals(player.getUserName()))
			return true;
		else
			return false;
	}

	@Override
	//override the abstract method in the interface for comparison
	public int compareTo(Player player){
		//get the winning ration and drawn ratio for comparison
		float win = getWinningRatio();
		float drawn = getDrawnRatio();

		//make sure there won't be exception in subsequent process
		if(player == null)
			return BETTER;

		if(win > player.getWinningRatio()){
			//player with bigger winning ration is better
			return BETTER;
		}
		else if(win < player.getWinningRatio()){
			//player with smaller winning ratio is worse
			return WORSE;
		}
		else {
			//when the winning ratio is the same, compare the drawn ratio
			if (drawn > player.getDrawnRatio()){
				//player with higher drawn ratio is better
				return BETTER;
			}
			else if (drawn < player.getDrawnRatio()){
				//player with lower drawn ratio is worse
				return WORSE;
			}
			else {
				//if other statistics are the same, compare based on the competitor usernames
				String competitorUserName = player.getUserName();
				return this.userName.compareTo(competitorUserName);
			}
		}
	}

	@Override
	//override the toString to for displaying player info and statistics
	public String toString(){
		return userName + "," +
				familyName + "," +
				givenName + "," +
				numberOfGamePlayed + " games,"+
				numberOfGameWon + " wins," +
				numberOfGameDrawn + " draws";
	}

	public String getPlayerInfoStats(){
		return this.userName + " "
				+ familyName + " "
				+ givenName + " "
				+ numberOfGamePlayed + " "
				+ numberOfGameWon + " "
				+ numberOfGameDrawn + " "
				+ getClass();
	}

	public abstract Move makeMove(char[][] gameBoard);

	//get the winning ratio of the player, if no game played, return zero
	public float getWinningRatio(){
		if(numberOfGamePlayed != DEFAULT_GAME_NUMBER){
			//if at least one game played, return a float number as winning ratio
			return ((float)numberOfGameWon) / numberOfGamePlayed ;
		}
		else{
			//if no game played, return a float zero
			return ZERO_RATIO;
		}
	}

	//get the drawn ratio of the player, if no game played, return zero
	public float getDrawnRatio(){
		if(numberOfGamePlayed != DEFAULT_GAME_NUMBER){
			//if at least one game played, return a float number as drawn ratio
			return ((float)numberOfGameDrawn) / numberOfGamePlayed ;
		}
		else{
			//if no game played, return a float zero
			return ZERO_RATIO;
		}
	}
}