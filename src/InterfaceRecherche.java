import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InterfaceRecherche extends JFrame implements ActionListener{

	private JPanel container = new JPanel();
	private JLabel ch1 = new JLabel();
	private JLabel ch2 = new JLabel();
	private JPanel c1 = new JPanel();	
	private JPanel c2 = new JPanel();
	private JTextField champ1 = new JTextField(20);
	private JComboBox combo = new JComboBox();
	private JButton recherche = new JButton("Recherche");
	private JButton annuler = new JButton("Annuler");
	private JPanel button = new JPanel();

	public InterfaceRecherche(){
		this.setSize(500, 200);
		this.setTitle("Recherche");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//On initialise le conteneur avec tous les composants
		initComposant();
		//On ajoute le conteneur
		this.setContentPane(container);
		this.setVisible(true);
	}

	public void initComposant(){
		ch1.setText("Entrer votre recherche");
		ch1.setSize(240, 40);
		ch2.setText("");
		ch2.setSize(240, 40);
		champ1.setText("");
		champ1.setSize(300, 40);
		combo.addItem("Classes");
		combo.addItem("Properties");
		combo.setPreferredSize(new Dimension(150, 20));
		button.add(recherche);
		button.add(annuler);

		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		c1.add(ch1);
		c1.add(ch2);
		container.add(c1);
		c2.add(champ1);
		c2.add(combo);
		container.add(c2);
		container.add(button);
		recherche.addActionListener(this);
		annuler.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String fichierC = "C:/Users/Eric/workspaceProjet/Protege/classesDansLOV-21052014.n3";
		String fichierP = "C:/Users/Eric/workspaceProjet/Protege/propertiesDansLOV-210514.n3";
		BufferedReader buff;
		
		if(source == recherche){
			try{
				if(combo.getSelectedIndex() == 0){
					buff = new BufferedReader(new FileReader(fichierC));
				}else{
					buff = new BufferedReader(new FileReader(fichierP));
				}
				try {
					String line;
					String oldLine = "";
					String newLine = "";
					
					line = buff.readLine();
					while (line != null) {
						newLine = buff.readLine();
						//System.out.println(line);
						if(line.contains(champ1.getText()) && oldLine.contains(champ1.getText())){
							System.out.println("\n");
						}else if (line.contains(champ1.getText())){
							System.out.println(oldLine);
							System.out.println(line);
							System.out.println(newLine);
						}
						oldLine = line;
						line = newLine;
					}
				} finally {
					buff.close();
				}
			} catch (IOException ioe) {
				// erreur de fermeture des flux
				System.out.println("Erreur --" + ioe.toString());
			}
		} else if(source == annuler){
			this.dispose();
		}
	}
}
