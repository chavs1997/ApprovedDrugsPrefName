package forer.drugs;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("serial")
public class DrugView extends JFrame {
	Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.ebi.ac.uk")
			.addConverterFactory(GsonConverterFactory.create()).build();
	DrugService service = retrofit.create(DrugService.class);

	private JComboBox<String> drugIDs = new JComboBox<String>();

	public final JComboBox<String> getDrugIDs() {
		return drugIDs;
	}

	DrugController controller = new DrugController(service, this);

	public DrugView() {

		setTitle("Approved Drugs");
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		controller.requestDrugFeed();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.fill = GridBagConstraints.BOTH;
		constraint.insets = new Insets(3, 2, 3, 2);

		topPanel.add(new JLabel("Select a drug ID: "), constraint);
		constraint.gridy = 1;
		topPanel.add(new JLabel("   Name: "), constraint);
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

		topPanel.add(drugIDs, constraint);
		constraint.gridy = 1;
		JLabel name = new JLabel();
		topPanel.add(name, constraint);
		constraint.gridy = 2;
		JLabel formula = new JLabel();
		topPanel.add(formula, constraint);
		constraint.gridy = 3;
		JLabel weight = new JLabel();
		topPanel.add(weight, constraint);
		constraint.gridy = 4;
		JLabel species = new JLabel();
		topPanel.add(species, constraint);
		constraint.gridy = 5;
		JLabel rings = new JLabel();
		topPanel.add(rings, constraint);

		JLabel image = new JLabel();

		drugIDs.addActionListener(e -> {
			try {
				controller.fillInData((String) drugIDs.getSelectedItem(), name, formula, weight, species, rings, image);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		mainPanel.add(topPanel);
		mainPanel.add(image);

		add(mainPanel);
	}

	public static void main(String[] args) {
		new DrugView().setVisible(true);
	}

}
