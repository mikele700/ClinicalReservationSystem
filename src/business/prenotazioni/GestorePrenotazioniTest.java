package business.prenotazioni;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import business.clienti.Cliente;
import business.exceptions.IndisponibilitaPrenotazione;

public class GestorePrenotazioniTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gestore=new GestorePrenotazioni();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gestore=null;
	}

	@Test
	public void testRegistraPrenotazioneDisponibile() throws IndisponibilitaPrenotazione {
		Prenotazione pRegistrata=gestore.RegistraPrenotazione(new GregorianCalendar(2017, 03, 06, 16, 30), "169-RM busto", new Cliente("Mario", "Rossi", "Via Monte Napoleone 35, Milano", new GregorianCalendar(1990, 3, 6), "MA90RO04MI06", "0815769164", "mario@gmail.com", 1));
		assertTrue((pRegistrata.getIdPersistenza()!=null)&&(pRegistrata.getCodiceAnalisi().equals("169-RM busto"))&&(pRegistrata.getOrario().get(Calendar.YEAR)==2017)&&(pRegistrata.getOrario().get(Calendar.MONTH)==03)&&(pRegistrata.getOrario().get(Calendar.DAY_OF_MONTH)==06)&&(pRegistrata.getOrario().get(Calendar.HOUR_OF_DAY)==16)&&(pRegistrata.getOrario().get(Calendar.MINUTE)==30)&&(pRegistrata.getStato().equals("ASSEGNATA"))&&(pRegistrata.getCliente().getIdPersistenza()==1)&&(pRegistrata.getStanza().getIdPersistenza()!=null));
	}
	
	@Test(expected=IndisponibilitaPrenotazione.class)
	public void testRegistraPrenotazioneClienteNonMemorizzato() throws IndisponibilitaPrenotazione{
		gestore.RegistraPrenotazione(new GregorianCalendar(2018, 03, 06, 16, 30), "169-RM busto", new Cliente("Cristoforo", "Colombo", "Genova", new GregorianCalendar(1492, 7, 3), "CRI", "081", "@colombo.it"));
		//assertTrue((pRegistrata.getIdPersistenza()!=null)&&(pRegistrata.getCodiceAnalisi().equals("169-RM busto"))&&(pRegistrata.getOrario().get(Calendar.YEAR)==2018)&&(pRegistrata.getOrario().get(Calendar.MONTH)==03)&&(pRegistrata.getOrario().get(Calendar.DAY_OF_MONTH)==06)&&(pRegistrata.getOrario().get(Calendar.HOUR_OF_DAY)==16)&&(pRegistrata.getOrario().get(Calendar.MINUTE)==30)&&(pRegistrata.getStato().equals("ASSEGNATA"))&&(pRegistrata.getCliente().getIdPersistenza()!=null)&&(pRegistrata.getStanza().getIdPersistenza()!=null));
		fail("ClienteInesistente non è stato sollevato.");
	}
	
	@Test(expected=IndisponibilitaPrenotazione.class)
	public void testRegistraPrenotazioneCodiceAnalisiNonMemorizzato() throws IndisponibilitaPrenotazione{
		gestore.RegistraPrenotazione(new GregorianCalendar(2019, 03, 06, 16, 30), "170-RM torace", new Cliente("Mario", "Rossi", "Via Monte Napoleone 35, Milano", new GregorianCalendar(1990, 3, 6), "MA90RO04MI06", "0815769164", "mario@gmail.com", 1));
		fail("IndisponibilitaPrenotazione non è stata sollevata.");
	}
	
	@Test(expected=IndisponibilitaPrenotazione.class)
	public void testRegistraPrenotazionePrenotazioneAssegnataSovrapposta() throws IndisponibilitaPrenotazione{
		gestore.RegistraPrenotazione(new GregorianCalendar(2017, 01, 10, 10, 40), "169-RM busto", new Cliente("Mario", "Rossi", "Via Monte Napoleone 35, Milano", new GregorianCalendar(1990, 3, 6), "MA90RO04MI06", "0815769164", "mario@gmail.com", 1));
		fail("IndisponibilitaPrenotazione non è stata sollevata.");
	}
	
	@Test
	public void testRegistraPrenotazionePrenotazioneAnnullataSovrapposta() throws IndisponibilitaPrenotazione{
		Prenotazione pRegistrata=gestore.RegistraPrenotazione(new GregorianCalendar(2017, 10, 01, 11, 30), "169-RM busto", new Cliente("Mario", "Rossi", "Via Monte Napoleone 35, Milano", new GregorianCalendar(1990, 3, 6), "MA90RO04MI06", "0815769164", "mario@gmail.com", 1));
		assertTrue((pRegistrata.getIdPersistenza()!=null)&&(pRegistrata.getCodiceAnalisi().equals("169-RM busto"))&&(pRegistrata.getOrario().get(Calendar.YEAR)==2017)&&(pRegistrata.getOrario().get(Calendar.MONTH)==10)&&(pRegistrata.getOrario().get(Calendar.DAY_OF_MONTH)==01)&&(pRegistrata.getOrario().get(Calendar.HOUR_OF_DAY)==11)&&(pRegistrata.getOrario().get(Calendar.MINUTE)==30)&&(pRegistrata.getStato().equals("ASSEGNATA"))&&(pRegistrata.getCliente().getIdPersistenza()==1)&&(pRegistrata.getStanza().getIdPersistenza()!=null));
	}

	private static GestorePrenotazioni gestore;
}
