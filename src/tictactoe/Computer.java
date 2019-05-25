package tictactoe;

import java.util.Random;

/**
 *
 * @author rcbgalido
 */
public class Computer {

    private final static int NUMBER_OF_TILES = 9;
    private final static int CENTER_TILE = 5;
    private final static int INVALID_MOVE = -1;
    
    private final String grid[];
    private final String sequences[] = {
        "123", "132", "231", "456", "465", "564", "789", "798", "897", //horizontal
        "147", "174", "471", "258", "285", "582", "369", "396", "693", //vertical
        "159", "195", "591", "357", "375", "573" //diagonal
    };
    private final String playerSymbol, computerSymbol;
    private final Random random;

    public Computer(String grid[], String computerSymbol, String playerSymbol) {
        this.grid = grid;
        this.computerSymbol = computerSymbol;
        this.playerSymbol = playerSymbol;
        random = new Random();
    }

    public int move() {
        int move = firstMove();
        if (move == INVALID_MOVE) move = winMove();
        if (move == INVALID_MOVE) move = counterMove();
        if (move == INVALID_MOVE) move = specialMove();
        if (move == INVALID_MOVE) move = randomMove();
        return move;
    }
    
    private int firstMove() {
        boolean isFirstMove = true;
        for (int tileNumber = 1; tileNumber <= NUMBER_OF_TILES; tileNumber++) {
            if (!grid[tileNumber].equals("")){
                isFirstMove = false;
                break;
            }
        }
        if (isFirstMove) {
            if (random.nextInt(3) == 0) { //33.33% chance (random move)
                return 1 + random.nextInt(NUMBER_OF_TILES); // possible values: 1-9
            } else { //66.67% chance
                return CENTER_TILE;
            }
        }
        return INVALID_MOVE;
    }

    private int winMove() {
        int first, second, third;
        for (String sequence : sequences) {
            first = Integer.parseInt("" + sequence.charAt(0));
            second = Integer.parseInt("" + sequence.charAt(1));
            third = Integer.parseInt("" + sequence.charAt(2));
            if (grid[first].equals(computerSymbol) && grid[second].equals(computerSymbol) && grid[third].equals("")) {
                return third;
            }
        }
        return INVALID_MOVE;
    }

    private int counterMove() {
        int first, second, third;
        for (String sequence : sequences) {
            first = Integer.parseInt("" + sequence.charAt(0));
            second = Integer.parseInt("" + sequence.charAt(1));
            third = Integer.parseInt("" + sequence.charAt(2));
            if (grid[first].equals(playerSymbol) && grid[second].equals(playerSymbol) && grid[third].equals("")) {
                return third;
            }
        }
        return INVALID_MOVE;
    }

    private int specialMove() {
        int unfilledTiles = 0;
        for (int tileNumber = 1; tileNumber < NUMBER_OF_TILES; tileNumber++) {
            if (grid[tileNumber].equals("")) {
                unfilledTiles++;
            }
        }

        if (unfilledTiles == 8 && grid[5].equals(playerSymbol)) {
            int tileNumber = random.nextInt(4);
            switch (tileNumber) {
                case 0: return 1; //25% chance
                case 1: return 3; //25% chance
                case 2: return 7; //25% chance
                default: return 9; //25% chance
            }
        } else if ((unfilledTiles == 8 || unfilledTiles == 7) && grid[5].equals("")) {
            return 5;
        } else if (unfilledTiles == 7 && grid[5].equals(computerSymbol) && grid[3].equals("")) {
            return 3;
        } else if (unfilledTiles == 6 && grid[5].equals(computerSymbol) && grid[4].equals("")) {
            return 4;
        } else if (grid[5].equals(playerSymbol) && grid[1].equals(computerSymbol) && grid[9].equals(playerSymbol) && grid[3].equals("")) {
            return 3;
        } else {
            return INVALID_MOVE;
        }
    }

    private int randomMove() {
        int tileNumber = 1 + random.nextInt(9);
        if (grid[tileNumber].equals("")) {
            return tileNumber;
        } else {
            for (tileNumber = 1; tileNumber < grid.length; tileNumber++) {
                if (grid[tileNumber].equals("")) {
                    return tileNumber;
                }
            }
            return 0; //not possible
        }
    }
}
