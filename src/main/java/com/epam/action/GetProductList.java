package com.epam.action;

import com.epam.config.Action;
import com.epam.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductList extends AbstractCommand implements ActionCommand {

     @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
         req.setAttribute("lst", ProductService.findProductsByAdministrator());
         View view = new View(this.getAction().getView());
         System.out.println("AddProduct="+this.getAction().getView());
         return view;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    public GetProductList(){};
    public GetProductList(Action action){this.action=action;};
}
