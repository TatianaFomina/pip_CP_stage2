<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 16.04.2018
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="post_data/kid">
        <h3>Adding a new kid </h3>
        <p>
            <label>Person id: </label>
            <input placeholder="not null" name="person_id" required>
        </p>
        <p>
            <label>Parent1 id: </label>
            <input placeholder="not null" name="parent1_id" required>
        </p>
        <p>
            <label>Parent2 id: </label>
            <input placeholder="" name="parent2_id" >
        </p>
        <button type="submit">Add</button>
    </form>
</body>
</html>
