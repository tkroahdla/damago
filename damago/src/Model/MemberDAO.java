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

	public int Login() {
		connect();
		
		String SQL = "Select id, password From user_info where id = ?";

		String id = getStrInput("ID :  ");
		int pw = getNumInput("PASSWORD :  ");
		// 실제 SQL에서 작동하게 할 명령문 입력

		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {

				if (rs.getInt(2) == pw) {

					return 1; // 로그인 성공
				} else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace(); // 예외처리
		}finally {
			close();
		}
		return -2; // 데이터베이스 오류
	}
	
	public boolean insertMember() {
		
		String id = getStrInput("   ID :  ");
		int pw = getNumInput("PassWord :  ");
		boolean check = false;
		try {
			connect();
			String sql = "insert into user_info values (?,?)";

			pst = conn.prepareStatement(sql);

			pst.setString(1, id);
			pst.setInt(2, pw);

			int cnt = pst.executeUpdate();

			if (cnt > 0) { // 추가 성공
				System.out.println("추가 성공!");
				check = true;
			} else { // 추가 실패
				System.out.println("추가 실패!");
				check = false;
			}

		} catch (Exception e) {// ClassNotFoundException만 처리 가능
			e.printStackTrace();
		} finally {
			close();
		}
		return check;

	}

	private boolean idCheck(String id) {// 아이디체크

		boolean check = true;
		MemberDTO member = FindById(id);
		if (member == null)
			check = false;
		return check;
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


	private String getStrInput(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

	private int getNumInput(String msg) {
		System.out.print(msg);
		return sc.nextInt();
	}





}
