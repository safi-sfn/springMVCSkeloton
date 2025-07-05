/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.safi.apps.pms.dao.impl;

import com.safi.apps.pms.config.MySQLConnection;
import com.safi.apps.pms.dao.IPmsManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author safi
 */
@Repository
public class PmsManagerImpl implements IPmsManager {

     private final MySQLConnection mySQLConnection;

    /**
     * Injects the MySQLConnection bean.
     * @param mySQLConnection The MySQLConnection instance.
     */
    @Autowired
    public PmsManagerImpl(MySQLConnection mySQLConnection) {
        this.mySQLConnection = mySQLConnection;
    }
    
    @Override
    public int addBrand(String brandName) {
        String sql = "INSERT INTO product_brand (brand_name) VALUES (?)";
        try (Connection conn = mySQLConnection.getConnection(); // Get connection from our custom class
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, brandName);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("affectedRows :"+affectedRows);
            if (affectedRows > 0) {
                
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            System.err.println("Error adding brand '" + brandName + "': " + e.getMessage());
            // In a real application, you might log this and re-throw a custom exception
            return 0;
        }
    }

}
