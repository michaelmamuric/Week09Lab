<%-- 
    Document   : resetNewPassword
    Created on : Mar 18, 2020, 7:06:44 PM
    Author     : 799470
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Enter a new password</h1>
        <form method="post">
            <input type="hidden" name="uuid" value="${uuid}" />
            <input type="password" name="newpassword" /><br />
            <input type="submit" name="submit" />
        </form>
            <div>${msg}</div>
    </body>
</html>
