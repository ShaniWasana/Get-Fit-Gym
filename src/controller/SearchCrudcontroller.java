package controller;

import Model.Member;
import Model.Suppliment;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchCrudcontroller {
    public static ArrayList<String> getlose() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT member_Id FROM member");
        ArrayList<String>ids =new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));

        }
        return ids;

    }

    public static Member getlose(String id) throws SQLException, ClassNotFoundException {
        ResultSet result =CrudUtil.execute("SELECT * FROM member WHERE member_Id=?",id);

        if(result.next()){
            return new Member(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getInt(5),
                    result.getString(6),
                    result.getString(7)

            );
        }
        return null;
    }
    public static void deleteMember(Member selectedItem) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("DELETE FROM member WHERE member_Id=?",selectedItem.getMember_Id());

    }

    public static ArrayList<Member> searchReportByMemberId(String s) {
        ArrayList<Member> arrayList = new ArrayList();

        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM member WHERE member.member_Id LIKE ?",s);
            while(resultSet.next()){
                arrayList.add(new Member(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;

    }

    public static ArrayList<Member> searchReportByMemberName(String s) {
        ArrayList<Member> arrayList = new ArrayList();

        try {

            ResultSet result = CrudUtil.execute("SELECT * FROM member WHERE member.name LIKE ?", s);
            while (result.next()) {
                arrayList.add(new Member(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        result.getString(6),
                        result.getString(7)
                ));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;


    }
}

