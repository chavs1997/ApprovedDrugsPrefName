package forer.drugs;

import retrofit2.Call;
import retrofit2.http.*;

public interface DrugService {

	@GET
	Call<DrugFeed> getApprovedDrugs(@Url String drugURL);

}
