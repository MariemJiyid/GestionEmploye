<%@ page import="java.util.*,model.Employe" %>
 
<h2>Liste des employés</h2>

<a href="employe-form.jsp">Ajouter un employé</a>

<br><br>

<table border="1">

<tr>
<th>ID</th>
<th>Nom</th>
<th>Prenom</th>
<th>Poste</th>
<th>Salaire</th>
<th>Actions</th>
</tr>

<%
List<Employe> liste = (List<Employe>) request.getAttribute("listeEmployes");

for(Employe e : liste){
%>

<tr>

<td><%= e.getId() %></td>
<td><%= e.getNom() %></td>
<td><%= e.getPrenom() %></td>
<td><%= e.getPoste() %></td>
<td><%= e.getSalaire() %></td>

<td>

<a href="edit?id=<%= e.getId() %>">Modifier</a>

|
<a href="delete?id=<%= e.getId() %>">Supprimer</a>

</td>

</tr>

<%
}
%>

</table>