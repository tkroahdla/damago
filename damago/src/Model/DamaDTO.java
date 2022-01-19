package Model;

import java.sql.Date;

public class DamaDTO {
	// 필드 : 별명,종류,경험치,레벨,포만감,에너지

	private String nick; // 별명
	private String type; // 종류
	private int exp;// 경험치
	private int level;// 레벨
	private int energy;// 에너지
	private String id;// 아이디
	private Date date;// 마지막 접속날짜

	public DamaDTO(String nick, String type, int exp, int level, int energy, String id, Date date) {
		super();// 부모클래스의 생성자 호출 (기본생성자)
		this.nick = nick;
		this.type = type;
		this.exp = exp;
		this.level = level;
		this.energy = energy;
		this.id = id;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getNick() {
		return nick;
	}

	public String gettype() {
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
		return "레벨 : " + level + "\t 이름 : " + nick + "(id :"+id +")"+"\t타입 : " + type + "\t경험치 : " + exp
	            + "\t에너지 : " + energy + "\t생성일 : " + date + "\n----------------------------------------------------------------------------------------------";
	}

	
	
}
