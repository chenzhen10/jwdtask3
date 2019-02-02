package by.epam.kimbar.controller;

import by.epam.kimbar.model.entity.Menu;
import by.epam.kimbar.dao.parser.dom.DomParser;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DomServlet")
public class DomServlet extends HttpServlet {
    private Logger log = null;


    @Override
    public void init() throws ServletException {
        log = Logger.getLogger(DomServlet.class);
        BasicConfigurator.configure();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        List<Menu> menu;
        try {
            menu = DomParser.parseByDomParser();
            request.setAttribute("menu", menu);
        } catch (SAXException e) {
            log.error(e);
        }
        request.getRequestDispatcher("jsp/dom.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("windows-1251");
        RequestDispatcher rq = request.getRequestDispatcher("jsp/dom.jsp");
        List<Menu> menu = null;
        try {
            menu = DomParser.parseByDomParser();
            request.setAttribute("menu",menu);
        } catch (SAXException e) {
            log.error(e);
        }



        rq.forward(request, response);
    }
}
