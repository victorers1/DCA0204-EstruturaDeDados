package sistema.bancario;

/**
 *
 * @author Victor Emanuel
 */
public class Letra {
    String caractere;
    String md5Code;
    
    Letra(String caractere){
        this.caractere = caractere;
        this.md5Code = SecurityProvider.md5(caractere);
    }

    public String getCaractere() {
        return caractere;
    }
    public void setCaractere(String caractere) {
        this.caractere = caractere;
    }
    public String getMd5Code() {
        return md5Code;
    }
    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }
}
