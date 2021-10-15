package ru.job4j.chapter1.question;

public class Info {

    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Info)) {
            return false;
        }
        Info info = (Info) o;
        return this == o || added == info.added
                && changed == info.changed
                && deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(added);
        result *= 31 + Integer.hashCode(changed);
        result *= 31 + Integer.hashCode(deleted);
        return result;
    }
}
