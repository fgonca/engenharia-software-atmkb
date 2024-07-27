package atmkb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import atmkb.model.*;

public class CartaoDAO 
{
	public Cartao consultar(Cartao cartao, int numero) throws ExcepcaoDAO
	{
		final String query = "SELECT * FROM v_cartao WHERE numero_cartao = ?";
		PreparedStatement pStatement = null;
		Connection con = null;
		
		try 
		{
			con = new Conexao().getConnection();
			pStatement = con.prepareStatement(query);
			pStatement.setInt(1, numero);
			ResultSet rs = pStatement.executeQuery();

			if(rs.next())
			{
				cartao.setNumero(rs.getInt("numero_cartao"));
				cartao.setPIN(rs.getString("pin"));
				cartao.setTentativas(rs.getInt("tentativas"));
				Date sqlDate = rs.getDate("data_validade");
				LocalDate localDate = sqlDate.toLocalDate();
				cartao.setDataValidade(localDate);				

				Conta conta = new Conta();				
				conta.setNumero(rs.getInt("numero_conta"));
				conta.setSaldo(rs.getFloat("saldo"));				
				
				ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				Cliente cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				clientes.add(cliente);
				
				/*System.out.println(cliente.getNome());
				System.exit(0);*/
				
				while(rs.next())
				{
					Cliente clienteOutros = new Cliente();
					clienteOutros.setNome(rs.getString("nome"));
					clientes.add(clienteOutros);
				}
				conta.setClientes(clientes);
				cartao.setConta(conta);				
			}
		} 
		catch (Exception e) 
		{
			throw new ExcepcaoDAO("Erro ao consultar cartao: " + e);
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
		return cartao;
	}
	
	public void iniciarTentativas(Cartao cartao) throws ExcepcaoDAO
	{
		final String query = "UPDATE cartao SET tentativas = 3 WHERE numero = ?";
		PreparedStatement pStatement = null;
		Connection con = null;
		try 
		{
			con = new Conexao().getConnection();
			pStatement = con.prepareStatement(query);
			pStatement.setInt(1, cartao.getNumero());
			pStatement.execute();
		} 
		catch (Exception e) 
		{
			throw new ExcepcaoDAO("Erro ao reiniciar o número de tentativas: " + e);
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
	
	public void decrementarTentativas (Cartao cartao) throws ExcepcaoDAO
	{
		final String query = "UPDATE cartao SET tentativas = tentativas - 1 "
				+ "WHERE numero = ?";
		PreparedStatement pStatement = null;
		Connection con = null;
		try 
		{
			con = new Conexao().getConnection();
			pStatement = con.prepareStatement(query);
			pStatement.setInt(1, cartao.getNumero());
			pStatement.execute();
		} 
		catch (Exception e) 
		{
			throw new ExcepcaoDAO("Erro ao decrementar número de tentativas: " + e);
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
