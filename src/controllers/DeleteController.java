package controllers;

import beans.IParentBean;
import beans.IPeopleBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteController extends HttpServlet implements Respondent{

    @EJB
    private IPeopleBean peopleBean;

    @EJB
    private IParentBean parentBean;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String[] pathArr = path.split("/");
        String id = req.getParameter("id");
        boolean success = false;
        switch (pathArr[2]) {
            case "people":
                success = peopleBean.delete(Long.parseLong(id));
                break;
            case "parent":
                success = parentBean.delete(Long.parseLong(id));
                break;
        }

        if (success)
            responseBlank(resp, "Record has been deleted!");
        else responseBlank(resp, "There's no record with such id");
    }

}
