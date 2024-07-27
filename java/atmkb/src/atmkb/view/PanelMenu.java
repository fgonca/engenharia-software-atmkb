package atmkb.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelMenu(final ATMFrame aTMFrame) {
		setLayout(null);
		
		JButton btnConsultarSaldo = new JButton("Consultar saldo");
		btnConsultarSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.consultarSaldo();
			}
		});
		btnConsultarSaldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultarSaldo.setBounds(10, 55, 185, 25);
		add(btnConsultarSaldo);
		
		JButton btnLevantamento = new JButton("Fazer levantamento");
		btnLevantamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.levantamento();
			}
		});
		btnLevantamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLevantamento.setBounds(10, 97, 185, 25);
		add(btnLevantamento);
		
		JButton btnTranferencia = new JButton("Fazer transferência");
		btnTranferencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTranferencia.setBounds(10, 139, 185, 25);
		add(btnTranferencia);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(10, 175, 185, 25);
		add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Escolha uma das opções");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(118, 11, 218, 33);
		add(lblNewLabel);

	}

}
