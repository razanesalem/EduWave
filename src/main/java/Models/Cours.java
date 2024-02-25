package Models;

public class Cours {

    private int id;

    private float prix;

    public Cours(int id, float prix) {
        this.id = id;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

}
