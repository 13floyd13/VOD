/**
 * 
 */
package com.forrest.cinema.repos;

import java.io.File;
import java.util.List;

/**
 * @author martin
 *
 */
public interface FileRepository {

	List<File> getAllFilesInDirectory(String directoryPath);
	List<File> getNewFilesInDirectory(String directoryPath, List<String> files);
	
}
