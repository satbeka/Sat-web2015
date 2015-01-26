package com.epam.action;

import com.epam.config.Eshop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ActionFactory {

    private static final Logger log = LoggerFactory.getLogger(ActionFactory.class);
    private Map<String, Action> actions;

/*
    public ArrayList<com.epam.config.Action> getCommandList() {
        return commandList;
    }

    private ArrayList<com.epam.config.Action> commandList;
*/

    public ActionFactory(Eshop config) {
        actions = new HashMap<>();
        //com.epam.config.Action result=ActionFactory.GetActionResult("AdministratorWhatDo", config);
        actions.put("AdministratorWhatDo", new AdministratorWhatDo(ActionFactory.GetActionResult("AdministratorWhatDo", config)));
        actions.put("GetProductList", new GetProductList(ActionFactory.GetActionResult("GetProductList", config)));
        //actions.put("EditProduct", new EditProduct());
        actions.put("SaveProduct", new SaveProduct(ActionFactory.GetActionResult("SaveProduct", config)));
        actions.put("GetClientList", new GetClientList(ActionFactory.GetActionResult("GetClientList", config)));
        actions.put("SaveClientList", new SaveClientList(ActionFactory.GetActionResult("SaveClientList", config)));
        actions.put("Login", new Login(ActionFactory.GetActionResult("Login", config)));

        actions.put("GetLogin", new GetLogin(ActionFactory.GetActionResult("GetLogin", config)));
        actions.put("Logout", new Logout(ActionFactory.GetActionResult("Logout", config)));
        actions.put("ClientWhatDo", new ClientWhatDo(ActionFactory.GetActionResult("ClientWhatDo", config)));
        actions.put("GetOrderList", new GetOrderList(ActionFactory.GetActionResult("GetOrderList", config)));
        actions.put("SaveBlankOrder", new SaveBlankOrder(ActionFactory.GetActionResult("SaveBlankOrder", config)));
        actions.put("GetProductListForOrder", new GetProductListForOrder(ActionFactory.GetActionResult("GetProductListForOrder", config)));

        actions.put("SaveProductListForOrder", new SaveProductListForOrder(ActionFactory.GetActionResult("SaveProductListForOrder", config)));
        actions.put("PayOrder", new PayOrder(ActionFactory.GetActionResult("PayOrder", config)));
    }

    public static com.epam.config.Action GetActionResult(String nameAction, Eshop config) {
        com.epam.config.Action res = null;
        for (com.epam.config.Action item : config.getList()) {
            if (item.getName().equals(nameAction)) {
                return item;
            }
        }

        return res;
    }

    /*
    public void LoadEshopConfig(Eshop eshop) {

        this.commandList = new ArrayList<com.epam.config.Action>();
        for (Iterator<com.epam.config.Action> i = eshop.getList().iterator(); i.hasNext(); ) {
            this.commandList.add(i.next());
        }
    }
    */

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }

    public Action defineCommand(String method, String path, Eshop config) {
        Action action = null;
        com.epam.config.Action result = null;
        for (Iterator<com.epam.config.Action> i = config.getList().iterator(); i.hasNext(); ) {
            result = i.next();
            log.debug(" result.getMethod()= " + result.getMethod());
            log.debug(" result.getPath()= " + result.getPath());

            if ((result.getMethod().equals(method)) & (result.getPath().equals(path))) {
                log.debug(" have result= " + result.getName());
                break;
            }
            result = null;

        }
        log.debug(" method= " + method);
        log.debug(" path= " + path);

        if (result == null) {
            return action;
        }

        action = this.getAction(result.getName());
        return action;

        /*
        if (result.getName().equals("GetLogin")) {
            action = new GetLogin(result);
            return action;

        }
        ;
        if (result.getName().equals("Login")) {
            action = new Login(result);
            return action;

        }
        ;
        //AdministratorWhatDo
        if (result.getName().equals("AdministratorWhatDo")) {
            action = new AdministratorWhatDo(result);
            return action;
        }
        ;
        if (result.getName().equals("Logout")) {
            action = new Logout(result);
            return action;
        }
        ;
        if (result.getName().equals("GetProductList")) {
            action = new GetProductList(result);
        }
        ;
        if (result.getName().equals("SaveProduct")) {
            action = new SaveProduct(result);
        }
        ;
        if (result.getName().equals("GetClientList")) {
            action = new GetClientList(result);
        }
        ;
        if (result.getName().equals("SaveClientList")) {
            action = new SaveClientList(result);
        }
        ;
        if (result.getName().equals("ClientWhatDo")) {
            action = new ClientWhatDo(result);
        }
        ;
        if (result.getName().equals("GetOrderList")) {
            action = new GetOrderList(result);
        }
        ;
        if (result.getName().equals("SaveBlankOrder")) {
            action = new SaveBlankOrder(result);
        }
        ;
        if (result.getName().equals("GetProductListForOrder")) {
            action = new GetProductListForOrder(result);
        }
        ;
        if (result.getName().equals("SaveProductListForOrder")) {
            action = new SaveProductListForOrder(result);
        }
        ;
        if (result.getName().equals("PayOrder")) {
            action = new PayOrder(result);
        }
        ;

        return action;
        */
    }
}
