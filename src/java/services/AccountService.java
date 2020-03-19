/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import models.User;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author awarsyle
 */
public class AccountService {

    public User login(String username, String password, String path) {
        try {
            UserDB userDB = new UserDB();
            User user = userDB.getUser(username);

            if (user.getPassword().equals(password)) {
                // successful login
                Logger.getLogger(AccountService.class.getName())
                        .log(Level.INFO, "User {0} logged in.", user.getUsername());
                
                // send email upon successful login
                //GmailService.sendMail(user.getEmail(), "Notes App Login",
                //        "Hi " + user.getFirstname() + "\nYou just logged in.", false);
                String email = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstname());
                tags.put("date", ((new java.util.Date())).toString());
                
                GmailService.sendMail(email, subject, template, tags);
                
                return user;
            }
        } catch (Exception e) {

        }

        return null;
    }
    
    public boolean forgotPassword(String email) {
        boolean success = false;
        UserService us = new UserService();
        User getUserByEmail = us.getByEmail(email);
        
        // Use String Builder to buld message
        StringBuilder buildMsg = new StringBuilder();
        buildMsg.append("Hi ").append(getUserByEmail.getFirstname()).append(" ").append(getUserByEmail.getLastname()).append(", ");
        buildMsg.append(System.lineSeparator());
        buildMsg.append("Here are your credentials to log back into Notes Keeper.");
        buildMsg.append(System.lineSeparator());
        buildMsg.append("User name: ").append(getUserByEmail.getUsername());
        buildMsg.append(System.lineSeparator());
        buildMsg.append("Password: ").append(getUserByEmail.getPassword());
        
        // Get Message
        String msg = buildMsg.toString();
        
        try {
            GmailService.sendMail(getUserByEmail.getEmail(), "Forgot Password", msg, false);
            success = true;
        } catch (MessagingException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return success;
    }
    
    public boolean resetPassword(String email, String path, String url) {
        boolean success = false;
        UserService us = new UserService();
        UserDB ud = new UserDB();
        User getUserByEmail = us.getByEmail(email);
        
        String uuid = UUID.randomUUID().toString();
        String to = getUserByEmail.getEmail();
        String subject = "Reset Password";
        String template = path + "/emailtemplates/resetpassword.html";
        String link = url + "?uuid=" + uuid;
        
        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstname", getUserByEmail.getFirstname());
        tags.put("lastname", getUserByEmail.getLastname());
        tags.put("username", getUserByEmail.getUsername());
        tags.put("link", link);
        
        try {
            getUserByEmail.setResetPasswordUUID(uuid);
            ud.update(getUserByEmail);
            GmailService.sendMail(email, subject, template, tags);
            success = true;
        } catch(Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        return success;
    }
    
    public boolean changePassword(String uuid, String password) {
        boolean success = false;
        
        UserService us = new UserService();
        try {
            User user = us.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUUID(null);
            UserDB ur = new UserDB();
            ur.update(user);
            success = true;
        } catch (Exception ex) {
            System.out.println(ex.toString());          
        }
            
        return success;
    }
}
