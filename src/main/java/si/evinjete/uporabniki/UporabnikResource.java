package si.evinjete.uporabniki;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("uporabniki")
public class UporabnikResource {

    @Inject
    private UporabnikService uporabnikBean;

    @GET
    public Response getAllUporabniki() {
        List<Uporabnik> uporabniki = uporabnikBean.getUporabniki();
        return Response.ok(uporabniki).build();
    }

    @GET
    @Path("/uporabnik/{uporabnikId}")
    public Response getUporabnikID(@PathParam("uporabnikId") String uporabnikId) {
        Uporabnik uporabnik = uporabnikBean.getUporabnik(uporabnikId);
        return uporabnik != null
                ? Response.ok(uporabnik).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Path("/email/")
    public Response getUporabnikE(@QueryParam("email") String email) {
        if(email == null) return Response.status(Response.Status.BAD_REQUEST).build();

        List<Uporabnik> uporabnik = uporabnikBean.getUporabnikFromEmail(email);
        return uporabnik != null
                ? Response.ok(uporabnik).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/verify/")
    public Response verifyUporabnikPassword(@QueryParam("user") String userEmail,
                                            @QueryParam("password") String password) {

        if(userEmail == null || password == null) return Response.status(Response.Status.BAD_REQUEST).build();

        List<Uporabnik> user = uporabnikBean.getUporabnikFromEmail(userEmail);
        if(!user.isEmpty() && user.get(0).getPassword().equals(password)){
            return Response.ok(userEmail).build();
        }

        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @POST
    public Response addNewUporabnik(Uporabnik uporabnik) {

        if (uporabnik.getName() == null || uporabnik.getSurname() == null || uporabnik.getEmail() == null || uporabnik.getType() == null || uporabnik.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if(!uporabnikBean.getUporabnikFromEmail(uporabnik.getEmail()).isEmpty()) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        else {
            uporabnik.setTimestamp(new Date());
            uporabnikBean.addNewUporabnik(uporabnik);
        }

        return Response.ok(uporabnik).build();
    }

    @DELETE
    @Path("{uporabnikId}")
    public Response deleteUporabnik(@PathParam("uporabnikId") String uporabnikId) {
        uporabnikBean.deleteUporabnik(uporabnikId);
        return Response.noContent().build();
    }
}
