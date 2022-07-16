package core;

import AI.*;
import model.ACTION_TYPE;
import model.Board;
import model.Cell;
import model.Node;

import java.util.Hashtable;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        System.out.println(" please enter rows and columns of your board : \n");
        Scanner sc = new Scanner(System.in);
        String mn = sc.nextLine();
        int rows = Integer.parseInt(mn.split(" ")[0]);
        int columns = Integer.parseInt(mn.split(" ")[1]);
        String[][] board = new String[rows][columns];
        String[] lines = new String[rows];
        for (int i = 0; i < rows; i++) {
            lines[i] = sc.nextLine();
            String[] line = lines[i].split(" ");
            if (columns >= 0) System.arraycopy(line, 0, board[i], 0, columns);
        }
        Mapper mapper = new Mapper();
        Cell[][] cells = mapper.createCells(board, rows, columns);
        Board gameBoard = mapper.createBoard(cells, rows, columns);
        Hashtable<String, Boolean> initHash = new Hashtable<>();
        initHash.put(Cell.getStart().toString(), true);
        Node start = new Node(Cell.getStart(), Cell.getStart().getValue(),
                Cell.getGoal().getValue(), gameBoard, null, initHash, ACTION_TYPE.RIGHT);


        BFS bfs = new BFS();
        System.out.println("\u001b[31m\n ---------------------BFS Algorithm Solved Problem--------------------\n");
        bfs.search(start);
        IDS ids = new IDS();
        System.out.println("\u001b[31m\n ---------------------IDS Algorithm Solved Problem--------------------\n");
        ids.search(start);
        System.out.println("\u001b[31m\n ---------------------A* Algorithm Solved Problem--------------------\n");
        AStar A = new AStar();
        A.search(start);
        System.out.println("\u001b[31m\n ---------------------IDA* Algorithm Solved Problem--------------------\n");
        IDAStar IDAStar = new IDAStar();
        IDAStar.search(start);
        System.out.println("\u001b[31m\n ---------------------BDS Algorithm Solved Problem--------------------\n");
        BDS bds = new BDS();
        bds.search(start);

    }
}
