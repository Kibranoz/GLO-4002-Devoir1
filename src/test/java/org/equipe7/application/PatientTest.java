package org.equipe7.application;

import org.equipe7.application.categories.VisibleSymptom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    @Test
    public void creationPatientWithParams() {
        Patient patient = new Patient("Jean", 1, VisibleSymptom.COLD);

        assertEquals("Jean", patient.getName());
        assertEquals(1, patient.getGravite());
        assertEquals(VisibleSymptom.COLD, patient.getSymptom());
    }

    @Test
    public void creationPatientNom() {
        String nom = "Olivier";

        Patient patient = new Patient(nom, 1, VisibleSymptom.COLD);

        assertEquals(nom, patient.getName());
    }

    @Test
    public void creationPatientGravite() {
        int gravite = 4;

        Patient patient = new Patient("Mathieu",  gravite, VisibleSymptom.COLD);

        assertEquals(gravite, patient.getGravite());
    }

    @Test
    public void creationPatientVisibleSymptom() {
        VisibleSymptom symptom = VisibleSymptom.CORONAVIRUS;

        Patient patient = new Patient("Mathieu",  3, symptom);

        assertEquals(symptom, patient.getSymptom());
    }
}