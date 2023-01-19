package net.spring.aws;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(String description, @RequestParam("file") MultipartFile multipartFile, Model model) {
        String filename = multipartFile.getOriginalFilename();

        System.out.println("Description: " + description);
        System.out.println("Filename: " + filename);

        String messsage = "";
        try {
            S3Util.uploadFile(filename, multipartFile.getInputStream());
            messsage = "Your file has been uploaded successfully!";
        } catch (Exception ex) {
            messsage = "Error uploading file: " + ex.getMessage();
        }

        model.addAttribute("message", messsage);
        return "message";
    }
}
