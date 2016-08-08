
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentfiveone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author rickyarora
 */
public class AssignmentFiveOne extends Application {

    static Label input;
    static Label IMDBRating, movie, RTRating, FDRating, movieInfo;
    static TextField IMDBInput, RTInput, movieInput, FDInput;
    static TextArea calculation;
    static ComboBox movieSelection;
    static ComboBox movieCategory;
    static double finalRating;
    static ArrayList<String> a1 = new <String> ArrayList();

    //static double finalMark;
    @Override
    public void start(Stage primaryStage) {
        input = new Label("Input Info in Decimal Form and out of 10.");
        IMDBRating = new Label("IMDB rating:  ");
        movie = new Label("Movie Name: ");
        RTRating = new Label("Rotten Tomatoes: ");
        FDRating = new Label("Fan Dango: ");
        movieInfo = new Label("View Movie Information: ");

        input.getStyleClass().add("outline");
        input.setMinWidth(Region.USE_PREF_SIZE);
        IMDBRating.getStyleClass().add("outline2");
        
        IMDBRating.setMinWidth(Region.USE_PREF_SIZE);
        movie.getStyleClass().add("outline3");
        movie.setMinWidth(Region.USE_PREF_SIZE);
        RTRating.getStyleClass().add("outline4");
        RTRating.setMinWidth(Region.USE_PREF_SIZE);
        FDRating.getStyleClass().add("outline5");
        FDRating.setMinWidth(Region.USE_PREF_SIZE);
        movieInfo.getStyleClass().add("outline6");
        movieInfo.setMinWidth(Region.USE_PREF_SIZE);
        // Decimal format

        IMDBInput = new TextField();
        //IMDBInput.setMinWidth(200);
        IMDBInput.setMaxWidth(250);
        RTInput = new TextField();
        //RTInput.setMinWidth(200);
        RTInput.setMaxWidth(250);
        movieInput = new TextField();
        //movieInput.setMinWidth(300);
        movieInput.setMaxWidth(250);
        FDInput = new TextField();
        //FDInput.setMinWidth(300);
        FDInput.setMaxWidth(250);

        calculation = new TextArea();
        //This is the main button to add new movies
        Button addNew = new Button("Add New");
        addNew.getStyleClass().add("btnoutline1");
        //This will move forward in the array
        Button next = new Button("Next");
        next.getStyleClass().add("btnoutline2");
        //This will move back in the array
        Button previous = new Button("Previous");
        previous.getStyleClass().add("btnoutline3");
        //Delete the data
        Button delete = new Button("Delete");
        delete.getStyleClass().add("btnoutline4");
        // Exit the program
        Button exit = new Button("Exit");
        exit.getStyleClass().add("btnoutline5");

        String s = getClass().getResource("Style.css").toExternalForm();

        addNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                write();
            }

        });
        //ComboBox that shows the movie info
        movieSelection = new ComboBox();
        //ComboBox that places the movies into categories
        movieCategory = new ComboBox();

        movieSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //calculation.setText(" ");
                read();

            }

        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        GridPane topLayout = new GridPane();

        topLayout.setPadding(new Insets(10, 10, 10, 10));
        topLayout.setMinSize(900, 100);
        topLayout.setVgap(20);
        topLayout.setHgap(1);
        //topLayout.setMaxSize(900, 100);

        topLayout.add(input, 1, 2);
        topLayout.add(movie, 0, 4);
        topLayout.add(movieInput, 1, 4);
        topLayout.add(IMDBRating, 2, 4);
        //topLayout.setConstraints(IMDBRating, 2, 4);
        //topLayout.setAlignment(IMDBRating, Pos.);
        topLayout.add(IMDBInput, 3, 4);
        //topLayout.setHalignment(IMDBInput, HPos.LEFT);
        topLayout.add(FDRating, 0, 5);
        //topLayout.setHalignment(RTRating, HPos.LEFT);
        topLayout.add(FDInput, 1, 5);
        //topLayout.setHalignment(RTInput, HPos.LEFT);
        topLayout.add(RTRating, 2, 5);
        topLayout.add(RTInput, 3, 5);
        
        GridPane centerLayout = new GridPane();
        centerLayout.setVgap(5);
        centerLayout.setHgap(5);
        centerLayout.add(addNew, 20, 1);
       // topLayout.setHalignment(addNew, HPos.LEFT);
        centerLayout.add(next, 40, 5);
        //topLayout.setHalignment(next, HPos.RIGHT);
        centerLayout.add(previous, 70, 5);
        //topLayout.setHalignment(previous, HPos.LEFT);
        centerLayout.add(delete, 90, 1);
        //topLayout.setHalignment(delete, HPos.RIGHT);
        

        GridPane bottomLayout = new GridPane();
        bottomLayout.add(movieInfo, 2, 0);
        bottomLayout.add(movieSelection, 1, 1);
        bottomLayout.add(calculation, 2, 1);
        bottomLayout.add(exit, 3, 1);
        BorderPane bp = new BorderPane();

        bp.setTop(topLayout);
        bp.setBottom(bottomLayout);
        bp.setCenter(centerLayout);
        Scene scene = new Scene(bp, 900, 500);

        primaryStage.setScene(scene);
        scene.getStylesheets().add(s);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void write() {

        PrintWriter output;

        try {
            FileWriter fw = new FileWriter("movieDatabase.txt", true);
            //BufferedWriter bw = new BufferedWriter(fw);
            output = new PrintWriter(fw);

            String movie = movieInput.getText();
            if ((movieSelection.getItems().contains(movie))) {
                JOptionPane.showMessageDialog(null, "Movie already Exist");
            } else {
                double imdbRate = Double.parseDouble(IMDBInput.getText());
                double rtRate = Double.parseDouble(RTInput.getText());
                // double cCode = Double.parseDouble(courseInput.getText());
                double fdRate = Double.parseDouble(FDInput.getText());
                finalRating = imdbRate + rtRate + fdRate;
                output.println(movie + ",imdb," + imdbRate);
                output.println(movie + ",Rotten Tomatoes," + rtRate);
                output.println(movie + ",Fan Dango," + fdRate);
                finalRating = imdbRate + rtRate + fdRate;

                if (!(movieSelection.getItems().contains(movie))) {
                    movieSelection.getItems().add(movie);
                }

                output.close();
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    public static void read() {
        String curCourse = "";
        if (movieSelection.getSelectionModel().getSelectedItem() != null) {
            curCourse = movieSelection.getSelectionModel().getSelectedItem().toString();
        }

        BufferedReader input;

        try {
            FileReader f = new FileReader("movieDatabase.txt");
            input = new BufferedReader(f);
            calculation.setText(" ");  // NEW LINE
            String s = input.readLine();
            double totalRating = 0;
            while (s != null) {
                String[] data = s.split(",");
                if (data[0].equals(curCourse)) {
                    calculation.appendText(data[0] + "\t" + data[1] + "\t" + data[2] + "\n");
                    System.out.println(data[0] + " " + data[1] + "" + data[2]);
                    String x = data[0] + " " + data[1] + "" + data[2];
                    a1.add(x);
                    totalRating += Double.parseDouble(data[2]);
                }
                s = input.readLine();
            }
            //There should only be 3 data at a time.
            totalRating = totalRating / 3;
            DecimalFormat df = new DecimalFormat(".##");
            calculation.appendText("\nAverage Rating is: " + df.format(totalRating));

        } catch (Exception e) {
            System.out.println("Error is: " + e);
        }
        /// courseSelection.getSelectionModel().clearSelection();   
    }
}
