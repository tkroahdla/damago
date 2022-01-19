package Controller;

import java.util.Scanner;

import Model.DamaDAO;
import Model.DamaDTO;

public class Logic_Controller {
	Scanner sc = new Scanner(System.in);
	String needs = null;
	DamaDAO dama = new DamaDAO();
	// System.out.println("1.밥먹이기\\n2.잠재우기\\n3.놀아주기\\n4.운동하기\\n5.뒤로가기");

	public DamaDTO sel_need(DamaDTO vo) {
		int need = dama.DamaNeeds();
		int input = getNumInput("무엇을 해줄까요??");
		if (need+1 != input) {
			System.out.println(vo.getNick() + "(이)가 삐졌다.");
			energytype1(vo); // 에너지 감소
		} else {
			if (input == 1) {
				System.out.println("밥을 먹었어요.");
				energytype2(vo);
			} else if (input == 2) {
				System.out.println("잠을 잤어요.");
				energytype2(vo);
			} else if (input == 3) {
				System.out.println("놀았어요.");
				energytype1(vo);
			} else if (input == 4) {
				System.out.println("운동을 했어요."); // 이거 매끄럽게좀 바꿔주세용..
				energytype1(vo);
			} else {
				System.out.println("잘못된 입력이에요.");
			}
		}
		return vo;
	}

	public DamaDTO energytype1(DamaDTO vo) { // 에너지가 감소하는경우 (놀기, 운동)
		vo.setEnergy(vo.getEnergy() - 10);
		return vo;
	}

	public DamaDTO energytype2(DamaDTO vo) {// 에너지가 증가하는경우 (밥먹기,잠자기)
		vo.setEnergy(vo.getEnergy() + 30);
		return vo;
	}

//	private String getStrInput(String msg) {
//		System.out.print(msg);
//		return sc.nextLine();
//	}

	private int getNumInput(String msg) {
		System.out.print(msg);
		return sc.nextInt();
	}

	public void start() {
		// menu(); // 메뉴 선택화면 출력
		// select(); // 메뉴 선택 번호 입력받기
		// process(); // 메뉴 작업에 따른 기능 수행 // 만일 JDBC 작업이라면 JDBC_Controller 객체에서 호출.
	}

}
