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

	public DrugController(DrugService service, DrugView view) {
		this.view = view;
		this.service = service;
	}

	public void requestDrugFeed(String pref_name) {
		service.getApprovedDrugs(pref_name.trim().toUpperCase()).enqueue(new Callback<DrugFeed>() {

			@Override
			public void onFailure(Call<DrugFeed> call, Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onResponse(Call<DrugFeed> call, Response<DrugFeed> response) {
				DrugFeed feed = response.body();
				if (!feed.getMolecules().isEmpty()) {
					try {
						fillInData(feed, view.getID(), view.getFormula(), view.getWeight(), view.getSpecies(),
								view.getRings());
						fillInImage(view.getID());
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					view.getImage().setIcon(null);
					view.getImage().setText(
							"Sorry; the drug you entered doesn't appear in the approved drugs database. "
							+ "Please check your spelling and try again.");
				}
			}

		});
	}

	public void fillInData(DrugFeed feed, JLabel id, JLabel formula, JLabel weight, JLabel species, JLabel rings)
			throws IOException {
		Molecule mol = feed.getMolecules().get(0);
		id.setText(mol.getMolId());
		MoleculeProperties molProps = mol.getProperties();
		formula.setText(molProps.getFormula());
		weight.setText(molProps.getWeight());
		species.setText(molProps.getSpecies());
		rings.setText(String.valueOf(molProps.getRings()));

	}

	private void fillInImage(JLabel molId) throws IOException {
		view.getImage().setText("");
		String id = molId.getText().trim();
		URL url = new URL("https://www.ebi.ac.uk/chembl/api/data/image/" + id + "?format=png");
		BufferedImage bufImage = ImageIO.read(url);
		Icon icon = new ImageIcon(bufImage);
		view.getImage().setIcon(icon);

	}

}
