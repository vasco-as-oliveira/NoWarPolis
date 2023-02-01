package com.example.nowarpolisfinal;

import com.NoWarPolis.City;
import com.NoWarPolis.City_Classes.User;
import com.NoWarPolis.City_Classes.Way;
import edu.princeton.cs.algs4.Point2D;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.util.EventListener;
import java.util.Random;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    public Group group;
    public StackPane root;
    public Button btn;
    public Button users;
    public VBox writeUsers;
    public Group group1;
    City city = new City(2);

    @FXML
    public void pressButtonUsers(ActionEvent event){
        users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                group.setVisible(false);
                group1.setVisible(true);
                users.setVisible(false);
                btn.setVisible(true);
            }
        });

        group.setVisible(false);
        group1.setVisible(true);
        users.setVisible(false);
        btn.setVisible(true);
        for(User user : city.getUsers().values()){
            FlowPane flowPane = new FlowPane();
            Text text = new Text(user.getUsername());
            text.setId("" + user.getId());
            Button button = new Button("Remove user");
            button.setVisible(false);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
            text.setOnMouseClicked(mouseEvent -> {
                if(button.visibleProperty().get()) button.setVisible(false);
                else button.setVisible(true);
                button.setOnMouseClicked(mouseEvent1 -> {
                    city.removeUser(Integer.parseInt(text.getId()));
                    flowPane.getChildren().clear();
                    //pressButtonUsers(event);
                });
            });
            flowPane.getChildren().addAll(text, button);
            writeUsers.getChildren().addAll(flowPane);
        }

       /* users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                group.setVisible(false);
                group1.setVisible(true);
                users.setVisible(false);
                btn.setVisible(true);
            }
        });*/
    }

    public int getChildById(String textId){
        int i=0;
        for(Node node : group1.getChildren()){
            if(node.getId().equals(textId)){
                return i;
            }
            i++;
        }
        return 0;
    }


    @FXML
    public void pressButtonGraph(ActionEvent event){

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                group.setVisible(true);
                group1.setVisible(false);
                users.setVisible(true);
                btn.setVisible(false);
            }
        });

        group.setVisible(true);
        group1.setVisible(false);
        generateNodes();
        generateWays();
        btn.setVisible(false);
        users.setVisible(true);

    }

    public void generateNodes(){


        for(Long key : city.cityNodes.keys()){
            Random r = new Random();
            int radius = 10;

            double x = radius + r.nextDouble() * 800;
            double y = radius + r.nextDouble() * 500;


            Circle circle = new Circle(x, y , radius, Color.BLUE);
            circle.setId("" + city.getCityNodes().get(key).id);

            Text text = new Text("" + city.getCityNodes().get(key).id);
            text.setFill(Color.WHITE);


            StackPane stackPane = new StackPane();
            stackPane.setLayoutX(x-radius);
            stackPane.setLayoutY(y-radius);
            stackPane.getChildren().addAll(circle, text);

            group.getChildren().add(stackPane);

        }
    }

    public void generateWays(){

        int i=0;
        for(Way way : city.ways){
            System.out.println(way.getTo().id + " " + way.getFrom().id);
            StackPane stackPane1 = (StackPane) group.getChildren().get(way.getFrom().id+1);
            Circle circle1 = (Circle) stackPane1.getChildren().get(0);
            StackPane stackPane2 = (StackPane) group.getChildren().get(way.getTo().id+1);
            Circle circle2 = (Circle) stackPane2.getChildren().get(0);

            Line line = new Line(circle1.getCenterX(), circle1.getCenterY(), circle2.getCenterX(), circle2.getCenterY());

            group.getChildren().add(line);
            i++;

        }

    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}