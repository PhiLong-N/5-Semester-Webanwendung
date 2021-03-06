package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import org.apache.tomcat.jni.Global;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class AllBestellungBean {
	
	Connection dbConn;
	DecimalFormat z = new DecimalFormat("#0.00");		//zwei nachkommastellen
	
	public void bestellung(int kundenNr, String name, String adresse, String stadt, int plz) throws SQLException {
		int bestellnr=1; //falls noch keine Bestellung im System get?tigt worden ist
		String sql="select bestellnr from allbestellung order by bestellnr desc";
		ResultSet dbRes3 = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		try { //falls noch keine Bestellung im System get?tigt worden ist bricht try ab
			dbRes3.next();
			int letzteBestellnr = dbRes3.getInt("bestellnr");
			bestellnr= letzteBestellnr+1;
		}catch (Exception e) {
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String warenkorb = "select position, artikelnr,menge from warenkorbkunde where kundennr = "+kundenNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(warenkorb).executeQuery();
		while(dbRes.next()) {
			int position = dbRes.getInt("position");
			int artikelNr = dbRes.getInt("artikelnr");
			int menge = dbRes.getInt("menge");
			
			sql="Insert into allbestellung(bestellnr,kundennr,artikelnr,menge,datum,name,adresse,stadt,plz) values(?,?,?,?,?,?,?,?,?)";
			this.dbConn = new PostgreSQLAccess().getConnection();
			PreparedStatement prep = this.dbConn.prepareStatement(sql);
			prep.setInt(1, bestellnr);
			prep.setInt(2,kundenNr);
			prep.setInt(3,artikelNr);
			prep.setInt(4,menge);
			prep.setTimestamp(5, timestamp);
			prep.setString(6, name);
			prep.setString(7, adresse);
			prep.setString(8, stadt);
			prep.setInt(9, plz);
			prep.executeUpdate();
			System.out.println("Artikel wurde zur Bestellung hinzugef?gt");
			
			sql = "DELETE FROM warenkorbkunde WHERE artikelnr="+artikelNr+"and kundennr="+kundenNr;
			prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.executeUpdate();
			System.out.println("Artikel wurde gel?scht vom warenkorb. /wkkBean"+artikelNr+" kundennr: "+kundenNr);
			
			sql = "select lager from artikel where artikelnr="+artikelNr;
			ResultSet dbRes2 = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
			dbRes2.next();
			int lagervorrat= dbRes2.getInt("lager");
			int lagerneu = lagervorrat - menge;
			sql = "update artikel set lager="+lagerneu+" where artikelnr="+artikelNr;
			prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.executeUpdate();
			System.out.println("Lagerbestand wurde reduziert.");
		}
	}
	
	
	//Alle Nachfolgenden Methoden sind teil von getAllBestellung() welche ganz unten ist. / Bestellhistorie
	//getTime, getSumme und getVersandadresse: Header pro Bestellung
	
	public Timestamp getTime(int bestellnr) throws NoConnectionException, SQLException {
		String sql="select datum from allbestellung where bestellnr="+bestellnr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		Timestamp time = dbRes.getTimestamp("datum");
		return time;
	}
	
	public String getSumme(int kundennr, int bestellnr ) throws NoConnectionException, SQLException {
		double summe=0;
		String sql ="select artikelnr,menge from allbestellung where kundennr="+kundennr+" and bestellnr="+bestellnr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			int artikelnr= dbRes.getInt("artikelnr");
			int menge = dbRes.getInt("menge");
			sql = "select preis from artikel where artikelnr="+artikelnr;
			ResultSet dbRes2 = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
			dbRes2.next();
			double preis = dbRes2.getDouble("preis");
			summe += menge*preis;
		}
		
		String summeString = z.format(summe); 
		
		return summeString;
	}
	
	public String getVersandadresse(int bestellnummer) throws NoConnectionException, SQLException {
		String sql = "select name,adresse,stadt,plz from allbestellung where bestellnr="+bestellnummer;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		String name=dbRes.getString("name");
		String adresse = dbRes.getString("adresse");
		String stadt = dbRes.getString("stadt");
		int plz = dbRes.getInt("plz");
		
		String html=name+"<br>"+adresse+"<br>"+stadt+"<br>"+plz+"<br>";
		return html;
	}
	
	// getBestellung: html-erzeugung von allen Artikeln einer Bestellung
	
	public String getBestellung(int bestellnummer) throws NoConnectionException, SQLException { //
		String html="";
		String sql ="select artikelnr, menge from allbestellung where bestellnr="+bestellnummer;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			int artikelnr=dbRes.getInt("artikelnr");
			int menge = dbRes.getInt("menge");
			String sql2 = "select artikel,preis from artikel where artikelnr="+artikelnr;
			ResultSet dbRes2 = new PostgreSQLAccess().getConnection().prepareStatement(sql2).executeQuery();
			dbRes2.next();
			String artikel=dbRes2.getString("artikel");
			double preis = dbRes2.getDouble("preis");
			html +="<tr> <td rowspan='2'> <a href='http://localhost:8080/5.Semester_Praktikum/jsp/ArtikelSeiteView.jsp?btnArtikel="+artikelnr+"'> <img src='../img/"+artikel.toLowerCase().trim()+".jpg' height='150px' width='150px' /></a></td>"
					+ "<td width='200px'><a href='http://localhost:8080/5.Semester_Praktikum/jsp/ArtikelSeiteView.jsp?btnArtikel="+artikelnr+"'>"+artikel +"</a> "
					+ "</td> <td width='100px'>Menge: "+menge +"</td> "
					+ "<td>Einzelpreis: "+z.format(preis)+"</td> </tr>" ;
			html += "<tr> <td  colspan='3'>";
			html += "<form action=\"./MeineBestellungAppl.jsp\" method=\"get\">\r\n"
					+ "	<select name='bewertung'>\r\n"
					+ "		<option value=1 >1 Stern</option>\r\n"
					+ "		<option value=2 >2 Stern</option>\r\n"
					+ "		<option value=3 >3 Stern</option>\r\n"
					+ "		<option value=4 >4 Stern</option>\r\n"
					+ "		<option value=5 >5 Stern</option> </select>";
			html += "<button type='submit' name='btnBewerten' value="+artikelnr+" onclick='bewertungBestatigen()' />Bewertung Abgeben </button> </form>  </td> </tr>";
		}
		return html;
	}
	
	//Zusammenstellung aller Bausteine f?r die Bestellhistorie
	
	public String getAllBestellung(int kundennr) throws NoConnectionException, SQLException {
		String html="";
		int bestellnrAlt=0;
		String sqlKundennr="select bestellnr from allbestellung where kundennr="+kundennr+" group by bestellnr ORDER by bestellnr DESC";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sqlKundennr).executeQuery();
		while (dbRes.next()) {
			int bestellnr = dbRes.getInt("bestellnr");		
			html+="<table class='outerborder'><tr><td width='200px'><b>"+getTime(bestellnr)+"</b></td>";
			html+="<td colspan='2'><b>	Gesamtbetrag: "+getSumme(kundennr, bestellnr)+" Euro </b></td>";
			html+=" <td><b>Bestellnummer: "+bestellnr+" </td></b></tr>";
			html+="<tr><td><b>"+getVersandadresse(bestellnr)+"</b><br></td></tr>";
			html+= getBestellung(bestellnr);
			html+="</table><br><br>";
		}
		return html;
	}

}
