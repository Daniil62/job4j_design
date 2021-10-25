package ru.job4j.chapter1.io.duplicates;

public class FileProperty {

    private long size;
    private String name;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FileProperty)) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return that.size == size
                && that.name.equals(name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result *= 31 + Long.hashCode(size);
        return result;
    }
}
