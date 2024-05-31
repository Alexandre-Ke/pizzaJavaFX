import com.example.projetjava.Pizza;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class PizzaTest {

    @Test
    void getNom() {
        // organiser
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Mozzarella");
        Pizza p1 = new Pizza("chorizo","10",ingredients);
        String nom = "chorizo";

        // agir
        String rep = p1.getNom();

        // verifier
        assertEquals(nom,rep);
    }

    @Test
    void setNom() {
        // organiser
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Mozzarella");
        Pizza p1 = new Pizza("","",ingredients);
        String nom = "chorizzo";

        // agir
        p1.setNom(nom);
        String rep = p1.getNom();

        // verifier
        assertEquals(nom,rep);
    }
    @Test
    void getPrix() {
        // organiser
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Mozzarella");
        Pizza p1 = new Pizza("chorizo","10",ingredients);
        String prix = "10";
        // agir
        String rep = p1.getPrix();

        // verifier
        assertEquals(prix,rep);
    }

    @Test
    void setPrix() {
        // organiser
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Mozzarella");
        Pizza p1 = new Pizza("chorizo","",ingredients);
        String prix = "10";

        // agir
        p1.setPrix(prix);
        String rep = p1.getPrix();

        // verifier
        assertEquals(prix,rep);
    }
    @Test
    void getListeIngredient() {
        // organiser
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Mozzarella");
        Pizza p1 = new Pizza("chorizo","10", ingredients);

        // agir
        ArrayList<String> rep = p1.getListeIngredient();

        // verifier
        assertEquals(ingredients,rep);
    }

    @Test
    void setListeIngredient() {
        // organiser
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Mozzarella");
        Pizza p1 = new Pizza("chorizo","",ingredients);

        ArrayList<String> ingredientsTest = new ArrayList<>();
        ingredientsTest.add("Fromage");
        // agir
        p1.setListeIngredient(ingredientsTest);
        ArrayList rep = p1.getListeIngredient();

        // verifier
        assertEquals(ingredients,rep);
    }
}
