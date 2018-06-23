/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author bio
 */


public class MachinistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public Label L1;
    @FXML
    public Label L2;
    
    @FXML
    public TableView table;
    @FXML
    public TableColumn N1;
    @FXML
    public TableColumn Km1;
    @FXML
    public TableColumn Pi1;
    @FXML
    public TableColumn AL1;
    @FXML
    public TableColumn NS1;
    @FXML
    public TableColumn Li1;
    
    
    
    @FXML
    public TextField km;
    @FXML
    public TextField piket;
    @FXML
    public TextField alsn;
    @FXML
    public TextField nomer;
    @FXML
    public TextField light;
    @FXML
    public TextField id2;
    
    private ObservableList<ALSN> usersData = FXCollections.observableArrayList();//данные для таблицы
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
        ResultSet rs=BofD.make("SELECT station_start,station_end,time_start,time_end FROM itineraries WHERE identification="+JavaFXApplication1.myStId+"");
        try {
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(MachinistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            L1.setText("старт с "+rs.getString("station_start")+" в "+rs.getString("time_start")+" станции, прибытие в станцию"+rs.getString("station_end")+" о "+rs.getString("time_end"));
        } catch (SQLException ex) {
            Logger.getLogger(MachinistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //L2.setText(null);
        //
        rs=BofD.make("SELECT distance,alsn,piket,num_section,stoplight,N FROM alsn WHERE itinerary_id="+JavaFXApplication1.myStId+"");
        System.out.println("F7");
        try {
            usersData.clear();
            while(rs.next()){
                System.out.println("F8");
                ALSN temp=new ALSN();
                temp.setN(rs.getInt("N"));
                temp.setAlsn(rs.getString("alsn"));
                temp.setPiket(rs.getInt("piket"));
                temp.setNum_section(rs.getInt("num_section"));
                temp.setStoplight(rs.getString("stoplight"));
                //
                
                usersData.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FxmlFormsController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("F9");
        }
        // TODO
        System.out.println("F3");
        N1.setCellValueFactory(new PropertyValueFactory<ALSN, String>("N"));
        Km1.setCellValueFactory(new PropertyValueFactory<ALSN, String>("distance"));
        Pi1.setCellValueFactory(new PropertyValueFactory<ALSN, String>("piket"));
        AL1.setCellValueFactory(new PropertyValueFactory<ALSN, String>("alsn"));
        NS1.setCellValueFactory(new PropertyValueFactory<ALSN, String>("num_section"));
        Li1.setCellValueFactory(new PropertyValueFactory<ALSN, String>("stoplight"));
        System.out.println("F4");
        //
        table.setItems(usersData);
        System.out.println("F5");
    }    
    
    public void B1add(ActionEvent actionEvent) throws SQLException
    {
        System.out.println("F66");
        BofD.statement.execute("INSERT INTO  alsn (distance,alsn,piket,num_section,stoplight,itinerary_id) " 
                            +"VALUES ("+parseInt( km.getText())+",'"
                +alsn.getText()+"',"+parseInt(piket.getText())+","
                +parseInt(nomer.getText())+",'"+light.getText()+"',"+JavaFXApplication1.myStId+")"  );
        
       System.out.println("F6");
        //
        ResultSet rs=BofD.make("SELECT distance,alsn,piket,num_section,stoplight,N FROM alsn WHERE itinerary_id="+JavaFXApplication1.myStId+"");
        System.out.println("F7");
        try {
            usersData.clear();
            while(rs.next()){
                System.out.println("F8");
                ALSN temp=new ALSN();
                temp.setN(rs.getInt("N"));
                temp.setAlsn(rs.getString("alsn"));
                temp.setPiket(rs.getInt("piket"));
                temp.setNum_section(rs.getInt("num_section"));
                temp.setStoplight(rs.getString("stoplight"));
                //
                
                usersData.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FxmlFormsController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("F9");
        }
        
        System.out.println("F10");
        
        
        //usersData.add(new Way( Station.getText(), calendar, TimeWait.getText() ));
        
        
        
    }
    
    public void B1del(ActionEvent actionEvent) throws SQLException
    {
        
        BofD.statement.execute("DELETE FROM alsn WHERE N="+id2.getText()+" and itinerary_id="+JavaFXApplication1.myStId+"" );
        /*
        usersData.remove(usersData.size()-1);
        ObservableList<Way> stationDr = FXCollections.observableArrayList(usersData);
        int legth=graff.build(stationDr);//--
        setArrivalT(legth);//установки времени прибытия
                */
        ResultSet rs=BofD.make("SELECT distance,alsn,piket,num_section,stoplight,N FROM alsn WHERE itinerary_id="+JavaFXApplication1.myStId+"");
        System.out.println("F7");
        try {
            usersData.clear();
            while(rs.next()){
                System.out.println("F8");
                ALSN temp=new ALSN();
                temp.setN(rs.getInt("N"));
                temp.setAlsn(rs.getString("alsn"));
                temp.setPiket(rs.getInt("piket"));
                temp.setNum_section(rs.getInt("num_section"));
                temp.setStoplight(rs.getString("stoplight"));
                //
                
                usersData.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FxmlFormsController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("F9");
        }
    }
    
}
