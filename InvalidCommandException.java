class InvalidCommandException extends Exception{

    public InvalidCommandException(){
        super("Invalid Command Exception");
    }

    public InvalidCommandException(String command){
        super("'" + command + "' " + "is not a valid command.");
    }

}
