package org.equipe7.application;

import org.equipe7.application.categories.VisibleSymptom;

public class Patient {
    private String nom;
    private int gravite;
    private VisibleSymptom visibleSymptom;

    public Patient(String nom, int gravite, VisibleSymptom visibleSymptom) {
        this.nom = nom;
        this.gravite = gravite;
        this.visibleSymptom = visibleSymptom;
    }

    public String getName() {
        return this.nom;
    }

    public int getGravite() {
        return this.gravite;
    }

    public VisibleSymptom getSymptom() {
        return this.visibleSymptom;
    }
}
