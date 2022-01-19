package Controller;

import java.util.Random;
import java.util.Scanner;

import Model.DamaDAO;
import Model.DamaDTO;

public class Logic_Controller {
	Scanner sc = new Scanner(System.in);
	String needs = null;
	DamaDAO dama = new DamaDAO();
	// System.out.println("1.밥먹이기\\n2.잠재우기\\n3.놀아주기\\n4.운동하기\\n5.뒤로가기");

	public DamaDTO expPlus(DamaDTO vo) { // 경험치 증가
		vo.setExp(vo.getExp() + 10);
		return vo;
	}

	public DamaDTO cleaning(DamaDTO vo) { // 청결도 감소
		vo.setCleaning(vo.getCleaning() - 10);
		return vo;
	}

	public int DamaNeeds() { // 욕구
		Random r = new Random();
		// String[] needs = {"meal","sleep","exercies","cleaning","play"};
		int a = r.nextInt(6);
		// String need = needs[r.nextInt(5)];
		if (a == 0)
			System.out.println("현재 상태 : 배고파!!\n");
		else if (a == 1)
			System.out.println("현재 상태 : 피곤해..\n");
		else if (a == 2)
			System.out.println("현재 상태 : 우오! 힘이 넘쳐흐른다!\n");
		else if (a == 3)
			System.out.println("현재 상태 : 심심해~ \n");
		else if (a == 4)
			System.out.println("현재 상태 : 으악 더러워!!\n");

		return a;
	}

	public DamaDTO sel_need(DamaDTO vo, int needs) {
		// int need = dama.DamaNeeds();
		System.out.println("스테이트는 : " + vo.getState());
		int input = getNumInput("무엇을 해줄까요??");
		if (input == 6) { // 종료인 경우가 우선순위가 될 수 있도록 배치
			vo.setStop(true);
			return vo;
		}
		if (vo.getState() == 1) {
			if (input == 1) {
				System.out.println("밥먹이기(에너지+30)");
				energyplus(vo);
				expPlus(vo);
				cleaning(vo);
			} else if (input == 2) {
				System.out.println("잠재우기(에너지+30)");
				energyplus(vo);
				cleaning(vo);
			} else if (input == 3) {
				System.out.println("놀아주기(에너지-10)");
				energyminus(vo);
				expPlus(vo);
				cleaning(vo);
			} else if (input == 4) {
				System.out.println("운동시키기(에너지-10)");
				energyminus(vo);
				expPlus(vo);
				cleaning(vo);
			} else if (input == 5) {
				System.out.println("씻겨주기(에너지+30)");
				energyplus(vo);
				// cleaning(vo);
			}  else {
				System.out.println("잘못된 입력이에요.");
			}
			return vo;
		}

		if (needs + 1 == input && vo.getState() == 0) {
			System.out.println(vo.getNick() + "(이)가 만족합니다...");
			if (input == 1) {
				System.out.println("밥먹이기(에너지+30) 경험치 없음.");
				energyplus(vo);
				// expPlus(vo);
				cleaning(vo);
			} else if (input == 2) {
				System.out.println("잠재우기(에너지+30) 경험치 없음.");
				energyplus(vo);
				cleaning(vo);
			} else if (input == 3) {
				System.out.println("놀아주기(에너지-10) 경험치 없음.");
				energyminus(vo);
				// expPlus(vo);
				cleaning(vo);
			} else if (input == 4) {
				System.out.println("운동시키기(에너지-10) 경험치 없음.");
				energyminus(vo);
				// expPlus(vo);
				cleaning(vo);
			} else if (input == 5) {
				System.out.println("씻겨주기(에너지+30) 경험치 없음.");
				energyplus(vo);
				// cleaning(vo);
			} else if (input == 6) {
				vo.setStop(true);
				return vo;
			} else {
				System.out.println("잘못된 입력이에요.");
			}
			vo.setState(1);
			System.out.println(vo.getCleaning());
		} else if (needs + 1 != input) {
			System.out.println(vo.getNick() + "(이)가 삐졌다.");
			energyminus(vo); // 에너지 감소
			return vo;
		}

		return vo;
	}

	public DamaDTO energyminus(DamaDTO vo) { // 에너지가 감소하는경우 (놀기, 운동)
		vo.setEnergy(vo.getEnergy() - 10);
		return vo;
	}

	public DamaDTO energyplus(DamaDTO vo) {// 에너지가 증가하는경우 (밥먹기,잠자기)
		vo.setEnergy(vo.getEnergy() + 30);
		return vo;
	}

	public DamaDTO exptype1(DamaDTO vo) {
		vo.setExp(vo.getExp() + 10);
		return vo;

	}

	public DamaDTO exptype2(DamaDTO vo) {
		vo.setExp(vo.getExp() + 30);
		return vo;

	}

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
