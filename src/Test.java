 import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		{
			List<String> listClients = new ArrayList<String>(), listPlats = new ArrayList<String>(),
					listCommandes = new ArrayList<String>();

			try {
				File file = new File("Test.txt");
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = bufferedReader.readLine()) != "Plats :") {
					listClients.add(line);

				}
				
				fileReader.close();
				System.out.println(Arrays.toString(listClients.toArray()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
