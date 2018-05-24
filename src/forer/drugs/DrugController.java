package forer.drugs;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import retrofit2.*;

public class DrugController {

	private DrugView view;
	private DrugService service;
	DrugFeed feed;

	public DrugController(DrugService service, DrugView view) {
		this.view = view;
		this.service = service;
	}

	public void requestDrugFeed() {
		service.getApprovedDrugs("/chembl/api/data/molecule?max_phase=4&format=json").enqueue(new Callback<DrugFeed>() {

			@Override
			public void onFailure(Call<DrugFeed> call, Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onResponse(Call<DrugFeed> call, Response<DrugFeed> response) {
				feed = response.body();
				for (Molecule mol : feed.getMolecules()) {
					view.getDrugIDs().addItem(mol.getMolId());
				}
			}

		});
	}

	public void fillInData(String selectedItem, JLabel name, JLabel formula, JLabel weight, JLabel species,
			JLabel rings, JLabel image) throws IOException {
		Molecule mol = feed.getMolecule(selectedItem);
		name.setText(mol.getName());
		formula.setText(mol.getProperties().getFormula());
		weight.setText(mol.getProperties().getWeight());
		species.setText(mol.getProperties().getSpecies());
		rings.setText(String.valueOf(mol.getProperties().getRings()));
		fillInImage(selectedItem, image);

	}

	private void fillInImage(String selectedItem, JLabel image) throws IOException {
		URL url = new URL("https://www.ebi.ac.uk/chembl/api/data/image/" + selectedItem.trim() + "?format=png");
		BufferedImage bufImage = ImageIO.read(url);
		Icon icon = new ImageIcon(bufImage);
		image.setIcon(icon);

	}

}
