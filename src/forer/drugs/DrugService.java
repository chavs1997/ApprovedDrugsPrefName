package forer.drugs;

import retrofit2.Call;
import retrofit2.http.*;

public interface DrugService {

	@GET("/chembl/api/data/molecule?max_phase=4&format=json")
	Call<DrugFeed> getApprovedDrugs(@Query("pref_name") String prefName);

}
