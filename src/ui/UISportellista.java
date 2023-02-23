package ui;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import business.clienti.Cliente;
import business.clienti.GestoreClienti;
import business.exceptions.DatoInputErrato;
import business.exceptions.IndisponibilitaPrenotazione;
import business.prenotazioni.GestorePrenotazioni;
import business.prenotazioni.Prenotazione;

public class UISportellista {
	public UISportellista(){
		
	}
	public void RegistraPrenotazione(){
		System.out.println("Inserisci i dati per la tua prenotazione.");
		try(Scanner input = new Scanner(System.in)){
			System.out.println("Inserisci la data");
			System.out.println("Anno: ");
			int anno, mese, giorno, ora, minuto;
			if(input.hasNextInt()){
				anno = input.nextInt();
			} else
				throw new DatoInputErrato("Data scorretta.");
			System.out.println("Mese: ");
			if(input.hasNextInt()){
				mese = input.nextInt();
				if(mese<=0 || mese>12)
					throw new DatoInputErrato("Valore del mese non corretto.");
			}
			else
				throw new DatoInputErrato("Data scorretta.");
			System.out.println("Giorno: ");
			if(input.hasNextInt()){
				giorno = input.nextInt();
				if(giorno<=0 ||giorno>31)
					throw new DatoInputErrato("Valore del giorno non corretto.");
			}
			else
				throw new DatoInputErrato("Data scorretta.");
			System.out.println("Inserisci l'orario.");
			System.out.println("Ora: ");
			if(input.hasNextInt()){
				ora = input.nextInt();
				if(ora<0 || ora>24)
					throw new DatoInputErrato("Valore dell'ora non corretto.");
			} else
				throw new DatoInputErrato("Orario scorretto.");
			System.out.println("Minuto: ");
			if(input.hasNextInt()){
				minuto = input.nextInt();
				if(minuto<0 || minuto>=60)
					throw new DatoInputErrato("Valore dei minuti non corretto.");
			}
			else
				throw new DatoInputErrato("Orario scorretto.");
			GregorianCalendar orario=new GregorianCalendar(anno, (mese-1), giorno, ora, minuto);
			GregorianCalendar orarioAttuale=new GregorianCalendar();
			if(orario.compareTo(orarioAttuale)<0)
				throw new DatoInputErrato("Prenotazione impossibile.");
			input.nextLine();
			
			System.out.println("Inserisci il codice dell'analisi");
			System.out.println("Codice: ");
			if(input.hasNextLine()){
				String codiceAnalisi=input.nextLine();
				if(codiceAnalisi.equals(""))
					throw new DatoInputErrato("Codice di analisi non specificato.");
				
				System.out.println("Lista Clienti");
				VisualizzaClienti();
				System.out.println("Immettere l'ID del cliente richiedente la prenotazione");
				if(input.hasNextInt()){
					Integer id=input.nextInt();
					GestoreClienti gestoreClienti=GestoreClienti.getInstance();
					Cliente cliente=gestoreClienti.RicercaCliente(id);
					GestorePrenotazioni gestorePrenotazioni=GestorePrenotazioni.getInstance();
					Prenotazione prenotazioneRegistrata=gestorePrenotazioni.RegistraPrenotazione(orario, codiceAnalisi, cliente);
					stampaPrenotazione(prenotazioneRegistrata);
				}
				else
					throw new DatoInputErrato("ID cliente non corretto.");
			}
			else
				throw new DatoInputErrato("Codice non corretto");
		} catch (DatoInputErrato e) {
			System.err.println(e.getMessage());
		} catch (IndisponibilitaPrenotazione e) {
			System.err.println(e.getMessage());
		}
	}
		public void VisualizzaClienti(){
			GestoreClienti gestoreClienti=GestoreClienti.getInstance();
			List<Cliente> ListaClienti=gestoreClienti.ListaClienti();
			for(Cliente cl: ListaClienti)
				stampaCliente(cl);
				}
		
		protected void stampaCliente(Cliente cl){
			System.out.println(cl.getIdPersistenza()+" - "+cl.getNome()+" - "+cl.getCognome()+" - "+cl.getResidenza()+" - "+cl.getDataNascita().get(Calendar.DAY_OF_MONTH)+"/"+(cl.getDataNascita().get(Calendar.MONTH)+1)+"/"+cl.getDataNascita().get(Calendar.YEAR)+" - "+cl.getCF()+" - "+cl.getNumTelefonico()+" - "+cl.getEmail());
		}
		
		protected void stampaPrenotazione(Prenotazione p){
			System.out.println(p.getIdPersistenza()+" - "+p.getCodiceAnalisi()+" - "+p.getOrario().get(Calendar.DAY_OF_MONTH)+"/"+(p.getOrario().get(Calendar.MONTH)+1)+"/"+p.getOrario().get(Calendar.YEAR)+" - "+p.getOrario().get(Calendar.HOUR_OF_DAY)+":"+p.getOrario().get(Calendar.MINUTE)+" - "+p.getStato()+" - IDCliente:"+p.getCliente().getIdPersistenza()+" - IDStanza:"+p.getStanza().getIdPersistenza());
		}
}
