

public class Book {

    private String cim;
    private String szerzo;
    private String azonositoKod;
    private int keszlet;
    private boolean elerheto = true;


    public Book() {
    }
    public Book(String cim, String szerzo, String azonositoKod,int keszlet) {
        this.cim = cim;
        this.szerzo = szerzo;
        this.azonositoKod = azonositoKod;
        this.keszlet=keszlet;
        this.elerheto = elerheto;
    }
    //region setter_getter

    public int getKeszlet() {
        return keszlet;
    }

    public Book setKeszlet(int keszlet) {
        this.keszlet = keszlet;
        return this;
    }

    public String getCim() {
        return cim;
    }

    public Book setCim(String cim) {
        this.cim = cim;
        return this;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public Book setSzerzo(String szerzo) {
        this.szerzo = szerzo;
        return this;
    }

    public String getAzonositoKod() {
        return azonositoKod;
    }

    public Book setAzonositoKod(String azonositoKod) {
        this.azonositoKod = azonositoKod;
        return this;
    }

    public boolean getElerheto() {
        return elerheto;
    }

    public Book setElerheto(boolean elerheto) {
        this.elerheto = elerheto;
        return this;
    }
    //endregion


    @Override
    public String toString() {
        return cim + " " + szerzo + " " + azonositoKod + " " + elerheto + "\n";
    }
}
