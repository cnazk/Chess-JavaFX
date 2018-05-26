package ir.cnazk.Pieces;

import ir.cnazk.Board;
import javafx.scene.image.Image;

public class Fil implements Mohre {


    Rang rang;
    String pos;
    Board board;
    Image whiteImage;

    public Fil(Rang rang, String pos) {
        this.rang = rang;
        this.pos = pos;
        whiteImage = new Image("File:/Users/cnazaker/IdeaProjects/Chess/Resources/fil_sefid.png");
    }

    @Override
    public boolean canMove(String square) {
        board = getBoard();

        if (Math.abs(pos.charAt(0) - square.charAt(0)) == Math.abs(pos.charAt(1) - square.charAt(1))) {
            //Left
            if (pos.charAt(0) > square.charAt(0)) {
                //Left - Up
                if (pos.charAt(1) < square.charAt(1)) {
                    char row = pos.charAt(1);
                    row++;
                    char column = pos.charAt(0);
                    column--;
                    while (square.charAt(0) != column && square.charAt(1) != row) {
                        if (board.get(String.format("%c%c", column, row)) != null)
                            return false;
                        row++;
                        column--;
                    }
                    if (board.get(String.format("%c%c", column, row)) != null)
                        if (board.get(String.format("%c%c", column, row)).getRang() == getRang())
                            return false;
                    return true;
                }
                //Left-Down
                if (pos.charAt(1) > square.charAt(1)) {
                    char row = pos.charAt(1);
                    row--;
                    char column = pos.charAt(0);
                    column--;
                    while (square.charAt(0) != column && square.charAt(1) != row) {
                        if (board.get(String.format("%c%c", column, row)) != null)
                            return false;
                        row--;
                        column--;
                    }
                    if (board.get(String.format("%c%c", column, row)) != null)
                        if (board.get(String.format("%c%c", column, row)).getRang() == getRang())
                            return false;
                    return true;
                }
            }
            //Right
            if (pos.charAt(0) < square.charAt(0)) {
                //Right - Up
                if (pos.charAt(1) < square.charAt(1)) {
                    char row = pos.charAt(1);
                    row++;
                    char column = pos.charAt(0);
                    column++;
                    while (square.charAt(0) != column && square.charAt(1) != row) {
                        if (board.get(String.format("%c%c", column, row)) != null)
                            return false;
                        row++;
                        column++;
                    }
                    if (board.get(String.format("%c%c", column, row)) != null)
                        if (board.get(String.format("%c%c", column, row)).getRang() == getRang())
                            return false;
                    return true;
                }
                //Right-Down
                if (pos.charAt(1) > square.charAt(1)) {
                    char row = pos.charAt(1);
                    row--;
                    char column = pos.charAt(0);
                    column++;
                    while (square.charAt(0) != column && square.charAt(1) != row) {
                        if (board.get(String.format("%c%c", column, row)) != null)
                            return false;
                        row--;
                        column++;
                    }
                    if (board.get(String.format("%c%c", column, row)) != null)
                        if (board.get(String.format("%c%c", column, row)).getRang() == getRang())
                            return false;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean moveTo(String square) {
        if (canMove(square)) {
            pos = square;
            return true;
        }
        return false;
    }

    @Override
    public Rang getRang() {
        return rang;
    }

    @Override
    public Rang getEnemyRang() {
        if (rang == Rang.BLACK)
            return Rang.WHITE;
        return Rang.BLACK;
    }

    @Override
    public Image getImage() {
        return whiteImage;
    }

    @Override
    public String toString() {
        return "F";
    }

    @Override
    public String getPos() {
        return pos;
    }
}
