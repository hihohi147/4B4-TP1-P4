import java.awt.TextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Facture {
	private static final String fichier = "Commandes.txt";
	private static final String ERREUR_PRIX = "Le prix du plat ne respecte pas le format demandé";
	private static final double TAXES = 0.15;
	
	public static String setFormatMonetaire (double monetaire) {
		NumberFormat nf = NumberFormat.getInstance(new Locale("en", "CAD"));

		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
	
		String enString = nf.format(monetaire);
		
		return enString;
		
	}
	
	public static double calculerTaxe (double prix) {
	double taxe = prix * TAXES;
	String taxeString = setFormatMonetaire(prix);
	return taxe;
}
	public  static String taxeEnString (double taxe) {
		String taxeString = setFormatMonetaire(taxe);
		return taxeString;
		
	}
public static double calculerPrixT(double prix, double taxe) {
	
	double prixTotal = prix * (1+TAXES);

	return prixTotal;
}
public static  String prixTotalEnString (double prixTotal) {
	String prixTotalEnString = setFormatMonetaire(prixTotal);
	return prixTotalEnString;
	
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader bReader = null;
		FileReader fReader = null;

		String nomFichierFacture = new SimpleDateFormat("yyyy-MM-dd'.txt'").format(new Date());

		nomFichierFacture = "Facture_du_" + nomFichierFacture;
		try (BufferedWriter w = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(nomFichierFacture), "utf-8"))) {
			try {

				String tabClients[] = new String[50];
				Plat tabPlats[] = new Plat[50];
				Commande tabCommandes[] = new Commande[50];

				boolean clients = false;
				boolean plats = false;
				boolean commandes = false;

				int nbrClient = 0;
				int nbrPlat = 0;
				int nbrCommande = 0;

				fReader = new FileReader(fichier);
				bReader = new BufferedReader(fReader);

				String lignecourante;

				while ((lignecourante = bReader.readLine()) != null) {
					if (lignecourante.contains("Clients")) {

						clients = true;

					} else if (lignecourante.contains("Plats")) {

						clients = false;
						plats = true;

					} else if (lignecourante.contains("Commandes")) {

						clients = false;
						plats = false;
						commandes = true;

					}

					if (clients) {

						tabClients[nbrClient] = lignecourante;

						nbrClient++;

					}

					if (plats && !lignecourante.contains("Plats") && !lignecourante.contains("Commandes")
							&& !lignecourante.contains("Fin")) {
						try {
							String[] pSplit = lignecourante.split(" ");
							Plat plat1 = new Plat(pSplit[0], Double.parseDouble(pSplit[1]));
							tabPlats[nbrPlat] = plat1;
							if (plat1.getPrix() < 0) {
								w.write(plat1.getNom());
								w.write("Le prix du plat ne peut être négatif");
								System.out.println("\n" + plat1.getNom());
								System.out.println("Le prix du plat ne peut être négatif");
							} else {
								nbrPlat++;
							}
						} catch (Exception prixIncorrect) {

							w.write(ERREUR_PRIX);
							System.out.println(ERREUR_PRIX);
						}

					}

					if (commandes && !lignecourante.contains("Commandes") && !lignecourante.contains("Fin")) {

						try {

							String[] cSplit = lignecourante.split(" ");
							Commande commande1 = new Commande(cSplit[0], cSplit[1], Integer.parseInt(cSplit[2]));
							tabCommandes[nbrCommande] = commande1;
							if (commande1.getQuantite() < 0) {
								w.write(commande1.getPlat());
								w.write("Le quantité du plat ne peut être négatif");
								System.out.println("\n" + commande1.getPlat());
								System.out.println("Le quantité du plat ne peut être négatif");
							} else {
								nbrCommande++;
							}

						} catch (Exception format) {

							System.out.println(" Le fichier ne respecte pas le format demandé !");

						}

					}

				}

				for (int i = 1; i < nbrClient; i++) {

					double prix = 0;

					for (int j = 0; j < nbrCommande; j++) {

						if (tabClients[i].equals(tabCommandes[j].getClient())) {

							boolean trouve = false;

							for (int c = 0; c <= nbrPlat && !trouve; c++) {
								try {

									if (tabPlats[c].getNom().equals(tabCommandes[j].getPlat())) {

										prix += tabPlats[c].getPrix() * tabCommandes[j].getQuantite();
										trouve = true;

									} else {

										prix += 0;

									}
								} catch (Exception nomIncorrect) {
									w.write("Commande pour" + tabClients[i]
											+ "non trouvé vérifiez la présence du client ou corrigez l'erreur au dessus");
									System.out.println("Commande pour " + tabClients[i]
											+ " non trouvé vérifiez la présence du client ou corrigez l'erreur au dessus\n");
									w.newLine();

								}
							}

						}

					}
					if (prix != 0.0) {
						
						System.out.println("Bienvenue chez Barette! ");
						System.out.println("Factures :");
						System.out.println(tabClients[i] + " : Prix : " + prix + "$" + " + Taxes (" + taxeEnString(calculerTaxe(prix))
								+ "$) = " + prixTotalEnString(calculerPrixT(prix, TAXES)) + "$\n");

						w.write("Bienvenue chez Barette! ");
						w.newLine();
						w.write("Factures : \n");
						w.newLine();
						w.write(tabClients[i] + " : Prix : " + prix + "$" + " + Taxes (" +taxeEnString(calculerTaxe(prix)) + "$) = "
								+ prixTotalEnString(calculerPrixT(prix, TAXES)) + "$");
						w.newLine();
						w.newLine();
					}

				}

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (bReader != null)

						bReader.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Fichier non-trouvé");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
