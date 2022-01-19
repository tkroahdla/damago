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
					// 여기부터 게임진행

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
										+ "-------------------------------------------------------------------------------");
								System.out.println(sel_list.get(i).toString());
							}
							DamaDTO sel_dama = dama.select(sel_list);
							System.out.println();

							int needs = lc.DamaNeeds(); // needs에 욕망 인덱스 저장..
						
							while (true) {// 키우기 그만할때 까지
								System.out.println("현재 경험치 : " + sel_dama.getExp());
								System.out.println("청결도 : " + sel_dama.getCleaning());
								// System.out.println("◆ 1.밥먹이기 \n◆ 2.잠재우기 \n◆ 3.놀아주기 \n◆ 4.운동하기 \n◆ 5.뒤로가기
								// \n");
								System.out.println("◆ 1.밥먹이기\n◆ 2.잠재우기 \n◆ 3.놀아주기 \n◆ 4.운동시키기\n◆ 5.씻겨주기 \n◆ 6.뒤로가기");
								lc.sel_need(sel_dama, needs);
								System.out.println("현재 에너지" + sel_dama.getEnergy());
								System.out.println("현재 EXP" + sel_dama.getExp());
								System.out.println();
								needs = 9;
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
//			// User 로그인
//			if (game_menu == 1) {
//				System.out.println("\n=====로 그 인=====");
//				login = mem.Login();

//				if (!login.equals()) {
//					System.out.println("로그인 성공!!");
//					// 여기부터 게임진행
//					while (true) {
//
//						System.out.println("1.다마고찌 등록\n2.다마고찌 관리\n3.다마고찌 랭킹\n4.뒤로 가기");
//						System.out.println("===========================");
//						dama_menu = sc.nextInt();
//						
//						if(dama_menu==1) {
//							System.out.println("다마고찌 등록");
//							dama.insertDama(login);
//						}else if(dama_menu==2) {
//							System.out.println("다마고찌 관리");
//						}else if(dama_menu==3) {
//							System.out.println("다마고찌 랭킹");
//						}else if(dama_menu==4) {
//							System.out.println("뒤로가기");
//						}
//					}
//
//			
//		
//			
//			} else if (game_menu == 2) {
//				mem.insertMember();
//			}
//
//			else {
//				System.out.println("게임 종료");
//				break;
//			}
//	}
//
//	// if(check2==true) {
//
//	// 다마고찌 관리 메뉴선택 while(true) { System.out.print("메뉴선택 >> "); dama_menu =
//	sc.nextInt();
//
//	// 다마고찌 등록 if(dama_menu==1) { System.out.println("다마고찌 등록");
//	System.out.println("등록할 다마고찌 정보를 입력하세요.");System.out.print("이름 : ");
//
//	String dama_name = sc.next();System.out.print("종류 : "); // 속성은 어떤 종류가 있는걸로 할 것인지
//	String dama_type = sc.next();

//				  boolean check3 = dama.insertdama(dama_name, dama_type);
//				  
//				  if(check3==true) { System.out.println("다마고찌 생성 성공"); }else {
//				  System.out.println("다마고찌 생성 실패"); }
//				  
//				  //다마고치 관리 }else if(dama_menu==2) { System.out.println("다마고찌 관리");
//				  
//				  //내 다마고찌 현재상태(욕구충족상태) // 레벨, 이름, 종류, 에너지, 경험치 출력
//				  
//				  //DB에 등록된 다마고찌의 전체 조회가 아닌 현재 로그인한 유저의 다마고찌 상태를 출력 //현재 로그인한 유저의 다마고치의 정보를
//				  가져오는 조건에 대한 조건작성은..잘모르겠음.. System.out.println("레벨 : " + vo.getDamaLevel());
//				  System.out.println("이름 : " + vo.getDamaName()); System.out.println("종류 : " +
//				  vo.getDamaType()); System.out.println("에너지 : " + vo.getDamaEnergy());
//				  System.out.println("경험치 : " + vo.getDamaExp()); System.out.println();
//				  
//				  //다마고찌 행동선택 while(true) { System.out.println("1.밥먹기\n2.잠자기\n3.운동하기\n4.공부하기");
//				  int act_menu = sc.nextInt();
//				  
//				  if(act_menu==1) { System.out.println("밥을 먹는다!(포만감+10)"); //(포만감+10)은 출력 예시,
//				  밥을 먹으면 포만감이 얼마나 차는지, //에너지가 얼만큼 회복하는지, 다른 요소나 상태가 어떻게 얼만큼 바뀌는지도 정해야할듯.
//				  
//				  //욕구 충족해소의 경우, 욕구를 해소하기 위한 행동의 경우 다마고찌가 원하는 욕구이면 //에너지가 증가하게, 욕구를 해소하지 않고 다른
//				  행동을 하면 에너지가 감소하게끔 하는 조건도 설정
//				  
//				  }else if(act_menu==2) { System.out.println("잠을 잔다.."); }else if(act_menu==3)
//				  { System.out.println("운동을 한다!"); }else if(act_menu==4) {
//				  System.out.println("공부를 한다!"); }else {
//				  System.out.println("잘못입력했습니다. 다시 입력해주세요."); } }
//				  
//				  //다마고찌 랭킹 }else if(dama_menu==3) { System.out.println("다마고치 랭킹");
//				  
//				  //DB에 등록된 다마고찌의 전체 조회가 아닌 현재 로그인한 유저의 다마고찌 상태를 출력 //현재 로그인한 유저의 다마고찌의 정보를
//				  가져오는 조건에 대한 조건작성은..잘모르겠음..
//				  System.out.println("\n===========================");
//				  
//				  dama.selectDama();
//				  
//				  ArrayList<DamaVO> al = dama.selectDama();
//				  
//				  System.out.println();
//				  
//				  for(DamaVO vo : al) { System.out.println("1등 : " + vo.);
//				  System.out.println("2등 : " + vo.); System.out.println("4등 : " + vo.);
//				  System.out.println("5등 : " + vo.); }
//				  System.out.println("==========================="); } }

		// User 회원가입
//			} else if (game_menu == 2) {
//				mem.insertMember();
//				boolean check2 = true;
//
//				// User 회원가입 성공
//				if (check2 == true) {
//					System.out.println("회원가입 성공!!");
//
//					// User 회원가입 실패
//				} else {
//					System.out.println("회원가입 실패..");
//				}
//
//				// 게임 종료
//			} else if (game_menu == 3)
//
//			{
//				System.out.println("게임 종료");
//				break;
//			} else {
//				System.out.println("잘못입력했습니다. 다시 입력해주세요.");
//			}

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
