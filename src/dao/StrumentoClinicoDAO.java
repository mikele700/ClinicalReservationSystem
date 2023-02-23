package dao;

import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import business.exceptions.DatoInputErrato;
import business.exceptions.NessunoStrumentoInStanzaTrovato;
import business.exceptions.StanzaInesistente;
import business.stanze.*;

public class StrumentoClinicoDAO {

	public static List<StrumentoClinico> leggiStrumentiPerStanza(Stanza s) throws SQLException, DatoInputErrato, NessunoStrumentoInStanzaTrovato, StanzaInesistente{
		if(s.getIdPersistenza()==null){
			throw new StanzaInesistente();
		}
		Connection c=DBManager.getConnection();
		PreparedStatement pstat=c.prepareStatement("SELECT * FROM STRUMENTICLINICI WHERE Stanza=?");
		pstat.setInt(1, s.getIdPersistenza());
		ResultSet rs= pstat.executeQuery();
		List<StrumentoClinico> ListaStrumentiPerStanza= new java.util.ArrayList<StrumentoClinico>();
		while(rs.next()){
			StrumentoClinico sc;
			Integer id=rs.getInt("ID");
			if(restoredObjects.containsKey(id))
				sc = new StrumentoClinico(restoredObjects.get(id));
			else{
				String codice= rs.getString("CodiceAnalisi");
				String tipoStr=rs.getString("Tipo");
				StrumentoClinico.tipoStrumento tipo=StrumentoClinico.StringToTipoStrumento(tipoStr);
				sc=new StrumentoClinico(codice, tipo, id);
				restoredObjects.put(id, sc);
			}
			ListaStrumentiPerStanza.add(sc);
		}
		pstat.close();
		s.setStrumenti(ListaStrumentiPerStanza);//lista salvata come attributo della stanza
		if(ListaStrumentiPerStanza.isEmpty())
			throw new NessunoStrumentoInStanzaTrovato();
		return ListaStrumentiPerStanza;
	}
	protected static Map<Integer, StrumentoClinico> restoredObjects= new java.util.HashMap<Integer, StrumentoClinico>();
}
