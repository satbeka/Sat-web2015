package com.epam.action;

import com.epam.config.Action;
import com.epam.service.ProductExtQuantityService;
import com.epam.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProductListForOrder extends AbstractCommand implements ActionCommand {
    private static final Logger log = LoggerFactory.getLogger(GetProductListForOrder.class);

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        //Object atrr=req.getSession().getAttribute("orderId");
        //System.out.println("req.getRequestURI()="+req.getRequestURI());
        //String[] listMarkId = req.getParameterValues("orderId");
        log.debug("req.getQueryString()=" + req.getQueryString());
        Long orderId = Long.parseLong(req.getQueryString().toString().replace("orderId=", ""));
        req.getSession().setAttribute("orderId", orderId);
        //String orderNumber=req.getQueryString().toString().replace("orderNumber=","");
        //req.getSession().setAttribute("orderNumber",orderNumber);

        req.setAttribute("productList", ProductExtQuantityService.findProductsForOrder(orderId));
        View view = new View(this.getAction().getView());
        log.debug("GetProductListForOrder=" + this.getAction().getView());
        return view;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    public GetProductListForOrder() {
    }

    ;

    public GetProductListForOrder(Action action) {
        this.action = action;
    }

    ;
}
