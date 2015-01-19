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

    public boolean remove(Product productRemove) {
        for (Product product : products) {
            if (product.getId() == productRemove.getId()) {
                return products.remove(product);
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSumPaid() {
        return sumPaid;
    }

    public void setSumPaid(BigDecimal sumPaid) {
        this.sumPaid = sumPaid;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date date) {
        this.insertDate = date;
    }

    private String number;
    private int quantity;
    //private Product product;
    private Client client;
    private BigDecimal sum;
    private BigDecimal sumPaid;
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
