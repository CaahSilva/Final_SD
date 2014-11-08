
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author casadei
 */



public class InsertDriver {
    DB db;
    public InsertDriver(){
        try {
            
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            
            db = mongoClient.getDB( "videoDB" );
        } catch (UnknownHostException ex) {
            Logger.getLogger(InsertDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void saveImageIntoMongoDB(File video) throws IOException {
        String dbFileName = video.getName();
        File videoFile = video;
        // create a "photo" namespace
        GridFS gfsPhoto = new GridFS(db, "video");

        // get image file from local drive
        GridFSInputFile gfsFile = gfsPhoto.createFile(videoFile);

        // set a new filename for identify purpose
        gfsFile.setFilename(dbFileName);

        // save the image file into mongoDB
        gfsFile.save();
        
        // print the result
        DBCursor cursor = gfsPhoto.getFileList();
        while (cursor.hasNext()) {
                System.out.println(cursor.next());
        }
        
    }
    
    public void getSingleImageExample(File video) throws IOException {
        // get image file by it's filename
        String dbFileName = video.getName();
        GridFS gfsPhoto = new GridFS(db, "video");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(dbFileName);
        
        // save it into a new image file
        imageForOutput.writeTo(video.getParent() + "\\ReturnedAudio.mp3");

        // remove the image file from mongoDB
        gfsPhoto.remove(gfsPhoto.findOne(dbFileName));

        System.out.println("Done");
        
    }
    
    public void saveToFileSystem(String path) throws IOException {
        String dbFileName = "DemoVideo";
        GridFS gfsPhoto = new GridFS(db, "videoDB");
        GridFSDBFile imageForOutput = gfsPhoto.findOne(dbFileName);
        imageForOutput.writeTo(path + dbFileName + ".mp3");
    }
}