package project.post_ident.classes;

import org.springframework.cache.annotation.Cacheable;

public class OCRResultObject {

    String result = null;
    String result2 = null;

    public OCRResultObject(String result, String result2) {
        this.result = result;
        this.result2 = result2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }
}
