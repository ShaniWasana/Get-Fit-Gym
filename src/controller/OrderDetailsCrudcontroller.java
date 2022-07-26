package controller;

import Model.LoseFat;
import Model.Member;
import Model.Order;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT order_Id FROM orders");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }
    public static double getUnitPrice(String supplimentId) throws SQLException, ClassNotFoundException {
         ResultSet result =CrudUtil.execute("SELECT sup_Price FROM suppliment WHERE suppliment_Id=?",supplimentId);
        if(result.next()){
        return  result.getDouble(1);
        }

        return 0;
    }
    public static Order getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM orders WHERE order_Id=?",id);

        if(result.next()){
            return new Order(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)





            );
        }
        return null;
    }

    public static ArrayList<Order> searchReportByOrderId(String s) {
        ArrayList<Order> arrayList = new ArrayList();

        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM orders WHERE orders.order_Id LIKE ?",s);
            while(resultSet.next()){
                arrayList.add(new Order(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;

    }

    public static void deleteOrder(Order selectedItem) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("DELETE FROM orders WHERE order_Id=?",selectedItem.getOrder_Id());
    }
}

