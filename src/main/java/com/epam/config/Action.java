package com.epam.config;


import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Action", propOrder = {
        "name",
        "view"
})


public class Action {
    @XmlAttribute(required = true)
    private String method;

    @XmlAttribute(required = true)
    private String path;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String view;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Action() { }
    public Action(String name,String view, String method, String path) {
        this.name = name;
        this.view=view;
        this.method = method;
        this.path = path;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Action {" +
                ", method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", view='" + view + '\'' +
                '}';
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
