package com.benr.lec.service;

import com.benr.lec.mapper.FileMapper;
import com.benr.lec.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    // db
    @Autowired
    private FileMapper fileMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public void upload(FileVO fileVO, MultipartFile fFile) {


        // 확장자 추출
        String ext = fFile.getOriginalFilename().substring(fFile.getOriginalFilename().lastIndexOf("."));

        // 파일명 재생성 (중복방지하려고)
        String uuid = UUID.randomUUID().toString().split("-")[0];
        System.out.println(uuid + ext);

        String fileName = uuid + ext;
        File saveFile = new File(uploadDir + "/" + fileName);

        try {
            fFile.transferTo(saveFile); // 업로드

            // db에 저장
            fileVO.setF_name(fileName);
            fileMapper.saveFile(fileVO);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String[] upload2(MultipartFile[] files) {


        String fNames = "";
        for (MultipartFile fFile : files) {
            String ext = fFile.getOriginalFilename().substring(fFile.getOriginalFilename().lastIndexOf("."));

            // 파일명 재생성 (중복방지하려고)
            String uuid = UUID.randomUUID().toString().split("-")[0];
            String fileName = uuid + ext;
            File saveFile = new File(uploadDir + "/" + fileName);
            fNames += fileName + "!";

            try {
                fFile.transferTo(saveFile); // 업로드

                // db에 저장

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            System.out.println(fNames);
            fileMapper.saveFile2(fNames);
            String[] fileNames = fNames.split("!");
            return fileNames;

    }
}
