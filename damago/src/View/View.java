package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Logic_Controller;
import Model.DamaDAO;
import Model.DamaDTO;
import Model.MemberDAO;

public class View {

	public static void main(String[] args) {
		ArrayList<DamaDTO> sel_list = null;
		ArrayList<DamaDTO> rank_list;
		DamaDAO dama = new DamaDAO();
		MemberDAO mem = new MemberDAO();
		Logic_Controller lc = new Logic_Controller();
		String id = null;
		int pw = 0;

		int input = 0;
		Scanner sc = new Scanner(System.in);
		int game_menu;
		int dama_menu;
		String login = null;
		// 메인 페이지 메뉴선택
		while (true) {
			startpic();
			System.out.println("===== 다마고찌 키우기 게임 =====");
			System.out.println("1.로그인\n2.회원가입\n3.게임종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 >> ");
			game_menu = sc.nextInt();
			// User 로그인
			if (game_menu == 1) {
				System.out.println("\n=====로 그 인=====");
				login = mem.Login();
				if (login != null) {
					System.out.println("로그인 성공!!");
					while (true) {
						System.out.println("===========================");
						System.out.println("1.다마고찌 등록\n2.다마고찌 관리\n3.다마고찌 랭킹\n4.뒤로 가기");
						System.out.println("===========================");
						System.out.print("메뉴선택 >> ");
						dama_menu = sc.nextInt();
						if (dama_menu == 1) { // 등록
							dama.insertDama(login);

						} else if (dama_menu == 2) { // 관리
							System.out.println("===== 내 다마고찌 =====");
							sel_list = dama.selectDama(login); // 리스트만 존재함 출력해야 확인가능
							for (int i = 0; i < sel_list.size(); i++) {
								//
								System.out.println("---------" + (i + 1) + "번 다마고치"
										+ "---------");
								System.out.println(sel_list.get(i).toString());
							}
							DamaDTO sel_dama = dama.select(sel_list);
							System.out.println();

							int needs = lc.DamaNeeds(); // needs에 욕망 인덱스 저장..
						
							while (true) {// 키우기 그만할때 까지
								System.out.println("현재 경험치 : " + sel_dama.getExp());
								System.out.println("청결도 : " + sel_dama.getCleaning());
								System.out.println("현재 에너지" + sel_dama.getEnergy());
								System.out.println("현재 EXP" + sel_dama.getExp());
								System.out.println("◆ 1.밥먹이기\t◆ 2.잠재우기\t◆ 3.놀아주기 \n◆ 4.운동시키기\t◆ 5.씻겨주기\t◆ 6.뒤로가기");
								lc.sel_need(sel_dama, needs);
								
								System.out.println();
								if(sel_dama.isStop()) {
									break; //뒤로가기.
								}
							}
						} else if (dama_menu == 3) {// 랭킹
							System.out.println("===== 다마고찌 랭킹 =====");
							rank_list = dama.DamaRank(); // 리스트만 존재함 출력해야 확인가능
							for (DamaDTO vo : rank_list) {
								System.out.println(vo.toString());
							}
						} else {
							System.out.println("뒤로가기");
							break;
						}
					}

				}
				System.out.println("로그인 실패!!");
			} else if (game_menu == 2) {
				mem.insertMember();
			}

			else {
				System.out.println("게임 종료");
				break;
			}

		}
	}
	public static void startpic() {
		System.out.println();
		System.out.println(",--------.                                         ,--.        ,--.     ,--.    ");
		System.out.println("'--.  .--',--,--.,--,--,--. ,--,--. ,---.  ,---. ,-'  '-. ,---.|  ,---. `--'    ");
		System.out.println("   |  |  ' ,-.  ||        |' ,-.  || .-. || .-. |'-.  .-'| .--'|  .-.  |,--.    ");
		System.out.println("   |  |  \\ '-'  ||  |  |  |\\ '-'  |' '-' '' '-' '  |  |  \\ `--.|  | |  ||  | ");
		System.out.println("   `--'   `--`--'`--`--`--' `--`--'.`-  /  `---'   `--'   `---'`--' `--'`--'    ");
		System.out.println("                                   `---'                                        ");
	}

}
