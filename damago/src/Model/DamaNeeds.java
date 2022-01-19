package Model;

public class DamaNeeds {
	public void need() {
		System.out.println("욕구");
	}
	
	public class meal extends DamaNeeds{
		public void need() {
			System.out.println("배고프다");
		}
	}
	
	public class sleep extends DamaNeeds{
		public void need() {
			System.out.println("자고싶다");
		}
	}
	
	public class exercise extends DamaNeeds{
		public void need() {
			System.out.println("놀고싶다");
		}
	}
	
	public class play extends DamaNeeds{
		public void need() {
			System.out.println("운동하고싶다");
		}
	}
	
	public class cleaning extends DamaNeeds{
		public void need() {
			System.out.println("주변이 더럽다");
		}
	}
	
}




	