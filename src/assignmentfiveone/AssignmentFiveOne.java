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

/**
 *
 * @author rickyarora
 */
public class AssignmentFiveOne extends Application {
    static Label input;
    static Label quiz, courseCode,finalGrade,assignment,gradeInfo;
    static TextField quizInput, finalInput, courseInput, assignmentInput;
    static TextArea calculation;
    static ComboBox courseSelection;
    static double finalMark;
    //static double finalMark;
    @Override
    public void start(Stage primaryStage) {
       input = new Label("Input Information Here");
      quiz = new Label("Quiz Grade:  ");
       courseCode = new Label("Course Code: ");
       finalGrade = new Label("Final Grade: ");
       assignment = new Label("assignment grade: ");
       gradeInfo = new Label("View Grade Info: ");
      
       quizInput = new TextField();
       finalInput = new TextField();
       courseInput = new TextField();
       assignmentInput = new TextField();      
      
       calculation = new TextArea();
      
      Button submit = new Button("Submit");
      
      submit.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e){
            write();
      }
        
    });
      courseSelection = new ComboBox();

      courseSelection.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e){
            //calculation.setText(" ");
            read();
                
          
      }
        
    });
      
      
      GridPane topLayout = new GridPane();
      
      topLayout.setPadding(new Insets(10,10,10,10));
      topLayout.setVgap(20);
      
      topLayout.add(input, 2,0);
      topLayout.add(courseCode, 0, 1);
      topLayout.add(courseInput,1,1);
      topLayout.add(quiz,2,1);
      topLayout.add(quizInput,3,1);
      topLayout.add(assignment,0,2);
      topLayout.add(assignmentInput,1,2);
      topLayout.add(finalGrade,2,2);
      topLayout.add(finalInput,3,2);
      topLayout.add(submit,2,4);
      
      GridPane bottomLayout = new GridPane();
      bottomLayout.add(gradeInfo,2,0);
      bottomLayout.add(courseSelection, 1,1);
      bottomLayout.add(calculation, 2,1);
      
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
            FileWriter fw= new FileWriter("records.txt", true);
            //BufferedWriter bw = new BufferedWriter(fw);
            output = new PrintWriter(fw);
            
            String course = courseInput.getText();
            double qGrade = Double.parseDouble(quizInput.getText());
            double fGrade = Double.parseDouble(finalInput.getText());
          // double cCode = Double.parseDouble(courseInput.getText());
            double aGrade= Double.parseDouble(assignmentInput.getText());
            finalMark = qGrade + fGrade + aGrade;
            output.println(course +",quiz," +qGrade);
            output.println(course + ",final," +fGrade);
            output.println(course+",assignment,"+aGrade);
            finalMark = qGrade+fGrade+aGrade;
            
            if(!(courseSelection.getItems().contains(course))){
                courseSelection.getItems().add(course);
            }
            
            
            
            
            output.close();
        }
        catch (Exception e) {
            System.out.println();
        }
    }
    
    public static void read(){
        String curCourse = "";
        if(courseSelection.getSelectionModel().getSelectedItem()!=null){
        curCourse = courseSelection.getSelectionModel().getSelectedItem().toString();
        }
        
        BufferedReader input;
        
        try{
            FileReader f = new FileReader("records.txt");
            input = new BufferedReader(f);
            calculation.setText(" ");  // NEW LINE
            String s = input.readLine();
            double totalMark = 0;
            while(s!=null){
                String [] data = s.split(",");
                if(data[0].equals(curCourse)){
                    calculation.appendText(data[0] + "\t"+ data[1]+"\t" +data[2]+ "\n"); 
                  //  System.out.println(data[0] + " " + data[1] + "" + data[2]);
                  totalMark += Double.parseDouble(data[2]);
                }
                s = input.readLine();
            }
            calculation.appendText("\nFinal mark is: " + totalMark);
                

            
        }
        catch(Exception e){
            System.out.println("Error is: "+ e);
            
        }
       /// courseSelection.getSelectionModel().clearSelection();   
    }
}

    
