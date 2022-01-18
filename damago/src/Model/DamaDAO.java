package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DamaDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public void connect() {
		try {
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
				rs.close(); // selectStds(), selectOneStd()������ ����ϴ� ��ü
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
	
	public boolean login(String id, int pw) {

		boolean check = false;

		try {
			connect();

			String sql = "insert into USER_INFO values( ?, ?)";

			pst = conn.prepareStatement(sql);

			pst.setString(1, id);
			pst.setInt(2, pw);

			int cnt = pst.executeUpdate();

			if (cnt > 0) { // �߰� ����
				check = true;
			} else { // �߰� ����
				check = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("������ �غ����Դϴ�"); // ���� �� ���
		} finally {
			close();
		}
		return check;
	}
	
	// ����ڰ� �Է��� ���� DAMA�� ����(�ٸ���ġ ���)
	public boolean insertPet(String nick, String species, int num) {

		boolean check = false;

		try {
			connect();
			String sql = "insert into DAMA values(Dama_SEQ.nextval, ?, ?, ?)";

			pst = conn.prepareStatement(sql);

			num = 0;
			
			pst.setString(1, nick);
			pst.setString(2, species);
			pst.setInt(3, num);
			

			int cnt = pst.executeUpdate();

			if (cnt > 0) { // �߰� ����
				check = true;
			} else { // �߰� ����
				check = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("������ �غ����Դϴ�"); // ���� �� ���
		} finally {
			close();
		}
		return check;
	}
}
