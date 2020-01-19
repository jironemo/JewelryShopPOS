import java.lang.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try{
            Item i = new Item("sfkldjg", "အးေး", new Weight(1,2,3), new Weight(0,1,1));
            Connection con = null;
                String query =  "INSERT INTO STOCK VALUES ("+" '"+i.getId()+"', '"+i.getName()+"', '"+i.getWeight().getString()+"','"+i.depreciation().getString()+"');";
            con = Connector.connect();
            Statement s = con.createStatement();
            s.executeQuery(query);
        }
        catch(SQLException sq){
            sq.printStackTrace();
        }
    }
}