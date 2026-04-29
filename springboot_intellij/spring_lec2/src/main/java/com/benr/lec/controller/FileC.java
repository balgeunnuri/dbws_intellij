package com.benr.lec.controller;

import com.benr.lec.service.FileService;
import com.benr.lec.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileC {

    @Autowired
    private FileService fileService;

    // 파일 업로드
    @GetMapping("/upload")
    public String uppload() {
        return "file";
    }

    @PostMapping("/upload")
    public String upload(FileVO fileVO, MultipartFile f_file) {
        fileService.upload(fileVO, f_file);

        return "file";
    }

    // 다중파일 업로드
    @PostMapping("/upload22")
    public String upload2(MultipartFile[] files, Model model) { // files -> input name
        model.addAttribute("fileNames", fileService.upload2(files));
        return  "file";
    }


}
