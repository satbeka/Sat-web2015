package com.epam.dao.product;


import com.epam.model.Product;

import javax.sql.RowSet;
import java.util.List;

public interface ProductDAO {
    public long insertProduct(Product product);
    public boolean deleteProduct(Product product);
    public Product findProductByName(String name);
    public Product findProductById(long id);
    public boolean updateProduct(Product product);
    public RowSet selectProductRS(Product product);
    public List<Product> selectProductTO();
}
