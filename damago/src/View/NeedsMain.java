package View;

import java.util.ArrayList;
import java.util.Random;

import Model.DamaNeeds;

public class NeedsMain extends DamaNeeds{
	
	ArrayList<DamaNeeds> needList = new ArrayList<DamaNeeds>();
	Random r = new Random();
	
	// �޼ҵ� : �屸 �߰�, �����̱�
	public void addNeed(DamaNeeds need) {
		needList.add(need);
	}
	
	public DamaNeeds getNeed() {
		int ran = r.nextInt(needList.size());
		return needList.get(ran);
	}
}
