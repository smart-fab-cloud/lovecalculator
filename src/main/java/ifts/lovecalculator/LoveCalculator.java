package ifts.lovecalculator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/lovecalculator")
@Produces(MediaType.APPLICATION_JSON)
public class LoveCalculator {
    
    private int n;
    
    public LoveCalculator(int n) {
        this.n = n;
    }
    
    @GET
    public RisultatoLoveCalculator calcolaAffinit�(
        @QueryParam("nome1") String nome1,
        @QueryParam("nome2") String nome2
    ) {
        // Calcola l'affinit� tra "nome1" e "nome2" con la funzione indicata
        int numero1 = Math.abs(nome1.hashCode())*n;
        int numero2 = Math.abs(nome2.hashCode())*n;
        int aff = (numero1 + numero2) % (this.n + 1);
        // Restituisce il risultato calcolato
        return new RisultatoLoveCalculator(nome1,nome2,aff);
    }
    
}
