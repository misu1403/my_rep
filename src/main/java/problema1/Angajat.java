package problema1;

import java.time.LocalDate;

public class Angajat {
    private String nume;
    private String post;
    private LocalDate data_angajarii;
    private float salariul;
    public  Angajat()
    {

    }
    public Angajat(String nume, String post, LocalDate data_angajarii, float salariul) {
        this.nume = nume;
        this.post = post;
        this.data_angajarii = data_angajarii;
        this.salariul = salariul;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDate getData_angajarii() {
        return data_angajarii;
    }

    public void setData_angajarii(LocalDate data_angajarii) {
        this.data_angajarii = data_angajarii;
    }

    public float getSalariul() {
        return salariul;
    }

    public void setSalariul(float salariul) {
        this.salariul = salariul;
    }

    @Override
    public String toString() {
        return "\nNumele: "+nume+"\nPostul: "+post+"\nData angajarii: "+data_angajarii+"\nSalarul: "+salariul;
    }
}
