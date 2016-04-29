/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 6th May, 2016
* Comment: Project B, TicTacToe game solution in COMP90041
*/
import java.util.Scanner;

class GameManager{
	public static final String MARK_OF_O = "O";
	public static final String MARK_OF_X = "X";
	public static final String MARK_OF_EMPTY = " ";
	public static final String MARK_OF_COLUMN = "|";
	public static final String MARK_OF_ROW = "-";

	public static final int NO_ERROR = 0;
	public static final int OCCUPATION_ERROR = 1;
	public static final int OUT_OF_BOUNDARY_ERROR = 2;
	public static final String ERROR = "Invalid move. ";
	public static final String
			ERROR_INFO1 = "The cell has been occupied.";
	public static final String
			ERROR_INFO2 = "You must place at a cell within {0,1,2} {0,1,2}.";
	
	//define the grid size
	public static final int ROW_UPPERBOUND = 3;
	public static final int COLUMN_UPPERBOUND = 3;
	
	//define the grid size including the |  - and marks
	public static final int GRID_ROW_UPPERBOUND = 2 * ROW_UPPERBOUND - 1;  
	public static final int GRID_COLUMN_UPPERBOUND = 2 * COLUMN_UPPERBOUND - 1;
	
	//define the meaning of the mark
	public static final int EMPTY_MARK = 0;
	public static final int PLAYER_O_MARK = 1;
	public static final int PLAYER_X_MARK = 2;
	
	//define the result state of the game
	public static final int GAME_CONTINUE = 0;
	public static final int PLAYER_O_WON = 1;
	public static final int PLAYER_X_WON = 2;
	public static final int DRAW = 3;

	private int[][] grid;
	private String nameOfPlayerO, nameOfPlayerX;

	public GameManager(){
		//assign the nameOfPlayerX and nameOfPlayerO with default value
		this(null,null);
	}

	public GameManager(String nameOfPlayerO, String nameOfPlayerX){
		//create the grid into the expected size and initialize the grid
		grid = new int[ROW_UPPERBOUND][COLUMN_UPPERBOUND];
		for(int i = 0; i < ROW_UPPERBOUND; i++)
			for (int j = 0 ;j < COLUMN_UPPERBOUND; j++)
				grid[i][j] = EMPTY_MARK;

		//initialize the player names with null
		this.nameOfPlayerO = nameOfPlayerO;
		this.nameOfPlayerX = nameOfPlayerX;
	}

	public int run(Scanner scanner){
		//flag to decide if it is player O's turn
		boolean isPlayerOTurn = true;

		//flag of the state of the game
		int gameResultType = GAME_CONTINUE;

		//show the welcome interface and get the names of the players
		printGrid();

		//for loop for each turn
		while(gameResultType == GAME_CONTINUE){
			if(isPlayerOTurn){              //player O's turn
				nextMove(PLAYER_O_MARK, nameOfPlayerO, scanner);
				isPlayerOTurn = false;
			}
			else{                           //player X's turn
				nextMove(PLAYER_X_MARK, nameOfPlayerX, scanner);
				isPlayerOTurn = true;
			}
			//print out the grid and check the state of the game
			printGrid();
			gameResultType = getGameState();
		}
		//scanner.close();
        scanner.nextLine();

		//print the final result of the game as indicated by gameResultType
		printResult(gameResultType);

		return gameResultType;
	}

	//print and record the next step of the certain player
	private void nextMove(int playerMark, String playerName, Scanner scanner) {
		int rowOfMark, columnOfMark;

		do {
			System.out.println(playerName + "'s move:");

			//get the row and column the player
			rowOfMark = scanner.nextInt();
			columnOfMark = scanner.nextInt();

	    }while (!isGridAvailable(rowOfMark, columnOfMark));

		//mark the grid with certain player
		grid[rowOfMark][columnOfMark] = playerMark;
	}

	private boolean isGridAvailable(int row, int column){
		int errorCode = checkError(row, column);
		switch(errorCode){
			case NO_ERROR:
				return true;
			case OCCUPATION_ERROR:
				System.out.println(ERROR + ERROR_INFO1);
				return false;
			case OUT_OF_BOUNDARY_ERROR:
				System.out.println(ERROR + ERROR_INFO2);
				return false;
			default:
				return false;
		}
	}

	private int checkError(int row, int column){
		if((row >= ROW_UPPERBOUND || row < 0)
				||(column >= COLUMN_UPPERBOUND || column < 0))
			return OUT_OF_BOUNDARY_ERROR;

		if(grid[row][column] != EMPTY_MARK)
			return OCCUPATION_ERROR;

		return NO_ERROR;
	}

	//when the game finishes, print out the game result
	private void printResult(int resultType){
		if(resultType == PLAYER_O_WON)
			System.out.println("Game over. " + nameOfPlayerO + " won!");
		else if(resultType == PLAYER_X_WON)
			System.out.println("Game over. " + nameOfPlayerX + " won!");
		else
			System.out.println("Game over. It was a draw!");
	}

	//print out the game grid
	private void printGrid(){
		for ( int i = 0 ; i < GRID_ROW_UPPERBOUND ; i ++ ){
			for ( int j = 0 ;j < GRID_COLUMN_UPPERBOUND ; j++ ){
				if(i%2 == 1)
					//all the odd rows print 5 -
					System.out.print(MARK_OF_ROW);
				else{
					if(j%2 == 1)
						//all odd columns of the even rows print |
						System.out.print(MARK_OF_COLUMN);
					else{
						//even columns in even rows print mark
						int t = grid[i/2][j/2];
						if(t == EMPTY_MARK)
							//empty cell print " "
							System.out.print(MARK_OF_EMPTY);
						else if(t == PLAYER_O_MARK)
							//mark of player O print "O"
							System.out.print(MARK_OF_O);
						else
							//mark of player X print "X"
							System.out.print(MARK_OF_X);
					}
				}
			}
			System.out.println();
		}
	}

	//return the current state of the game after a player's move
	private int getGameState(){
		//judge if there is a winner
		int winner = checkWinner();
		if(winner != GAME_CONTINUE)
			return winner;

		//no empty cell and it is a draw
		if(isDraw())
			return DRAW;

		//no winner and there is empty cell
		return GAME_CONTINUE;
	}

	//check if there is a winner if yes return winner's mark
	private int checkWinner(){
		//first check the rows
		for(int i = 0; i < ROW_UPPERBOUND; i++)
			if( (grid[i][0] == grid[i][1])
					&& (grid[i][0] == grid[i][2]) )
				if(grid[i][0] != GAME_CONTINUE)
					return grid[i][0];

		//second check the columns
		for(int i = 0; i < COLUMN_UPPERBOUND; i++)
			if( (grid[0][i] == grid[1][i])
					&& (grid[0][i] == grid[2][i]) )
				if(grid[0][i] != GAME_CONTINUE)
					return grid[0][i];

		//then check the diagonal
		if( (grid[0][0] == grid[1][1])
				&& (grid[0][0] == grid[2][2]) )
			if(grid[0][0] != GAME_CONTINUE)
				return grid[0][0];

		//finally check the anti-diagonal
		if( (grid[0][2] == grid[1][1])
				&& (grid[0][2] == grid[2][0]))
			if(grid[1][1] != GAME_CONTINUE)
				return grid[1][1];

		return GAME_CONTINUE;
	}

	//helper function to check if the result is a draw
	private boolean isDraw(){
		for(int i = 0; i < ROW_UPPERBOUND; i++)
			for (int j = 0 ;j < COLUMN_UPPERBOUND; j++)
				if(grid[i][j] == EMPTY_MARK)
					return false;
		return true;
	}
	
}