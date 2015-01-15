package com.epam.action;

import com.epam.config.Action;
import com.epam.dao.factory.DAOFactory;
import com.epam.dao.product.H2ProductDAO;
import com.epam.dao.product.ProductDAO;
import com.epam.dao.user.H2UserDAO;
import com.epam.dao.user.UserDAO;
import com.epam.db.ConnectionPool;
import com.epam.model.Product;
import com.epam.model.User;
import com.epam.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


public class SaveProduct extends AbstractCommand implements ActionCommand{
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String name=req.getParameter("name");
        String price=req.getParameter("price");
        View view = new View(this.getAction().getView());
        if (!Validator.isPriceCorrect(price)){
            view.setName("errors/administrator");
            req.getSession().setAttribute("pricenotcorrect", " PRICE not correct!");
            return view;
        };

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ProductDAO productDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getProductDAO();
        H2ProductDAO h2ProductDAO = (H2ProductDAO) productDAO;
        h2ProductDAO.setConnection(connectionPool);
        Product product =new Product();
        product.setName(name);
        product.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));

        long id=((H2ProductDAO) productDAO).insertProduct(product);
        if (id == -1) {
            //req.setAttribute();
            view.setName("errors/admin");
            return view;
        };
        view.setRedirect(true);
        System.out.println("saveproduct view.getName()=" + view.getName());
        return view;
    };


    public SaveProduct() {};
    public SaveProduct(Action action){
        this.action=action;
    };


}
