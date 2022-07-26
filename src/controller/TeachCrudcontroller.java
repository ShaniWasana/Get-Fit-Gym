package controller;

import Model.Conduct;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeachCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT  trainer_Id FROM teach");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Conduct getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM teach WHERE trainer_Id=?",id);

        if(result.next()){
            return new Conduct(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)





            );
        }
        return null;
    }


}
