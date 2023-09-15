package org.equipe7.application;

import org.equipe7.application.categories.TriageType;
import org.equipe7.application.categories.VisibleSymptom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicTest {
    @Test
    public void creationClinique() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        assertEquals(TriageType.FIFO, clinic.getFileDoctor());
        assertEquals(TriageType.FIFO, clinic.getFileRadio());
    }

    @Test
    public void creationCliniqueAvecGraviteEtatDoctor() {
        Clinic clinic = new Clinic(TriageType.ETAT, TriageType.FIFO);

        assertEquals(TriageType.ETAT, clinic.getFileDoctor());
    }

    @Test
    public void creationCliniqueAvecGraviteEtatRadio() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.ETAT);

        assertEquals(TriageType.ETAT, clinic.getFileRadio());
    }

    @Test
    public void creationCliniqueFileVide() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        assertTrue(clinic.isDoctorLineEmpty());
        assertTrue(clinic.isRadioLineEmpty());
    }

    @Test
    public void algoFIFO_quandPatientAjouter_premierFileDocteur() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.MIGRAINE);

        assertEquals(0, clinic.getIndexDoctorPatient("Jean"));
    }

    @Test
    public void algoFIFO_quandPatientAjouter_pasDansFileRadio() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.MIGRAINE);

        assertEquals(-1, clinic.getIndexRadioPatient("Jean"));
    }

    @Test
    public void algoFIFO_fileDocteurNonVide_quandPatientAjouter_deuxiemeFileDocteur() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.MIGRAINE);
        clinic.triagePatient("Patrick", 1, VisibleSymptom.FLU);

        assertEquals(1, clinic.getIndexDoctorPatient("Patrick"));
    }

    @Test
    public void algoFIFO_fileDocteurNonVide_quandPatientAjouter_pasDansFileRadio() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.MIGRAINE);
        clinic.triagePatient("Patrick", 1, VisibleSymptom.FLU);

        assertEquals(-1, clinic.getIndexRadioPatient("Patrick"));
    }

    @Test
    public void algoFIFO_quandPatientAjouterEntorse_premierFileDocteur() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.SPRAIN);

        assertEquals(0, clinic.getIndexDoctorPatient("Jean"));
    }

    @Test
    public void algoFIFO_quandPatientAjouterEntorse_premierFileRadio() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.SPRAIN);

        assertEquals(0, clinic.getIndexRadioPatient("Jean"));
    }

    @Test
    public void algoFIFO_quandPatientAjouterOsBrise_premierFileRadio() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.BROKEN_BONE);

        assertEquals(0, clinic.getIndexRadioPatient("Jean"));
    }

    @Test
    public void algoFIFO_fileDocteurNonVide_quandPatientAjouterEntorse_deuxiemeFileDocteur() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.SPRAIN);
        clinic.triagePatient("Patrick", 1, VisibleSymptom.BROKEN_BONE);

        assertEquals(1, clinic.getIndexDoctorPatient("Patrick"));
    }

    @Test
    public void algoFIFO_fileRadioVide_quandPatientAjouterEntorse_premierFileRadio() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.FLU);
        clinic.triagePatient("Patrick", 1, VisibleSymptom.BROKEN_BONE);

        assertEquals(0, clinic.getIndexRadioPatient("Patrick"));
    }

    @Test
    public void algoFIFO_fileRadioNonVide_quandPatientAjouterEntorse_deuxiemeFileRadio() {
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient("Jean", 1, VisibleSymptom.SPRAIN);
        clinic.triagePatient("Patrick", 1, VisibleSymptom.BROKEN_BONE);

        assertEquals(1, clinic.getIndexRadioPatient("Patrick"));
    }
    @Test
    public void sorting_nonEmptyDoctorLine_whenSortingByGravity_whenHighGravityFluPatient_firstInLine() {
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);

        clinic.triagePatient("Jeremy",1,VisibleSymptom.SPRAIN);
        clinic.triagePatient("Torben", 7, VisibleSymptom.FLU);

        assertEquals(0, clinic.getIndexDoctorPatient("Torben"));
    }

    @Test
    public void given_nonEmptyDoctorLine_whenSortingByGravity_whenHighGravityPatients_thenFirstsInLine() {
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);

        clinic.triagePatient("Louis",2,VisibleSymptom.COLD);
        clinic.triagePatient("Carmelle",6,VisibleSymptom.FLU);
        clinic.triagePatient("Torben", 7, VisibleSymptom.FLU);


        assertEquals(0, clinic.getIndexDoctorPatient("Torben"));
        assertEquals(1, clinic.getIndexDoctorPatient("Carmelle"));
        assertEquals(2, clinic.getIndexDoctorPatient("Louis"));

    }

    @Test
    public void given_sortingByGravity_whenOnlyFlu_EveryoneInTleLine() {
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);

        clinic.triagePatient("Louis",8,VisibleSymptom.FLU);
        clinic.triagePatient("Carmelle",7,VisibleSymptom.FLU);
        clinic.triagePatient("Torben", 6, VisibleSymptom.FLU);

        assertEquals(0, clinic.getIndexDoctorPatient("Louis"));
        assertEquals(1, clinic.getIndexDoctorPatient("Carmelle"));
        assertEquals(2, clinic.getIndexDoctorPatient("Torben"));
    }

    @Test
    public void given_nonEmptyBothLines_whenSortingByGravity_whenBrokenBones_thenSecondInRadiology() {
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);

        clinic.triagePatient("Louis",2,VisibleSymptom.FLU);
        clinic.triagePatient("Jeremy", 4, VisibleSymptom.SPRAIN);
        clinic.triagePatient("Émilie",6,VisibleSymptom.SPRAIN);
        clinic.triagePatient("Torben", 7, VisibleSymptom.BROKEN_BONE);

        assertEquals(0,clinic.getIndexRadioPatient("Jeremy"));
        assertEquals(1, clinic.getIndexRadioPatient("Torben"));
        assertEquals(2, clinic.getIndexRadioPatient("Émilie"));

    }

    @Test
    public void given_nonEmptyBothLines_whenSortingByGravity_whenBrokenBones_thenSecondsAndMoreInRadiology_orderOfSecondsIsByGravity(){
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);

        clinic.triagePatient("Louis",2,VisibleSymptom.FLU);
        clinic.triagePatient("Jeremy", 4, VisibleSymptom.SPRAIN);
        clinic.triagePatient("Émilie",6,VisibleSymptom.BROKEN_BONE);
        clinic.triagePatient("Torben", 7, VisibleSymptom.BROKEN_BONE);

        assertEquals(0,clinic.getIndexRadioPatient("Jeremy"));
        assertEquals(1, clinic.getIndexRadioPatient("Torben"));
        assertEquals(2, clinic.getIndexRadioPatient("Émilie"));
    }
}