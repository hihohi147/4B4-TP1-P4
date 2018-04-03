package Main;

public class Plat {
	private String nom;
	private double prix;
	
	
	public Plat(String nom, double prix){
		
		this.nom = nom;
		
		this.prix = prix;
		
	}
	
	public void setNom(String nom){
		
		this.nom = nom;
		
	}
	
public String getNom(){
		
		return this.nom;
		
	}
	
	public void setPrix(double prix){
		
		this.prix = prix;
		
	}
	
	
	public double getPrix(){
		
		return this.prix;
		
	}
	
}

