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

    public ActionCommand getCommand(String method, String path) {
        ActionCommand actionCommand = null;
        ArrayList<Action> listAction = new ArrayList<Action>();
        listAction = this.getCommandList();
        Action result = null;
        for (Iterator<Action> i = listAction.iterator(); i.hasNext(); ) {
            result = i.next();
            if ((result.getMethod() == method) & (result.getPath() == path)) {
            } else {
                result = null;
            }
        }
        if (result.getName().toString() == "LoginAction") {
            actionCommand = new LoginAction(result);

        }
        ;
        if (result.getName().toString() == "MarkClientBlackListAction") {
            actionCommand = new MarkClientBlackListAction();
        }
        ;
        if (result.getName().toString() == "LogoutAction") {
            actionCommand = new LogoutAction();
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
