package Old;

/*# Imagine we have a system that stores files, and these files can be grouped into collections. We are interested in knowing where our resources are being taken up.

        # For this system we would like to generate a report that lists:

        # - The total size of all files stored; and

        # - The top N collections (by file size) where N can be a user-defined value


        # An example input into your report generator might look like:


        # file1.txt (size: 100)
        # file2.txt (size: 200) in collection "collection1"
        # file3.txt (size: 200) in collection "collection1"
        # file4.txt (size: 300) in collection "collection2"
        # file5.txt (size: 10)

        N ,
        O(1)
        O(NlogN)
        SC O(N)

 */
public class Solve {

    public static void main(String[] args) {
        CollectionManager.processFiles("file1.txt", 100L , "");
        System.out.println(CollectionManager.getTotalFileSize());
        System.out.println(CollectionManager.getTopNCollections(1));
        CollectionManager.processFiles("file2.txt", 200L , "collection1");
        CollectionManager.processFiles("file3.txt", 200L , "collection1");
        CollectionManager.processFiles("file4.txt", 300L , "collection2");
        CollectionManager.processFiles("file2.txt", 200L , "collection2");
        System.out.println(CollectionManager.getTotalFileSize());
        System.out.println(CollectionManager.getTopNCollections(2));
        CollectionManager.processFiles("file5.txt", 10L , "");
        System.out.println(CollectionManager.getTotalFileSize());
        System.out.println(CollectionManager.getTopNCollections(2));
        System.out.println(CollectionManager.getTopNCollections(3));
        System.out.println(CollectionManager.getTotalFileSize());
        System.out.println(CollectionManager.getTopNCollections(4));

    }
}
