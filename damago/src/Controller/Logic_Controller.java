package Controller;

import java.util.Random;
import java.util.Scanner;

import Model.DamaDAO;
import Model.DamaDTO;

public class Logic_Controller {
	Scanner sc = new Scanner(System.in);
	String needs = null;
	DamaDAO dama = new DamaDAO();
	// System.out.println("1.����̱�\\n2.������\\n3.����ֱ�\\n4.��ϱ�\\n5.�ڷΰ���");

	public DamaDTO expPlus(DamaDTO vo) { // ����ġ ����
		vo.setExp(vo.getExp() + 10);
		return vo;
	}

	public DamaDTO cleaning(DamaDTO vo) { // û�ᵵ ����
		vo.setCleaning(vo.getCleaning() - 10);
		return vo;
	}
	
	public int DamaNeeds() { // �屸
		Random r = new Random();
		// String[] needs = {"meal","sleep","exercies","cleaning","play"};
		int a = r.nextInt(6);
		// String need = needs[r.nextInt(5)];
		if (a == 0)
			System.out.println("���� ���� : �����!!\n");
		else if (a == 1)
			System.out.println("���� ���� : �ǰ���..\n");
		else if (a == 2)
			System.out.println("���� ���� : ���! ���� �����帥��!\n");
		else if (a == 3)
			System.out.println("���� ���� : �ɽ���~ \n");
		else if (a == 4)
			System.out.println("���� ���� : ���� ������!!\n");

		return a;
	}

	public DamaDTO sel_need(DamaDTO vo, int needs) {
		// int need = dama.DamaNeeds();
		System.out.println("������Ʈ�� : "+vo.getState());
		int input = getNumInput("������ ���ٱ��??");
		
		if(vo.getState()==1) {
			if (input == 1) {
				System.out.println("����̱�(������+30)");
				energyplus(vo);
				expPlus(vo);
				cleaning(vo);
			} else if (input == 2) {
				System.out.println("������(������+30)");
				energyplus(vo);
				cleaning(vo);
			} else if (input == 3) {
				System.out.println("����ֱ�(������-10)");
				energyminus(vo);
				expPlus(vo);
				cleaning(vo);
			} else if (input == 4) {
				System.out.println("���Ű��(������-10)");
				energyminus(vo);
				expPlus(vo);
				cleaning(vo);
			} else if (input == 5) {
				System.out.println("�İ��ֱ�(������+30)");
				energyplus(vo);
			//	cleaning(vo);
			} else {
				System.out.println("�߸��� �Է��̿���.");
			}
			return vo;
			}
		
		if(needs+1 == input && vo.getState()==0) {
			System.out.println(vo.getNick()+"(��)�� �����մϴ�...");
			if (input == 1) {
				System.out.println("����̱�(������+30) ����ġ ����.");
				energyplus(vo);
				//expPlus(vo);
				cleaning(vo);
			} else if (input == 2) {
				System.out.println("������(������+30) ����ġ ����.");
				energyplus(vo);
				cleaning(vo);
			} else if (input == 3) {
				System.out.println("����ֱ�(������-10) ����ġ ����.");
				energyminus(vo);
				//expPlus(vo);
				cleaning(vo);
			} else if (input == 4) {
				System.out.println("���Ű��(������-10) ����ġ ����.");
				energyminus(vo);
				//expPlus(vo);
				cleaning(vo);
			} else if (input == 5) {
				System.out.println("�İ��ֱ�(������+30) ����ġ ����.");
				energyplus(vo);
			//	cleaning(vo);
			} else {
				System.out.println("�߸��� �Է��̿���.");
			}
			vo.setState(1);
			System.out.println(vo.getCleaning());
		}
		else if (needs + 1 != input) {
			System.out.println(vo.getNick() + "(��)�� ������.");
			energyminus(vo); // ������ ����
			return vo;
		}
		
		return vo;
	}

	public DamaDTO energyminus(DamaDTO vo) { // �������� �����ϴ°�� (���, �)
		vo.setEnergy(vo.getEnergy() - 10);
		return vo;
	}

	public DamaDTO energyplus(DamaDTO vo) {// �������� �����ϴ°�� (��Ա�,���ڱ�)
		vo.setEnergy(vo.getEnergy() + 30);
		return vo;
	}

	public DamaDTO exptype1(DamaDTO vo) {
		vo.setExp(vo.getExp() + 10);
		return vo;
		
	}
	public DamaDTO exptype2(DamaDTO vo) {
		vo.setExp(vo.getExp() + 30);
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
