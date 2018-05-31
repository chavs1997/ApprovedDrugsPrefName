package forer.drugs;

public class Molecule {

	private String molecule_chembl_id;
	private Properties molecule_properties;
	private String pref_name;

	
	
	public Molecule(String molecule_chembl_id, Properties molecule_properties, String pref_name) {
		super();
		this.molecule_chembl_id = molecule_chembl_id;
		this.molecule_properties = molecule_properties;
		this.pref_name = pref_name;
	}

	public final String getMolId() {
		return molecule_chembl_id;
	}

	public final Properties getProperties() {
		return molecule_properties;
	}

	public final String getName() {
		if (pref_name == null) {
			return "";
		} else {
			return pref_name;
		}
	}

}
