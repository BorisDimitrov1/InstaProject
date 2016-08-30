package photo;

import java.io.File;
import java.net.URL;
import java.security.InvalidParameterException;

public class Photo {
	private static final String INVALID_FILE_TEXT = "Invalid photo location";
	private File pathToThePhoto;

	public Photo(File file) throws PhotoException {
		this.setFile(file);
	}
	
	private void setFile(File file) throws PhotoException{
		if(file != null && file.exists()){
			this.pathToThePhoto = file;
		}else{
			throw new PhotoException(INVALID_FILE_TEXT);
		}
	}
}
