package pl.boleck.posejdoh;

import java.util.ArrayList;
import java.util.Map;

public class DataHolder {
	static Map<String, Object> rodzaje;
	static String[] dodatki;
	static String RODZAJ;
	static String PAKIET;
	static ArrayList<String> dodatkisend = new ArrayList<String>();
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
	public static void setDodatki(String[] rodz) {
		DataHolder.dodatki = rodz;
		
	}
	public static void wyczysc(){
		setPakiet(null);
		setRodzaj(null);
	}
	public static void dodajDodatekDoWyslania(String some){
		dodatkisend.add(some);
	}
	
	public static void usunDodatekDoWyslania(String some){
		dodatkisend.remove(some);
	}
}