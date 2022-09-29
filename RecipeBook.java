import java.util.*;
public class RecipeBook{

  public static void printIntro(){
      System.out.println("What would you like to do? Enter a number:");
      System.out.println("1 - Add a recipe");
      System.out.println("2 - Search for a recipe");
      System.out.println("3 - View all recipes");
      System.out.println("4 - Exit");
      System.out.println("-------------------------------\n\n");
  }

  public static void main(String[] args) {
    System.out.println("Welcome to your recipe book!");
    System.out.println("-------------------------------\n");

    while(true){

      Scanner scanner = new Scanner(System.in);

      printIntro();

      try {

        int val = Integer.parseInt(scanner.nextLine());
        System.out.println("Value entered was " + val);
        // TODO: Add a recipe
        if (val == 1){

        }
        // TODO: Search for a recipe
        else if (val == 2){

        }
        // TODO: View all recipes
        else if (val == 3){

        }
        // Exit
        else if (val == 4){
          System.exit(0);
        }

        else {
          System.out.println("That is not a valid option please try again.\n\n");
        }
      }
      catch (Exception e){
          System.out.println("That is not a valid option please try again.\n\n");

      }

    }

  }

}
