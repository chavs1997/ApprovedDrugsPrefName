package forer.drugs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.squareup.picasso.Picasso;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrugMain {

	public static void main(String[] args) throws IOException {
		BufferedImage image;
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.ebi.ac.uk")
				.addConverterFactory(GsonConverterFactory.create()).build();
		DrugService service = retrofit.create(DrugService.class);

		Call<DrugFeed> call = service.getApprovedDrugs();
		call.enqueue(new Callback<DrugFeed>() {

			@Override
			public void onFailure(Call<DrugFeed> call, Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onResponse(Call<DrugFeed> call, Response<DrugFeed> response) {
				DrugFeed feed = response.body();
				System.out.println(feed.getMolecules().get(3).getName());
				System.out.println(feed.getMolecules().get(3).getProperties().getWeight());
				
				System.exit(0);
			}

		});
		
		URL url = new URL("https://www.ebi.ac.uk/chembl/api/data/image/CHEMBL4?format=png");
		image = ImageIO.read(url);

		ImageIO.write(image, "png", new File("src\\forer\\drugs\\drugImage.png"));
				
	}

}
