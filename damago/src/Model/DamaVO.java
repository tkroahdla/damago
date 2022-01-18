package Model;



public class DamaVO {
	//필드 :  별명,종류,경험치,레벨,포만감,에너지
	
	private String nick; //별명
	private String species; //종류
	private int exp;//경험치
	private int level;//레벨
	private int energy;//에너지
	private String id;//아이디
	private String date;//마지막 접속날짜
	
	

	
	public	DamaVO(String nick, String species, int exp, int level,int energy,String id, String date) {
		super();// 부모클래스의 생성자 호출 (기본생성자)
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
