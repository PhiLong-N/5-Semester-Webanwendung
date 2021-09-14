package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.directory.SearchResult;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class ArtikelBean {
	int artikelnr;
	String artikel;
	String kategorie;
	double preis;
	int clicks;
	String beschreibung;
	String herstellerlink;
	
	String searchResult="";
	
	Connection dbConn;
	
	public String searchBarKategorie() throws NoConnectionException, SQLException {
		String sql="select kategorie from artikel group by kategorie";
		String html="<select name='kategorieDropdown'>";
		html += "<option value='Alle'>"+"Alle"+"</option>";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while (dbRes.next()) {
			String kategorie = dbRes.getString("kategorie").trim();
			String kategorieLower = kategorie.toLowerCase();
			html += "<option value='"+kategorieLower+"'>"+kategorie+"</option>";
		}
		html += "</select>";
		return html;
	}
	
	public void searchAll(String eingabe) throws NoConnectionException, SQLException {
		String sql = "select artikelnr,artikel,preis,lager from artikel where artikelLower like '%"+eingabe.toLowerCase() +"%'";
		search(sql);
	}
	
	public void searchKategorie(String eingabe,String katLower) throws NoConnectionException, SQLException{
		String sql = "select artikelnr,artikel,preis,kategorie,lager from artikel where artikelLower like '%"+eingabe.toLowerCase() +"%' and kategorieLower like '%"+katLower+"%'";
		search(sql);
	}
	
	public void search(String sql) throws NoConnectionException, SQLException {
		String html="<table>";
		int a=0;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			if (a%2==0)html+="<tr>";
			int artikelnr = dbRes.getInt("artikelnr");
			String artikel = dbRes.getString("artikel").trim();
			double preis = dbRes.getDouble("preis");
			html += "<td><button type='submit' name='btnArtikel' value='"+artikelnr+"'>"+"<img src='../img/caipi.jpg' height='100px' />"+artikel+"</button>";
			int lager = dbRes.getInt("lager");
			if(lager>=10)html += "Auf Lager";
			else if(lager>=1)html += "Nur noch wenige Verfügbar";
			else html += "Ausverkauft";
			html +="</td>";
			if (a%2==1)html+="</tr>";
			a++;
		}
		html += "</table>";
		setSearchResult(html);
	}
	
	public void setSearchResult(String html) {
		searchResult=html;
	}
	
	public String getSearchResult() {
		return searchResult;
	}
	
	public void getAllInfo(int artikelNr) throws NoConnectionException, SQLException {
		String sql = "select artikelnr,artikel,kategorie, preis,beschreibung,clicks from artikel where artikelnr ="+artikelNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		this.artikelnr=dbRes.getInt("artikelnr");
		this.artikel=dbRes.getString("artikel");
		this.kategorie=dbRes.getString("kategorie");
		this.preis=dbRes.getDouble("preis");
		this.beschreibung=dbRes.getString("beschreibung");
		this.clicks=dbRes.getInt("clicks");
	}
	
	public int maxClicksArtikel() throws NoConnectionException, SQLException {
		String sql="select max(clicks) as clicks from artikel";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		int clicks =dbRes.getInt("clicks");
		sql = "select artikelnr from artikel where clicks ="+clicks;
		dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		int artikelnr = dbRes.getInt("artikelnr");
		return artikelnr;
	}
	
	public void increaseClick(int artikelNr) throws NoConnectionException, SQLException {
		String sql = "select clicks from artikel where artikelnr ="+artikelNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		int tempClicks = dbRes.getInt("clicks")+1;
		
		sql = "update artikel set clicks="+tempClicks+ " where artikelnr="+artikelNr;
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
	}

	
	
	
	public int getArtikelnr() {
		return artikelnr;
	}

	public void setArtikelnr(int artikelnr) {
		this.artikelnr = artikelnr;
	}

	public String getArtikel() {
		return artikel;
	}

	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	
	
	
	
	
	
}
