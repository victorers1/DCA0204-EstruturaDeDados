package lab_cripto;

public class Letra {
	String caractere;
	String md5code;
	
	Letra(String caractere){
		this.caractere = caractere;
		this.md5code = SecurityProvider.md5(caractere);
	}

	public String getCaractere() {
		return caractere;
	}

	public void setCaractere(String caractere) {
		this.caractere = caractere;
	}

	public String getMd5code() {
		return md5code;
	}

	public void setMd5code(String md5code) {
		this.md5code = md5code;
	}
	
	
}
