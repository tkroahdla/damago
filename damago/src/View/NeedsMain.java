package View;

import java.util.ArrayList;
import java.util.Random;

import Model.DamaDAO;

public class NeedsMain extends DamaDAO{
	
	ArrayList<DamaDAO> needList = new ArrayList<DamaDAO>();
	Random r = new Random();
	
	// �޼ҵ� : �屸 �߰�, �����̱�
	public void addNeed(DamaDAO need) {
		needList.add(need);
	}
	
	public DamaDAO getNeed() {
		int ran = r.nextInt(needList.size());
		return needList.get(ran);
	}
}
