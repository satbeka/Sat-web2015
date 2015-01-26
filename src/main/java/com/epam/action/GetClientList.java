package com.epam.action;

import com.epam.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetClientList extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(GetClientList.class);
    private com.epam.config.Action action;

    public GetClientList() {
    }

    public GetClientList(com.epam.config.Action action) {
        this.action = action;
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("lst", ClientService.findClientsByAdministrator());
        View view = new View(this.getAction().getView());
        log.debug("GetClientList =" + this.getAction().getView());
        return view;
    }

    public com.epam.config.Action getAction() {
        return action;
    }

    public void setAction(com.epam.config.Action action) {
        this.action = action;
    }

}
