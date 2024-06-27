package com.efimchick.ifmo.io.filetree;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileTreeImpl implements FileTree {

	@Override
	public Optional<String> tree(Path path) {
		if (path == null || !Files.exists(path)) {
			return Optional.empty();
		}
		
		StringBuilder sb = new StringBuilder();
		createTree(path, sb, "", "");		
		return Optional.of(sb.toString());
	}
	
	private void createTree(Path path, StringBuilder sb, String prefix, String dir) {		
		isFile(path, sb, prefix);
		
		if(Files.isDirectory(path)) {
			sb.append(prefix).append(path.getFileName()).append(" ").append(sizeDir(path)).append(" bytes\n");
			
			try {
				 List<Path> list = Files.list(path).collect(Collectors.toList());	
				 List<Path> dirList = new ArrayList<>();
				 List<Path> fileList = new ArrayList<>();
				 
				 for (Path listValue : list) {
					 if(Files.isDirectory(listValue)) {
						 dirList.add(listValue);
					 }else {
						 fileList.add(listValue);
					 }
		         }
				 
				 int totalSize = dirList.size() + fileList.size();				 
				 sorted(dirList);
				 sorted(fileList);
				 
				 for (int i = 0; i < totalSize; i++) {
					 Path check = i < dirList.size() ? dirList.get(i) : fileList.get(i - dirList.size());
					 prefix = (i == totalSize - 1) ? "└─ " : "├─ ";
					 String dir2 = (i == totalSize - 1) ? "   " : "│  ";
					 createTree(check, sb, dir + prefix, dir + dir2);
				 }	
				 
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}

	}

	private void sorted(List<Path> data) {
		data.sort(Comparator.comparing(t -> t.getFileName().toString(), String.CASE_INSENSITIVE_ORDER));
	}

	private void isFile(Path path, StringBuilder sb, String prefix) {
		if(Files.isRegularFile(path)) {
			try {
				sb.append(prefix).append(path.getFileName()).append(" ").append(Files.size(path)).append(" bytes\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public long sizeDir(Path path) {
		long size = 0;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                if(attrs.isDirectory()) {
                	size += sizeDir(entry);
                }
                if (attrs.isRegularFile()) {                	
                    size += attrs.size();
				}              
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
		return size;
	}

}
