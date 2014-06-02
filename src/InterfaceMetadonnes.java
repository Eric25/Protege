import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InterfaceMetadonnes extends JFrame implements ActionListener{
	private JPanel container = new JPanel();
	private JDialog popUp = new JDialog();
	private JLabel ch1 = new JLabel();
	private JLabel ch2 = new JLabel();
	private JLabel ch3 = new JLabel();
	private JLabel ch4 = new JLabel();
	private JLabel ch5 = new JLabel();
	private JLabel ch6 = new JLabel();
	private JLabel ch7 = new JLabel();
	private JLabel ch8 = new JLabel();
	private JLabel ch9 = new JLabel();
	private JTextField nom = new JTextField();
	private JTextField uri = new JTextField();
	private JTextField langue = new JTextField();
	private JTextField createur = new JTextField();
	private JTextField prefixe = new JTextField();
	private JTextField contributeur = new JTextField();
	private JTextField state = new JTextField();
	private JTextField date = new JTextField();
	private JTextField dateI = new JTextField();
	private JLabel pop = new JLabel();
	private JButton continuer = new JButton("Creer RDF");
	private JButton annuler = new JButton("Annuler");
	private JButton ok = new JButton("Ok");
	private JPanel button = new JPanel();

	public InterfaceMetadonnes(){
		this.setSize(240, 500);
		this.setTitle("Champs a remplir");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//On initialise le conteneur avec tous les composants
		initComposant();
		//On ajoute le conteneur
		this.setContentPane(container);
		this.setVisible(true);
	}

	private void initComposant() {
		ch1.setText("Titre");
		ch2.setText("URI");
		ch3.setText("Language");
		ch4.setText("Creator");
		ch5.setText("Prefixe");
		ch6.setText("Contributeur(s)");
		ch7.setText("Etat de l'ontologie (ongoing, finished)");
		ch8.setText("Date (aaaa-yy-dd)");
		ch9.setText("Date of first publication");
		continuer.setSize(80, 50);
		annuler.setSize(80, 50);
		button.setLayout(new GridLayout(0,2));
		button.add(continuer);
		button.add(annuler);

		container.setLayout(new GridLayout(0,1));
		container.add(ch5);
		container.add(prefixe);
		container.add(ch1);
		container.add(nom);
		container.add(ch2);
		container.add(uri);
		container.add(ch3);
		container.add(langue);
		container.add(ch4);
		container.add(createur);
		container.add(ch6);
		container.add(contributeur);
		container.add(ch7);
		container.add(state);
		container.add(ch8);
		container.add(date);
		container.add(ch9);
		container.add(dateI);
		container.add(button);
		continuer.addActionListener(this);
		annuler.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String lang;
		String etat = null;
		String dateM = null;
		if(source == continuer){
			if(nom.getText().equals("") || uri.getText().equals("") || langue.getText().equals("")  || prefixe.getText().equals("") || contributeur.getText().equals("") || state.getText().equals("") || date.getText().equals("")){
				afficherPopUp();
			} else {
				if(langue.getText().toLowerCase().equals("anglais")|| langue.getText().toLowerCase().equals("english")){
					lang = "en";
				} else if(langue.getText().toLowerCase().equals("francais")|| langue.getText().toLowerCase().equals("french")){
					lang = "fr";
				} else {
					lang = "ot";
				}
				if(state.getText().toLowerCase().equals("finished")){
					etat = "Finished";
				} else if(state.getText().toLowerCase().equals("ongoing")){
					dateM = date.getText();
					etat = "Ongoing";
				}
				final String chemin = "C:/users/Eric/workspaceProjet/Protege/test.txt";
				final File fichier =new File(chemin); 
				try {
					fichier .createNewFile();
					final FileWriter writer = new FileWriter(fichier);
					try {
						writer.write("<!DOCTYPE uridef[\n");
						writer.write("<!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n");
						writer.write("<!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\">\n");
						writer.write("<!ENTITY owl \"http://www.w3.org/2002/07/owl#\">\n");
						writer.write("<!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\">\n");
						writer.write("<!ENTITY security \"http://securitytoolbox.appspot.com/securityMain#\">\n");
						writer.write("<!ENTITY algo \"http://securitytoolbox.appspot.com/securityAlgorithms#\">\n");
						writer.write("<!ENTITY dcterms \"http://purl.org/dc/terms/\">\n");
						writer.write("<!ENTITY assurance \"http://securitytoolbox.appspot.com/securityAssurance.owl#\">\n");
						writer.write("<!ENTITY skos \"http://www.w3.org/2004/02/skos/core#\">\n");
						writer.write("<!ENTITY dc \"http://purl.org/dc/elements/1.1/\">\n");
						writer.write("<!ENTITY foaf \"http://xmlns.com/foaf/0.1/\">\n"); 
						writer.write("]>\n");
						writer.write("<rdf:RDF\n");
						writer.write("xmlns =\"&" + prefixe.getText() + ";\"\n");
						writer.write("xmlns:algo =\"&" + prefixe.getText() + ";\"\n");
						writer.write("xml:base =\"&" + prefixe.getText() + ";\"\n");
						writer.write("xmlns:rdf =\"&rdf;\"\n");
						writer.write("xmlns:rdfs =\"&rdfs;\"\n");
						writer.write("xmlns:owl =\"&owl;\"\n");
						writer.write("xmlns:dc =\"&dc;\"\n");
						writer.write("xmlns:xsd =\"&xsd;\"\n");
						writer.write("xmlns:security =\"&security;\"\n");
						writer.write("xmlns:dcterms=\"&dcterms;\"\n");
						writer.write("xmlns:skos=\"&skos;\"\n");
						writer.write("xmlns:foaf=\"&foaf;\"\n");
						writer.write("xmlns:assurance =\"&assurance;\"\n");
						writer.write("xmlns:vann=\"http://purl.org/vocab/vann/\"\n");
						writer.write("xmlns:cc=\"http://creativecommons.org/ns#\"\n");
						writer.write("xmlns:vs=\"http://www.w3.org/2003/06/sw-vocab-status/ns#\">\n");
						writer.write("<owl:Ontology rdf:about=\"&"+ nom.getText() +";\">\n");
						//writer.write("<rdfs:comment> An ontology to describe various cryptographic algorithms</rdfs:comment>\n");
						writer.write("<rdf:type rdf:resource=\"http://purl.org/vocommons/voaf#Vocabulary\"/>\n");
						writer.write("<dc:title xml:lang=\""+ lang +"\">"+ nom.getText() +"</dc:title>\n");
						//writer.write("<skos:historyNote xml:lang=\""+ lang +"\">Ontology extracted from the paper Security Ontology for Annotating Resources. [Kim et al. 2005] (See APPENDIX D. OWL Representations of the NRL Security Ontology) Security ontology to faciliate web service description and discovery.</skos:historyNote>\n");
						//writer.write("<dc:description xml:lang=\""+ lang +"\">An ontology to describe various cryptographic algorithms</dc:description>\n");
						writer.write("<dcterms:source rdf:resource=\"http://www.dtic.mil/cgi-bin/GetTRDoc?AD=ADA437938\"/>\n"); 
						writer.write("<dcterms:creator>\n");
						writer.write("<foaf:Person>\n");
						writer.write("<foaf:name>" + nom.getText() + "</foaf:name>\n");
						writer.write("</foaf:Person>\n");
						writer.write("</dcterms:creator>\n");
						writer.write("<dcterms:creator>\n");
						writer.write("<foaf:Person>\n");
						writer.write("<foaf:name>" + createur.getText() + "</foaf:name>\n");
						writer.write("</foaf:Person>\n");
						writer.write("</dcterms:creator>\n");
						//writer.write("<dcterms:creator>\n");
						//writer.write("<foaf:Person rdf:about=\"mailto:mkang@itd.nrl.navy.mil\">\n");
						//writer.write("<foaf:name>Myong Kang</foaf:name>\n");
						//writer.write("</foaf:Person>\n");
						//writer.write("</dcterms:creator>\n");
						writer.write("<dc:contributor>\n");
						writer.write("<foaf:Person>\n");
						writer.write("<foaf:name>"+ contributeur.getText() +"</foaf:name>\n");
						writer.write("</foaf:Person>\n");
						writer.write("</dc:contributor>\n");
						writer.write("<dcterms:issued rdf:datatype=\"http://www.w3.org/2001/XMLSchema#date\">"+ dateI.getText() +"</dcterms:issued>\n");
						writer.write("<dcterms:modified rdf:datatype=\"http://www.w3.org/2001/XMLSchema#date\">"+ dateM +"</dcterms:modified>\n"); 
						writer.write("<owl:versionInfo rdf:datatype=\"http://www.w3.org/2001/XMLSchema#decimal\">0.2</owl:versionInfo>\n");
						//writer.write("<vs:term_status>"+ etat +"</vs:term_status>\n");
						//writer.write("<cc:license rdf:resource=\"http://creativecommons.org/licenses/by/3.0/\"/>\n"); 
						writer.write("<vann:preferredNamespacePrefix>" + prefixe.getText() + "</vann:preferredNamespacePrefix>\n");
						writer.write("<vann:preferredNamespaceUri>" + uri.getText() + "</vann:preferredNamespaceUri>\n");
						writer.write("</owl:Ontology>\n");
					} finally {
						writer.close();
					}
				} catch (Exception ep) {
					System.out.println("Impossible de creer le fichier");
				}
			}
		} else if(source == annuler){
			this.dispose();
		} else if(source == ok){
			popUp.dispose();
		}

	}

	private void afficherPopUp() {
		pop.setText("Les champs ne sont pas tous remplis");
		popUp.setLayout(new BorderLayout());
		popUp.add(pop , BorderLayout.CENTER);
		popUp.add(ok, BorderLayout.SOUTH);
		popUp.setSize(250 , 100);
		popUp.setLocationRelativeTo(container);
		ok.addActionListener(this);
		popUp.setVisible(true);
	}
}

