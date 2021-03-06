package gas.money.calculator;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//author Austin Lee

public class GasMoneyCalculator extends Application {
    Stage window, errorWindow;
    Scene scene, scene2, errorScene;
    GridPane grid;
    GridPane grid2;
    Trip myTrip = new Trip(0,0,0,0);
    String amountOwed;
    TextField riderInput, distanceInput, mpgInput, gasPriceInput;
    DecimalFormat money = new DecimalFormat("$0.00");
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        Label total = new Label ("Each person owes: ");
        GridPane.setConstraints(total, 0, 13);
        
        Label priceLabel = new Label("");
        GridPane.setConstraints(priceLabel, 1, 13);
        
        Label riderLabel = new Label("Number of Riders:");
        GridPane.setConstraints(riderLabel, 0, 5);
        
        riderInput = new TextField();
        riderInput.setPromptText("Number of Riders");
        GridPane.setConstraints(riderInput, 1, 5);      
    
        Label distanceLabel = new Label("Distance of Trip: ");
        GridPane.setConstraints(distanceLabel, 0, 6);
        
        CheckBox roundTrip = new CheckBox("Round Trip?");
        GridPane.setConstraints(roundTrip, 2, 6);
        
        distanceInput = new TextField();
        distanceInput.setPromptText("Distance of trip");
        GridPane.setConstraints(distanceInput, 1, 6);
        
        Label carPrompt = new Label("Choose Car");
        GridPane.setConstraints(carPrompt, 0, 8);
        
        ChoiceBox<String> carBox = new ChoiceBox<>();
        carBox.getItems().add("2009 Hyundai Genesis");
        carBox.getItems().add("2014 Acura ILX");
        carBox.getItems().add("2012 Lexus GX460");
        carBox.getItems().add("2015 Toyota Prius");
        carBox.getItems().add("2015 Tesla Model S");
        GridPane.setConstraints(carBox, 1, 8);
        
        mpgInput = new TextField();
        mpgInput.setPromptText("Average MPG");
        GridPane.setConstraints(mpgInput, 1, 10);
        
        //Listen for drop down selection changes
        carBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            switch (newValue) {
                case "2014 Acura ILX":
                    mpgInput.setText("30");
                    break;
                case "2012 Lexus GX460":
                    mpgInput.setText("18");
                    break;
                case "2009 Hyundai Genesis":
                    mpgInput.setText("21");
                    break;
                case "2015 Toyota Prius":
                    mpgInput.setText("50");
                    break;
                case "2015 Tesla Model S":
                    mpgInput.setText("101");
                    break;
                default:
                    break;
            }
        });
        
        Label or = new Label("Or: ");
        GridPane.setConstraints(or, 0, 9);
        
        Label mpgLabel = new Label("Enter Average MPG: ");
        GridPane.setConstraints(mpgLabel, 0, 10);
        
        Label gasPriceLabel = new Label("Price of Gas: ");
        GridPane.setConstraints(gasPriceLabel, 0, 11);
        
        gasPriceInput = new TextField();
        gasPriceInput.setPromptText("Price of gas");
        GridPane.setConstraints(gasPriceInput, 1, 11);
        
        CheckBox driverBox = new CheckBox("Driver Contributing?");
        GridPane.setConstraints(driverBox, 1, 12);
            
        Button submitButton = new Button();
        submitButton.setText("Calculate");
        submitButton.disableProperty().bind(
        Bindings.isEmpty(riderInput.textProperty()).or(Bindings.isEmpty(mpgInput.textProperty()).or(Bindings.isEmpty(gasPriceInput.textProperty()).or(Bindings.isEmpty(distanceInput.textProperty())))));
        submitButton.setOnAction(e -> { 
            if (!isInt(riderInput, riderInput.getText()) || !isDouble(distanceInput, distanceInput.getText()) || !isDouble(mpgInput, mpgInput.getText()) || !isDouble(gasPriceInput, gasPriceInput.getText())){
                ConfirmBox.display("Error!", "You have entered invalid characters!");
            }
            myTrip = new Trip(Integer.parseInt(riderInput.getText()), Double.parseDouble(distanceInput.getText()), Double.parseDouble(mpgInput.getText()), Double.parseDouble(gasPriceInput.getText()));
            handleOptions(driverBox);
            roundTripHandler(roundTrip);
            amountOwed = money.format(myTrip.getPrice());
            priceLabel.setText(amountOwed);
        });
        GridPane.setConstraints(submitButton, 2, 13 );
        
        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        grid.getChildren().addAll(roundTrip, or, carPrompt, carBox, total, riderLabel, riderInput, distanceLabel, distanceInput, mpgLabel, mpgInput, gasPriceLabel,gasPriceInput, driverBox, submitButton, priceLabel);
        
        scene = new Scene(grid, 400, 350);
        
        window.setTitle("Gas Calculator");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private boolean isInt(TextField input, String message){
        try{
            int numRider = Integer.parseInt(input.getText());
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isDouble(TextField input, String message){
        try{
            double numRider = Double.parseDouble(input.getText());
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
    
    //handle driverIncluded checkbox operations
    private void handleOptions(CheckBox box1){
        if (box1.isSelected()){
            myTrip.incRider();
        }
    }
    
    //handle roundTrip checkbox operations
    private void roundTripHandler (CheckBox box1){
        if (box1.isSelected()){
            myTrip.roundTrip();
        }
    }
}
