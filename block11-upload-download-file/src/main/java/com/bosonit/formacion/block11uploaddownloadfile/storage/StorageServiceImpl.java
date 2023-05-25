package com.bosonit.formacion.block11uploaddownloadfile.storage;

import com.bosonit.formacion.block11uploaddownloadfile.domain.Fichero;
import com.bosonit.formacion.block11uploaddownloadfile.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    private  Path rootLocation;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    StorageProperties storageProperties;
    @Value("${ruta.archivo}")
    private String rutaArchivo;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
    @Override
    public Resource loadAsResourceById(String id) {
        try {
            String fichero = fileRepository.findById(Integer.parseInt(id)).get().getNombre();

            Path file = load(fichero);
                    Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file with id: " + id);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file with id: " + id, e);
        }
    }

    @Override
    public Resource loadAsResourceByFilename(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file with id: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file with id: " + filename, e);
        }
    }

    @Override
    public Fichero store(MultipartFile file) {

        //If we haven't passed a parameter through the console
        // (ex.: java -jar target/block11-upload-download-file-0.0.1-SNAPSHOT.jar --ruta.archivo=/FormacionBosonit/NuevosArchivos),
        // it takes the path of the directory specified in 'StorageProperties'
        Path finalLocation=this.rootLocation;
        if(!rutaArchivo.isBlank()){
            finalLocation=Paths.get(rutaArchivo);
        }
        try {
            if (file.isEmpty()) {
                throw new StorageException("Error al intentar almacenar un fichero vacío");
            }

            Path destinationFile = finalLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(finalLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "No se puede almacenar el archivo fuera del directorio actual");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                //Inserción en h2 de los datos del fichero
                Fichero fichero = new Fichero();
                fichero.setNombre(file.getOriginalFilename());
                fichero.setCategoria(file.getContentType());
                fichero.setFecha_subida(Date.valueOf(LocalDate.now()).toString());

                return fileRepository.save(fichero);
            }
        } catch (IOException e) {
            throw new StorageException("Error al almacenar el archivo", e);
        }
    }
    //Method to set location in 'storageProperties' and then to update 'this.rootLocation' with the new location
    @Override
    public void setPath(String path) throws Exception {
        storageProperties.setLocation(path);
        this.rootLocation=Paths.get(storageProperties.getLocation());
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }


}
