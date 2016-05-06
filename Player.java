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

    //the constant for the default username
	public static final String DEFAULT_USERNAME = "username";

	//the user basic information variables
	private String userName;

	//the player's official name
	private Name realName;

	//the player's game statistics
	private Stats stats;

	public Player(){
		this(DEFAULT_USERNAME, new Name());
	}

	//constructing a instance with given player information
	public Player(String userName, Name realName){
		//set the player info with given strings
		this.userName = userName;
		this.realName = realName;
		this.stats = new Stats();
	}

	//constructing a player instance with username, realname and stats
	public Player(String userName, Name realName, Stats stats){
		this.userName = userName;
		this.realName = realName;
		this.stats = stats;
	}
	//getter of variable userName
	public String getUserName() {
		return userName;
	}

	//get the player's given name
	public String getGivenName(){
		return realName.getGivenName();
	}

	//edit the player's family name
	public void setFamilyName(String familyName){
		realName.setFamilyName(familyName);
	}

	//edit the player's given name
	public void setGivenName(String givenName){
		realName.setGivenName(givenName);
	}

	//get the player's winning ratio
	public float getWinningRatio(){
		return stats.getWinningRatio();
	}

	//get the player's drawn ratio
	public float getDrawnRatio(){
		return stats.getDrawnRatio();
	}

	//get the player's total number of game played
	public int getNumberOfGamePlayed(){
		return stats.getNumberOfGamePlayed();
	}

	//reset the player's game statistics
	public void resetStats(){
		stats.resetStats();
	}

	//add a winning match to the player's stats
	public void win(){
		stats.addWin();
	}

	//add a drawn match to player's stats
	public void draw(){
		stats.addDraw();
	}

	//add a lose match to player's stats
	public void lose(){
		stats.addLose();
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
				realName.getFamilyName() + "," +
				realName.getGivenName() + "," +
				stats.getNumberOfGamePlayed() + " games,"+
				stats.getNumberOfGameWon() + " wins," +
				stats.getNumberOfGameDrawn() + " draws";
	}

	@Override
	//get the copy of the player
	public Player clone(){
		return new Player(this.userName, this.realName.clone(), this.stats.clone());
	}


}