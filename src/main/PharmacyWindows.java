package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PharmacyWindows extends JFrame {

	private JPanel contentPane;
	private JTextField txtMedicineName;
	private JTextField txtItemsNumber;
	private JLabel lblMedicineNameCheck = new JLabel("");
	private JLabel lblItemNumberCheck = new JLabel("");
	private JLabel lblAddressCheck = new JLabel("");
	private static ImageIcon INFO_ICON = new ImageIcon(PharmacyWindows.class.getResource("/resources/info.jpg"));
	private static ImageIcon CHECK_ICON = new ImageIcon(PharmacyWindows.class.getResource("/resources/check.jpg"));
	private ButtonGroup btnGroup = new ButtonGroup();
	private JCheckBox chckbxMain;
	private JCheckBox chckbxAlternative;
	private JRadioButton rdbtnProvider1;
	private JRadioButton rdbtnProvider2;
	private JRadioButton rdbtnProvider3;
	private JComboBox cmbMedicineType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PharmacyWindows frame = new PharmacyWindows();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PharmacyWindows() {
		setBackground(UIManager.getColor("Button.background"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.setBounds(494, 289, 130, 23);
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se ha pulsado el boton confirmar");
				System.out.println("Empezando validación...");
				if(validateForm()) {
					
					PharmacyItem item = new PharmacyItem();
					item.setNameMedicine(txtMedicineName.getText());
					item.setTypeMedicine(cmbMedicineType.getSelectedItem().toString());
					item.setItemNumber(Integer.parseInt(txtItemsNumber.getText()));
					item.setProviderName(getSelectedProvider());
					
					String finalAddress = ""; 	
					if(chckbxAlternative.isSelected()) {
						finalAddress += "Calle de la Rosa n. 28 ";
						if(chckbxMain.isSelected()) 
							finalAddress += " y\n";	

					}
					
					if(chckbxMain.isSelected()) {
						finalAddress += "Calle Alcazabilla n. 3. ";
					}
					
					item.setAddress(finalAddress);
					
					System.out.println("Validación exitosa... ");
					ItemResponse responseForm = new ItemResponse(item);
					responseForm.setVisible(true);
					
					
				}else {
					System.out.println("Aún existen pendientes revisar los campos...");
				}
				
			}
		});
		contentPane.setLayout(null);
		
		txtMedicineName = new JTextField();
		txtMedicineName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				isValidateMedicineName();
			}
		});
		
		txtMedicineName.setBounds(203, 85, 86, 20);
		txtMedicineName.setColumns(10);
		contentPane.add(txtMedicineName);
		contentPane.add(btnConfirm);
		
		JLabel lblTitle = new JLabel("Ingrese su pedido:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTitle.setBounds(35, 33, 158, 14);
		contentPane.add(lblTitle);
		
		JLabel lblMedicineName = new JLabel("Nombre del medicamento:");
		lblMedicineName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedicineName.setBounds(35, 87, 158, 14);
		contentPane.add(lblMedicineName);
		
		JLabel lblMedicineType = new JLabel("Tipo del medicamento:");
		lblMedicineType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedicineType.setBounds(326, 86, 158, 14);
		contentPane.add(lblMedicineType);
		
		cmbMedicineType = new JComboBox();
		cmbMedicineType.setModel(new DefaultComboBoxModel(new String[] {"Analg\u00E9sico", "Anal\u00E9ptico", "Anest\u00E9sico", "Anti\u00E1cido", "Antidepresivo ", "Antibi\u00F3ticos"}));
		cmbMedicineType.setBounds(494, 83, 130, 22);
		contentPane.add(cmbMedicineType);
		
		JLabel lblItemNumber = new JLabel("Cantidad del producto:");
		lblItemNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblItemNumber.setBounds(35, 131, 158, 14);
		contentPane.add(lblItemNumber);
		
		txtItemsNumber = new JTextField();
		txtItemsNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				 char c = e.getKeyChar();
		          if (!((c >= '0') && (c <= '9') ||
		             (c == KeyEvent.VK_BACK_SPACE) ||
		             (c == KeyEvent.VK_DELETE))) {
		            getToolkit().beep();
		            e.consume();
		          }
			}
			@Override
			public void keyReleased(KeyEvent e) {
				isValidItemNumbers();
			}
		});
		txtItemsNumber.setColumns(10);
		txtItemsNumber.setBounds(203, 129, 86, 20);
		contentPane.add(txtItemsNumber);
		
		JLabel lblProvider = new JLabel("Seleccione el distribuidor:");
		lblProvider.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProvider.setBounds(35, 181, 158, 14);
		contentPane.add(lblProvider);
		
		rdbtnProvider1 = new JRadioButton("Cofarma");
		rdbtnProvider1.setSelected(true);
		rdbtnProvider1.setBounds(203, 178, 109, 23);
		contentPane.add(rdbtnProvider1);
		
		rdbtnProvider2 = new JRadioButton("Empsephar");
		rdbtnProvider2.setBounds(203, 204, 109, 23);
		contentPane.add(rdbtnProvider2);
		
		rdbtnProvider3 = new JRadioButton("Cemefar");
		rdbtnProvider3.setBounds(203, 230, 109, 23);
		contentPane.add(rdbtnProvider3);
		

		btnGroup.add(rdbtnProvider1);
		btnGroup.add(rdbtnProvider2);
		btnGroup.add(rdbtnProvider3);
		
		JLabel lblSucursal = new JLabel("Entrega en sucursal:");
		lblSucursal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSucursal.setBounds(326, 181, 119, 14);
		contentPane.add(lblSucursal);
		
		chckbxMain = new JCheckBox("Principal");
		chckbxMain.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isValidAddress();
			}
		});
		chckbxMain.setBounds(480, 178, 97, 23);
		contentPane.add(chckbxMain);
		
		chckbxAlternative = new JCheckBox("Secundaria");
		chckbxAlternative.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isValidAddress();
			}
		});
		chckbxAlternative.setBounds(480, 204, 97, 23);
		contentPane.add(chckbxAlternative);
		
		lblMedicineNameCheck.setBounds(293, 85, 19, 14);
		contentPane.add(lblMedicineNameCheck);
		

		lblItemNumberCheck.setBounds(293, 132, 19, 14);
		contentPane.add(lblItemNumberCheck);
		
		lblAddressCheck.setBounds(455, 181, 19, 14);
		contentPane.add(lblAddressCheck);
	}
	
	/***
	 * Método para validar todos los campos del formulario
	 */
	public boolean validateForm() {
		
		//Validacion campo de nombre de medicina
		if(isValidateMedicineName() && isValidItemNumbers() && isValidAddress()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean isValidateMedicineName() {
		
		boolean isValid = true;
		if(txtMedicineName.getText().isEmpty()) {
			isValid = false;
			lblMedicineNameCheck.setToolTipText("El campo no puede estar vacío");
			lblMedicineNameCheck.setIcon(INFO_ICON);
		}else {
			lblMedicineNameCheck.setToolTipText("");
			lblMedicineNameCheck.setIcon(CHECK_ICON);
		}
		return isValid;
	}
	
	public boolean isValidItemNumbers() {
		
		boolean isValid = true;
		if(txtItemsNumber.getText().isEmpty()) {
			isValid = false;
			lblItemNumberCheck.setToolTipText("El campo no puede estar vacío");
			lblItemNumberCheck.setIcon(INFO_ICON);
		}else {
			lblItemNumberCheck.setToolTipText("");
			lblItemNumberCheck.setIcon(CHECK_ICON);
		}
		return isValid;
	}
	
	public boolean isValidAddress() {
		
		boolean isValid = true;
		if(chckbxAlternative.isSelected() || chckbxMain.isSelected()) {
			lblAddressCheck.setToolTipText("");
			lblAddressCheck.setIcon(CHECK_ICON);
		}else {
			lblAddressCheck.setToolTipText("El campo no puede estar vacío");
			lblAddressCheck.setIcon(INFO_ICON);
			isValid = false;
		}
		return isValid;
	}
	
	public String getSelectedProvider() {
		
		if(rdbtnProvider1.isSelected())
			return rdbtnProvider1.getText();
		else if (rdbtnProvider2.isSelected())
			return rdbtnProvider2.getText();
		else if (rdbtnProvider3.isSelected())
			return rdbtnProvider3.getText();
		else 
			return "No se ha seleccionado proveedor";
	}
	
	
}
