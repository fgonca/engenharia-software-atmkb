package atmkb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import atmkb.dao.ExcepcaoDAO;
import atmkb.model.*;
import atmkb.view.*;

public class ControlMovimento 
{
	private int numeroCartao;
	private char[] pin;
	private float valor;
	private Cartao cartao;
	private Conta conta;
	private ATMFrame aTMFrame;
	private LocalDate dataCorrente;

	public ControlMovimento(ATMFrame aTMFrame) throws ExcepcaoDAO 
	{
		this.aTMFrame = aTMFrame;
		this.numeroCartao = aTMFrame.getNumeroCartao();
		this.valor = aTMFrame.getValor();
		this.pin = aTMFrame.getPIN();
	}

	public void consultarSaldo() 
	{
		if(autenticado())
		{
			conta = cartao.getConta();
			String talao = obterTalaoSaldo();
			aTMFrame.exibirTalaoSaldo(talao);
		}
	}
	
	public void levantar() 
	{
		if(autenticado())
		{
			conta = cartao.getConta();
			if(saldoSuficiente())
			{
				String talao = obterTalaoDebido();
				aTMFrame.devolverCartaoDinheiro(talao);
			}
			else
			{
				aTMFrame.naoEntregarDinheiro();
			}
		}
	}
	
	private boolean tempoValido() throws ExcepcaoDAO
	{
		LocalDate dataValidade = cartao.getDataValidade();
		dataCorrente = LocalDate.now();
		if (dataValidade!=null && dataValidade.isAfter(dataCorrente))
		{
			return true;
		}
		return false;
	}
	
	private boolean pinCorreto()
	{
		if (BCrypt.checkpw(new String(this.pin), cartao.getPIN()))
		{
			try 
			{
				cartao.iniciarTentativas();
			} catch (ExcepcaoDAO e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	private boolean saldoSuficiente()
	{
		float saldo = conta.getSaldo();
		if(saldo > this.valor)
		{
			return true;
		}
		return false;
	}
	
	public void debitar() throws ExcepcaoDAO
	{
		conta.debitar(valor);
	}
	
	public String obterTalaoDebido()
	{
		String talao = "Comprovativo\n";

		int numeroConta = conta.getNumero();
		talao = talao.concat("Conta: ").concat(Integer.toString(numeroConta));

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String data = dataCorrente.format(formato);
		talao = talao.concat("      ").concat(data).concat("\n");
		
		talao = talao.concat("Titular(es): ");
		talao = talao.concat(consultarTitular()).concat("\n");
		
		talao = talao.concat("Débito: ").concat(Float.toString(this.valor));
		talao = talao.concat("\n");		
		
		float saldo = conta.getSaldo();
		saldo = saldo - valor; 
		talao = talao.concat("Saldo: ").concat(Float.toString(saldo));
		talao = talao.concat("\n");
		
		return talao;
	}
	
	private String consultarTitular()
	{
		String titular = "";
		ArrayList<Cliente> clientes = conta.getClientes();
		int i = clientes.size();
		for (Cliente cliente: clientes)
		{
			titular = titular.concat(cliente.getNome());
			if(i > 1)
			{
				titular = titular.concat("; ");
				i--;
			}
		}
		return titular;
	}
	
	public String obterTalaoSaldo()
	{
		String talao = "Talão saldo\n";

		int numeroConta = conta.getNumero();
		talao = talao.concat("Conta: ").concat(Integer.toString(numeroConta));

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String data = dataCorrente.format(formato);
		talao = talao.concat("      ").concat(data).concat("\n");
		
		talao = talao.concat("Titular(es): ");
		talao = talao.concat(consultarTitular()).concat("\n");	
		
		float saldo = conta.getSaldo();
		talao = talao.concat("Saldo: ").concat(Float.toString(saldo));
		talao = talao.concat("\n");
		
		return talao;
	}
	
	private boolean autenticado()
	{
		boolean resultado = false;
		cartao = new Cartao();
		try {
			cartao.consultar(numeroCartao);

			if(tempoValido())
			{
				resultado = pinCorreto();
				if(!resultado)
				{
					cartao.decrementarTentativas();
					int numeroTentativas = cartao.getTentativas();
					numeroTentativas --;
					if(numeroTentativas > 0)
					{						
						aTMFrame.devolverCartao(numeroTentativas);
					}
					else 
					{
						aTMFrame.reterCartao("Número de tentativas excedido");
					}
				}
			}
			else
			{
				aTMFrame.reterCartao("Cartão expirado");
			}
		} catch (ExcepcaoDAO e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
}
