package yoa.pmdm.claudio.ciclesaflorida;

import android.os.Parcel;
import android.os.Parcelable;

public class Ciclo implements Parcelable {
    private String familiaProfessional;
    private String tipus;
    private String titol;
    private String Descripcio;

    public Ciclo(String familiaProfessional, String tipus, String titol, String descripcio) {

        this.familiaProfessional = familiaProfessional;
        this.tipus = tipus;
        this.titol = titol;
        this.Descripcio = descripcio;
    }

    protected Ciclo(Parcel in) {
        familiaProfessional = in.readString();
        tipus = in.readString();
        titol = in.readString();
        Descripcio = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(familiaProfessional);
        dest.writeString(tipus);
        dest.writeString(titol);
        dest.writeString(Descripcio);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ciclo> CREATOR = new Parcelable.Creator<Ciclo>() {
        @Override
        public Ciclo createFromParcel(Parcel in) {
            return new Ciclo(in);
        }

        @Override
        public Ciclo[] newArray(int size) {
            return new Ciclo[size];
        }
    };

    public String getFamiliaProfessional() {
        return familiaProfessional;
    }

    public void setFamiliaProfessional(String familiaProfessional) {
        this.familiaProfessional = familiaProfessional;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getDescripcio() {
        return Descripcio;
    }

    public void setDescripcio(String descripcio) {
        Descripcio = descripcio;
    }

    @Override
    public String toString() {
        return "Ciclo{" +
                "familiaProfessional='" + familiaProfessional + '\'' +
                ", tipus='" + tipus + '\'' +
                ", titol='" + titol + '\'' +
                ", Descripcio='" + Descripcio + '\'' +
                '}';
    }
}
