package business.clienti;

import java.util.List;

public interface IGestoreClienti {
	public List<Cliente> ListaClienti();
	public Cliente RicercaCliente(Integer id);

}
