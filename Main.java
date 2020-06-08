import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static List<ABC> beolvasABC() {
		List<ABC> lista = new ArrayList<ABC>();
		try {
			List<String> sorok = Files.readAllLines(Paths.get("morzeabc.txt"));
			for (String sor : sorok.subList(1, sorok.size())) {
				String[] split = sor.split("\t");
				ABC o = new ABC(split[0], split[1]);
				lista.add(o);
			}
		} catch (Exception e) {
			System.out.println("Hiba a fájl beolvasásakor. ABC" + e);
		}
		return lista;
	}

	public static String betuzes(String betu, List<ABC> lista) {
		for (ABC abc : lista) {
			if (abc.getBetu().toUpperCase().equals(betu.toUpperCase())) {
				return "\tA " + betu + " karakter morze kódja: " + abc.getMorzekod();
			}
		}

		return "\tA Nem található a kódtárban ilyen karakter! ";
	}

	public static List<Szoveg> beolvasSzoveg() {
		List<Szoveg> lista = new ArrayList<Szoveg>();
		try {
			List<String> sorok = Files.readAllLines(Paths.get("morze.txt"));
			for (String sor : sorok.subList(0, sorok.size())) {
				String[] split = sor.split(";");
				Szoveg sz = new Szoveg(split[0], split[1]);
				lista.add(sz);
			}
		} catch (Exception e) {
			System.out.println("Hibab a beolvasásnál: morze.txt" + e);
		}
		return lista;
	}

	public static String Morze2Szöveg(String kodsor, List<ABC> lista) {
		String dekodolt = "";

		String[] szo = kodsor.replace("       ", ";").split(";");
		for (int j = 0; j < szo.length; j++) {
			String[] betu = szo[j].replace("   ", ";").split(";");
			for (String s : betu) {
				for (ABC x : lista) {
					if (x.getMorzekod().equals(s)) {
						dekodolt += x.getBetu();
					}
				}
			}
			dekodolt += " ";
		}

		return dekodolt;
	}

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<ABC> a = beolvasABC();

		System.out.println("3. feladat: A morze abc " + a.size() + " db karakter kódját tartalmazza. ");

		// 4. feladat.
		System.out.print("4. feladat: Kérek egy karaktert: ");
		String beker = sc.nextLine();
		System.out.println(betuzes(beker, a));
		// 5. feladat:
		List<Szoveg> b = beolvasSzoveg();
		String teszt = ".-   .-.   ..   ...   --..   -   ---   -   .   .-..   ..-..   ...   --..   ;.-       -...   .-   .-.   .--.-   -   ...   .--.-   --.       .-   --..   --..--       .-   --   ..   -.-   ---   .-.       .   --.   -.--       .-..   ..-..   .-..   .   -.-       -.-   ..-..   -       -   .   ...   -   -...   .   -.       .-..   .-   -.-   ..   -.-   .-.-.-"
				+ "";
		// 6. feladat:
		System.out.println(Morze2Szöveg(teszt, a));

		// 7. feladat
		Szoveg elsosor = b.get(0);
		String elsoszerzo = elsosor.getSzerzo();
		System.out.println("7. feladat: Az első idézet szerzőjének a neve: " + Morze2Szöveg(elsoszerzo, a));

		// 8. feladat:
		int maxHossz = 0;
		String aktSzoveg = "";
		int aktHossz = 0;
		Szoveg maxHosszSz = null;
		for (Szoveg szoveg : b) {
			aktSzoveg = Morze2Szöveg(szoveg.getIdezet(), a);
			aktHossz = aktSzoveg.length();
			if (aktHossz > maxHossz) {
				maxHossz = aktHossz;
				maxHosszSz = szoveg;
			}
		}
		System.out.println("8. feladat: A leghosszabb idézet szerője és az idézet: "
				+ Morze2Szöveg(maxHosszSz.getSzerzo(), a) + ": " + Morze2Szöveg(maxHosszSz.getIdezet(), a));
		
		//9.feladat: 
		System.out.println("9. feladat: Arisztotelész idézetei:");
		for (Szoveg szoveg : b) {
			if(szoveg.getSzerzo().equals(b.get(0).getSzerzo())) {
				System.out.println("\t- "+Morze2Szöveg(szoveg.getIdezet(),a));
			}
		}
		//10. feladat
		String fajlba="";
		for (Szoveg szoveg : b) {
			fajlba+=Morze2Szöveg(szoveg.getSzerzo(),a).substring(0,Morze2Szöveg(szoveg.getSzerzo(),a).length()-1)+":"+Morze2Szöveg(szoveg.getIdezet(), a)+"\n";
		}
		System.out.println(fajlba);
		Files.write(Paths.get("forditas.txt"), fajlba.getBytes());
		System.out.println("10. feladat: A fájlt létrehoztam. ");
	}
}
