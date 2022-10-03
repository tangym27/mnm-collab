public class Recipe {
  public String name;
  public String description;
  public String ingredients;
  public String[] instructions;
  public Integer number;

  public Recipe() {}

  public Recipe(String name, String description, String ingredients,
                String[] instructions, Integer number) {
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.number = number;
  }

  public String toString() { return number + " - " + name; }
}
