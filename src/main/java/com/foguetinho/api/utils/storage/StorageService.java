package com.foguetinho.api.utils.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.foguetinho.api.model.RegTelefonico;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    RegTelefonico store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
