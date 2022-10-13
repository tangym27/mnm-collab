public class Recipe {
  public String name;
  public String description;
  public String ingredients;
  public String[] instructions;
  public Integer number;

  public Recipe() {}

  public Recipe(Integer number, String name, String description,
                String ingredients, String[] instructions) {
    this.number = number;
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
    this.instructions = instructions;
  }

  public String toString() { return number + " - " + name; }

  public String toSaveString() {
    String groupedInstructions = String.join(",", instructions);
    return String.join(";", number + "", name, description, ingredients, groupedInstructions);
  }
}
