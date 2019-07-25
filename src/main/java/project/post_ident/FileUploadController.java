package project.post_ident;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FileUploadController {

    private static final String fileSeparator = System.getProperty("file.separator");

    private Path uploadFolder;

    @PostConstruct
    public void init() {
        String path = System.getProperty("user.home") + fileSeparator + "uploads";
        uploadFolder = Paths.get(path);
        try {
            Files.createDirectories(uploadFolder);
        } catch (IOException e) {
            throw new RuntimeException("Could not create uploads directory.", e);
        }
    }

    @RequestMapping(value = "/uploadHome", method = RequestMethod.GET)
    public String showUpload(Model model) {

        return "file-upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(Model model, @RequestParam("uploadName") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        InputStream inputStream = multipartFile.getInputStream();
        Files.copy(inputStream, uploadFolder.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        model.addAttribute("targetFileName", "readImage/" + fileName);
        return "file-view";
    }

    @GetMapping("/readImage/{imageName}")
    @ResponseBody
    public byte[] readImage(@PathVariable(value = "imageName") String imageName) throws IOException {

        File file = new File(uploadFolder + fileSeparator + imageName);
        byte[] bytes = Files.readAllBytes(file.toPath());

        return bytes;
    }

}
