package com.epam.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class Order implements Serializable {
    private Long id;

    public boolean add(ProductExtQuantity productExtQuantity) {
        return products.add(productExtQuantity);
    }

    public void initProducts() {
        products = new ArrayList();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public boolean remove(ProductExtQuantity productExtQuantityRemove) {
        for (ProductExtQuantity productExtQuantity : products) {
            if (productExtQuantity.getId() == productExtQuantityRemove.getId()) {
                return products.remove(productExtQuantity);
            }
        }
        return false;
    }

    public ArrayList<ProductExtQuantity> getProducts() {
        return products;
    }

    private ArrayList<ProductExtQuantity> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date date) {
        this.insertDate = date;
    }

    private String number;
    private BigDecimal sumPaid;

    public BigDecimal getSumPaid() {
        return sumPaid;
    }

    public void setSumPaid(BigDecimal sumPaid) {
        this.sumPaid = sumPaid;
    }

    //private int quantity;
    //private Product product;
    private Client client;
    private Date insertDate;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
