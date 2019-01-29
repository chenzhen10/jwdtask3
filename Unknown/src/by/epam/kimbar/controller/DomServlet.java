package by.epam.kimbar.controller;

import by.epam.kimbar.model.entity.Menu;
import by.epam.kimbar.dao.parsers.dom.DomParser;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("windows-1251");
        RequestDispatcher rq = request.getRequestDispatcher("jsp/dom.jsp");
        List<Menu> menu;
        try {
            menu = DomParser.parseByDomParser();

            request.setAttribute("menu", menu);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        rq.forward(request, response);

    }
}
