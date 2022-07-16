package model;

import core.Constants;

public enum OPERATION_TYPE {
    MINUS,
    ADD,
    GOAL,
    START,
    MULT,
    POW,
    WALL,
    DECREASE_GOAL,
    INCREASE_GOAL,
    UNDEFINED;


    public static OPERATION_TYPE getOperation(String op) {
        return switch (op) {
            case "-" -> MINUS;
            case "+" -> ADD;
            case "g", Constants.GOAL -> GOAL;
            case "*" -> MULT;
            case "^" -> POW;
            case "s", Constants.CASTLE -> START;
            case "w", Constants.WALL -> WALL;
            case "a", Constants.FLOWER -> INCREASE_GOAL;
            case "b", Constants.BOMB -> DECREASE_GOAL;
            default -> UNDEFINED;
        };
    }

    public static String getOperationTag(OPERATION_TYPE opt) {
        return switch (opt) {
            case MINUS -> "-";
            case ADD -> "+";
            case MULT -> "*";
            case POW -> "^";
            case GOAL -> Constants.GOAL;
            case START -> Constants.CASTLE;
            case WALL -> Constants.WALL;
            case INCREASE_GOAL -> Constants.FLOWER;
            case DECREASE_GOAL -> Constants.BOMB;
            default -> " ";
        };

    }

    public static String operations_pattern = "["+Constants.BOMB+Constants.CASTLE+Constants.FLOWER+
                                                    Constants.PLAYER+Constants.START+
                                                       Constants.WALL+Constants.GOAL + "+\\-*^]";
}
