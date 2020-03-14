package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConfirmBox {
    static  boolean answer;
    public static boolean display(String title,String message){
        Stage Window= new Stage();
        Window.initModality(Modality.APPLICATION_MODAL);
        Window.setTitle(title);
        Window.setMinWidth(300);
        Label label=new Label();
        label.setText(message);
        Button button1 =new Button("yes");
        Button button2 =new Button("no");
        button1.setOnAction(e->{ answer=true;
            Window.close();

        });
        button2.setOnAction(e->{ answer=false;
            Window.close();

        });
        VBox layout =new VBox(20);
        layout.getChildren().addAll(label,button1,button2);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout);
        Window.setScene(scene);
        Window.showAndWait();


        return answer;

    }
}
