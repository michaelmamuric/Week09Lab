<%-- 
    Document   : forgot
    Created on : Mar 18, 2020, 3:04:37 PM
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
        <h1>Forgot Password</h1>
        <div>Please enter your e-mail address to retrieve your password</div>
        <form method="post">
            E-mail address: 
            <input type="text" name="email" />
            <br />
            <input type="submit" name="submit" value="Submit" />
        </form>
        <div>${msg}</div>
    </body>
</html>
