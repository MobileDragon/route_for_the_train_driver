/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;


import javafx.application.Application;

import javafx.stage.Stage;

/**
 *
 * @author bio трудно привыкнуть к этой посветке
 */
public class JavaFXApplication1 extends Application {
    //public static Connection connection;
    @Override
    
    public void start(Stage primaryStage) {
        pStage=primaryStage;
        FormLogin a=new FormLogin(primaryStage);
        
    }

    /**
     * @param args the command line arguments
     */
    public static int myStId=0;
    public static String mySt="";
    public static Stage pStage;
    public static void main(String[] args) {

        launch(args);
    }
    
}
