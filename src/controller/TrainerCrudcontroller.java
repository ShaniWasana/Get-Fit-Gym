package controller;

import Model.Trainer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainerCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT trainer_Id FROM trainer");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Trainer getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM trainer WHERE trainer_Id=?",id);

        if(result.next()){
            return new Trainer (
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getInt(5),
                    result.getDouble(6)







            );
        }
        return null;
    }

    public static ArrayList<Trainer> searchReportByTrainerId(String s) {
        ArrayList<Trainer> arrayList = new ArrayList();

        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM trainer WHERE trainer.trainer_Id LIKE ?",s);
            while(resultSet.next()){
                arrayList.add(new Trainer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6)
                ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;

    }

    public static void deleteTrainer(Trainer selectedItem) throws SQLException, ClassNotFoundException {

            CrudUtil.execute("DELETE FROM trainer WHERE trainer_Id=?",selectedItem.getTrainer_Id());

        }
    public static boolean UpdateTrainer(Trainer t) throws SQLException, ClassNotFoundException {

      return  CrudUtil.execute("UPDATE trainer SET t_Name=?,t_Address=?,t_Cotactno=?,t_Age=?,t_Salary=? WHERE trainer_Id=?",t.getT_Name(),t.getT_Address(),t.getT_Contactno(),t.getT_Age(),t.getT_Salary(),t.getTrainer_Id());

    }

    }

