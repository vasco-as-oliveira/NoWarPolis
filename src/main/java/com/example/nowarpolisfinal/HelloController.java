package com.example.nowarpolisfinal;

import com.NoWarPolis.City;
import com.NoWarPolis.City_Classes.*;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Abastecimento;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Crossing;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Semaforo;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Stop_Signal;
import com.NoWarPolis.Util.Data;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    private static int pos = 0;
    private static int textVisible = 0;

    public Group group;
    public AnchorPane root;
    public Button btn;
    public MenuItem users;
    public VBox writeUsers;
    public Group group1;
    public VBox addPanel;
    public Button returnToUsers;
    public Button submitUser;
    public TextField l1;
    public PasswordField l2;
    public DatePicker birth_Text;
    public TextField l4;
    public TextField l5;
    public MenuItem nodes;
    public Button button_Add;
    public GridPane gridPanel;
    public Button seeWeight;
    public VBox editNode;
    public GridPane node_gridpane;
    public AnchorPane addPois;
    public Text poisIn;
    City city = new City(2);

    @FXML
    public void pressButton_(ActionEvent event){
        writeUsers.getChildren().clear();
    }


    /**
     * Open the container to add users/poi
     */

    public void open_add(){
        setOnlyVisible();
        addPanel.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-insets: 20;" +
                "-fx-border-color: #020000;");

        addPanel.setVisible(true);


        FadeTransition trans = new FadeTransition(Duration.seconds(1), addPanel);
        trans.setFromValue(0);
        trans.setToValue(1.0);
        trans.play();
    }

    /**
     * In the container, rewrite the informations for addition
     * @param button
     * @param l1
     * @param l2
     * @param birth
     * @param l4
     * @param l5
     */

    public void setButtonText(String button, String l1, String l2, String birth, String l4, String l5){
        button_Add.setText(button);
        gridPanel.getChildren().get(0).setVisible(!l1.isEmpty());
        gridPanel.getChildren().get(5).setVisible(!l1.isEmpty());
        ((Text) gridPanel.getChildren().get(0)).setText(l1);
        gridPanel.getChildren().get(1).setVisible(!l2.isEmpty());
        gridPanel.getChildren().get(6).setVisible(!l2.isEmpty());
        ((Text) gridPanel.getChildren().get(1)).setText(l2);
        gridPanel.getChildren().get(2).setVisible(!birth.isEmpty());
        gridPanel.getChildren().get(7).setVisible(!birth.isEmpty());
        ((Text) gridPanel.getChildren().get(2)).setText(birth);
        gridPanel.getChildren().get(3).setVisible(!l4.isEmpty());
        gridPanel.getChildren().get(8).setVisible(!l4.isEmpty());
        ((Text) gridPanel.getChildren().get(3)).setText(l4);
        gridPanel.getChildren().get(4).setVisible(!l5.isEmpty());
        gridPanel.getChildren().get(9).setVisible(!l5.isEmpty());
        ((Text) gridPanel.getChildren().get(4)).setText(l5);
    }

    /**
     * Show all the users in a scrollPane, and removes if asked
     * @param event
     */

    @FXML
    public void pressButtonUsers(ActionEvent event){
        writeUsers.getChildren().clear();
        button_Add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pos = 1;
                open_add();
            }
        });
        setOnlyVisible(); btn.setVisible(true); group1.setVisible(true);
        setButtonText("Add User", "Username", "Password", "Birth", "ID", "Current Location");


        for(User user : city.getUsers().values()){
            addUserFx(user.getUsername(), user.getId());
        }
    }

    /**
     * Set everything invisible
     */

    public void setOnlyVisible(){
        for(Node node : root.getChildren()){

            node.setVisible(false);
        }
        editNode.setVisible(false);
    }

    /**
     * Get the child of a node by his textid
     * @param textId
     * @return
     */

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

    /**
     * Like the users, here we can see the graph and generate it
     * @param event
     * @throws FileNotFoundException
     */

    @FXML
    public void pressButtonGraph(ActionEvent event) throws FileNotFoundException {
        setOnlyVisible();
        group.setVisible(true);
        users.setVisible(true);
        seeWeight.setVisible(true);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                group.getChildren().removeIf(node -> !(node instanceof ScrollPane));
                try {
                    pressButtonGraph(actionEvent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        generateNodes();
        generateWays();


    }

    /**
     * Generates all the node parts
     * @throws FileNotFoundException
     */

    public void generateNodes() throws FileNotFoundException {
        for(Long key : city.cityNodes.keys()) {
            Random r = new Random();
            int radius = 10;

            double x = radius + r.nextDouble() * 750;
            double y = radius + r.nextDouble() * 450;
            Circle circle = new Circle(x, y, radius, Color.BLUE);
            circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    editNode.setVisible(!editNode.isVisible());

                }
            });
            circle.setId("" + city.getCityNodes().get(key).id);
            if (!city.cityNodes.get(key).pointsOfInteress.isEmpty()) {
                if(city.cityNodes.get(key).pointsOfInteress.get(0).name.equals("crossing")){
                FileInputStream inputStream = new FileInputStream("src/main/java/com/NoWarPolis/images/crossing.png");
                circle.setFill(new ImagePattern(new Image(inputStream)));
                circle.setId("");

                } else if (city.cityNodes.get(key).pointsOfInteress.get(0).name.equals("semaforo")) {
                    FileInputStream inputStream = new FileInputStream("src/main/java/com/NoWarPolis/images/sem.png");
                    circle.setFill(new ImagePattern(new Image(inputStream)));
                    circle.setId("");
                } else if (city.cityNodes.get(key).pointsOfInteress.get(0).name.equals("stop_sign")) {
                    FileInputStream inputStream = new FileInputStream("src/main/java/com/NoWarPolis/images/Stop_sign.png");
                    circle.setFill(new ImagePattern(new Image(inputStream)));
                    circle.setId("");
                } else if (city.cityNodes.get(key).pointsOfInteress.get(0).name.equals("abastecimento")){
                    FileInputStream inputStream = new FileInputStream("src/main/java/com/NoWarPolis/images/abastecimento.png");
                    circle.setFill(new ImagePattern(new Image(inputStream)));
                    circle.setId("");
                }
            }
            Text text = new Text("" + city.getCityNodes().get(key).id);
            text.setId(""+city.getCityNodes().get(key).idPos);
            text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(editNode.isVisible()){
                        editNode.setVisible(false);
                        ((TextField) node_gridpane.getChildren().get(1)).setText("");
                        poisIn.setText("");
                    } else {
                        editNode.setVisible(true);
                        ((Text) node_gridpane.getChildren().get(2)).setText("Get all Points Of Interest this node:");

                        ((Button) node_gridpane.getChildren().get(0)).onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                String typeOfPoi = ((TextField) node_gridpane.getChildren().get(1)).getCharacters().toString();
                                printAllPoisOfSpecificNode(Long.parseLong(text.getId()), typeOfPoi);
                            }
                        });



                    }
                }
            });
            text.setFill(Color.WHITE);
            StackPane stackPane = new StackPane();
            stackPane.setLayoutX(x-radius);
            stackPane.setLayoutY(y-radius);
            stackPane.getChildren().addAll(circle, text);

            group.getChildren().add(stackPane);
        }
    }

    /**
     * Prints all the pois in a specific node
     * @param num id of node
     * @param type type of poi
     */


    public void printAllPoisOfSpecificNode(long num, String type){
        poisIn.setText("");
        for(PoI poI : city.cityNodes.get(num).pointsOfInteress){
            System.out.println(type);
            switch (type) {
                case "abastecimento":
                    if (poI instanceof Abastecimento)
                        poisIn.setText(poisIn.getText() + "\n" + poI.toString());
                    break;
                case "crossing":
                    if (poI instanceof Crossing)
                        poisIn.setText(poisIn.getText() + "\n" + poI.toString());
                    break;
                case "stop_signal":
                    if (poI instanceof Stop_Signal)
                        poisIn.setText(poisIn.getText() + "\n" + poI.toString());
                    break;
                case "semaforo":
                    if (poI instanceof Semaforo)
                        poisIn.setText(poisIn.getText() + "\n" + poI.toString());
                    break;
            }
        }
    }

    /**
     * Generates all the ways, by lines, in the graph panel
     * @throws FileNotFoundException
     */

    public void generateWays() throws FileNotFoundException {


        for(Long key : city.getCityNodes().keys()) {
            System.out.println(city.cityNodes.get(key).id);
        }

        int i=0;
        for(Way way : city.ways){
            //System.out.println(way.getTo().id + " " + way.getFrom().id);


            StackPane stackPane1 = (StackPane) group.getChildren().get(way.getFrom().id+1);
            Circle circle1 = (Circle) stackPane1.getChildren().get(0);

            StackPane stackPane2 = (StackPane) group.getChildren().get(way.getTo().id+1);
            Circle circle2 = (Circle) stackPane2.getChildren().get(0);

            Circle arrow;
            Line line = new Line(circle1.getCenterX(), circle1.getCenterY(), circle2.getCenterX(), circle2.getCenterY());
            Text text = new Text(((circle1.getCenterX() + circle2.getCenterX())/2), ((circle1.getCenterY() + circle2.getCenterY())/2), (way.getDistance().floatValue() + ""));
            text.setVisible(false);
            text.setId("1");
            if(way instanceof Highway){
                if(((Highway) way).isOneWay()){
                    System.out.println("entedere");
                    Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.RED)};
                    LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
                    line.setStroke(lg1);
                }
            }
            if(way instanceof Park){
                line.setStroke(Color.GREEN);
            }
            group.getChildren().addAll(line, text);
            i++;
        }
    }

    /**
     * Gets an angle
     * @param y1
     * @param y2
     * @param x1
     * @param x2
     * @return
     */

    public double getAngle(double y1, double y2, double x1, double x2) {
        double angle = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * When in panel to add user/poi, returns to the main panel where are the users/pois
     * @param actionEvent
     * @throws InterruptedException
     */

    public void returnToUsers(ActionEvent actionEvent) throws InterruptedException {
        FadeTransition trans = new FadeTransition(Duration.seconds(1), addPanel);
        trans.setFromValue(1);
        trans.setToValue(0);
        trans.play();
        if(pos == 1) pressButtonUsers(actionEvent);
        if(pos == 2) pressButtonNodes(actionEvent);

        /*setOnlyVisible();
        group1.setVisible(true);
        users.setVisible(true);*/

        System.out.println(city.getCityNodes().contains(128560443L));

    }

    /**
     * Chooses to submit between users or pois
     * @param actionEvent
     * @throws InterruptedException
     */

    public void submit(ActionEvent actionEvent) throws InterruptedException {
        if(pos == 1) sumbitUsers(actionEvent);
        if(pos == 2) submitPoI(actionEvent);
    }


    /**
     * Submition of the users with all the conditions it has
     * @param actionEvent
     * @throws InterruptedException
     */

    public void sumbitUsers(ActionEvent actionEvent) throws InterruptedException {
        boolean flag=true;

        if(l1.getCharacters().toString().isEmpty()) {
            l1.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            l1.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            flag=false;
        } else {
            l1.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        if(l2.getCharacters().toString().isEmpty()) {
            l2.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            flag=false;
        }else {
            l2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        if(birth_Text.getValue()==null) {
            birth_Text.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            flag=false;
        }else {
            birth_Text.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        }

        try{Integer.parseInt(l4.getCharacters().toString());} catch (Exception e){
            System.out.println("NOT A NUMBER");
            l4.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            flag=false;
            return;
        }

        if(l4.getCharacters().toString().isEmpty()) {
            flag=false;
        }else {
            l4.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        if(l5.getCharacters().toString().isEmpty()) {
            l5.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            flag=false;

        } else {
            try{Long.parseLong(l5.getCharacters().toString());} catch (Exception e){
                System.out.println("NOT A NUMBER");
                l5.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
                flag=false;
                return;
            }
            if (!city.cityNodes.contains(Long.parseLong(l5.getCharacters().toString()))) {
                l5.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
                flag=false;
            }else {
                l5.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            }
        }

        if(flag) {
            String user = l1.getCharacters().toString();
            String password = l2.getCharacters().toString();
            Data data = new Data(birth_Text.getValue().getDayOfMonth(), birth_Text.getValue().getMonthValue(), birth_Text.getValue().getYear());
            int id = Integer.parseInt(l4.getCharacters().toString());
            long cl = Long.parseLong(l5.getCharacters().toString());
            User user1 = new User(user, password, data, id, null, city.cityNodes.get(cl));
            System.out.println(user1);
            city.addUser(user1);
            addUserFx(user, id);
            l1.setText("");
            l2.setText("");
            birth_Text.getEditor().setText("");
            l4.setText("");
            l5.setText("");
            returnToUsers(actionEvent);

        }
    }


    /**
     * Submition of the pois with all the conditions it has
     * @param actionEvent
     * @throws InterruptedException
     */

    public void submitPoI(ActionEvent actionEvent) throws InterruptedException {
        boolean flag=true;

        if(l1.getCharacters().toString().isEmpty()) {
            l1.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            l1.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            flag=false;
        } else {
            l1.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        /*if(l2.getCharacters().toString().isEmpty()) {
            l2.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            flag=false;
        }else {
            l2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        if(birth_Text.getValue()==null) {
            birth_Text.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            flag=false;
        }else {
            birth_Text.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        }*/

        try{Integer.parseInt(l4.getCharacters().toString());} catch (Exception e){
            System.out.println("NOT A NUMBER");
            l4.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            flag=false;
            return;
        }

        if(l4.getCharacters().toString().isEmpty()) {
            flag=false;
        }else {
            l4.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
        if(l5.getCharacters().toString().isEmpty()) {
            l5.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
            flag=false;

        } else {
            try{Long.parseLong(l5.getCharacters().toString());} catch (Exception e){
                System.out.println("NOT A NUMBER");
                l5.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
                flag=false;
                return;
            }
            if (!city.cityNodes.contains(Long.parseLong(l5.getCharacters().toString()))) {
                l5.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
                flag=false;
            }else {
                l5.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            }
        }

        if(flag) {
            String poiname = l1.getCharacters().toString();
            //String password = l2.getCharacters().toString();
            //Data data = new Data(birth_Text.getValue().getDayOfMonth(), birth_Text.getValue().getMonthValue(), birth_Text.getValue().getYear());
            int id = Integer.parseInt(l4.getCharacters().toString());
            long cl = Long.parseLong(l5.getCharacters().toString());

            if(l1.getCharacters().toString().toLowerCase().contains("semaforo")) {
                Semaforo semaforo = new Semaforo(city.getCityNodes().get(cl), id);
                /*for(PoI poi : city.getPointsOfInterest()){
                    if(poi instanceof Semaforo){
                        if(semaforo.location.equals(poi.location)){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Already exists");
                            alert.show();
                            city.removePointOfInterest(poi);
                        }
                    }
                }*/
                try {
                    city.addEtiqueta_Node(semaforo);
                } catch (Exception e) {
                    returnToUsers(actionEvent);
                    e.printStackTrace();
                    return;
                }
            } else if(l1.getCharacters().toString().toLowerCase().contains("abastecimento")) {
                Abastecimento abastecimento = new Abastecimento(city.getCityNodes().get(cl), id);
                for(PoI poi : city.getPointsOfInterest()){
                    if(poi instanceof Abastecimento){
                        if(abastecimento.location.equals(poi.location)){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Already exists");
                            alert.show();
                            city.removePointOfInterest(poi);
                        }
                    }
                }
                try {
                    city.addEtiqueta_Node(abastecimento);
                } catch (Exception e) {
                    returnToUsers(actionEvent);
                    e.printStackTrace();
                    return;
                }
            } else if(l1.getCharacters().toString().toLowerCase().contains("crossing")) {
                Crossing crossing = new Crossing(city.getCityNodes().get(cl), id);
                for(PoI poi : city.getPointsOfInterest()){
                    if(poi instanceof Crossing){
                        if(crossing.location.equals(poi.location)){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Already exists");
                            alert.show();
                            city.removePointOfInterest(poi);
                        }
                    }
                }
                try {
                    city.addEtiqueta_Node(crossing);
                } catch (Exception e) {
                    returnToUsers(actionEvent);
                    e.printStackTrace();
                    return;
                }
            } else if(l1.getCharacters().toString().toLowerCase().contains("stop_signal")) {
                Stop_Signal stop_signal = new Stop_Signal(city.getCityNodes().get(cl), id);
                for(PoI poi : city.getPointsOfInterest()){
                    if(poi instanceof Stop_Signal){
                        if(stop_signal.location.equals(poi.location)){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Already exists");
                            alert.show();
                            city.removePointOfInterest(poi);
                        }
                    }
                }
                try {
                    city.addEtiqueta_Node(stop_signal);
                } catch (Exception e) {
                    returnToUsers(actionEvent);
                    e.printStackTrace();
                    return;
                }
            }

            addPoIFx(city.searchPoIByID(id), id);
            l1.setText("");
            l2.setText("");
            birth_Text.getEditor().setText("");
            l4.setText("");
            l5.setText("");
            returnToUsers(actionEvent);

        }
    }


    /**
     * add user in the panel on javafx
     * @param user
     * @param id
     */


    public void addUserFx(String user, int id){
        FlowPane flowPane = new FlowPane();
        Text text = new Text(user);
        text.setId("" + id);
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

    /**
     * add poi in the panel on javafx
     * @param poI
     * @param id
     */

    public void addPoIFx(PoI poI, int id){
        FlowPane flowPane = new FlowPane();
        Text text = new Text(poI.name + ":" + poI.location.idPos);
        text.setId("" + poI.PoiId);
        Button button = new Button("Remove point Of Interest");
        button.setVisible(false);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        text.setOnMouseClicked(mouseEvent -> {
            if(button.visibleProperty().get()) button.setVisible(false);
            else button.setVisible(true);
            button.setOnMouseClicked(mouseEvent1 -> {
                try {
                    city.removePointOfInterest(city.searchPoIByID(Integer.parseInt(text.getId())));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                flowPane.getChildren().clear();

                System.out.println(city.PointsOfInterest);
                //pressButtonUsers(event);
            });
        });
        flowPane.getChildren().addAll(text, button);
        writeUsers.getChildren().addAll(flowPane);
    }

    /**
     * When button is pressed, shows all pois and option for removal
     * @param actionEvent
     */

    public void pressButtonNodes(ActionEvent actionEvent) {
        writeUsers.getChildren().clear();
        button_Add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pos = 2;
                open_add();
            }
        });
        setOnlyVisible(); btn.setVisible(true); group1.setVisible(true);
        setButtonText("Add PointOfInterest", "Type", "", "", "ID", "Position");

        setOnlyVisible();
        btn.setVisible(true);
        group1.setVisible(true);


        for(PoI poI : city.getPointsOfInterest()){
            addPoIFx(poI, poI.PoiId);
        }
    }

    /**
     * Button to save the project
     * @param actionEvent
     */

    public void saveproject(ActionEvent actionEvent) {
        city.updateFile(2);

        /*for(Long key : city.cityNodes.keys()){
            System.out.println(city.cityNodes.get(key).idPos + ": " + city.cityNodes.get(key).pointsOfInteress);
        }*/
        System.out.println(city.PointsOfInterest);

    }

    /**
     * when clicked, it shows all the weights in the graph
     * @param actionEvent
     */

    public void seeWeight(ActionEvent actionEvent) {
        if(textVisible == 0){
            for(Node node : group.getChildren()){
                if(node instanceof Text){
                    node.setVisible(true);
                }
            }
            textVisible = 1;
        } else if(textVisible == 1){
            for(Node node : group.getChildren()){
                if(node instanceof Text){
                    node.setVisible(false);
                }
            }
            textVisible = 0;
        }

    }
}