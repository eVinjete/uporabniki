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
    @Path("test")
    public Response getTest(){
        return Response.ok().build();
    }

    @GET
    public Response getAllUporabniki(@QueryParam("user") String userEmail) {
        if(userEmail == null) return Response.status(Response.Status.FORBIDDEN).build();

        List<Uporabnik> user = uporabnikBean.getUporabnikFromEmail(userEmail);
        if(user.get(0).getType() < 2){
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        List<Uporabnik> uporabniki = uporabnikBean.getUporabniki();
        return Response.ok(uporabniki).build();
    }

    @GET
    @Path("/uporabnik/{uporabnikId}")
    public Response getUporabnikID(@PathParam("uporabnikId") String uporabnikId, @QueryParam("user") String userEmail) {
        if(userEmail == null) return Response.status(Response.Status.FORBIDDEN).build();

        List<Uporabnik> user = uporabnikBean.getUporabnikFromEmail(userEmail);
        if(user.get(0).getType() < 2){
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Uporabnik uporabnik = uporabnikBean.getUporabnik(uporabnikId);
        return uporabnik != null
                ? Response.ok(uporabnik).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/uporabnik/")
    public Response getUporabnikNSE(@QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("email") String email, @QueryParam("user") String userEmail) {
        if(userEmail == null) return Response.status(Response.Status.FORBIDDEN).build();
        if(name == null || surname == null || email == null) return Response.status(Response.Status.BAD_REQUEST).build();

        List<Uporabnik> user = uporabnikBean.getUporabnikFromEmail(userEmail);
        if(user.get(0).getType() < 2){
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        List<Uporabnik> uporabnik = uporabnikBean.getUporabnikFromNameSurnameEmail(name,
                surname,
                email);
        return uporabnik != null
                ? Response.ok(uporabnik).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/email/")
    public Response getUporabnikE(@QueryParam("email") String email, @QueryParam("user") String userEmail) {
        if(userEmail == null) return Response.status(Response.Status.FORBIDDEN).build();
        if(email == null) return Response.status(Response.Status.BAD_REQUEST).build();

        List<Uporabnik> user = uporabnikBean.getUporabnikFromEmail(userEmail);
        if(user.get(0).getType() < 2){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        List<Uporabnik> uporabnik = uporabnikBean.getUporabnikFromEmail(email);
        return uporabnik != null
                ? Response.ok(uporabnik).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewUporabnik(Uporabnik uporabnik) {

        if (uporabnik.getName() == null || uporabnik.getSurname() == null || uporabnik.getEmail() == null || uporabnik.getType() == null) {
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
