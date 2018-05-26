package ir.cnazk.Pieces;

import ir.cnazk.Board;
import ir.cnazk.Main;
import javafx.scene.image.Image;

public interface Mohre {

    boolean canMove(String square);
    boolean moveTo(String square);
    Rang getRang();

    default int diff(char a, char b) {
        String s = "abcdefgh";
        return s.indexOf(b) - s.indexOf(a);
    }

    Rang getEnemyRang();

    default Board getBoard() {
        return Main.board;
    }

    String getPos();

    Image getImage();

    enum Rang {
        WHITE,
        BLACK;
    }



}
