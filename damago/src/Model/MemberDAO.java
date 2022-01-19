package Model;

import java.sql.Connection;
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

	public String Login() {
	
		try {
			connect();
			
			String SQL = "Select id, password From user_info where id = ?";

			String id = getStrInput("ID :  ");
			int pw = getNumInput("PASSWORD :  ");
			pst = conn.prepareStatement(SQL);
			pst.setString(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {

				if (rs.getInt(2) == pw) {

					return id; // �α��� ����
				} else
					return null; // ��й�ȣ ����ġ
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����ó��
		}finally {
			close();
		}
		return null;
	}
	
	public boolean insertMember() {
		
		String id = getStrInput("    ID :  ");
		int pw = getNumInput("PassWord :  ");
		boolean check = false;
		try {
			connect();
			String sql = "insert into user_info values (?,?)";

			pst = conn.prepareStatement(sql);

			pst.setString(1, id);
			pst.setInt(2, pw);

			int cnt = pst.executeUpdate();

			if (cnt > 0) { // �߰� ����
				System.out.println("�߰� ����!");
				check = true;
			} else { // �߰� ����
				System.out.println("�߰� ����!");
				check = false;
			}

		} catch (Exception e) {// ClassNotFoundException�� ó�� ����
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
