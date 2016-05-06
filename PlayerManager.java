/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
* Description: this class contains all the Player instances
*              that will be recorded and methods to manulpilate
*              the players.
*/
import java.util.Arrays;

class PlayerManager{
	//the constants represent the game result 
    public static final int WIN = 1;
    public static final int LOSE = 2;
    public static final int DRAW = 3;
	
	//this is a flag used to indicate that no player is found
    public static final int NO_SUCH_PLAYERNUM = -1;
    public static final Player NO_SUCH_PLAYER = null;
	
	//the maximum player number that this player manager can handle
    public static final int DEFAULT_PLAYER_CAPACITY = 100;
	
	//the initialized the player number of the player manager
    public static final int RESET_PLAYER_NUMBER = 0;
	
	//in remove and reset methods, the indicator for proceeded action
    public static final String
            CONFIRM_REMOVE_ALL = "Are you sure you want to remove all players? (y/n)";
    public static final String
            CONFIRM_RESET_ALL = "Are you sure you want to reset all player statistics? (y/n)";
			
	//confirmation reply of the program user			
    public static final String CONFIRM_ANSWER = "y";
	
	//error information when an error occurs
    public static final String
            USERNAME_EXISTS_ERROR = "The username has been used already.";
    public static final String
            PLAYER_NOT_EXISTS_ERROR = "The player does not exist.";

	//the player array and the total number of players
    private Player[] playerArray;
    private int playerTotalNum;

	//constructor for a default maximum player number, which is 100
    public PlayerManager(){
        this(DEFAULT_PLAYER_CAPACITY);
    }

	//constructor for a given maximum player number
	public PlayerManager(int totalPlayers){
        playerArray = new Player[totalPlayers];
        resetPlayerManager();
    }

    //get the player instance of the player
    public Player getPlayer(String userName){
        //find the player's index first
        int playerNum = findPlayerNum(userName);

        if(playerNum != NO_SUCH_PLAYERNUM) {
            //return the player that is needed
            return playerArray[playerNum].clone();
        }
        else{
            return NO_SUCH_PLAYER;
        }
    }

	//remove all the players in the array
    private void resetPlayerManager(){
        playerTotalNum = RESET_PLAYER_NUMBER;
    }

	//find a player given its userName, if not existent, return -1
    private int findPlayerNum(String userName){
        String playerUserName;
        if(userName == null){
			//no username will be null
		    return NO_SUCH_PLAYERNUM;
		}
        for(int i = 0; i < playerTotalNum; i++){
			//get the username of player i
            playerUserName = playerArray[i].getUserName();
            if(playerUserName.equals(userName)){
				//if the username is the same as the required one, return its index
                return i;
			}
        }

		//if can not find the player, just return -1
        return NO_SUCH_PLAYERNUM;
    }

	//add the given Player instance to the player list
    public void addPlayer(Player player){
		//if the player is null, do nothing
        if(player == null){
            return;
		}
		
        String playerUserName = player.getUserName();
        if(findPlayerNum(playerUserName) != NO_SUCH_PLAYERNUM){
			//if the player already exists, print an error
            System.out.println(USERNAME_EXISTS_ERROR);
		}
        else{
			//if the player does not exist, add this player to the list
            playerArray[playerTotalNum] = player;
			
			//increment the total player number with one
            playerTotalNum++;
        }
    }

	//execute the remove command, remove a given player or remove all
    public void removePlayer(String userName){
        if(userName == null){
			//if no name given, ask if remove all the players
            System.out.println(CONFIRM_REMOVE_ALL);
            String answer = TicTacToe.scanner.nextLine();
			
            if(answer.equals(CONFIRM_ANSWER)){
				//remove all the players if user confirms
                removeAllPlayers();
			}
        }
        else{
			//if one name given, try to get the player index first
            int playerNum = findPlayerNum(userName);
			
            if(playerNum == NO_SUCH_PLAYERNUM){
				//player does not exist, print error information
                System.out.println(PLAYER_NOT_EXISTS_ERROR);
			}
            else{
				//remove the specific player
			    removeOnePlayer(playerNum);
			}
        }
    }

	//remove one player 
    private void removeOnePlayer(int playerNum){
        for(int i = playerNum; i < playerTotalNum - 1; i++){
			//move all the player one step forward to cover the removed player
            playerArray[i] = playerArray[i + 1];
		}
		
		//decrement the total player number with one
        playerTotalNum --;
    }

	//after confirmation, remove all the player in the array
    private void removeAllPlayers(){
		//reset the player manager to the initialized condition
        resetPlayerManager();
    }

	//edit one player with given family name and given name
    public void editPlayer(String userName, String familyName, String givenName){
		//trying to get the player index first
        int playerNo = findPlayerNum(userName);
		
        if(playerNo == NO_SUCH_PLAYERNUM){
			//if no player found, print an error message
            System.out.println(PLAYER_NOT_EXISTS_ERROR);
		}
        else{
			//edit the players family name and given name
            playerArray[playerNo].setFamilyName(familyName);
            playerArray[playerNo].setGivenName(givenName);
        }
    }

	//reset the statistics of one or all players
    public void resetStats(String userName){
        if(userName == null){
			//ask the user if reset all players
            System.out.println(CONFIRM_RESET_ALL);
            String answer = TicTacToe.scanner.nextLine();
			
            if(answer.equals(CONFIRM_ANSWER)){
				//reset all player statistics after confirmation
                resetAllStats();
			}
        }
        else{
			//get the player index first
            int playerNo = findPlayerNum(userName);
            if( playerNo == NO_SUCH_PLAYERNUM){
				//if no player found, print an error message
                System.out.println(PLAYER_NOT_EXISTS_ERROR);
			}
            else{
				//reset statistics of specific player
                resetOneStats(playerNo);
			}
        }
    }

	//reset statistics of all players
    private void resetAllStats(){
        for( int i = 0; i < playerTotalNum; i++ ){
			//reset every single player in this array
            playerArray[i].resetStats();
		}
    }

	//reset the player statistics based on its index
    private void resetOneStats(int playerNum){
		//reset player statistics given its index in the array
        playerArray[playerNum].resetStats();
    }

	//display one or all player informaiton with required format
    public void displayPlayer(String userName){
        if(userName == null){
			//if no username given, display all users
            displayAllPlayers();
        }
        else{
			//get the player index in the array first
            int playerNo = findPlayerNum(userName);
            if(playerNo == NO_SUCH_PLAYERNUM){
				//if no player found, print an error message
                System.out.println(PLAYER_NOT_EXISTS_ERROR);
			}
            else{
				//display the player according to its index in array
                displayOnePlayer(playerNo);
			}
        }
    }

	//display all player information and statistics
    private void displayAllPlayers(){
		//create an array that contain all the players' usernames
        String[] userName = new String[playerTotalNum];
		
        for(int i = 0; i < playerTotalNum; i++){
			//copy usernames of the array into the new String array
            userName[i] = playerArray[i].getUserName();
		}
		
		//sort the array according to alphabetical sequence 
        Arrays.sort(userName);
		
		//print out all the player information alphabetically
        for(int i = 0; i< playerTotalNum; i++){
			int playerNum = findPlayerNum(userName[i]);
            System.out.println(playerArray[playerNum].toString());
		}
    }

	//display information and statistics of one player given the index
    private void displayOnePlayer(int playerNum){
        System.out.println(playerArray[playerNum].toString());
    }

	//display the ranking of all the players
    public void displayRankings(){
		//first print the headers
        System.out.println(" WIN  | DRAW | GAME | USERNAME");
		
		//sort the whole player array using defined comparison method
        Arrays.sort(playerArray, 0, playerTotalNum);
		
		//print out all the player game statistics
        for(int i = 0; i < playerTotalNum ; i++){
			//format the game statistics with required format
            formatResult(playerArray[i]);
		}
    }

	//format the game statistics of certain player
    private void formatResult(Player player){
		//print the winning ratio into XX% format 
        System.out.printf(" %3d", (int)(player.getWinningRatio()*100));
        System.out.print("% | ");
		
		//print the drawn ratio into XX% format
        System.out.printf("%3d", (int)(player.getDrawnRatio()*100));
        System.out.print("% | ");
		
		//print the number of game played into XX format
        System.out.printf("%2d", player.getNumberOfGamePlayed());
        System.out.print("   | ");
		
		//print the username and start a new line
        System.out.print(player.getUserName());
        System.out.println();
    }

	//when a game finishes, store the game result into the player instance respectively
    public void storeGameResult(String player1UserName, String player2UserName, int resultStats){
		//first get the player indexes of the two player
        int player1Num = findPlayerNum(player1UserName);
        int player2Num = findPlayerNum(player2UserName);
		
		//change to statistics of two palyers
        switch (resultStats){
            case WIN:
			    //player 1 wins player 2
                playerArray[player1Num].win();
                playerArray[player2Num].lose();
                break;
            case LOSE:
			    //player 2 wins player 1
                playerArray[player1Num].lose();
                playerArray[player2Num].win();
                break;
            case DRAW:
			    //the two players get drawn
                playerArray[player1Num].draw();
                playerArray[player2Num].draw();
                break;
            default:
        }
    }

}