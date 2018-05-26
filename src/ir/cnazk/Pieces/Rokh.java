package ir.cnazk.Pieces;

import ir.cnazk.Board;
import javafx.scene.image.Image;

public class Rokh implements Mohre {

    Rang rang;
    String pos;
    Board board;
    Image whiteImage;

    public Rokh(Rang rang, String pos) {
        this.rang = rang;
        this.pos = pos;
        whiteImage = new Image("File:/Users/cnazaker/IdeaProjects/Chess/Resources/rokh_sefid.png");
    }

    @Override
    public boolean canMove(String square) {
        board = getBoard();
        //Check Reachable

        //Left or Right
        if (pos.charAt(1) == square.charAt(1)) {
            //Left
            if (pos.charAt(0) > square.charAt(0)) {
                char temp = pos.charAt(0);
                temp--;
                while (temp != square.charAt(0)) {
                    if (board.get(String.format("%c%c", temp, square.charAt(1))) != null)
                        return false;
                    temp--;
                }
                if (board.get(String.format("%c%c", temp, square.charAt(1))) != null)
                    if (board.get(String.format("%c%c", temp, square.charAt(1))).getRang() == getRang())
                        return false;
                return true;
            }
            //Right
            if (pos.charAt(0) < square.charAt(0)) {
                char temp = pos.charAt(0);
                temp++;
                while (temp != square.charAt(0)) {
                    if (board.get(String.format("%c%c", temp, square.charAt(1))) != null)
                        return false;
                    temp++;
                }
                if (board.get(String.format("%c%c", temp, square.charAt(1))) != null)
                    if (board.get(String.format("%c%c", temp, square.charAt(1))).getRang() == getRang())
                        return false;
                return true;
            }
        }
        //Up or Down
        if (pos.charAt(0) == square.charAt(0)) {
            //Up
            if (pos.charAt(1) < square.charAt(1)) {
                char temp = pos.charAt(1);
                temp++;
                while (temp != square.charAt(1)) {
                    if (board.get(String.format("%c%c", square.charAt(0), temp)) != null)
                        return false;
                    temp++;
                }
                if (board.get(String.format("%c%c", square.charAt(0), temp)) != null)
                    if (board.get(String.format("%c%c", square.charAt(0), temp)).getRang() == getRang())
                        return false;
                return true;
            }
            //Down
            if (pos.charAt(1) > square.charAt(1)) {
                char temp = pos.charAt(1);
                temp--;
                while (temp != square.charAt(1)) {
                    if (board.get(String.format("%c%c", square.charAt(0), temp)) != null)
                        return false;
                    temp--;
                }
                if (board.get(String.format("%c%c", square.charAt(0), temp)) != null)
                    if (board.get(String.format("%c%c", square.charAt(0), temp)).getRang() == getRang())
                        return false;
                return true;
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
        return "R";
    }

    @Override
    public String getPos() {
        return pos;
    }
}
