/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import core.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Gokhan
 */
public class ProductDAO implements DAO<Product> {
    public ProductDAO() {

    }

    List<Product> products;

    /**
     * Get a single product entity as a product object
     *
     * @param id
     * @return
     */

    @Override
    public Optional<Product> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM HD_Product WHERE Product_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getInt("Product_ID"), rs.getString("Product_Name"), rs.getString("Product_Description"), rs.getString("Product_Color"), rs.getString("Product_Size"), rs.getDouble("Product_Price"));
            }
            return Optional.ofNullable(product);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Get all product entities as a List
     *
     * @return
     */
    @Override
    public List<Product> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HD_Product";
            rs = db.executeQuery(sql);
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getInt("Product_ID"), rs.getString("Product_Name"), rs.getString("Product_Description"), rs.getString("Product_Color"), rs.getString("Product_Size"), rs.getDouble("Product_Price"));
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Insert a product object into product table
     *
     * @param product
     */
    @Override
    public void insert(Product product) {
        DB db = DB.getInstance();
        try {
            int nextId = getNextId();
            product.setID(nextId);

            String sql = "INSERT INTO HD_Product(Product_ID, Product_Name, Product_Description, Product_Color, Product_Size, Product_Price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, product.getID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getColor());
            stmt.setString(5, product.getSize());
            stmt.setDouble(6, product.getPrice());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new product was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Update a product entity in database if it exists using a product object
     *
     * @param product
     */
    @Override
    public void update(Product product) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE HD_Product SET Product_Name=?, Product_Description=?, Product_Color=?, Product_Size=?, Product_Price=? WHERE Product_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getColor());
            stmt.setString(4, product.getSize());
            stmt.setDouble(5, product.getPrice());
            stmt.setInt(6, product.getID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing product was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Delete a product from product table if the entity exists
     *
     * @param product
     */
    @Override
    public void delete(Product product) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM HD_Product WHERE Product_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, product.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A product was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Get all column names in a list array
     *
     * @return
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HD_Product WHERE Product_ID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }

    }

    private int getNextId() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT MAX(Product_ID) AS max_id FROM HD_Product";
            rs = db.executeQuery(sql);
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                return maxId + 1;
            } else {
                return 1; // table empty, start from 1
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return 1; // fallback
        }
    }
}
