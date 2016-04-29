/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
*/
class Player implements Comparable<Player>{
	public static final int BETTER = -1;
	public static final int WORSE = 1;
	public static final float ZERO_RATIO = 0;
	public static final int DEFAULT_GAME_NUMBER = 0;

	public static final String DEFAULT_USERNAME = "username";
	public static final String DEFAULT_FAMILYNAME = "familyname";
	public static final String DEFAULT_GIVENNAME = "givenname";

	private String userName;
	private String familyName;
	private String givenName;
	private int numberOfGamePlayed;
	private int numberOfGameWon;


	private int numberOfGameDrawn;
	
	public Player(){
		this(DEFAULT_USERNAME,DEFAULT_FAMILYNAME,DEFAULT_GIVENNAME);
	}
	
	public Player(String userName, String familyName, String givenName){
		setUserName(userName);
		setFamilyName(familyName);
		setGivenName(givenName);
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

	public float GetWinningRatio(){
		if(numberOfGamePlayed != DEFAULT_GAME_NUMBER)
		    return ((float)numberOfGameWon) / numberOfGamePlayed ;
		else
			return ZERO_RATIO;
	}

	public float GetDrawnRatio(){
		if(numberOfGamePlayed != DEFAULT_GAME_NUMBER)
			return ((float)numberOfGameDrawn) / numberOfGamePlayed ;
		else
			return ZERO_RATIO;
	}

	@Override
	public int compareTo(Player player){
		float win = GetWinningRatio();
		float drawn = GetDrawnRatio();

		if(player == null)
			return BETTER;

		if(win > player.GetWinningRatio())
			return BETTER;
		else if(win < player.GetWinningRatio())
			return WORSE;
		else {
			if (drawn > player.GetDrawnRatio())
				return BETTER;
			else if (drawn < player.GetDrawnRatio())
				return WORSE;
			else {
				String competitorUserName = player.getUserName();
				return this.userName.compareToIgnoreCase(competitorUserName);
			}
		}
	}

	@Override
	public String toString(){
		return userName + "," +
				familyName + "," +
				givenName + "," +
				numberOfGamePlayed + " games,"+
				numberOfGameWon + " wins," +
				numberOfGameDrawn + " draws";
	}

	public void ResetStats(){
		this.numberOfGamePlayed = DEFAULT_GAME_NUMBER;
		this.numberOfGameDrawn = DEFAULT_GAME_NUMBER;
		this.numberOfGameWon = DEFAULT_GAME_NUMBER;
	}

	public void addWin(){
		int numberPlayed = getNumberOfGamePlayed();
		int numberWin = getNumberOfGameWon();
		setNumberOfGamePlayed(numberPlayed++);
		setNumberOfGameWon(numberWin++);
	}

	public void addDraw(){
		int numberPlayed = getNumberOfGamePlayed();
		int numberDraw = getNumberOfGameDrawn();
		setNumberOfGamePlayed(numberPlayed++);
		setNumberOfGameDrawn(numberDraw++);
	}

	public void addLose(){
		int numberPlayed = getNumberOfGamePlayed();
		setNumberOfGamePlayed(numberPlayed++);
	}
}