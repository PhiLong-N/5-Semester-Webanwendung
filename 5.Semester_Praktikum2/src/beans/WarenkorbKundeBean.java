package beans;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class WarenkorbKundeBean {
	int kundenNr;
	int artikelNr;
	int menge;
	double einzelpreis;
	
	Connection dbConn;
	DecimalFormat z = new DecimalFormat("#0.00");		//zwei nachkommastellen
	
	public void insertWarenkorb(int kundenNr,int artikelNr,int menge,double preis) throws SQLException {
		this.kundenNr=kundenNr;
		
		String sql="Select menge from warenkorbkunde where kundennr="+kundenNr+"and artikelnr="+artikelNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		if(dbRes.next()) {	//falls artikel bereits im Warenkorb wird dann dazu addiert (somit ist es möglich mehr als 30 Stück eines Artikels zu bestellen)
			int neueMenge= menge+dbRes.getInt("menge");
			sql = "update warenkorbkunde set menge="+neueMenge+"where kundennr="+kundenNr+"and artikelnr="+artikelNr;
			PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.executeUpdate();
			System.out.println("Artikel zum Warenkorb hinzugefügt");
		}else {
		sql = "INSERT INTO warenkorbkunde (kundenNr,artikelNr,menge, einzelpreis, gesamtpreis) VALUES(?,?,?,?,?)";
		this.dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.setInt(1,kundenNr);
		prep.setInt(2,artikelNr);
		prep.setInt(3,menge);
		prep.setDouble(4, preis);
		prep.setDouble(5, preis*menge);
		prep.executeUpdate();
		System.out.println("Neuer Artikel zum Warenkorb hinzugefügt");
		}
	}
	
	
	public String getWarenKorbKunde(int kundenNr) throws SQLException {
		double gesamtbetrag=0;
		this.kundenNr=kundenNr;
		String html="<table>";
		String sql ="select position, artikelnr,menge, einzelpreis, gesamtpreis from warenkorbkunde where kundennr= "+kundenNr+" order by position asc";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			int position = dbRes.getInt("position");
			int artikelNr = dbRes.getInt("artikelnr");
			int menge = dbRes.getInt("menge");
			double einzelpreis = dbRes.getDouble("einzelpreis");
			double gesamtpreis = dbRes.getDouble("gesamtpreis");
			
			String sqlArtikel = "select artikel,lager from artikel where artikelnr="+artikelNr;
			ResultSet dbResArtikel = new PostgreSQLAccess().getConnection().prepareStatement(sqlArtikel).executeQuery();
			dbResArtikel.next();
			String artikel = dbResArtikel.getString("artikel");
			int lager = dbResArtikel.getInt("lager");
			
			html+="<form action='./WarenkorbAppl.jsp' method='get'>";
			html+="<tr><td rowspan='4'><a href='http://localhost:8080/5.Semester_Praktikum/jsp/ArtikelSeiteView.jsp?btnArtikel="+artikelNr+"'> <img src='../img/"+artikel.toLowerCase().trim()+".jpg' height='150px' width='150px' /></a></td>";
			html+="<tr><td>"+artikel+"</td>";
			html+="<td>Menge: ";
			html+="<select name='anzahlArtikelAndern'>";
			
			//Im Warenkorb kann die Menge auf max=30 erhöht werden. Falls bereits mehr im Warenkorb ist werden so viele <option> erstellt wie nötig aber nicht mehr
			//falls weniger als 30 bestellt werden gibt es nur so viele <option>'s erstellt wie es die im Lager gibt(max 30) oder die bestellte menge auch wenn diese die Lageranzahl übersteigt
			if (menge>30){
				for (int i=1;i<=menge+1;i++){
					if (i==menge) html += "<option value="+i+" selected>"+i+"</option>";
					else html += "<option value="+i+">"+i+"</option>";	
				}
			}else{
				int max = 30;
				if (lager<30)max=lager;
				if (menge>max)max=menge;
				for (int i=1;i<=max;i++){
					if (i==menge) html += "<option value="+i+" selected>"+i+"</option>";
					else html += "<option value="+i+">"+i+"</option>";	
				}
			}
			html+= "</select>";
			html+="<button type='submit' name='btnArtikelAndern' value="+artikelNr+">Menge ändern</button> </td>"; 
			html+="<td><button type='submit' name='btnArtikelLoschen' value="+artikelNr+">Artikel Löschen</button> </td>";
			if(menge>lager)html+="<td>Reduzieren Sie bitte ihre ausgewählte Menge.</td>";
			else html+="<td></td>";
			html+="</tr>";
			html+="<tr> <td></td> <td>Einzelpreis: "+z.format(einzelpreis)+" Euro</td></tr>";
			html+="<tr> <td></td> <td>Gesamtpreis: "+z.format(gesamtpreis)+" Euro </td></tr>";
			html+="</form>";
			gesamtbetrag+=gesamtpreis;
		}
		html+="</table>";
		html+="<br><br><br><h4>Gesamtbetrag: "+z.format(gesamtbetrag)+" Euro</h4>";
		return html;
	}
	
	
	
	public void changeMenge(int menge, int artikelNr) throws NoConnectionException, SQLException {	//im Warenkorb
		String sql = "update warenkorbkunde set menge="+menge+"where kundennr="+this.kundenNr+"and artikelnr="+artikelNr;
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		sql = "select einzelpreis from warenkorbkunde where kundennr="+this.kundenNr+" and artikelnr= "+artikelNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		double gesamtpreis= menge* dbRes.getDouble("einzelpreis");
		sql = "update warenkorbkunde set gesamtpreis="+gesamtpreis+"where kundennr="+this.kundenNr+"and artikelnr="+artikelNr;
		prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		System.out.println("Menge wurde geändert. /wkkBean");
		
	}
	
	public void deletArtikel(int artikelNr) throws NoConnectionException, SQLException {		//aus Warenkorb entfernen
		String sql = "DELETE FROM warenkorbkunde WHERE artikelnr="+artikelNr+"and kundennr="+kundenNr;
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel wurde gelöscht. /wkkBean"+artikelNr+" kundennr: "+kundenNr);
	}
	
	public String endpreis() throws NoConnectionException, SQLException {
		double endpreis=0;
		String sql="select gesamtpreis from warenkorbkunde where kundennr= "+kundenNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			endpreis += dbRes.getDouble("gesamtpreis");
		}
		return z.format(endpreis);
	}
	
	public boolean checkMenge() throws NoConnectionException, SQLException {
		String sql = "select artikelnr, menge from warenkorbkunde where kundennr="+kundenNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			 int artikelnr=dbRes.getInt("artikelnr");
			 int menge = dbRes.getInt("menge");
			 
			 String sqlLager = "select lager from artikel where artikelnr="+artikelnr;
			 ResultSet dbResLager = new PostgreSQLAccess().getConnection().prepareStatement(sqlLager).executeQuery();
			 dbResLager.next();
			 int lager = dbResLager.getInt("lager");
			 if (menge >lager)return false;
		}
		return true;
	}
		
}
