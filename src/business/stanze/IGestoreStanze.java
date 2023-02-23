package business.stanze;

import java.util.List;

public interface IGestoreStanze {
	public List<Stanza> ListaStanze();
	public List<StrumentoClinico> ListaStrumentiPerStanza(Stanza stanza);

}
