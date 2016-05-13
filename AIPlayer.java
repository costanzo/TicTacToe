class AIPlayer extends Player{
    public AIPlayer(){
        super();
    }

    public AIPlayer(String userName, Name realName){
        super(userName, realName);
    }

    public AIPlayer(String userName, Name realName, Stats stats){
        super(userName, realName, stats);
    }

    @Override
    public Move makeMove(char[][] gameBoard){
        for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
            for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++){
                if(gameBoard[i][j] == ' ')
                    return new Move(i, j);
            }

        return null;
    }
}
