package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;


public class HelloApplication extends Application {
    private Button play = new Button("Play");
    private Button playAgain = new Button("New Game");

    private Label leftLabelForMode = new Label("");
    private ComboBox<String> comboBoxOperations = new ComboBox<>();
    private RadioButton radioButtonO = new RadioButton("O");
    private RadioButton radioButtonX = new RadioButton("X");
    private String currentPlayer = "human";
    private Button[] buttons = new Button[9];
    private GridPane gridPane = new GridPane();
    private String currentPlayerChar = "O";
    private String currentAiChar = "X";
    private Label outputWinnerLabel = new Label("");
    private int numOfPlayed = 0;
    private HBox hBoxChar = new HBox(7);
    private Stage primaryStage = new Stage();
    
    ////
    private HBox hnew = new HBox(15);
    private Label WhoStart = new Label("Who Starts : ");
    private RadioButton userStart = new RadioButton("human");
    private RadioButton aiStart = new RadioButton("AI");
    
    private HBox hnew1 = new HBox(15);
    private TextField numOfGame = new TextField();
    private static int checkNum = 1;    
    private Button rePlay = new Button("rePlay");
    
    private HBox hnew2 = new HBox(15);
    private TextField NameOfUser1 = new TextField();
    private TextField NameOfUser2 = new TextField();
    
    private static int resultUser1 = 0;
    private static int resultUser2 = 0;
    
    private Label resLabel = new Label();
    
    private static String resultUserOne = "";
    private static String resultUserTwo = "";
    
    private static int chooseGame = 0;
    private static int numGame = 1;
    
    private RadioButton WithMessage = new RadioButton("With Message?");
    
    int[] probabilities = new int[buttons.length];;
    
    private TextArea area = new TextArea();
    
    private static int prob = 1;

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        //

        // stage = primaryStage;

        primaryStage.setTitle("Hello Tic Tac Toe");
        primaryStage.setScene(mainAppScene());
        primaryStage.show();
    }

    
    private Scene mainAppScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 70, 10));

        Label welcomeLabel = new Label("Welcome to Tic Tac Toe game");
        welcomeLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        welcomeLabel.setTextFill(Color.DARKGRAY);

        borderPane.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.TOP_CENTER);


        gridPane.setHgap(2);
        gridPane.setVgap(2);


        //
        //borderPane.setLeft(leftLabelForMode);
        borderPane.setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(gridPane, Pos.CENTER);

        comboBoxOperations.getItems().addAll("Easy mode", "Play with friend", "Hard mode");
        comboBoxOperations.getSelectionModel().select(chooseGame);
//        comboBoxOperations.getSelectionModel().selectFirst();
        System.out.println();
        NameOfUser1.setText("User 1");
        NameOfUser2.setText("User 2");
        
        
        hnew2.getChildren().addAll(NameOfUser1,NameOfUser2);
        
        
        numOfGame.setPromptText("How many Game?");
        hnew1.getChildren().addAll(comboBoxOperations,numOfGame);
        VBox vboxx = new VBox(20);
        resLabel.setText("Scour is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
        vboxx.getChildren().addAll(hnew1,WithMessage,resLabel);
        borderPane.setRight(vboxx);
        resLabel.setVisible(false);

        outputWinnerLabel.setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        outputWinnerLabel.setTextFill(Color.RED);


        HBox hBoxBottom = new HBox(50);
        play.setOnAction(new PlayButtonHandler());
        
        playAgain.setVisible(false);
        rePlay.setVisible(false);
        
        area.setPrefWidth(200); // Set your desired width
        area.setPrefHeight(100);
        
        rePlay.setOnAction(new PlayButtonHandler());
        playAgain.setOnAction(new PlayAgainButtonHandler());
        hBoxBottom.getChildren().addAll(outputWinnerLabel, play, playAgain,rePlay ,area );

        borderPane.setBottom(hBoxBottom);
        Label chooseYourCharLabel = new Label("Choose the letter you want");

        
        ToggleGroup toggleGroup = new ToggleGroup();
        ToggleGroup toggleGroup1 = new ToggleGroup();

        radioButtonO.setSelected(true);
        radioButtonO.setToggleGroup(toggleGroup);
        radioButtonX.setToggleGroup(toggleGroup);
        
        userStart.setSelected(true);
        userStart.setToggleGroup(toggleGroup1);
        aiStart.setToggleGroup(toggleGroup1);
        hnew.getChildren().addAll(WhoStart,userStart,aiStart);
        
        WithMessage.setSelected(true);

        hBoxChar.getChildren().addAll(chooseYourCharLabel, radioButtonO, radioButtonX);
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(hBoxChar,hnew ,NameOfUser1,NameOfUser2, leftLabelForMode);
        borderPane.setLeft(vBox);

        //
        // System.out.println(gridPane.getChildren().get(1));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(borderPane, 750, 550);
        return scene;
    }

    
    public void setTextOfButton(Button button, char x_o) {
        button.setText((x_o + "").toUpperCase());
    }

    
    private void makeInvisible(Node hBoxChar, Node comboBoxOperations, Node play, Node hnew , Node hnew1) {
        hBoxChar.setVisible(false);
        comboBoxOperations.setVisible(false);
        play.setVisible(false);
        hnew.setVisible(false);
        hnew1.setVisible(false);
        numOfGame.setVisible(false);
        hnew2.setVisible(false);
        NameOfUser1.setVisible(false);
        NameOfUser2.setVisible(false);
        leftLabelForMode.setVisible(true);
        WithMessage.setVisible(false);
//        resLabel.setVisible(false);

    }

    
    private void makeVisible(Node hBoxChar, Node comboBoxOperations, Node play , Node hnew) {
        hBoxChar.setVisible(true);
        comboBoxOperations.setVisible(true);
        play.setVisible(true);
        hnew.setVisible(true);
        hnew1.setVisible(true);
        numOfGame.setVisible(true);
        hnew2.setVisible(true);
        NameOfUser1.setVisible(true);
        NameOfUser2.setVisible(true);
        WithMessage.setVisible(true);
        
        
//        leftLabelForMode.setVisible(false);
    }

    
    private void makeVisiblePlayAgain() {
        playAgain.setVisible(true);
        leftLabelForMode.setVisible(false);
    }
    

    
    private boolean checkIfWon(String playerChar) {


        return checkRowsWon(playerChar) || checkColsWon(playerChar) || checkDiagonalsWon(playerChar);
        //return false;

    }

    
    private boolean checkRowsWon(String playerChar) {
        int counter = 0;

        for (int i = 0; i < 3; i++) {

            int countEqualsRows = 0;
            for (int j = 0; j < 3; j++) {
                Button buttonRows = (Button) gridPane.getChildren().get(counter);

                if (buttonRows.getText().equals(playerChar.trim())) {
                    countEqualsRows++;
                }
                if (countEqualsRows == 3)
                    return true;
                counter++;
            }
        }
        return false;

    }

    
    private boolean checkColsWon(String playerChar) {


        int indexOriginal = -3;
        for (int i = 0; i < 3; i++) {

            int index = indexOriginal + i;
            int countEqualsCols = 0;

            for (int j = 0; j < 3; j++) {
                index += 3;
                Button buttonCls = (Button) gridPane.getChildren().get(index);

                // constAdd=3;
                if (buttonCls.getText().equals(playerChar.trim())) {
                    countEqualsCols++;
                }


            }
            if (countEqualsCols == 3)
                return true;
        }

        return false;
    }

    
    private boolean checkDiagonalsWon(String playerChar) {


        // Button buttonCls = (Button) gridPane.getChildren().get(index);


        int index = 0;

        int countEqualsDiagonals = 0;
        for (int j = 0; j < 3; j++) {
            Button buttonCls = (Button) gridPane.getChildren().get(index);
            if (buttonCls.getText().equals(playerChar.trim())) {
                countEqualsDiagonals++;
            }
            index += 4;
        }
        if (countEqualsDiagonals == 3)
            return true;


        index = 2;

        countEqualsDiagonals = 0;
        for (int j = 0; j < 3; j++) {
            Button buttonCls = (Button) gridPane.getChildren().get(index);
            if (buttonCls.getText().equals(playerChar.trim())) {
                countEqualsDiagonals++;
            }
            index += 2;
        }
        return countEqualsDiagonals == 3;
    }

    
    private void winnerEvent(String currentPlayerChar) {

        for (int i = 0; i < buttons.length; i++) {

            buttons[i].setDisable(true);
        }
        outputWinnerLabel.setText("'" + currentPlayerChar + "' is the Winner");

    }

    //method to replay again if i have another game!
    private void checkAnotherGame() {
    	
    	if(checkNum == 1  ) {
        	makeVisiblePlayAgain();   
        	resultUserTwo = "";
         	resultUserOne = "";
//         	resLabel.setText("");
//         	leftLabelForMode.setVisible(false);
         	numGame = 1;
         	resultUser1 = 0;
         	resultUser2 = 0;
        }
        else {
        	if(resultUser1 > numGame/2 || resultUser2 >numGame/2) {
        		makeVisiblePlayAgain(); 
        		resultUserTwo = "";
             	resultUserOne = "";
//             	resLabel.setText("");
//             	leftLabelForMode.setVisible(false);
             	numGame = 1;
             	resultUser1 = 0;
             	resultUser2 = 0;
        	}
//        	makeVisiblePlayAgain(); 
        	rePlay.setVisible(true);
        	PlayAgainButtonHandler buttonHandler = new PlayAgainButtonHandler();
        	PlayButtonHandler buttonHandler1 = new PlayButtonHandler();
        	
            // Create a dummy ActionEvent (can be null or any other appropriate value)
            ActionEvent dummyEvent = new ActionEvent();
            

            rePlay.setOnAction(e ->{
            	// Call the handle method manually
            	buttonHandler.handle(dummyEvent);
            	buttonHandler1.handle(dummyEvent);
            	
            });
        	checkNum--;
        }
    }

    
    //create the square of the game!
    private void addButtonsToCenter(int mode) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // buttons[i] = new Button(i +" " + j);
                buttons[counter] = new Button();
                buttons[counter].setPrefSize(60, 60);
                buttons[counter].setMaxSize(60, 60);
                buttons[counter].setFont(Font.font("arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
                gridPane.add(buttons[counter], j, i);
                if (mode == 0)
                    buttons[counter].setOnAction(new ButtonHandlerEasy());
                else if (mode == 1)
                    buttons[counter].setOnAction(new ButtonHandlerFriend());
                else
                    buttons[counter].setOnAction(new ButtonHandlerHard());

                counter++;

            }
        }
    }

    
    private class ButtonHandlerFriend implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	if(resultUserOne.isEmpty()) {
            	resultUserOne = NameOfUser1.getText();
            	resultUserTwo = NameOfUser2.getText();                        	
            }
        	
            System.out.println("I am inside the handerler for friend");

            Button clickedButton = (Button) event.getSource();
            //String ch = clickedButton.getText();

            //if (!(ch.equalsIgnoreCase("x")) && !(ch.equalsIgnoreCase("o"))) {

            setTextOfButton(clickedButton, currentPlayerChar.trim().charAt(0));
            // check if the "human"won call here exactly
            clickedButton.setDisable(true);
            boolean won = checkIfWon(currentPlayerChar);
            
            if (won) {
            	if(currentPlayer.equalsIgnoreCase("1")) {
            		winnerEvent(currentPlayerChar+"  "+resultUserTwo); 
            		resultUser2++;
            	}
            	else {
            		winnerEvent(currentPlayerChar+"  "+resultUserOne); 
            		resultUser1++;
            	}
            	
            	if(!numOfGame.getText().isEmpty()) {
            		numGame = Integer.parseInt(numOfGame.getText());
            		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
            	}
            	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
            	resLabel.setVisible(true);
            	
            	  checkAnotherGame();
                //makeVisible(hBoxChar, comboBoxOperations, play);
//                makeVisiblePlayAgain();
                
            } else {

                if (currentPlayer.equalsIgnoreCase("1")) {
                	currentPlayer = "2";
                	leftLabelForMode.setText("Now "+resultUserOne+ " turn");                	
                }
                else {
                	currentPlayer = "1";
                	leftLabelForMode.setText("Now "+resultUserTwo+ " turn");                	
                }

                if (currentPlayerChar.equalsIgnoreCase("X"))
                    currentPlayerChar = "O";
                else
                    currentPlayerChar = "X";

                numOfPlayed++;
                if (numOfPlayed != 9) {
                    //nextTurn();
                } else {
                    outputWinnerLabel.setText("equality (tie)");
//                    makeVisiblePlayAgain();
//                    resultUser2++;
                	if(!numOfGame.getText().isEmpty()) {
                		numGame = Integer.parseInt(numOfGame.getText());
                		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	}
                	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	resLabel.setVisible(true);
                    checkAnotherGame();
                    
                    numOfPlayed = 0;
                }
            }


        }


    }

     
    private class ButtonHandlerHard implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	if(resultUserOne.isEmpty()) {
            	resultUserOne = NameOfUser1.getText();
            	resultUserTwo = NameOfUser2.getText();   
            }
        	leftLabelForMode.setVisible(false);
            Button clickedButton = (Button) event.getSource();
            //String ch = clickedButton.getText();

            //if (!(ch.equalsIgnoreCase("x")) && !(ch.equalsIgnoreCase("o"))) {
            if (currentPlayer.equals("human")) {
                setTextOfButton(clickedButton, currentPlayerChar.trim().charAt(0));
                clickedButton.setDisable(true);
                
                
                
                
                
//                calculateProbabilities();
//                printProbabilities();

                // check if the "human"won call here exactly
                boolean won = checkIfWon(currentPlayerChar);
                if (won) {
                	resultUser1++;
                	if(!numOfGame.getText().isEmpty()) {
                		numGame = Integer.parseInt(numOfGame.getText());
                		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	}
                	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	resLabel.setVisible(true);
                	
                    winnerEvent(currentPlayerChar+"  "+resultUserOne);
                    //makeVisible(hBoxChar, comboBoxOperations, play);
//                    makeVisiblePlayAgain();
                    checkAnotherGame();
                    
                } else {

                    currentPlayer = "ai";
                    numOfPlayed++;
                    if (numOfPlayed != 9) {
                        nextTurnWithBestMove();
//                        if(WithMessage.isSelected()) {
//                        	
//                        int bestScore = Integer.MIN_VALUE;
//                        int bestMove = 0;
//                        for (int i = 0; i < buttons.length; i++) {
//
//                            if (!buttons[i].isDisabled()) {
//                                setTextOfButton(buttons[i], currentAiChar.trim().charAt(0));
//                                buttons[i].setDisable(true);
//                                int score = miniMax(false); // score 1: means best for the "AI" player
//                                buttons[i].setDisable(false);                   // the "AI" player is the maximizing
//                                buttons[i].setText("");
////                                buttons[i].setText(""+score+"");                         // so i am checking the move after me
//                                if (score >= bestScore) {                        // (which is the human) so i pass the "false"
//                                    bestScore = score;
//                                    bestMove = i;
//                                }
////                                buttons[i].setText(""+score);
////                                System.out.println(currentPlayer+"  i is: "+i+" score is :"+score);
//
//                            }
//                        }
//                        buttons[bestMove].setText(""+bestScore);
//                        System.out.println("The best move of user is ,"+bestMove +" : "+bestScore);
//                        System.out.println("_____________________________________");
//                        }
                        
                                       
                    } else {
                        outputWinnerLabel.setText("equality (tie)");
//                        makeVisiblePlayAgain();
                        if(!numOfGame.getText().isEmpty()) {
                    		numGame = Integer.parseInt(numOfGame.getText());
                    		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                    	}
                    	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                    	resLabel.setVisible(true);
                        checkAnotherGame();

                        numOfPlayed = 0;
                    }
                }
            }

        }
       
        private void nextTurnWithBestMove() {
        	
            int bestScore = Integer.MIN_VALUE;
            int bestMove = 0;
            for (int i = 0; i < buttons.length; i++) {

                if (!buttons[i].isDisabled()) {
                    setTextOfButton(buttons[i], currentAiChar.trim().charAt(0));
                    buttons[i].setDisable(true);
                    int score = miniMax( false); // score 1: means best for the "AI" player
                    buttons[i].setDisable(false);                   // the "AI" player is the maximizing
                    buttons[i].setText("");
//                    buttons[i].setText(""+score+"");                         // so i am checking the move after me
                    if (score >= bestScore) {                        // (which is the human) so i pass the "false"
                        bestScore = score;
                        bestMove = i;
                        buttons[i].setText(""+score+"");
                    }
//                    prob =1;
//                    buttons[i].setText(""+score);
                    if(WithMessage.isSelected()) {
//                    	buttons[bestMove].setText(String.valueOf(bestScore));
                    	System.out.println(currentPlayer+"  i is: "+i+" score is :"+score);
                    	area.setText(area.getText()+"\n"+currentPlayer+"  i is: "+i+" score is :"+score);
//                    	buttons[i].setText(""+score+"");
                    }
                    
                    
                }
            }
            if(WithMessage.isSelected())
            System.out.println("_____________________________________");
            area.setText(area.getText()+"\n"+"Score of ai,"+bestMove +" : "+bestScore+"\n"+"_____________________________________");
            System.out.println("Best Move of ai is"+bestMove +" with best score "+bestScore);
            setTextOfButton(buttons[bestMove], currentAiChar.trim().charAt(0));
            buttons[bestMove].setDisable(true);


            // check if the "ai "won call here exactly

            boolean won = checkIfWon(currentAiChar);
            if (won) {
                winnerEvent(currentAiChar+"  "+NameOfUser2.getText());
                // makeVisible(hBoxChar, comboBoxOperations, play);
//                makeVisiblePlayAgain();
                resultUser2++;
                if(!numOfGame.getText().isEmpty()) {
            		numGame = Integer.parseInt(numOfGame.getText());
            		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
            	}
            	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
            	resLabel.setVisible(true);
                checkAnotherGame();

            } else {


                //aiButton.isDisable();
                currentPlayer = "human";
                numOfPlayed++;
                if (numOfPlayed == 9) {

                    outputWinnerLabel.setText("equality (tie)");
//                    makeVisiblePlayAgain();
                    if(!numOfGame.getText().isEmpty()) {
                		numGame = Integer.parseInt(numOfGame.getText());
                		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	}
                	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	resLabel.setVisible(true);
                    checkAnotherGame();

                    numOfPlayed = 0;
                }
            }
        }

        private int miniMax(boolean isMaximizing) {  // returns the score: 1, -1,0

            boolean playerWon = checkIfWon(currentPlayerChar);
            boolean aiWon = checkIfWon(currentAiChar);
            
            int score;

            if (playerWon)
                score = -1;

            else if (aiWon)

                score = 1;
            else {

                score = 0; // this means "tie"- they are equal
                for (int i = 0; i < buttons.length; i++) {
                    if (!buttons[i].isDisabled())
                        score = Integer.MAX_VALUE;
                }
            }


            if (score != Integer.MAX_VALUE) {
                return score;
            }
            if (isMaximizing) { // the 'ai' turn

                int bestScore = Integer.MIN_VALUE;

                for (int i = 0; i < buttons.length; i++) {

                    if (!buttons[i].isDisabled()) {
                        setTextOfButton(buttons[i], currentAiChar.trim().charAt(0));
                        buttons[i].setDisable(true);
                        int scoreFrom = miniMax( false);
                        buttons[i].setDisable(false);
//                        buttons[i].setText(""+score+"");
                        buttons[i].setText("");
                        bestScore = Math.max(scoreFrom, bestScore);
//                        probabilities[i] = Math.max(probabilities[i], scoreFrom);
                       /* if (scoreFrom > bestScore) {
                            bestScore = scoreFrom;
                            bestMove = i;
                        }*/
                        // j++;
                    }
                  
                }
                return bestScore;


            } else {
                int bestScore = Integer.MAX_VALUE;

                for (int i = 0; i < buttons.length; i++) {

                    if (!buttons[i].isDisabled()) {
                        setTextOfButton(buttons[i], currentPlayerChar.trim().charAt(0));
                        buttons[i].setDisable(true);
                        int scoreFrom = miniMax( true);
                        buttons[i].setDisable(false);
                        buttons[i].setText("");
                        bestScore = Math.min(scoreFrom, bestScore);
//                        probabilities[i] = Math.min(probabilities[i], scoreFrom);
                       /* if (scoreFrom < bestScore) {
                            bestScore = scoreFrom;
                            bestMove = i;
                        }*/
                        // j++;
//                        buttons[i].setText(""+bestScore);
//                        if(prob == 1) {
//                        	System.out.println("Score ,"+i +" : "+scoreFrom);
//                        	prob++;
//                        }
                        
                    }
//                    System.out.println("Score ,"+i +" : "+bestScore);
                }
                return bestScore;
            }


            //return 1; // always picks the first move*/

        }
    }

    
    
    
    private class ButtonHandlerEasy implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
        	if(resultUserOne.isEmpty()) {
            	resultUserOne = NameOfUser1.getText();
            	resultUserTwo = NameOfUser2.getText();   
            }
        	leftLabelForMode.setVisible(false);
            System.out.println("I am inside the handerler");

            Button clickedButton = (Button) event.getSource();
            //String ch = clickedButton.getText();

            //if (!(ch.equalsIgnoreCase("x")) && !(ch.equalsIgnoreCase("o"))) {
            if (currentPlayer.equals("human")) {
                setTextOfButton(clickedButton, currentPlayerChar.trim().charAt(0));
                // check if the "human"won call here exactly
                
                boolean won = checkIfWon(currentPlayerChar);
                if (won) {
                	resultUser1++;
                	if(!numOfGame.getText().isEmpty()) {
                		numGame = Integer.parseInt(numOfGame.getText());
                		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	}
                	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	resLabel.setVisible(true);
                    winnerEvent(currentPlayerChar+" "+resultUserOne);
                    //makeVisible(hBoxChar, comboBoxOperations, play);
                    System.out.println(checkNum);
                    
                    checkAnotherGame();
                    
                } else {
                    clickedButton.setDisable(true);
                    currentPlayer = "ai";
                    numOfPlayed++;
                    if (numOfPlayed != 9) {
                        nextTurn();
                    } else {
                        outputWinnerLabel.setText("equality (tie)");
//                        makeVisiblePlayAgain();
                        if(!numOfGame.getText().isEmpty()) {
                    		numGame = Integer.parseInt(numOfGame.getText());
                    		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                    	}
                    	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                    	resLabel.setVisible(true);
                        checkAnotherGame();
                        numOfPlayed = 0;
                    }
                }

            }

            // }

        }

        public void nextTurn() {
            Button[] availableButtons = new Button[9];


            int j = 0;
            for (int i = 0; i < buttons.length; i++) {
                if (!buttons[i].isDisabled()) {
                    availableButtons[j] = buttons[i];
                    j++;
                }
            }
            Random random = new Random();
            int move = 0;
            move = random.nextInt(j);


            Button aiButton = availableButtons[move];

            setTextOfButton(aiButton, currentAiChar.trim().charAt(0));
            // check if the "ai "won call here exactly

            boolean won = checkIfWon(currentAiChar);
            if (won) {
            	resultUser2++;
            	if(!numOfGame.getText().isEmpty()) {
            		numGame = Integer.parseInt(numOfGame.getText());
            		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
            	}
            	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
            	resLabel.setVisible(true);
                winnerEvent(currentAiChar+" "+resultUserTwo);
                // makeVisible(hBoxChar, comboBoxOperations, play);
//                makeVisiblePlayAgain();
                checkAnotherGame();

            } else {

                aiButton.setDisable(true);
                //aiButton.isDisable();
                currentPlayer = "human";
                numOfPlayed++;
                if (numOfPlayed == 9) {
                	if(!numOfGame.getText().isEmpty()) {
                		numGame = Integer.parseInt(numOfGame.getText());
                		resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	}
                	resLabel.setText("Scour of "+numGame+" game is \n"+resultUserOne+" : "+resultUser1+"\n"+resultUserTwo+" : "+resultUser2);
                	resLabel.setVisible(true);
                    outputWinnerLabel.setText("equality (tie)");
//                    makeVisiblePlayAgain();
                    checkAnotherGame();
                    numOfPlayed = 0;
                }
            }

        }
    }
    

    
    
    private class PlayButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            if (radioButtonO.isSelected()) {
                currentPlayerChar = "O";
                currentAiChar = "X";
            } else {
                currentPlayerChar = "X";
                currentAiChar = "O";
            }
            

            int chosenMode = comboBoxOperations.getSelectionModel().getSelectedIndex();
            chooseGame = chosenMode;

            if(!numOfGame.getText().isEmpty()) {
            	checkNum = Integer.parseInt(numOfGame.getText());              	
            }
            addButtonsToCenter(chosenMode);

            makeInvisible(hBoxChar, comboBoxOperations, play ,hnew, hnew1);
            if (chosenMode == 1) {
//                currentPlayer = "1";

                if(userStart.isSelected()) {
                	currentPlayer = "1";
//                	leftLabelForMode.setText(comboBoxOperations.getSelectionModel().getSelectedItem() + "\n"
//                			+ "Player " + NameOfUser1.getText() + " turn");                	
                }
                else {
                	currentPlayer = "2";
//                	leftLabelForMode.setText(comboBoxOperations.getSelectionModel().getSelectedItem() + "\n"
//                			+ "Player " + NameOfUser2.getText() + " turn");
                }
                // 0: easy, 1: with friend, 2: hard (minimax)
            }

            currentPlayer = userStart.isSelected() ? "human" : "AI";
            if (currentPlayer.equals("AI") && chosenMode != 1) {
                // If AI starts, simulate its first move
            	ButtonHandlerEasy buttonHandlerEasy = new ButtonHandlerEasy();

            	// Call the nextTurn method
            	buttonHandlerEasy.nextTurn();
            }
            
            
        }
    }

    
    
    
    private class PlayAgainButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            // reset the data;
            play = new Button("Play");
            playAgain = new Button("New Game");

            comboBoxOperations = new ComboBox<>();
            WithMessage = new RadioButton("With Message?");
            radioButtonO = new RadioButton("O");
            radioButtonX = new RadioButton("X");
            currentPlayer = "human";
            buttons = new Button[9];
            gridPane = new GridPane();
            currentPlayerChar = "O";
            currentAiChar = "X";
            outputWinnerLabel = new Label("");
            numOfPlayed = 0;
            hBoxChar = new HBox(7);
             hnew = new HBox(15);
             WhoStart = new Label("Who Starts : ");
             userStart = new RadioButton("human");
             aiStart = new RadioButton("AI");
             hnew1 = new HBox(15);
             numOfGame = new TextField();
             hnew2 = new HBox(15);
             NameOfUser1 = new TextField();
             NameOfUser2 = new TextField();
             resLabel = new Label();
             area = new TextArea();
//             resLabel.setText("");
//         	 numGame = 1;
             
            
            

            primaryStage.setScene(mainAppScene());
            primaryStage.show();
        }
    }

}