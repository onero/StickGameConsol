/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import java.util.Random;

/**
 *
 * @author Adam Hansen
 */
public class AIPlayer extends GameBoard {

    //Refers to the buttons one through nine of the game board
    private final int[][] matrixIndex = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    private int btn = 0;

    private int r = 0;
    private int c = 0;

    /**
     * Gets the button index where the AI should place the next marker
     *
     * @return button where marker should be places as an int
     */
    public int getAIMove() {
        setRandomAvailableIndex();
        set3InRow();
        updateMatrix(r, c, INT_SECOND_PLAYER);
        decreaseMaxMoves();
        return btn;
    }

    /**
     * Put the O at a random available spot
     */
    public void setRandomAvailableIndex() {
        Random rand = new Random();
        boolean randomIndexUpdated = false;
        while (randomIndexUpdated == false) {
            r = rand.nextInt(SIZE_OF_MATRIX);
            c = rand.nextInt(SIZE_OF_MATRIX);
            if (getTTT_MATRIX()[r][c] == 0) {
                btn = matrixIndex[r][c];
                randomIndexUpdated = true;
            }
        }
    }

    /**
     * Checks for possibility to get 3 in a row on any combination
     */
    public void set3InRow() {
        //if 2 O's are found
        checkHorizontally();
        checkVertically();
        checkDiagonally();
    }

    /**
     * Checks the two diagonals of the game board for 2 O's, if find it will try to put down the third and win 
     */
    private void checkDiagonally() {
        //Diagonal 1
        if (getTTT_MATRIX()[0][0] == INT_SECOND_PLAYER && getTTT_MATRIX()[1][1] == INT_SECOND_PLAYER) {
            if (getTTT_MATRIX()[2][2] == 0) {
                btn = 9;
                r = 2;
                c = 2;
            }
        } else if (getTTT_MATRIX()[1][1] == INT_SECOND_PLAYER && getTTT_MATRIX()[2][2] == INT_SECOND_PLAYER) {
            if (getTTT_MATRIX()[0][0] == 0) {
                btn = 1;
                r = 0;
                c = 0;
            }
        } else if (getTTT_MATRIX()[0][0] == INT_SECOND_PLAYER && getTTT_MATRIX()[2][2] == INT_SECOND_PLAYER) {
            if (getTTT_MATRIX()[1][1] == 0) {
                btn = 5;
                r = 1;
                c = 1;
            }
        }

        //Diagonal 2
        if (getTTT_MATRIX()[0][2] == INT_SECOND_PLAYER && getTTT_MATRIX()[1][1] == INT_SECOND_PLAYER) {
            if (getTTT_MATRIX()[2][0] == 0) {
                btn = 7;
                r = 2;
                c = 0;
            }
        } else if (getTTT_MATRIX()[1][1] == INT_SECOND_PLAYER && getTTT_MATRIX()[2][0] == INT_SECOND_PLAYER) {
            if (getTTT_MATRIX()[0][2] == 0) {
                btn = 3;
                r = 0;
                c = 2;
            }
        } else if (getTTT_MATRIX()[0][2] == INT_SECOND_PLAYER && getTTT_MATRIX()[2][0] == INT_SECOND_PLAYER) {
            if (getTTT_MATRIX()[1][1] == 0) {
                btn = 5;
                r = 1;
                c = 1;
            }
        }
    }

    /**
     * Checks all the columns for 2 O's, if found it will try to put down the third
     */
    private void checkVertically() {
        //Vertical
        for (int v = 0; v < SIZE_OF_MATRIX; v++) {
            if (getTTT_MATRIX()[0][v] == INT_SECOND_PLAYER && getTTT_MATRIX()[1][v] == INT_SECOND_PLAYER) {
                if (getTTT_MATRIX()[2][v] == 0) {
                    btn = matrixIndex[2][v];
                    r = 2;
                    c = v;
                }
            } else if (getTTT_MATRIX()[1][v] == INT_SECOND_PLAYER && getTTT_MATRIX()[2][v] == INT_SECOND_PLAYER) {
                if (getTTT_MATRIX()[0][v] == 0) {
                    btn = matrixIndex[0][v];
                    r = 0;
                    c = v;
                }
            } else if (getTTT_MATRIX()[0][v] == INT_SECOND_PLAYER && getTTT_MATRIX()[2][v] == INT_SECOND_PLAYER) {
                if (getTTT_MATRIX()[1][v] == 0) {
                    btn = matrixIndex[1][v];
                    r = 1;
                    c = v;
                }
            }
        }
    }

    /**
     * Checks all rows for 2 O's, if found it will try to put down the third
     */
    private void checkHorizontally() {
        for (int h = 0; h < SIZE_OF_MATRIX; h++) {
            if (getTTT_MATRIX()[h][0] == INT_SECOND_PLAYER && getTTT_MATRIX()[h][1] == INT_SECOND_PLAYER) {
                if (getTTT_MATRIX()[h][2] == 0) {
                    btn = matrixIndex[h][2];
                    r = h;
                    c = 2;
                }
            } else if (getTTT_MATRIX()[h][1] == INT_SECOND_PLAYER && getTTT_MATRIX()[h][2] == INT_SECOND_PLAYER) {
                if (getTTT_MATRIX()[h][0] == 0) {
                    btn = matrixIndex[h][0];
                    r = h;
                    c = 0;
                }
            } else if (getTTT_MATRIX()[h][0] == INT_SECOND_PLAYER && getTTT_MATRIX()[h][2] == INT_SECOND_PLAYER) {
                if (getTTT_MATRIX()[h][1] == 0) {
                    btn = matrixIndex[h][1];
                    r = h;
                    c = 1;
                }
            }
        }
    }

}
