package asw.ciaomondo;

public class CiaoMondo {

    public String getSaluto() {
        return "Ciao, mondo!";
    }

	public static void main(String[] args) {
        System.out.println( new CiaoMondo().getSaluto() );
	}

}
