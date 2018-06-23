package javafxapplication1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafxapplication1.GraffPanel.gc;
/**
 * FXML Controller class
 *
 * @author bio
 */
public class FxmlFormsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public ChoiceBox ChoiceTrain;
    @FXML
    public ChoiceBox ChoiceM;
    @FXML
    public DatePicker Datek;
    @FXML
    public TextField TimeOut;
    
    
    @FXML
    public TableView tableWay;
    @FXML
    public TableColumn stationColumn;
    @FXML
    public TableColumn timeAColumn;
    @FXML
    public TableColumn timeWColumn;
    @FXML
    public AnchorPane pictureWall;
    
    @FXML
    public Button add;
    @FXML
    public Button del;
    @FXML
    public Button take;
    
    @FXML
    public TextField Station;
    @FXML
    public TextField TimeWait;
    @FXML
    public Label TimeArrival;
    
    
    GraffPanel graff=new GraffPanel();
    private boolean activSt=false;
    private String selectSt="";//для отметки станции до добавления
    private ObservableList<Way> usersData = FXCollections.observableArrayList();//данные для таблицы
    protected Map LTrain=new HashMap<String, Integer>();// список поездов
    protected ObservableList<String> trains = FXCollections.observableArrayList();
    protected Map LMachinists=new HashMap<String, String>();// список машинистов
    protected ObservableList<String> machinists = FXCollections.observableArrayList();
    LocalDate date;//дата в календаре
    Calendar calendar;
    private static SimpleDateFormat dateFormat;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //pictureWall.screenToLocal(Point2D.GraffPanel);//незнаю ерунда
        //GraffPanel graff=new GraffPanel();
        //SELECT id,model FROM `trains` WHERE station_id=1 and itinerary_id IS NULL
        
        ResultSet rs=BofD.make("SELECT id,model FROM trains WHERE station_id="+JavaFXApplication1.myStId
                +" and itinerary_id IS NULL");
        try {
            while(rs.next()){
                String name=rs.getString("id")+"/"+rs.getString("model");
                LTrain.put(name, rs.getString("id"));
                trains.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FxmlFormsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChoiceTrain.setItems(trains);
        rs=BofD.make("SELECT fio,passport_id FROM `machinists` WHERE station_id="+JavaFXApplication1.myStId
                +" and itinerary_id IS NULL");
        try {
            while(rs.next()){
                String name=rs.getString("fio");
                LMachinists.put(name, rs.getString("passport_id"));
                machinists.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FxmlFormsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChoiceM.setItems(machinists);
        //-------------------------------------------------------------------------------------
        date = Datek.getValue();
        //
        pictureWall.getChildren().addAll(graff);
        graff.paint();
        add.setDisable(true);
        stationColumn.setCellValueFactory(new PropertyValueFactory<Way, String>("station"));
        timeAColumn.setCellValueFactory(new PropertyValueFactory<Way, String>("timeArrive"));
        timeWColumn.setCellValueFactory(new PropertyValueFactory<Way, String>("timeWait"));
        tableWay.setItems(usersData);
        
        calendar = Calendar.getInstance();
    }
    
    private void activAdd()//активность кнопки добавления
    {
        Pattern p = Pattern.compile("^[0-9]{1,2}[\\:][0-9]{1,2}$");  
        Matcher m = p.matcher(TimeWait.getText());  
        Matcher m1 = p.matcher(TimeOut.getText());  
        if(m.matches() && m1.matches() && activSt)
        {
            add.setDisable(false);
        }
        else
            add.setDisable(true);
    }
    public void setArrivalT(int legth)
    {
        System.out.println("зап0");
        
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
        calendar.set(Datek.getValue().getYear(), Datek.getValue().getMonthValue(), Datek.getValue().getDayOfMonth(),
                parseInt(TimeOut.getText().split(":")[0]),parseInt(TimeOut.getText().split(":")[1]));
        System.out.println("зап1");
        //calendar.
        for(int i=0;i<usersData.size();i++){
            System.out.println("зап2");
            usersData.get(i).getTimeWait();
            calendar.add(Calendar.HOUR, parseInt(TimeWait.getText().split(":")[0]));
            calendar.add(Calendar.MINUTE, parseInt(TimeWait.getText().split(":")[1]));
        }
        calendar.add(Calendar.HOUR, (legth/100));
        calendar.add(Calendar.MINUTE, (int)(((legth/100.0)%1)*60));
        System.out.println("зап3");
        TimeArrival.setText( dateFormat.format(calendar.getTime()));
        //(calendar.getTime().toString());
        System.out.println("зап4");
    }
    public void TStationRel(KeyEvent e) throws SQLException
    {
        
        System.out.println("запрос TStationRel ---");
        if ( !(e.getCode().isLetterKey()))
        {
            if ( selectSt.compareTo( Station.getText() )!=0 )
            {
                activSt=false;
                activAdd();
            }
            return;
        }
        ResultSet rs=BofD.make("SELECT name FROM stations where name like '"+Station.getText()+"%'" );
        rs.last();
        if( rs.getRow()==1 )
        {
            Station.setText(rs.getString("name"));
            activSt=true;
            selectSt=rs.getString("name");
            //
            ObservableList<Way> stationDr = FXCollections.observableArrayList(usersData);
            stationDr.add(new Way(selectSt,calendar,""));
            int legth=graff.build(stationDr);//--
            setArrivalT(legth);//установки времени прибытия
        }
        else
            activSt=false;
        activAdd();
        //отобразить на карте-----------------------------------------------------------------
        
    }
    public void TWaitRel(KeyEvent e) throws SQLException
    {
        TimeWait.getCursor().toString();
        System.out.println("запрос TStationRel --- "+TimeWait.getCursor().toString());
        int kol=TimeWait.getLength();
        
        if (e.getCode().isLetterKey())
        {
            String t = TimeWait.getText(0, kol-2);
                TimeWait.setText(t);
                return;
        }
        boolean isNum=true;
        try {
            parseInt(TimeWait.getText());
        }
        catch (Exception error) {
            isNum=false;
        }
        if(isNum) {
            if(kol==2)
                TimeWait.setText(TimeWait.getText()+":");
            else
                if ( kol<2 && e.getCode()== KeyCode.RIGHT){
                        if(kol==1)
                            TimeWait.setText("0"+TimeWait.getText()+":");
                        else
                            TimeWait.setText("00:");
                    }
            
        }
        activAdd();
    }

    public void B1add(ActionEvent actionEvent) throws SQLException
    {
        usersData.add(new Way( Station.getText(), calendar, TimeWait.getText() ));
        
        
    }
    
    public void B2del(ActionEvent actionEvent) throws SQLException
    {
        usersData.remove(usersData.size()-1);
        ObservableList<Way> stationDr = FXCollections.observableArrayList(usersData);
        int legth=graff.build(stationDr);//--
        setArrivalT(legth);//установки времени прибытия
    }
    
    public void B3take(ActionEvent actionEvent)
    {
        System.out.println("запрос2---");

        TextField loginField; 
        PasswordField passwordField;
        loginField = new TextField();
        loginField.setPromptText("логин");
        loginField.setPrefWidth(190);
        passwordField = new PasswordField();
        passwordField.setPromptText("пароль");
        passwordField.setPrefWidth(190);
        Label label=new Label("Создай аватара для миссии");
        Button btn1 = new Button("отмена");
        Button btn2 = new Button("активировать");
        //
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(btn1, btn2);
        box.getChildren().addAll(label, loginField,passwordField, buttons);
        Scene scene = new Scene(box);
        dialog.setScene(scene);
        dialog.show();
        //
        btn1.setOnAction((ActionEvent event) -> {
            dialog.close();
        });
        btn2.setOnAction((ActionEvent event) -> {
            ResultSet rs=BofD.make("SELECT COUNT(login) as kol FROM avatars WHERE login like'"+loginField.getText()+"'");
            try {
                rs.next();
                int kol=0;
                kol=rs.getInt("kol");
                if(kol==0){
                    //
                    System.out.println("аватар");
                    rs=BofD.make("SELECT id FROM admins WHERE station_id="+JavaFXApplication1.myStId+" ");
                    rs.next();
                    int idAdm=rs.getInt("id");
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println("аватар0-0");
                    
                    BofD.statement.execute("INSERT INTO avatars (passport_id, start_date, end_date, "
                            + "login, password, admin_id) " 
                            +"VALUES ('"+LMachinists.get(ChoiceM.getValue())+"',"
                            + " '"+Datek.getValue().getYear()+"-"+Datek.getValue().getMonthValue()+"-"+Datek.getValue().getDayOfMonth()+"',"
                            + " '"+dateFormat1.format(calendar.getTime())+"',"
                            + " '"+loginField.getText()+"',"
                            + " '"+passwordField.getText()+"',"
                            + " "+idAdm+")");
                    //создание маршрутных листов
                    String startSt=JavaFXApplication1.mySt, endSt="";
                    int hours=parseInt(TimeOut.getText().split(":")[0]);
                    int minuts=parseInt(TimeOut.getText().split(":")[1]);
                    //
                    
                    Calendar timeStart = Calendar.getInstance();
                    timeStart.set(Datek.getValue().getYear(), Datek.getValue().getMonthValue(), Datek.getValue().getDayOfMonth(),
                parseInt(TimeOut.getText().split(":")[0]),parseInt(TimeOut.getText().split(":")[1]));
                    //
                    
                    for(int i=0;i<usersData.size();i++){
                        endSt=usersData.get(i).getStation();
                        System.out.println("аватар0-4"+ "SELECT itinerary_id from avatars WHERE login LIKE '"+loginField.getText()+"'");
                        ResultSet rs1=BofD.make("SELECT itinerary_id from avatars WHERE login LIKE '"+loginField.getText()+"'");
                        System.out.println("аватар0-33");
                        rs1.next();
                        System.out.println("аватар0-333");
                        int itinerary_id=rs1.getInt("itinerary_id");
                        System.out.println("аватар0-3");
                        System.out.println("аватар0-5" +"INSERT INTO  itineraries (identification, train_id, station_start, "
                            + "station_end, time_start, time_end) " 
                            +"VALUES ("+itinerary_id+","
                            + " "+LTrain.get(ChoiceTrain.getValue())+","
                            + " '"+startSt+"',"
                            + " '"+endSt+"',"
                            + " '"+dateFormat.format(timeStart.getTime())+"',"
                            + " '"+dateFormat.format(usersData.get(i).getTimeArrive().getTime() )+"')");
                        System.out.println("аватар0-2");
                        BofD.statement.execute("INSERT INTO  itineraries (identification, train_id, station_start, "
                            + "station_end, time_start, time_end) " 
                            +"VALUES ("+itinerary_id+","
                            + " "+LTrain.get(ChoiceTrain.getValue())+","
                            + " '"+startSt+"',"
                            + " '"+endSt+"',"
                            + " '"+dateFormat.format(timeStart.getTime())+"',"
                            + " '"+dateFormat.format(usersData.get(i).getTimeArrive().getTime() )+"')");
                        startSt=endSt;
                        //
                        timeStart= usersData.get(i).getTimeArrive() ;
                        timeStart.add(Calendar.HOUR, parseInt(usersData.get(i).getTimeWait().split(":")[0]));
                        timeStart.add(Calendar.MINUTE, parseInt(usersData.get(i).getTimeWait().split(":")[1]));
                    }
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("FxmlForms.fxml"));
                    } catch (IOException ex) {
                       System.out.println("FxmlForms.fxml чет не то");
                    }
                    Scene scene2 = new Scene(root);
                    JavaFXApplication1.pStage.setScene(scene2);
                    JavaFXApplication1.pStage.show();
                        dialog.close();
                    }
                else
                    label.setText("придумайте другой аватар");
            } catch (SQLException ex) {
                Logger.getLogger(FxmlFormsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
