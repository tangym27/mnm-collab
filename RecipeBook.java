import java.util.*;
public class RecipeBook{

  private static ArrayList<Recipe> book = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void printIntro(){
      System.out.println("\n-----------------------------------------\n");
      System.out.println("What would you like to do? Enter a number:");
      System.out.println("1 - Add a recipe");
      System.out.println("2 - Search for a recipe");
      System.out.println("3 - View all recipes");
      System.out.println("4 - Exit");
      System.out.println("\n-----------------------------------------\n");
  }

  public static void main(String[] args) {
    System.out.println("Welcome to your recipe book!");

    // TODO: REMOVE THIS - USED FOR DEBUG/TESTING
    preloadedRecipes();

    while(true){
      printIntro();
      try {

        int val = Integer.parseInt(scanner.nextLine());
        System.out.println("Value entered was " + val);
        // TODO: Add a recipe
        if (val == 1){
          addRecipe();
        }
        // TODO: Search for a recipe
        else if (val == 2){

        }
        // TODO: View all recipes
        else if (val == 3){
          showAll();
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

  public static void addRecipe(){
    try{

      System.out.println("Adding a recipe!\n");
      System.out.print("Enter the recipe name (ex: 'Cake'): ");
      String name = scanner.nextLine();
      System.out.print("Enter the ingredients using commas (ex: 'eggs, chocolate'): ");
      String ingredients = scanner.nextLine();
      System.out.print("Enter the instructions using commas (ex: 'Mix the eggs, Bake at 350, Enjoy'): ");
      String[] instructionsArray = scanner.nextLine().split(",");
      Recipe r = new Recipe(name, ingredients, instructionsArray);
      if (book.add(r)){
        System.out.println("The recipe on " + name + " has been added!");
      } else{
        System.out.println("Issue adding recipe. Please try again later.");
      }
    } catch(Exception e){
      System.out.println("Issue adding recipe. Please try again later.");
    }

  }

  private static void preloadedRecipes(){
    String[] instructions = new String[]{"Mix the stuff", "Live, laugh, and love", "Enjoy"};
    String[] instructions2 = new String[]{"Mix the stuff2", "Live, laugh, and love", "Enjoy"};
    String[] instructions3 = new String[]{"Mix the stuff3", "Live, laugh, and love", "Enjoy"};

    Recipe r = new Recipe("cake", "eggs, butter", instructions);
    book.add(r);
    Recipe r2 = new Recipe("cake2", "eggs2, butter2", instructions2);
    book.add(r2);
    Recipe r3 = new Recipe("cake3", "eggs3, butter3", instructions3);
    book.add(r3);
  }

  private static void showAll(){
    for (Recipe x : book){
      System.out.println(x);
    }
  }

}
