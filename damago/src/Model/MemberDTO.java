package Model;

public class MemberDTO {
	
	// species => type 으로 변경
	// 다마고치 별명 => nick 추가 
	private String id;
	private String pw;
	private String type;
	private String nick;
	
	public MemberDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", type=" + type + ", nick=" + nick + "]";
	}

	
	

	

	

}

