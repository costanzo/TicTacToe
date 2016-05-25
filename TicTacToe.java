/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: TicTacToe class contains the main class used
*              to conduct the game, including process user
*              input and using other classes to handle the 
*              command.
*/
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

class TicTacToe{
	//define two delimiters to split the user inputs
    public static final String COMMAND_DELIMITER = " ";
    public static final String CONTENT_DELIMITER = ",";

    //enumerate all command types
    private enum Command{
        EXIT,              //exit the system
        ADDPLAYER,        //add a player to the playerManager
        REMOVEPLAYER,     //remove one or all players from playerManager
        EDITPLAYER,       //edit the player's family name and given name
        RESETSTATS,       //reset one or all player stats
        DISPLAYPLAYER,    //display one or more players
        RANKINGS,          //show the rankings of all players
        PLAYGAME,           //make two players play TicTacToe game
        ADDAIPLAYER         //add an AI player to player list
    }

	//scanner to collect user input 
    public static Scanner scanner;
	
	//the command type for processing, such as addplayer
    private Command commandType ;
	
	//the command execution target or edition content
    private String commandContent ;
	
	//PlayerManager instance to manipulate the players
    private PlayerManager playerManger;

    //GameManager instance for handling TicTacToe game
    private GameManager gameManager;

    public static void main(String[] args){
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();
	}
	
	//constructor for a default system 
	public TicTacToe(){
        TicTacToe.scanner = new Scanner(System.in);
        this.playerManger = new PlayerManager();
        this.gameManager = new GameManager();
	}
	
	//run the whole TicTacToe game
	public void run(){
		//initialize the UI
		systemInitialisation();

        do {
			//this block is going to process all the exceptions
            try {
                //collect and split the user inputs
                processInput();

                //choose the exact action to be invoked according to command type
                chooseAction();
            }
            catch (InvalidCommandException ice){
				//when an invalid command exception occur,
                //show the message and return to prompt
                System.out.println(ice.getMessage());
            }
            catch (InvalidArgumentNumberException iane){
				//when argument number is less than expected
				//show the error message and return to prompt
                System.out.println(iane.getMessage());
            }
			
			//show the command prompt in the UI 
            showCommandPrompt();
        }while(true);
    }

	//show the initialization UI
    private void systemInitialisation(){
        System.out.println("Welcome to Tic Tac Toe!");
        showCommandPrompt();
    }

	//collect and split the user input, throw exception if command is invalid
    private void processInput() throws InvalidCommandException{
		//get the user inputs
        String input = TicTacToe.scanner.nextLine();

        StringTokenizer stOfInput = new StringTokenizer(input,COMMAND_DELIMITER);

		//get the command type from the input and change it into enumerate type
        String commandTypeString = stOfInput.nextToken();

        try {
			//try to find if the input is a valid command
            this.commandType = Command.valueOf(commandTypeString.toUpperCase());
        }
        catch (Exception e){
			//throw the exception and escape the method
            throw new InvalidCommandException(commandTypeString);
        }
		
		//get the command content
        if(stOfInput.hasMoreTokens()){
			//if there are command contents, get them as a whole
            this.commandContent = stOfInput.nextToken();
		}
        else{
			//no command content is given
            this.commandContent = null;
		}
    }

	/*take actions according to the user input command type and throw exception
	if the argument number is less than expected*/
    private void chooseAction() throws InvalidArgumentNumberException{
		//find the suitable action from the command type
        switch(commandType){
            case EXIT :           //exit the system
                exit();
                break;
            case ADDPLAYER :      //add a player to the 
                addPlayer();
                break;
            case REMOVEPLAYER:    //remove one or all players
                removePlayer();
                break;
            case EDITPLAYER:      //edit player information
                editPlayer();
                break;
            case RESETSTATS:      //reset stats of one or all players
                resetStats();
                break;
            case DISPLAYPLAYER:   //display one or all players
                displayPlayer();
                break;
            case RANKINGS:        //display the ranking of all players
                rankings();
                break;
            case PLAYGAME:        //make two players play the game
                playGame();
                break;
            case ADDAIPLAYER:     //add an AI player
                addPlayer();
                break;
            default:
        }
    }

	//show the command prompt to ask for user input
    private void showCommandPrompt(){
        System.out.println();
        System.out.print(">");
    }

	//exit the game 
    private void exit(){
		//first close the scanner 
        TicTacToe.scanner.close();

		//when system exit, save all the data to a local file
        playerManger.recordPlayerStats();
		
        System.out.println();
        System.exit(0);
    }

	//add a player to the PlayerManager instance
    private void addPlayer() throws InvalidArgumentNumberException{
		//create variables for the arguments and set the default valueOf
        String userName = null, familyName = null, givenName = null;
        StringTokenizer st = null;

        try {
			//when there is no argument or not enough argument, throw exception
            st = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
            userName = st.nextToken();
            familyName = st.nextToken();
            givenName = st.nextToken();
        }
        catch (Exception e){
			//terminate the method and throw a the InvalidArgumentNumberException
            throw new InvalidArgumentNumberException();
        }

		//an anonymous instance to be defined
        Player newPlayer = null;
        if(this.commandType == Command.ADDPLAYER) {
			//if command ask to add a human player
            newPlayer = new HumanPlayer(userName, familyName, givenName);
        }
        else{
			//if command ask to add an aiplayer
            newPlayer = new AIPlayer(userName, familyName, givenName);
        }
		
		//add a player to the player list
        playerManger.addPlayer(newPlayer);
    }

	//remove one or all players
    private void removePlayer(){
		//remove the players according to the given content
        playerManger.removePlayer(this.commandContent);
    }

	//edit the information of a player and try to throw exception
    private void editPlayer() throws InvalidArgumentNumberException{
		//create some variables and set the default value
        String userName = null, familyName = null, givenName = null;
        StringTokenizer st = null;

        try {
			//when there is no argument or not enough argument, throw exception
            st = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
			
            //get the user name, family name and given name from the UI input
            userName = st.nextToken();
            familyName = st.nextToken();
            givenName = st.nextToken();
        }
        catch (Exception e){
			//terminate the method and throw a the InvalidArgumentNumberException
            throw new InvalidArgumentNumberException();
        }
		
		//edit the player's family name and given name
        playerManger.editPlayer(userName, familyName, givenName);
    }

	//reset the stats of one or all players
    private void resetStats(){
        playerManger.resetStats(this.commandContent);
    }

	//display one or all players
    private void displayPlayer(){
        playerManger.displayPlayer(this.commandContent);
    }

	//display the ranking of all players
    private void rankings(){
        playerManger.displayRankings();
    }

	//make the two player play the TicTacToe game
    private void playGame() throws InvalidArgumentNumberException{
		//create some variables and set the default value
        StringTokenizer stOfPlayer = null;
        String player1UserName = null;
        String player2UserName = null;

        try {
			//when there is no argument or not enough argument, throw exception
            stOfPlayer = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
			
            //get the two user names from the UI inputs
            player1UserName = stOfPlayer.nextToken();
            player2UserName = stOfPlayer.nextToken();
        }
        catch (Exception e){
			//terminate the method and throw a the InvalidArgumentNumberException
            throw new InvalidArgumentNumberException();
        }

		//try to let the two players play the game
        makePlayersPlay(player1UserName, player2UserName);
    }

    //make the given two username players play game
    private void makePlayersPlay(String player1UserName, String player2UserName){
        //try get the two players from the user names
        Player player1 = playerManger.getPlayer(player1UserName);
        Player player2 = playerManger.getPlayer(player2UserName);

		/*if at least one of the players does not exist
		   print an error message and terminate this command*/
        if( (player1 == PlayerManager.NO_SUCH_PLAYER)
                || (player2 == PlayerManager.NO_SUCH_PLAYER) ){
            System.out.println(GameManager.GAME_ERROR);
            return ;
        }

        int result ;

        //play the game and get the result
        result = this.gameManager.playGame(player1, player2);

        //store the game result into the player instances
        playerManger.storeGameResult(player1, player2, result);
    }

}
