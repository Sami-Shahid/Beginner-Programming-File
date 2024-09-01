
/*Base Calculator 
 Written by: Sami Shahid
 Due: 05/30/2023
 Program Description: This program will prompt the user for an equation which has input base, output base, math operation and 2 numbers in the input base.
 If format is incorrect then we reprompt the entire equation. If anything specific is invalid like a base thats not 2-10 we just reprompt that specific value.
 If everything is valid we convert the user's equation to base 10 and display it. Then show the output in base 10, then show it in their disired output base.
 Data Dictionary:
 
   Public variables:
    input: Stores input for if user wants to play or stop playing
    expression: Stores user's equation and splits it all by seperate information
 */

// class header
public class BaseCalculator {

  /*
   * Calculates users and returns output in base 10 and desired base
   * num1: stores first number
   * op: stores operation user wants to do
   * num2: stores second number
   * `` base: stores the base the user wants their output to be in
   * out: stores the calculated number in base 10
   */
  public static String mathOp(int num1, char op, int num2, int base) {
    // variable declaration
    int out;
    if (op == '+') {// checking if we are adding
      out = num1 + num2;// does required calculation
    } else if (op == '-') {// checking if we are subtracting
      out = num1 - num2;// does required calculation
    } else if (op == '*') {// checking if we are multiplying
      out = num1 * num2;// does required calculation
    } else if (op == '%') {// checking if we are using module
      if (num2 == 0) {// checking if we are doing module by 0
        return "= Undefined\n= Undefined"; // returning appropriate message for module by 0
      } else {
        out = num1 % num2;// calculation for module
      }
    } else {// all false so we are dividing
      if (num2 == 0) {// checking if we are dividing by 0
        return "= Undefined\n= Undefined";// returning appropriate message for division by 0
      } else {
        return "= " + (num1 / num2) + " remainder of " + (num1 % num2) + "\n= " + convertToBase(num1 / num2, base)
            + " remainder " + convertToBase(num1 % num2, base);// returning appropriate message for division with module

      }
    }
    return ("= " + out + "\n= " + convertToBase(out, base));// returning appropriate message
  }

  // Displays the user's equation, converts it to base 10, and shows the result in
  // the desired output base
  // arr: stores user's input all split in sections

  public static void display(String[] arr) {
    // Print the equation with the first number, base, math operation, second
    // number, and base
    System.out.printf("%s (base %d) %s %s (base %d)\n", arr[1], Integer.parseInt(arr[0]), arr[2], arr[3],
        Integer.parseInt(arr[0]));
    // Convert the first and second numbers to base 10 and print the equation in
    // base 10

    System.out.printf("Equation in base 10:\n= %d %s %d\n", toBase10(arrNum(arr[1]), Integer.parseInt(arr[0])), arr[2],
        toBase10(arrNum(arr[3]), Integer.parseInt(arr[0])));
    // Perform the math operation on the first and second numbers in base 10,
    // convert the result to the desired output base, and print it

    System.out.printf("%s (base %s)\n", mathOp(toBase10(arrNum(arr[1]), Integer.parseInt(arr[0])), arr[2].charAt(0),
        toBase10(arrNum(arr[3]), Integer.parseInt(arr[0])), Integer.parseInt(arr[4])), arr[4]);
  }

  // Takes user's number and the base it is in and converts it to base 10
  // num: stores users number, seperating each digit in a different index
  // base: stores users base
  // out: store user's new number in base 10
  public static int toBase10(int[] num, int base) {
    // variable initilization and declaration
    int out = 0;
    // loop through the array of digits in reverse order

    for (int i = num.length - 1; i >= 0; i--) {
      // Multiply each digit by the corresponding power of the base and add the result

      out += num[i] * Math.pow(base, num.length - 1 - i);
    }
    return out;// returning new number
  }

  // Takes users base 10 number and the base they want to convert it to and
  // convert it to that desired base
  // num: holds base 10 number user wants to convert
  // base: holds user's desired base
  // converted: hold's users new number in desired base
  public static String convertToBase(int num, int base) {
    // variable initialization and declaration
    String converted = "";
    // checking if users number is 0
    if (num == 0) {
      return "0";
    } else {
      // dividing the number by the base until it = 0
      for (int i = num; i != 0; i /= base) {
        // getting the remainder every time we divide by the base and adding that digit
        // to the start of the converted answer to convert it to users desired base
        // (Double dabble method)
        converted = (i % base) + converted;
      }
    }
    return converted;// returning new number

  }

  // Checks if the user wants the program to finish
  // str: holds user's input
  public static boolean isFinish(String str) {
    // Checking if user input says stop, ignoring the casing and returing if its
    // true or false
    return (str.toLowerCase().equals("stop"));
  }

  // Checks if the user has a valid base and returns if true or false
  // strBase: holds users base as a string data type
  // base: holds users base as an integer data type
  public static boolean validBase(String strBase) {
    // Checking if strBase has a valid length
    if (strBase.length() <= 0) {
      return true;// appropriate return statement if true
    }

    // Looping through each character in str base
    for (int i = 0; i < strBase.length(); i++) {
      // ensuring each character is a digit
      if (!(strBase.charAt(i) >= '0' && strBase.charAt(i) <= '9')) {
        return true;
      }
    }
    // getting base as an integer
    int base = Integer.parseInt(strBase);
    return !(base >= 2 && base <= 10);// Checking if its between 2 and 10 and returning if true or false
  }

  // Checking if user has a properly formatted input with spacing and characters
  // arr: holds user's equation as an array with everything split
  public static boolean checkSpace(String arr[]) {
    // Looping through each character in the array
    for (int i = 0; i < arr.length; i++) {
      // Making sure all strings in the array are not null, empty string, or only have
      // a space
      if (arr[i] == (null) || arr[i].equals("") || arr[i].equals(" ")) {
        return true;
      }
      //
      // looping through all character in the string
      for (int x = 0; x < arr[i].length(); x++) {
        // checking if there is a space anywhere in the string
        if (arr[i].charAt(x) == ' ') {
          return true;
        }
      }
    }
    return false;
  }

  // Checks if the user has a valid number that is in correct input base and isnt
  // negative
  // num: holds users number as a string
  // base: holds users input base
  // arrNum: holds users number as an array
  public static boolean validNum(String num, int base) {
    // Check if number is not blank
    if (num.length() <= 0) {
      return true;
    }
    // Loop through each digit in the users number
    for (int i = 0; i < num.length(); i++) {
      // making sure each character is a digit
      if (!(num.charAt(i) >= '0' && num.charAt(i) <= '9')) {
        return true;
      }
    }

    // variable initialization and declaration
    int arrNum[] = (arrNum(num));

    // Looping through each digit in the array
    for (int i = 0; i < arrNum.length; i++) {
      // Making sure each digit in the array is less than the base
      if (arrNum[i] >= base) {
        return true;
      }
    }
    return false;
  }

  // Makes sure that the users math operator is a valid operator
  // op: holds users inputted operator
  public static boolean validOp(String op) {
    // Makes sure the operator is only 1 character long
    if (op.length() != 1) {
      return true;
    }

    // makes sure the operator only consists of valid operator characters
    for (int i = 0; i < op.length(); i++) {
      if (!(op.charAt(i) == '+' || op.charAt(i) == '-' || op.charAt(i) == '*' || op.charAt(i) == '/'
          || op.charAt(i) == '%')) {
        return true;
      }
    }
    return false;
  }

  // Makes sure that the user is not going to get a negative output when
  // subtracting
  // num1: holds users first number
  // num2: holds users second number
  // op: holds the operator user is doing
  public static boolean validSubtraction(int num1, int num2, char op) {
    return (op == '-' && num1 < num2);// Making sure user is subtracting and second number is bigger than first and
                                      // returning if the subtraction valid or not
  }


  // Checks if the users input is valid and if something is invalid asks for the appropriate input to make it all valid
  //arr: holds users inputted expression
  public static String[] makeValid(String[] arr) {
    //run if formatting is invalid
    while (checkSpace(arr)) {
      System.out.println("Incorrect Format\nReinput your expression");
      arr = arrExpression(In.getString().trim());//Getting entire equation again
    }

    //run if user has invalid inputs base 
    while (validBase(arr[0])) {
      System.out.println("Invalid input base\nReinput your input base");
      arr[0] = In.getString();//Getting inputs base again
    }

    //run if users first number is invaid
    while (validNum(arr[1], Integer.parseInt(arr[0]))) {
      System.out.println("First number is invalid or not in base " + arr[0] + "\nReinput your first number");
      arr[1] = In.getString();//get first number
    }
    //run if user has invalid operator
    while (validOp(arr[2])) {
      System.out.println("Invalid Math operation\nReinput your math operator");
      arr[2] = In.getString();//get math operator again
    }
    //run if users second number is invaid
    while (validNum(arr[3], Integer.parseInt(arr[0]))) {
      System.out.println("Second number is invalid or not in base " + arr[0] + "\nReinput your second number");
      arr[3] = In.getString();//get seconde number
    }
    //run if user has invalid output base
    while (validBase(arr[4])) {
      System.out.println("Invalid output base\nReinput your output base");
      arr[4] = In.getString();//get output base again
    }
    //run if user has invalid subtraction(subtraction that has negative outcome)
    while (validSubtraction(Integer.parseInt(arr[1]), Integer.parseInt(arr[3]), arr[2].charAt(0))) {
      System.out.println("Subtraction cannot produce negative output\nReinput your first number");
      arr[1] = In.getString();//get first num
      //makes sure new first num is valid
      while (validNum(arr[1], Integer.parseInt(arr[0]))) {
        System.out.println("First number is invalid or not in base " + arr[0] + "\nReinput your first number");
        arr[1] = In.getString();//get it again if invalid
      }

    }
    return arr;//returns the new expression that is valid
  }

  //Converts a string that holds a number to an array that seperates all the digits as integers
  //num: holds users number
  //tempArr: holds the new array with seperated digits
  public static int[] arrNum(String num) {
    //Variable initialiation and declaration
    int[] tempArr = new int[num.length()];

    //loop through all digits in num
    for (int i = 0; i < num.length(); i++) {
      //add that digit into the array as an integer
      tempArr[i] = Integer.parseInt(num.substring(i, i + 1));
    }
    return tempArr;//return the new array
  }

  //Converts users string expression input to an array with all data past a space indexed (Basically splitting it)
  //str: holds users original expression input as a string
  //arrTemp: holds the new array that will hold the split expression
  //start: holds users last seen space
  //count: holds the index we are adding
  public static String[] arrExpression(String str) {
    //variable declaration and initialization
    String[] arrTemp = new String[5];
    int start = 0;
    int count = 0;

    //Loop through string expression until all indexes are filled
    for (int i = 0; i < str.length() && count < 5; i++) {
      //Check if we are at the end of the string
      if (i == str.length() - 1) {
        arrTemp[count] = str.substring(start, i + 1);//Last seen space to end of string

      //Checking if a space is seen in the program and that we are not on the last index
      } else if (str.charAt(i) == ' ' && count != 4) {
        arrTemp[count] = str.substring(start, i);//last seen space to new space
        start = i + 1;//updating last seen space
        count++;//updating index
      }

    }
    return arrTemp;//returning the split expression as an array
  }

  public static void main(String[] args) {
    //Variable declaration
    String input;
    String[] expression;

    //Do loop that reruns if user decides to play or play again
    do {
      System.out.println("Welcome to the different base calculator\nEnter (1) to use the calculator\nEnter (stop) to exit the program");
      input = In.getString();//Getting users input to see if they want to continue or end
      if (!(isFinish(input) || input.equals("1") || input.equals(null))) {//Checking if user has a valid input for if they want to continue or stop 
        System.out.println("Invalid input");//printing appropriate message
      } else if (!(isFinish(input))) {//Checking if user is continuing to play
        System.out.println("Enter the base for both integers, integer, operation, integer and output base (Ex: 5 14 + 12 2)");
        expression = arrExpression(In.getString().trim());//Getting users expression
        display(makeValid(expression));//Displaying the expression with a valid input
      }
    } while (!(isFinish(input)));
    System.out.println("Goodbye");//Printing appropriate message for when user wants to end

  }
}
    

