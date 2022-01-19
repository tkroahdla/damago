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

					return id; // �α��� ����
				} else
					return null; // ��й�ȣ ����ġ
			}
		} catch (Exception e) {
			e.printStackTrace(); // ����ó��
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
				System.out.println("������ ���ӽð� : "+ dt);
				
			} 
		} catch (Exception e) {
			e.printStackTrace(); // ����ó��
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

			int cnt = pst.executeUpdate(); // �Ű���������. �Ű������� sql�� �־�����..(sql)�̷���.. �ڵ����� �־��ֱ淡 �����Ĺ��ǳ׿�
			if (cnt > 0) {
				//System.out.println("�������ӳ�¥ ���� ����");
			}
			else {
				//System.out.println("�������ӳ�¥ ���� ����");
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

			if (cnt > 0) { // �߰� ����
				System.out.println("ȸ������ �Ϸ�!");
				check = true;
			} else { // �߰� ����
				System.out.println("ȸ������ ����!");
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
