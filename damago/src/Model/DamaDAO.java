package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DamaDAO {
	// 전역변수로 선언
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	// DB 연결 메소드
	public void connect() {
		try {
			// ClassNotFoundException 발생할 수 있음
			// 해결방법1. 프로젝트에 ojdbc 라이브러리를 추가하였는지 확인
			// 해결방법2. 오타 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 사용할 계정 선택, DB와 연결할 객체(Connection) 생성
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "campus_d_6_0115";
			String password = "smhrd6";

			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 연결 종료 메소드
	// 사용한 객체들 반환
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
	
	// 사용자가 입력한 값을 USER_INFO에 삽입(회원가입)
	public boolean insertMember(String id, int pw) {
		// JAVA - Oracle DB를 연결해 줄 JDBC java api 사용

		boolean check = false;

		try {
			// 1. Oracle JDBC driver을 동적로딩(= Oracle DB와 연결 선언)
			connect();

			// 3. 실행할 SQL문(String으로) 정의
			// ? : 바인드 변수(변해야 하는 값을 ?로 정의)
			String sql = "insert into USER_INFO values(User_SEQ.nextval, ?, ?)";

			// 4. SQL구문 실행 준비 객체(PreparedStatement) 생성
			// prepareStatement(정의할 sql);
			pst = conn.prepareStatement(sql);

			// 5. 바인드 변수를 채우기
			// pst.set변수형(바인드변수의 순번, 채울 것)
			pst.setString(1, id);
			pst.setInt(2, pw);

			// 6. SQL문 실행하여 결과 처리
			// executeUpdate() : insert, delete, update -> table상에 변화가 일어남
			// 반환값 : int(-> table상에서 몇개의 행이 수정되었는지 반환)
			// 수정이 제대로 일어난 경우 항상 0보다 큰 값을 반환함
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
	
	// 사용자가 입력한 값을 DAMA에 삽입(다마고치 등록)
	public boolean insertPet(String nick, String species, int num) {
		// JAVA - Oracle DB를 연결해 줄 JDBC java api 사용

		boolean check = false;

		try {
			// 1. Oracle JDBC driver을 동적로딩(= Oracle DB와 연결 선언)
			connect();

			// 3. 실행할 SQL문(String으로) 정의
			// ? : 바인드 변수(변해야 하는 값을 ?로 정의)
			String sql = "insert into DAMA values(Dama_SEQ.nextval, ?, ?, ?)";

			// 4. SQL구문 실행 준비 객체(PreparedStatement) 생성
			// prepareStatement(정의할 sql);
			pst = conn.prepareStatement(sql);

			// 초기값
			num = 0;
			
			// 5. 바인드 변수를 채우기
			// pst.set변수형(바인드변수의 순번, 채울 것)
			pst.setString(1, nick);
			pst.setString(2, species);
			pst.setInt(3, num);
			

			// 6. SQL문 실행하여 결과 처리
			// executeUpdate() : insert, delete, update -> table상에 변화가 일어남
			// 반환값 : int(-> table상에서 몇개의 행이 수정되었는지 반환)
			// 수정이 제대로 일어난 경우 항상 0보다 큰 값을 반환함
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
}
