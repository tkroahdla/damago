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
	public boolean insertDama(String nick, String type , String id) {
// 생성시 필수적으로 필요한 인자들 ??
//nick type , int exp, int level, int energy, String id 제외
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

			if (cnt > 0) { // 추가 성공
				check = true;
			} else { // 추가 실패
				check = false;
			}

			// catch : try내에서 예외상황 발생 시 catch문으로 들어오게 됨
			// 단, catch문 다음 괄호에 적힌 오류 발생 시에만 실행됨
			// 이 경우 ClassNotFoundException만 처리 가능
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

			// 3. 실행할 SQL문 정의

			// order by를 넣어야 할건데 기준을 뭐로 잡을지를 정하지 않았으니 일단 생략
			String sql = "select * from dama";

			// 4. SQL구문 실행 준비 객체(PreparedStatement) 생성
			// prepareStatement(정의할 sql);
			pst = conn.prepareStatement(sql);

			// 5. sql문을 실행하고 결과 처리
			// executeQuery : select -> 검색(table상에 변화가 일어나지 않음)
			// 반환타입 : ResultSet이라는 객체를 반환
			rs = pst.executeQuery();

			// dama 테이블의 값을 읽어서 출력
			while (rs.next()) {
				int num = rs.getInt(1); // 커서가 가리키고 있는 행의 첫번째 column값을 읽어옴
				String nick = rs.getString("nick");
				String type = rs.getString("type");
				int exp = rs.getInt("exp");
				int energy = rs.getInt("energy");
				String id = rs.getString("id");
				String date = rs.getString("date");

				// 위에서 읽어온 값들로 초기화시켜 생성한 DamaVO 객체의 참조값을
				// ArrayList에 추가
				rank_list.add(new DamaVO(type, nick, num, exp, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 객체들 마무리(Connection, PreparedStatement, ResultSet)
			close();
		}
		return rank_list;
	}
	
	// 로그인 한 사용자의 다마고치 출력
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
				int num = rs.getInt(1); // 커서가 가리키고 있는 행의 첫번째 column값을 읽어옴
				String nick = rs.getString("nick");
				String type = rs.getString("type");
				int exp = rs.getInt("exp");
				int energy = rs.getInt("energy");
				id = rs.getString("id");
				String date = rs.getString("date");

				// 위에서 읽어온 값들로 초기화시켜 생성한 DamaVO 객체의 참조값을
				// ArrayList에 추가
				dama_list.add(new DamaVO(type, nick, num, exp, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 객체들 마무리(Connection, PreparedStatement, ResultSet)
			close();
		}
		return dama_list;
	}
}