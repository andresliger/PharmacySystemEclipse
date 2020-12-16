package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemResponse extends JFrame {

	private JPanel contentPane;
	private PharmacyItem item;

	/**
	 * Create the frame.
	 */
	public ItemResponse(PharmacyItem item) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Distribuidor "+item.getProviderName());
		
		JButton btnSend = new JButton("Enviar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Operacion realizada con Éxito");
				System.out.println("Pedido enviado");
				dispose();
				
			}
		});
		btnSend.setBounds(303, 192, 89, 23);
		contentPane.add(btnSend);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnCancel.setBounds(182, 192, 89, 23);
		contentPane.add(btnCancel);
		
		JTextArea txtAreaResult = new JTextArea();
		txtAreaResult.setBounds(65, 71, 338, 91);
		txtAreaResult.setText(item.getFullDescriptionPayment());
		
		contentPane.add(txtAreaResult);
		
		JLabel lblNewLabel = new JLabel("Confirmaci\u00F3n del pedido");
		lblNewLabel.setBounds(65, 41, 168, 14);
		contentPane.add(lblNewLabel);
	}

	public PharmacyItem getItem() {
		return item;
	}

	public void setItem(PharmacyItem item) {
		this.item = item;
	}
	
	
}
