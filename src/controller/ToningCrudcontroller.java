package controller;

import Model.Toning;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToningCrudcontroller {
    public static ArrayList<String> getWorkersIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Day FROM toning");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Toning getWorkers(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM toning WHERE Day=?",id);

        if(result.next()){
            return new Toning(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)




            );
        }
        return null;
    }

}
