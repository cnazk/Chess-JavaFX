package ir.cnazk;

import ir.cnazk.Pieces.*;

import java.util.HashMap;

public class Board {

    String s = "abcdefgh";
    Mohre whiteShah, blackShah;

    private HashMap<String, Mohre> board;

    private Board(HashMap<String, Mohre> board, Mohre whiteShah, Mohre blackShah) {
        this.board = board;
        this.whiteShah = whiteShah;
        this.blackShah = blackShah;
    }

    public Board() {
        board = new HashMap<>();
        for (int i = 6; i >= 3; i--)
            for (int j = 0; j < 8; j++)
                board.put(s.charAt(j) + String.valueOf(i), null);

        //Sarbaz
        for (int i = 0; i < 8; i++) {
            board.put(s.charAt(i) + "2", new Sarbaz(Mohre.Rang.WHITE, s.charAt(i) + "2"));
            board.put(s.charAt(i) + "7", new Sarbaz(Mohre.Rang.BLACK, s.charAt(i) + "7"));
        }

        //Rokh
        board.put("a8", new Rokh(Mohre.Rang.BLACK, "a8"));
        board.put("h8", new Rokh(Mohre.Rang.BLACK, "h8"));
        board.put("a1", new Rokh(Mohre.Rang.WHITE, "a1"));
        board.put("h1", new Rokh(Mohre.Rang.WHITE, "h1"));

        //Asb
        board.put("b1", new Asb(Mohre.Rang.WHITE, "b1"));
        board.put("b8", new Asb(Mohre.Rang.BLACK, "b8"));
        board.put("g1", new Asb(Mohre.Rang.WHITE, "g1"));
        board.put("g8", new Asb(Mohre.Rang.BLACK, "g8"));

        //Fil
        board.put("c1", new Fil(Mohre.Rang.WHITE, "c1"));
        board.put("c8", new Fil(Mohre.Rang.BLACK, "c8"));
        board.put("f1", new Fil(Mohre.Rang.WHITE, "f1"));
        board.put("f8", new Fil(Mohre.Rang.BLACK, "f8"));

        //Vazir
        board.put("e1", new Vazir(Mohre.Rang.WHITE, "e1"));
        board.put("e8", new Vazir(Mohre.Rang.BLACK, "e8"));

        //Shah
        whiteShah = new Shah(Mohre.Rang.WHITE, "d1");
        blackShah = new Shah(Mohre.Rang.BLACK, "d8");
        board.put("d1", whiteShah);
        board.put("d8", blackShah);

    }

    public boolean treats(Mohre.Rang rang, String square) {
        for (int i = 0; i < 8; i++)
            for (char j = '1'; j <= '8'; j++) {
                Mohre mohre = board.get(String.format("%c%c", s.charAt(i), j));
                if (mohre != null) {
                    if (mohre.getClass().equals(Shah.class))
                        continue;
                    if (mohre.getRang() == rang)
                        if (mohre.canMove(square))
                            return true;
                }
            }
            return false;
    }

    public Mohre get(String square) {
        return board.get(square);
    }

    private boolean check(String from, String to) {
        try {
            Board tempBoard = (Board) this.clone();
//            System.out.println(tempBoard);
//            System.out.println(this);
            tempBoard.forceMove(from, to);
            if (get(from).getRang() == Mohre.Rang.BLACK) {
                return !tempBoard.treats(Mohre.Rang.WHITE, blackShah.getPos());
            }
            return !tempBoard.treats(Mohre.Rang.BLACK, whiteShah.getPos());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void forceMove(String from, String to) {
        printBoard();
        board.put(to, get(from));
        board.put(from, null);
    }

    public boolean perform(String from, String to) {
        if (this.get(from) == null)
            return false;
        if (!this.get(from).moveTo(to))
            return false;
        if (!check(from, to)) {
            System.out.println("CHECK!");
            return false;
        }
        if (board.get(from) != null)
            board.put(to, get(from));
        board.put(from, null);
        board.get(to).moveTo(to);
//        printBoard();
        return true;
    }

    public void createVazir(Mohre.Rang rang, String pos, String lastPos) {
        board.put(pos, new Vazir(rang, pos));
        board.put(lastPos, null);
    }

    private void printBoard() {
        for (int i = 8; i >= 1; i--) {
            for (int j = 0; j < 8; j++) {
                if (board.get(s.charAt(j) + String.valueOf(i)) != null)
                    System.out.print(board.get(s.charAt(j) + String.valueOf(i)));
                else
                    System.out.print("+");
            }
            System.out.println();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Board((HashMap) board.clone(), whiteShah, blackShah);
    }
}
