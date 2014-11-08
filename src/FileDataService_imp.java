import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDataService_imp extends UnicastRemoteObject implements FileDataService {

    public FileDataService_imp() throws RemoteException {

    }

    @Override
    public void upload(String filename, byte[] fileContent) throws RemoteException {
        File file = new File(filename);
        InsertDriver insertObj = new InsertDriver();
        try {
            insertObj.saveImageIntoMongoDB(file);
            insertObj.getSingleImageExample(file);
        } catch (IOException ex) {
            Logger.getLogger(FileDataService_imp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
