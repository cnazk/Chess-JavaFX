package ir.cnazk.Pieces;

import ir.cnazk.Board;
import javafx.scene.image.Image;

public class Asb implements Mohre {

    Rang rang;
    String pos;
    Board board;
    Image whiteImage;

    public Asb(Rang rang, String pos) {
        this.rang = rang;
        this.pos = pos;
        whiteImage = new Image("File:/Users/cnazaker/IdeaProjects/Chess/Resources/asb_sefid.png");
    }

    @Override
    public boolean canMove(String square) {
        board = getBoard();
        int columnDiff = Math.abs(diff(pos.charAt(0), square.charAt(0)));
        int rowDiff = Math.abs(pos.charAt(1) - square.charAt(1));
        try {
            if (columnDiff * rowDiff == 2 && board.get(square).getRang() != getRang())
                return true;
        } catch (Exception e) {
            if (columnDiff * rowDiff == 2)
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
        return "A";
    }

    @Override
    public String getPos() {
        return pos;
    }
}
