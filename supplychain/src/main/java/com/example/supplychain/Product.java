package com.example.supplychain;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.PipedOutputStream;
import java.sql.ResultSet;

public class Product {
    // id, name, price
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleDoubleProperty price;

    public  Product(int id, String name, double price){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public  int getId() { return  id.get(); }
    public  String  getName() { return  name.get(); }
    public  double getPrice() { return  price.get(); }

    public static ObservableList<Product> getAllProducts(){
        String selectProducts = "SELECT * FROM product";
        return getProductList(selectProducts);
    }

    public static ObservableList<Product> getProductsByName(String productName){
        String selectProducts = String.format("SELECT * FROM product WHERE name like '%%%s%%'", productName.toLowerCase());
        return  getProductList(selectProducts);
    }

    private static ObservableList<Product> getProductList(String query){
        DatabaseConnection dbCon = new DatabaseConnection();
        ObservableList<Product> data = FXCollections.observableArrayList();
        try{
            ResultSet rs =  dbCon.getQueryTable(query);
            while(rs.next()){
                data.add(new Product(rs.getInt("pid"), rs.getString("name"), rs.getDouble("price")));
//                System.out.println(rs.getInt("pid") + " " +
//                        rs.getString("name") + " " +
//                        rs.getDouble("price")
//                );
            }
            rs.close();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return data;
    }



}