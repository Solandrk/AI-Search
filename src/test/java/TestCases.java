
import AI.*;
import core.Mapper;
import model.ACTION_TYPE;
import model.Board;
import model.Cell;
import model.Node;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Hashtable;
import java.util.Scanner;

public class TestCases {

    @Test
    public void TestCase1()
    {
        String testCase1 =
                "3 3\n" +
                "s1 +7 +3\n" +
                "*3 -2 +1\n" +
                "-3 *8 g10";
        testAIs(testCase1);

    }

    @Test
    public void TestCase2()
    {
        String testCase2 =
                "4 4\n" +
                        "s1 *1 *10 *2\n" +
                        "*1 -10 -100 +10\n" +
                        "+5 *10 g100 +5\n" +
                        "*1 +5 +90 +10";
        testAIs(testCase2);

    }

    @Test
    public void TestCase3()
    {
        String testCase2 =
                "6 6\n" +
                        "s1 +9 *1 *1 *1 *1\n" +
                        "*5 +5 *1 *1 *1 *1\n" +
                        "*2 +15 w0 w0 w0 +10\n" +
                        "-50 -50 w0 g100 w0 +10\n" +
                        "*1 *1 a10 -10 +10 +10\n" +
                        "+10 +10 b10 w0 -10 -100";
        testAIs(testCase2);
    }

    @Test
    public void boardRefactor()
    {
        String board =
                "6 6\n" +
                        "s1 +9 *1 *1 *1 *1\n" +
                        "*5 +5 *1 *1 *1 *1\n" +
                        "*2 +15 w0 w0 w0 +10\n" +
                        "-50 -50 w0 g100 w0 +10\n" +
                        "*1 *1 a10 -10 +10 +10\n" +
                        "+10 +10 b10 w0 -10 -100";
        Board gameBoard = testBoard(board);
        assertEquals("\n"+gameBoard.toString(),"\n"+gameBoard.refactor(gameBoard.toString()));
    }
    public Board testBoard(String input)
    {
        Scanner sc = new Scanner(input);
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
        return gameBoard;
    }
    public  void testAIs(String input) {
        Scanner sc = new Scanner(input);
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
       // bds.search(start);
    }
}
