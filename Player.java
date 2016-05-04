/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
* Description: This class contain the basic information and 
*              game statistics of one single player.
*/

class Player implements Comparable<Player>{
	//the static constant is used for comparing player results
	public static final int BETTER = -1;
	public static final int WORSE = 1;

	//this constant is for the situation that the player does not play any game
	public static final float ZERO_RATIO = 0;

	//when reset the player result, use this as default number
	public static final int DEFAULT_GAME_NUMBER = 0;

	//when no user info is input, use these constant as default value
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
		this(DEFAULT_USERNAME,DEFAULT_FAMILYNAME,DEFAULT_GIVENNAME);
	}

	//constructing a instance with given player information
	public Player(String userName, String familyName, String givenName){
		//set the player info with given strings
		setUserName(userName);
		setFamilyName(familyName);
		setGivenName(givenName);

		//when a player is initialized, all the statistics should be set to default number
		setNumberOfGamePlayed(DEFAULT_GAME_NUMBER);
		setNumberOfGameWon(DEFAULT_GAME_NUMBER);
		setNumberOfGameDrawn(DEFAULT_GAME_NUMBER);
	}

	//getter of variable userName
	public String getUserName() {
		return userName;
	}

	//setter of variable userName
	public void setUserName(String userName) {
		this.userName = userName;
	}

	//setter of variable familyName
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	//getter of variable givenName
	public String getGivenName() {
		return givenName;
	}
	
	//setter of variable givenName
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	//get the total number of game played
	public int getNumberOfGamePlayed() {
		return numberOfGamePlayed;
	}

	//set the total number of game played
	public void setNumberOfGamePlayed(int numberOfGamePlayed) {
		this.numberOfGamePlayed = numberOfGamePlayed;
	}

	//set the number of the winning game played
	public void setNumberOfGameWon(int numberOfGameWon) {
		this.numberOfGameWon = numberOfGameWon;
	}

	//set the number of the drawn games played
	public void setNumberOfGameDrawn(int numberOfGameDrawn) {
		this.numberOfGameDrawn = numberOfGameDrawn;
	}

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
				return this.userName.compareToIgnoreCase(competitorUserName);
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

	//reset this player's game statistics
	public void resetStats(){
		//all the game statistics will be set to the default value
		this.numberOfGamePlayed = DEFAULT_GAME_NUMBER;
		this.numberOfGameDrawn = DEFAULT_GAME_NUMBER;
		this.numberOfGameWon = DEFAULT_GAME_NUMBER;
	}

	//when a player win a game, add a winning record to statistics
	public void addWin(){
		//total game number and winning game number plus one
		this.numberOfGamePlayed++;
		this.numberOfGameWon++;
	}

	//when a player gets a drawn, add a drawn record to statistics
	public void addDraw(){
		//total game number and drawn game number plus one
		this.numberOfGamePlayed++;
		this.numberOfGameDrawn++;
	}

	//when a player loses a game, add a lose record to statistics
	public void addLose(){
		//only the total game number should plus one
		this.numberOfGamePlayed++;
	}
}