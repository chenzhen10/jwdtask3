package by.epam.kimbar.servlets;

import by.epam.kimbar.beans.Menu;
import by.epam.kimbar.utils.parsers.stax_parser.StaxParser;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StAX")
public class StaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("windows-1251");
        RequestDispatcher rq = request.getRequestDispatcher("stax.jsp");
        List<Menu> menu = StaxParser.parseByStaxParser();

        request.setAttribute("menu", menu);

        rq.forward(request, response);
    }
}
