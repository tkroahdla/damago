package Model;

import java.sql.Date;

public class DamaVO {
	//�ʵ� :  ����,����,����ġ,����,������,������
	
	private String nick; //����
	private String type; //����
	private int exp;//����ġ
	private int level;//����
	private int energy;//������
	private String id;//���̵�
	private Date date;//������ ���ӳ�¥
	
	
	public	DamaVO(String nick, String type, int exp, int level,int energy,String id, Date date) {
		super();// �θ�Ŭ������ ������ ȣ�� (�⺻������)
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
		return "DamaVO [nick=" + nick + ", type=" + type + ", exp=" + exp + ", level=" + level + ", energy=" + energy
				+ ", id=" + id + ", date=" + date + "]";
	};
	
	
}
