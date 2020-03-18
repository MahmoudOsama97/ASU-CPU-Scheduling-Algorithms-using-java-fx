package sample;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.ParseException;
import java.util.Arrays;

public class FCFS{

    // Function to find the waiting time for all
    // processes
    static void findWaitingTime(int processes[], int n,
                                int bt[], int wt[]) {
        // waiting time for first process is 0
        wt[0] = 0;

        // calculating waiting time
        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }
    }

    // Function to calculate turn around time
    static void findTurnAroundTime(int processes[], int n,
                                   int bt[], int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }
    }

    //Function to calculate average time
    public static void findavgTime(int processes[], int n, int bt[]) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        //Function to find waiting time of all processes
        findWaitingTime(processes, n, bt, wt);

        //Function to find turn around time for all processes
        findTurnAroundTime(processes, n, bt, wt, tat);

        //Display processes along with all details
        System.out.printf("Processes Burst time Waiting"
                +" time Turn around time\n");

        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.printf(" %d ", (i + 1));
            System.out.printf("     %d ", bt[i]);
            System.out.printf("     %d", wt[i]);
            System.out.printf("     %d\n", tat[i]);
        }
        float s = (float)total_wt /(float) n;
        float t = (float)total_tat / (float)n;

        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        TextField TF7=new TextField(String.valueOf((float)total_wt / (float)n));
        TextField TF8=new TextField(String.valueOf((float)total_tat / (float)n));
        Label TF1=new Label("waiting time");
        GridPane.setConstraints(TF1,0,0);
        GridPane.setConstraints(TF7,1,0);
        Label TF3=new Label ("turn around time");
        GridPane.setConstraints(TF3,0,1);
        GridPane.setConstraints(TF8,1,1);
        Button TF5=new Button("exit");
        GridPane.setConstraints(TF5,1,2);
        grid.getChildren().addAll(TF1,TF7,TF3,TF8,TF5);
        Scene scene=new Scene(grid,300,150);
        Stage Window2=new Stage();
        Window2.setTitle("FCFS");
        Window2.setScene(scene);
        TF5.setOnAction(e->{
            Window2.close();});
        Window2.show();


        Stage stage=new Stage();
        stage.setTitle("Gantt Chart Sample");

        String[] machines = new String[] { "Processes"};

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(4);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(machines)));

        chart.setTitle("GanttChart");
        chart.setLegendVisible(false);
        chart.setBlockHeight(50);
        String machine;

        machine = machines[0];
        XYChart.Series series1 = new XYChart.Series();
        int distance=0;
        for(int i=0;i<n;i++) {
            if (i % 7 == 0)
                series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(bt[i], "status-red")));
            else if (i % 7 == 1)
                series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(bt[i], "status-green")));
            else if (i % 7 == 2)
                series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(bt[i], "status-blue")));
            else if (i % 7 == 3)
                series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(bt[i], "status-yellow")));
            else if (i % 7 == 4)
                series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(bt[i], "status-purple")));
            else if (i % 7 == 5)
                series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(bt[i], "status-cyan")));
            else if (i % 7 == 6)
                series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(bt[i], "status-grey")));
            distance+=bt[i];

        }

        chart.getData().addAll(series1);
        chart.getStylesheets().add(FCFSArrivaltime.class.getResource("ganttchart.css").toExternalForm());

        Scene scenex  = new Scene(chart,620,350);
        stage.setScene(scenex);
        stage.show();

        System.out.println("Average waiting time ="+s);
        System.out.println("\n");
        System.out.println("Average turn around time ="+t);
    }

    // Driver code
//    public static void main(String[] args) throws ParseException {
//        //process id's
//        int processes[] = {1, 2, 3};
//        int n = processes.length;
//
//        //Burst time of all processes
//        int burst_time[] = {10, 5, 8};
//
//        findavgTime(processes, n, burst_time);
//
//    }
}
