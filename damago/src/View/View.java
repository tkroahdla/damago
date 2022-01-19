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
		// ���� ������ �޴�����
		while (true) {
			startpic();
			System.out.println("===== �ٸ����� Ű��� ���� =====");
			System.out.println("1.�α���\n2.ȸ������\n3.��������");
			System.out.println("==========================");
			System.out.print("�޴����� >> ");
			game_menu = sc.nextInt();
			// User �α���
			if (game_menu == 1) {
				System.out.println("\n===== �� �� �� =====");
				login = mem.Login();
				if (login != null) {
					System.out.println("�α��� ����!!");
					while (true) {
						System.out.println("===========================");
						System.out.println("1.�ٸ����� ���\n2.�ٸ����� ����\n3.�ٸ����� ��ŷ\n4.�ڷ� ����");
						System.out.println("===========================");
						System.out.print("�޴����� >> ");
						dama_menu = sc.nextInt();
						if (dama_menu == 1) { // ���
							dama.insertDama(login);

						} else if (dama_menu == 2) { // ����
							System.out.println("===== �� �ٸ����� =====");
							sel_list = dama.selectDama(login);// ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							if(sel_list.size() ==0) {
								System.out.println("���� �� �ٸ���ġ�� �����!! \n�ٸ���ġ�� ������ּ���..");
								break;
							}
							sel_list = dama.selectDama(login); // ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							for (int i = 0; i < sel_list.size(); i++) {
								System.out.println("\n---------" + (i + 1) + "�� �ٸ���ġ"
										+ "---------");
								System.out.println(sel_list.get(i).toString());
							}
							DamaDTO sel_dama = dama.select(sel_list);
							System.out.println();

							int needs = lc.DamaNeeds(); // needs�� ��� �ε��� ����..
						
							while (true) {// Ű��� �׸��Ҷ� ����
								System.out.println("���� ����ġ : " + sel_dama.getExp());
								System.out.println("û�ᵵ : " + sel_dama.getCleaning());
								System.out.println("���� ������" + sel_dama.getEnergy());
								System.out.println("���� EXP" + sel_dama.getExp());
								System.out.println("�� 1.����̱�\t�� 2.������\t�� 3.����ֱ� \n�� 4.���Ű��\t�� 5.�İ��ֱ�\t�� 6.�ڷΰ���");
								lc.sel_need(sel_dama, needs);
								
								System.out.println();
								if(sel_dama.isStop()) {
									break; //�ڷΰ���.
								}
								//dama.DamaUpdate(sel_dama); // column.. energy, exp�� 2�ڸ� number �׷��ٸ�.. �������� ���̻� �����ʵ����ϰ� .. ����ġ�� �ٷ� ������ ��ȯ�ɼ��յ���.
							}
						} else if (dama_menu == 3) {// ��ŷ
							System.out.println("===== �ٸ����� ��ŷ =====");
							rank_list = dama.DamaRank(); // ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							for (DamaDTO vo : rank_list) {
								System.out.println(vo.toString());
							}
						} else {
							System.out.println("�ڷΰ���");
							break;
						}
					}

				}
				System.out.println("�α��� ����!!");
			} else if (game_menu == 2) {
				mem.insertMember();
			}

			else {
				System.out.println("���� ����");
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
