package pgdp.filetree;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Directory extends File {

	private final List<File> files;

	public Directory(Path path, List<File> files) {
		super(path);
		this.files = files;
	}

	@Override
	public Iterator<File> iterator() {
		// TODO
		return files.iterator();
	}

	@Override
	public int getHeight() {
		// TODO
		int sum = 1;
		try{
			int max = 1;
			Iterator<File> a = iterator();
			while (a.hasNext()){
				File b = a.next();
				if(this.hasDirectory() ){
					int tmp = sum + b.getHeight();
					if(tmp>max) max = tmp;
				}else{
					return sum;
				}
			}
			return max;
		}catch (Exception ex){
			throw new NoSuchElementException();
		}
	}
	public boolean hasDirectory(){
		try{
			Iterator<File> a = iterator();
			while (a.hasNext()){
				File b = a.next();
				if(!b.isRegularFile()){
					return true;
				}
			}
			return false;
		}catch (Exception ex){
			throw new NoSuchElementException();
		}
	}
	@Override
	public boolean isRegularFile() {
		return false;
	}

	public List<File> getFiles() {
		return files;
	}

}
