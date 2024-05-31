package com.example.projetjava;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    @FXML
    private TableView tableau;
    @FXML
    private TableColumn colNom;
    @FXML
    private TableColumn colPrix;
    @FXML
    private TableColumn<Pizza, String> colIngredients;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtIngredients;
    @FXML
    private Button btnAjouter;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PizzaDAO pizzaDAO = new PizzaDAO();

        // Configurer les colonnes
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        colIngredients.setCellValueFactory(cellData -> {
            Pizza pizza = cellData.getValue();
            return new SimpleStringProperty(pizza.getIngredientsAsString());
        });

        // Récupérer les données de la base de données
        List<Pizza> pizzas = pizzaDAO.getPizza();

        // Ajoutez des journaux de débogage
        System.out.println("Nombre de pizzas récupérées : " + pizzas.size());
        for (Pizza pizza : pizzas) {
            System.out.println(pizza.getNom() + " - " + pizza.getPrix() + " - " + pizza.getListeIngredient());
        }

        // Lier les données au TableView
        ObservableList<Pizza> pizzaList = FXCollections.observableArrayList(pizzas);
        tableau.setItems(pizzaList);
    }
    @FXML
    public void ajouterPizzaDansTableau(String nom, String prix, String listeIngredients) {
        Pizza pizza = new Pizza();

        ArrayList<String> ingredientsList = new ArrayList<>(List.of(listeIngredients.split(",")));

        pizza.setNom(nom);
        pizza.setPrix(prix);
        pizza.setListeIngredient(ingredientsList);
        ObservableList<Pizza> films =tableau.getItems();
        films.add(pizza);
        tableau.setItems(films);
    }

    @FXML
    public void ajouterPizza(){
        PizzaDAO pizzaDAO = new PizzaDAO();
        var ingredientsChamp = Integer.parseInt(txtIngredients.getText());
        ajouterPizzaDansTableau(txtNom.getText(),txtPrix.getText(),txtIngredients.getText());

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colIngredients.setCellValueFactory(new PropertyValueFactory<>("ingredients"));

        // Pizza newPizza = new Pizza(txtNom.getText(),txtPrix.getText(),);
        // pizzaDAO.ajouterPizza(newPizza);
        var theSelectedObject = tableau.getSelectionModel().getSelectedIndex();
        System.out.println(theSelectedObject);
    }

    @FXML
    private void buttonAjouterPizza() {
        String nom = txtNom.getText();
        String prix = txtPrix.getText();
        String ingredientsText = txtIngredients.getText();

        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(ingredientsText.split(",")));

        // Créez une nouvelle pizza avec les données du formulaire
        Pizza pizza = new Pizza(nom, prix, ingredients);

        // Ajouter la pizza dans la base de données
        PizzaDAO pizzaDAO = new PizzaDAO();
        pizzaDAO.ajouterPizza(pizza);

        // Rafraîchir la TableView
        List<Pizza> pizzas = pizzaDAO.getPizza();
        ObservableList<Pizza> pizzaList = FXCollections.observableArrayList(pizzas);
        tableau.setItems(pizzaList);

        // Effacer les champs de texte
        txtNom.clear();
        txtPrix.clear();
        txtIngredients.clear();
    }

    @FXML
    public void viderTab(){
        PizzaDAO pizzaDAO = new PizzaDAO();
        pizzaDAO.clearPizzas();
        tableau.getItems().clear(); // Efface les éléments du tableau JavaFX
    }
}

