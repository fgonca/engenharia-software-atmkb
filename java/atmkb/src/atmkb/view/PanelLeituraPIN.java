package atmkb.view;

import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelLeituraPIN extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordPIN;

	/**
	 * Create the panel.
	 */
	public PanelLeituraPIN(final ATMFrame aTMFrame) 
	{
		setLayout(null);
		
		passwordPIN = new JPasswordField(4);
		passwordPIN.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				char[] pIN = passwordPIN.getPassword();
				if(passwordPIN.getPassword().length == 4)
				{
					aTMFrame.setPIN(pIN);
				}
			}
		});
		passwordPIN.setFont(new Font("Dialog", Font.PLAIN, 14));
		passwordPIN.setBounds(163, 107, 107, 31);
		add(passwordPIN);
		
		JLabel lblNewLabel = new JLabel("Digite o seu PIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(144, 21, 144, 25);
		add(lblNewLabel);
		
		JButton btnCancelar = new JButton("Cancelar");
		//btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCancelar.setBounds(163, 169, 99, 31);
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				aTMFrame.exibirPanel("panelLeituraCartao");
			}
		});
		add(btnCancelar);
	}
	
	public void limparPasswordPIN()
	{
		passwordPIN.setText("");
	}
}
