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
			vo.getEnergy();
			//�̷��� ���Ͱ� �ʿ��Ѱǰ�.
		}
		else if(input ==2)System.out.println("���� ����.");
		else if(input ==3)System.out.println("��Ҿ��.");
		else if(input ==4)System.out.println("��� �߾��."); //�̰� �Ų������� �ٲ��ּ���..
		else {
			System.out.println("�߸��� �Է��̿���.");
		}
		return vo;
		
	}
	public DamaDTO energytype(DamaDTO vo) {
		
		return vo;
	}
	
	
	private String getStrInput(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

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
