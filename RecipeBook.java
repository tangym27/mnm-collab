import java.util.*;

public class RecipeBook {

  private static ArrayList<Recipe> book = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void printIntro() {
    System.out.println("-----------------------------------------------");
    System.out.println("What would you like to do?");
    System.out.println("1 - Add a recipe");
    System.out.println("2 - Search for a recipe");
    System.out.println("3 - View all recipes");
    System.out.println("4 - I'm feeling hungry!");
    System.out.println("5 - Exit");
    System.out.println("-----------------------------------------------\n");
    System.out.print("Enter a number: ");
  }

  public static void main(String[] args) {
    System.out.println("-----------------------------------------------");
    System.out.println("Welcome to your recipe book!");

    // TODO: REMOVE THIS - USED FOR DEBUG/TESTING (Preloaded Recipes)
    preloadedRecipes();

    while (true) {
      printIntro();
      try {

        int val = Integer.parseInt(scanner.nextLine());
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Display value entered (DEBUG)
        // System.out.println("Value entered was " + val);

        // Add a recipe
        if (val == 1) {
          addRecipe();
        }

        // TODO: Search for a recipe
        else if (val == 2) {
          // ask user if they want to view recipe info
          System.out.print(
              "Enter the name of the recipe you would like to search for: ");
          String recipeDetails = scanner.nextLine();
          System.out.println();

          ArrayList<Recipe> bookClone = new ArrayList<Recipe>();
          for (Recipe recipe : book) {
            if (recipe.name.matches("(?i)(" + recipeDetails + ").*")) {
              bookClone.add(recipe);
            }
          }

          if (bookClone.isEmpty() == true) {
            System.out.println("-----------------------------------------------");
            System.out.println("No matches found. Enter any key to return home.");
            scanner.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
          } else {
            System.out.println("-----------------------------------------------");
            System.out.println("Matches found! Enter the corresponding recipe number.");

            for (Recipe x : bookClone) {
              System.out.println(x);
            }

            String userChoice = scanner.nextLine();

            for (Recipe x : book) {
              if (x.number == Integer.parseInt(userChoice)) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Viewing recipe: " + x.name);
                showRecipeInfo(x.name);
              }
            }
          }
        }

        // View all recipes
        else if (val == 3) {
          showAll();
          System.out.print("Would you like to view a recipe's details? (Y/N) ");
          String recipeDetails = scanner.nextLine();
          System.out.println();

          // User accepts view
          if (recipeDetails.equalsIgnoreCase("Y") ||
              recipeDetails.equalsIgnoreCase("Yes")) {
            System.out.print("Enter the recipe's corresponding number: ");
            int rec_num = Integer.parseInt(scanner.nextLine());

            for (Recipe x : book) {
              if (x.number == rec_num) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Viewing recipe: " + x.name);
                showRecipeInfo(x.name);
              }
            }
          }

          // User declines view
          else if (recipeDetails.equalsIgnoreCase("N") ||
                   recipeDetails.equalsIgnoreCase("No")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
          }

          // Return Home
          else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(
                "That is not a valid option, returning to home.");
          }
        }

        // Random number
        else if (val == 4) {
          int rand = (int) Math.floor(Math.random()* book.size());
          Recipe x = book.get(rand);
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.println("Viewing recipe: " + x.name);
          showRecipeInfo(x.name);

        }

        // Exit
        else if (val == 5){
          System.exit(0);
        }

        else {
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.println();
          System.out.println("That is not a valid option, please try again.\n");
        }
      }

      catch (Exception e) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        System.out.println("That is not a valid option, please try again.\n");
      }
    }
  }

  // Add Recipe
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

      // Book successfully added
      if (book.add(r)) {
        System.out.println();
        System.out.println("The recipe " + name + " has been added!");
        System.out.println();
        System.out.print("Enter any key to continue: ");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }

      // Book not added
      else {
        System.out.println("Issue adding recipe. Please try again later.");
      }
    } catch (Exception e) {
      System.out.println("Issue adding recipe. Please try again later.");
    }
  }

  // Test Recipes
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
    Recipe r2 =
        new Recipe("Cake 2", "Ice Cream", "eggs2, butter2", instructions2, 2);
    book.add(r2);
    Recipe r3 =
        new Recipe("Cake 3", "Mochi", "eggs3, butter3", instructions3, 3);
    book.add(r3);
  }

  // List Recipes
  private static void showAll() {
    System.out.println("-----------------------------------------------");

    System.out.println("Recipes: ");
    for (Recipe x : book) {
      System.out.println(x);
    }

    System.out.println("-----------------------------------------------\n");
  }

  // Display Recipe Info
  private static void showRecipeInfo(String name) {
    System.out.println();
    System.out.println("-----------------------------------------------");
    System.out.println("Choose how you would like to view the recipe:");
    System.out.println("0 - View the recipe in its' entirety");
    System.out.println("1 - View the recipe's instructions step-by-step");
    System.out.println("-----------------------------------------------");
    System.out.print("Enter your viewing choice: ");
    int view_choice = Integer.parseInt(scanner.nextLine());
    System.out.println("-----------------------------------------------");

    // User Views Recipe
    if (view_choice == 0) {
      for (Recipe x : book) {
        if (name.equalsIgnoreCase(x.name)) {
          System.out.println("Name: " + x.name);
          System.out.println();
          System.out.println("Description: " + x.description);
          System.out.println();
          System.out.println("Ingredients: " + x.ingredients);
          System.out.println();
          System.out.println("Instructions:");
          for (String instr : x.instructions) {
            System.out.println("• " + instr);
          }
        }
      }
      System.out.println();
      System.out.print("Enter any key to continue: ");
      scanner.nextLine();
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }

    // User Views Recipe Instructions
    else if (view_choice == 1) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("Viewing recipe: " + name);
      System.out.println("-----------------------------------------------");

      System.out.println("Instructions: (Enter any key to continue)");
      for (Recipe x : book) {
        if (name.equalsIgnoreCase(x.name)) {
          int instr_count = 1;
          for (String instr : x.instructions) {
            System.out.println(instr_count + ". " + instr);
            instr_count++;
            scanner.nextLine();
          }
        }
      }

      System.out.println("You're finished! Enter any key to continue.");
      scanner.nextLine();
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
    else {
      System.out.println("That is not a valid option, please try again. Defaulting to menu.");

    }
  }
}
