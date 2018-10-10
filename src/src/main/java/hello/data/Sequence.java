package hello.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
public class Sequence {
  
    @Id
    private String _id;
    private int _sequence;

    public Sequence(){
        this._id = "";
    }

    public Sequence(String sequenceName){
        this._id = sequenceName;
    }

    public int getSequance() {
        return _sequence;
    }

    public void setSequance(int seq) {
        this._sequence = seq;
    }
}