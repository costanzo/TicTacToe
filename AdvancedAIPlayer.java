class AdvancedAIPlayer extends Player {
    public static final int WIN_SCORE = 3;
    public static final int DRAWN_SCORE = 1;
    public static final int LOSE_SCORE = 0;

    public static final int FIRST_ROUND = 0;

    public static final char DEFAULT_MARK = '\u0000';

    private char aiMark;
    private char humanMark;

    private Move bestMove ;

    public AdvancedAIPlayer(){
    }

    @Override
    public Move makeMove(char[][] gameBoard){
		if(aiMark == DEFAULT_MARK){
			findPlayerMark(gameBoard);
		}
        this.bestMove = null;
        MaxMin(gameBoard, this.aiMark, FIRST_ROUND);
        return this.bestMove;
    }

    private int MaxMin(char[][] gameBoard, char playerMark, int roundNum){
        if(playerMark == aiMark){
            int maxScore = LOSE_SCORE;
            Move betterMove = null;
            for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
                for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++){
                    if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY){
                        Move move = new Move(i, j);
                        int score = getMoveResult(gameBoard, move, this.aiMark, roundNum);
                        if(score > maxScore){
                            maxScore = score;
                            betterMove = move;
                        }
                    }
                }
            if(roundNum == FIRST_ROUND){
                this.bestMove = betterMove;
            }
            return maxScore;
        }
        else{
            int minScore = WIN_SCORE;
            for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
                for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++){
                    if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY){
                        Move move = new Move(i, j);
                        int score = getMoveResult(gameBoard, move, this.humanMark, roundNum);
                        if(score < minScore){
                            minScore = score;
                        }
                    }
                }
            return minScore;
        }
    }

    private int getMoveResult(char[][] gameBoard, Move move, char playMark, int roundNum){
        char[][] newGameBoard = copyGameBoard(gameBoard);
        newGameBoard[move.getRow()][move.getColumn()] = playMark;
        if(win(newGameBoard, aiMark)){
            return WIN_SCORE;
        }
        else if(win(newGameBoard, humanMark)){
            return LOSE_SCORE;
        }
        else if(drawn(newGameBoard)){
            return DRAWN_SCORE;
        }
        else{
            if(playMark == this.aiMark){
                return MaxMin(newGameBoard, this.humanMark, roundNum + 1);
            }
            else{
                return MaxMin(newGameBoard, this.aiMark, roundNum + 1);
            }
        }

    }

    private void findPlayerMark(char[][] gameBoard){
		int countEmptyCells = 0;
        for(int i = GameManager.ROW_LOWERBOUND; i < GameManager.ROW_UPPERBOUND; i++)
            for (int j = GameManager.COLUMN_LOWERBOUND ;j < GameManager.COLUMN_UPPERBOUND; j++)
                if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY){
					countEmptyCells ++;
				}
		
		if(countEmptyCells == GameManager.ROW_UPPERBOUND * GameManager.COLUMN_UPPERBOUND){
			this.aiMark = GameManager.MARK_OF_O;
			this.humanMark = GameManager.MARK_OF_X;
		}
		else{
			this.aiMark = GameManager.MARK_OF_X;
			this.humanMark = GameManager.MARK_OF_O;
		}
        
    }
	
	private boolean win(char[][] gameBoard, char mark){
		if((gameBoard[0][0] == mark && gameBoard[0][1] == mark && gameBoard[0][2] == mark)||
		    (gameBoard[1][0] == mark && gameBoard[1][1] == mark && gameBoard[1][2] == mark) ||
		    (gameBoard[2][0] == mark && gameBoard[2][1] == mark && gameBoard[2][2] == mark) ||
		    (gameBoard[0][0] == mark && gameBoard[1][0] == mark && gameBoard[2][0] == mark) ||
		    (gameBoard[0][1] == mark && gameBoard[1][1] == mark && gameBoard[2][1] == mark) ||
		    (gameBoard[0][2] == mark && gameBoard[1][2] == mark && gameBoard[2][2] == mark) ||
		    (gameBoard[0][0] == mark && gameBoard[1][1] == mark && gameBoard[2][2] == mark) ||   
		    (gameBoard[0][2] == mark && gameBoard[1][1] == mark && gameBoard[2][0] == mark))
		    return true;
		else{
			return false;
		}
	}

    private boolean drawn(char[][] gameBoard){
        for(int i = GameManager.ROW_LOWERBOUND; i < GameManager.ROW_UPPERBOUND; i++)
            for (int j = GameManager.COLUMN_LOWERBOUND ;j < GameManager.COLUMN_UPPERBOUND; j++)
                if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY)
                    return false;
        return true;
    }

    private char[][] copyGameBoard(char[][] gameBoard){
        char[][] newGameBoard = new char[GameManager.ROW_UPPERBOUND][GameManager.ROW_UPPERBOUND];
        for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
            for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++){
                newGameBoard[i][j] = gameBoard[i][j];
            }
        return newGameBoard;
    }

}
