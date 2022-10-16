import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class RecipeBook {

  private static ArrayList<Recipe> book = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("-----------------------------------------------");
    System.out.println("Welcome to your recipe book!");

    // Check for recipes.txt
    try {
      File f = new File("recipes.txt");

      // File not found
      if (f.createNewFile()) {
        System.out.println("No file found.");
        System.out.println("Creating recipes.txt...");
      }

      // File found, load
      else {
        System.out.println("File found! Recipes loaded.");
        loadRecipes(f);
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    while (true) {
      printIntro();
      try {

        int val = Integer.parseInt(scanner.nextLine());
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Choice 1: Add a recipe
        if (val == 1) {
          addRecipe();
        }

        // Choice 2: Search for a recipe
        else if (val == 2) {
          searchRecipe();
        }

        // Choice 3: View all recipes
        else if (val == 3) {
          showAll();
          System.out.println("-----------------------------------------------");
          System.out.println("Please enter the recipe number of your choice or enter 0 to exit: ");
          String userChoice = scanner.nextLine();
          if (Integer.parseInt(userChoice) == 0){
            System.out.print("\033[H\033[2J");
            System.out.flush();
          } else {
            editRecipe(userChoice);
          }
        }

        // Choice 4: Random recipe
        else if (val == 4) {
          int rand = (int) Math.floor(Math.random()* book.size());
          Recipe x = book.get(rand);
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.println("Viewing recipe: " + x.name);
          showRecipeInfo(x.name);
        }

        // Choice 5: Exit & Save recipe book
        else if (val == 5){
          saveRecipe();
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


  private static void printIntro() {
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


  // Add Recipe
  private static void addRecipe() {
    try {
      System.out.println("Adding a recipe!\n");
      System.out.print("Enter the recipe name (ex: 'Cake'): ");
      String name = scanner.nextLine();
      System.out.print("Enter the recipe's description': ");
      String description = scanner.nextLine();
      System.out.print(
          "Enter the ingredients using commas (ex: 'eggs, chocolate'): ");
      String ingredients = scanner.nextLine();
      
      // Ingredients Empty
      if (ingredients.isEmpty()) {
        ingredients = "N/A";
      }

      System.out.print(
          "Enter the instructions using commas (ex: 'Mix the eggs, Bake at 350, Enjoy'): ");
      String[] instructionsArray = scanner.nextLine().split(",");

      int thingCount = 0;

      for (String thing : instructionsArray) {
        if (thing.isEmpty()) {
          instructionsArray[thingCount] = "?";
        }

        thingCount += 1;
      }

      int current_num = 1;

      for (Recipe x : book) {
        if (x.number > current_num) {
          current_num = x.number;
        }
      }

      Recipe r = new Recipe((current_num + 1), name, description, ingredients,
                            instructionsArray);

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

  // Search for a recipe
  private static void searchRecipe(){
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

    // Matches found
    else {
      System.out.println("-----------------------------------------------");
      System.out.println("Matches found!");

      for (Recipe x : bookClone) {
        System.out.println(x);
      }
      System.out.println("-----------------------------------------------");
      System.out.println("Please enter the recipe number of your choice: ");
      String userChoice = scanner.nextLine();
      editRecipe(userChoice);
    }
  }

  // Give user options to view details or edit recipe
  private static void editRecipe(String userChoice){
    System.out.println("");
    System.out.println("-----------------------------------------------");
    System.out.println("What would you like to do?");
    System.out.println("1 - View recipe details");
    System.out.println("2 - Edit the recipe");
    System.out.println("3 - Exit");
    System.out.println("-----------------------------------------------");
    System.out.print("Enter a number: ");

    boolean found = false;
    int view_edit = Integer.parseInt(scanner.nextLine());
    // if user wants to view details
    if (view_edit == 1) {
      for (Recipe x : book) {
        if (x.number == Integer.parseInt(userChoice)) {
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.println("Viewing recipe: " + x.name);
          showRecipeInfo(x.name);
          found = true;
        }
      }
    }
    // if user wants to edit details
    else if (view_edit == 2) {
      for (Recipe x : book) {
        if (x.number == Integer.parseInt(userChoice)){
          found = true;
          // edit name
          System.out.println("Would you like to edit the recipe's name? (Y/N) ");
          String recipeEdit = scanner.nextLine();
          String new_name = "";
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

          Recipe edited_r = new Recipe(x.number, new_name, new_description, new_ingredients, new_instructionsArray);

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
        found = true;
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }

      else {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(
            "That is not a valid option, returning to home.");
      }

      if (!found){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(
            "That was not a valid recipe number, returning to home.");
      }

  }

  // List All Recipes
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


  // Load recipes from file
  private static void loadRecipes(File f) {
    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
      String line;

      String[] temp;
      String[] instructions;
      while ((line = br.readLine()) != null) {
        temp = line.split(";");
        if (temp.length > 4){
          instructions = temp[4].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
          Recipe r = new Recipe(Integer.parseInt(temp[0]), temp[1], temp[2],
                                temp[3], instructions);
          book.add(r);
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  // Save recipes to file
  private static void saveRecipe(){
    StringBuilder s = new StringBuilder();
    for (Recipe x: book){
      s.append(x.toSaveString()).append("\n");
    }
    try{
      String fileName = "recipes.txt";
      FileWriter fileWritter = new FileWriter("recipes.txt");
      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
      bufferWritter.write(s.toString());
      System.out.println("Successfully saved recipe book.");
      bufferWritter.close();
    }
    catch (Exception e) {
      System.out.println("Error saving file");
    }
  }

}
