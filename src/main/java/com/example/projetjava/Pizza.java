package com.example.projetjava;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pizza {
    String nom;
    String prix;
    ArrayList<String> listeIngredient;

    public Pizza(String nom, String prix, ArrayList<String> listeIngredient){
        this.nom=nom;
        this.prix=prix;
        this.listeIngredient=listeIngredient;
    }

    public Pizza(){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public ArrayList<String> getListeIngredient() {
        return listeIngredient;
    }

    public void setListeIngredient(ArrayList<String> listeIngredient) {
        this.listeIngredient = listeIngredient;
    }

    public String getIngredientsAsString() {
        return String.join(", ", listeIngredient);
    }

    public String toString(){
        return listeIngredient.toString();
    }
}


