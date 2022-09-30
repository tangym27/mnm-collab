import java.util.*;

public class Recipe{
  private String name;
  private String ingredients;
  private String[] instructions;

  public Recipe(){
  }

  public Recipe(String name, String ingredients, String[] instructions){
    this.name = name;
    this.ingredients = ingredients;
    this.instructions = instructions;
  }

  public String toString(){
    return name + " " + ingredients + " " + instructions[0];
  }

}
