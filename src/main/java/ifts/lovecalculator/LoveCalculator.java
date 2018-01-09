package ifts.lovecalculator;

import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lovecalculator")
@Produces(MediaType.APPLICATION_JSON)
public class LoveCalculator {
    
    private int n;
    
    public LoveCalculator(int n) {
        this.n = n;
    }
    
    @GET
    public Response calcolaAffinità(
        @QueryParam("nome1") Optional<String> nome1,
        @QueryParam("nome2") Optional<String> nome2
    ) {
        // Se uno dei due nomi non è stato inserito
        if(!nome1.isPresent() || !nome2.isPresent())
            // Restituisce un opportuno messaggio d'errore
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("nome1 e nome2 devono essere entrambi presenti.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        // Se nome1 è vuoto
        if(nome1.get().isEmpty())
            // Restituisce un opportuno messaggio d'errore
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("nome1 non può essere vuoto.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        // Se nome2 è vuoto
        if(nome2.get().isEmpty())
            // Restituisce un opportuno messaggio d'errore
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("nome2 non può essere vuoto.")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        
        // Calcola l'affinità tra "nome1" e "nome2" con la funzione indicata
        int numero1 = Math.abs(nome1.hashCode())*n;
        int numero2 = Math.abs(nome2.hashCode())*n;
        int aff = (numero1 + numero2) % (this.n + 1);
        // Restituisce il risultato calcolato
        RisultatoLoveCalculator ris = new RisultatoLoveCalculator(nome1.get(),nome2.get(),aff);
        return Response.ok().entity(ris).build();
    }
    
}
