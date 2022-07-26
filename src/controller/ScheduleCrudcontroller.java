package controller;


import Model.Schedule;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT schedule_Id FROM schedule");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Schedule getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM schedule WHERE schedule_Id=?",id);

        if(result.next()){
            return new Schedule(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)





            );
        }
        return null;
    }

}

