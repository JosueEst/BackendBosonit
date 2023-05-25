package com.bosonit.formacion.block11uploaddownloadfile.storage;

import com.bosonit.formacion.block11uploaddownloadfile.domain.Fichero;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

public interface StorageService {


    Path load(String filename);
    Resource loadAsResourceById (String id);
    Resource loadAsResourceByFilename (String filename);
    Fichero store(MultipartFile file);
    void setPath(String path) throws Exception;
    void deleteAll();
    void init();
}
