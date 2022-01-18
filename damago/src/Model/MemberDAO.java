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
			// ClassNotFoundExeption �߻��� �� ����
			// 1. ������Ʈ�� ojdbc ���̺귯���� �߰��Ͽ����� Ȯ��
			// 2. ��ŸȮ��}

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";
			// 2. ����� ���� ����, db ���� ��ü(Connection) ����
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
		
		public void close() {
			try {
				if (rs != null) {
					rs.close(); // selectStds(), selectOneStd() ������ ����ϴ� ��ü
								// ResultSet ��ü�� ���� �Ǿ������� ȣ�� ������ �޼���
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

			// 3. ������ sql��(String) ����
			// ? : ���ε� ���� ( ���߿� ä���� ����)
			// Java�� �����ϴ� sql���� ��� auto commit(�ǵ����� ����) �̶� ��� sql�� �ڿ��� �����ݷ� ���̸� �ȵ�
			String sql = "insert into Member values(stdseq.nextval,?,?)";

			// 4. sql���� �غ� ��ü(PreparedStatement) ����
			pst = conn.prepareStatement(sql);

			// 5.���ε� ���� ä���
			pst.setString(1, id);
			pst.setString(2, pw);
		

			// 6.sql���� �����Ͽ� ��� ó��
			// executeUpdate():insert,delete,update -> table �� ��ȭ�� �Ͼ
			// ��ȯ�� : int (-> table �󿡼� ��� ���� ���� �Ǿ������� ��ȯ)
			// ������ ����� �Ͼ ��쿡�� �׻� 0���� ū ���� ��ȯ
			// executeQuery:select 0 (table �� ��ȭ�� �Ͼ�� ����)
			// ��ȯŸ�� : ResultSet ��ü ��ȯ
			int cnt = pst.executeUpdate();

			if (cnt > 0) { // �߰� ����
				System.out.println("�߰� ����!");
				check = true;
			} else { // �߰� ����
				System.out.println("�߰� ����!");
				check = false;
			}

			// catch : try������ ���ܻ�Ȳ�� �߻��� ��� catch������ �����Ե�
		} catch (Exception e) {// ClassNotFoundException�� ó�� ����
			// ��� ���� ��Ȳ�� ���� ���� Ŭ���� (Exception e-> ��� ���� ��Ȳ�� ���ؼ� ó��)

			// �߻��� ���ܻ�Ȳ�� �߻� ������� ���(��𿡼� ���ܻ�Ȳ�� �߻��߰�
			// ���������� �߻��ߴ����� �ܼ�â���� ���� Ȯ��)
			e.printStackTrace();
			// finally : try ������ ���ܻ�Ȳ�� �߻��ϴ���/�߻����� �ʴ��� �������� ������ ����
			// �ɼ� (�ᵵ �ǰ� �Ƚᵵ �ǰ�)
		} finally {
			// ����� ��ü��(Connection, PreparedStatement) ������(��ȯ)
			// ���� �� ����! : ������ ���� �ݴ�� �ݾ������
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
 
    private boolean idCheck(String id) {//���̵�üũ
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

	

