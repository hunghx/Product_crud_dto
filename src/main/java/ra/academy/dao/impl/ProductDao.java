package ra.academy.dao.impl;

import org.springframework.stereotype.Component;
import ra.academy.dao.IProductDao;
import ra.academy.model.Product;
import ra.academy.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component

public class ProductDao implements IProductDao {
    @Override
    public List<Product> findAll() {
        Connection conn = ConnectDB.getConnection();
        List<Product> list = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("Select * from Product");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
               Product p = new Product();
               p.setId(rs.getLong("id"));
               p.setName(rs.getString("name"));
               p.setDescription(rs.getString("description"));
               p.setStock(rs.getInt("stock"));
               p.setPrice(rs.getDouble("price"));
               p.setImageUrl(rs.getString("image_url"));
               p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Product findById(Long id) {
        Connection conn = ConnectDB.getConnection();
        Product p = null;
        try {
            CallableStatement callSt = conn.prepareCall("Select * from Product where  id =?");
            callSt.setLong(1,id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()){
                p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setStock(rs.getInt("stock"));
                p.setPrice(rs.getDouble("price"));
                p.setImageUrl(rs.getString("image_url"));
                p.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return p;
    }

    @Override
    public void save(Product product) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;

        try {
            if(product.getId()!=null){
                // cập nhật
                callSt = conn.prepareCall("update product set name=?, description=?, image_url=?, stock=?, price=?, status=? where  id =?");
                callSt.setString(1,product.getName());
                callSt.setString(2,product.getDescription());
                callSt.setString(3,product.getImageUrl());
                callSt.setInt(4,product.getStock());
                callSt.setDouble(5,product.getPrice());
                callSt.setBoolean(6,product.isStatus());
                callSt.setLong(7,product.getId());
                callSt.executeUpdate();
            }else {
                // thêm mới
                callSt = conn.prepareCall("insert into product(name, description, image_url, stock, price, status)" +
                        " values (?,?,?,?,?,?)");
                callSt.setString(1,product.getName());
                callSt.setString(2,product.getDescription());
                callSt.setString(3,product.getImageUrl());
                callSt.setInt(4,product.getStock());
                callSt.setDouble(5,product.getPrice());
                callSt.setBoolean(6,product.isStatus());
                callSt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("delete from Product where  id =?");
            callSt.setLong(1,id);
           callSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
