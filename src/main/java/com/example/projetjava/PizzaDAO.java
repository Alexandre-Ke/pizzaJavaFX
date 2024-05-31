package com.example.projetjava;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaDAO {
    public List<Pizza> getPizza() {
        List<Pizza> pizzas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/e3951u_pizza", "e3951u_appli", "32320679");

            Statement sst = con.createStatement();
            ResultSet rs = sst.executeQuery("SELECT p.nom AS pizza_id, p.prix AS pizza_nom, Group_concat(i.nom ORDER BY i.nom SEPARATOR ', ') AS ingredients FROM Pizza p JOIN Pizza_Ingredient pi ON p.id = pi.pizza_id JOIN Ingredient i ON pi.ingredient_id = i.id GROUP BY p.id, p.nom;");
            while(rs.next()){
                String tousLesIngredients = rs.getString(3);
                System.out.println(tousLesIngredients);
                // Split the comma-separated string into an array of strings
                String[] itemsArray = tousLesIngredients.split(",");

                // Convert the array to an ArrayList
                ArrayList<String> list = new ArrayList<>(Arrays.asList(tousLesIngredients.split(",")));

                // Remove any empty strings resulting from trailing commas
                // itemList.removeIf(String::isEmpty);

                // Print the resulting ArrayList
                System.out.println(list);
                pizzas.add(new Pizza(rs.getString(1),rs.getString(2),list));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return pizzas;
    }


    public void ajouterPizza(Pizza pizza) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/e3951u_pizza", "e3951u_appli", "32320679")) {
            // Insérer la nouvelle pizza
            PreparedStatement pizzaStatement = con.prepareStatement("INSERT INTO Pizza (nom, prix) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pizzaStatement.setString(1, pizza.getNom());
            pizzaStatement.setString(2, pizza.getPrix());
            pizzaStatement.executeUpdate();

            // Obtenir l'ID de la pizza insérée
            ResultSet generatedKeys = pizzaStatement.getGeneratedKeys();
            int pizzaId = 0;
            if (generatedKeys.next()) {
                pizzaId = generatedKeys.getInt(1);
            }

            // Insérer les ingrédients dans la table Pizza_Ingredient
            PreparedStatement ingredientStatement = con.prepareStatement("INSERT INTO Pizza_Ingredient (pizza_id, ingredient_id) VALUES (?, ?)");

            for (String ingredient : pizza.getListeIngredient()) {
                // Obtenir l'ID de l'ingrédient
                PreparedStatement ingredientIdStatement = con.prepareStatement("SELECT id FROM Ingredient WHERE nom = ?");
                ingredientIdStatement.setString(1, ingredient);
                ResultSet ingredientRs = ingredientIdStatement.executeQuery();
                int ingredientId = 0;
                if (ingredientRs.next()) {
                    ingredientId = ingredientRs.getInt("id");
                } else {
                    // Si l'ingrédient n'existe pas, l'ajouter à la table Ingredient
                    PreparedStatement insertIngredientStatement = con.prepareStatement("INSERT INTO Ingredient (nom) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                    insertIngredientStatement.setString(1, ingredient);
                    insertIngredientStatement.executeUpdate();

                    ResultSet ingredientGeneratedKeys = insertIngredientStatement.getGeneratedKeys();
                    if (ingredientGeneratedKeys.next()) {
                        ingredientId = ingredientGeneratedKeys.getInt(1);
                    }
                }

                // Ajouter l'entrée dans Pizza_Ingredient
                ingredientStatement.setInt(1, pizzaId);
                ingredientStatement.setInt(2, ingredientId);
                ingredientStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerPizza(String pizzaNom) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/e3951u_pizza", "e3951u_appli", "32320679");

            PreparedStatement statement = con.prepareStatement("DELETE FROM pizza WHERE nom = ?;");
            statement.setString(1, pizzaNom);

            statement.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clearPizzas() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/e3951u_pizza", "e3951u_appli", "32320679");
             PreparedStatement statement = con.prepareStatement("DELETE FROM Pizza")) {

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


