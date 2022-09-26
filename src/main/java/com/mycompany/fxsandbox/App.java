package com.mycompany.fxsandbox;

import javafx.application.Application;

import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        WordlePane thePane = new WordlePane(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}