package forer.drugs;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

import retrofit2.*;

public class DrugController {

	private DrugView view;
	private DrugService service;
	DrugFeed feed;

	public DrugController(DrugService service, DrugView view) {
		this.view = view;
		this.service = service;
	}

	public void requestDrugFeed(String pref_name) {
		service.getApprovedDrugs(
				"/chembl/api/data/molecule?max_phase=4&format=json&pref_name=" + pref_name.trim().toUpperCase())
				.enqueue(new Callback<DrugFeed>() {

					@Override
					public void onFailure(Call<DrugFeed> call, Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onResponse(Call<DrugFeed> call, Response<DrugFeed> response) {
						feed = response.body();
						if (!feed.getMolecules().isEmpty()) {
							try {
								fillInData(pref_name);
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							view.getImage().setIcon(null);
							view.getImage().setText(
									"Sorry; the drug you entered doesn't appear in the approved drugs database. Please check your spelling and try again.");
						}
					}

				});
	}

	public void fillInData(String selectedItem) throws IOException {
		Molecule mol = feed.getMolecules().get(0);
		view.getID().setText(mol.getMolId());
		view.getFormula().setText(mol.getProperties().getFormula());
		view.getWeight().setText(mol.getProperties().getWeight());
		view.getSpecies().setText(mol.getProperties().getSpecies());
		view.getRings().setText(String.valueOf(mol.getProperties().getRings()));
		fillInImage();

	}

	private void fillInImage() throws IOException {
		view.getImage().setText("");
		String id = feed.getMolecules().get(0).getMolId();
		URL url = new URL("https://www.ebi.ac.uk/chembl/api/data/image/" + id + "?format=png");
		BufferedImage bufImage = ImageIO.read(url);
		Icon icon = new ImageIcon(bufImage);
		view.getImage().setIcon(icon);

	}

}
