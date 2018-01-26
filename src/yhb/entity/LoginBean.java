
package yhb.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBean {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}