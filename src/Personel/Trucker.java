package Personel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trucker extends User {
    private boolean hasValidMedicalCard;
    private boolean hasValidDriversLicence;
    private String DriversLicense;
    private String MedicalCard;
    private int fullDrivenDistance;
    @OneToMany (mappedBy = "trucker", cascade = CascadeType.ALL)
    private List<Truck> trucks;

    public Trucker(String DriversLicense, String MedicalCard, String password, String name, String lastname, LocalDate dateOfBirth, String phoneNum, String login, boolean hasValidMedicalCard, boolean hasValidDriversLicence, int fullDrivenDistance) {
        super(password, name, lastname, dateOfBirth, phoneNum, login);
        this.hasValidMedicalCard = hasValidMedicalCard;
        this.hasValidDriversLicence = hasValidDriversLicence;
        this.fullDrivenDistance = fullDrivenDistance;
        this.DriversLicense = DriversLicense;
        this.MedicalCard = MedicalCard;
    }

}
