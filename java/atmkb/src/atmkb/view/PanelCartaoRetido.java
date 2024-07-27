package atmkb.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelCartaoRetido extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblMensagem;

	/**
	 * Create the panel.
	 */
	public PanelCartaoRetido() 
	{
		setLayout(null);
		lblMensagem = new JLabel();
		lblMensagem.setForeground(Color.RED);
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensagem.setBounds(10, 126, 430, 17);
		add(lblMensagem);
	}
	
	public void adicionarMotivo(String motivo)
	{
		lblMensagem.setText("O seu cartão foi retido! "+motivo);
	}
}
