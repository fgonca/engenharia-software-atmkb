package atmkb.view;

import javax.swing.*;

import atmkb.controller.ControlMovimento;
import atmkb.dao.ExcepcaoDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int numeroCartao = 0;
	private char[] pIN = null;
	private float valor = 0;
	private PanelMenu panelMenu;
	private PanelLeituraCartao panelLeituraCartao;
	private PanelLeituraPIN panelLeituraPIN;
	private PanelEscolherValor panelEscolherValor;
	private PanelOutrosValores panelOutrosValores;
	private PanelDevolverCartaoDinheiro panelDevolverCartaoDinheiro;
	private PanelDevolverCartao panelDevolverCartao;
	private PanelSaldo panelSaldo;
	private ControlMovimento ctrlLeva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{ 
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ATMFrame frame = new ATMFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ATMFrame() 
	{
		setTitle("ATM Kuya Bwé");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
        contentPane.setLayout(new CardLayout(0, 0));
		setContentPane(contentPane);
		
		criarPanels();

        // Mostrar o primeiro painel
        exibirPanel("panelLeituraCartao");
	}
	
	public void lerCartao(int numeroCartao) 
	{
		this.numeroCartao = numeroCartao;
		exibirPanel("panelLeituraPIN");
	}
	
	private void criarPanels()
	{
		// Adicionar as panels
		panelLeituraCartao = new PanelLeituraCartao(this);
		panelLeituraCartao.setBounds(0, 0, 434, 261);

		panelLeituraPIN = new PanelLeituraPIN(this);
		panelLeituraPIN.setBounds(0, 0, 434, 261);

		panelMenu = new PanelMenu(this);
		panelMenu.setBounds(0, 0, 434, 261);

		panelDevolverCartao = new PanelDevolverCartao(this);
		panelDevolverCartao.setBounds(0, 0, 434, 261);

		contentPane.add(panelLeituraCartao, "panelLeituraCartao");
		contentPane.add(panelLeituraPIN, "panelLeituraPIN");
		contentPane.add(panelMenu, "panelMenu");
		contentPane.add(panelDevolverCartao, "panelDevolverCartao");
	}
	
	public void exibirPanel(String panelName) 
	{
		CardLayout cL = (CardLayout) contentPane.getLayout();
        cL.show(contentPane, panelName);
        switch(panelName)
        {
        case "panelLeituraCartao":
        	panelLeituraCartao.limparTextCartao();
        	break;
        case "panelLeituraPIN":
        	panelLeituraPIN.limparPasswordPIN();
        	break;        
        }
    }
	
	public void devolverCartaoDinheiro(String talao) 
	{
		panelDevolverCartaoDinheiro.exibirTalaoLevantamento(talao);
		CardLayout cL = (CardLayout) contentPane.getLayout();
        cL.show(contentPane, "panelDevolverCartaoDinheiro");
	}
	
	public void devolverCartao(int tentativas) 
	{
		panelDevolverCartao.mensagemPINErrado(tentativas);
		CardLayout cL = (CardLayout) contentPane.getLayout();
        cL.show(contentPane, "panelDevolverCartao");
    }
	
	public void exibirTalaoSaldo(String talao) 
	{
		panelSaldo = new PanelSaldo(this);
		panelSaldo.setBounds(0, 0, 434, 261);
		contentPane.add(panelSaldo, "panelSaldo");
		
		panelSaldo.exibirTalao(talao);
		CardLayout cL = (CardLayout) contentPane.getLayout();
        cL.show(contentPane, "panelSaldo");
    }

	public int getNumeroCartao() 
	{
		return numeroCartao;
	}

	public char[] getPIN() 
	{
		return pIN;
	}

	public float getValor() 
	{
		return valor;
	}
	
	public void setPIN(char[] pIN) 
	{
		this.pIN = pIN;
		exibirPanel("panelMenu");
	}
	
	public void levantamento()
	{
		panelEscolherValor = new PanelEscolherValor(this);
		panelEscolherValor.setBounds(0, 0, 434, 261);

		panelOutrosValores = new PanelOutrosValores(this);
		panelOutrosValores.setBounds(0, 0, 434, 261);

		panelDevolverCartaoDinheiro = 
				new PanelDevolverCartaoDinheiro(this);
		panelDevolverCartaoDinheiro.setBounds(0, 0, 434, 261);
		
		contentPane.add(panelEscolherValor, "panelEscolherValor");
		contentPane.add(panelOutrosValores, "panelOutrosValores");
		contentPane.add(panelDevolverCartaoDinheiro, "panelDevolverCartaoDinheiro");

		exibirPanel("panelEscolherValor");
	}
	
	public void outrosValores()
	{
		exibirPanel("panelOutrosValores");
	}
	
	public void debitar() throws ExcepcaoDAO
	{
		ctrlLeva.debitar();
	}
	
	public void reterCartao(String motivo)
	{
		PanelCartaoRetido panelCartaoRetido = new PanelCartaoRetido();
		panelCartaoRetido.setBounds(0, 0, 434, 261);
		panelCartaoRetido.adicionarMotivo(motivo);
		contentPane.add(panelCartaoRetido, "panelCartaoRetido");		
		exibirPanel("panelCartaoRetido");
		
		Timer tempo = new Timer(5000, 
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					exibirPanel("panelLeituraCartao");
				}
			});
		tempo.setRepeats(false);
		tempo.start();		
	}
	
	public void entregarDinheiro(float valor)
	{
		this.valor = valor;
		try 
		{
			ctrlLeva = new ControlMovimento(this);
			ctrlLeva.levantar();
		} 
		catch (ExcepcaoDAO e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public void consultarSaldo()
	{
		try 
		{
			ctrlLeva = new ControlMovimento(this);
			ctrlLeva.consultarSaldo();
		} 
		catch (ExcepcaoDAO e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public void naoEntregarDinheiro()
	{
		panelDevolverCartao.mensagemNaoEntregarDinheiro();
		CardLayout cL = (CardLayout) contentPane.getLayout();
        cL.show(contentPane, "panelDevolverCartao");
	}
}
