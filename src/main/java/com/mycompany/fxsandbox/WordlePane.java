package com.mycompany.fxsandbox;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WordlePane {

    private TextField entryField;
    private Button entryButton;
    private int counter = 1;
    private String guess;
    private String answer = "potato";
    private TextArea[][] areaArray = new TextArea[6][6];
    private GridPane mainPane;

    public WordlePane(Stage menuStage) {//set and organize buttons, images, and titles
        mainPane = new GridPane();
        Scene mainScene = mainScene = new Scene(mainPane, 650, 700);

        entryField = new TextField();
        entryField.setPromptText("Enter guess");

        entryButton = new Button("Submit guess");

        mainPane.add(entryField, 2, 0);
        mainPane.add(entryButton, 3, 0);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < answer.length(); j++) {
                areaArray[i][j] = new TextArea();
                areaArray[i][j].setEditable(false);
                mainPane.add(areaArray[i][j], j, i + 2);
            }
        }

        menuStage.setResizable(true);
        menuStage.setTitle("Wordle!");
        menuStage.setScene(mainScene);
        menuStage.show();

        entryButton.setOnAction(this::processButton);
    }

    public boolean wordCorrect(String guess, String answer) {
        if (answer.length() == guess.length()) {
            if (guess.equals(answer)) {
                entryField.setText(null);
                entryField.setPromptText("Correct!");
                for (int i = 0; i < answer.length(); i++) {
                    areaArray[counter - 1][i].setText(guess.substring(i, i + 1));
                    areaArray[counter - 1][i].setStyle("-fx-text-fill: green; -fx-font-size: 30px;");
                }
                entryField.setEditable(false);
                return true;
            } else {
                for (int i = 0; i < answer.length(); i++) {
                    if (answer.contains(guess.valueOf(guess.charAt(i)))) {
                        if (answer.charAt(i) == guess.charAt(i)) {
                            areaArray[counter - 1][i].setText(guess.substring(i, i + 1));
                            areaArray[counter - 1][i].setStyle("-fx-text-fill: green; -fx-font-size: 30px;");
                        } else {
                            areaArray[counter - 1][i].setText(guess.substring(i, i + 1));
                            areaArray[counter - 1][i].setStyle("-fx-text-fill: yellow; -fx-font-size: 30px;");
                        }
                    } else {
                        areaArray[counter - 1][i].setText(guess.substring(i, i + 1));
                        areaArray[counter - 1][i].setStyle("-fx-text-fill: gray; -fx-font-size: 30px;");
                    }
                }
            }
            counter++;
        } else if (guess.length() != 0) {
            entryField.setPromptText("Incorrect length try again");
        }
        return false;
    }

    public void processButton(ActionEvent e) {
        if (e.getSource() == entryButton) {
            guess = entryField.getText();

            if (!wordCorrect(guess, answer)) {

                if (counter < 6) {
                    entryField.setText(null);
                    entryField.setPromptText("Enter guess " + counter + "/5");
                }

                if (counter >= 6) {
                    entryField.setText(null);
                    entryField.setEditable(false);
                    entryField.setPromptText("The correct answer was " + answer);
                }

            }

        }
    }
}
