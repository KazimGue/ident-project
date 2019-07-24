package project.post_ident.classes;

import java.io.File;

public class DeleteOldImage {

    public static void deleteOldImage(){
        File file = new File("C:\\Users\\krach\\Pictures\\persoUpload.png");
        /*File file = new File("src\\main\\resources\\static\\images\\persoUpload.png");*/
        file.delete();
    }
}
