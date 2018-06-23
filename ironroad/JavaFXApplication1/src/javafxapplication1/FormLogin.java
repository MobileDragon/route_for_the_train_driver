/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author bio
 */

public class FormLogin {
        
    TextField loginField; 
    PasswordField passwordField;
    Button btn1 = addButton("отмена");
    Button btn2 = addButton("войти");
    
    FormLogin(Stage primaryStage)
    {
        BorderPane panel = new BorderPane();
        VBox vBox = new VBox(10);
        HBox hBoxLogin = new HBox(10);
        HBox hBoxPassword = new HBox(10);
        HBox hBox = new HBox(20);
        HBox hBoxWarning = new HBox(2);

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
        
        
        Scene scene = new Scene(panel,190,140);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Авторизация");
        primaryStage.setResizable(false);
        primaryStage.show();
        
        btn1.setOnAction((ActionEvent event) -> {
            primaryStage.close();
        });
        btn2.setOnAction((ActionEvent event) -> {
            ResultSet rs=BofD.make("SELECT station_id "
                    + "FROM admins WHERE login LIKE '"+loginField.getText()+"' "
                    + "AND password LIKE '"+passwordField.getText()+"';");
            int station_id = 0;
            try {
                rs.next();
                station_id = rs.getInt("station_id");
                JavaFXApplication1.myStId=station_id;
            } catch (SQLException ex) {
                Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Низкий запрос2");
                //notification.setVisible(true);
            }
            if(station_id!=0){
                rs=BofD.make("SELECT stations.name as st FROM stations WHERE "
                        + "(id="+station_id+")");
                try {
                    rs.next();
                    JavaFXApplication1.mySt=rs.getString("st");
                } catch (SQLException ex) {
                    Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                primaryStage.close();
                primaryStage.setTitle(loginField.getText()+" лучший админ станции "+JavaFXApplication1.mySt);
                //FormAdmin b=new FormAdmin(primaryStage,loginField.getText());
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("FxmlForms.fxml"));
                } catch (IOException ex) {
                   System.out.println("FxmlForms.fxml чет не то");
                }
                Scene scene2 = new Scene(root);
                primaryStage.setScene(scene2);
                primaryStage.show();
                //
            }
            else
            {
                System.out.println("Fx "+"SELECT login "
                    + "FROM avatars WHERE login LIKE '"+loginField.getText()+"' "
                    + "AND password LIKE '"+passwordField.getText()+"';");
                rs=BofD.make("SELECT login,itinerary_id "
                    + "FROM avatars WHERE login LIKE '"+loginField.getText()+"' "
                    + "AND password LIKE '"+passwordField.getText()+"';");
                try {
                    rs.next();
                } catch (SQLException ex) {
                    Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Fx1");
                String login="";
                try {
                    login=rs.getString("login");
                    JavaFXApplication1.mySt=login;
                    JavaFXApplication1.myStId=rs.getInt("itinerary_id");
                } catch (SQLException ex) {
                    Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
                }System.out.println("Fx2");
                
                if(login.length()>1)
                {
                    System.out.println("Fx3");
                    primaryStage.close();
                    primaryStage.setTitle("Аватар "+login);
                    //FormAdmin b=new FormAdmin(primaryStage,loginField.getText());
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("Machinist.fxml"));
                    } catch (IOException ex) {
                       System.out.println("Machinist.fxml чет не то");
                    }
                    Scene scene2 = new Scene(root);
                    primaryStage.setScene(scene2);
                    primaryStage.show();
                }
                else
                    notification.setVisible(true);//добавить
            }
                
                
        });
        
        
    }
 
    private Button addButton(String name){
        Button btn = new Button(name);
        btn.setPrefSize(70,15);
        return btn;
    }
    
}



