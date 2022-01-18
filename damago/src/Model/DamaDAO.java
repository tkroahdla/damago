package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DamaDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

//			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
//			String user = "campus_d_6_0115";
//			String password = "smhrd6";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";

			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	// ����ڰ� �Է��� ���� DAMA�� ����(�ٸ���ġ ���)
	public boolean insertDama(String nick, String type , String id) {
// ������ �ʼ������� �ʿ��� ���ڵ� ??
//nick type , int exp, int level, int energy, String id ����
		boolean check = false;
		try {
			connect();
			String sql = "insert into DAMA values(?, ?, ?, ?, ?, ?,sys_date, ?)";

			pst = conn.prepareStatement(sql);

			int exp = 0;
			int level = 1;
			int energy = 0;

			pst.setString(1, nick);
			pst.setString(2, type);
			pst.setInt(3, exp);
			pst.setInt(4, level);
			pst.setInt(5, energy);
			pst.setString(6, id );
			pst.setString(7,"meal" );
			

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
	
	// ��ü ������� �ٸ���ġ ���(��ŷ��)
	public ArrayList<DamaVO> DamaRank() {
		ArrayList<DamaVO> rank_list = new ArrayList<DamaVO>();

		try {
			connect();

			// 3. ������ SQL�� ����

			// order by�� �־�� �Ұǵ� ������ ���� �������� ������ �ʾ����� �ϴ� ����
			String sql = "select * from dama";

			// 4. SQL���� ���� �غ� ��ü(PreparedStatement) ����
			// prepareStatement(������ sql);
			pst = conn.prepareStatement(sql);

			// 5. sql���� �����ϰ� ��� ó��
			// executeQuery : select -> �˻�(table�� ��ȭ�� �Ͼ�� ����)
			// ��ȯŸ�� : ResultSet�̶�� ��ü�� ��ȯ
			rs = pst.executeQuery();

			// dama ���̺��� ���� �о ���
			while (rs.next()) {
				int num = rs.getInt(1); // Ŀ���� ����Ű�� �ִ� ���� ù��° column���� �о��
				String nick = rs.getString("nick");
				String type = rs.getString("type");
				int exp = rs.getInt("exp");
				int energy = rs.getInt("energy");
				String id = rs.getString("id");
				String date = rs.getString("date");

				// ������ �о�� ����� �ʱ�ȭ���� ������ DamaVO ��ü�� ��������
				// ArrayList�� �߰�
				rank_list.add(new DamaVO(type, nick, num, exp, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ��ü�� ������(Connection, PreparedStatement, ResultSet)
			close();
		}
		return rank_list;
	}
	
	// �α��� �� ������� �ٸ���ġ ���
	public ArrayList<DamaVO> selectDama(String id) {
		ArrayList<DamaVO> dama_list = new ArrayList<DamaVO>();

		try {
			connect();

			// 3. ������ SQL�� ����
			String sql = "select * from dama where id = ?";

			// 4. SQL���� ���� �غ� ��ü(PreparedStatement) ����
			// prepareStatement(������ sql);
			pst = conn.prepareStatement(sql);

			// ���ε� ���� ä���
			pst.setString(1, id);
			// 5. sql���� �����ϰ� ��� ó��
			// executeQuery : select -> �˻�(table�� ��ȭ�� �Ͼ�� ����)
			// ��ȯŸ�� : ResultSet�̶�� ��ü�� ��ȯ
			rs = pst.executeQuery();

			// dama ���̺��� ���� �о ���
			while (rs.next()) {
				int num = rs.getInt(1); // Ŀ���� ����Ű�� �ִ� ���� ù��° column���� �о��
				String nick = rs.getString("nick");
				String type = rs.getString("type");
				int exp = rs.getInt("exp");
				int energy = rs.getInt("energy");
				id = rs.getString("id");
				String date = rs.getString("date");

				// ������ �о�� ����� �ʱ�ȭ���� ������ DamaVO ��ü�� ��������
				// ArrayList�� �߰�
				dama_list.add(new DamaVO(type, nick, num, exp, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ��ü�� ������(Connection, PreparedStatement, ResultSet)
			close();
		}
		return dama_list;
	}
}