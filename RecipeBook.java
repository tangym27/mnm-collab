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

          // Create temporary storage to store matches
          ArrayList<Recipe> bookClone = new ArrayList<Recipe>();
          for (Recipe recipe : book) {
            if (recipe.name.matches("(?i)(" + recipeDetails + ").*")) {
              bookClone.add(recipe);
            }
          }

          // No matches found
          if (bookClone.isEmpty() == true) {
            System.out.println("-----------------------------------------------");
            System.out.println("No matches found. Enter any key to return home.");
            scanner.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
          }
          else {
            System.out.println("-----------------------------------------------");
            System.out.println("Matches found!");

            for (Recipe x : bookClone) {
              System.out.println(x);
            }
            System.out.println("-----------------------------------------------");
            System.out.println("Please enter the recipe number of your choice: ");
            String userChoice = scanner.nextLine();

            // give user options to view detials or edit recipe
            System.out.println("");
            System.out.println("-----------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1 - View recipe details");
            System.out.println("2 - Edit the recipe");
            System.out.println("3 - Exit");
            System.out.println("-----------------------------------------------");
            System.out.print("Enter a number: ");


            int view_edit = Integer.parseInt(scanner.nextLine());
            // if user wants to view details
            if (view_edit == 1) {
              for (Recipe x : book) {
              if (x.number == Integer.parseInt(userChoice)) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Viewing recipe: " + x.name);
                showRecipeInfo(x.name);
                }
              }
            }
            // if user wants to edit details
            else if (view_edit == 2) {
              for (Recipe x : book) {
                if (x.number == Integer.parseInt(userChoice)){
                  // edit name
                  System.out.println("Would you like to edit the recipe's name? (Y/N) ");
                  String recipeEdit = scanner.nextLine();
                  String new_name;
                  if (recipeEdit.equalsIgnoreCase("Y") || recipeEdit.equalsIgnoreCase("Yes")) {
                    System.out.println("Please enter the new name: ");
                    new_name = scanner.nextLine();
                  }
                  else {
                    new_name = x.name;
                  }
                  // edit description
                  System.out.println("Would you like to edit the recipe's description? (Y/N) ");
                  recipeEdit = scanner.nextLine();
                  String new_description;
                  if (recipeEdit.equalsIgnoreCase("Y") || recipeEdit.equalsIgnoreCase("Yes")) {
                    System.out.println("Please enter the new description: ");
                    new_description = scanner.nextLine();
                  }
                  else {
                    new_description = x.description;
                  }
                  // edit ingredients
                  System.out.println("Would you like to edit the recipe's ingredients? (Y/N) ");
                  recipeEdit = scanner.nextLine();
                  String new_ingredients;
                  if (recipeEdit.equalsIgnoreCase("Y") || recipeEdit.equalsIgnoreCase("Yes")) {
                    System.out.println("Please enter the new ingredients using commas (ex: 'eggs, chocolate'): ");
                    new_ingredients = scanner.nextLine();
                  }
                  else {
                    new_ingredients = x.ingredients;
                  }

                  // edit instructions
                  System.out.println("Would you like to edit the recipe's instructions? (Y/N) ");
                  recipeEdit = scanner.nextLine();
                  String[] new_instructionsArray;
                  if (recipeEdit.equalsIgnoreCase("Y") || recipeEdit.equalsIgnoreCase("Yes")) {
                    System.out.println("Enter the instructions using commas (ex: 'Mix the eggs, Bake at 350, Enjoy'): ");
                    new_instructionsArray = scanner.nextLine().split(",");
                  }
                  else {
                    new_instructionsArray = new String [x.instructions.length];
                    for (int i = 0; i < x.instructions.length; i++) {
                      new_instructionsArray[i] = x.instructions[i];
                    }
                  }

                  Recipe edited_r = new Recipe(new_name, new_description, new_ingredients, new_instructionsArray, x.number);

                  book.set((x.number - 1), edited_r);
                  System.out.println("-----------------------------------------------");
                  System.out.println("The recipe " + new_name + " has been edited!");
                  System.out.println();
                  System.out.print("Enter any key to continue: ");
                  scanner.nextLine();
                  System.out.print("\033[H\033[2J");
                  System.out.flush();
                  }

                }
              }

              else if (view_edit == 3){
                System.out.print("\033[H\033[2J");
                System.out.flush();
              }


              else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(
                    "That is not a valid option, returning to home.");
              }


              }
              }




        // View all recipes
        else if (val == 3) {
          showAll();
          System.out.println("-----------------------------------------------");
          System.out.println("Please enter the recipe number of your choice or enter 0 to exit: ");
          String userChoice = scanner.nextLine();

          if (Integer.parseInt(userChoice) == 0){
            System.out.print("\033[H\033[2J");
            System.out.flush();
          }

          System.out.println("");
          System.out.println("-----------------------------------------------");
          System.out.println("What would you like to do?");
          System.out.println("1 - View recipe details");
          System.out.println("2 - Edit the recipe");
          System.out.println("3 - Exit");
          System.out.println("-----------------------------------------------");
          System.out.print("Enter a number: ");

          int view_edit = Integer.parseInt(scanner.nextLine());
          // if user wants to view details
          if (view_edit == 1) {
            for (Recipe x : book) {
            if (x.number == Integer.parseInt(userChoice)) {
              System.out.print("\033[H\033[2J");
              System.out.flush();
              System.out.println("Viewing recipe: " + x.name);
              showRecipeInfo(x.name);
              }
            }
          }
          // if user wants to edit details
          else if (view_edit == 2) {
            for (Recipe x : book) {
              if (x.number == Integer.parseInt(userChoice)){
                // edit name
                System.out.println("-----------------------------------------------");
                System.out.println("Would you like to edit the recipe's name? (Y/N) ");
                String recipeEdit = scanner.nextLine();
                String new_name;
                if (recipeEdit.equalsIgnoreCase("Y") || recipeEdit.equalsIgnoreCase("Yes")) {
                  System.out.println("Please enter the new name: ");
                  new_name = scanner.nextLine();
                }
                else {
                  new_name = x.name;
                }
                // edit description
                System.out.println("-----------------------------------------------");
                System.out.println("Would you like to edit the recipe's description? (Y/N) ");
                recipeEdit = scanner.nextLine();
                String new_description;
                if (recipeEdit.equalsIgnoreCase("Y") || recipeEdit.equalsIgnoreCase("Yes")) {
                  System.out.println("Please enter the new description: ");
                  new_description = scanner.nextLine();
                }
                else {
                  new_description = x.description;
                }
                // edit ingredients
                System.out.println("-----------------------------------------------");
                System.out.println("Would you like to edit the recipe's ingredients? (Y/N) ");
                recipeEdit = scanner.nextLine();
                String new_ingredients;
                if (recipeEdit.equalsIgnoreCase("Y") || recipeEdit.equalsIgnoreCase("Yes")) {
                  System.out.println("Please enter the new ingredients using commas (ex: 'eggs, chocolate'): ");
                  new_ingredients = scanner.nextLine();
                }
                else {
                  new_ingredients = x.ingredients;
                }

                // edit instructions
                System.out.println("-----------------------------------------------");
                System.out.println("Would you like to edit the recipe's instructions? (Y/N) ");
                recipeEdit = scanner.nextLine();
                String[] new_instructionsArray;
                if (recipeEdit.equalsIgnoreCase("Y") || recipeEdit.equalsIgnoreCase("Yes")) {
                  System.out.println("Enter the instructions using commas (ex: 'Mix the eggs, Bake at 350, Enjoy'): ");
                  new_instructionsArray = scanner.nextLine().split(",");
                }
                else {
                  new_instructionsArray = new String [x.instructions.length];
                  for (int i = 0; i < x.instructions.length; i++) {
                    new_instructionsArray[i] = x.instructions[i];
                  }
                }

                Recipe edited_r = new Recipe(new_name, new_description, new_ingredients, new_instructionsArray, x.number);

                book.set((x.number - 1), edited_r);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                }
              }
            }

            else if (view_edit == 3){
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
    String[] instructions2 = new String[] {
        "Bring 300ml of water to a boil",
        "Mix egg, kewpie mayo, ramen flavor packets in a bowl (this is called 'tare') ",
        "Drop noodle pack into pot and stir for 3 minutes",
        "Drain water into bowl and mix in the 'tare'",
        "Put noodles into bowl and stir well", "Let sit until warm and Enjoy!"};
    String[] instructions3 = new String[] {
        "Preheat oven to 350°F. Grease an 8x8 square pan or line with foil and set aside.",
        "In a medium bowl combine melted butter and cocoa and sugar stir until fully dissolved.",
        "Add eggs one at a time then vanilla and stir until well combined.",
        "Stir in flour and salt until the flour is fully combine. Be careful not to overmix mix.",
        "(optional) fold in 1 cup of nuts, raisins, chocolate chips or anything you desire.",
        "Spread in pan and bake for approximately 20-22 minutes or until the center is slightly set.  Be careful not to over-bake!",
        "Cool completely then cut into 9 large squares or 16 small squares ( I cut mine into 16)"};

    Recipe r = new Recipe(
        "Cacio e pepe", "butter and pepper spaghetti dish",
        "spaghetti, butter, ground black pepper, grated pecorino or parmesan",
        instructions, 1);
    book.add(r);
    Recipe r2 = new Recipe("Instant Ramen Hack", "easy ramen for fun!",
                           "Ichiban Miso Ramen Pack x1, Kewpie Mayo x1, Egg x1",
                           instructions2, 2);
    book.add(r2);
    Recipe r3 = new Recipe(
        "Brownies", "Easy brownies!",
        "1/2 cup melted butter, 1/2 cup unsweetened cocoa, 1 cup sugar, 2 large eggs, 1 teaspoon vanilla, 1/2 cup flour, 1/4 tsp salt",
        instructions3, 3);
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
