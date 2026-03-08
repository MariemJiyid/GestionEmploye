<%@ page contentType="text/html;charset=UTF-8" %>

<form action="EmployeServlet" method="post">
Nom : <input type="text" name="nom"/><br>
Prenom : <input type="text" name="prenom"/><br>
Poste : <input type="text" name="poste"/><br>
Salaire : <input type="text" name="salaire"/><br>
<input type="submit" value="Ajouter Employé">


</form>

<br>

<a href="EmployeServlet">Voir la liste</a>