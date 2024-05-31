import com.example.projetjava.Pizza;
import com.example.projetjava.PizzaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzaDAOTest {
    private PizzaDAO pizzaDAO;

    @Test
    public void testAjouterEtCompterPizzas() {
        pizzaDAO = new PizzaDAO();
        // a) Récupérer le nombre de pizzas en DB
        List<Pizza> initialPizzas = pizzaDAO.getPizza();
        int initialCount = initialPizzas.size();


        // b) Ajouter une pizza
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomate");
        ingredients.add("Mozzarella");
        Pizza newPizza = new Pizza("TestPizza", "10.99", ingredients);
        pizzaDAO.ajouterPizza(newPizza);

        // c) Récupérer le nouveau nombre de pizzas en DB et vérifier qu’il est bien incrémenté
        List<Pizza> updatedPizzas = pizzaDAO.getPizza();
        int updatedCount = updatedPizzas.size();

        assertEquals(initialCount + 1, updatedCount);
    }


}
