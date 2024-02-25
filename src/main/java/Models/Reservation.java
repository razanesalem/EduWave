package Models;


import java.sql.Date;

public class Reservation {
    private int id;
    private int id_user;
    private int id_cours;

    private float prix;
    private Date date ;


    public Reservation(int id, int id_user, int id_cours, float prix, Date date) {
        this.id = id;
        this.id_user = id_user;
        this.id_cours = id_cours;
        this.prix = prix;
        this.date = date;
    }

    public Reservation(int id_user, int id_cours, float prix, Date date) {
        this.id_user = id_user;
        this.id_cours = id_cours;
        this.prix = prix;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Abonnement{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", id_cours=" + id_cours +
                ", prix=" + prix +
                ", date=" + date +
                '}';
    }
}
