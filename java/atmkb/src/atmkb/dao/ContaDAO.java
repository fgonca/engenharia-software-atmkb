package atmkb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import atmkb.model.Conta;

public class ContaDAO 
{
	public void debitar(Conta conta, float valor) throws  ExcepcaoDAO
	{
		final String query = "UPDATE conta SET saldo = saldo - ? WHERE numero = ?";
		PreparedStatement pStatement = null;
		Connection con = null;
		try 
		{
			con = new Conexao().getConnection();
			pStatement = con.prepareStatement(query);
			pStatement.setFloat(1, valor);
			pStatement.setInt(2, conta.getNumero());
			pStatement.execute();
		} 
		catch (Exception e) 
		{
			throw new ExcepcaoDAO("Erro ao debitar" + e);
		}
		finally
		{
			try 
			{
				if(pStatement != null){ pStatement.close();	}
			} 
			catch (Exception e) 
			{
				throw new ExcepcaoDAO("Erro ao fechar a declaração: " + e);
			}
			
			try 
			{
				if(con != null ){ con.close(); }
			} 
			catch (Exception e) 
			{
				throw new ExcepcaoDAO("Erro ao fechar a conexão: " + e);
			}
		}
	}
}
