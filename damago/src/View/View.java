package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.DamaDAO;

import Model.MemberDAO;

public class View {

	public static void main(String[] args) {
		String id = null;
		int pw = 0;
		DamaDAO dama = new DamaDAO();
		MemberDAO mem = new MemberDAO();
		int input = 0;
		Scanner sc = new Scanner(System.in);

		int game_menu;
		int dama_menu;

		// ���� ������ �޴�����
		while (true) {
			System.out.println("===== �ٸ����� Ű��� ���� =====");
			System.out.println("1.�α���\n2.ȸ������\n3.��������");
			System.out.println("==========================");
			System.out.print("�޴����� >> ");
			game_menu = sc.nextInt();
			// User �α���
			if (game_menu == 1) {
				System.out.println("\n=====�� �� ��=====");
				String login = mem.Login();
				if (login == 1) {
					System.out.println("�α��� ����!!");
					// ������� ��������
					while (true) {

						System.out.println("1.�ٸ����� ���\n2.�ٸ����� ����\n3.�ٸ����� ��ŷ\n4.�ڷ� ����");
						System.out.println("===========================");
						dama_menu = sc.nextInt();
					}

				} else if (login == 0) {
					System.out.println("��й�ȣ Ʋ�Ⱦ��!");
				} else if (login == -1) {
					System.out.println("���̵� �����!");
				} else {
					System.out.println("�����ͺ��̽� ����");
				}

			} else if (game_menu == 2) {
				mem.insertMember();
			}

			else {
				System.out.println("���� ����");
				break;
			}

			// if(check2==true) {

			// �ٸ����� ���� �޴����� while(true) { System.out.print("�޴����� >> "); dama_menu =
			sc.nextInt();

			// �ٸ����� ��� if(dama_menu==1) { System.out.println("�ٸ����� ���");
			System.out.println("����� �ٸ����� ������ �Է��ϼ���.");
			System.out.print("�̸� : ");
			String dama_name = sc.next();
			System.out.print("���� : "); // �Ӽ��� � ������ �ִ°ɷ� �� ������
			String dama_type = sc.next();

//				  boolean check3 = dama.insertdama(dama_name, dama_type);
//				  
//				  if(check3==true) { System.out.println("�ٸ����� ���� ����"); }else {
//				  System.out.println("�ٸ����� ���� ����"); }
//				  
//				  //�ٸ���ġ ���� }else if(dama_menu==2) { System.out.println("�ٸ����� ����");
//				  
//				  //�� �ٸ����� �������(�屸��������) // ����, �̸�, ����, ������, ����ġ ���
//				  
//				  //DB�� ��ϵ� �ٸ������� ��ü ��ȸ�� �ƴ� ���� �α����� ������ �ٸ����� ���¸� ��� //���� �α����� ������ �ٸ���ġ�� ������
//				  �������� ���ǿ� ���� �����ۼ���..�߸𸣰���.. System.out.println("���� : " + vo.getDamaLevel());
//				  System.out.println("�̸� : " + vo.getDamaName()); System.out.println("���� : " +
//				  vo.getDamaType()); System.out.println("������ : " + vo.getDamaEnergy());
//				  System.out.println("����ġ : " + vo.getDamaExp()); System.out.println();
//				  
//				  //�ٸ����� �ൿ���� while(true) { System.out.println("1.��Ա�\n2.���ڱ�\n3.��ϱ�\n4.�����ϱ�");
//				  int act_menu = sc.nextInt();
//				  
//				  if(act_menu==1) { System.out.println("���� �Դ´�!(������+10)"); //(������+10)�� ��� ����,
//				  ���� ������ �������� �󸶳� ������, //�������� ��ŭ ȸ���ϴ���, �ٸ� ��ҳ� ���°� ��� ��ŭ �ٲ������ ���ؾ��ҵ�.
//				  
//				  //�屸 �����ؼ��� ���, �屸�� �ؼ��ϱ� ���� �ൿ�� ��� �ٸ���� ���ϴ� �屸�̸� //�������� �����ϰ�, �屸�� �ؼ����� �ʰ� �ٸ�
//				  �ൿ�� �ϸ� �������� �����ϰԲ� �ϴ� ���ǵ� ����
//				  
//				  }else if(act_menu==2) { System.out.println("���� �ܴ�.."); }else if(act_menu==3)
//				  { System.out.println("��� �Ѵ�!"); }else if(act_menu==4) {
//				  System.out.println("���θ� �Ѵ�!"); }else {
//				  System.out.println("�߸��Է��߽��ϴ�. �ٽ� �Է����ּ���."); } }
//				  
//				  //�ٸ����� ��ŷ }else if(dama_menu==3) { System.out.println("�ٸ���ġ ��ŷ");
//				  
//				  //DB�� ��ϵ� �ٸ������� ��ü ��ȸ�� �ƴ� ���� �α����� ������ �ٸ����� ���¸� ��� //���� �α����� ������ �ٸ������� ������
//				  �������� ���ǿ� ���� �����ۼ���..�߸𸣰���..
//				  System.out.println("\n===========================");
//				  
//				  dama.selectDama();
//				  
//				  ArrayList<DamaVO> al = dama.selectDama();
//				  
//				  System.out.println();
//				  
//				  for(DamaVO vo : al) { System.out.println("1�� : " + vo.);
//				  System.out.println("2�� : " + vo.); System.out.println("4�� : " + vo.);
//				  System.out.println("5�� : " + vo.); }
//				  System.out.println("==========================="); } }

			// User ȸ������
//			} else if (game_menu == 2) {
//				mem.insertMember();
//				boolean check2 = true;
//
//				// User ȸ������ ����
//				if (check2 == true) {
//					System.out.println("ȸ������ ����!!");
//
//					// User ȸ������ ����
//				} else {
//					System.out.println("ȸ������ ����..");
//				}
//
//				// ���� ����
//			} else if (game_menu == 3)
//
//			{
//				System.out.println("���� ����");
//				break;
//			} else {
//				System.out.println("�߸��Է��߽��ϴ�. �ٽ� �Է����ּ���.");
//			}

		}
	}
}
