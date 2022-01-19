package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MemberDAO {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	Scanner sc = new Scanner(System.in);
	List<MemberDTO> members = new ArrayList<>();

	public void connect() {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "hr";
//			String password = "hr";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "campus_d_6_0115";
			String password = "smhrd6";

			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String Login(String id,int pw) {
		try {
			connect();

			String SQL = "Select id, password From user_info where id = ?";

			pst = conn.prepareStatement(SQL);
			pst.setString(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {

				if (rs.getInt(2) == pw) {

					return id; // 로그인 성공
				} else
					return null; // 비밀번호 불일치
			}
		} catch (Exception e) {
			e.printStackTrace(); // 예외처리
		} finally {
			close();
		}
		return null;
	}

	public void last_date(String id) {
		try {

			connect();

			String SQL = "Select last_date From user_info where id = ?";

			pst = conn.prepareStatement(SQL);
			pst.setString(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				String dt = rs.getString(1);
				System.out.println("마지막 접속시간 : "+ dt);
				
			} 
		} catch (Exception e) {
			e.printStackTrace(); // 예외처리
		} finally {
			close();
		}
		//return null;
	}
	
	public void sysdate_update(String id) {
		try {
			connect();

			String sql = "update user_info set last_date=sysdate where id=?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, id);

			int cnt = pst.executeUpdate(); // 매개변수없다. 매개변수로 sql을 넣어놨어요..(sql)이렇게.. 자동으로 넣어주길래 엔터쳐버렷네여
			if (cnt > 0) {
				//System.out.println("최종접속날짜 삽입 성공");
			}
			else {
				//System.out.println("최종접속날짜 삽입 실패");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public boolean insertMember() {

		String id = getStrInput("    ID :  ");
		int pw = getNumInput("PassWord :  ");
		boolean check = false;
		try {
			connect();
			String sql = "insert into user_info values (?,?,sysdate)";

			pst = conn.prepareStatement(sql);

			pst.setString(1, id);
			pst.setInt(2, pw);

			int cnt = pst.executeUpdate();

			if (cnt > 0) { // 추가 성공
				System.out.println("회원가입 완료!");
				check = true;
			} else { // 추가 실패
				System.out.println("회원가입 실패!");
				check = false;
			}

		} catch (Exception e) {// ClassNotFoundException만 처리 가능
			e.printStackTrace();
		} finally {
			close();
		}
		return check;

	}

	private String getStrInput(String msg) {
		System.out.print(msg);
		return sc.next();
	}

	private int getNumInput(String msg) {
		System.out.print(msg);
		return sc.nextInt();
	}

}
