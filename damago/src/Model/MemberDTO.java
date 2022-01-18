package Model;

public class MemberDTO {
	private String id;

	private String pw;

	private String species;

	
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

	

	public String getName() {

		return species;

	}



	public void setSpiceis(String species) {

		this.species = species;

	}
	
	public String getSpecies() {
		return species;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", species=" + species + "]";
	}
	

	

	

}

