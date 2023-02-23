package dao;
import java.sql.DriverManager;

public class H2ConnectionFactory implements ConnectionFactory {
		@Override
		public java.sql.Connection createConnection() throws Exception{
			Class.forName("org.h2.Driver");
	        return DriverManager.getConnection(CONNECTION_STRING, "sa", "");
		}
		
		protected final static String DB_PATH = "./labDiaImm";
		//protected final static String DB_PATH = "./test.";
		protected final static String CONNECTION_STRING = "jdbc:h2:" + DB_PATH;
}
