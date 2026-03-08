package servlet;

import dao.EmployeDAO;
import model.Employe;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/EmployeServlet")
public class EmployeServlet extends HttpServlet {

    private EmployeDAO employeDAO;

    public void init() {
        employeDAO = new EmployeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            if(action == null) {
                listEmployes(request,response);
            }

            else if(action.equals("new")) {
                showForm(request,response);
            }

            else if(action.equals("insert")) {
                insertEmploye(request,response);
            }

            else if(action.equals("delete")) {
                deleteEmploye(request,response);
            }

            else if(action.equals("edit")) {
                showEditForm(request,response);
            }

            else if(action.equals("update")) {
                updateEmploye(request,response);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void listEmployes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Employe> list = employeDAO.selectAllEmployes();

        request.setAttribute("listeEmployes", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("employe-list.jsp");

        dispatcher.forward(request,response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("employe-form.jsp");

        dispatcher.forward(request,response);
    }

    private void insertEmploye(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String poste = request.getParameter("poste");
        double salaire = Double.parseDouble(request.getParameter("salaire"));

        Employe emp = new Employe(nom,prenom,poste,salaire);

        employeDAO.insertEmploye(emp);

        response.sendRedirect("EmployeServlet");
    }

    private void deleteEmploye(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        employeDAO.deleteEmploye(id);

        response.sendRedirect("EmployeServlet");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Employe emp = employeDAO.selectEmploye(id);

        request.setAttribute("employe", emp);

        RequestDispatcher dispatcher = request.getRequestDispatcher("employe-form.jsp");

        dispatcher.forward(request,response);
    }

    private void updateEmploye(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String poste = request.getParameter("poste");
        double salaire = Double.parseDouble(request.getParameter("salaire"));

        Employe emp = new Employe(id,nom,prenom,poste,salaire);

        employeDAO.updateEmploye(emp);

        response.sendRedirect("EmployeServlet");
    }
}