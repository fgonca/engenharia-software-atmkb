package atmkb.model;

import java.time.LocalDate;

import atmkb.dao.CartaoDAO;
import atmkb.dao.ExcepcaoDAO;

public class Cartao 
{
	private int numero;
	private String pIN;
	private Conta conta;
	private int tentativas = 3;
	private LocalDate dataValidade;
	

	public int getNumero() 
	{
		return numero;
	}

	public void setNumero(int numero) 
	{
		this.numero = numero;
	}

	public String getPIN() 
	{
		return pIN;
	}

	public void setPIN(String pIN) 
	{
		this.pIN = pIN;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) 
	{
		this.conta = conta;
	}
	
	public int getTentativas() 
	{
		return tentativas;
	}

	public void setTentativas(int tentativas) 
	{
		this.tentativas = tentativas;
	}
	
	public LocalDate getDataValidade() 
	{
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) 
	{
		this.dataValidade = dataValidade;
	}
	
	public void decrementarTentativas() throws ExcepcaoDAO
	{
		new CartaoDAO().decrementarTentativas(this);
	}
	
	public Cartao consultar(int numero) throws ExcepcaoDAO
	{
		return new CartaoDAO().consultar(this, numero);
	}
	
	public void iniciarTentativas() throws ExcepcaoDAO
	{
		new CartaoDAO().iniciarTentativas(this);
	}
}
