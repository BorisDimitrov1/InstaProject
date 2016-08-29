package user;

import java.io.File;
import java.net.URL;
import java.security.InvalidParameterException;

public class Photo {
	private File pathToThePhoto;

	public Photo(File file) {
		try {
			this.setFile(file);
		} catch (PhotoException e) {
			System.out.println(e.getMessage());
		}
		
			
	}
	
	private void setFile(File file) throws PhotoException{
		if(file != null && file.exists()){
			this.pathToThePhoto = file;
		}else{
			throw new PhotoException("Invalid link URL!");
		}
	}
}
