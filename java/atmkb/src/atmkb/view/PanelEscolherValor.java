package atmkb.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PanelEscolherValor extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelEscolherValor(final ATMFrame aTMFrame) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Escolha a importância que deseja levantar");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(28, 11, 378, 25);
		add(lblNewLabel);
		
		JButton btnDez = new JButton("10");
		btnDez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.entregarDinheiro(10f);
			}
		});
		btnDez.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDez.setBounds(10, 62, 53, 31);
		add(btnDez);
		
		JButton btnVinte = new JButton("20");
		btnVinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.entregarDinheiro(20f);
			}
		});
		btnVinte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVinte.setBounds(10, 104, 53, 31);
		add(btnVinte);
		
		JButton btnOutrosValores = new JButton("Outras importâncias");
		btnOutrosValores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aTMFrame.outrosValores();
			}
		});
		btnOutrosValores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOutrosValores.setBounds(10, 146, 189, 31);
		add(btnOutrosValores);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(10, 188, 99, 31);
		add(btnCancelar);

	}

}
