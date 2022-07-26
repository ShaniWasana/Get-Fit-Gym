package controller;

import Model.Order;
import Model.Payments;
import Model.Trainer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDetailsCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT payment_Id FROM payment");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Payments getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM payment WHERE payment_Id=?",id);

        if(result.next()){
            return new Payments(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDate(4),
                    result.getDouble(5)







            );
        }
        return null;
    }

    public static void deletePayment(Payments selectedItem) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("DELETE FROM payment WHERE payment_Id=?",selectedItem.getPayment_Id());
    }




    public static ArrayList<Payments> searchReportByPaymentId(String s) {

            ArrayList<Payments> arrayList = new ArrayList();

            try {

                ResultSet resultSet = CrudUtil.execute("SELECT * FROM payment WHERE payment.payment_Id LIKE ?",s);
                while(resultSet.next()){
                    arrayList.add(new Payments(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4),
                            resultSet.getDouble(5)
                    ));
                }

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            return arrayList;

        }
    }

