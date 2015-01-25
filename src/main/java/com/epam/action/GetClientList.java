package com.epam.action;

import com.epam.config.Action;
import com.epam.service.ClientService;
import com.epam.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetClientList extends AbstractCommand implements ActionCommand {
    private static final Logger log = LoggerFactory.getLogger(GetClientList.class);

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("lst", ClientService.findClientsByAdministrator());
        View view = new View(this.getAction().getView());
        log.debug("GetClientList =" + this.getAction().getView());
        return view;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    public GetClientList() {
    }

    ;

    public GetClientList(Action action) {
        this.action = action;
    }

    ;
}
