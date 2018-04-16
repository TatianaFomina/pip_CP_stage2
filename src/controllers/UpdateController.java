package controllers;

import beans.IContactsBean;
import beans.IKidBean;
import beans.IParentBean;
import beans.IPeopleBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateController extends HttpServlet implements Respondent{

    @EJB
    private IPeopleBean peopleBean;

    @EJB
    private IParentBean parentBean;

    @EJB
    private IContactsBean contactsBean;

    @EJB
    private IKidBean kidBean;



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String[] pathArr = path.split("/");
        try {


            //responseBlank(resp, name + " " + middleName + " " + surname + " " + sex + " " + dateOfBirth);
            //peopleBean.add(name, middleName, surname, Boolean.parseBoolean(sex), date);
            boolean success = false;
            String personId = null;
            String parentId = null;
            switch (pathArr[2]) {
                case "person":
                    personId = req.getParameter("id");
                    String name = req.getParameter("name");
                    String middleName = req.getParameter("middle_name");
                    String surname = req.getParameter("surname");
                    String sex = req.getParameter("sex");
                    String dateOfBirth = req.getParameter("date_of_birth");
                    Date date = null;
                    if ((dateOfBirth != null) && !dateOfBirth.equals("")) {
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        try {
                            date = format.parse(dateOfBirth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    success = peopleBean.update(Long.parseLong(personId), name, middleName, surname, Boolean.parseBoolean(sex), date);
                    break;
                case "parent":
                    personId = req.getParameter("person_id");
                    parentId = req.getParameter("parent_id");
                    success = parentBean.update(Long.parseLong(parentId), Long.parseLong(personId));
                    break;
                case "contacts":
                    String contactsId = req.getParameter("contacts_id");
                    parentId = req.getParameter("parent_id");
                    String address = req.getParameter("address");
                    String job = req.getParameter("job");
                    String jobPhoneNumber = req.getParameter("job_phone_number");
                    String cellPhoneNumber = req.getParameter("cell_phone_number");
                    success = contactsBean.update(Long.parseLong(contactsId), Long.parseLong(parentId), address, job, jobPhoneNumber, cellPhoneNumber);
                    break;
                case "kid":
                    String kidId = req.getParameter("kid_id");
                    personId = req.getParameter("person_id");
                    String parent1Id = req.getParameter("parent1_id");
                    String parent2Id = req.getParameter("parent2_id");
                    success = kidBean.update(Long.parseLong(kidId), Long.parseLong(personId), Long.parseLong(parent1Id), Long.parseLong(parent2Id));
                    break;
            }
            if (!success)
                throw new Exception();
        }catch (Exception e){
            responseBlank(resp, "Unable to update record");
        }

        responseBlank(resp, "Record has been updated!");

    }
}
