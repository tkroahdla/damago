package View;
import java.util.ArrayList;
import java.util.Scanner;
import Controller.Logic_Controller;
import Model.DamaDAO;
import Model.DamaDTO;
import Model.MemberDAO;
import pic.pic;

public class View {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
		ArrayList<DamaDTO> sel_list = null;
		ArrayList<DamaDTO> rank_list;
		DamaDAO dama = new DamaDAO();
		MemberDAO mem = new MemberDAO();
		Logic_Controller lc = new Logic_Controller();
		boolean logout; // 로그인 중 인지 확인
		int game_menu;
		int dama_menu;
		String login = null;
		// 메인 페이지 메뉴선택
		while (true) {
			logout = false;
			startpic();
			startprint();
			game_menu = sc.nextInt();
			if (game_menu == 1) {
				System.out.println("\n========= 로 그 인 ==========");
				String id = getStrInput("ID  : "); //아이디 비밀번호 입력부분
				int pw = getNumInput("PW  : ");
				login = mem.Login(id,pw);
				mem.last_date(login);
				if (login != null) {
					System.out.println("로그인 성공!!! 당신의 다마고치를 키워보세요.");
					logout = true;
					while (true) {
						 d_menuprint();
						dama_menu = sc.nextInt();
						if (dama_menu == 1) { // 등록
							String nick = getStrInput(" 다마고치의 별명을 입력해주세요! : ");
							System.out.println("[1]아구몬 [2]파피몬 [3]팔몬");
							int type = getNumInput(" 다마고치의 종류를 선택해주세요! : ");
							if(dama.insertDama(login,nick,type)) {
								System.out.println("다마고치 등록 완료!");
							}
							else {
								System.out.println("다마고치 등록 실패...");
							}

						} else if (dama_menu == 2) { // 관리

							sel_list = dama.selectDama(login);// 리스트만 존재함 출력해야 확인가능
							if (sel_list.size() == 0) {
								System.out.println("관리할 다마고치가 없어요! \n먼저 다마고치를 등록해주세요...");
							} else {
								damaprint(sel_list);
								int input = getNumInput(" 어떤 다마고치를 선택할까요? :");
								DamaDTO sel_dama = dama.select(sel_list,input);

								if (sel_dama.getEnergy() == 999) {
									System.out.println("이미 사망한 다마고치에요...");
								} else {
									pic.typeprint(sel_dama);
									int needs = lc.DamaNeeds(); // needs에 욕망 인덱스 저장..
									while (true) {// 키우기 그만할때 까지
										now_state(sel_dama);
										lc.sel_need(sel_dama, needs);
										System.out.println();
										if (sel_dama.isStop()) {
											break; // 뒤로가기.
										}
										lc.energyCheck(sel_dama);
										lc.expCheck(sel_dama);
										lc.cleanCheck(sel_dama);
										dama.DamaUpdate(sel_dama); // column.. energy, exp는 2자리 number 그렇다면.. 에너지는
										
										if (sel_dama.getEnergy() > 100) { // 차지않도록하고 .. 경험치는 바로 레벨로 전환될수잇도록.
											System.out.println(sel_dama.getNick() + "(이)가 더 이상 움직이지 않는다...");
											break;
										}
									}
								}
							}
						} else if (dama_menu == 3) {// 랭킹
							System.out.println("========= 다마고치 랭킹 =========");
							rank_list = dama.DamaRank(); // 리스트만 존재함 출력해야 확인가능
							for (DamaDTO vo : rank_list) {
								System.out.println(vo.toString());
							}
						} else if (dama_menu == 4) { // 삭제
							sel_list = dama.selectDama(login);// 리스트만 존재함 출력해야 확인가능
							if (sel_list.size() == 0) {
								System.out.println("삭제 할 다마고치가 없어요! \n먼저 다마고치를 등록해주세요...");
							} else {
								damaprint(sel_list);
								String delete = getStrInput("삭제할 다마고치의 별명을 입력해주세요! : ");
								if(dama.deleteDama(delete)) {
									 System.out.println("다마고치 삭제 완료!");
								}
								else {
									 System.out.println("다마고치 삭제 실패...");
								}
								
							}
						} else {
							System.out.println("목록으로 돌아가기");
							logout = true;
							break;
						}
					}
				}
				if(!logout) { // 되돌아가기와 로그인 실패 구분
					System.out.println("로그인 실패! ID와 비밀번호를 다시 확인해 주세요.");
				}
			} else if (game_menu == 2) {
				mem.insertMember();
			}
			else {
				System.out.println("다마고치 키우기를 종료합니다...");
				break;
			}
			mem.sysdate_update(login);
		}
	}

	public static void startpic() {
		System.out.println();
		System.out.println(",--------.                                         ,--.        ,--.     ,--.    ");
		System.out.println("'--.  .--',--,--.,--,--,--. ,--,--. ,---.  ,---. ,-'  '-. ,---.|  ,---. `--'    ");
		System.out.println("   |  |  ' ,-.  ||        |' ,-.  || .-. || .-. |'-.  .-'| .--'|  .-.  |,--.    ");
		System.out.println("   |  |  \\ '-'  ||  |  |  |\\ '-'  |  ' ' /' ' '-'  '  |  | \\`` |  | |  ||  |    ");
		System.out.println("   `--'   `--`--'`--`--`--' `--`--'.`-  /  `---'   `--'   `---'`--' `--'`--'    ");
		System.out.println("                                   `---'                                        ");
	}
	
	public static void startpic2() {
		System.out.println("DDDDDDDDDDDDD                  AAA               MMMMMMMM               MMMMMMMM               AAA                  GGGGGGGGGGGGG     OOOOOOOOO     TTTTTTTTTTTTTTTTTTTTTTT       CCCCCCCCCCCCCHHHHHHHHH     HHHHHHHHHIIIIIIIIII");
		System.out.println("D::::::::::::DDD              A:::A              M:::::::M             M:::::::M              A:::A              GGG::::::::::::G   OO:::::::::OO   T:::::::::::::::::::::T    CCC::::::::::::CH:::::::H     H:::::::HI::::::::I");
		System.out.println("D:::::::::::::::DD           A:::::A             M::::::::M           M::::::::M             A:::::A           GG:::::::::::::::G OO:::::::::::::OO T:::::::::::::::::::::T  CC:::::::::::::::CH:::::::H     H:::::::HI::::::::I");
		System.out.println("DDD:::::DDDDD:::::D         A:::::::A            M:::::::::M         M:::::::::M            A:::::::A         G:::::GGGGGGGG::::GO:::::::OOO:::::::OT:::::TT:::::::TT:::::T C:::::CCCCCCCC::::CHH::::::H     H::::::HHII::::::II");
		System.out.println("  D:::::D    D:::::D       A:::::::::A           M::::::::::M       M::::::::::M           A:::::::::A       G:::::G       GGGGGGO::::::O   O::::::OTTTTTT  T:::::T  TTTTTTC:::::C       CCCCCC  H:::::H     H:::::H    I::::I");
		System.out.println("  D:::::D     D:::::D     A:::::A:::::A          M:::::::::::M     M:::::::::::M          A:::::A:::::A     G:::::G              O:::::O     O:::::O        T:::::T       C:::::C                H:::::H     H:::::H    I::::I");
		System.out.println("  D:::::D     D:::::D    A:::::A A:::::A         M:::::::M::::M   M::::M:::::::M         A:::::A A:::::A    G:::::G              O:::::O     O:::::O        T:::::T       C:::::C                H::::::HHHHH::::::H    I::::I");
		System.out.println("  D:::::D     D:::::D   A:::::A   A:::::A        M::::::M M::::M M::::M M::::::M        A:::::A   A:::::A   G:::::G    GGGGGGGGGGO:::::O     O:::::O        T:::::T       C:::::C                H:::::::::::::::::H    I::::I");
		System.out.println("  D:::::D     D:::::D  A:::::A     A:::::A       M::::::M  M::::M::::M  M::::::M       A:::::A     A:::::A  G:::::G    G::::::::GO:::::O     O:::::O        T:::::T       C:::::C                H:::::::::::::::::H    I::::I");
		System.out.println("  D:::::D     D:::::D A:::::AAAAAAAAA:::::A      M::::::M   M:::::::M   M::::::M      A:::::AAAAAAAAA:::::A G:::::G    GGGGG::::GO:::::O     O:::::O        T:::::T       C:::::C                H::::::HHHHH::::::H    I::::I");
		System.out.println("  D:::::D     D:::::D A:::::AAAAAAAAA:::::A      M::::::M   M:::::::M   M::::::M      A:::::AAAAAAAAA:::::A G:::::G    GGGGG::::GO:::::O     O:::::O        T:::::T       C:::::C                H::::::HHHHH::::::H    I::::I");
		System.out.println("  D:::::D     D:::::DA:::::::::::::::::::::A     M::::::M    M:::::M    M::::::M     A:::::::::::::::::::::AG:::::G        G::::GO:::::O     O:::::O        T:::::T       C:::::C                H:::::H     H:::::H    I::::I");
		System.out.println("  D:::::D    D:::::DA:::::AAAAAAAAAAAAA:::::A    M::::::M     MMMMM     M::::::M    A:::::AAAAAAAAAAAAA:::::AG:::::G       G::::GO::::::O   O::::::O        T:::::T        C:::::C       CCCCCC  H:::::H     H:::::H    I::::I");
		System.out.println("DDD:::::DDDDD:::::DA:::::A             A:::::A   M::::::M               M::::::M   A:::::A             A:::::AG:::::GGGGGGGG::::GO:::::::OOO:::::::O      TT:::::::TT       C:::::CCCCCCCC::::CHH::::::H     H::::::HHII::::::II");
		System.out.println("D:::::::::::::::DDA:::::A               A:::::A  M::::::M               M::::::M  A:::::A               A:::::AGG:::::::::::::::G OO:::::::::::::OO       T:::::::::T        CC:::::::::::::::CH:::::::H     H:::::::HI::::::::I");
		System.out.println("D::::::::::::DDD A:::::A                 A:::::A M::::::M               M::::::M A:::::A                 A:::::A GGG::::::GGG:::G   OO:::::::::OO         T:::::::::T          CCC::::::::::::CH:::::::H     H:::::::HI::::::::I");
		System.out.println("DDDDDDDDDDDDD   AAAAAAA                   AAAAAAAMMMMMMMM               MMMMMMMMAAAAAAA                   AAAAAAA   GGGGGG   GGGG     OOOOOOOOO           TTTTTTTTTTT             CCCCCCCCCCCCCHHHHHHHHH     HHHHHHHHHIIIIIIIIII");
	}
	
	public static void startprint() {
		System.out.println("===== 다마고치 키우기 게임 ======");
		System.out.println("1.로그인\n2.회원가입\n3.게임종료");
		System.out.println("===========================");
		System.out.print("메뉴 선택 >> ");
	}
	
	public static void d_menuprint() {
		System.out.println("===========================");
		System.out.println("1.다마고치 등록\n2.다마고치 관리\n3.다마고치 랭킹\n4.다마고치 삭제\n5.목록으로 돌아가기");
		System.out.println("===========================");
		System.out.print("메뉴 선택 >> ");
	}
	
	public static void damaprint(ArrayList<DamaDTO> sel_list) {
		//System.out.println("========= 나의 다마고치 =========");
		for (int i = 0; i < sel_list.size(); i++) {
			System.out.println("\n---------" + (i + 1) + "번 다마고치" + "---------");
			System.out.println(sel_list.get(i).toString());
		}
	}
	
	public static void now_state(DamaDTO sel_dama) {
		System.out.println("=================================================");
		System.out.print("   EXP :  " + sel_dama.getExp());
		System.out.print("   |  ENERGY :  " + sel_dama.getEnergy());
		System.out.println("   |  CLEAN :  " + sel_dama.getCleaning());
		System.out.println("=================================================");
		System.out.println();
		System.out.println(" 1.식사 시간!\t 2.잘 시간!\t 3.놀이 시간! \n 4.운동 시간!\t 5.목욕 시간!\t 6.목록으로 돌아가기");
	}
	
	private static String getStrInput(String msg) { //문자열 입력 메소드
		System.out.print(msg);
		return sc.next();
	}

	private static int getNumInput(String msg) { //정수형 입력 메소드
		System.out.print(msg);
		return sc.nextInt();
	}

}
