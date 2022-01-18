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
	
    public boolean insertMember(String id,  int pw ) {

		boolean check = false;
		String search = "";
		try {
			connect();

			String sql = "insert into Member values(stdseq.nextval,?,?)";
			
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
    
    
 
    private void Rank() {
       
        }
        
    
    private boolean idCheck(String id) {//아이디체크
        boolean check = true;
        MemberDTO member = FindById(id);
        if(member == null)
            check = false;
        return check;
    }
 
    public void Login() {
        
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

	

