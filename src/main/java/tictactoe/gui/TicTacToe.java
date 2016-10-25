/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Stegger
 */
public class TicTacToe extends Application
{
    private Scene scene;
    private Pane root;
    
    @Override
    public void start(final Stage stage) throws Exception
    {
     
        stage.setFullScreen(true);
        root = FXMLLoader.load(getClass().getResource("views/TicTacView.fxml"));
//        Rectangle2d visualBonds = Screen.getPrimary().getVisualBounds();
        scene = new Scene(root);
        
        
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.setTitle("Tic Tac Toe");
//        stage.centerOnScreen();
        
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
