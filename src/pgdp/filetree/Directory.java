package pgdp.filetree;

import java.nio.file.Path;
import java.util.ArrayList;
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
		if(files.size() == 0){
			throw new NoSuchElementException();
		}
		List<File> docs = new ArrayList<File>();
		if(!docs.contains(this)){
			docs.add(this);
		}
		files.forEach(file -> {
			if(!docs.contains(file)){
				docs.add(file);
			}
			if(file instanceof Directory){
				Iterator<File> it = file.iterator();
				while (it.hasNext()){
					File sb = it.next();
					if(!docs.contains(sb)){
						docs.add(sb);
					}
				}
			}
		});
		return docs.iterator();
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
