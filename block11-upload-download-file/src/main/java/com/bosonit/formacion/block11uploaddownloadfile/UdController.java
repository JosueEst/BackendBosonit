package com.bosonit.formacion.block11uploaddownloadfile;


import com.bosonit.formacion.block11uploaddownloadfile.domain.Fichero;
import com.bosonit.formacion.block11uploaddownloadfile.storage.StorageException;
import com.bosonit.formacion.block11uploaddownloadfile.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Path;

@RestController
public class UdController {

    @Autowired
    StorageService storageService;


//    @GetMapping ("/files/{id}")
//    public ResponseEntity<Resource> getFileById (@RequestParam Path path, @PathVariable String id ){
//        Resource file = storageService.loadAsResource(id);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
//    @GetMapping ("/files/{nombre}")
//    public ResponseEntity<Resource> getFileByName (@RequestParam Path path, @PathVariable String nombre){
//
//    }

    // Method to download a file from: '/upload_files' by the 'Fichero' object id store in database (H2)
    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable String id) {

        Resource file = storageService.loadAsResourceById(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    // Method to download a file from: '/upload_files' by the file name
    @GetMapping("/filename/{filename}")
    public ResponseEntity<Resource> downloadFileByFilename(@PathVariable String filename) {

        Resource file = storageService.loadAsResourceByFilename(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    //Method to upload any file in: /upload_files
    @PostMapping("/")
    public ResponseEntity<Fichero> uploadAnyFile(@RequestParam MultipartFile file,
                                                 RedirectAttributes redirectAttributes
    ) throws StorageException {
        try {

            redirectAttributes.addFlashAttribute("mensaje",
                    "Has subido satisfactoriamente " + file.getName() + "!");
            return ResponseEntity.ok().body(storageService.store(file));

        } catch (StorageException e) {
            throw new StorageException(e.getMessage());
        }
    }
    // ----------------------------------------------------------------------------------------------------------------

    // Método para modificar el path donde se guardan los ficheros
    @GetMapping("/setpath")
    public ResponseEntity<String> setPath (@RequestParam String path) throws Exception {
        try{
            storageService.setPath(path);
            storageService.init();
            return ResponseEntity.ok().body("El nuevo directorio es: "+path);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    //
    @PostMapping("/upload/{tipo}")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file,
                                     @PathVariable String tipo) throws StorageException {
        try {
            String fileName = file.getOriginalFilename().toString();
            // Code to get characters from '.' until the end of de filename
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
            if (!tipo.equals(fileType)) {
                return ResponseEntity.internalServerError().body("Solo se permite subir ficheros con extensión '.txt'");
            } else {
                return ResponseEntity.ok().body(storageService.store(file));
            }
        } catch (StorageException e) {
            throw new StorageException(e.getMessage());
        }
    }


}
