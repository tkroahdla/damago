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

	public DamaDTO expCheck(DamaDTO vo) {
		if (vo.getExp() > 99) {
			vo.setExp(vo.getExp() % 100);
			vo.setLevel(vo.getLevel() + 1);
		}
		return vo;
	}
	
	public DamaDTO energyCheck(DamaDTO vo) {
		if(vo.getEnergy()< -50) {
			System.out.println(vo.getNick()+"(은)는 움직이기 힘들어한다... ");
		}
		else if(vo.getEnergy()< -70) {
			System.out.println(vo.getNick()+"(은)는 축 처져있다...");
		}
		else if(vo.getEnergy()< -90) {
			System.out.println(vo.getNick()+"(은)는 힘이 없다...");
		}
		else if (vo.getEnergy() < -99) {
			vo.setEnergy(-999);
			System.out.println(vo.getNick()+"(은)는 숨을 쉬지 않는다...");
		}
		return vo;
	}
	
	public boolean deathCheck(DamaDTO vo) {
		if(vo.getEnergy()==-999) return true;
		
		return false;
	}
	
	
	
	public DamaDTO dama_death(DamaDTO vo) {
		return vo;
		
	}

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

	public DamaDTO sel_need(DamaDTO vo, int needs) { // 매개인자로 다마 객체와, 욕망 인덱스번호 받음. 여기서 needs는 밖에서 need메서드 호출하면 int가 나오는데
														// 그것임
		// int need = dama.DamaNeeds();
		System.out.println("스테이트는 : " + vo.getState());
		int input = getNumInput("무엇을 해줄까요??");
		if (input == 6) { // 종료인 경우가 우선순위가 될 수 있도록 배치 // 종료인게 우선순위가 먼저여야 해서,, 그 다음 우선순위는 욕망 해결
			vo.setStop(true); // 다마고치 필드에 stop이라고 값을 정의했어요,
			return vo; // 종료누르면 트루로 바꾸고 반환
		}
		if (vo.getState() == 1) { // 이건 getstate가 1일때. 왜냐하면 초기값이 0이라서 1인경우는 정상작동하는걸루..
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
			} else {
				System.out.println("잘못된 입력이에요.");
			}
			return vo;
		}

		if (needs + 1 == input && vo.getState() == 0) { // 이건 욕망을 풀어주는 번호 눌럿을때 && 그리구 state상태가 0일때 0이라는건 욕망이 있다는뜻!
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
			System.out.println(vo.getNick() + "(이)가 삐졌다."); // 나머지는 욕망을 못들어주는 거기때문에 삐졋다고 출력함..
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
