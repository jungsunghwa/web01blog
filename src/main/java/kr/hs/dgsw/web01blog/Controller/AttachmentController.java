package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web01blog.Service.PostService;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile){

        String destFileName
                = "/Users/jungsunghwa/Documents/GitHub/web01blog/upload"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();

        try {
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            return new AttachmentProtocol(destFileName, srcFile.getOriginalFilename());
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable Long id){
        try {
            AttachmentProtocol attachmentProtocol = null;
            if (type.equals("post")) attachmentProtocol = this.postService.getPostImageByID(id);
            if (type.equals("user")) attachmentProtocol = this.userService.getUserImageByID(id);

            String filePath = attachmentProtocol.getStorepath();
            String filename = attachmentProtocol.getOriginalName();

            File file = new File(filePath);
            if (!file.exists()) return;

            String mineType = URLConnection.guessContentTypeFromName(file.getName());
            if (mineType == null) mineType = "application/octet-stream";

            response.setContentType(mineType);
            response.setHeader("Content-Disposition", "inline; filename = \"" + filename + "\"");
            response.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
