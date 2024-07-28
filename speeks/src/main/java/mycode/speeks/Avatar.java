package mycode.speeks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Avatar {
	
	Avatar(){
		
	}
	// save profile picture
		protected void writeToFile(InputStream uploadedInputStream,String FileName,  String userName) {
		
			 //String filename = fileDetail.getFileName();
			String uploadedLocation = "C://Users//User//eclipse-workspace//speeks//src//main//webapp//avatar//"+FileName;
			
			try {
				
				OutputStream out = new FileOutputStream(new File(uploadedLocation));
				int read = 0;
				//array bytes for buffer
				byte[] bytes = new byte[1024];

				while ((read = uploadedInputStream.read(bytes)) != -1) {
					//Writes len bytes from the specified byte array starting at offset off to this output stream
					//bytes - the data. 0 - the start offset in the data. read - the number of bytes to write.
					out.write(bytes, 0, read); 
				}
				
				SaveAvatarDB avatar = new SaveAvatarDB();
				avatar.save(userName, FileName);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		//save share files
		protected void writeTo(InputStream InputStream,String filename,  String location) {
		
			 //String filename = file.getFileName();
			String uploadedLocation = "C://Users//User//eclipse-workspace//speeks//src//main//webapp//"+location+"//"+filename;
		
			try {
				OutputStream out = new FileOutputStream(new File(uploadedLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				//out = new FileOutputStream(new File(uploadedLocation));
				while ((read = InputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
}
