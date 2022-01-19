package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DamaDAO {
	Scanner sc = new Scanner(System.in);
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
	public boolean insertDama(String id) {
		boolean check = false;
		try {
			connect();
			String sql = "insert into DAMA values(?, ?, ?, ?, ?, ?,sysdate, ?)";
<<<<<<< HEAD
			String nick = getStrInput(" �г����� �����ּ��� : ");
			String type = getStrInput(" Ÿ���� �����ּ���  : ");
		
=======
			String nick = getStrInput("�г����� �����ּ��� : ");
			String type = getStrInput("Ÿ���� �����ּ���  : ");
			// type�� varchar2(3)�̶� ��û �۴� �����ؼ� ����.
			// �� ���� �߰��Ѵٸ� ũ���� �÷��ּ���
			// ���̶� db������ �����ֳ׿�.
>>>>>>> branch 'master' of https://github.com/tkroahdla/damago.git
			pst = conn.prepareStatement(sql);

			int exp = 0;
			int level = 1;
			int energy = 0;

			pst.setString(1, nick);
			pst.setString(2, type);
			pst.setInt(3, exp);
			pst.setInt(4, level);
			pst.setInt(5, energy);
			pst.setString(6, id);
			pst.setString(7, "meal");

			int cnt = pst.executeUpdate();

			if (cnt > 0) { // �߰� ����
				check = true;
				System.out.println("���� �Ϸ�!");
			} else { // �߰� ����
				check = false;
				System.out.println("���� ����..");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("������ �غ����Դϴ�"); // ���� �� ���
		} finally {
			close();
		}
		return check;
	}

	// ��ü ������� �ٸ���ġ ���(��ŷ��)
	public ArrayList<DamaDTO> DamaRank() {
		ArrayList<DamaDTO> rank_list = new ArrayList<DamaDTO>();

		try {
			connect();

			String sql = "select * from dama";

			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();

			while (rs.next()) {
				String nick = rs.getString("nick");
				String type = rs.getString("type");
				int exp = rs.getInt("exp");
				int level = rs.getInt("leb");
				int energy = rs.getInt("energy");
				String id = rs.getString("id");
				Date date = rs.getDate(7);
				// System.out.println(date);
				String needs = rs.getString("needs");

				rank_list.add(new DamaDTO(nick, type, exp, level, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rank_list;
	}

	// �α����� ��� �ٸ���ġ ���
	public ArrayList<DamaDTO> selectDama(String id) {
		ArrayList<DamaDTO> dama_list = new ArrayList<DamaDTO>();

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
				// int num = rs.getInt(1); // Ŀ���� ����Ű�� �ִ� ���� ù��° column���� �о��
				String nick = rs.getString("nick");
				String type = rs.getString("type");
				int level = rs.getInt("leb");
				int exp = rs.getInt("exp");
				int energy = rs.getInt("energy");

				Date date = rs.getDate(7);

				// ������ �о�� ����� �ʱ�ȭ���� ������ DamaVO ��ü�� ��������
				// ArrayList�� �߰�
				dama_list.add(new DamaDTO(nick, type, exp, level, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ��ü�� ������(Connection, PreparedStatement, ResultSet)
			close();
		}
		return dama_list;
	}

	public DamaDTO select(ArrayList<DamaDTO> list) { // �ٸ���ġ ���� �� VO��ü ��ȯ
		int input = getNumInput(" ������ �ٸ���ġ ��ȣ :");

		return list.get(input - 1); // ����Ʈ�� 0���ʹϱ� -1
	}

	public void insertNeeds(DamaDTO vo) {

	}

	private String getStrInput(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

	private int getNumInput(String msg) {
		System.out.print(msg);
		return sc.nextInt();
	}

	

}
