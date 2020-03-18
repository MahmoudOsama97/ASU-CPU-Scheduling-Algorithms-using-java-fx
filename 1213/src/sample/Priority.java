
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

class ProcessP implements Comparable<ProcessP> {
    int pid;  // Process ID
    int bt;   // CPU Burst time required
    int priority; // Priority of this process

    public int getQuantity() {
        return priority;
    }
    public ProcessP(int pid, int bt, int p)
    {
        this.pid = pid;
        this.bt = bt;
        this.priority = p;
    }
    public int compareTo(ProcessP compareFruit) {

        int compareQuantity = ((ProcessP) compareFruit).getQuantity();

        //ascending order
        return this.priority - compareQuantity;
    }
}

public  class Priority {
    // Function to sort the Process acc. to priority
    boolean comparison(ProcessP a, ProcessP b) {
        return (a.priority > b.priority);
    }

    // Function to find the waiting time for all
// processes
    static void findWaitingTime(ProcessP proc[], int n,
                                int wt[]) {
        // waiting time for first process is 0
        wt[0] = 0;

        // calculating waiting time
        for (int i = 1; i < n; i++)
            wt[i] = proc[i - 1].bt + wt[i - 1];
    }

    // Function to calculate turn around time
    static void findTurnAroundTime(ProcessP proc[], int n,
                            int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }

    //Function to calculate average time
   static void findavgTime(ProcessP proc[], int n) {
        int wt[]=new int[n];
        int tat[]=new int[n];
        int total_wt = 0;
        int total_tat = 0;

        //Function to find waiting time of all processes
        findWaitingTime(proc, n, wt);

        //Function to find turn around time for all processes
        findTurnAroundTime(proc, n, wt, tat);

        //Display processes along with all details
        System.out.println( "\nProcesses  " + " Burst time  "
               + " Waiting time  " + " Turn around time\n");

        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println( "   " + proc[i].pid + "\t\t"
                    + proc[i].bt + "\t    " + wt[i]
                    + "\t\t  " + tat[i]+"\n" );
        }

        System.out.println( "\nAverage waiting time = "
                + (float) total_wt / (float) n);
        System.out.println("\nAverage turn around time = "
                + (float) total_tat / (float) n);
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
        Window2.setTitle("SRTF");
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
               series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(proc[i].bt, "status-red")));
           else if (i % 7 == 1)
               series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(proc[i].bt, "status-green")));
           else if (i % 7 == 2)
               series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(proc[i].bt, "status-blue")));
           else if (i % 7 == 3)
               series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(proc[i].bt, "status-yellow")));
           else if (i % 7 == 4)
               series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(proc[i].bt, "status-purple")));
           else if (i % 7 == 5)
               series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(proc[i].bt, "status-cyan")));
           else if (i % 7 == 6)
               series1.getData().add(new XYChart.Data(distance, machine, new GanttChart.ExtraData(proc[i].bt, "status-grey")));
           distance+=proc[i].bt;

       }

       chart.getData().addAll(series1);
       chart.getStylesheets().add(FCFSArrivaltime.class.getResource("ganttchart.css").toExternalForm());

       Scene scenex  = new Scene(chart,620,350);
       stage.setScene(scenex);
       stage.show();
    }

    static void priorityScheduling(ProcessP proc[], int n) {
        // Sort processes by priority
        sort(proc,0, n);

        System.out.println( "Order in which processes gets executed \n");
        for (int i = 0; i < n; i++) {
            System.out.println(proc[i].pid + " ");
        }
        findavgTime(proc, n);
    }
    public static void mainP(int processes[], int n, int bt[], int p[])
    { ProcessP proc[]=new ProcessP[100];
        for(int i=0;i<n;i++){
            proc[i]=new ProcessP(i+1, bt[i],p[i]);
        }
//        Process proc[] = { new Process(1, 6, 1),
//                new Process(2, 8, 1),
//                new Process(3, 7, 2),
//                new Process(4, 3, 3)};

        priorityScheduling(proc,n);
    }
}

