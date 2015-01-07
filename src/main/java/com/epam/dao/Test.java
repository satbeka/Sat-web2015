package com.epam.dao;


import com.epam.db.ConnectionPool;
import com.epam.dao.administrator.AdministratorDAO;
import com.epam.dao.administrator.H2AdministratorDAO;
import com.epam.dao.factory.DAOFactory;
import com.epam.dao.factory.H2DAOFactory;
import com.epam.model.Administrator;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;


public class Test {

    public static void main(String[] args) {




        ConnectionPool inst= ConnectionPool.getInstance();

        DAOFactory factory =  DAOFactory.getDAOFactory(DAOFactory.DAOType.H2);
        H2DAOFactory h2DAOFactory=(H2DAOFactory)factory;

        // Create a DAO
        AdministratorDAO administratorDAO = null;
        H2AdministratorDAO h2AdministratorDAO=null;

        try {
            administratorDAO = factory.getAdministratorDAO();
            h2AdministratorDAO=(H2AdministratorDAO)administratorDAO;
            Connection connection=h2AdministratorDAO.setConnection(inst);

        } catch (ConnectionException e) {
            e.printStackTrace();
        }

        // create a new admin
        Administrator administrator=new Administrator();
        administrator.setName("admin");
        administrator.setLogin("admin");
        administrator.setPassword("admin");
        administrator.setInn("1111111111");
        java.util.Date sysDate=new java.util.Date();
        Date sqlDate = new Date(sysDate.getTime());
        administrator.setInsertDate(sqlDate);

        long newAdministratorId = administratorDAO.insertAdministrator(administrator);
        h2AdministratorDAO.closeConnection(inst);

        administrator.setId(newAdministratorId);
        // Find a customer object. Get the Transfer Object.
        h2AdministratorDAO.setConnection(inst);
        Administrator administrator22 = administratorDAO.findFirstAdministratorByName("admin");
        h2AdministratorDAO.closeConnection(inst);

        // modify the values in the Transfer Object.

        String string = "01.01.1980";
        java.util.Date birthDay=null;
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        try {
            birthDay=format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //birthDay=birthDay
            //Date birthDay= (Date) format.parse("01.01.1980");

        System.out.println(birthDay.toString());
        Date sqlBirthDay=new Date(birthDay.getTime());
        administrator22.setBirthDay(sqlBirthDay);


        administrator22.setLogin("admin22");

        // update the customer object using the DAO
        h2AdministratorDAO.setConnection(inst);
        administratorDAO.updateAdministrator(administrator22);
        h2AdministratorDAO.closeConnection(inst);



        /*
        // delete a customer object
        custDAO.deleteCustomer(...);
        // select all customers in the same city
        Customer criteria=new Customer();
        criteria.setCity("New York");
        Collection customersList =
                custDAO.selectCustomersTO(criteria);
// returns customersList - collection of Customer
// Transfer Objects. iterate through this collection to
// get values.
*/


    }




}
