package by.epam.kimbar.controller;

import by.epam.kimbar.model.entity.Menu;
import by.epam.kimbar.dao.parser.sax_parser.SaxParser;

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

@WebServlet(name = "SAXServlet")
public class SaxServlet extends HttpServlet {
    private Logger log  = null;


    @Override
    public void init() throws ServletException {
        log = Logger.getLogger(SaxServlet.class);
        BasicConfigurator.configure();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        List<Menu> menu;
        try {
            menu = SaxParser.parseBySaxAndWriteOutput();
            request.setAttribute("menu",menu);
        } catch (SAXException e) {
            log.error(e);
        }
        request.getRequestDispatcher("jsp/sax.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("windows-1251");
        RequestDispatcher rq = request.getRequestDispatcher("jsp/sax.jsp");
        List<Menu> menu;
        try {
            menu = SaxParser.parseBySaxAndWriteOutput();
            request.setAttribute("menu",menu);
        } catch (SAXException e) {
            log.error(e);
        }
        rq.forward(request, response);


    }
}
