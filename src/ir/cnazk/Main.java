package ir.cnazk;

import ir.cnazk.Pieces.Mohre;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Board board;
    String from = "";
    Mohre.Rang turn = Mohre.Rang.WHITE;

    @Override
    public void start(Stage primaryStage) throws Exception{
        board = new Board();
        GridPane gridPane = new GridPane();
        drawBoard(gridPane, primaryStage);
        primaryStage.setTitle(turn.toString());
        primaryStage.setScene(new Scene(gridPane, 800, 800));
        primaryStage.show();
    }

    private void drawBoard(GridPane pane, Stage stage) {
        String temp = "abcdefgh";
        for (int i = 8; i >= 1; i--) {
            for (int j = 0; j < 8; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                if (board.get(temp.charAt(j) + String.valueOf(i)) != null)
                    imageView.setImage(board.get(temp.charAt(j) + String.valueOf(i)).getImage());
                Group b;
                imageView.setBlendMode(BlendMode.BLUE);
                if (board.get(temp.charAt(j) + String.valueOf(i)) != null) {
                    if (board.get(temp.charAt(j) + String.valueOf(i)).getRang() == Mohre.Rang.BLACK)
                        imageView.setBlendMode(BlendMode.MULTIPLY);
                    else
                        imageView.setBlendMode(BlendMode.HARD_LIGHT);
                }
                if ((i + j) % 2 == 0) {
                    ImageView gray = new ImageView(new Image("File:/Users/cnazaker/IdeaProjects/Chess/Resources/gray.png"));
                    b = new Group(gray, imageView);
                }
                else {
                    ImageView white = new ImageView(new Image("File:/Users/cnazaker/IdeaProjects/Chess/Resources/white.png"));
                    b = new Group(white, imageView);
                }
                b.setId(temp.charAt(j) + String.valueOf(i));
                b.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.setTitle(turn.toString());
                        if (from.equals("")) {
                            if (turn == board.get(b.getId()).getRang()) {
                                from = b.getId();
                                System.out.println("From: " + from);
                            }
                        }
                        else {
                            System.out.println("To: " + b.getId());
                            if (board.perform(from, b.getId())) {
                                from = "";
                                drawBoard(pane, stage);
                                nextTurn();
                                stage.setTitle(turn.toString());
                            }
                            from = "";
                        }
                        if (!from.equals("")) {
                            stage.setTitle("From: " + from);
                        }
                    }
                });
                pane.add(b, j, 9 - i);
            }
        }
    }

    private void nextTurn() {
        if (turn == Mohre.Rang.WHITE)
            turn = Mohre.Rang.BLACK;
        else
            turn = Mohre.Rang.WHITE;
    }

    public static void main(String[] args) {
        launch(args);
    }
}