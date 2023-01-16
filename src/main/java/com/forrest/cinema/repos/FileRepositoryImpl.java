/**
 * 
 */
package com.forrest.cinema.repos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author martin
 *
 */
public class FileRepositoryImpl implements FileRepository {

	private static final Logger Logger = LoggerFactory.getLogger(FileRepositoryImpl.class);
	
	@Override
	public List<File> getAllFilesInDirectory(String directoryPath) {
		List<File> fileList = new ArrayList<>();
		
		try {
			try (Stream<Path> paths = Files.find(Paths.get(directoryPath),
					Integer.MAX_VALUE,
					(path, file) -> file.isRegularFile())) {
						paths.forEach( path -> {
							fileList.add(path.toFile());
						});
			}
		} catch (IOException ioe) {
			Logger.error("IO_EXCEPTION_LIST_OF_FILES", ioe);
		}
		return fileList;
	}

	
	@Override
	public List<File> getNewFilesInDirectory(String directoryPath, List<String> actualFilesTitle) {
		List<File> allFilesInDirectory = this.getAllFilesInDirectory(directoryPath);
		List<File> newFiles = new ArrayList<>();
		
		for (File file : allFilesInDirectory) {
			if (!actualFilesTitle.contains(file.getName())) {
				newFiles.add(file);
			}
		}
		return newFiles;
	}

}
