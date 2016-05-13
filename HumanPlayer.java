class HumanPlayer extends Player{
    public HumanPlayer(){
        super();
    }

    public HumanPlayer(String userName, Name realName){
        super(userName, realName);
    }

    public HumanPlayer(String userName, Name realName, Stats stats){
        super(userName, realName, stats);
    }

    @Override
    public Move makeMove(char[][] gameBoard){
        int row = TicTacToe.scanner.nextInt();
        int column = TicTacToe.scanner.nextInt();

        return new Move(row, column);
    }
}
