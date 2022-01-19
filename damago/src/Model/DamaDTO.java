package Model;

import java.sql.Date;

public class DamaDTO {
	// �ʵ� : ����,����,����ġ,����,������,������

	private String nick; // ����
	private String type; // ����
	private int exp;// ����ġ
	private int level;// ����
	private int energy;// ������
	private String id;// ���̵�
	private Date date;// ������ ���ӳ�¥
	private int cleaning=100;
	private int state;
	private boolean stop;

	public DamaDTO(String nick, String type, int exp, int level, int energy, String id, Date date) {
		super();// �θ�Ŭ������ ������ ȣ�� (�⺻������)
		this.nick = nick;
		this.type = type;
		this.exp = exp;
		this.level = level;
		this.energy = energy;
		this.id = id;
		this.date = date;
		this.state= 0;
		this.stop= false;
		
	}
	
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	public int getCleaning() {
		return cleaning;
	}

	public void setCleaning(int cleaning) {
		this.cleaning = cleaning;
	}

	public void setEnergy(int energy) {
		this.energy= energy;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
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
		return "���� : " + level + "\n �̸� : " + nick + "\n(id : " + id + ")" + "\nŸ�� : " + type + "\n����ġ : " + exp
	            + "\n������ : " + energy + "\n������ : " + date + "\n----------------------------";
	}

	
	
}
