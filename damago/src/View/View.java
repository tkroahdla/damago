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
			System.out.println("===== 다마고치 키우기 게임 =====");
			System.out.println("1.로그인\n2.회원가입\n3.게임종료");
			System.out.println("==========================");
			System.out.print("메뉴 선택 >> ");
			game_menu = sc.nextInt();
			// User 로그인
			if (game_menu == 1) {
				System.out.println("\n===== 로 그 인 =====");
				login = mem.Login();
				mem.last_date(login);
				if (login != null) {
					System.out.println("로그인 성공!!! 당신의 다마고치를 키워보세요.");
					while (true) {
						System.out.println("===========================");
						System.out.println("1.다마고치 등록\n2.다마고치 관리\n3.다마고치 랭킹\n4.다마고치 삭제\n5.목록으로 돌아가기");
						System.out.println("===========================");
						System.out.print("메뉴 선택 >> ");
						dama_menu = sc.nextInt();
						if (dama_menu == 1) { // 등록
							dama.insertDama(login);

						} else if (dama_menu == 2) { // 관리

							sel_list = dama.selectDama(login);// 리스트만 존재함 출력해야 확인가능
							if (sel_list.size() == 0) {
								System.out.println("관리할 다마고치가 없어요! \n먼저 다마고치를 등록해주세요...");
							} else {
								System.out.println("===== 나의 다마고치 =====");
								for (int i = 0; i < sel_list.size(); i++) {
									System.out.println("\n---------" + (i + 1) + "번 다마고치" + "---------");
									System.out.println(sel_list.get(i).toString());
								}
								DamaDTO sel_dama = dama.select(sel_list);

								if (sel_dama.getEnergy() == 999) {
									System.out.println("이미 사망한 다마고치에요...");
								} else {
									// 인자로 리스트
									int needs = lc.DamaNeeds(); // needs에 욕망 인덱스 저장..

									while (true) {// 키우기 그만할때 까지
										System.out.println("현재 경험치는? " + sel_dama.getExp());
										System.out.println("현재 청결도는? " + sel_dama.getCleaning());
										System.out.println("현재 에너지는? " + sel_dama.getEnergy());
										System.out.println();
										// System.out.println("현재 EXP : " + sel_dama.getExp());
										System.out.println(
												"◆ 1.식사 시간!\t◆ 2.잘 시간!\t◆ 3.놀이 시간! \n◆ 4.운동 시간!\t◆ 5.목욕 시간!\t◆ 6.목록으로 돌아가기");
										lc.sel_need(sel_dama, needs);

										System.out.println();
										if (sel_dama.isStop()) {
											break; // 뒤로가기.
										}
										lc.energyCheck(sel_dama);
										lc.expCheck(sel_dama);

										dama.DamaUpdate(sel_dama); // column.. energy, exp는 2자리 number 그렇다면.. 에너지는
																	// 더이상
										if (sel_dama.getEnergy() > 100) { // 차지않도록하고 .. 경험치는 바로 레벨로 전환될수잇도록.
											System.out.println(sel_dama.getNick() + "(이)가 죽었어요..");
											break;
										}
										if (sel_dama.isStop()) {
											break; // 뒤로가기.
										}
										lc.energyCheck(sel_dama);
										lc.expCheck(sel_dama);

										dama.DamaUpdate(sel_dama); // column.. energy, exp는 2자리 number 그렇다면.. 에너지는
																	// 더이상
										if (sel_dama.getEnergy() > 100) { // 차지않도록하고 .. 경험치는 바로 레벨로 전환될수잇도록.
											System.out.println(sel_dama.getNick() + "(이)가 더 이상 움직이지 않는다...");
											break;
										}

									}
								}
							}
						} else if (dama_menu == 3) {// 랭킹
							System.out.println("===== 다마고치 랭킹 =====");
							rank_list = dama.DamaRank(); // 리스트만 존재함 출력해야 확인가능
							for (DamaDTO vo : rank_list) {
								System.out.println(vo.toString());
							}
						} else if (dama_menu == 4) { // 삭제
							sel_list = dama.selectDama(login);// 리스트만 존재함 출력해야 확인가능
							if (sel_list.size() == 0) {
								System.out.println("삭제 할 다마고치가 없어요! \n먼저 다마고치를 등록해주세요...");
							} else {
								System.out.println("===== 내 다마고치 =====");
								for (int i = 0; i < sel_list.size(); i++) {
									System.out.println("\n---------" + (i + 1) + "번 다마고치" + "---------");
									System.out.println(sel_list.get(i).toString());
								}
								dama.deleteDama();
							}
						} else {
							System.out.println("목록으로 돌아가기");
							break;
						}
					}

				}
				System.out.println("로그인 실패! ID와 비밀번호를 다시 확인해 주세요.");
			} else if (game_menu == 2) {
				mem.insertMember();
			}

			else {
				System.out.println("다마고치 키우기를 종료합니다...");
				break;
			}

		}
	}

	public static void startpic() {
		System.out.println();
		System.out.println(",--------.                                         ,--.        ,--.     ,--.    ");
		System.out.println("'--.  .--',--,--.,--,--,--. ,--,--. ,---.  ,---. ,-'  '-. ,---.|  ,---. `--'    ");
		System.out.println("   |  |  ' ,-.  ||        |' ,-.  || .-. || .-. |'-.  .-'| .--'|  .-.  |,--.    ");
		System.out.println("   |  |  \\ '-'  || |  |  |\\ '-'  | ' ' /' ' '-'  '  |  | \\``|  | |  ||  |    ");
		System.out.println("   `--'   `--`--'`--`--`--' `--`--'.`-  /  `---'   `--'   `---'`--' `--'`--'    ");
		System.out.println("                                   `---'                                        ");
	}

}
