package atmkb.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class PanelSaldo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextPane jTextPaneSaldo;
	
	public PanelSaldo(ATMFrame aTMFrame) 
	{
		setLayout(null);
		
		JButton btnRetirarCartao = new JButton("Retirar o cartão");
		btnRetirarCartao.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				iniciarTextPanelSaldo();
				aTMFrame.exibirPanel("panelLeituraCartao");
			}
		});
		btnRetirarCartao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRetirarCartao.setBounds(250, 175, 153, 31);
		add(btnRetirarCartao);
		
		btnRetirarCartao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRetirarCartao.setBounds(262, 175, 153, 31);
		add(btnRetirarCartao);
		
		JButton btnOutasOperacoes = new JButton("Outras operações");
		btnOutasOperacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.exibirPanel("panelLeituraPIN");
			}
		});
		btnOutasOperacoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOutasOperacoes.setBounds(47, 175, 159, 31);
		add(btnOutasOperacoes);
		
		jTextPaneSaldo = new JTextPane();
		jTextPaneSaldo.setFont(new Font("Monospaced", Font.PLAIN, 14));
		jTextPaneSaldo.setBounds(21, 33, 402, 123);
		add(jTextPaneSaldo);
	}
	
	public void exibirTalao(String talao)
	{
		jTextPaneSaldo.setText(talao);
		
		//Centralizar o título
		StyledDocument documento = jTextPaneSaldo.getStyledDocument();
		SimpleAttributeSet centrado = new SimpleAttributeSet();
		StyleConstants.setAlignment(centrado, StyleConstants.ALIGN_CENTER);
		documento.setParagraphAttributes(0, 12, centrado, true);
	}
	
	private void iniciarTextPanelSaldo()
	{
		jTextPaneSaldo.setDocument(new DefaultStyledDocument());
	}
}
