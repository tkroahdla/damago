package Model;

public class DamaNeeds {
	public void need() {
		System.out.println("�屸");
	}
	
	public class meal extends DamaNeeds{
		public void need() {
			System.out.println("�������");
		}
	}
	
	public class sleep extends DamaNeeds{
		public void need() {
			System.out.println("�ڰ�ʹ�");
		}
	}
	
	public class exercise extends DamaNeeds{
		public void need() {
			System.out.println("���ʹ�");
		}
	}
	
	public class play extends DamaNeeds{
		public void need() {
			System.out.println("��ϰ�ʹ�");
		}
	}
	
	public class cleaning extends DamaNeeds{
		public void need() {
			System.out.println("�ֺ��� ������");
		}
	}
	
}




	