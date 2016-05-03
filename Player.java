/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
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

	public String getUserName() {
		return userName;
	}

	private void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public int getNumberOfGamePlayed() {
		return numberOfGamePlayed;
	}

	private void setNumberOfGamePlayed(int numberOfGamePlayed) {
		this.numberOfGamePlayed = numberOfGamePlayed;
	}

	private void setNumberOfGameWon(int numberOfGameWon) {
		this.numberOfGameWon = numberOfGameWon;
	}

	private void setNumberOfGameDrawn(int numberOfGameDrawn) {
		this.numberOfGameDrawn = numberOfGameDrawn;
	}

	public int getNumberOfGameDrawn() {
		return numberOfGameDrawn;
	}

	public int getNumberOfGameWon() {
		return numberOfGameWon;
	}

	//get the winning ratio of the player, if no game played, return zero
	public float GetWinningRatio(){
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
	public float GetDrawnRatio(){
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
		float win = GetWinningRatio();
		float drawn = GetDrawnRatio();

		//make sure there won't be exception in subsequent process
		if(player == null)
			return BETTER;

		if(win > player.GetWinningRatio()){
			//player with bigger winning ration is better
			return BETTER;
		}
		else if(win < player.GetWinningRatio()){
			//player with smaller winning ratio is worse
			return WORSE;
		}
		else {
			//when the winning ratio is the same, compare the drawn ratio
			if (drawn > player.GetDrawnRatio()){
				//player with higher drawn ratio is better
				return BETTER;
			}
			else if (drawn < player.GetDrawnRatio()){
				//player with lower drawn ratio is worse
				return WORSE;
			}
			else {
				//
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
	public void ResetStats(){
		this.numberOfGamePlayed = DEFAULT_GAME_NUMBER;
		this.numberOfGameDrawn = DEFAULT_GAME_NUMBER;
		this.numberOfGameWon = DEFAULT_GAME_NUMBER;
	}

	//when a player win a game, add a winning record to statistics
	public void addWin(){
		this.numberOfGamePlayed++;
		this.numberOfGameWon++;
	}

	//when a player gets a drawn, add a drawn record to statistics
	public void addDraw(){
		this.numberOfGamePlayed++;
		this.numberOfGameDrawn++;
	}

	//when a player loses a game, add a lose record to statistics
	public void addLose(){
		this.numberOfGamePlayed++;
	}
}