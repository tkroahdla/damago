package Controller;

import java.util.Scanner;

import Model.DamaDTO;

public class Logic_Controller {
	Scanner sc = new Scanner(System.in);
	//System.out.println("1.밥먹이기\\n2.잠재우기\\n3.놀아주기\\n4.운동하기\\n5.뒤로가기");
	public DamaDTO sel_need(DamaDTO vo) {
		int input = getNumInput("무엇을 해줄까요??");
		if(input == 1) {
			System.out.println("밥을 먹었어요.");
			vo.getEnergy();
			//이러면 세터가 필요한건가.
		}
		else if(input ==2)System.out.println("잠을 잤어요.");
		else if(input ==3)System.out.println("놀았어요.");
		else if(input ==4)System.out.println("운동을 했어요."); //이거 매끄럽게좀 바꿔주세용..
		else {
			System.out.println("잘못된 입력이에요.");
		}
		return vo;
		
	}
	public DamaDTO energytype(DamaDTO vo) {
		
		return vo;
	}
	
	
	private String getStrInput(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

	private int getNumInput(String msg) {
		System.out.print(msg);
		return sc.nextInt();
	}
	
	
	public void start() {
		//menu(); // 메뉴 선택화면 출력
		//select(); // 메뉴 선택 번호 입력받기
		//process(); // 메뉴 작업에 따른 기능 수행 // 만일 JDBC 작업이라면 JDBC_Controller 객체에서 호출.
	}

}
