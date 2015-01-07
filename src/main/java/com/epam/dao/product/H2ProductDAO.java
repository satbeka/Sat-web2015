package com.epam.dao.product;

import com.epam.db.ConnectionPool;
import com.epam.model.Product;

import javax.sql.RowSet;
import java.sql.Connection;
import java.util.List;


public class H2ProductDAO implements ProductDAO {

    private Connection connection = null;
    // initialization
    public Connection createConnection(ConnectionPool connectionPool) {
        if (this.connection==null){
            this.connection=connectionPool.takeConnection();};
        return this.connection;
    }

    public void closeConnection(ConnectionPool connectionPool) {
        if (this.connection!=null){
            connectionPool.releaseConnection(this.connection);};
        return;
    }
    public H2ProductDAO(){
    }

    @Override
    public int insertProduct(Product product) {
        return 0;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return false;
    }

    @Override
    public Product findProductByName(String name) {
        return null;
    }

    @Override
    public Product findProductById(long id) {
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public RowSet selectProductRS(Product product) {
        return null;
    }

    @Override
    public List<Product> selectProductTO() {
        return null;
    }
}
