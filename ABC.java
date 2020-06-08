
public class ABC {
	private String betu;
	private String morzekod;

	public ABC(String betu, String morzekod) {
		super();
		this.betu = betu;
		this.morzekod = morzekod;
	}

	public String getBetu() {
		return betu;
	}

	public void setBetu(String betu) {
		this.betu = betu;
	}

	public String getMorzekod() {
		return morzekod;
	}

	public void setMorzekod(String morzekod) {
		this.morzekod = morzekod;
	}

	@Override
	public String toString() {
		return "ABC [betu=" + betu + ", morzekod=" + morzekod + "]";
	}

}
