/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tictactoe.bll.AIPlayer;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;

/**
 *
 * @author Stegger
 */
public class TicTacViewController implements Initializable {

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;

    @FXML
    private Button btnSwitchGameMode;

    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;

    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        AIPlayer AI = new AIPlayer();
        try {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            Button btn = (Button) event.getSource();
            String btnText;
            btnText = btn.getText();
            if (game.canPlay(btnText)) {

                String xOrO = game.getCurrentPlayer() == GameBoard.INT_FIRST_PLAYER ? GameBoard.STRING_FIRST_PLAYER : GameBoard.STRING_SECOND_PLAYER;

                //Checks if we are playing vs real opponent or AI
                if (game.getIsMultiPlayer() == true) {
                    //Player takes turn, matrix is updated and turn is passed on
                    playerTurn(r, c, btn, xOrO);
                } else {
                    playerTurn(r, c, btn, xOrO);
                    //Checks if the game has moves left and lets the AI play if it has
                    if (game.getMovesLeft() != 0 && !game.isGameOver()) {
                        int btnNumber = AI.getAIMove();
                        AIMove(btnNumber);
                        game.setNextPlayer();
                        btn.setText(xOrO);
                        setPlayer();
                    }
                }
                //Checks if the game status has ended
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    displayWinner(winner);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void playerTurn(int r, int c, Button btn, String xOrO) {
        game.updateMatrix(r, c, game.getCurrentPlayer());
        game.setNextPlayer();
        btn.setText(xOrO);
        setPlayer();
    }

    /**
     * Finds the corrosponding button to the int returned from the getAIMove method
     * @param btnNumber 
     */
    private void AIMove(int btnNumber) {
        switch (btnNumber) {
            case 1:
                btn1.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            case 2:
                btn2.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            case 3:
                btn3.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            case 4:
                btn4.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            case 5:
                btn5.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            case 6:
                btn6.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            case 7:
                btn7.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            case 8:
                btn8.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            case 9:
                btn9.setText(GameBoard.STRING_SECOND_PLAYER);
                break;
            default:
                System.out.println("WTF!");
                break;
        }
    }

    /**
     * Initiates a new game and wipes clean the board
     * @param event 
     */
    @FXML
    private void handleNewGame(ActionEvent event) {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Will check the game mode. If the button says "AI" we will be facing the computer, otherwise it is vs a friend
     * @param event 
     */
    @FXML
    private void handleGameMode(ActionEvent event) {
        if (btnSwitchGameMode.getText().equals("AI")) {
            game.setIsMultiPlayer(true);
            game.newGame();
            setPlayer();
            clearBoard();
            btnSwitchGameMode.setText("Normal");
        } else if (btnSwitchGameMode.getText().equals("Normal")) {
            game.setIsMultiPlayer(false);
            game.newGame();
            setPlayer();
            clearBoard();
            btnSwitchGameMode.setText("AI");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        setPlayer();
    }

    /**
     * Will display the current player on top of the game
     */
    private void setPlayer() {
        lblPlayer.setText(TXT_PLAYER + game.getCurrentPlayer());
    }

    /**
     * Will display the winner of the game on top of the game or dispaly a draw
     * if no winner is found
     *
     * @param winner
     */
    private void displayWinner(int winner) {
        String message;
        switch (winner) {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * This will clear the board of any X/O and make it ready for a new game
     */
    private void clearBoard() {
        for (Node n : gridPane.getChildren()) {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

}
