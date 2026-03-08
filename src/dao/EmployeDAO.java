package dao;

import java.sql.*;
import java.util.*;
import model.Employe;

public class EmployeDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/entreprise";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_EMP =
            "INSERT INTO employe (nom,prenom,poste,salaire) VALUES (?,?,?,?)";

    private static final String SELECT_ALL =
            "SELECT * FROM employe";

    private static final String DELETE_EMP =
            "DELETE FROM employe WHERE id=?";

    private static final String SELECT_BY_ID =
            "SELECT * FROM employe WHERE id=?";

    private static final String UPDATE_EMP =
            "UPDATE employe SET nom=?, prenom=?, poste=?, salaire=? WHERE id=?";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertEmploye(Employe emp) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_EMP);

        ps.setString(1, emp.getNom());
        ps.setString(2, emp.getPrenom());
        ps.setString(3, emp.getPoste());
        ps.setDouble(4, emp.getSalaire());

        ps.executeUpdate();
    }

    public List<Employe> selectAllEmployes() {

        List<Employe> employes = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String poste = rs.getString("poste");
                double salaire = rs.getDouble("salaire");

                employes.add(new Employe(id,nom,prenom,poste,salaire));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return employes;
    }

    public void deleteEmploye(int id) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_EMP);

        ps.setInt(1, id);

        ps.executeUpdate();
    }

    public Employe selectEmploye(int id) {

        Employe emp = null;

        try {

            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String poste = rs.getString("poste");
                double salaire = rs.getDouble("salaire");

                emp = new Employe(id,nom,prenom,poste,salaire);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return emp;
    }

    public void updateEmploye(Employe emp) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(UPDATE_EMP);

        ps.setString(1, emp.getNom());
        ps.setString(2, emp.getPrenom());
        ps.setString(3, emp.getPoste());
        ps.setDouble(4, emp.getSalaire());
        ps.setInt(5, emp.getId());

        ps.executeUpdate();
    }
}