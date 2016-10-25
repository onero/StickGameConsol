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
public class GameBoard implements IGameModel {


    public static final String STRING_FIRST_PLAYER = "X";
    public static final String STRING_SECOND_PLAYER = "O";
    public static final int INT_FIRST_PLAYER = 1;
    public static final int INT_SECOND_PLAYER = 2;
    public static final int SIZE_OF_MATRIX = 3;
    public static final int MAX_MOVES = SIZE_OF_MATRIX * SIZE_OF_MATRIX;

    private static int[][] ttt_MATRIX = new int[SIZE_OF_MATRIX][SIZE_OF_MATRIX];
    private static int movesLeft = MAX_MOVES;
    private boolean isMultiPlayer = true;
    private int player = INT_FIRST_PLAYER;
    private int winner;
    private boolean isGameOver;

    /**
     * Returns 1 for player 1, 2 for player 2.
     *
     * @return int Id of the next player.
     */
    @Override
    public int setNextPlayer() {
        if (player != INT_FIRST_PLAYER) {
            player = INT_FIRST_PLAYER;
        } else {
            player = INT_SECOND_PLAYER;
        }
        return player;
    }

    /**
     * Attempts to let the current player canPlay at the given coordinates. It
     * the attempt is succesfull the current player has ended his turn and it is
     * the next players turn.
     *
     * @return true if there is currently nothing placed at the position and it
     * is possible to place an X/O
     */
    @Override
    public boolean canPlay(String btnText) {
        boolean canPlay;
        if (!isGameOver()) {
            canPlay = true;
            if (btnText.equals(STRING_FIRST_PLAYER) || btnText.equals(STRING_SECOND_PLAYER)) {
                canPlay = false;
            }
            decreaseMaxMoves();
        } else {
            canPlay = false;
        }
        return canPlay;
    }

    /**
     * Checks all rows, columns and diagonals for a winning combo
     *
     * @return whether the game is over yet
     */
    @Override
    public boolean isGameOver() {
        //Will return a draw (-1) unless any other condition is met
        winner = -1;
        
        //Game will by default not be over before a winning condition is met or the game results in a draw
        isGameOver = false;

        //If this is 0 all moves have been taken and the game will end
        if (movesLeft == 0) {
            isGameOver = true;
        }

        //Game is over if three consecutive X/O are found
        isGameOver = check3InRow();

        return isGameOver;
    }

    /**
     * Checks the game board for 3 in a row and changes the game status to over if any is found
     * @return true if game is over
     */
    private boolean check3InRow() {
        //Horizontal
        for (int h = 0; h < SIZE_OF_MATRIX; h++) {
            if (ttt_MATRIX[h][0] != 0 && ttt_MATRIX[h][0] == (ttt_MATRIX[h][1]) && ttt_MATRIX[h][1] == (ttt_MATRIX[h][2])) {
                winner = ttt_MATRIX[h][0];
                isGameOver = true;
            }
        }
        //Vertical
        for (int v = 0; v < SIZE_OF_MATRIX; v++) {
            if (ttt_MATRIX[0][v] != 0 && ttt_MATRIX[0][v] == (ttt_MATRIX[1][v]) && ttt_MATRIX[1][v] == (ttt_MATRIX[2][v])) {
                winner = ttt_MATRIX[0][v];
                isGameOver = true;
            }
        }
        //Diagonal 1
        if (ttt_MATRIX[0][0] != 0 && ttt_MATRIX[0][0] == ttt_MATRIX[1][1] && ttt_MATRIX[0][0] == ttt_MATRIX[2][2]) {
            winner = ttt_MATRIX[0][0];
            isGameOver = true;
        }
        //Diagonal 2
        if (ttt_MATRIX[0][2] != 0 && ttt_MATRIX[0][2] == ttt_MATRIX[1][1] && ttt_MATRIX[0][2] == ttt_MATRIX[2][0]) {
            winner = ttt_MATRIX[0][2];
            isGameOver = true;
        }
        return isGameOver;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() {
        player = INT_FIRST_PLAYER;
        winner = 0;
        movesLeft = MAX_MOVES;
        ttt_MATRIX = new int[SIZE_OF_MATRIX][SIZE_OF_MATRIX];
    }

    /**
     * @return the current player
     */
    @Override
    public int getCurrentPlayer() {
        return player;
    }

    /**
     * updates the matrix array at the current col and row with the current
     * player
     *
     * @param col
     * @param row
     * @param currentPlayer
     */
    @Override
    public void updateMatrix(int row, int col, int currentPlayer) {
        ttt_MATRIX[row][col] = currentPlayer;
    }

    /**
     * Decreases the max amount of moves by 1
     */
    @Override
    public void decreaseMaxMoves() {
        movesLeft--;
    }

    /**
     * Gets the state of the game mode
     * @return true if we're playing vs a friend and false if against the AI
     */
    @Override
    public boolean getIsMultiPlayer() {
        return isMultiPlayer;
    }

    /**
     * Changes the game mode
     * @param isMultiPlayer true to play vs friend and false to play vs AI
     */
    @Override
    public void setIsMultiPlayer(boolean isMultiPlayer) {
        this.isMultiPlayer = isMultiPlayer;
    }

    /**
     * Gets the moves left in this game
     * @return the moves left until the game ends
     */
    @Override
    public int getMovesLeft() {
        return movesLeft;
    }

    /**
     * Gets the matrix containing all the registered moves of players
     * @return the matrix of the game
     */
    @Override
    public int[][] getTTT_MATRIX() {
        return ttt_MATRIX;
    }

}
