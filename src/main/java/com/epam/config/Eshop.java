package com.epam.config;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

@XmlRootElement
public class Eshop {

    public ArrayList<Action> getList() {
        return list;
    }

    @XmlElement(name = "action")
    private ArrayList<Action> list = new ArrayList<Action>();

    public Eshop() {
        super();
    }

    @XmlTransient
    public void setList(ArrayList<Action> list) {
        this.list = list;
    }

    public boolean add(Action ac) {
        return list.add(ac);
    }

    @Override
    public String toString() {
        return "Eshop [list=" + list + "]";
    }
}



