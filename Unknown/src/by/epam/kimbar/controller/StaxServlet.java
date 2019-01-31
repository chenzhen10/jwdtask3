package by.epam.kimbar.controller;

import by.epam.kimbar.model.entity.Menu;
import by.epam.kimbar.dao.parsers.stax_parser.StaxParser;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "StAX")
public class StaxServlet extends HttpServlet {
    private  Logger log  = null;


    @Override
    public void init() throws ServletException {
        log = Logger.getRootLogger();
        BasicConfigurator.configure();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        List<Menu> menu = null;
        try {
            menu = StaxParser.parseByStaxParser();
        } catch (XMLStreamException e) {
            log.info(e);
        }
        request.setAttribute("menu", menu);
        request.getRequestDispatcher("jsp/stax.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("windows-1251");
        RequestDispatcher rq = request.getRequestDispatcher("jsp/stax.jsp");
        List<Menu> menu = null;
        try {
            menu = StaxParser.parseByStaxParser();
        } catch (XMLStreamException e) {
           log.info(e);
        }

        request.setAttribute("menu", menu);
        rq.forward(request, response);
    }
}
