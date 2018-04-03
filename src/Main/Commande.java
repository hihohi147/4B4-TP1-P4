package Main;


public class Commande {

	

	private String client;
	private String nomPlat;
	private int quantite;
	
	public Commande (String client,String plat,int quantite){
		this.client = client;
		this.nomPlat = plat;
		this.quantite = quantite;
		
	}
	
	public void setClient(String client){
		this.client = client;
	}
	
	public String getClient(){
		return this.client;
	}
	
	public void setPlat(String nomPlat){
		this.nomPlat = nomPlat;
	}
	
	public String getPlat(){
		return this.nomPlat;
	}
	
	public void setQuantite (int quantite){
		this.quantite = quantite;
	}
	
	public int getQuantite(){
		return this.quantite;
	}
}



