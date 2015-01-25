package com.epam.config.parser;


import com.epam.config.Eshop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    public Eshop parser() {

        Eshop eshop = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(Eshop.class);
            Unmarshaller u = jc.createUnmarshaller();
            //FileReader reader = new FileReader();
            InputStream xmlInput = Parser.class.getClassLoader().getResourceAsStream("eshopConfig.xml");

            eshop = (Eshop) u.unmarshal(xmlInput);
            log.debug(eshop.toString());
            return eshop;

        } catch (JAXBException e) {
            log.debug("JAXBException=" + e.toString());
        }
        return eshop;
    }
}
