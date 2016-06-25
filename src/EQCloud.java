/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Marcos
 */
public class EQCloud {
   
    static String userName = "postgres";
    static String userPassword = "di4sjje7";
    static String databaseUrl =
    "jdbc:postgresql://localhost:5432/Electronic_Queue";
    //*/
    static String userQuery = "select * from owner";

    
    public static String [] dbTest() {
    
        String res = "false";
        String [] strArr = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);
            
           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = userQuery;
            ResultSet result = stat.executeQuery(query);

            while(result.next()){
                if (res.equals("false"))
                    res = result.getString("login");
                else
                    res += " " + result.getString("login");
            }

            strArr = res.split(" ");

            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return strArr;
    }

    public String plus_marco ( String in ){
        return in + "_Marco";
    }

    public String [] viewQueue () {
        
        String res = "false";
        String [] strArr = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);

           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "SELECT id, name, last_password_called, next_password_available FROM queues";
            ResultSet result = stat.executeQuery(query);
            
            //res = result.getString("name");
            //res = resArr.toString();

            while(result.next()){
                if (res.equals("false")){
                    res = result.getString("id");
                    res += ":" + result.getString("name").trim();
                    res += ":" + result.getString("last_password_called");
                    res += ":" + result.getString("next_password_available");
                }    
                else {
                    res += "&" + result.getString("id");
                    res += ":" + result.getString("name").trim();
                    res += ":" + result.getString("last_password_called");
                    res += ":" + result.getString("next_password_available");
                }
            }

            strArr = res.split("&");
            /*
            System.out.println("Result(s): ");
            while(result.next()){
                System.out.println("Name:\t" + result.getString("name"));
                System.out.println("");
            }
            */
            //res = "true";
            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return strArr;
    
    }

    public String [] searchQueueName (String search){

        String res = "false";
        String [] strArr = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);
           
           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "SELECT id, name, last_password_called, "
                    + "next_password_available FROM queues WHERE name LIKE '" + search + "%'";
            
            ResultSet result = stat.executeQuery(query);          

            while(result.next()){
                if (res.equals("false")){
                    res = result.getString("id");
                    res += ":" + result.getString("name").trim();
                    res += ":" + result.getString("last_password_called");
                    res += ":" + result.getString("next_password_available");
                }    
                else {
                    res += "&" + result.getString("id");
                    res += ":" + result.getString("name").trim();
                    res += ":" + result.getString("last_password_called");
                    res += ":" + result.getString("next_password_available");
                }
            }

            strArr = res.split("&");

            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return strArr;
    }

    public String searchQueueId (Integer search){

        String res = "false";
        
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);

           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "SELECT id, name, last_password_called, "
                    + "next_password_available FROM queues WHERE id='" + search + "'";
            
            ResultSet result = stat.executeQuery(query);

            while(result.next()){
                
                res = result.getString("id");
                res += ":" + result.getString("name").trim();
                res += ":" + result.getString("last_password_called");
                res += ":" + result.getString("next_password_available");
                
            }

            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return res;
    }

    public String enterQueue (Integer queue){

        String res = "false";
        boolean update = false;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);

           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "SELECT next_password_available FROM queues WHERE id='" + queue + "'";
            ResultSet result = stat.executeQuery(query);
            
            while(result.next()){
                    res = result.getString("next_password_available");
                }
                  
            query = "UPDATE queues SET next_password_available = "
                    + "next_password_available + 1 WHERE id='" + queue + "'";
            
            while (!update){              
                if (stat.executeUpdate(query) > 0)
                    update = true;             
            }

            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return res;
    }


    public String createQueue (String name, Integer owner){

        String res = "false";
        boolean update = false;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);
            
           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "INSERT INTO queues (name, last_password_called, "
                    + "next_password_available, owner_id) VALUES ('" + name + "', 0, 1, " + owner + ")";
                          
            if (stat.executeUpdate(query) > 0)
                update = true;
                        
            if (update)
                res = "true";

            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return res;
    }

    public String createController (String name, String login, String password, Integer owner){

        String res = "false";
        boolean update = false;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);
           
           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "INSERT INTO controllers (name, login, password, owner_id) "
                    + "VALUES ('" + name + "','" + login + "', '" + password + "', " + owner + ")";
                          
            if (stat.executeUpdate(query) > 0)
                update = true;
                        
            if (update)
                res = "true";

            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return res;
    }

    public String consumeQueue (Integer queue){

        String res = "false";
        boolean update = false;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);
          
           // System.out.println("database connected");
            Statement stat = conn.createStatement();
                  
            String query = "UPDATE queues SET last_password_called = "
                    + "last_password_called + 1 WHERE id='" + queue + "'";
                                    
            if (stat.executeUpdate(query) > 0)
                update = true;
            
            if (update)
                res = "true";
            
            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return res;
    }

    public String createOwner (String companyName, String login, String password){

        String res = "false";
        boolean update = false;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl,userName, userPassword);

           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "INSERT INTO owners (login, password) VALUES "
                    + "('" + companyName + "', '" + login + "', '" + password + "')";
                          
            if (stat.executeUpdate(query) > 0)
                update = true;
                        
            if (update)
                res = "true";

            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return res;
    }

    public String authenticateOwner (String login, String password){

        String res = "false";

        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);
          
           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "SELECT id, password FROM owners WHERE login = '" + login + "'";                        
            ResultSet result = stat.executeQuery(query);        
            
            if (result.next()){
                
                res = result.getString("password");
                res = res.trim();
                
                if (res.equals(password))
                    res = result.getString("id");
                else               
                    res = "Wrong Password";
            }            
            else             
                res = "Inexistent Username";
                   
            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return res;
    }

    public String authenticateController (String login, String password){

        String res = "false";

        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance ();
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection (databaseUrl, userName, userPassword);
           
           // System.out.println("database connected");
            Statement stat = conn.createStatement();
            
            String query = "SELECT owner_id, password FROM controllers WHERE login = '" + login + "'";                        
            ResultSet result = stat.executeQuery(query);
            
            if (result.next()){
                
                res = result.getString("password");
                res = res.trim();
                
                if (res.equals(password)){
                    res = result.getString("owner_id");
                }
                else               
                    res = "Wrong Password";
            }            
            else             
                res = "Inexistent Username";

            conn.close();

        }catch (SQLException e) {e.printStackTrace();}
        //catch (InstantiationException e) {System.out.println("InstantiationException");}
        //catch (IllegalAccessException e) {System.out.println("IllegalAccessException");}
        catch (ClassNotFoundException e) {e.printStackTrace();}

        return res;
    }
}

