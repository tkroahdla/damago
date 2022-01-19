package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		// 객체들 마무리(Connection, PreparedStatement, ResultSet)
		try {
			// 객체가 생겼다 = null이 아니다
			if (rs != null) {
				rs.close(); // selectStds(), selectOneStd()에서만 사용하는 객체
				// ResultSet 객체가 생성되었을 때만 호출 가능한 메소드
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

	// 사용자가 입력한 값을 DAMA에 삽입(다마고치 등록)
	public boolean insertDama(String id) {
		boolean check = false;
		try {
			connect();
			String sql = "insert into DAMA values(?, ?, ?, ?, ?, ?,sysdate, ?)";
			String nick = getStrInput(" 닉네임을 적어주세요 : ");
			String type = getStrInput(" 타입을 적어주세요  : ");
			// type이 varchar2(3)이라 엄청 작다 주의해서 넣자.
			// 이 문구 발견한다면 크기좀 늘려주세요
			// 밤이라 db서버가 꺼져있네요.
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

			if (cnt > 0) { // 추가 성공
				check = true;
			} else { // 추가 실패
				check = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("지금은 준비중입니다"); // 서비스 중 사용
		} finally {
			close();
		}
		return check;
	}

	// 전체 사용자의 다마고치 출력(랭킹용)
	public ArrayList<DamaVO> DamaRank() {
		ArrayList<DamaVO> rank_list = new ArrayList<DamaVO>();

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
				System.out.println(date);
				String needs = rs.getString("needs");

				rank_list.add(new DamaVO(nick, type, exp, level, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rank_list;
	}

	// 로그인한 사람 다마고치 출력
	public ArrayList<DamaVO> selectDama(String id) {
		ArrayList<DamaVO> dama_list = new ArrayList<DamaVO>();

		try {
			connect();

			// 3. 실행할 SQL문 정의
			String sql = "select * from dama where id = ?";

			// 4. SQL구문 실행 준비 객체(PreparedStatement) 생성
			// prepareStatement(정의할 sql);
			pst = conn.prepareStatement(sql);

			// 바인드 변수 채우기
			pst.setString(1, id);
			// 5. sql문을 실행하고 결과 처리
			// executeQuery : select -> 검색(table상에 변화가 일어나지 않음)
			// 반환타입 : ResultSet이라는 객체를 반환
			rs = pst.executeQuery();

			// dama 테이블의 값을 읽어서 출력
			while (rs.next()) {
				// int num = rs.getInt(1); // 커서가 가리키고 있는 행의 첫번째 column값을 읽어옴
				String nick = rs.getString("nick");
				String type = rs.getString("type");
				int level = rs.getInt("leb");
				int exp = rs.getInt("exp");
				int energy = rs.getInt("energy");

				Date date = rs.getDate(7);

				// 위에서 읽어온 값들로 초기화시켜 생성한 DamaVO 객체의 참조값을
				// ArrayList에 추가
				dama_list.add(new DamaVO(nick, type, exp, level, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 객체들 마무리(Connection, PreparedStatement, ResultSet)
			close();
		}
		return dama_list;
//	}catch(Exception e)
//	{
//		e.printStackTrace();
//	}finally
//	{
//		// 객체들 마무리(Connection, PreparedStatement, ResultSet)
//		close();
//	}return dama_list;
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