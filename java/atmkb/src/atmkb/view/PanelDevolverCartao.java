package atmkb.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PanelDevolverCartao extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblMensagem;

	/**
	 * Create the panel.
	 */
	public PanelDevolverCartao(final ATMFrame aTMFrame) {
		setLayout(null);
		
		lblMensagem = new JLabel("");
		lblMensagem.setForeground(Color.RED);
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensagem.setBounds(21, 26, 407, 80);
		add(lblMensagem);
		
		JButton btnRetirarCartao = new JButton("Retirar o cartão");
		btnRetirarCartao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.exibirPanel("panelLeituraCartao");
			}
		});
		btnRetirarCartao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRetirarCartao.setBounds(150, 183, 153, 31);
		add(btnRetirarCartao);

	}
	
	public void mensagemPINErrado(int tenta)
	{
		String palavra = "tentativa";
		if(tenta > 1)
		{
			palavra = "tentativas";
		}
			
		this.lblMensagem.setText("PIN errado! Restam-lhe "+tenta+" "+palavra+"!");
	}
	
	public void mensagemNaoEntregarDinheiro()
	{
		this.lblMensagem.setText("Por ordem do seu banco não podemos satisfazer o seu pedido");
	}
}
