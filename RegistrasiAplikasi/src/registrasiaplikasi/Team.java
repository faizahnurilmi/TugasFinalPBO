package registrasiaplikasi;
public class Team {
    private String namaTim;
    private String genre;
    private String email;
    private int jumlahMember;

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJumlahMember() {
        return jumlahMember;
    }

    public void setJumlahMember(int jumlahMember) {
        this.jumlahMember = jumlahMember;
    }
}
