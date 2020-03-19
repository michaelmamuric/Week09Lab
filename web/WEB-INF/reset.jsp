<%-- 
    Document   : reset
    Created on : Mar 18, 2020, 5:06:00 PM
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
        <h1>Reset Password</h1>
        <div>Please enter your email address below to reset your password.</div>
        <form method="post">
            E-mail address <input type="text" name="email" /><br />
            <input type="submit" name="submit" />
        </form>
        <div>${msg}</div>
    </body>
</html>
