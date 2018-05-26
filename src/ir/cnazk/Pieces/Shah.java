package ir.cnazk.Pieces;

import ir.cnazk.Board;
import javafx.scene.image.Image;

public class Shah implements Mohre {

    Rang rang;
    String pos;
    Board board;
    Image whiteImage;

    public Shah(Rang rang, String pos) {
        this.rang = rang;
        this.pos = pos;
        whiteImage = new Image("File:/Users/cnazaker/IdeaProjects/Chess/Resources/shah_sefid.png");
    }

    @Override
    public boolean canMove(String square) {
        board = getBoard();
        if (square.equals(pos) || board.treats(getEnemyRang(), square))
            return false;
        if (Math.abs(square.charAt(0) - pos.charAt(0)) < 2 && Math.abs(square.charAt(1) - pos.charAt(1)) < 2) {
            return true;
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
        return "V";
    }

    @Override
    public String getPos() {
        return pos;
    }
}
