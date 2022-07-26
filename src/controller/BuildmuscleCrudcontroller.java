package controller;

import Model.Buildmuscle;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuildmuscleCrudcontroller {

        public static ArrayList<String> getbuild() throws SQLException, ClassNotFoundException {
            ResultSet result = CrudUtil.execute("SELECT Day FROM build_muscles");
            ArrayList<String>ids =new ArrayList<>();
            while (result.next()){
                ids.add(result.getString(1));

            }
            return ids;

        }

        public static Buildmuscle getWorkers(String id) throws SQLException, ClassNotFoundException {
            ResultSet result =CrudUtil.execute("SELECT * FROM build_muscles WHERE Day=?",id);

            if(result.next()){
                return new Buildmuscle(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)





                );
            }
            return null;
        }

    }

