package com.epam.action;


//import com.epam.config.Action;

import com.epam.config.Action;
import com.epam.config.Eshop;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandFabric {

    public ArrayList<Action> getCommandList() {
        return commandList;
    }

    private ArrayList<Action> commandList;

    public CommandFabric() {
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
            System.out.println(" result.getMethod()= "+result.getMethod());
            System.out.println(" result.getPath()= "+result.getPath());

            if ((result.getMethod().equals(method)) & (result.getPath().equals(path))) {
                System.out.println(" have result ");
                break;
            }
            result = null;

        }
        if (result.getName().equals("GetLoginAction")) {
            actionCommand = new GetLoginAction();
            return actionCommand;

        }
        ;
        if (result.getName().equals("LoginAction")) {
            actionCommand = new LoginAction(result);
            return actionCommand;

        }
        ;
        if (result.getName().equals("MarkClientBlackListAction")) {
            actionCommand = new MarkClientBlackListAction();
            return actionCommand;
        }
        ;
        if (result.getName().toString() == "LogoutAction") {
            actionCommand = new LogoutAction();
            return actionCommand;
        }
        ;
        if (result.getName().toString() == "AddProductAction") {
            actionCommand = new AddProductAction();
        }
        ;
        if (result.getName().toString() == "LoginAction") {
            actionCommand = new LoginAction();
        }
        ;
        if (result.getName().toString() == "LoginAction") {
            actionCommand = new LoginAction();
        }
        ;
        if (result.getName().toString() == "LoginAction") {
            actionCommand = new LoginAction();
        }
        ;
        if (result.getName().toString() == "LoginAction") {
            actionCommand = new LoginAction();
        }
        ;
        if (result.getName().toString() == "LoginAction") {
            actionCommand = new LoginAction();
        }
        ;
        return actionCommand;
    }
}
