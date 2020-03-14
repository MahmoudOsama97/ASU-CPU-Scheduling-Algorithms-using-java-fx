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

public class FCFSArrivaltime{


    // Function to find the waiting time for all
// processes
    static void findWaitingTime(int processes[], int n, int bt[], int wt[], int at[])
    {
        int service_time[] = new int[n];
        service_time[0] = 0;
        wt[0] = 0;

        // calculating waiting time
        for (int i = 1; i < n ; i++)
        {
            // Add burst time of previous processes
            service_time[i] = service_time[i-1] + bt[i-1];

            // Find waiting time for current process =
            // sum - at[i]
            wt[i] = service_time[i] - at[i];

            // If waiting time for a process is in negative
            // that means it is already in the ready queue
            // before CPU becomes idle so its waiting time is 0
            if (wt[i] < 0)
                wt[i] = 0;
        }
    }

    // Function to calculate turn around time
    static void findTurnAroundTime(int processes[], int n, int bt[],
                                   int wt[], int tat[])
    {
        // Calculating turnaround time by adding bt[i] + wt[i]
        for (int i = 0; i < n ; i++)
            tat[i] = bt[i] + wt[i];
    }

    // Function to calculate average waiting and turn-around
// times.
    public static void findavgTime(int processes[], int n, int bt[], int at[])
    {
        int wt[] = new int[n], tat[] = new int[n];

        // Function to find waiting time of all processes
        findWaitingTime(processes, n, bt, wt, at);

        // Function to find turn around time for all processes
        findTurnAroundTime(processes, n, bt, wt, tat);

        // Display processes along with all details
        int total_wt = 0, total_tat = 0;
        for (int i = 0 ; i < n ; i++)
        {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            int compl_time = tat[i] + at[i];
            System.out.println(i+1 + "\t\t" + bt[i] + "\t\t"
                    + at[i] + "\t\t" + wt[i] + "\t\t "
                    + tat[i] + "\t\t " + compl_time);
        }

        System.out.print("Average waiting time = "
                + (float)total_wt / (float)n);
        System.out.print("\nAverage turn around time = "
                + (float)total_tat / (float)n);


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
        Scene scene=new Scene(grid,200,200);
        Stage Window2=new Stage();
        Window2.setScene(scene);
        TF5.setOnAction(e->{
            Window2.close();});
        Window2.show();



    }

// Driver code

    ///public static void main(String args[]) {
        // Process id's
//        int processes[] = {1, 2, 3};
//        int n = processes.length;
//
//        // Burst time of all processes
//        int burst_time[] = {5, 9, 6};
//
//        // Arrival time of all processes
//        int arrival_time[] = {0, 3, 6};

        //findavgTime(processes, n, burst_time, arrival_time);

   // }
}

