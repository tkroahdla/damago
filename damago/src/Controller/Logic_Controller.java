package Controller;

import java.util.Scanner;

import Model.DamaDAO;
import Model.DamaDTO;

public class Logic_Controller {
	Scanner sc = new Scanner(System.in);
	String needs = null;
	DamaDAO dama = new DamaDAO();
	// System.out.println("1.����̱�\\n2.������\\n3.����ֱ�\\n4.��ϱ�\\n5.�ڷΰ���");

	public DamaDTO sel_need(DamaDTO vo, int needs) {
		// int need = dama.DamaNeeds();
		int input = getNumInput("������ ���ٱ��??");
		if (needs == 9) {
			if (input == 1) {
				System.out.println("����̱�(������+30)");
				energytype2(vo);
			} else if (input == 2) {
				System.out.println("������(������+30)");
				energytype2(vo);
			} else if (input == 3) {
				System.out.println("����ֱ�(������-10)");
				energytype1(vo);
			} else if (input == 4) {
				System.out.println("���Ű��(������-10)");
				energytype1(vo);
			} else if (input == 5) {
				System.out.println("�İ��ֱ�(������+30)");
				energytype2(vo);
			} else {
				System.out.println("�߸��� �Է��̿���.");
			}
		} else if (needs + 1 != input) {
			System.out.println(vo.getNick() + "(��)�� ������.");
			energytype1(vo); // ������ ����
		}
		
		return vo;
	}

	public DamaDTO energytype1(DamaDTO vo) { // �������� �����ϴ°�� (���, �)
		vo.setEnergy(vo.getEnergy() - 10);
		vo.setExp(vo.getExp() + 30);
		return vo;
	}

	public DamaDTO energytype2(DamaDTO vo) {// �������� �����ϴ°�� (��Ա�,���ڱ�)
		vo.setEnergy(vo.getEnergy() + 30);
		vo.setExp(vo.getExp() + 10);
		return vo;
	}

	private int getNumInput(String msg) {
		System.out.print(msg);
		return sc.nextInt();
	}

	public void start() {
		// menu(); // �޴� ����ȭ�� ���
		// select(); // �޴� ���� ��ȣ �Է¹ޱ�
		// process(); // �޴� �۾��� ���� ��� ���� // ���� JDBC �۾��̶�� JDBC_Controller ��ü���� ȣ��.
	}

}
