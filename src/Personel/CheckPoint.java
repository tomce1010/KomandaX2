package Personel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CheckPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String address;
    private String GPS;
    @ManyToOne
    private Destination destination;

    public CheckPoint(String country, String address, String GPS) {
        this.country = country;
        this.address = address;
        this.GPS = GPS;
    }

    public CheckPoint(String country, String address, String GPS, Destination destination) {
        this.country = country;
        this.address = address;
        this.GPS = GPS;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return  "ID = " + id +
                "\nCountry = '" + country + '\'' +
                "\nAddress = '" + address + '\'' +
                "\nGPS = '" + GPS + '\'';
    }
}
