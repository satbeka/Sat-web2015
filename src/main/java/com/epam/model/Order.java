package com.epam.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class Order implements Serializable {
    private Long id;
    private ArrayList<Warehouse> products;
    private String number;
    private BigDecimal sumPaid;
    //private int quantity;
    //private Product product;
    private Client client;
    private Date insertDate;

    public boolean add(Warehouse warehouse) {
        return products.add(warehouse);
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

    public boolean remove(Warehouse warehouseRemove) {
        for (Warehouse warehouse : products) {
            if (warehouse.getProduct().getId() == warehouseRemove.getProduct().getId()) {
                return products.remove(warehouse);
            }
        }
        return false;
    }

    public ArrayList<Warehouse> getProducts() {
        return products;
    }

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

    public BigDecimal getSumPaid() {
        return sumPaid;
    }

    public void setSumPaid(BigDecimal sumPaid) {
        this.sumPaid = sumPaid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
