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
/**
 *
 * @author rickyarora
 */
public class AssignmentFiveOne extends Application {
    static Label input;
    static Label IMDBRating, movie,RTRating,FDRating,movieInfo;
    static TextField IMDBInput, RTInput, movieInput, FDInput;
    static TextArea calculation;
    static ComboBox movieSelection;
    static ComboBox movieCategory;
    static double finalRating;
    //static double finalMark;
    @Override
    public void start(Stage primaryStage) {
       input = new Label("Input Info in Decimal Form and out of 10.");
       IMDBRating = new Label("IMDB rating:  ");
       movie = new Label("Movie Name: ");
       RTRating = new Label("Rotten Tomatoes: ");
       FDRating = new Label("Fan Dango: ");
       movieInfo = new Label("View Movie Information: ");
      
       // Decemial format
       
       IMDBInput = new TextField();
       RTInput = new TextField();
       movieInput = new TextField();
       FDInput = new TextField();      
      
       calculation = new TextArea();
      //This is the main button to add new movies
      Button addNew = new Button("Add New");
      //This will edit existing data using CREATE, i think
      Button edit = new Button("Edit");
      //Save Edited info
      Button save = new Button("Save");
      //Delete the data
      Button delete = new Button("Delete");
      // Exit the program
      Button exit = new Button("Exit");
      
      addNew.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e){
            write();
      }
        
    });
      //ComboBox that shows the movie info
      movieSelection = new ComboBox();
      //ComboBox that places the movies into categories
      movieCategory = new ComboBox();

      movieSelection.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e){
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
      
      topLayout.setPadding(new Insets(10,10,10,10));
      topLayout.setVgap(20);
      
      topLayout.add(input, 2,0);
      topLayout.add(movie, 0, 1);
      topLayout.add(movieInput,1,1);
      topLayout.add(IMDBRating,2,1);
      topLayout.add(IMDBInput,3,1);
      topLayout.add(FDRating,0,2);
      topLayout.add(FDInput,1,2);
      topLayout.add(RTRating,2,2);
      topLayout.add(RTInput,3,2);
      topLayout.add(addNew,2,4);
      
      GridPane bottomLayout = new GridPane();
      bottomLayout.add(movieInfo,2,0);
      bottomLayout.add(movieSelection, 1,1);
      bottomLayout.add(calculation, 2,1);
      bottomLayout.add(exit, 3,0);
      
      BorderPane bp = new BorderPane();
      
      bp.setTop(topLayout);
      bp.setBottom(bottomLayout);
      Scene s = new Scene(bp, 700, 400);
      
      primaryStage.setScene(s);
      primaryStage.show();
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void write(){
        
        PrintWriter output;
        
        try {
            FileWriter fw= new FileWriter("movieDatabase.txt", true);
            //BufferedWriter bw = new BufferedWriter(fw);
            output = new PrintWriter(fw);
            
            String movie = movieInput.getText();
            double imdbRate = Double.parseDouble(IMDBInput.getText());
            double rtRate = Double.parseDouble(RTInput.getText());
          // double cCode = Double.parseDouble(courseInput.getText());
            double fdRate= Double.parseDouble(FDInput.getText());
            finalRating = imdbRate + rtRate + fdRate;
            output.println(movie +",imdb," +imdbRate);
            output.println(movie + ",Rotten Tomatoes," +rtRate);
            output.println(movie+",Fan Dango,"+fdRate);
            finalRating = imdbRate+rtRate+fdRate;
            
            if(!(movieSelection.getItems().contains(movie))){
                movieSelection.getItems().add(movie);
            } 
            
            
            
            
            output.close();
        }
        catch (Exception e) {
            System.out.println();
        }
    }
    
    public static void read(){
        String curCourse = "";
        if(movieSelection.getSelectionModel().getSelectedItem()!=null){
        curCourse = movieSelection.getSelectionModel().getSelectedItem().toString();
        }
        
        BufferedReader input;
        
        try{
            FileReader f = new FileReader("movieDatabase.txt");
            input = new BufferedReader(f);
            calculation.setText(" ");  // NEW LINE
            String s = input.readLine();
            double totalRating = 0;
            while(s!=null){
                String [] data = s.split(",");
                if(data[0].equals(curCourse)){
                    calculation.appendText(data[0] + "\t"+ data[1]+"\t" +data[2]+ "\n"); 
                    System.out.println(data[0] + " " + data[1] + "" + data[2]);
                  totalRating += Double.parseDouble(data[2]);
                }
                s = input.readLine();
            }
            //There should only be 3 data at a time.
            totalRating = totalRating/3;
            DecimalFormat df = new DecimalFormat(".##"); 
            calculation.appendText("\nAverage Rating is: " +  df.format(totalRating));
                

            
        }
        catch(Exception e){
            System.out.println("Error is: "+ e);
            
        }
       /// courseSelection.getSelectionModel().clearSelection();   
    }
}

    
