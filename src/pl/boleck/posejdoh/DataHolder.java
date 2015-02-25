package pl.boleck.posejdoh;

import java.util.Map;

public class DataHolder {
	static Map<String, Object> rodzaje;
	static Map<String, Object> dodatki;
	static String RODZAJ;
	static String PAKIET;
	public static void setPakiet(String pAKIET) {
		PAKIET = pAKIET;
	}
	public static void setRodzaj(String rODZAJ) {
		RODZAJ = rODZAJ;
	}
	public static String getPAKIET() {
		return PAKIET;
	}
	public static String getRODZAJ() {
		return RODZAJ;
	}
	public static void setRodzaje(Map<String, Object> rodzaje) {
		DataHolder.rodzaje = rodzaje;
		
	}
	public static void setDodatki(Map<String, Object> dodatki) {
		DataHolder.dodatki = dodatki;
		
	}
}