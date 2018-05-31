package forer.drugs;

import java.awt.*;
import javax.swing.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("serial")
public class DrugView extends JFrame {
	Retrofit retrofit;
	DrugService service;

	DrugController controller;
	JLabel species;
	JLabel id;
	JLabel formula;
	JLabel weight;
	JLabel rings;
	JLabel image;

	public final JLabel getID() {
		return id;
	}

	public final JLabel getImage() {
		return image;
	}

	public final JLabel getSpecies() {
		return species;
	}

	public final JLabel getFormula() {
		return formula;
	}

	public final JLabel getWeight() {
		return weight;
	}

	public final JLabel getRings() {
		return rings;
	}

	public DrugView() {
		retrofit = new Retrofit.Builder().baseUrl("https://www.ebi.ac.uk")
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(DrugService.class);

		controller = new DrugController(service, this);
		species = new JLabel();
		id = new JLabel();
		formula = new JLabel();
		weight = new JLabel();
		rings = new JLabel();
		image = new JLabel();

		setTitle("Approved Drugs");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(255, 204, 255));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.setBackground(new Color(255, 229, 204));
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.fill = GridBagConstraints.BOTH;
		constraint.insets = new Insets(3, 2, 3, 2);

		topPanel.add(new JLabel("Search for a drug: "), constraint);
		constraint.gridy = 1;
		topPanel.add(new JLabel("   ChEMBL ID: "), constraint);
		constraint.gridy = 2;
		topPanel.add(new JLabel("   Formula: "), constraint);
		constraint.gridy = 3;
		topPanel.add(new JLabel("   Molecular Weight: "), constraint);
		constraint.gridy = 4;
		topPanel.add(new JLabel("   Species: "), constraint);
		constraint.gridy = 5;
		topPanel.add(new JLabel("   Aromatic Rings: "), constraint);

		constraint.gridy = 0;
		constraint.gridx = 1;

		JTextField pref_name = new JTextField();
		topPanel.add(pref_name, constraint);
		constraint.gridy = 1;
		topPanel.add(id, constraint);
		constraint.gridy = 2;
		topPanel.add(formula, constraint);
		constraint.gridy = 3;
		topPanel.add(weight, constraint);
		constraint.gridy = 4;
		topPanel.add(species, constraint);
		constraint.gridy = 5;
		topPanel.add(rings, constraint);
		constraint.gridy = 6;
		JButton button = new JButton("Find Drug");
		topPanel.add(button, constraint);

		button.addActionListener(e -> {
			controller.requestDrugFeed(pref_name.getText());

		});

		JScrollPane scroll = new JScrollPane(image);
		scroll.setBackground(new Color(255, 204, 255));
		mainPanel.add(topPanel, BorderLayout.WEST);
		mainPanel.add(scroll, BorderLayout.CENTER);
		add(mainPanel);
	}

	public static void main(String[] args) {
		new DrugView().setVisible(true);
	}

}
