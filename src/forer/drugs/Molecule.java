package forer.drugs;

public class Molecule {

	private String molecule_chembl_id;
	private Properties molecule_properties;
	private String pref_name;

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
