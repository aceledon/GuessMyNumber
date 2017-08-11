package game;

        import javafx.application.Application;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.image.Image;
        import javafx.scene.layout.Background;
        import javafx.scene.layout.BackgroundImage;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;

public class UI extends Application{

    private Stage window;
    private int randomNumber;
    private int guess;
    private int lowRange;
    private int highRange;
    private Text instruction;
    private int size = 300;


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {

        //WINDOW SETTINGS
        window = primaryStage;
        window.setTitle("Andrea's App");

        Image bg = new Image("background.png");
        BackgroundImage img = new BackgroundImage(bg, null, null, null, null);
        Background background = new Background(img);


        //LayoutOne
        Label titleOne = new Label("The Guessing Game");
        titleOne.setStyle("-fx-font-family: Verdana; -fx-font-size: 20; -fx-font-weight:bold");
        Button btnStart = new Button("Start Game");
        VBox layoutOne = new VBox(20);
        layoutOne.setAlignment(Pos.CENTER);
        layoutOne.setBackground(background);
        layoutOne.getChildren().addAll(titleOne, btnStart);
        Scene sceneOne = new Scene(layoutOne, size, size);

        //LayoutTwo
        instruction = new Text("Guess a number between 0 and 50");
        instruction.setStyle("-fx-font-family: Verdana; -fx-font-size: 14");
        TextField textNum = new TextField();
        textNum.setMaxWidth(35);
        Button btnGuess = new Button("Guess");
        VBox layoutTwo = new VBox(20);
        layoutTwo.setAlignment(Pos.CENTER);
        layoutTwo.setBackground(background);
        layoutTwo.getChildren().addAll(instruction, textNum, btnGuess);
        Scene sceneTwo = new Scene(layoutTwo, size, size);

        //LayoutThree
        Text titleThree = new Text("That's right! You are awesome!");
        titleThree.setStyle("-fx-font-family: Verdana; -fx-font-size: 14; -fx-font-weight:bold");
        Button btnNew = new Button("New Game");
        VBox layoutThree = new VBox(20);
        layoutThree.setAlignment(Pos.CENTER);
        layoutThree.setBackground(background);
        layoutThree.getChildren().addAll(titleThree, btnNew);
        Scene sceneThree = new Scene(layoutThree, size, size);



        //start game with a new random number
        btnStart.setOnAction(e -> {
            randomNumber = (int)(Math.random()*50);
            lowRange = randomNumber - 5 ;
            highRange = randomNumber + 5;
            System.out.println("Random number is " + randomNumber);
            window.setScene(sceneTwo);
        });

        //submit number and check if guess is right
        btnGuess.setOnAction(e-> {

            if (isInt(textNum)) {

                if (guess < randomNumber && guess >= lowRange){
                    instruction.setText("Getting closer, but still low");
                    window.setScene(sceneTwo);

                } else if (guess > randomNumber && guess <= highRange) {
                    instruction.setText("Almost there, but still high");
                    window.setScene(sceneTwo);
                } else if (guess > randomNumber){
                    instruction.setText(textNum.getText() + " is too high");
                    window.setScene(sceneTwo);
                } else if (guess < randomNumber) {
                    instruction.setText(textNum.getText() + " is too low");
                    window.setScene(sceneTwo);
                }
                else window.setScene(sceneThree);
            }

            else {
                instruction.setText(textNum.getText() + " is not a number");
            }
        });

        btnNew.setOnAction(e -> {
            window.setScene(sceneOne);
        });


        //SHOW WINDOW
        window.setScene(sceneOne);
        window.show();
    }


    /**
     * IsInt converts the text inserted in the TextField into an integer. It returns false
     * if text was non numeric.
     * @param input: text inserted in TextField object
     * @return boolean: true if successfully converted or false if exception encountered
     */
    private boolean isInt(TextField input){

        try{
            int number = Integer.parseInt(input.getText());
            guess = number;
            return true;

        } catch(NumberFormatException e){
            return false;
        }
    }



}
