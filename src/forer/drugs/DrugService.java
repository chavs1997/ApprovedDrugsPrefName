package forer.drugs;

import java.awt.image.BufferedImage;
import retrofit2.Call;
import retrofit2.http.*;

public interface DrugService {

	String drugURL = "/chembl/api/data/molecule?max_phase=4&format=json";
	

	@GET(drugURL)
	Call<DrugFeed> getApprovedDrugs();

}
