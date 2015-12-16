/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gas.money.calculator;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 *
 * @author ahlin_000
 */
public class ConfirmBox {
    
    static String answer;
    
    public static void display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        Label label = new Label();
        label.setText(message);
        
        //Create two buttons
        Button okButton = new Button("OK");
        
        //Functionality of buttons
        okButton.setOnAction(e -> {
            window.close();
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
