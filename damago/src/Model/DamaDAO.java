package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DamaDAO {
	// ���������� ����
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	// DB ���� �޼ҵ�
	public void connect() {
		try {
			// ClassNotFoundException �߻��� �� ����
			// �ذ���1. ������Ʈ�� ojdbc ���̺귯���� �߰��Ͽ����� Ȯ��
			// �ذ���2. ��Ÿ Ȯ��
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. ����� ���� ����, DB�� ������ ��ü(Connection) ����
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "campus_d_6_0115";
			String password = "smhrd6";

			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� ���� �޼ҵ�
	// ����� ��ü�� ��ȯ
	public void close() {
		// ��ü�� ������(Connection, PreparedStatement, ResultSet)
		try {
			// ��ü�� ����� = null�� �ƴϴ�
			if (rs != null) {
				rs.close(); // selectStds(), selectOneStd()������ ����ϴ� ��ü
				// ResultSet ��ü�� �����Ǿ��� ���� ȣ�� ������ �޼ҵ�
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
	
	// ����ڰ� �Է��� ���� USER_INFO�� ����(ȸ������)
	public boolean insertMember(String id, int pw) {
		// JAVA - Oracle DB�� ������ �� JDBC java api ���

		boolean check = false;

		try {
			// 1. Oracle JDBC driver�� �����ε�(= Oracle DB�� ���� ����)
			connect();

			// 3. ������ SQL��(String����) ����
			// ? : ���ε� ����(���ؾ� �ϴ� ���� ?�� ����)
			String sql = "insert into USER_INFO values(User_SEQ.nextval, ?, ?)";

			// 4. SQL���� ���� �غ� ��ü(PreparedStatement) ����
			// prepareStatement(������ sql);
			pst = conn.prepareStatement(sql);

			// 5. ���ε� ������ ä���
			// pst.set������(���ε庯���� ����, ä�� ��)
			pst.setString(1, id);
			pst.setInt(2, pw);

			// 6. SQL�� �����Ͽ� ��� ó��
			// executeUpdate() : insert, delete, update -> table�� ��ȭ�� �Ͼ
			// ��ȯ�� : int(-> table�󿡼� ��� ���� �����Ǿ����� ��ȯ)
			// ������ ����� �Ͼ ��� �׻� 0���� ū ���� ��ȯ��
			int cnt = pst.executeUpdate();

			if (cnt > 0) { // �߰� ����
				check = true;
			} else { // �߰� ����
				check = false;
			}

			// catch : try������ ���ܻ�Ȳ �߻� �� catch������ ������ ��
			// ��, catch�� ���� ��ȣ�� ���� ���� �߻� �ÿ��� �����
			// �� ��� ClassNotFoundException�� ó�� ����
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
		// JAVA - Oracle DB�� ������ �� JDBC java api ���

		boolean check = false;

		try {
			// 1. Oracle JDBC driver�� �����ε�(= Oracle DB�� ���� ����)
			connect();

			// 3. ������ SQL��(String����) ����
			// ? : ���ε� ����(���ؾ� �ϴ� ���� ?�� ����)
			String sql = "insert into DAMA values(Dama_SEQ.nextval, ?, ?, ?)";

			// 4. SQL���� ���� �غ� ��ü(PreparedStatement) ����
			// prepareStatement(������ sql);
			pst = conn.prepareStatement(sql);

			// �ʱⰪ
			num = 0;
			
			// 5. ���ε� ������ ä���
			// pst.set������(���ε庯���� ����, ä�� ��)
			pst.setString(1, nick);
			pst.setString(2, species);
			pst.setInt(3, num);
			

			// 6. SQL�� �����Ͽ� ��� ó��
			// executeUpdate() : insert, delete, update -> table�� ��ȭ�� �Ͼ
			// ��ȯ�� : int(-> table�󿡼� ��� ���� �����Ǿ����� ��ȯ)
			// ������ ����� �Ͼ ��� �׻� 0���� ū ���� ��ȯ��
			int cnt = pst.executeUpdate();

			if (cnt > 0) { // �߰� ����
				check = true;
			} else { // �߰� ����
				check = false;
			}

			// catch : try������ ���ܻ�Ȳ �߻� �� catch������ ������ ��
			// ��, catch�� ���� ��ȣ�� ���� ���� �߻� �ÿ��� �����
			// �� ��� ClassNotFoundException�� ó�� ����
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("������ �غ����Դϴ�"); // ���� �� ���
		} finally {
			close();
		}
		return check;
	}
}
