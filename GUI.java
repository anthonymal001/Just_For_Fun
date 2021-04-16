import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        
        

        // Create borderpane
        GridPane pane  = new GridPane();
        pane.setPadding(new Insets(15));
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setVgap(8);
        pane.setHgap(8);

        //Create Text for student info
        Text name = new Text("Name: ");
        name.setFont(new Font(15));

        Text age = new Text("Age: ");
        age.setFont(new Font(15));

        Text address = new Text("Address: ");
        address.setFont(new Font(15));

        Text phone = new Text("Phone: ");
        phone.setFont(new Font(15));

        //Create textfield for user input
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();

        //Create button
        Button submit = new Button("Submit");
        Button clear = new Button("Clear");
        Button close = new Button("Close");

        //Function of submit button
        submit.setOnAction(e ->{
            String names = tf1.getText();
            String ages = tf2.getText();
            String addresses = tf3.getText();
            String phones = tf4.getText();

            try {
                //Change the path to where you save your file  (Starting from before E:/Visual......)
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://E:/Visual Code Java/Java/GUI with Database/student_info.accdb");
                if(connection != null){
                    System.out.println("Connected to database");

                    //Create statement
                    Statement stat = connection.createStatement();

                    //stat.executeUpdate("Create table student(name varchar(40), age int, address varchar(150), phone int)");
                    stat.executeUpdate("Insert into student values ('"+names+"', '"+ages+"','"+addresses+"','"+phones+"')");
                    
                }
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        //Function of clear button
        clear.setOnAction(e -> {
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
        });

        //Function of close button
        close.setOnAction(e->{
            System.exit(0);
        });

        //Arrange all node in the grid
        pane.add(name, 0, 0);
        pane.add(tf1,1,0);
        pane.add(age,0,1);
        pane.add(tf2,1,1);
        pane.add(address,0,2);
        pane.add(tf3,1,2);
        pane.add(phone,0,3);
        pane.add(tf4,1,3);
        pane.add(submit,0,4);
        pane.add(clear,1,4);
        pane.add(close,0,5);

        //Scene
        Scene scene =new Scene(pane,250,230);
        stage.setTitle("Student Info");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}