package Model;

public class DamaVO {
	//필드 :  별명,종류,경험치,레벨,포만감,에너지
	
	private String nick; //별명
	private String type; //종류
	private int exp;//경험치
	private int level;//레벨
	private int energy;//에너지
	private String id;//아이디
	private String date;//마지막 접속날짜
	
	public	DamaVO(String nick, String species, int exp, int level, int energy, String id, String date) {
		super();
		this.nick = nick;
		this.type = species;
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
		return type;
	}

	public int getExp() {
		return exp;
	}

	public int getLevel() {
		return level;
	}

	public int getEnergy() {
		return energy;
	}

	@Override
	public String toString() {
		return "DamaVO [nick=" + nick + ", type=" + type + ", exp=" + exp + ", level=" + level + ", energy=" + energy
				+ ", id=" + id + ", date=" + date + "]";
	};
	
	
}
