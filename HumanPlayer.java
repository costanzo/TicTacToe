class HumanPlayer extends Player{
    public HumanPlayer() {
    }

    public HumanPlayer(String userName, String familyName, String givenName) {
        super(userName, familyName, givenName);
    }

    public HumanPlayer(String userName, String familyName, String givenName, int numberOfGamePlayed, int numberOfGameWon, int numberOfGameDrawn) {
        super(userName, familyName, givenName, numberOfGamePlayed, numberOfGameWon, numberOfGameDrawn);
    }

    @Override
    public Move makeMove(char[][] gameBoard){
        int row = TicTacToe.scanner.nextInt();
        int column = TicTacToe.scanner.nextInt();

        return new Move(row, column);
    }
}
