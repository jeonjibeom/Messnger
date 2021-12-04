package MessengerUDP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConnetor {
    private static MariaDBConnetor mariaDBConnetor = new MariaDBConnetor();
    private MariaDBConnetor(){} //디폴트 생성자
    public static Connection makeConnection(){
        String url = "jdbc:mariadb://localhost:3308/msg_db";
        String id = "root";
        String password = "2267";
        Connection con = null;

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 적재 성공");
            con = DriverManager.getConnection(url,id,password);
            System.out.println("데이터 베이스 연결 성공");
        }catch (ClassNotFoundException e){
            System.out.println("드라이버를 찾을 수 없습니다.");
        }catch (SQLException e){
            System.out.println("연결에 실패했습니다.");
        }
        return con;

    }
    public static MariaDBConnetor getInstance(){
        if(mariaDBConnetor == null){
            mariaDBConnetor = new MariaDBConnetor();
        }
        return mariaDBConnetor;
    }
}
