package dao;

public interface ConnectionFactory{
		java.sql.Connection createConnection() throws Exception;//FactoryMethod per la connessione
}
