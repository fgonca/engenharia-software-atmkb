package atmkb.model;

import java.util.ArrayList;

import atmkb.dao.ContaDAO;
import atmkb.dao.ExcepcaoDAO;

public class Conta 
{
	private int numero;
	private float saldo;
	private ArrayList<Cliente> clientes= new ArrayList<Cliente>();
	
	public int getNumero() 
	{
		return numero;
	}

	public void setNumero(int numero) 
	{
		this.numero = numero;
	}

	public float getSaldo() 
	{
		return saldo;
	}

	public void setSaldo(float saldo) 
	{
		this.saldo = saldo;
	}

	public ArrayList<Cliente> getClientes() 
	{
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) 
	{
		this.clientes = clientes;
	}
	
	public void debitar(float valor) throws ExcepcaoDAO
	{
		new ContaDAO().debitar(this, valor);
	}
}
