package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alertbox {
    public static void display(String title,String message){
        Stage Window= new Stage();
        Window.initModality(Modality.APPLICATION_MODAL);
        Window.setTitle(title);
        Window.setMinWidth(300);
        Label label=new Label();
        label.setText(message);
        Button button =new Button("close");
        button.setOnAction(e->Window.close());
        VBox layout =new VBox(10);
        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout);
        Window.setScene(scene);
        Window.showAndWait();



    }
}
