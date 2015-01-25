package com.epam.model;

import java.io.Serializable;


public class Client extends User implements Serializable {

    private final Role role = Role.CLIENT;
    private Integer blackList;

    public Integer getBlackList() {
        return blackList;
    }

    public void setBlackList(Integer blackList) {
        this.blackList = blackList;
        //H2ClientDAO h2ClientDAO=new H2ClientDAO()
    }
}
