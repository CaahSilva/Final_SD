 import java.io.BufferedInputStream; 
    import java.io.File; 
    import java.io.FileInputStream; 
    import java.io.FileNotFoundException; 
    import java.io.IOException; 
    import java.net.MalformedURLException; 
    import java.rmi.Naming; 
    import java.rmi.NotBoundException; 
    import java.rmi.RemoteException; 

    public class FileClient { 

        public FileClient() { 
            // TODO Auto-generated constructor stub 
        } 

        public static void main(String[] args) { 
            try { 
                FileDataService fileDataService = (FileDataService) Naming.lookup("rmi://localhost:9001/FileDataService"); 
                fileDataService.upload("F:\\Músicas\\Teste.mp3", new FileClient().fileToByte("F:\\Músicas\\Teste.mp3")); 
            } catch (MalformedURLException | RemoteException | NotBoundException e) { 
                // TODO Auto-generated catch block 
                e.printStackTrace(); 
            } 
        } 
//This method is more important, through this method to a file named filename is converted into a byte array
        private byte[] fileToByte(String filename){ 
            byte[] b = null; 
            try { 
                File file = new File(filename); 
                b = new byte[(int) file.length()]; 
                BufferedInputStream is = new BufferedInputStream(new FileInputStream(file)); 
                is.read(b); 
            } catch (FileNotFoundException e) { 
            // TODO Auto-generated catch block 
                e.printStackTrace(); 
            } catch (IOException e) { 
                // TODO Auto-generated catch block 
                e.printStackTrace(); 
            } 
            return b; 
        } 
    } 
