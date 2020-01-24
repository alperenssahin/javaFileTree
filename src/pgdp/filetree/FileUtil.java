package pgdp.filetree;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FileUtil {

	public static File toFileRepresentation(Path path) throws IOException {
		// TODO
		try{
			List<File> files = new LinkedList<>();
			java.io.File file = new java.io.File(path.toString());
			if(file.isDirectory()){
				for(java.io.File c : Objects.requireNonNull(file.listFiles())){
					files.add(toFileRepresentation(FileSystems.getDefault().getPath(c.getPath())));
				}
				return new Directory(path,files);
			}else{
				return new RegularFile(path);
			}
		}catch (Exception ex){
			throw new IOException();
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println(toFileRepresentation(FileSystems.getDefault().getPath("./")).getHeight());
	}

}
