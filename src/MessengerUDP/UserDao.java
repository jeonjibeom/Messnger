package MessengerUDP;

import org.mariadb.jdbc.MariaDbConnection;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UserDao {
    //쿼리문 + 마리아 디비 접속
    private static UserDao userDao = new UserDao();
    private static MariaDBConnetor mariaDBConnetor
            = MariaDBConnetor.getInstance(); //이 부분?에러 가능성 많음\
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    UserData user;
    ArrayList<UserData> userList;

    private UserDao(){} //의미 없음
    public static UserDao getInstance(){//의미..?
        if(userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }
    //UserData를 가지고 있는 ArrayList반환
    public ArrayList<UserData> select() throws Exception{
        con = MariaDBConnetor.makeConnection();
        String sql = "SELECT * FROM customer"; //customer 은 뭐지? 아마 테이블 이름일텐데
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        userList = new ArrayList<>();
        while(rs.next()){
            user = new UserData();
            user.setId(rs.getString("id"));
            user.setPwd(rs.getString("pwd"));
            user.setName(rs.getString("name"));
            userList.add(user);
        }
        return userList;
    }
    //회원가입 성공시 발생 메소드
    public int insert(String id, String pwd , String name){
        con = MariaDBConnetor.makeConnection();
        int loginok;
        try{
            String sql = "INSERT INTO CUSETOMER(uid,pwd,name) VALUES";
            sql += "('" + id + "','" + pwd + "','" + name + "')";
            pstmt = con.prepareStatement(sql);
            loginok = pstmt.executeUpdate();
        } catch (SQLException e) {
            return loginok = 0;
        }
        return loginok;
    }
    //회원가입된 사람인지 확인하기 위한 코드
    public boolean loginCheck (String id, String pwd) throws Exception{
        con = MariaDBConnetor.makeConnection();
        userList  = this.select();
        Boolean loginok = false;
        for(UserData user:userList){
            if(id.equals(user.getId()) && pwd.equals(user.getPw())){
                return loginok = true;
            }
        }
        return loginok;
    }
    //로그인 성공시...
    public void loginSuccesse(String id) throws Exception{
        con = MariaDBConnetor.makeConnection();
        for(UserData user:userList){
            if(id.equals(user.getId())){
                String loginName = user.getName();
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            InetAddress address= InetAddress.getByName("224.0.1.100"); // 에러 가능성 아이피..127.0.0.1 이 아닌 이유
                            //new Messenger (id,loginName,address,9999); //에러 발생 가능성..
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
