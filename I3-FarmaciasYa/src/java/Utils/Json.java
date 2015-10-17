package Utils;

import java.util.List;

/**
 * @author martingonzalez
 */
public class Json {
    private String place_id;
    private String licence;
    private String osm_type;
    private String osm_id;
    private List<String> boundingbox;
    private String lat;
    private String lon;
    private String display_name;
    private String clas;
    private String type;
    private double importance;

    /**
     * @return the place_id
     */
    public String getPlace_id() {
        return place_id;
    }

    /**
     * @param place_id the place_id to set
     */
    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    /**
     * @return the licence
     */
    public String getLicence() {
        return licence;
    }

    /**
     * @param licence the licence to set
     */
    public void setLicence(String licence) {
        this.licence = licence;
    }

    /**
     * @return the osm_type
     */
    public String getOsm_type() {
        return osm_type;
    }

    /**
     * @param osm_type the osm_type to set
     */
    public void setOsm_type(String osm_type) {
        this.osm_type = osm_type;
    }

    /**
     * @return the osm_id
     */
    public String getOsm_id() {
        return osm_id;
    }

    /**
     * @param osm_id the osm_id to set
     */
    public void setOsm_id(String osm_id) {
        this.osm_id = osm_id;
    }

    /**
     * @return the boundingbox
     */
    public List<String> getBoundingbox() {
        return boundingbox;
    }

    /**
     * @param boundingbox the boundingbox to set
     */
    public void setBoundingbox(List<String> boundingbox) {
        this.boundingbox = boundingbox;
    }

    /**
     * @return the lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public String getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     * @return the display_name
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * @param display_name the display_name to set
     */
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    /**
     * @return the clas
     */
    public String getClas() {
        return clas;
    }

    /**
     * @param clas the clas to set
     */
    public void setClas(String clas) {
        this.clas = clas;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the importance
     */
    public double getImportance() {
        return importance;
    }

    /**
     * @param importance the importance to set
     */
    public void setImportance(double importance) {
        this.importance = importance;
    }
    
}
