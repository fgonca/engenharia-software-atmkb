package atmkb.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PanelLeituraCartao extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textCartao;

	/**
	 * Create the panel.
	 */
	public PanelLeituraCartao(final ATMFrame aTMFrame) {
		setLayout(null);
		
		JButton btnInserirCartao = new JButton("Inserir o cartão");
		btnInserirCartao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInserirCartao.setBounds(151, 248, 156, 33);
		btnInserirCartao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textCartao.getText().length() > 0)
				{
					int numeroCartao = Integer.parseInt(textCartao.getText());
					aTMFrame.lerCartao(numeroCartao);
				} 
			}
		});
		add(btnInserirCartao);
		
		textCartao = new JTextField();
		textCartao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCartao.setBounds(218, 218, 88, 23);
		add(textCartao);
		textCartao.setColumns(8);
		
		JLabel lblNewLabel = new JLabel("Nº cartão");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(151, 226, 65, 17);
		add(lblNewLabel);
		
		
		ImageIcon imagemOriginal = new ImageIcon(PanelLeituraCartao.class.getResource("/images/funji_lambula_maxanana.jpg"));
		Image imagem = imagemOriginal.getImage();
		Image imagemAjustada = imagem.getScaledInstance(195, 195, ABORT);
		ImageIcon iconAjustado = new ImageIcon(imagemAjustada);
		JLabel lblNewLabel_1 = new JLabel(iconAjustado);
		//lblNewLabel_1.setIcon(imagem);
		lblNewLabel_1.setBounds(123, 15, 211, 195);
		add(lblNewLabel_1);

	}
	
	public void limparTextCartao()
	{
		textCartao.setText("");
	}
}
