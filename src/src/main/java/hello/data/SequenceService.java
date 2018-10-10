package hello.data;

import static org.springframework.data.mongodb.core.query.Query.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;
 
@Service
@Configurable
public class SequenceService implements ISequenceService{
  
  @Autowired
  private SequenceRepository _sequenceRepository;
  
  @Autowired 
  private MongoOperations mongo;
   
  public int getNextSequence(String collectionName) {
    
    Sequence counter = mongo.findAndModify(
      query(where("_id").is(collectionName)), 
      new Update().inc("_sequence", 1),
      options().returnNew(true),
      Sequence.class);

      if(counter == null){
        counter = new Sequence( collectionName);
        counter.setSequance(1);
        _sequenceRepository.insert( counter  );
      }
       
    return counter.getSequance();

  }

  @Override
  public void reset() {
    _sequenceRepository.deleteAll();
  }
}