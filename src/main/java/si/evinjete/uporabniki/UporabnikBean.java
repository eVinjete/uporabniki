package si.evinjete.uporabniki;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@SessionScoped
public class UporabnikBean implements Serializable {

    @Inject
    private UporabnikService uporabnikBean;

    public UporabnikBean(){}

    public UporabnikBean(Integer id, String location, String direction){
        this.id = id;
        this.location = location;
        this.timestamp = new Date();
        this.direction = direction;
    }

    private Integer id;
    private String location;
    private Date timestamp;
    private String direction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void  setLocation(String location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void addUporabnik() {
        Uporabnik uporabnik = new Uporabnik();
        uporabnik.setId(this.id);
        uporabnik.setTimestamp(this.timestamp);

        //TODO

        //uporabnikBean.addNewUporabnik(uporabnik);

        this.setId(null);
        this.setDirection(null);
        this.setTimestamp(null);
        this.setLocation(null);
    }

//    public List<Uporabnik> getUporabniki() {
//        return uporabnikBean.getUporabniki();
//    }

    @Override
    public String toString() {
        //TODO

        return "Uporabnik {\n" +
                "  id='" + id + "',\n" +
                "  location='" + location + "',\n" +
                "  direction='" + direction + "'\n" +
                "  timestamp='" + timestamp + "'\n" +
                "}";
    }
}
