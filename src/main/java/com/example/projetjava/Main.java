package com.example.projetjava;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pizza-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 480);
        stage.setTitle("Gestion des pizzas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        PizzaDAO pizzaDAO = new PizzaDAO();
        List<Pizza> pizzas = pizzaDAO.getPizza();

        // Créer un document PDF
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("pizzas.pdf"));
            document.open();

            // Ajouter les informations des pizzas au document
            for (Pizza pizza : pizzas) {
                document.add(new Paragraph("Nom: " + pizza.getNom()));
                document.add(new Paragraph("Prix: " + pizza.getPrix() + "€"));
                document.add(new Paragraph("Ingrédients: " + String.join(", ", pizza.getListeIngredient())));
                document.add(new Paragraph("\n")); // Ajouter une ligne vide entre les pizzas
            }

            document.close();
            System.out.println("PDF créé avec succès !");
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
