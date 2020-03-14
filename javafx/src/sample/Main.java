package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;

import java.awt.*;

public class Main extends Application implements EventHandler<ActionEvent> {
    int processes[]=new int[100];
    int n = processes.length;
    int i=0;
    // Burst time of all processes
    int burst_time[]=new int[100];

    // Arrival time of all processes
    int arrival_time[]=new int[100];
    Button button;
    Stage window;
    Scene scene1;
    TextField TF2=new TextField();
    TextField TF4=new TextField();
    TextField TF9=new TextField();
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Label label2=new Label("dosy3m");
       // Button button2=new Button("here");
        //button.setOnAction(e->{programclose(); });
       // button2.setOnAction(e->window.setScene(scene1));
//        HBox topmenu=new HBox();
//        Button buttona=new Button("file");
//        Button buttonb=new Button("edit");
//        Button buttonc=new Button("view");
//        topmenu.getChildren().addAll(buttonc,buttona,buttonb);
//
//
//        VBox leftmenu=new VBox();
//        Button buttonE=new Button("file");
//        Button buttonF=new Button("edit");
//        Button buttonG=new Button("view");
//        leftmenu.getChildren().addAll(buttonE,buttonF,buttonG);
//
//        BorderPane layout =new BorderPane();
//        layout.setTop(topmenu);
//        layout.setLeft(leftmenu);
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

        Label TF10=new Label ("Priority Time");
        GridPane.setConstraints(TF10,0,2);
        TF9.setPromptText("0");
        GridPane.setConstraints(TF9,1,2);


        Button TF5=new Button("Add Process");
        GridPane.setConstraints(TF5,3,0);
        Button TF6=new Button("Calculate");
        GridPane.setConstraints(TF6,3,2);
        CheckBox cb1=new CheckBox("FCFS");
        GridPane.setConstraints(cb1,2,0);
        CheckBox cb2=new CheckBox("SJF");
        GridPane.setConstraints(cb2,2,1);
        CheckBox cb3=new CheckBox("RR");
        GridPane.setConstraints(cb3,2,2);
        CheckBox cb4=new CheckBox("Priority");
        GridPane.setConstraints(cb4,2,3);
        grid.getChildren().addAll(TF1,TF2,TF3,TF4,TF5,TF6,cb1,cb2,cb3,cb4,TF10,TF9);
        window=new Stage();
        TF5.setOnAction(e->addprogram());
        TF6.setOnAction(e->calculateprogram());
       // VBox layout =new VBox(20);
        window.setOnCloseRequest(e->{e.consume();
        programclose();});
      //  layout.getChildren().addAll(label1,button);
        scene1= new Scene(grid);
        window.setScene(scene1);
        window.show();
    }
    public void calculateprogram(){
        FCFSArrivaltime.findavgTime(processes, i, burst_time, arrival_time);
    }
    public void addprogram(){
        processes[i]=i;
        burst_time[i]=Integer.parseInt(TF2.getText());
       arrival_time[i]=Integer.parseInt(TF4.getText());
       i++;
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



//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("scheduling algorithms");
//        //primaryStage.setScene(new Scene(root, 300, 275));
//        button=new Button("OK");
//        button.setOnAction(e->System.out.println("ohyea123hhh"));
////        button.setOnAction(new EventHandler<ActionEvent>() {
////            @Override
////            public void handle(ActionEvent actionEvent) {
////                System.out.println("ohyeahhh");
////            }
////        });
//       // primaryStage.show();
//        StackPane   layout=new StackPane();
//        layout.getChildren().add(button);
//        Scene scene=new Scene(layout,300,250);
//        primaryStage.setScene(scene);
//        primaryStage.show();

//    @Override
//    public void handle(ActionEvent actionEvent) {
//
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }

