package com.example.Application.service.demande;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemandeUtils {
    @Value("${gdpr.env.uploadFolder}")
    static String UPLOAD_FOLDER;


    static Date dateFromString(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }
    static Double doubleFromString(String d){
        return Double.parseDouble(d);
    }
    static String getFilePath(MultipartFile file, String titre){
        return UPLOAD_FOLDER + "\\"+titre+"\\"+file.getOriginalFilename();
    }
    static void saveFileToUploadFolder(MultipartFile file,String foldername) throws IOException {
        File folder = new File(UPLOAD_FOLDER+"\\"+foldername);
        folder.mkdir();
        file.transferTo(new File(folder.getAbsoluteFile()+"\\"+file.getOriginalFilename()));
    }
    static boolean deleteDemandeFiches(String FolderTitle){
        File folder = new File(UPLOAD_FOLDER+"\\"+FolderTitle);
        if(folder.exists() && folder.isDirectory()){
            File[] files = folder.listFiles();
            for (File file : files ) {
                file.delete();
            }
            return folder.delete();
        }
        return false;
    }
}
