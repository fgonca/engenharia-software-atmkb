package atmkb.view;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import atmkb.dao.ExcepcaoDAO;

public class PanelDevolverCartaoDinheiro extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private boolean cartaoRetirado = false;
	private boolean dinheiroRetirado = false;
	private JTextPane talaoLevantamento;

	/**
	 * Create the panel.
	 */
	public PanelDevolverCartaoDinheiro(final ATMFrame aTMFrame) 
	{
		setLayout(null);
		
		JButton btnRetirarDinheiro = new JButton("Retirar o dinheiro e o comprovativo");
		btnRetirarDinheiro.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					aTMFrame.debitar();
				} 
				catch (ExcepcaoDAO e1) 
				{
					e1.printStackTrace();
				}
				dinheiroRetirado = true;
				if(cartaoRetirado) 
				{
					cartaoRetirado = false;
					dinheiroRetirado = false;
					iniciarTalaoLevantamento();
					aTMFrame.exibirPanel("panelLeituraCartao");
				}
			}
		});
		btnRetirarDinheiro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRetirarDinheiro.setBounds(86, 167, 261, 31);
		add(btnRetirarDinheiro);
		
		JButton btnRetirarCartao = new JButton("Retirar o cartão");
		btnRetirarCartao.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				cartaoRetirado = true;
				if(dinheiroRetirado) 
				{
					cartaoRetirado = false;
					dinheiroRetirado = false;
					iniciarTalaoLevantamento();
					aTMFrame.exibirPanel("panelLeituraCartao");
				}
			}
		});
		btnRetirarCartao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRetirarCartao.setBounds(141, 209, 153, 31);
		add(btnRetirarCartao);
		
		talaoLevantamento = new JTextPane();
		talaoLevantamento.setFont(new Font("Monospaced", Font.PLAIN, 14));
		talaoLevantamento.setBounds(21, 33, 402, 123);
		add(talaoLevantamento);
	}
	
	public void exibirTalaoLevantamento(String comprovativo)
	{
		talaoLevantamento.setText(comprovativo);
		
		//Centralizar o título
		StyledDocument documento = talaoLevantamento.getStyledDocument();
		SimpleAttributeSet centrado = new SimpleAttributeSet();
		StyleConstants.setAlignment(centrado, StyleConstants.ALIGN_CENTER);
		documento.setParagraphAttributes(0, 13, centrado, true);
	}
	
	private void iniciarTalaoLevantamento()
	{
		talaoLevantamento.setDocument(new DefaultStyledDocument());
	}
}
