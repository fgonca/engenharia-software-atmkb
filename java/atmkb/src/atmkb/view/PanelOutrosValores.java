package atmkb.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelOutrosValores extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelOutrosValores(ATMFrame aTMFrame) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digitar o valor da importância a levantar");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(44, 5, 362, 25);
		add(lblNewLabel);
		
		JTextField textValor = new JTextField();
		textValor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textValor.setBounds(44, 88, 156, 28);
		add(textValor);
		textValor.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.entregarDinheiro(Float.parseFloat(textValor.getText()));
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmar.setBounds(44, 142, 109, 31);
		add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.exibirPanel("panelLeituraCartao");
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(185, 142, 99, 31);
		add(btnCancelar);

	}
}
