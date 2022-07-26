package controller;

import Model.Buildmuscle;
import Model.Improving;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImprovingCrudcontroller {
    public static ArrayList<String> getbuild() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Day FROM improving_endurance");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Improving getim(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM improving_endurance WHERE Day=?",id);

        if(result.next()){
            return new Improving(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)





            );
        }
        return null;
    }

}

