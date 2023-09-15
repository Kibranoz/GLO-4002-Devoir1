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
        this.assignFileDoctor(patient);

        if (visibleSymptom.equals(VisibleSymptom.SPRAIN) || visibleSymptom.equals(VisibleSymptom.BROKEN_BONE)) {
            this.assignFileRadio(patient);
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

    public void assignFileDoctor(Patient newPatient) {
        if (this.triageDoctor == TriageType.GRAVITY && newPatient.getGravite() >= 5 && newPatient.getSymptom() == VisibleSymptom.FLU) {
            int compteur;
            for (compteur = 0; compteur < this.fileDoctor.size(); compteur++) {
                Patient currentPatient = this.fileDoctor.get(compteur);
                if (currentPatient.getSymptom() != VisibleSymptom.FLU || currentPatient.getGravite() < newPatient.getGravite()) {
                    this.fileDoctor.add(compteur, newPatient);
                    break;
                }
            }
            if (compteur == this.fileDoctor.size()) {
                this.fileDoctor.add(newPatient);
            }
        } else {
            this.fileDoctor.add(newPatient);
        }
    }

    public void assignFileRadio(Patient newPatient) {
        if (this.triageDoctor == TriageType.GRAVITY && newPatient.getGravite() >= 5 && newPatient.getSymptom() == VisibleSymptom.BROKEN_BONE) {
            int compteur;
            for (compteur = 0; compteur < this.fileRadio.size(); compteur++) {
                Patient currentPatient = this.fileDoctor.get(compteur);
                if (compteur > 0 && (currentPatient.getSymptom() != VisibleSymptom.BROKEN_BONE || currentPatient.getGravite() < newPatient.getGravite())) {
                    this.fileRadio.add(compteur, newPatient);
                    break;
                }
            }
            if (compteur == this.fileRadio.size()) {
                this.fileRadio.add(newPatient);
            }
        } else {
            this.fileRadio.add(newPatient);
        }

    }


    public int getIndexDoctorPatient(String nom) {
        int pos = -1;

        for (int compteur = 0; compteur < this.fileDoctor.size(); compteur++) {
            if (this.fileDoctor.get(compteur).getName().equals(nom)) {
                pos = compteur;
                break;
            }
        }

        return pos;
    }

    public int getIndexRadioPatient(String nom) {
        int pos = -1;

        for (int compteur = 0; compteur < this.fileRadio.size(); compteur++) {
            if (this.fileRadio.get(compteur).getName().equals(nom)) {
                pos = compteur;
                break;
            }
        }

        return pos;
    }

    // D'autres méthodes peuvent être nécessaires

}
