import java.util.*;
public class RecipeBook {

  private static ArrayList<Recipe> book = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void printIntro() {
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

    while (true) {
      printIntro();
      try {

        int val = Integer.parseInt(scanner.nextLine());
        System.out.println("Value entered was " + val);
        // TODO: Add a recipe
        if (val == 1) {
          addRecipe();
        }
        // TODO: Search for a recipe
        else if (val == 2) {

          // ask user if they want to view recipe info
          System.out.println("Would you like to view this recipe? (yes/no) ");
          String recipeDetails = scanner.nextLine();
          if (recipeDetails.equalsIgnoreCase("yes")) {
            // showRecipeInfo(name);
          }

        }
        // TODO: View all recipes
        else if (val == 3) {
          showAll();
          System.out.println(
              "Would you like to view a recipe's details? (yes/no) ");
          String recipeDetails = scanner.nextLine();
          if (recipeDetails.equalsIgnoreCase("yes")) {
            System.out.println(
                "Please enter the recipe's corresponding number: ");
            int rec_num = Integer.parseInt(scanner.nextLine());

            for (Recipe x : book) {
              if (x.number == rec_num) {
                showRecipeInfo(x.name);
              }
            }
          }

        }
        // Exit
        else if (val == 4) {
          System.exit(0);
        }

        else {
          System.out.println(
              "That is not a valid option please try again.\n\n");
        }
      } catch (Exception e) {
        System.out.println("That is not a valid option please try again.\n\n");
      }
    }
  }

  public static void addRecipe() {
    try {

      System.out.println("Adding a recipe!\n");
      System.out.print("Enter the recipe name (ex: 'Cake'): ");
      String name = scanner.nextLine();
      System.out.print("Enter the recipe's description': ");
      String description = scanner.nextLine();
      System.out.print(
          "Enter the ingredients using commas (ex: 'eggs, chocolate'): ");
      String ingredients = scanner.nextLine();
      System.out.print(
          "Enter the instructions using commas (ex: 'Mix the eggs, Bake at 350, Enjoy'): ");
      String[] instructionsArray = scanner.nextLine().split(",");
      int current_num = 1;
      for (Recipe x : book) {
        if (x.number > current_num) {
          current_num = x.number;
        }
      }
      Recipe r = new Recipe(name, description, ingredients, instructionsArray,
                            (current_num + 1));
      if (book.add(r)) {
        System.out.println("The recipe on " + name + " has been added!");
      } else {
        System.out.println("Issue adding recipe. Please try again later.");
      }
    } catch (Exception e) {
      System.out.println("Issue adding recipe. Please try again later.");
    }
  }

  private static void preloadedRecipes() {
    String[] instructions = new String[] {
        "Cook spaghetti in boiling water", "melt butter in medium frying pan",
        "add black pepper to butter", "drain pasta but keep some pasta water",
        "add pasta and pasta water to pan with butter and pepper"};
    String[] instructions2 =
        new String[] {"Mix the stuff2", "Live, laugh, and love", "Enjoy"};
    String[] instructions3 =
        new String[] {"Mix the stuff3", "Live, laugh, and love", "Enjoy"};

    Recipe r = new Recipe(
        "Cacio e pepe", "butter and pepper spaghetti dish",
        "spaghetti, butter, ground black pepper, grated pecorino or parmesan",
        instructions, 1);
    book.add(r);
    Recipe r2 = new Recipe("cake2", "popular dessert", "eggs2, butter2",
                           instructions2, 2);
    book.add(r2);
    Recipe r3 = new Recipe("cake3", "popular dessert", "eggs3, butter3",
                           instructions3, 3);
    book.add(r3);
  }

  private static void showAll() {
    for (Recipe x : book) {
      System.out.println(x);
    }
  }

  private static void showRecipeInfo(String name) {
    System.out.println("Please choose how you would like to view the recipe.");
    System.out.println(
        "Enter 0 to view the recipe in its entirety or enter 1 to view the recipe's instructions step-by-step: ");
    int view_choice = Integer.parseInt(scanner.nextLine());
    if (view_choice == 0) {
      for (Recipe x : book) {
        if (name.equalsIgnoreCase(x.name)) {
          System.out.println("Name: " + x.name);
          System.out.println("Description: " + x.description);
          System.out.println("Ingredients: " + x.ingredients);
          System.out.println("Instructions:");
          for (String instr : x.instructions) {
            System.out.println(instr);
          }
        }
      }
    } else if (view_choice == 1) {

      System.out.println(
          "Instructions: (Please hit enter to see next instruction) \n");
      for (Recipe x : book) {
        if (name.equalsIgnoreCase(x.name)) {
          int instr_count = 1;
          for (String instr : x.instructions) {
            System.out.println(instr_count + ". " + instr);
            instr_count++;
            String next_instr = scanner.nextLine();
          }
        }
      }
    }
  }
}
