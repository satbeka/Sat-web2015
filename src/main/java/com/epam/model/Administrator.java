package com.epam.model;


import java.io.Serializable;

public class Administrator extends User implements Serializable{

    private final Role role=Role.ADMINISTRATOR;

}
