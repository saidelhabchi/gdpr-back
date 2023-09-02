package com.example.Application.service.demande;

import com.example.Application.dto.demande.ApproveDemandeDTO;
import com.example.Application.dto.demande.DemandeDTO;
import com.example.Application.dto.demande.DemandeFirstDTO;
import com.example.Application.model.*;
import com.example.Application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DemandeService {
    DemandeRepository demandeRepository;
    OccupantRepository occupantRepository;
    DomainPublicRepository domainPublicRepository;
    DecisionAutorisationRepository decisionAutorisationRepository;
    RedevanceRepository redevanceRepository;
    FichesRepository fichesRepository;

    @Value("${gdpr.env.uploadFolderFiches}")
    public String UPLOAD_FOLDER_FICHES;

    @Autowired
    public DemandeService(DemandeRepository demandeRepository, OccupantRepository occupantRepository, DomainPublicRepository domainPublicRepository, DecisionAutorisationRepository decisionAutorisationRepository, RedevanceRepository redevanceRepository,FichesRepository fichesRepository) {
        this.demandeRepository = demandeRepository;
        this.occupantRepository = occupantRepository;
        this.domainPublicRepository = domainPublicRepository;
        this.decisionAutorisationRepository = decisionAutorisationRepository;
        this.redevanceRepository = redevanceRepository;
        this.fichesRepository = fichesRepository;
    }

    public List<Demande> allDemandes() {
        return demandeRepository.findAll();
    }


    public void addDemande(
            DemandeFirstDTO demandeFirstDTO
            ) throws IOException {

        //store file path
        String fichePlansPath = getFilePath(demandeFirstDTO.getFiche_plans(),demandeFirstDTO.getTitre());
        String ficheCPSPath =  getFilePath(demandeFirstDTO.getFiche_cps(),demandeFirstDTO.getTitre());
        String ficheDemandePath = getFilePath(demandeFirstDTO.getFiche_demande(),demandeFirstDTO.getTitre());

        Optional<Occupant> OccOpt = occupantRepository.findByName(demandeFirstDTO.getNom_occupant());
        Occupant occupant = new Occupant();
        if(OccOpt.isPresent()) {
            occupant = OccOpt.get();
        }else{
            occupant.setName(demandeFirstDTO.getNom_occupant());
            occupant.setIdentity(demandeFirstDTO.getIdentite_occupant());
            occupant.setType(demandeFirstDTO.getType_occupant());
            occupant.setPhone(demandeFirstDTO.getPhone_occupant());
            occupant.setCin(demandeFirstDTO.getCin_occupant());
            occupantRepository.save(occupant);
        }


        DomainePublic domainePublic = new DomainePublic();
        domainePublic.setAdresse(demandeFirstDTO.getNumero_route());
        domainePublic.setPtDebut(demandeFirstDTO.getPt_depart());
        domainePublic.setPtFin(demandeFirstDTO.getPt_fin());
        domainePublic.setLineaireOccupee(doubleFromString(demandeFirstDTO.getLineaire_occupe()));
        domainePublic.setSuperficieOccupee(doubleFromString(demandeFirstDTO.getSuperficie_occupe()));

        //set file path to database
        Fiches fiches = new Fiches();
        fiches.setFichePlans(fichePlansPath);
        fiches.setFicheCps(ficheCPSPath);
        fiches.setFicheDemande(ficheDemandePath);

        //save file to repo
        saveFileToUploadFolder(demandeFirstDTO.getFiche_plans(),demandeFirstDTO.getTitre());
        saveFileToUploadFolder(demandeFirstDTO.getFiche_cps(),demandeFirstDTO.getTitre());
        saveFileToUploadFolder(demandeFirstDTO.getFiche_demande(),demandeFirstDTO.getTitre());

        Demande demande = new Demande();
        demande.setTitle(demandeFirstDTO.getTitre());
        demande.setOccupant(occupant);
        domainPublicRepository.save(domainePublic);
        demande.setDomainePublic(domainePublic);
        fichesRepository.save(fiches);
        demande.setFiches(fiches);
        demandeRepository.save(demande);
    }

    public Demande approveDemande(ApproveDemandeDTO approveDemandeDTO) throws IOException, ParseException {

        Demande demande = demandeRepository.findById(approveDemandeDTO.getId()).get();


        String rapportTechniquePath = getFilePath(approveDemandeDTO.getRapport_technique(),demande.getTitle());
        String plansSignesPath = getFilePath(approveDemandeDTO.getPlans_signes(),demande.getTitle());
        String guideExplicatifRedevencePath = getFilePath(approveDemandeDTO.getGuide_explicatif_redevance(),demande.getTitle());

        DecisionAutorisation decisionAutorisation = new DecisionAutorisation();
        decisionAutorisation.setNumero(approveDemandeDTO.getNumero_decision_autorisation());
        decisionAutorisation.setDateDecision(dateFromString(approveDemandeDTO.getDate_decision_autorisation()));
        decisionAutorisation.setFinAutorisation(dateFromString(approveDemandeDTO.getFin_d_autorisation()));
        decisionAutorisation.setDelai(approveDemandeDTO.getDelai());
        decisionAutorisationRepository.save(decisionAutorisation);

        Redevance redevance = new Redevance();
        redevance.setMontant(doubleFromString(approveDemandeDTO.getRedevence_annuelle()));
        redevance.setDernierPayment(doubleFromString(approveDemandeDTO.getDernier_payment()));
        redevance.setDateDernierPayment(dateFromString(approveDemandeDTO.getDate_dernier_payment()));
        redevance.setResteAPayer(doubleFromString(approveDemandeDTO.getReste_a_payer()));
        redevance.setDhParUnite(doubleFromString(approveDemandeDTO.getDh_par_unite()));
        redevanceRepository.save(redevance);

        Fiches fiches = demande.getFiches();

        fiches.setRapportTechnique(rapportTechniquePath);
        fiches.setPlansSignes(plansSignesPath);
        fiches.setGuideExplicatifRedevance(guideExplicatifRedevencePath);


        saveFileToUploadFolder(approveDemandeDTO.getRapport_technique(),demande.getTitle());
        saveFileToUploadFolder(approveDemandeDTO.getPlans_signes(),demande.getTitle());
        saveFileToUploadFolder(approveDemandeDTO.getGuide_explicatif_redevance(),demande.getTitle());

        if(approveDemandeDTO.getLettre_approvation().getSize() != 0){
            String ficheDApprovationPath = getFilePath(approveDemandeDTO.getLettre_approvation(),demande.getTitle());
            fiches.setFicheDApprovation(ficheDApprovationPath);
            saveFileToUploadFolder(approveDemandeDTO.getLettre_approvation(),demande.getTitle());
        }

        demande.setDecisionAutorisation(decisionAutorisation);
        demande.setRedevance(redevance);
        demande.setFiches(fiches);

        demande.setApproved(true);

        return demandeRepository.save(demande);
    }

    public void deleteDemande(int id) {
        Demande toBeDeleted = demandeRepository.findById(id).get();
        Fiches fiches = toBeDeleted.getFiches();
        deleteDemandeFiches(toBeDeleted.getTitle());
        demandeRepository.deleteById(id);
        fichesRepository.delete(fiches);

    }
    public List<Demande> getApprovedDemands() {
        return demandeRepository.findByApproved(true);
    }

    public List<Demande> getNoneApprovedDemands() {
        return demandeRepository.findByApproved(false);
    }

    public Demande updateDemande(DemandeDTO demandeDTO) throws ParseException, IOException {
        Demande demande = demandeRepository.findById(demandeDTO.getId()).get();

        deleteDemandeFiches(demande.getTitle());

        demande.setTitle(demandeDTO.getTitre());

        Occupant occupant = demande.getOccupant();
        occupant.setName(demandeDTO.getNom_occupant());
        occupant.setIdentity(demandeDTO.getIdentite_occupant());

        DomainePublic domainePublic = demande.getDomainePublic();
        domainePublic.setAdresse(demandeDTO.getNumero_route());
        domainePublic.setPtDebut(demandeDTO.getPt_depart());
        domainePublic.setPtFin(demandeDTO.getPt_depart());
        domainePublic.setLineaireOccupee(doubleFromString(demandeDTO.getLineaire_occupe()));
        domainePublic.setSuperficieOccupee(doubleFromString(demandeDTO.getSuperficie_occupe()));

        DecisionAutorisation decisionAutorisation = demande.getDecisionAutorisation();
        decisionAutorisation.setNumero(demandeDTO.getNumero_decision_autorisation());
        decisionAutorisation.setDateDecision(dateFromString(demandeDTO.getDate_decision_autorisation()));
        decisionAutorisation.setFinAutorisation(dateFromString(demandeDTO.getFin_d_autorisation()));

        Redevance redevance = demande.getRedevance();
        redevance.setMontant(doubleFromString(demandeDTO.getRedevence_annuelle()));
        redevance.setDernierPayment(doubleFromString(demandeDTO.getDernier_payment()));
        redevance.setDateDernierPayment(dateFromString(demandeDTO.getDate_dernier_payment()));
        redevance.setResteAPayer(doubleFromString(demandeDTO.getReste_a_payer()));

        Fiches fiches = demande.getFiches();

        String fichePlansPath = getFilePath(demandeDTO.getFiche_plans(), demandeDTO.getTitre());
        String ficheCPSPath =  getFilePath(demandeDTO.getFiche_cps(), demandeDTO.getTitre());
        String ficheDemandePath = getFilePath(demandeDTO.getFiche_demande(), demandeDTO.getTitre());
        String ficheDApprovationPath = getFilePath(demandeDTO.getLettre_approvation(),demandeDTO.getTitre());
        String rapportTechniquePath = getFilePath(demandeDTO.getRapport_technique(),demandeDTO.getTitre());
        String plansSignesPath = getFilePath(demandeDTO.getPlans_signes(),demandeDTO.getTitre());
        String guideExplicatifRedevencePath = getFilePath(demandeDTO.getGuide_explicatif_redevance(),demandeDTO.getTitre());

        fiches.setFichePlans(fichePlansPath);
        fiches.setFicheCps(ficheCPSPath);
        fiches.setFicheDemande(ficheDemandePath);
        fiches.setFicheDApprovation(ficheDApprovationPath);
        fiches.setRapportTechnique(rapportTechniquePath);
        fiches.setPlansSignes(plansSignesPath);
        fiches.setGuideExplicatifRedevance(guideExplicatifRedevencePath);

        saveFileToUploadFolder(demandeDTO.getFiche_plans(),demandeDTO.getTitre());
        saveFileToUploadFolder(demandeDTO.getFiche_cps(),demandeDTO.getTitre());
        saveFileToUploadFolder(demandeDTO.getFiche_demande(),demandeDTO.getTitre());
        saveFileToUploadFolder(demandeDTO.getLettre_approvation(),demandeDTO.getTitre());
        saveFileToUploadFolder(demandeDTO.getRapport_technique(),demandeDTO.getTitre());
        saveFileToUploadFolder(demandeDTO.getPlans_signes(),demandeDTO.getTitre());
        saveFileToUploadFolder(demandeDTO.getGuide_explicatif_redevance(),demandeDTO.getTitre());


        demandeRepository.save(demande);
        return null;
    }

    public Demande getDemandeById(int id){
        return demandeRepository.findById(id).get();
    }

    private Date dateFromString(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }
    private Double doubleFromString(String d){
        return Double.parseDouble(d);
    }
    private String getFilePath(MultipartFile file,String titre){
        return UPLOAD_FOLDER_FICHES + "\\"+titre+"\\"+file.getOriginalFilename();
    }
    private void saveFileToUploadFolder(MultipartFile file,String foldername) throws IOException {
        if(file != null){
            File folder = new File(UPLOAD_FOLDER_FICHES +"\\"+foldername);
            if(!folder.exists()){
                folder.mkdir();
            }
            file.transferTo(new File(folder.getAbsoluteFile()+"\\"+file.getOriginalFilename()));
        }
    }

    private boolean deleteDemandeFiches(String FolderTitle){
        File folder = new File(UPLOAD_FOLDER_FICHES +"\\"+FolderTitle);
        if(folder.exists() && folder.isDirectory()){
            File[] files = folder.listFiles();
            for (File file : files ) {
                file.delete();
            }
            return folder.delete();
        }
        return false;
    }


    public List<String> test(MultipartFile file) throws IOException {
        File folder = new File(UPLOAD_FOLDER_FICHES +"\\test");
        folder.mkdir();
        saveFileToUploadFolder(file,folder.getAbsolutePath()+"\\"+file.getOriginalFilename());
//        var test = List.of("x",UPLOAD_FOLDER+file.getOriginalFilename());
        var test = List.of("x", folder.getAbsolutePath()+"\\"+file.getOriginalFilename());
        return test;
    }

    public boolean testDelete() {
        return deleteDemandeFiches(UPLOAD_FOLDER_FICHES +"\\test");
    }

}
