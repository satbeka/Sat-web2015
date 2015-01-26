package com.epam.action;

import com.epam.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductList extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(GetProductList.class);
    private com.epam.config.Action action;

    public GetProductList() {
    }

    public GetProductList(com.epam.config.Action action) {
        this.action = action;
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("lst", ProductService.findProductsByAdministrator());
        View view = new View(this.getAction().getView());
        log.debug("AddProduct=" + this.getAction().getView());
        return view;
    }

    public com.epam.config.Action getAction() {
        return action;
    }

    public void setAction(com.epam.config.Action action) {
        this.action = action;
    }

}
