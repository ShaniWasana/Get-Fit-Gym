package controller;

import Model.Improving;
import Model.Increase;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IncreaseCrudcontroller {
    public static ArrayList<String> getbuild() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Day FROM increase_fexibility");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Increase getim(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM increase_fexibility WHERE Day=?",id);

        if(result.next()){
            return new Increase(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)





            );
        }
        return null;
    }
}
