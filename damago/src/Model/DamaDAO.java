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
		try {
			if (rs != null) {
				rs.close(); // selectStds(), selectOneStd()에서만 사용하는 객체
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

	public boolean insertDama(String id,String nick,int type) {
		boolean check = false;
		try {
			connect();
			String sql = "insert into DAMA values(?, ?, ?, ?, ?, ?,sysdate, ?)";
		//	String nick = getStrInput(" 다마고치의 별명을 입력해주세요! : ");
			String[] t = {"아구몬","파피몬","팔몬"};
			//System.out.println("[1]동글이 [2]네모 [3]세모");
			//int type = getNumInput(" 다마고치의 종류를 선택해주세요! : ");

			pst = conn.prepareStatement(sql);

			int exp = 0;
			int level = 1;
			int energy = 40;

			pst.setString(1, nick);
			pst.setString(2, t[type-1]);
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
			System.out.println("지금은 준비 중이에요."); // 서비스 중 사용
		} finally {
			close();
		}
		return check;
	}

	public ArrayList<DamaDTO> DamaRank() {
		ArrayList<DamaDTO> rank_list = new ArrayList<DamaDTO>();

		try {
			connect();

			String sql = "select * from dama order by leb desc,exp desc";

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

	// 로그인한 사람 다마고치 출력
	public ArrayList<DamaDTO> selectDama(String id) {
		ArrayList<DamaDTO> dama_list = new ArrayList<DamaDTO>();

		try {
			connect();

			String sql = "select * from dama where id = ?";

			pst = conn.prepareStatement(sql);

			pst.setString(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				String nick = rs.getString("nick");
				String type = rs.getString("type");
				int level = rs.getInt("leb");
				int exp = rs.getInt("exp");
				int energy = rs.getInt("energy");
				if(energy == 999) {
					nick += "  (사망 상태)";
				}

				Date date = rs.getDate(7);

				dama_list.add(new DamaDTO(nick, type, exp, level, energy, id, date));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 객체들 마무리(Connection, PreparedStatement, ResultSet)
			close();
		}
		return dama_list;
	}

	public DamaDTO select(ArrayList<DamaDTO> list,int input) { // 다마고치 선택 후 VO객체 반환
		
		return list.get(input - 1); // 리스트는 0부터니까 -1
	}
	
	public boolean deleteDama(String nick) {

	      boolean check = false;

	      try {
	    	 //String nick =getStrInput("삭제할 다마고치의 별명을 입력해주세요! : ");
	         connect();

	         String sql = "delete from dama where nick = ?";

	         // SQL구문 실행 준비 객체(PreparedStatement) 생성
	         // prepareStatement(정의할 sql);
	         pst = conn.prepareStatement(sql);

	         // 바인드 변수
	         pst.setString(1, nick); 
	         // num <- view에서 int num = sc.nextInt();로 번호 넘겨야함

	         int cnt = pst.executeUpdate();

	         if (cnt > 0) { // 변경 성공
	            check = true;
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return check;
	   }
	
	public boolean DamaUpdate(DamaDTO vo) { // 업데이트해야하는것이 무엇인가. 경험치 레벨 에너지 업데이트해야함
		boolean check = false;
		try {
			connect();

			String sql = "update dama set exp=?,leb=?,energy=? where nick = ?";

			pst = conn.prepareStatement(sql);

			pst.setInt(1, vo.getExp());
			pst.setInt(2, vo.getLevel());
			pst.setInt(3, vo.getEnergy());
			pst.setString(4, vo.getNick());

			int cnt = pst.executeUpdate();

			if (cnt > 0) { // 추가 성공
				check = true;
			} else { // 추가 실패
				check = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 객체들 마무리(Connection, PreparedStatement, ResultSet)
			close();
		}
		return false;
	}

	public void insertNeeds(DamaDTO vo) {

	}

	private String getStrInput(String msg) {
		System.out.print(msg);
		return sc.next();
	}

	private int getNumInput(String msg) {
		System.out.print(msg);
		return sc.nextInt();
	}

}
