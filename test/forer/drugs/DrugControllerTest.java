package forer.drugs;

import java.io.IOException;
import java.util.*;
import javax.swing.JLabel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DrugControllerTest {

	@Test
	void testFillInData() throws IOException {
		DrugView view = Mockito.mock(DrugView.class);
		DrugService service = Mockito.mock(DrugService.class);
		DrugController controller = new DrugController(service, view);
		Properties prop = new Properties(1, "(C17H19N03)2 H2S04 5H20", "758.85", "Pain Relief");
		Molecule mol = new Molecule("5288826", prop, "Morphine Sulfate");
		List<Molecule> list = new ArrayList<Molecule>();
		list.add(mol);
		DrugFeed feed = new DrugFeed(list);
		JLabel id = Mockito.mock(JLabel.class);
		JLabel formula = Mockito.mock(JLabel.class);
		JLabel weight = Mockito.mock(JLabel.class);
		JLabel species = Mockito.mock(JLabel.class);
		JLabel rings = Mockito.mock(JLabel.class);
		controller.fillInData(feed, id, formula, weight, species, rings);
		Mockito.verify(id).setText("5288826");
		Mockito.verify(rings).setText("1");
	}

}
