package com.popularsafi.service.impl;

import com.popularsafi.dto.FileDocumentoRutaDTO;
import com.popularsafi.model.FileDocumentoRuta;
import com.popularsafi.model.Periodo;
import com.popularsafi.repo.IFileDocumentoRepo;
import com.popularsafi.repo.IPeriodoRepo;
import com.popularsafi.service.IFileDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;


@Service
public class FileDocumentoServiceImpl implements IFileDocumentoService{

    @Autowired
    IFileDocumentoRepo iFileDocumentoRepo;

    @Override
    public FileDocumentoRuta save(FileDocumentoRuta fileDocumentoRuta) {
        return null;
    }

    @Override
    public FileDocumentoRuta update(FileDocumentoRuta fileDocumentoRuta, String s) {
        return null;
    }

    @Override
    public List<FileDocumentoRuta> findAll() {
        return null;
    }

    @Override
    public FileDocumentoRuta findById(String s) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public FileDocumentoRuta findByDocumentoAll(String pfile) {
        return iFileDocumentoRepo.findByDocumentoAll(pfile);
    }

    @Override
    public byte[] generateReport(String ruta) throws Exception {
         return  Files.readAllBytes(new File(ruta).toPath());
    }
}
