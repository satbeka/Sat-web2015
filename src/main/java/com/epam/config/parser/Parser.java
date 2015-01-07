package com.epam.config.parser;


import com.epam.config.Eshop;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class Parser {

  public Eshop parser() {

      Eshop eshop= null;
      try {
          JAXBContext jc = JAXBContext.newInstance(Eshop.class);
          Unmarshaller u = jc.createUnmarshaller();
          //FileReader reader = new FileReader();
          InputStream xmlInput = Parser.class.getClassLoader().getResourceAsStream("eshopConfig.xml");

          eshop= (Eshop) u.unmarshal(xmlInput);
          System.out.println(eshop.toString());
          return eshop;

      } catch (JAXBException e) {
          System.out.println(e.toString());
      }
      return eshop;
}
}
