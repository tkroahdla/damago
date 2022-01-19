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
			System.out.println("===== �ٸ���ġ Ű��� ���� =====");
			System.out.println("1.�α���\n2.ȸ������\n3.��������");
			System.out.println("==========================");
			System.out.print("�޴� ���� >> ");
			game_menu = sc.nextInt();
			// User �α���
			if (game_menu == 1) {
				System.out.println("\n===== �� �� �� =====");
				login = mem.Login();
				mem.last_date(login);
				if (login != null) {
					System.out.println("�α��� ����!!! ����� �ٸ���ġ�� Ű��������.");
					while (true) {
						System.out.println("===========================");
						System.out.println("1.�ٸ���ġ ���\n2.�ٸ���ġ ����\n3.�ٸ���ġ ��ŷ\n4.�ٸ���ġ ����\n5.������� ���ư���");
						System.out.println("===========================");
						System.out.print("�޴� ���� >> ");
						dama_menu = sc.nextInt();
						if (dama_menu == 1) { // ���
							dama.insertDama(login);

						} else if (dama_menu == 2) { // ����

							sel_list = dama.selectDama(login);// ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							if (sel_list.size() == 0) {
								System.out.println("������ �ٸ���ġ�� �����! \n���� �ٸ���ġ�� ������ּ���...");
							} else {
								System.out.println("===== ���� �ٸ���ġ =====");
								for (int i = 0; i < sel_list.size(); i++) {
									System.out.println("\n---------" + (i + 1) + "�� �ٸ���ġ" + "---------");
									System.out.println(sel_list.get(i).toString());
								}
								DamaDTO sel_dama = dama.select(sel_list);

								if (sel_dama.getEnergy() == 999) {
									System.out.println("�̹� ����� �ٸ���ġ����...");
								} else {
									// ���ڷ� ����Ʈ
									int needs = lc.DamaNeeds(); // needs�� ��� �ε��� ����..

									while (true) {// Ű��� �׸��Ҷ� ����
										System.out.println("���� ����ġ��? " + sel_dama.getExp());
										System.out.println("���� û�ᵵ��? " + sel_dama.getCleaning());
										System.out.println("���� ��������? " + sel_dama.getEnergy());
										System.out.println();
										// System.out.println("���� EXP : " + sel_dama.getExp());
										System.out.println(
												"�� 1.�Ļ� �ð�!\t�� 2.�� �ð�!\t�� 3.���� �ð�! \n�� 4.� �ð�!\t�� 5.��� �ð�!\t�� 6.������� ���ư���");
										lc.sel_need(sel_dama, needs);

										System.out.println();
										if (sel_dama.isStop()) {
											break; // �ڷΰ���.
										}
										lc.energyCheck(sel_dama);
										lc.expCheck(sel_dama);

										dama.DamaUpdate(sel_dama); // column.. energy, exp�� 2�ڸ� number �׷��ٸ�.. ��������
																	// ���̻�
										if (sel_dama.getEnergy() > 100) { // �����ʵ����ϰ� .. ����ġ�� �ٷ� ������ ��ȯ�ɼ��յ���.
											System.out.println(sel_dama.getNick() + "(��)�� �׾����..");
											break;
										}
										if (sel_dama.isStop()) {
											break; // �ڷΰ���.
										}
										lc.energyCheck(sel_dama);
										lc.expCheck(sel_dama);

										dama.DamaUpdate(sel_dama); // column.. energy, exp�� 2�ڸ� number �׷��ٸ�.. ��������
																	// ���̻�
										if (sel_dama.getEnergy() > 100) { // �����ʵ����ϰ� .. ����ġ�� �ٷ� ������ ��ȯ�ɼ��յ���.
											System.out.println(sel_dama.getNick() + "(��)�� �� �̻� �������� �ʴ´�...");
											break;
										}

									}
								}
							}
						} else if (dama_menu == 3) {// ��ŷ
							System.out.println("===== �ٸ���ġ ��ŷ =====");
							rank_list = dama.DamaRank(); // ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							for (DamaDTO vo : rank_list) {
								System.out.println(vo.toString());
							}
						} else if (dama_menu == 4) { // ����
							sel_list = dama.selectDama(login);// ����Ʈ�� ������ ����ؾ� Ȯ�ΰ���
							if (sel_list.size() == 0) {
								System.out.println("���� �� �ٸ���ġ�� �����! \n���� �ٸ���ġ�� ������ּ���...");
							} else {
								System.out.println("===== �� �ٸ���ġ =====");
								for (int i = 0; i < sel_list.size(); i++) {
									System.out.println("\n---------" + (i + 1) + "�� �ٸ���ġ" + "---------");
									System.out.println(sel_list.get(i).toString());
								}
								dama.deleteDama();
							}
						} else {
							System.out.println("������� ���ư���");
							break;
						}
					}

				}
				System.out.println("�α��� ����! ID�� ��й�ȣ�� �ٽ� Ȯ���� �ּ���.");
			} else if (game_menu == 2) {
				mem.insertMember();
			}

			else {
				System.out.println("�ٸ���ġ Ű��⸦ �����մϴ�...");
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
