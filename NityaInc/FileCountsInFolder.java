package NityaInc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileCountsInFolder {

	public static void main(String[] args) throws IOException {
		Map<Integer, Integer> filesMap = new HashMap<>();
		List<Path> subfolder = Files.walk(Paths.get("D:\\Microservices Projects"), 1).collect(Collectors.toList());

		for (int i = 0; i < 12; i++) {
			filesMap.put(i, 0);
		}
		subfolder.remove(0);
		subfolder.forEach(subFolder -> {
			System.err.println(subFolder.getFileName());
			BasicFileAttributeView basicfile = Files.getFileAttributeView(subFolder, BasicFileAttributeView.class,
					LinkOption.NOFOLLOW_LINKS);
			try {
				BasicFileAttributes attr = basicfile.readAttributes();
				FileTime fileTime = attr.creationTime();
				Date date = new Date(fileTime.toMillis());
				int count = filesMap.get(date.getMonth());
				filesMap.put(date.getMonth(), count += 1);
			} catch (IOException e) {
				System.out.println("Exception raised while reading File creation Time " + e.getMessage());
			}
		});

		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();

		filesMap.entrySet().forEach(fileMap -> {
			System.err.println(months[fileMap.getKey()] + "------" + fileMap.getValue());
		});
	}
}
