package com.epam.action;

import com.epam.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOrderList extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(GetOrderList.class);
    private com.epam.config.Action action;

    public GetOrderList() {
    }

    public GetOrderList(com.epam.config.Action action) {
        this.action = action;
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
         /*
         String cl=req.getSession().getAttribute("clientId").toString();
         System.out.println("cl="+cl);
         */
        Long clientId = Long.parseLong(req.getSession().getAttribute("clientId").toString());
        req.setAttribute("orderList", OrderService.findOrdersByClient(clientId));
        View view = new View(this.getAction().getView());
        log.debug("ordersclient =" + this.getAction().getView());
        return view;
    }

    public com.epam.config.Action getAction() {
        return action;
    }

    public void setAction(com.epam.config.Action action) {
        this.action = action;
    }

}
