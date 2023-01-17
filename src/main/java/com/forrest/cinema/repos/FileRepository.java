/**
 * 
 */
package com.forrest.cinema.repos;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author martin
 *
 */
@Repository
public interface FileRepository {

	List<File> getAllFilesInDirectory(String directoryPath);
	List<File> getNewFilesInDirectory(String directoryPath, List<String> files);
	
}
