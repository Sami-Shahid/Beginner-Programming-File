/*
 
ICS3U1
Ms. Karasinska
Sami Shahid
June 16, 2023
Description:
This is a simple strategy-based game implemented using programming concepts. It is played on a grid-type board with two players. Each player has their own grid where they place their boats, which are hidden from the opponent.
The grid is represented as a 7x7 array for each player, and there is an additional 7x7 array for each player to track their shots and related information.
The objective of the game is to sink all of your opponent's boats before they sink yours. Both players take turns shooting at spots on the opponent's grid. They receive feedback on whether their shot hit a boat, sunk a boat, or missed.
To sink a boat, all cells of the boat must be hit. Each boat is represented by its size and position.
Each player has a limited number of shots per turn, usually three. The game continues until one player loses all their boats.
The game incorporates basic programming concepts like arrays, turn-based systems, and condition checks to provide an enjoyable gaming experience.


Variable dictionary:

Main and public variables: 
  p1boats: holds coordinates for player 1's boats
  p2boats: holds coordinates for player 2's boats
  p1BoatGrid: holds grid that holds and displays player 1's boats
  p2BoatGrid: holds grid that holds and displays player 2's boats
  
Method variables and parameters:
  input: holds the coordinates the user inputs
  point: holds points of coordinates
  length: holds the length of the boat
  grid: holds the grid which holds the user's boats
  boats: holds the coordinates of each point on each boat
  x: holds x coordinate
  y: holds y coordinate
  n: holds which boat is being checked
  attackGrid: holds all of user's attacks positions and hit, miss or sink
  hitChar: holds character for hit point or missed point
  check: holds if the boat is sunk or not
  isnotvalid: holds the validity of boat palcement based on orientation and coordinates
*/
class Battleship {
  // Declaring public variables
  public static String p1boats[][] = { { "0" }, { "0" }, { "0", "0", "0" }, { "0", "0", "0" },
      { "0", "0", "0", "0", "0" } };
  public static String p2boats[][] = { { "0" }, { "0" }, { "0", "0", "0" }, { "0", "0", "0" },
      { "0", "0", "0", "0", "0" } };

  // This method is used to print the start screen
  public static void startScreen() {

    System.out.println(
        "=================================================================================================================\n");
    System.out.println(
        "  __          __  _                            _______      ____        _   _   _       _____ _     _       _ ");
    System.out.println(
        "  \\ \\        / / | |                          |__   __|    |  _ \\      | | | | | |     / ____| |   (_)     | |");
    System.out.println(
        "   \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___     | | ___   | |_) | __ _| |_| |_| | ___| (___ | |__  _ _ __ | |");
    System.out.println(
        "    \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\    | |/ _ \\  |  _ < / _` | __| __| |/ _ \\\\___ \\| '_ \\| | '_ \\| |");
    System.out.println(
        "     \\  /\\  /  __/ | (_| (_) | | | | | |  __/    | | (_) | | |_) | (_| | |_| |_| |  __/____) | | | | | |_) |_|");
    System.out.println(
        "      \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|    |_|\\___/  |____/ \\__,_|\\__|\\__|_|\\___|_____/|_| |_|_| .__/(_)");
    System.out.println(
        "                                                                                                    | |      ");
    System.out.println(
        "                                                                                                    |_|      ");
    System.out.println("                                                   |__");
    System.out.println("                                                   |\\/");
    System.out.println("                                                   ---");
    System.out.println("                                                   / | [");
    System.out.println("                                            !      | |||");
    System.out.println("                                          _/|     _/|-++'");
    System.out.println("                                      +  +--|    |--|--|_ |-");
    System.out.println("                                   { /|__|  |/\\__|  |--- |||__/");
    System.out.println("                                  +---------------___[}-_===_.'____               /\\");
    System.out.println("                              ____`-' ||___-{]_| _[}-  |     |_[___\\==--          \\/   _");
    System.out.println("               __..._____--==/___]_|__|_____________________________[___\\==--___,-----' .7");
    System.out.println("              |                                                                   BB-61/");
    System.out.println("               \\_______________________________________________________________________|");
    System.out.println("");
    System.out.println("");
    System.out.println("");
    System.out
        .println("            ____                                 _              _              _             _   ");
    System.out
        .println("           |  _ \\ _ __ ___  ___ ___    ___ _ __ | |_ ___ _ __  | |_ ___    ___| |_ __ _ _ __| |_ ");
    System.out.println(
        "           | |_) | '__/ _ \\/ __/ __|  / _ \\ '_ \\| __/ _ \\ '__| | __/ _ \\  / __| __/ _` | '__| __|");
    System.out.println(
        "           |  __/| | |  __/\\__ \\__ \\ |  __/ | | | ||  __/ |    | || (_) | \\__ \\ || (_| | |  | |_ ");
    System.out.println(
        "           |_|   |_|  \\___||___/___/  \\___|_| |_|\\__\\___|_|     \\__\\___/  |___/\\__\\__,_|_|   \\__|");

    System.out.println(
        "\n\n=================================================================================================================");
    In.getString();
    System.out.println("\033[H\033[2JRules: The game is played on a grid-type board with two players.Each player has their own grid to place their boats, which are hidden from the opponent. \nThe grid consists of a 7x7 layout for boat placement and another 7x7 layout for tracking shots and associated information. \nThe objective of the game is to sink all of your opponent's boats before they sink yours.\n Each player has five boats:\n One 5x1 long boat\n Two 3x1 medium boats\n Two 1x1 small boats \nThere is a planning phase where each player strategically places their boats on their grid. During gameplay, each player takes turns shooting spots on the opponent's grid. \nA player is notified whether their shot hit a boat, sunk a boat, or missed. To sink a boat, every cell of the boat must be hit. Each player gets to shoot three times per turn. \nThe game continues until one player loses all their boats. \nThe player who sinks all of their opponent's boats first wins the game.\n\n Now press enter to begin!!!");
    In.getString();
    System.out.println("\033[H\033[2J");

  }

  // Creates and returns a 2d array with spaces in all indices
  // tempArr: holds 2d array with the spaces in each indice and is returned
  public static char[][] makePlayerBoatGrid() {

    // variable declaration
    char[][] tempArr = new char[7][7];

    // looping through entire array
    for (int i = 0; i < 7; i++) {
      for (int x = 0; x < 7; x++) {
        // giving each indice a space charadcter
        tempArr[i][x] = ' ';
      }
    }

    return tempArr;
  }

  // This method prints a 2d array in a grid format
  // tempArr: holds the 2d that will be printed
  // board: holds the 2d array in grid format in a string
  public static void printGrid(char[][] tempArr) {

    // variable declaration and initilization
    String board = "";
    board += "   ⏐  1  ⏐  2  ⏐  3  ⏐  4  ⏐  5  ⏐  6  ⏐  7  ⏐      \n___⏐_____⏐_____⏐_____⏐_____⏐_____⏐_____⏐_____⏐____\n   ⏐     ⏐     ⏐     ⏐     ⏐     ⏐     ⏐     ⏐   \n";
    // looping through entire 2d array and adding proper formatting
    for (int i = 0; i < 7; i++) {
      board += String.format(" %d ⏐  ", i + 1);
      for (int x = 0; x < 7; x++) {
        board += String.format("%c  ⏐  ", tempArr[i][x]);

      }
      board += String.format(
          "%d  \n___⏐_____⏐_____⏐_____⏐_____⏐_____⏐_____⏐_____⏐____\n   ⏐     ⏐     ⏐     ⏐     ⏐     ⏐     ⏐     ⏐    \n",
          i + 1);
    }
    board += "   ⏐  1  ⏐   2 ⏐   3 ⏐  4  ⏐  5  ⏐  6  ⏐  7  ⏐      \n\nLegend:  * - Placed boat     X - Hit boat shot     O - Missed shot     # - Sunken boat\n\n";
    // printing the 2d array in the grid format
    System.out.println(board);
  }

  // Checks if the user inputs a valid attack on the board
  public static boolean validShot(String input) {
    // return condition
    return (input == null || input.length() != 2 || validShot(input.charAt(0)) || validShot(input.charAt(1)));
  }

  // Method overload for valid shot to see if they attack
  public static boolean validShot(char point) {
    // return condition
    return (point > '7' || point < '1');
  }

  // Checks if user inputted a valid input for their boats placement
  public static boolean validPlacementInput(String input) {
    // return condition
    return (input == null || input.length() != 3
        || !(input.substring(0, 1).equalsIgnoreCase("h") || input.substring(0, 1).equalsIgnoreCase("v"))
        || validShot(input.substring(1, 3)));
  }

  // sets up user's boats on the grid and ensures they are valid
  public static char[][] setUpBoat(char[][] grid, String[][] boat) {

    // variable declaration and initilization
    String input = "";
    int length = 0;
    boolean isnotvalid = false;

    // Looping through each boat
    for (int i = 0; i < boat.length; i++) {
      printGrid(grid);
      length = boat[i].length;

      // Getting user coordinate iinput
      System.out.printf("Boats left: %d\nCurrent input boat's length: %d\n", 5 - i, length);
      input = In.getString();

      // Reloop if invalid input
      while (validPlacementInput(input)) {
        System.out.println("Invalid input or format try again (Example: V43)");
        input = In.getString();
      }

      // Checking if user is placing boat horizontally
      if (input.charAt(0) == 'h' | input.charAt(0) == 'H') {
        isnotvalid = validBoatPlacementHorizontal(length, input.charAt(1) - '0', input.charAt(2) - '0', grid);
        System.out.println('h');
      } else {
        isnotvalid = validBoatPlacementVertical(length, input.charAt(1) - '0', input.charAt(2) - '0', grid);
      }

      // Reloop if boat is off grid or touching another boat
      while (isnotvalid) {
        System.out.println(
            "Boat is off the grid or is overlapping with another boat, try again (Example: V43)");
        input = In.getString();

        // Reloop if user input is invalid
        while (validPlacementInput(input)) {
          System.out.println("Invalid input or format try again (Example: V43)");
          input = In.getString();
        }
        if (input.charAt(0) == 'h' | input.charAt(0) == 'H') {
          isnotvalid = validBoatPlacementHorizontal(length, input.charAt(1) - '0', input.charAt(2) - '0', grid);
        } else {
          isnotvalid = validBoatPlacementVertical(length, input.charAt(1) - '0', input.charAt(2) - '0', grid);
        }

      }
      //checking if boat should be places horizontally or vertically
      if (input.charAt(0) == 'h' | input.charAt(0) == 'H') {
        // updating the grid with boats
        grid = placeBoatHorizontal(length, input.charAt(1) - '0', input.charAt(2) - '0', grid, boat, i);
        
      } else {
        // updating the grid with boats
        grid = placeBoatVertical(length, input.charAt(1) - '0', input.charAt(2) - '0', grid, boat, i);
      }
    }

    // returning new array
    return grid;
  }

  // Checks if the vertical boat inputted is not overlapping any other boat and is
  // on grid
  public static boolean validBoatPlacementVertical(int length, int x, int y, char[][] grid) {

    // looping through each coordinate on the boat
    for (int i = -1 * length / 2; i <= length / 2; i++) {

      // Checking if boat is on grid
      if (y - i > 7 || y - i < 1) {
        return true;
      }

      // Checking if boat is overlapping
      if (grid[y - 1 - i][x - 1] == '*') {
        return true;
      }
    }

    return false;
  }

  // Places boat vertically based on user input
  public static char[][] placeBoatVertical(int length, int x, int y, char[][] grid, String[][] boat, int n) {

    // loop through each coordinate on the boat
    for (int i = -1 * length / 2; i <= length / 2; i++) {

      // place * on that point of the grid
      grid[y - 1 - i][x - 1] = '*';

      // update coordinate in boats array to keep track of coordinate on each boat
      boat[n][i + length / 2] = (x - 1) + "" + (y - i - 1);
    }

    return grid;
  }

  // Places boat horizontally based on user input
  public static char[][] placeBoatHorizontal(int length, int x, int y, char[][] grid, String[][] boat, int n) {

    // loop through each coordinate on the boat
    for (int i = -1 * length / 2; i <= length / 2; i++) {

      // place * on that point of the grid
      grid[y - 1][x - 1 - i] = '*';

      // update coordinate in boats array to keep track of coordinate on each boat
      boat[n][i + length / 2] = (x - i - 1) + "" + (y - 1);
    }
    return grid;
  }

  // Checks if the horizontal boat inputted is not overlapping any other boat and
  // is on grid
  public static boolean validBoatPlacementHorizontal(int length, int x, int y, char[][] grid) {

    // looping through each coordinate on the boat
    for (int i = -1 * length / 2; i <= length / 2; i++) {

      // checking if all coordinates are on grid
      if (x - i > 7 || x - i < 1) {
        return true;
      }

      // checking if each coordinate is not overlapping
      if (grid[y - 1][x - i - 1] == '*') {
        return true;
      }
    }
    return false;
  }

  // Checks if any of the user's have won based on their attacks and opponents
  // boat coordinates
  public static boolean isWin(String[][] boat, char[][] attackGrid) {

    // loop through each boat coordinate
    for (int i = 0; i < boat.length; i++) {
      for (int x = 0; x < boat[i].length; x++) {

        // check if each boat coordinate shows its sunked on the attack grid
        if ((attackGrid[boat[i][x].charAt(1) - '0'][boat[i][x].charAt(0) - '0'] != '#')) {
          return false;

        }
      }
    }
    return true;
  }

  // Checks if the user is shooting in a empty square that they haven't shot in
  // before
  public static boolean validShotPlacement(char[][] attackGrid, int x, int y) {

    // Return if that spot is not empty
    return (attackGrid[y][x] != ' ');
  }

  // Checks if the user has hit a boat or missed a boat and updates attack grid
  // based upon that
  public static char[][] hitOrMiss(char[][] attackGrid, String[][] boats, int x, int y) {

    // variable declation and initilization
    char hitChar = 'O';

    // loop through each boat coordinate
    for (int i = 0; i < boats.length; i++) {
      for (int n = 0; n < boats[i].length; n++) {

        // check if the boat coordinate matches up with the users attack coordinate
        if (boats[i][n].equals((x - 1) + "" + (y - 1))) {
          hitChar = 'X';
        }
      }
    }
    // updated grid with hit or miss
    attackGrid[y - 1][x - 1] = hitChar;
    return attackGrid;
  }

  // checks if there are any boats that have all points hit on them and should
  // display they have sunk
  public static char[][] sinkBoat(char[][] attackGrid, String[][] boats) {

    // variale declaration
    boolean check;

    // Loop through each point on the boat
    for (int i = 0; i < boats.length; i++) {
      check = true;
      for (int n = 0; n < boats[i].length; n++) {

        // checking if each coordinate on a both displays that its been hit
        if (attackGrid[boats[i][n].charAt(1) - '0'][boats[i][n].charAt(0) - '0'] != 'X') {
          check = false;
        }
      }
      // Runs if the boat is sunked then replaces all the hit points of a boat with a
      // sunked sign
      if (check) {
        for (int n = 0; n < boats[i].length; n++) {

          // replaces all hit points with sunked point
          attackGrid[boats[i][n].charAt(1) - '0'][boats[i][n].charAt(0) - '0'] = '#';

        }
      }
    }
    return attackGrid;
  }

  // Gets the user coordinate for their attack and updates the grid based on the
  // attack coordinate
  public static char[][] getAttack(char[][] attackGrid, String[][] boats) {

    // variable declaration
    String input;

    // loop that runs 3 times or until a user wins
    for (int i = 1; i <= 3 && !(isWin(boats, attackGrid)); i++) {

      //getting user input
      printGrid(attackGrid);
      System.out.println("Shots left: " + (4-i));
      input = In.getString();

      // Rerun if user has invalid input
      while (validShot(input)) {
        System.out.println("Invalid input or format, try again (Example: 43)");
        input = In.getString();
      }

      // Rerun if user has already shot in this spot
      while (validShotPlacement(attackGrid, input.charAt(0) - '0' - 1, input.charAt(1) - '0' - 1)) {
        System.out.println("You already shot here, try again");
        input = In.getString();

        // Rerun if user has invalid input
        while (validShot(input)) {
          System.out.println("Invalid input or format, try again (Example: 43)");
          input = In.getString();
        }
      }
      // update grid for hit or miss
      attackGrid = hitOrMiss(attackGrid, boats, input.charAt(0) - '0', input.charAt(1) - '0');

      // update grid for sunken ships
      attackGrid = sinkBoat(attackGrid, boats);
    }

    printGrid(attackGrid);
    return attackGrid;
  }

  // Alternates the turns for player 1 and 2 for their attacking turns and ends
  // when one of them win
  public static void playGame(char[][] p1BoatGrid, char[][] p2BoatGrid) {
    // Creating empty arrays that will be used to hold both player's attack grids
    char[][] p1AttackGrid = makePlayerBoatGrid();
    char[][] p2AttackGrid = makePlayerBoatGrid();

    // Rerun if nobody has won
    while (!(isWin(p1boats, p2AttackGrid) || isWin(p2boats, p1AttackGrid))) {
      System.out.println(
          "\u001B[31mPlayer 1\u001B[0m turn, input x,y coordinates in format of xy, you have 3 shots before you switch turns \n(Example: 34)");

      // Getting player 1's attacks
      p1AttackGrid = getAttack(p1AttackGrid, p2boats);

      // Checking if player 1 has won
      if (!(isWin(p2boats, p1AttackGrid))) {
        System.out.println("Player 2 turn, input x,y coordinates 3 times in format of xy\n (Example: 34)");

        // Getting player 2's attacks
        p2AttackGrid = getAttack(p2AttackGrid, p1boats);
      }

    }
    // Checking who won
    if (isWin(p2boats, p1AttackGrid)) {
      System.out.println("\u001B[31mPlayer 1\u001B[0m wins!");
    } else {
      System.out.println("\u001B[34mPlayer 2\u001B[0m wins!");
    }
  }

  public static void main(String[] args) {
    // Display start screen
    startScreen();
    String playAgain;
    do {
            System.out.println("\033[H\033[2J");

      // Declaring and initilizating empty grids for the boats
      char[][] p1BoatGrid = makePlayerBoatGrid();
      char[][] p2BoatGrid = makePlayerBoatGrid();
      System.out.println(
          "\u001B[31mPlayer 1\u001B[0m, please input the orientation of your boat (V for vertically, H for horizontally), then input your x and then y coordinate (1-7) for the centre of the boat\nExample: V43 (V for vertical, 4 for x coordinate, 3 for y coordinate)\n");

      // Setting up player 1's boat
      p1BoatGrid = setUpBoat(p1BoatGrid, p1boats);
      printGrid(p1BoatGrid);

      // Move on to player 2 when player 1 is done
      System.out.println("\u001B[31mPlayer 1\u001B[0m, press enter to hide your boats from \u001B[34mPlayer 2\u001B[0m and start their set up");
      In.getString();
      System.out.println("\033[H\033[2J");
      System.out.println(
          "\u001B[34mPlayer 2\u001B[0m, please input the orientation of your boat (V for vertically, H for horizontally), then input your x and then y coordinate (1-7) for the centre of the boat\nExample: V43 (V for vertical, 4 for x coordinate, 3 for y coordinate)\n");

      // setting up player 2's boat
      p2BoatGrid = setUpBoat(p2BoatGrid, p2boats);
      printGrid(p2BoatGrid);

      // start game when player 2 is done and ready
      System.out.println("\u001B[34mPlayer 2\u001B[0m, press enter to hide your boats and start the game");
      In.getString();
      System.out.println("\033[H\033[2J");

      // Starting game
      playGame(p1BoatGrid, p2BoatGrid);

      // Getting user input for if they want to play again
      System.out.println("Do you want to play again? (Y for yes, N for no)");
      playAgain = In.getString().toLowerCase();
      // rerun if its invalid
      while (playAgain == null || playAgain.equals("") || (playAgain.charAt(0) != 'y' && playAgain.charAt(0) != 'n')
          || playAgain.length() > 1) {
        System.out.println("Invalid input");
        playAgain = In.getString().toLowerCase();
      }

      // Loop entire program again if they want to play again
    } while (playAgain.equals("y"));
    System.out.println("Thanks for playing!");
  }
}
