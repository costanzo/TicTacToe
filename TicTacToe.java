/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
* Description: TicTacToe class contains the main class used
*              to conduct the game, including process user
*              input and using other classes to handle the 
*              command.
*/
import java.util.Scanner;
import java.util.StringTokenizer;

class TicTacToe{
	//define two delimiters to split the user inputs
    public static final String COMMAND_DELIMITER = " ";
    public static final String CONTENT_DELIMITER = ",";

	//scanner to collect user input 
    private Scanner scanner;
	
	//the command type for processing, such as addplayer
    private String commandName ;
	
	//the command execution target or edition content
    private String commandContent ;
	
	//PlayerManager instance to manupilate the players
    private PlayerManager playerManger;

	//enumerate all command types 
    public enum Command{
        EXIT,
        ADDPLAYER,
        REMOVEPLAYER,
        EDITPLAYER,
        RESETSTATS,
        DISPLAYPLAYER,
        RANKINGS,
        PLAYGAME
    }

    public static void main(String[] args){
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();
	}
	
	//constructor for a default system 
	public TicTacToe(){
		this.scanner = new Scanner(System.in);
        this.playerManger = new PlayerManager();
	}
	
	//run the whole TicTacToe game
	public void run(){
		//initialize the UI
		SystemInitialisation();

        do {
			//collect and split the user inputs 
            ProcessInput();
			
			//choose the exact action to be invoked according to command type
            chooseAction();
			
			//show the command prompt in the UI 
            ShowCommandPrompt();
        }while(true);
    }

	//show the inistialization UI 
    private void SystemInitialisation(){
        System.out.println("Welcome to Tic Tac Toe!");
        ShowCommandPrompt();
    }

	//collect and split the user input 
    private void ProcessInput(){
		//get the user inputs
        String input = this.scanner.nextLine();

        StringTokenizer stOfInput = new StringTokenizer(input,COMMAND_DELIMITER);

		//get the command type from the input
        this.commandName = stOfInput.nextToken();
		
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

	//take actions according to the user input command type
    private void chooseAction(){
		//first get the enumerate type of the command
        Command command = Command.valueOf(commandName.toUpperCase());
        switch(command){
            case EXIT :           //exit the system
                Exit(); 
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
            case RANKINGS:        //displayer the ranking of all players
                displayRanking();
                break;
            case PLAYGAME:        //make two players play the game
                playGame();
                break;
            default:
        }
    }

	//show the command prompt to ask for user input
    private void ShowCommandPrompt(){
        System.out.println();
        System.out.print(">");
    }

	//exit the game 
    private void Exit(){
		//first close the scanner 
        this.scanner.close();
		
        System.out.println();
        System.exit(0);
    }

	//add a player to the PlayerManager instance
    private void addPlayer(){
        String userName, familyName, givenName;
        StringTokenizer st = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
        userName = st.nextToken();
        familyName = st.nextToken();
        givenName = st.nextToken();
        Player newPlayer = new Player(userName, familyName, givenName);
        playerManger.addPlayer(newPlayer);
    }

	//remove one or all players
    private void removePlayer(){
		//remove the players according to the given content
        playerManger.removePlayer(this.commandContent, this.scanner);
    }

	//edit the information of a player
    private void editPlayer(){
        String userName, familyName, givenName;
        StringTokenizer st = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
        userName = st.nextToken();
        familyName = st.nextToken();
        givenName = st.nextToken();
		//edit the player's family name and given name
        playerManger.editPlayer(userName, familyName, givenName);
    }

	//reset the stats of one or all players
    private void resetStats(){
        playerManger.resetStats(this.commandContent, this.scanner);
    }

	//displayer one or all players
    private void displayPlayer(){
        playerManger.displayPlayer(this.commandContent);
    }

	//display the ranking of all players
    private void displayRanking(){
        playerManger.displayRankings();
    }

	//make the two player play the TicTacToe game
    private void playGame(){
        StringTokenizer stOfPlayer = new StringTokenizer(this.commandContent, CONTENT_DELIMITER);
        String player1UserName = stOfPlayer.nextToken();
        String player2UserName = stOfPlayer.nextToken();
        playerManger.makePlayersPlay(player1UserName, player2UserName, this.scanner);
    }

}