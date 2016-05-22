class AIPlayer extends Player{
    public AIPlayer() {
    }

    public AIPlayer(String userName, String familyName, String givenName) {
        super(userName, familyName, givenName);
    }

    public AIPlayer(String userName, String familyName, String givenName, int numberOfGamePlayed, int numberOfGameWon, int numberOfGameDrawn) {
        super(userName, familyName, givenName, numberOfGamePlayed, numberOfGameWon, numberOfGameDrawn);
    }

    @Override
    public Move makeMove(char[][] gameBoard){
        for(int i = GameManager.ROW_LOWERBOUND ; i < GameManager.ROW_UPPERBOUND ; i++)
            for(int j = GameManager.COLUMN_LOWERBOUND ; j < GameManager.ROW_UPPERBOUND ; j++){
                if(gameBoard[i][j] == GameManager.MARK_OF_EMPTY)
                    return new Move(i, j);
            }

        return null;
    }
}
