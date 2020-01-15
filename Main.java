import java.lang.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try{
            Connection con = null;
            String query = "SELECT * FROM Stock";
            con = Connector.connect();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);

            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }
    }
}