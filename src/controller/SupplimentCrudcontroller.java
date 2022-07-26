package controller;

import Model.Member;
import Model.Suppliment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplimentCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT suppliment_Id FROM suppliment");
        ArrayList<String> ids = new ArrayList<>();
        while (result.next()) {
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Suppliment getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM suppliment WHERE suppliment_Id=?", id);

        if (result.next()) {
            return new Suppliment(
                    result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getDouble(4)


            );
        }
        return null;
    }

    public static void deleteSup(Suppliment selectedItem) throws SQLException, ClassNotFoundException {

        CrudUtil.execute("DELETE FROM suppliment WHERE suppliment_Id=?", selectedItem.getSuppliment_Id());

    }

    public static ArrayList<Suppliment> searchReportBySupplimentId(String s) {
        ArrayList<Suppliment> arrayList = new ArrayList();

        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM suppliment WHERE suppliment.suppliment_Id LIKE ?", s);
            while (resultSet.next()) {
                arrayList.add(new Suppliment(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4)

                ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;

    }

    public static ObservableList<String> getsupplimentId() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT suppliment_Id FROM suppliment");

        ObservableList<String> obList = FXCollections.observableArrayList();

        while (result.next()) {
            obList.add(result.getString(1));
        }
        return obList;
    }

    public static boolean updateQty(String id,String qty) throws SQLException, ClassNotFoundException {
        int q=Integer.parseInt(qty);
        return CrudUtil.execute("UPDATE suppliment SET suppliment_Quantity=? WHERE suppliment_Id=?",q,id);

    }


}






