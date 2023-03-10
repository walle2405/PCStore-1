/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import models.Product;

/**
 *
 * @author trung
 */
public class ProductFacade {

    private List<Product> list = null;

    public List<Product> select() throws SQLException {
        List<Product> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("SELECT ProductId, productName, price, category.name AS categoryName, brand.name as brandName, description FROM Product INNER JOIN Brand ON Product.BrandId = Brand.BrandId INNER JOIN Category ON Product.CategoryId = Category.CategoryId");
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin hien hanh de vao doi tuong toy
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryName(rs.getString("categoryName"));
            product.setBrandName(rs.getString("brandName"));
            product.setDescription(rs.getString("description"));
            //Them toy vao list
            list.add(product);
        }
        con.close();
        return list;
    }

    public void create(Product product) throws SQLException {
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement("insert product values (?,?,?,?,?)");
        stm.setString(1, product.getProductName());
        stm.setDouble(2, product.getPrice());
        stm.setInt(3, product.getCategoryId());
        stm.setInt(4, product.getBrandId());
        stm.setString(5, product.getDescription());
        int count = stm.executeUpdate();
        con.close();
    }

    public Product read(int productId) throws SQLException {
        Product product = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        PreparedStatement stm = con.prepareStatement("select * from product where productId = ?");
        //Thực thi lệnh SELECT
        stm.setInt(1, productId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            //Doc mau tin hien hanh de vao doi tuong product
            product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setBrandId(rs.getInt("brandId"));
            product.setDescription(rs.getString("description"));
        }
        con.close();
        return product;
    }

    public Product readForCart(int productId) throws SQLException {
        Product product = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        PreparedStatement stm = con.prepareStatement("SELECT ProductId, productName, price, category.name AS categoryName, brand.name as brandName, description FROM Product INNER JOIN Brand ON Product.BrandId = Brand.BrandId INNER JOIN Category ON Product.CategoryId = Category.CategoryId WHERE ProductId = ?");
        //Thực thi lệnh SELECT
        stm.setInt(1, productId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            //Doc mau tin hien hanh de vao doi tuong product
            product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryName(rs.getString("categoryName"));
            product.setBrandName(rs.getString("brandName"));
            product.setDescription(rs.getString("description"));
        }
        con.close();
        return product;
    }

    public void delete(int productId) throws SQLException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DBContext.getConnection();
        //Tạo đối tượng statement
        PreparedStatement stm = con.prepareStatement("delete from product where productId = ?");
        //Thực thi lệnh 
        stm.setInt(1, productId);
        int count = stm.executeUpdate();
        con.close();
        // neu xoa khong duoc thi gay ra ngoai le
    }

    public void update(Product product) throws SQLException {
        Connection con = DBContext.getConnection();
        PreparedStatement stm = con.prepareStatement("update product set productName = ?, price = ?, categoryId = ?, brandId = ?, description = ? where productId = ?");
        stm.setString(1, product.getProductName());
        stm.setDouble(2, product.getPrice());
        stm.setInt(3, product.getCategoryId());
        stm.setInt(4, product.getBrandId());
        stm.setString(5, product.getDescription());
        stm.setInt(6, product.getProductId());
        int count = stm.executeUpdate();
        con.close();
        // neu xoa khong duoc thi gay ra ngoai le
    }

    public Product select(int productId) {
        for (Product product : list) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
}