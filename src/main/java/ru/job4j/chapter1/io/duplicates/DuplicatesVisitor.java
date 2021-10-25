package ru.job4j.chapter1.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final List<FileProperty> duplicates = new ArrayList<>();
    private final Set<FileProperty> paths = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        File file = path.toFile();
        FileProperty fileProperty = new FileProperty(file.length(), file.getName());
        if (!paths.add(fileProperty)) {
            duplicates.add(fileProperty);
        }
        return super.visitFile(path, attrs);
    }

    public List<FileProperty> getDuplicates() {
        return duplicates;
    }
}
