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

import java.util.Arrays;

import static java.util.Arrays.sort;

public class RR {
    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(int processes[], int n,
                                int bt[], int wt[], int quantum)
    {
        // Make a copy of burst times bt[] to store remaining
        // burst times.
        int rem_bt[] = new int[n];
        for (int i = 0 ; i < n ; i++)
            rem_bt[i] =  bt[i];

        int t = 0; // Current time

        // Keep traversing processes in round robin manner
        // until all of them are not done.
        while(true)
        {
            boolean done = true;

            // Traverse all processes one by one repeatedly
            for (int i = 0 ; i < n; i++)
            {
                // If burst time of a process is greater than 0
                // then only need to process further
                if (rem_bt[i] > 0)
                {
                    done = false; // There is a pending process

                    if (rem_bt[i] > quantum)
                    {
                        // Increase the value of t i.e. shows
                        // how much time a process has been processed
                        t += quantum;

                        // Decrease the burst_time of current process
                        // by quantum
                        rem_bt[i] -= quantum;
                    }

                    // If burst time is smaller than or equal to
                    // quantum. Last cycle for this process
                    else
                    {
                        // Increase the value of t i.e. shows
                        // how much time a process has been processed
                        t = t + rem_bt[i];

                        // Waiting time is current time minus time
                        // used by this process
                        wt[i] = t - bt[i];

                        // As the process gets fully executed
                        // make its remaining burst time = 0
                        rem_bt[i] = 0;
                    }
                }
            }

            // If all processes are done
            if (done == true)
                break;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(int processes[], int n,
                                   int bt[], int wt[], int tat[])
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n ; i++)
            tat[i] = bt[i] + wt[i];
    }

    // Method to calculate average time
    static void findavgTime(int processes[], int n, int bt[],
                            int quantum)
    {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all processes
        findWaitingTime(processes, n, bt, wt, quantum);

        // Function to find turn around time for all processes
        findTurnAroundTime(processes, n, bt, wt, tat);

        // Display processes along with all details
        System.out.println("Processes " + " Burst time " +
                " Waiting time " + " Turn around time");

        // Calculate total waiting time and total turn
        // around time
        for (int i=0; i<n; i++)
        {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + (i+1) + "\t\t" + bt[i] +"\t " +
                    wt[i] +"\t\t " + tat[i]);
        }

        System.out.println("Average waiting time = " +
                (float)total_wt / (float)n);
        System.out.println("Average turn around time = " +
                (float)total_tat / (float)n);
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
        Window2.setTitle("RR");
        Window2.setScene(scene);
        TF5.setOnAction(e->{
            Window2.close();});
        Window2.show();

        Stage stage = new Stage();
        stage.setTitle("Gantt Chart Sample");

        String[] machines = new String[]{"Processes"};

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number, String> chart = new GanttChart<Number, String>(xAxis, yAxis);
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
        int total = 0;
        for (int i = 0; i < n; i++)
            total += bt[i];

        int Q=quantum;
        int p=0;
        machine = machines[0];
        XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i < total; i++) {
            if (p % 7 == 0)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-red")));
            else if (p % 7 == 1)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-green")));
            else if (p % 7 == 2)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-blue")));
            else if (p % 7 == 3)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-yellow")));
            else if (p % 7 == 4)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-purple")));
            else if (p % 7 == 5)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-cyan")));
            else if (p % 7 == 6)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-grey")));
             if(i==(total-1) )break;
            bt[p] -= 1;
            Q--;
            if(Q == 0 ){ p=(p+1)%n; Q=quantum;
            while(bt[p]==0)p=(p+1)%n;
                }
            if(bt[p]==0){
                Q=quantum;
                while(bt[p]==0)p=(p+1)%n;
            }
        }




//            if(i%3==0) series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(rxt[i], "status-red")));
//            else if(i%3==1) series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(rxt[i], "status-green")));
//            else if(i%3==2) series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(rxt[i], "status-blue")));
//            distance+=rxt[i];



        chart.getData().addAll(series1);
        chart.getStylesheets().add(FCFSArrivaltime.class.getResource("ganttchart.css").toExternalForm());

        Scene scenex  = new Scene(chart,620,350);
        stage.setScene(scenex);
        stage.show();

    }
    }

    // Driver Method
//    public static void main(String[] args)
//    {
//        // process id's
//        int processes[] = { 1, 2, 3};
//        int n = processes.length;
//
//        // Burst time of all processes
//        int burst_time[] = {10, 5, 8};
//
//        // Time quantum
//        int quantum = 2;
//        findavgTime(processes, n, burst_time, quantum);
//    }

