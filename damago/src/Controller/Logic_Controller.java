package Controller;

import java.util.Scanner;

import Model.DamaDTO;

public class Logic_Controller {
	Scanner sc = new Scanner(System.in);
	//System.out.println("1.����̱�\\n2.������\\n3.����ֱ�\\n4.��ϱ�\\n5.�ڷΰ���");
	
	public DamaDTO sel_need(DamaDTO vo) {
		int input = getNumInput("������ ���ٱ��??");
		if(input == 1) {
			System.out.println("���� �Ծ����.");
			energytype2(vo);
		}
		else if(input ==2) {
			System.out.println("���� ����.");
			energytype2(vo);
		}
		else if(input ==3) {
			System.out.println("��Ҿ��.");
			energytype1(vo);
		}
		else if(input ==4) {
			System.out.println("��� �߾��."); //�̰� �Ų������� �ٲ��ּ���..
			energytype1(vo);
		}
		else {
			System.out.println("�߸��� �Է��̿���.");
		}
		return vo;
	}
	
	
	public DamaDTO energytype1(DamaDTO vo) { //�������� �����ϴ°�� (���, �)
		vo.setEnergy(vo.getEnergy()-10);
		return vo;
	}
	
	public DamaDTO energytype2(DamaDTO vo) {// �������� �����ϴ°�� (��Ա�,���ڱ�)
		vo.setEnergy(vo.getEnergy()+30);
		return vo;
	}
	

//	private String getStrInput(String msg) {
//		System.out.print(msg);
//		return sc.nextLine();
//	}

	private int getNumInput(String msg) {
		System.out.print(msg);
		return sc.nextInt();
	}
	
	
	public void start() {
		//menu(); // �޴� ����ȭ�� ���
		//select(); // �޴� ���� ��ȣ �Է¹ޱ�
		//process(); // �޴� �۾��� ���� ��� ���� // ���� JDBC �۾��̶�� JDBC_Controller ��ü���� ȣ��.
	}

}
