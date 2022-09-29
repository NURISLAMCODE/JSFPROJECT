/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Student
 */

@ManagedBean
@SessionScoped
public class Product {
 
    int id;
    int price;
    int quantity;
    String brand;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<Product> list = new ArrayList();

    public Product() {
    }

    public Product(int id, int price, int quantity, String brand) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ArrayList<Product> getList() {
        return list;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }
    
     public String insert() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsf", "root", "nclc123");
        pst = con.prepareStatement("INSERT INTO product VALUES (?,?,?,?)");
        pst.setInt(1, id);
        pst.setInt(2, price);
        pst.setInt(3, quantity);
        pst.setString(4, brand);
        pst.executeUpdate();
        show();
        return "index.xhtml";
                
    }
     
      public String update() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsf", "root", "nclc123");
        pst = con.prepareStatement("UPDATE product SET price = ?, quantity = ?, brand = ? WHERE id = ?");
        pst.setInt(4, id);
        pst.setInt(1, price);
        pst.setInt(2, quantity);
        pst.setString(3, brand);
        pst.executeUpdate();
        show();
        return "index.xhtml";
        
    }

    public String delete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsf", "root", "nclc123");
        pst = con.prepareStatement("DELETE FROM product WHERE id = ?");
        pst.setInt(1, id);
        pst.executeUpdate();
        show();
        return "index.xhtml";
        
    }

    public String show() throws ClassNotFoundException, SQLException {
        list.clear();
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsf", "root", "nclc123");
        pst = con.prepareStatement("SELECT* FROM product");
        rs = pst.executeQuery();

        Product st;
        while (rs.next()) {
            st = new Product(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
            list.add(st);
        }
        
        return "index.xhtml";
    }
    
    public String clear(){
    return "clear.xhtml";
    }
}
