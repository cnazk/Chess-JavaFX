package ir.cnazk.Pieces;

import ir.cnazk.Board;
import javafx.scene.image.Image;

public class Vazir implements Mohre {

    Rang rang;
    String pos;
    Board board;
    Image whiteImage;

    public Vazir(Rang rang, String pos) {
        this.rang = rang;
        this.pos = pos;
        whiteImage = new Image("File:/Users/cnazaker/IdeaProjects/Chess/Resources/vazir_sefid.png");
    }

    @Override
    public boolean canMove(String square) {
        if (pos.equals(square) && pos.charAt(1) == '8')
            return true;
        return (new Fil(rang, pos).canMove(square) || new Rokh(rang, pos).canMove(square));
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
    public String getPos() {
        return pos;
    }

    @Override
    public Image getImage() {
        return whiteImage;
    }

    @Override
    public String toString() {
        return "V";
    }

}
