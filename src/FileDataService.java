/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author casadei
 */
import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileDataService extends Remote {

//Here the filename of the file should be stored in the address of the server
    public void upload(String filename, byte[] file) throws RemoteException;

}
