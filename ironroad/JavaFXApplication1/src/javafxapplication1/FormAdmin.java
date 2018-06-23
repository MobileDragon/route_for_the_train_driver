/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author bio
 */
public class FormAdmin {
    TextField loginField; 
    PasswordField passwordField;
    Button btn1 = addButton("куку");
    Button btn2 = addButton("войти");
    
    FormAdmin(Stage primaryStage, String login_name)
    {

        Pagination pagination = new Pagination(10, 0);
         
        BorderPane panel = new BorderPane();
        VBox vBox = new VBox(10);
        HBox hBoxLogin = new HBox(10);
        HBox hBoxPassword = new HBox(10);
        HBox hBox = new HBox(20);
        HBox hBoxWarning = new HBox(2);
        Pane pane = new Pane();

        hBoxLogin.setAlignment(Pos.CENTER);
        hBoxPassword.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        hBoxWarning.setAlignment(Pos.CENTER);

        loginField = new TextField();
        loginField.setPromptText("логин");
        loginField.setPrefWidth(160);
        passwordField = new PasswordField();
        passwordField.setPromptText("пароль");
        passwordField.setPrefWidth(160);
        
        Label notification=new Label("Логин или пароль наверны");
        notification.setVisible(false);

        notification.setTextFill(Color.RED);
       
        
        hBoxLogin.getChildren().addAll(loginField);
        hBoxPassword.getChildren().addAll(passwordField);
        hBox.getChildren().addAll(btn1,btn2);
        hBoxWarning.getChildren().addAll(notification);
        
       
        
        vBox.getChildren().addAll(hBoxLogin,hBoxPassword,hBox,hBoxWarning);
        
        panel.setCenter(vBox);
        
        
        Scene scene = new Scene(panel,600,400);
        pagination.setPadding(Insets.EMPTY);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Адмиистратор "+login_name);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                ResultSet rs=BofD.make("SELECT COUNT( admins.login ) AS admin "
                        + "FROM admins WHERE login LIKE 'wing' "
                        + "AND password LIKE '1111';");
                int zapr = 0;
                try {
                    rs.next();
                    zapr = rs.getInt("admin");
                } catch (SQLException ex) {
                    Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Низкий запрос2");
                    notification.setVisible(true);
                }
                loginField.setText(zapr+"");
                if(zapr==1){
                    
                    primaryStage.close();
                }
            }
        });
        
        
    }
 
    private Button addButton(String name){
        Button btn = new Button(name);
        btn.setPrefSize(70,15);
        return btn;
    }

}
