package br.com.fabrisio.guideme.util.firebase;

import br.com.fabrisio.guideme.entity.user.UserEntity;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class FirebaseBlobStorage {

    private static String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/guideme-js.appspot.com/o/%s?alt=media";
    private static String TEMP_URL = "";


    public String getFileNamePhotoProfile(MultipartFile multipartFile, UserEntity userEntity) {
        String fileName = multipartFile.getOriginalFilename();
        return userEntity.getId().toString().concat(this.getExtension(fileName));
    }

    public String upload(MultipartFile multipartFile, String fileName) {
        try {
            File file = this.convertToFile(multipartFile, fileName);
            TEMP_URL = this.uploadFile(file, fileName);
            file.delete();
            return TEMP_URL;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFile(String fileName) throws IOException {
        BlobId blobId = BlobId.of("guideme-js.appspot.com", fileName);
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/json/guideme.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.delete(blobId);
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("guideme-js.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/json/guideme.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
