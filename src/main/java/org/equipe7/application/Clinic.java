package org.equipe7.application;

import org.equipe7.application.categories.TriageType;
import org.equipe7.application.categories.VisibleSymptom;

import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private TriageType triageDoctor;
    private TriageType triageRadio;

    private List<Patient> fileDoctor = new ArrayList<>();
    private List<Patient> fileRadio = new ArrayList<>();

    public Clinic(TriageType triageDoctor, TriageType triageRadio) {
        this.triageDoctor = triageDoctor;
        this.triageRadio = triageRadio;
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        Patient patient = new Patient(name, gravity, visibleSymptom);
        this.fileDoctor.add(patient);

        if(visibleSymptom.equals(VisibleSymptom.SPRAIN) || visibleSymptom.equals(VisibleSymptom.BROKEN_BONE)) {
            this.fileRadio.add(patient);
        }
    }

    public TriageType getFileDoctor() {
        return this.triageDoctor;
    }

    public TriageType getFileRadio() {
        return this.triageRadio;
    }

    public boolean isDoctorLineEmpty() {
        return this.fileDoctor.isEmpty();
    }

    public boolean isRadioLineEmpty() {
        return this.fileRadio.isEmpty();
    }

    public int getIndexDoctorPatient(String nom) {
        int pos = -1;

        for(int compteur = 0; compteur < this.fileDoctor.size(); compteur++) {
            if(this.fileDoctor.get(compteur).getName().equals(nom)) {
                pos = compteur;
                break;
            }
        }

        return pos;
    }

    public int getIndexRadioPatient(String nom) {
        int pos = -1;

        for(int compteur = 0; compteur < this.fileRadio.size(); compteur++) {
            if(this.fileRadio.get(compteur).getName().equals(nom)) {
                pos = compteur;
                break;
            }
        }

        return pos;
    }

    // D'autres méthodes peuvent être nécessaires

}
