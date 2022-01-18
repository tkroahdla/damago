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

    private void Rank() {
    }
      
    private boolean idCheck(String id) {//���̵�üũ
        boolean check = true;
        MemberDTO member = FindById(id);
        if(member == null)
            check = false;
        return check;
    }
 
    int game_menu;
    public void Login() {
    	System.out.print("�޴����� >> ");
		game_menu = sc.nextInt();
    	
        String id = getStrInput("      ID : ");
        String pw = getStrInput("PassWord : ");
        
        MemberDTO member = FindById(id);
       
        if(member == null){
            System.out.println("��ϵ��� ���� ID�Դϴ�."); 
        }else if(member.getPw().equals(pw)) { // ���̵� ��� ��� �¾�����
            System.out.println("[" + member.getId() + "]�Բ��� �α��� �ϼ̽��ϴ�.");
        }else {
            System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
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
        System.out.println("�ٸ���ġ");
        return getNumInput("[1]�α��� [2]ȸ������ [3]��ũ [0]����");
    }
 
    private int getNumInput(String msg) {
        System.out.println(msg);
        return sc.nextInt();
    }
 
}

	

