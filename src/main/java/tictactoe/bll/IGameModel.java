/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

/**
 *
 * @author Stegger
 */
public interface IGameModel
{

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int setNextPlayer();
    
    /**
     * Decreases the max amount of moves
     */
    public void decreaseMaxMoves();
    
    /**
     * Gets the matrix
     * @return the matrix
     */
    public int[][] getTTT_MATRIX();
    
    /**
     * 
     * @return the current player
     */
    public int getCurrentPlayer();
    
    /**
     * Checks if we're facing a real opponent or an AI
     * @return true if we're facing a real opponent and false if it is an AI
     */
    public boolean getIsMultiPlayer();
    
    /**
     * Changes the game mode from multiplayer to singleplayer or reverse
     * @param isMultiPlayer
     */
    public void setIsMultiPlayer(boolean isMultiPlayer);

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param btnText
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    public boolean canPlay(String btnText);
    
    /**
     * Updates the columns and rows of the matrix with the corresponding player number
     * @param col is the column of the game matrix
     * @param row is the row of the game matrix
     * @param currentPlayer
     */
    public void updateMatrix(int col, int row, int currentPlayer);

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver();

    /**
     * Gets the id of the winner, -1 if its a draw or if the game is still running.
     *
     * @return int id of winner, or -1 if draw or if gameOver() == false.
     */
    public int getWinner();
    
    /**
     * Gets the moves left for the board
     * @return 
     */
    public int getMovesLeft();

    /**
     * Resets the game to a new game state.
     */
    public void newGame();
    
}
