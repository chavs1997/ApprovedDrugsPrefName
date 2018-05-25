package forer.drugs;

import java.awt.*;
import javax.swing.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("serial")
public class DrugView extends JFrame {
	Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.ebi.ac.uk")
			.addConverterFactory(GsonConverterFactory.create()).build();
	DrugService service = retrofit.create(DrugService.class);

	DrugController controller = new DrugController(service, this);
	JLabel species = new JLabel();
	JLabel id = new JLabel();
	JLabel formula = new JLabel();
	JLabel weight = new JLabel();
	JLabel rings = new JLabel();
	JLabel image = new JLabel();
	
	
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

		setTitle("Approved Drugs");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
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
		mainPanel.add(topPanel, BorderLayout.WEST);
		mainPanel.add(scroll, BorderLayout.CENTER);
		add(mainPanel);
	}

	public static void main(String[] args) {
		new DrugView().setVisible(true);
	}

}
