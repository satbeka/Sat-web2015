package com.epam.action;

import com.epam.config.Action;
import com.epam.service.ClientService;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrderList extends AbstractCommand implements ActionCommand {

     @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
         int clientId= (int) req.getSession().getAttribute("clientId");
         req.setAttribute("orderList", OrderService.findOrdersByClient(clientId));
         View view = new View(this.getAction().getView());
         System.out.println("markclient ="+this.getAction().getView());
         return view;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    public GetOrderList(){};
    public GetOrderList(Action action){this.action=action;};
}
