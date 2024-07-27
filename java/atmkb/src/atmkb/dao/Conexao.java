package atmkb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	// Informações de conexão com o banco de dados
    private String url = "jdbc:mysql://localhost:3306/atmkb";
    private String usuario = "root";
    private String senha = "";
    private Connection conexao = null;
	
	public Connection getConnection()
	{		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			System.out.println("Falhou a obter a conexão");
			e.printStackTrace();			 
		}
		
		try 
		{
			// Estabelecer conexão com o banco de dados
            conexao = DriverManager.getConnection(url, usuario, senha);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return conexao;
	}
}
