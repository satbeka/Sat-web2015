package com.epam.model;


public class Id {
    //private Long id;
    public static long nextId(String tblName){
        String firstId="70";
        Long id;
        switch (tblName){
            case "PRODUCT":firstId="10";
                break;
            case "ROLE":firstId="20";
                break;
            case "USER":firstId="30";
                break;
            case "USER_ORDER":firstId="40";
                break;
            case "ADDRESS":firstId="50";
                break;
        }
        return id= Long.parseLong(firstId);
        //return id=firstId+getSEQ_ID;


    }
}
