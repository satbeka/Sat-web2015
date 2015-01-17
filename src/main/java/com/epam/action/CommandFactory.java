package com.epam.action;


//import com.epam.config.Action;

import com.epam.config.Action;
import com.epam.config.Eshop;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandFactory {

    public ArrayList<Action> getCommandList() {
        return commandList;
    }

    private ArrayList<Action> commandList;

    public CommandFactory() {
    }

    ;

    public void LoadEshopConfig(Eshop eshop) {

        this.commandList = new ArrayList<Action>();
        for (Iterator<Action> i = eshop.getList().iterator(); i.hasNext(); ) {
            this.commandList.add(i.next());
        }
    }

    ;

    public ActionCommand defineCommand(String method, String path) {
        ActionCommand actionCommand = null;
        ArrayList<Action> listAction = new ArrayList<Action>();
        listAction = this.getCommandList();
        Action result = null;
        for (Iterator<Action> i = listAction.iterator(); i.hasNext(); ) {
            result = i.next();
//            System.out.println(" result.getMethod()= "+result.getMethod());
//            System.out.println(" result.getPath()= "+result.getPath());

            if ((result.getMethod().equals(method)) & (result.getPath().equals(path))) {
                System.out.println(" have result= "+result.getName());
                break;
            }
            result = null;

        }
        System.out.println(" method= " + method);
        System.out.println(" path= " + path);

        if (result == null) {
            return actionCommand;
        }


        if (result.getName().equals("GetLogin")) {
            actionCommand = new GetLogin(result);
            return actionCommand;

        }
        ;
        if (result.getName().equals("Login")) {
            actionCommand = new Login(result);
            return actionCommand;

        };
        //AdministratorWhatDo
        if (result.getName().equals("AdministratorWhatDo")) {
            actionCommand = new AdministratorWhatDo(result);
            return actionCommand;
        }
        ;
        if (result.getName().equals("Logout")) {
            actionCommand = new Logout(result);
            return actionCommand;
        }
        ;
        if (result.getName().equals("GetProductList")) {
            actionCommand = new GetProductList(result);
        }
        ;
        if (result.getName().equals("SaveProduct")) {
            actionCommand = new SaveProduct(result);
        }
        ;
        if (result.getName().equals("GetClientList")) {
            actionCommand = new GetClientList(result);
        }
        ;
        if (result.getName().equals("SaveClientList")) {
            actionCommand = new SaveClientList(result);
        }
        ;
        if (result.getName().equals("ClientWhatDo")) {
            actionCommand = new ClientWhatDo(result);
        }
        ;
        if (result.getName().equals("GetOrderList")) {
            actionCommand = new GetOrderList(result);
        }
        ;

        if (result.getName().toString() == "GetOrderList") {
            actionCommand = new Login();
        }
        ;
        if (result.getName().toString() == "LoginAction") {
            actionCommand = new Login();
        }
        ;
        return actionCommand;
    }
}
