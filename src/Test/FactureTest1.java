package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Main.Facture;

public class FactureTest1 {
	private static final double TAXES = 0.15;
	
	@Test
	public final void testprixTotalString() {
	String resultat = "10.00";
	assertEquals(resultat, Facture.prixTotalEnString(10));
		
		
	}
	@Test
	public final void testTaxesString() {
	String resultat = "1.15";
	assertEquals(resultat, Facture.taxeEnString(1.15));
	}
	@Test 
	public final void testErreur() {
	String resultat = "Doit être supérieur à 0" ;
	assertEquals(resultat, Facture.prixTotalEnString(-10));
	}
}
