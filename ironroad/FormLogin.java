/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author bio
 */

public class FormLogin {
    
    
    FormLogin(Stage primaryStage)
    {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
       
        BorderPane panel = new BorderPane();
        VBox vBox = new VBox(10);
        HBox hBox = new HBox(20);
        Pane pane = new Pane();

        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
 
        Button btn1 = addButton("отмена");
        Button btn2 = addButton("войти");
        Button btn3 = addButton("12войти");
            
        
        hBox.getChildren().addAll(btn1,btn2);
        
        vBox.getChildren().addAll(btn3,hBox);
        
        panel.setCenter(vBox);
        
        
        Scene scene = new Scene(panel,300,160);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pro-JavaFx");
        primaryStage.show();
    }
 
    private Button addButton(String name){
        Button btn = new Button(name);
        btn.setPrefSize(70,15);
        return btn;
    }
    

}



