package beans;

import java.awt.List;

public class ListArtikel {
	public int position;
	public int artikelNr;
	public int menge;
	public double einzelpreis;
	public double gesamtpreis;
	
	//für WarenkorbKundeBean
	
	public double getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}

	public ListArtikel(int position,int artikelnr, int menge, double einzelpreis, double gesamtpreis) {
		this.position=position;
		this.artikelNr=artikelnr;
		this.menge=menge;
		this.einzelpreis=einzelpreis;
		this.gesamtpreis=gesamtpreis;
	}

	public int getArtikelNr() {
		return artikelNr;
	}

	public void setArtikelNr(int artikelNr) {
		this.artikelNr = artikelNr;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public double getEinzelpreis() {
		return einzelpreis;
	}

	public void setEinzelpreis(double einzelpreis) {
		this.einzelpreis = einzelpreis;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	
	
	
}
