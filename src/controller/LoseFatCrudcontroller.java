package controller;


import Model.LoseFat;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoseFatCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Day FROM lose_fat");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static LoseFat getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM lose_fat WHERE Day=?",id);

        if(result.next()){
            return new LoseFat(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)





            );
        }
        return null;
    }

}

