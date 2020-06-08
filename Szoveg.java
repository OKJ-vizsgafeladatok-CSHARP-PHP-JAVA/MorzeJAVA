
public class Szoveg {
	private String szerzo;
	private String idezet;
	public Szoveg(String szerzo, String idezet) {
		super();
		this.szerzo = szerzo;
		this.idezet = idezet;
	}
	public String getSzerzo() {
		return szerzo;
	}
	public void setSzerzo(String szerzo) {
		this.szerzo = szerzo;
	}
	public String getIdezet() {
		return idezet;
	}
	public void setIdezet(String idezet) {
		this.idezet = idezet;
	}
	@Override
	public String toString() {
		return "Szoveg [szerzo=" + szerzo + ", idezet=" + idezet + "]";
	}
	
	
}
