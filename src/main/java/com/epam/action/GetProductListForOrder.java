package com.epam.action;

import com.epam.config.Action;
import com.epam.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductListForOrder extends AbstractCommand implements ActionCommand {

     @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
         Long orderId= Long.parseLong(req.getSession().getAttribute("orderId").toString());

         req.setAttribute("productList", ProductService.findProductsForOrder(orderId));
         View view = new View(this.getAction().getView());
         System.out.println("GetProductListForOrder="+this.getAction().getView());
         return view;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    public GetProductListForOrder(){};
    public GetProductListForOrder(Action action){this.action=action;};
}
