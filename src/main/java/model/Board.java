package model;

import core.Constants;
import core.Mapper;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Board {
    private int row;
    private int col;
    private Cell[][] cells;

    public Board(int row, int col, Cell[][] cells) {
        this.row = row;
        this.col = col;
        this.cells = cells;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Cell[][] getCells() {
        return cells;
    }

    /**
     * <p>Refactor :  </p>
     *
     * @param data generated from Board.toString()
     */
    public static Board refactor(String data) {
        Scanner scanner = new Scanner(data);
        int rowC = data.split("\n").length;
        int colC = data.split("\n")[0].split("\t").length;
        Cell[][] cells = new Cell[rowC][colC];
        int rc = 0;
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String[] columns = row.split("\t");
            Cell[] rowCells = new Cell[columns.length];
            for (int i = 0; i < columns.length; i++) {
                String column = columns[i];
                column = column.replace(Constants.ANSI_BRIGHT_BLUE, "")
                        .replace(Constants.ANSI_BRIGHT_GREEN, "");
                String op = new Scanner(column).findInLine(Pattern.compile(OPERATION_TYPE.operations_pattern));
                int value = Integer.parseInt(column.replaceAll(String.valueOf("\\"+op),""));
                Cell cell = new Cell(rc, i, value,op);
                rowCells[i] = cell;
            }
            cells[rc] = rowCells;
            rc++;
        }
       return new Board(rowC, colC, cells);

    }

    @Override
    public String toString() {
        StringBuilder map = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map.append(Constants.ANSI_BRIGHT_BLUE)
                        .append(OPERATION_TYPE.getOperationTag(cells[i][j].getOperationType()))
                        .append(cells[i][j].getValue())
                        .append("\t");

            }
            map.append("\n");
        }
        return map.toString();
    }

}
