package com.example.demo1.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane sp_main;

    @FXML
    private TextField tf_message;

    @FXML
    private Button button_send;

    @FXML
    private VBox vBoxMessages;
    private TCP_Connection_Chat connection2;

    @FXML
    void initialize() {
        try {
            connection2 = new TCP_Connection_Chat(new Socket("localhost", 8189));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     vBoxMessages.heightProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            sp_main.setVvalue((Double) newValue);
        }
    });

            connection2.receiveMessageFromServer(vBoxMessages);
        tf_message.setOnAction(actionEvent -> {
                String messageToSend = tf_message.getText();
                if (!messageToSend.isBlank()) {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5, 5, 5, 10));

                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);

                    textFlow.setStyle(
                            "-fx-color: rgb(239, 242, 255);" +
                                    "-fx-background-color: rgb(15, 125, 242);" +
                                    "-fx-background-radius: 20px;" +
                                    "-fx-font-family: Arial;" + "-fx-font-size: 14px;");

                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.934, 0.925, 0.996));

                    hBox.getChildren().add(textFlow);
                    vBoxMessages.getChildren().add(hBox);

                    connection2.sendMessageToServer(messageToSend);
                    tf_message.clear();
        }});

            button_send.setOnAction(actionEvent -> {
            String messageToSend = tf_message.getText();
            if (!messageToSend.isBlank()) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                Text text = new Text(messageToSend);
                TextFlow textFlow = new TextFlow(text);

                textFlow.setStyle(
                        "-fx-color: rgb(239, 242, 255);" +
                                "-fx-background-color: rgb(15, 125, 242);" +
                                "-fx-background-radius: 20px;" +
                        "-fx-font-family: Arial;" +
                                "-fx-font-size: 14px;");

                textFlow.setPadding(new Insets(5, 10, 5, 10));
                text.setFill(Color.color(0.934, 0.925, 0.996));

                hBox.getChildren().add(textFlow);
                vBoxMessages.getChildren().add(hBox);
                connection2.sendMessageToServer(messageToSend);
                tf_message.clear();
            }
    });}
    public static void constructor (String message, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle(
                "-fx-background-color: rgb(233, 233, 235);" +
                        "-fx-background-radius: 20px;" +
                        "-fx-font-family: Arial;" +
                        "-fx-font-size: 14px;");

        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }
}