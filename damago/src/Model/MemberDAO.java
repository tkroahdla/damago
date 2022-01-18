package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
			// ClassNotFoundExeption 발생할 수 있음
			// 1. 프로젝트에 ojdbc 라이브러리를 추가하였는지 확인
			// 2. 오타확인}

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";
			// 2. 사용할 계정 선택, db 연결 객체(Connection) 생성
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
		
		public void close() {
			try {
				if (rs != null) {
					rs.close(); // selectStds(), selectOneStd() 에서만 사용하는 객체
								// ResultSet 객체가 생성 되었을때만 호출 가능한 메서드
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
	
    public boolean insertMember(String id,  String pw ) {
		// JAVA-Oracle DB : JDBC java api

		boolean check = false;
		String search = "";
		try {
			connect();

			// 3. 실행할 sql문(String) 정의
			// ? : 바인드 변수 ( 나중에 채워질 변수)
			// Java로 실행하는 sql문은 모두 auto commit(되돌릴수 없음) 이라서 모든 sql문 뒤에는 세미콜론 붙이면 안됨
			String sql = "insert into Member values(stdseq.nextval,?,?)";

			// 4. sql문의 준비 객체(PreparedStatement) 생성
			pst = conn.prepareStatement(sql);

			// 5.바인드 변수 채우기
			pst.setString(1, id);
			pst.setString(2, pw);
		

			// 6.sql문을 실행하여 결과 처리
			// executeUpdate():insert,delete,update -> table 상에 변화가 일어남
			// 반환값 : int (-> table 상에서 몇개의 행이 수정 되었는지를 반환)
			// 수정이 제대로 일어난 경우에는 항상 0보다 큰 값을 반환
			// executeQuery:select 0 (table 상에 변화가 일어나지 않음)
			// 반환타입 : ResultSet 객체 반환
			int cnt = pst.executeUpdate();

			if (cnt > 0) { // 추가 성공
				System.out.println("추가 성공!");
				check = true;
			} else { // 추가 실패
				System.out.println("추가 실패!");
				check = false;
			}

			// catch : try내에서 예외상황이 발생한 경우 catch문으로 들어오게됨
		} catch (Exception e) {// ClassNotFoundException만 처리 가능
			// 모든 예외 상황에 대한 상위 클래스 (Exception e-> 모든 예외 상황에 대해서 처리)

			// 발생한 예외상황을 발생 순서대로 출력(어디에서 예외상황이 발생했고
			// 무엇때문에 발생했는지를 콘솔창에서 직접 확인)
			e.printStackTrace();
			// finally : try 문에서 예외상황이 발생하던지/발생하지 않던지 마지막에 무조건 실행
			// 옵션 (써도 되고 안써도 되고)
		} finally {
			// 사용한 객체들(Connection, PreparedStatement) 마무리(반환)
			// 닫을 때 순서! : 생성한 순서 반대로 닫아줘야함
			close();
		}

		return check;
    }
    
    public void run() {
        
        int key = 0;
        while ((key = menu()) != 0) {
            switch (key) {
                case 1:
                    Login();
                    break;
                case 2:
                    NewMember();
                    break;
                case 3:
                    Rank();
            }
        }
    }
 
    private void Rank() {
        
        
       
        }
        
    
 
    private void NewMember() {
        sc.nextLine();
        String id = getStrInput("               ID : ");
        String pw = getStrInput("         PassWord : ");
        
        
    }
 
    private boolean idCheck(String id) {//아이디체크
        boolean check = true;
        MemberDTO member = FindById(id);
        if(member == null)
            check = false;
        return check;
    }
 
    private void Login() {
        sc.nextLine();
        String id = getStrInput("      ID : ");
        String pw = getStrInput("PassWord : ");
        
        MemberDTO member = FindById(id);
       
        if(member == null){
            System.out.println("등록되지 않은 ID입니다."); 
        }else if(member.getPw().equals(pw)) { // 아이디 비번 모두 맞았을때
            System.out.println("[" + member.getId() + "]님께서 로그인 하셨습니다.");
        }else {
            System.out.println("비밀번호가 틀렸습니다.");
        }
    }
 
    private MemberDTO FindById(String id) {
        for(MemberDTO memberDTO : members) {
            if(memberDTO.getId().equals(id)) {
                return memberDTO;
            }
        }
        return null;
    }
 
    private String getStrInput(String msg) {
        System.out.println(msg);
        return sc.nextLine();        
    }
 
    private int menu() {
        System.out.println("다마고치");
        return getNumInput("[1]로그인 [2]회원가입 [3]랭크 [0]종료");
    }
 
    private int getNumInput(String msg) {
        System.out.println(msg);
        return sc.nextInt();
    }
 
}

	

