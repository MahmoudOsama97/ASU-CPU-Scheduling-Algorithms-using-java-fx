package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.chart.XYChart;

import java.util.Arrays;

public class Main extends Application implements EventHandler<ActionEvent> {
    int processes[]=new int[100];
    int n = processes.length;
    int i=0;
    int type=1;
    int q=4;
    // Burst time of all processes
    int burst_time[]=new int[100];

    // Arrival time of all processes
    int arrival_time[]=new int[100];
    //priority for all process
    int priority[]=new int[100];
    Button button;
    Stage window;
    Scene scene1;
    TextField TF2=new TextField();
    TextField TF4=new TextField();
    TextField TF9=new TextField();
    TextField TF11=new TextField();
    Button TF5=new Button("Add Process P1");
    Button TF6=new Button("Calculate");
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        Label TF1=new Label("Burst Time");
        GridPane.setConstraints(TF1,0,0);
        TF2.setPromptText("0");
        GridPane.setConstraints(TF2,1,0);


        Label TF3=new Label ("Arrival Time");
        GridPane.setConstraints(TF3,0,1);
        TF4.setPromptText("0");
        GridPane.setConstraints(TF4,1,1);

        Label TF10=new Label ("Priority");
        GridPane.setConstraints(TF10,0,2);
        TF9.setPromptText("0");
        GridPane.setConstraints(TF9,1,2);

        Label TF12=new Label ("quantum");
        GridPane.setConstraints(TF12,0,3);
        TF11.setPromptText("0");
        GridPane.setConstraints(TF11,1,3);

        GridPane.setConstraints(TF5,3,1);

        GridPane.setConstraints(TF6,3,2);

        CheckBox cb1=new CheckBox("FCFS");
        GridPane.setConstraints(cb1,2,0);
        CheckBox cb2=new CheckBox("SJF");
        GridPane.setConstraints(cb2,2,1);
        CheckBox cb3=new CheckBox("RR");
        GridPane.setConstraints(cb3,2,2);
        CheckBox cb4=new CheckBox("Priority");
        GridPane.setConstraints(cb4,2,3);
        cb1.setSelected(true);

        TF2.setDisable(false);
        TF4.setDisable(true);
        TF9.setDisable(true);
        TF11.setDisable(true);



        grid.getChildren().addAll(TF1,TF2,TF3,TF4,TF5,TF6,cb1,cb2,cb3,cb4,TF10,TF9,TF11,TF12);
        window=new Stage();

        TF5.setOnAction(e->addprogram());
        TF6.setOnAction(e->calculateprogram());
        cb1.setOnAction(e->checkboxhadner1(cb1,cb2,cb3,cb4));
        cb2.setOnAction(e->checkboxhadner2(cb1,cb2,cb3,cb4));
        cb3.setOnAction(e->checkboxhadner3(cb1,cb2,cb3,cb4));
        cb4.setOnAction(e->checkboxhadner4(cb1,cb2,cb3,cb4));

       // VBox layout =new VBox(20);
        window.setOnCloseRequest(e->{e.consume();
        programclose();});
      //  layout.getChildren().addAll(label1,button);
        scene1= new Scene(grid);
        window.setTitle("Scheduling Program");
        window.setScene(scene1);
        window.show();

    }
    public void checkboxhadner1( CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4){
        if(cb1.isSelected()) {
            type = 1;
            TF5.setText("Add Process P1");
            cb2.setSelected(false);
            cb3.setSelected(false);
            cb4.setSelected(false);
            TF2.setDisable(false);
            TF4.setDisable(true);
            TF9.setDisable(true);
            TF11.setDisable(true);
            for(int j=0;j<100;j++){
                burst_time[j]=0;
                arrival_time[j]=0;
                priority[j]=0;
            } i=0;
        }

    }
    public void checkboxhadner2( CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4){
        if(cb2.isSelected()) {
            type = 2;
            TF5.setText("Add Process P1");
            cb1.setSelected(false);
            cb3.setSelected(false);
            cb4.setSelected(false);
            TF2.setDisable(false);
            TF4.setDisable(false);
            TF9.setDisable(true);
            TF11.setDisable(true);
            for(int j=0;j<100;j++){
                burst_time[j]=0;
                arrival_time[j]=0;
                priority[j]=0;
            } i=0;
        }

    }
    public void checkboxhadner3( CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4){
        if(cb3.isSelected()) {
            type = 3;
            TF5.setText("Add Process P1");
            cb2.setSelected(false);
            cb1.setSelected(false);
            cb4.setSelected(false);
            TF2.setDisable(false);
            TF4.setDisable(true);
            TF9.setDisable(true);
            TF11.setDisable(false);
            for(int j=0;j<100;j++){
                burst_time[j]=0;
                arrival_time[j]=0;
                priority[j]=0;
            } i=0;

        }

    }
    public void checkboxhadner4( CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4){
        if(cb4.isSelected()) {
            type = 4;
            TF5.setText("Add Process P1");
            cb2.setSelected(false);
            cb3.setSelected(false);
            cb1.setSelected(false);
            TF2.setDisable(false);
            TF4.setDisable(true);
            TF9.setDisable(false);
            TF11.setDisable(true);
            for(int j=0;j<100;j++){
                burst_time[j]=0;
                arrival_time[j]=0;
                priority[j]=0;
            } i=0;
        }

    }
    public void calculateprogram(){
        if(type==1) {
            FCFS.findavgTime(processes, i, burst_time);
        }
        if(type==2){
            SRTF.mainSRTF(processes, i, burst_time, arrival_time);
        }
        if(type==3){
            RR.findavgTime(processes, i, burst_time, q);
        }
        if(type==4)
            Priority.mainP(processes,i,burst_time,priority);
    }
    public void addprogram(){
        if(type==1){
            processes[i]=i;
            burst_time[i]=Integer.parseInt(TF2.getText());
            i++;
        }
        if(type==2){
            processes[i]=i;
            burst_time[i]=Integer.parseInt(TF2.getText());
            arrival_time[i]=Integer.parseInt(TF4.getText());
            i++;
        }
        if(type==3){
            processes[i]=i;
            burst_time[i]=Integer.parseInt(TF2.getText());
            q=Integer.parseInt(TF11.getText());
            i++;
        }
        if(type==4){
            processes[i]=i;
            burst_time[i]=Integer.parseInt(TF2.getText());
            priority[i]=Integer.parseInt(TF9.getText());
            i++;
        }
        TF5.setText("Add Process P"+(i+1));
    }
    public void programclose(){
        Boolean answer= ConfirmBox.display("Exit","Are you sure you want to exit?");
        if(answer) window.close();
    }
        @Override
    public void handle(ActionEvent actionEvent) {


    }
        public static void main(String[] args) {
        launch(args);
    }

}