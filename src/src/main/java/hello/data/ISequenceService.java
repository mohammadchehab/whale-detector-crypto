package hello.data;

public interface ISequenceService {
     int getNextSequence(String collectionName);

     void reset();
}