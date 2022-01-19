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
		boolean logout; // �α��� �� ���� Ȯ��
		int game_menu;
		int dama_menu;
		String login = null;
		// ���� ������ �޴�����
		while (true) {
			logout = false;
			startpic();
			startprint();
			game_menu = sc.nextInt();
			if (game_menu == 1) {
				System.out.println("\n========= �� �� �� ==========");
				String id = getStrInput("ID  : "); //���̵� ��й�ȣ �Էºκ�
				int pw = getNumInput("PW  : ");
				login = mem.Login(id,pw);
				mem.last_date(login);
				if (login != null) {
					System.out.println("�α��� ����!!! ����� �ٸ���ġ�� Ű��������.");
					logout = true;
					while (true) {
						 d_menuprint();
						dama_menu = sc.nextInt();
						if (dama_menu == 1) { // ���
							String nick = getStrInput(" �ٸ���ġ�� ������ �Է����ּ���! : ");
							System.out.println("[1]�Ʊ��� [2]���Ǹ� [3]�ȸ�");
							int type = getNumInput(" �ٸ���ġ�� ������ �������ּ���! : ");
							if(dama.insertDama(login,nick,type)) {
								System.out.println("�ٸ���ġ ��� �Ϸ�!");
							}
							else {
								System.out.println("�ٸ���ġ ��� ����...");
							}

						} else if (dama_menu == 2) { // ����

							sel_list = dama.selectDama(login);// ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							if (sel_list.size() == 0) {
								System.out.println("������ �ٸ���ġ�� �����! \n���� �ٸ���ġ�� ������ּ���...");
							} else {
								damaprint(sel_list);
								int input = getNumInput(" � �ٸ���ġ�� �����ұ��? :");
								DamaDTO sel_dama = dama.select(sel_list,input);

								if (sel_dama.getEnergy() == 999) {
									System.out.println("�̹� ����� �ٸ���ġ����...");
								} else {
									pic.typeprint(sel_dama);
									int needs = lc.DamaNeeds(); // needs�� ��� �ε��� ����..
									while (true) {// Ű��� �׸��Ҷ� ����
										now_state(sel_dama);
										lc.sel_need(sel_dama, needs);
										System.out.println();
										if (sel_dama.isStop()) {
											break; // �ڷΰ���.
										}
										lc.energyCheck(sel_dama);
										lc.expCheck(sel_dama);
										lc.cleanCheck(sel_dama);
										dama.DamaUpdate(sel_dama); // column.. energy, exp�� 2�ڸ� number �׷��ٸ�.. ��������
										
										if (sel_dama.getEnergy() > 100) { // �����ʵ����ϰ� .. ����ġ�� �ٷ� ������ ��ȯ�ɼ��յ���.
											System.out.println(sel_dama.getNick() + "(��)�� �� �̻� �������� �ʴ´�...");
											break;
										}
									}
								}
							}
						} else if (dama_menu == 3) {// ��ŷ
							System.out.println("========= �ٸ���ġ ��ŷ =========");
							rank_list = dama.DamaRank(); // ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							for (DamaDTO vo : rank_list) {
								System.out.println(vo.toString());
							}
						} else if (dama_menu == 4) { // ����
							sel_list = dama.selectDama(login);// ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							if (sel_list.size() == 0) {
								System.out.println("���� �� �ٸ���ġ�� �����! \n���� �ٸ���ġ�� ������ּ���...");
							} else {
								damaprint(sel_list);
								String delete = getStrInput("������ �ٸ���ġ�� ������ �Է����ּ���! : ");
								if(dama.deleteDama(delete)) {
									 System.out.println("�ٸ���ġ ���� �Ϸ�!");
								}
								else {
									 System.out.println("�ٸ���ġ ���� ����...");
								}
								
							}
						} else {
							System.out.println("������� ���ư���");
							logout = true;
							break;
						}
					}
				}
				if(!logout) { // �ǵ��ư���� �α��� ���� ����
					System.out.println("�α��� ����! ID�� ��й�ȣ�� �ٽ� Ȯ���� �ּ���.");
				}
			} else if (game_menu == 2) {
				mem.insertMember();
			}
			else {
				System.out.println("�ٸ���ġ Ű��⸦ �����մϴ�...");
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
		System.out.println("===== �ٸ���ġ Ű��� ���� ======");
		System.out.println("1.�α���\n2.ȸ������\n3.��������");
		System.out.println("===========================");
		System.out.print("�޴� ���� >> ");
	}
	
	public static void d_menuprint() {
		System.out.println("===========================");
		System.out.println("1.�ٸ���ġ ���\n2.�ٸ���ġ ����\n3.�ٸ���ġ ��ŷ\n4.�ٸ���ġ ����\n5.������� ���ư���");
		System.out.println("===========================");
		System.out.print("�޴� ���� >> ");
	}
	
	public static void damaprint(ArrayList<DamaDTO> sel_list) {
		//System.out.println("========= ���� �ٸ���ġ =========");
		for (int i = 0; i < sel_list.size(); i++) {
			System.out.println("\n---------" + (i + 1) + "�� �ٸ���ġ" + "---------");
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
		System.out.println(" 1.�Ļ� �ð�!\t 2.�� �ð�!\t 3.���� �ð�! \n 4.� �ð�!\t 5.��� �ð�!\t 6.������� ���ư���");
	}
	
	private static String getStrInput(String msg) { //���ڿ� �Է� �޼ҵ�
		System.out.print(msg);
		return sc.next();
	}

	private static int getNumInput(String msg) { //������ �Է� �޼ҵ�
		System.out.print(msg);
		return sc.nextInt();
	}

}
