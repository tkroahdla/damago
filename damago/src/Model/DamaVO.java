package Model;



public class DamaVO {
	//�ʵ� :  ����,����,����ġ,����,������,������
	
	private String nick; //����
	private String species; //����
	private int exp;//����ġ
	private int level;//����
	private int energy;//������
	private String id;//���̵�
	private String date;//������ ���ӳ�¥
	
	

	
	public	DamaVO(String nick, String species, int exp, int level,int energy,String id, String date) {
		super();// �θ�Ŭ������ ������ ȣ�� (�⺻������)
		this.nick = nick;
		this.species = species;
		this.exp = exp;
		this.level = level;
		this.energy = energy;
		this.id = id;
		this.date = date;
		
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getNick() {
		return nick;
	}

	public String getSpecies() {
		return species;
	}

	public int getExp() {
		return exp;
	}

	public int getLevel() {
		return level;
	}

	public int getEnergy() {
		return energy;
	};
	
	
}
