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

	public boolean insertMember(String id, int pw) {

		boolean check = false;
		String search = "";
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
	




	private boolean idCheck(String id) {// ���̵�üũ

		boolean check = true;
		MemberDTO member = FindById(id);
		if (member == null)
			check = false;
		return check;
	}

	public int Login(String id, String pw) {

		String SQL = "Select id From use_info where id = ?";

		// ���� SQL���� �۵��ϰ� �� ��ɹ� �Է�

		try {

			pst = conn.prepareStatement(SQL);

			pst.setString(1, id);

			// ��������ŷ���� �����ϱ� ���� ��� ?�� ID���� ���� �� ���.

			rs = pst.executeQuery();

			if (rs.next()) {

				if (rs.getString(1).equals(pw)) {

					return 1; // �α��� ����
				} else
					return 0; // ��й�ȣ ����ġ
			}
			return -1; // ���̵� ����
		} catch (Exception e) {
			e.printStackTrace(); // ����ó��
		}
		return -2; // �����ͺ��̽� ����
	}

	private MemberDTO FindById(String id) {
		for (MemberDTO memberDTO : members) {
			if (memberDTO.getId().equals(id)) {
				return memberDTO;
			}
		}
		return null;
	}


//	private void Rank() {
//	}


	int game_menu;

//	public void Login() {
//		System.out.print("�޴����� >> ");
//		game_menu = sc.nextInt();
//
//		String id = getStrInput("      ID : ");
//		String pw = getStrInput("PassWord : ");
//
//		MemberDTO member = FindById(id);
//
//		if (member == null) {
//			System.out.println("��ϵ��� ���� ID�Դϴ�.");
//		} else if (member.getPw().equals(pw)) { // ���̵� ��� ��� �¾�����
//			System.out.println("[" + member.getId() + "]�Բ��� �α��� �ϼ̽��ϴ�.");
//		} else {
//			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
//		}
//	}


	private String getStrInput(String msg) {
		System.out.println(msg);
		return sc.nextLine();
	}

	private int getNumInput(String msg) {
		System.out.println(msg);
		return sc.nextInt();
	}

}
