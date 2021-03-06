/*
* Author: Shuyi Sun
* Student ID: 731209
* Date: 27th May, 2016
* Comment: Project C, TicTacToe game solution in COMP90041
* Description: this class is used to handle a single game-play
*              between two players including showing the game  
*              grid and player intruction.
*/

class GameManager{
	//these are the marks that will be showed in the grid
	public static final char MARK_OF_O = 'O';
	public static final char MARK_OF_X = 'X';
	public static final char MARK_OF_EMPTY = ' ';
	public static final char MARK_OF_COLUMN = '|';
	public static final char MARK_OF_ROW = '-';

	//set the default player name as null
	public static final Player DEFAULT_PLAYER = null;

	//the error code, each one indicates a certain error or no error
	public static final int NO_ERROR = 0;
	public static final int OCCUPATION_ERROR = 1;
	public static final int OUT_OF_BOUNDARY_ERROR = 2;
	
	//the error information that will show up when an error occurs 
	public static final String ERROR = "Invalid move. ";
	public static final String
			ERROR_INFO1 = "The cell has been occupied.";
	public static final String
			ERROR_INFO2 = "You must place at a cell within {0,1,2} {0,1,2}.";
	public static final String
			GAME_ERROR = "Player does not exist.";
	
	//define the grid size
	public static final int ROW_UPPERBOUND = 3;
	public static final int ROW_LOWERBOUND = 0;
	public static final int COLUMN_UPPERBOUND = 3;
	public static final int COLUMN_LOWERBOUND = 0;
	
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
	
	//used when printing the grid
	public static final int ODD = 1;

	//the grid matrix and names of the two players
	private int[][] grid;
	private Player playerO, playerX;

	public GameManager(){
		//assign the nameOfPlayerX and nameOfPlayerO with default value
		this(DEFAULT_PLAYER,DEFAULT_PLAYER);
	}

	public GameManager(Player playerO, Player playerX){
		//create the grid into the expected size and initialize the grid
		grid = new int[ROW_UPPERBOUND][COLUMN_UPPERBOUND];

		//initialize the player names with null
		this.playerO = playerO;
		this.playerX = playerX;
	}

	//clear the grid for the new players
	private void resetGrid(){
		for(int i = ROW_LOWERBOUND; i < ROW_UPPERBOUND; i++)
			for (int j = COLUMN_LOWERBOUND ;j < COLUMN_UPPERBOUND; j++)
				grid[i][j] = EMPTY_MARK;
	}

	//run the game between the given two players
	public int playGame(Player player1, Player player2){
		//clear the grid for new players
		resetGrid();

		//replace the default player with given players
		this.playerO = player1;
		this.playerX = player2;

		//flag to decide if it is player O's turn
		boolean isPlayerOTurn = true;

		//flag of the state of the game
		int gameResultType = GAME_CONTINUE;

		//show the welcome interface and get the names of the players
		printGrid();

		//for loop for each turn
		while(gameResultType == GAME_CONTINUE){
			if(isPlayerOTurn){              //player O's turn
				nextMove(PLAYER_O_MARK, playerO);
				isPlayerOTurn = false;
			}
			else{                           //player X's turn
				nextMove(PLAYER_X_MARK, playerX);
				isPlayerOTurn = true;
			}
			//print out the grid and check the state of the game
			printGrid();
			gameResultType = getGameState();
		}

		//this line collect the junks
        TicTacToe.scanner.nextLine();

		//print the final result of the game as indicated by gameResultType
		printResult(gameResultType);

		return gameResultType;
	}

	//print and record the next step of the certain player
	private void nextMove(int playerMark, Player player) {
		int rowOfMark, columnOfMark;

		do {
			System.out.println(player.getGivenName() + "'s move:");

			//get the move from the player's method, it depends on 
			//which kind of player it is
			Move move = player.makeMove(transferGrid());

			//if the move does not exist, system exit
			if(move == null){
				System.exit(0);
			}

			//get row and column from the move
			rowOfMark = move.getRow();
			columnOfMark = move.getColumn();

	    }while (!isGridAvailable(rowOfMark, columnOfMark));

		//mark the grid with certain player
		grid[rowOfMark][columnOfMark] = playerMark;
	}

	//check if player can put a mark in this grid
	private boolean isGridAvailable(int row, int column){
		//check if any error will occur in this grid 
		int errorCode = checkError(row, column);
		
		//get result from corresponding error code
		switch(errorCode){
			case NO_ERROR:
				return true;
			case OCCUPATION_ERROR:    //this grid has already been occupied
				System.out.println(ERROR + ERROR_INFO1);
				return false;
			case OUT_OF_BOUNDARY_ERROR:   // this grid is out of boundary
				System.out.println(ERROR + ERROR_INFO2);
				return false;
			default:
				return false;
		}
	}

	//check if this point is out of boundary or occupied
	private int checkError(int row, int column){
		//if the point is out of boundary, then return out of boundary error
		if((row >= ROW_UPPERBOUND || row < ROW_LOWERBOUND)
				||(column >= COLUMN_UPPERBOUND || column < COLUMN_LOWERBOUND))
			return OUT_OF_BOUNDARY_ERROR;

		//if this point is occupied, return occupation error
		if(grid[row][column] != EMPTY_MARK)
			return OCCUPATION_ERROR;

		return NO_ERROR;
	}

	//when the game finishes, print out the game result
	private void printResult(int resultType){
		if(resultType == PLAYER_O_WON)
			System.out.println("Game over. " + playerO.getGivenName() + " won!");
		else if(resultType == PLAYER_X_WON)
			System.out.println("Game over. " + playerX.getGivenName() + " won!");
		else
			System.out.println("Game over. It was a draw!");
	}

	//print out the game grid
	private void printGrid(){
		for ( int i = ROW_LOWERBOUND ; i < GRID_ROW_UPPERBOUND ; i ++ ){
			for ( int j = COLUMN_LOWERBOUND ;j < GRID_COLUMN_UPPERBOUND ; j++ ){
				if(i%2 == ODD)
					//all the odd rows print 5 -
					System.out.print(MARK_OF_ROW);
				else{
					if(j%2 == ODD)
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
		for(int i = ROW_LOWERBOUND; i < ROW_UPPERBOUND; i++)
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
		for(int i = ROW_LOWERBOUND; i < ROW_UPPERBOUND; i++)
			for (int j = COLUMN_LOWERBOUND ;j < COLUMN_UPPERBOUND; j++)
				if(grid[i][j] == EMPTY_MARK)
					return false;
		return true;
	}

	//to make the advanceaiplayer compatible, manually transfer the int[][] into char[][]
	private char[][] transferGrid(){
		//create a new two-dimension char grid
		char[][] cGrid = new char[ROW_UPPERBOUND][COLUMN_UPPERBOUND];

		//initialize the char[][] gameboard according to the int[][] gameboard
		for(int i = ROW_LOWERBOUND ; i< ROW_UPPERBOUND ; i++)
			for(int j = COLUMN_LOWERBOUND; j < COLUMN_UPPERBOUND ; j++){
				switch (grid[i][j]){
					case EMPTY_MARK:    //set the empty cell
						cGrid[i][j] = MARK_OF_EMPTY;
						break;
					case PLAYER_O_MARK:  //set the player O mark
						cGrid[i][j] = MARK_OF_O;
						break;
					case PLAYER_X_MARK:  //set the player X mark
						cGrid[i][j] = MARK_OF_X;
						break;
					default:
						break;
				}
			}

		return cGrid;
	}
	
}