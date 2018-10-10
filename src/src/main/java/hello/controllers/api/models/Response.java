package hello.controllers.api.models;

public class Response {

    protected boolean success;
    protected String  message;

    public Response(boolean success, String message){
        this.message = message;
        this.success = success;
    }

    public Response(){
        this.success = true;
        this.message = "";
    }

    public boolean getSuccess(){
        return this.success;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}