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

class Process
{
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time

    public Process(int pid, int bt, int art)
    {
        this.pid = pid;
        this.bt = bt;
        this.art = art;

    }

}

public class SRTF
{
    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(Process proc[], int n,
                                int wt[])
    {
        int rt[] = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets
        // completed
        while (complete != n) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++)
            {
                if ((proc[j].art <= t) &&
                        (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time -
                        proc[shortest].bt -
                        proc[shortest].art;

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Process proc[], int n,
                                   int wt[], int tat[])
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }

    // Method to calculate average time
    static void findavgTime(Process proc[], int n) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(proc, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(proc, n, wt, tat);

        // Display processes along with all
        // details
        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + proc[i].pid + "\t\t"
                    + proc[i].bt + "\t\t " + wt[i]
                    + "\t\t" + tat[i]);
        }

        System.out.println("Average waiting time = " +
                (float) total_wt / (float) n);
        System.out.println("Average turn around time = " +
                (float) total_tat / (float) n);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        TextField TF7 = new TextField(String.valueOf((float) total_wt / (float) n));
        TextField TF8 = new TextField(String.valueOf((float) total_tat / (float) n));
        Label TF1 = new Label("waiting time");
        GridPane.setConstraints(TF1, 0, 0);
        GridPane.setConstraints(TF7, 1, 0);
        Label TF3 = new Label("turn around time");
        GridPane.setConstraints(TF3, 0, 1);
        GridPane.setConstraints(TF8, 1, 1);
        Button TF5 = new Button("exit");
        GridPane.setConstraints(TF5, 1, 2);
        grid.getChildren().addAll(TF1, TF7, TF3, TF8, TF5);
        Scene scene = new Scene(grid, 300, 150);
        Stage Window2 = new Stage();
        Window2.setTitle("SRTF");
        Window2.setScene(scene);
        TF5.setOnAction(e -> {
            Window2.close();
        });
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
        int rxt[] = new int[n];
        boolean arrived[] = new boolean[n];
        boolean consumed[] = new boolean[n];
        int smallest = 100;
        int smallestvalue = 100;
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += proc[i].bt;
            rxt[i] = proc[i].bt;
        }
        sort(rxt, 0, n);
        machine = machines[0];
        XYChart.Series series1 = new XYChart.Series();
        int distance = 0;
        for (int i = 0; i < total; i++) {
            for (int p = 0; p < n; p++) {
                if (proc[p].art <= i) {
                    if(smallestvalue==0)smallestvalue=100;
                    if ((proc[p].bt < smallestvalue)&&(proc[p].bt>0)) {
                        smallest = p;
                        smallestvalue = proc[p].bt;
                    }
                }
            }
            if (smallest % 7 == 0)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-red")));
            else if (smallest % 7 == 1)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-green")));
            else if (smallest % 7 == 2)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-blue")));
            else if (smallest % 7 == 3)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-yellow")));
            else if (smallest % 7 == 4)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-purple")));
            else if (smallest % 7 == 5)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-cyan")));
            else if (smallest % 7 == 6)
                series1.getData().add(new XYChart.Data(i, machine, new GanttChart.ExtraData(1, "status-grey")));


            proc[smallest].bt -= 1;
            smallestvalue -= 1;
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

    // Driver Method
    public static void mainSRTF(int processes[], int n, int bt[], int at[])
    { Process proc[]=new Process[100];
        for(int i=0;i<n;i++){
           proc[i]=new Process(i+1, bt[i],at[i]);
        }
//        Process proc[] = { new Process(1, 6, 1),
//                new Process(2, 8, 1),
//                new Process(3, 7, 2),
//                new Process(4, 3, 3)};

        findavgTime(proc,n);
    }
}