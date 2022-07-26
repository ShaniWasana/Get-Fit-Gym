package controller;

import Model.Packages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PackageCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT package_Id FROM package");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Packages getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM package WHERE package_Id=?",id);

        if(result.next()){
            return new Packages(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5)





            );
        }
        return null;
    }

    public static ObservableList<String> getPackageId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT package_Id FROM package");

        ObservableList<String> obList = FXCollections.observableArrayList();

        while (result.next()) {
            obList.add(result.getString(1));
        }
        return obList;
    }
}

