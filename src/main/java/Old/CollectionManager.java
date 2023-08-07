package Old;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CollectionManager {

    private static long totalFileSize=0;

    private static Set<String> filesProcessed = new HashSet<>();

    private static String DEFAULT = "DEFAULT";

    private static Map<String , FileCollection> collectionMap = new ConcurrentHashMap<>();

    public static long getTotalFileSize() {
        return totalFileSize;
    }

    public static List<FileCollection> getTopNCollections(int n) {
        List<FileCollection> fileCollections = new ArrayList<>();
        fileCollections.addAll(collectionMap.values());
        Collections.sort(fileCollections , (o1,o2) -> Long.compare(o2.size,o1.size));
        return fileCollections.stream().limit(n).collect(Collectors.toList());
    }

    public static void processFiles(String fileName, long size, String collectionName) {
        if("".equals(collectionName) || collectionName == null)
            collectionName = DEFAULT;
        if(!collectionMap.containsKey(collectionName))
            collectionMap.put(collectionName , new FileCollection(collectionName , 0L));
        FileCollection fileCollection = collectionMap.get(collectionName);
        fileCollection.setSize(fileCollection.getSize() + size);
        if(!filesProcessed.contains(fileName)) {
            totalFileSize += size;
            filesProcessed.add(fileName);
        }
    }
}
