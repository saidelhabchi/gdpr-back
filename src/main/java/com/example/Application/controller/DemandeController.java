package com.example.Application.controller;

import com.example.Application.dto.demande.ApproveDemandeDTO;
import com.example.Application.dto.demande.DemandeDTO;
import com.example.Application.dto.demande.DemandeFirstDTO;
import com.example.Application.model.Demande;
import com.example.Application.repository.DemandeRepository;
import com.example.Application.service.demande.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {
    @Autowired
    DemandeService demandeService;
    @Autowired
    private DemandeRepository demandeRepository;

    @GetMapping("/download/{folder}/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("folder") String folder, @PathVariable("filename") String filename) throws MalformedURLException {
        Path fileStorageLocation = Paths.get(demandeService.UPLOAD_FOLDER_FICHES);
        Path filePath = fileStorageLocation.resolve(folder).resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping
    public List<Demande> allDemandes(){
        return demandeService.allDemandes();
    }
    @GetMapping("/approved")
    public List<Demande> getApprovedDemandes(){
        return demandeService.getApprovedDemands();
    }
    @GetMapping("/none-approved")
    public List<Demande> getNoneApprovedDemandes(){
        return demandeService.getNoneApprovedDemands();
    }
    //insertFirstTime
    @PostMapping("/demande")
    public ResponseEntity<String> insertDemande(DemandeFirstDTO demandeDTO) throws IOException {
        demandeService.addDemande(demandeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/approve")
    public Demande approveDemande(ApproveDemandeDTO approveDemandeDTO) throws IOException, ParseException {
        return demandeService.approveDemande(approveDemandeDTO);
    }

    @PutMapping("/update")
    public Demande updateDemande(DemandeDTO demandeDTO) throws ParseException, IOException {
        return demandeService.updateDemande(demandeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDemande(@PathVariable int id){
        demandeService.deleteDemande(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/demande/{id}")
    public Demande getDemandeById(@PathVariable int id){
        return demandeService.getDemandeById(id);
    }
    @PostMapping("/test")
    public ResponseEntity<List<String>> test(@RequestParam MultipartFile fiche) throws IOException {
        return new ResponseEntity<>(demandeService.test(fiche), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/test-delete")
    public String testDelete(){
        return demandeService.testDelete() ? "deleted" : "not deleted";
    }
}
