package ru.job4j.chapter1.examples.filemanipulations;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class FileAttributes {

    public void showAttributes(Path file) {
        BasicFileAttributeView attributeView = Files.getFileAttributeView(file, BasicFileAttributeView.class);
        BasicFileAttributes attributes = null;
        try {
            attributes = attributeView.readAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:YYYY, HH:mm:ss");
        if (attributes != null) {
            System.out.println(
                    new Formatter().format("IS FILE: %b%nCREATION TIME: %s%nLAST ACCESS: %s%nLAST MODIFIED: %s%nSIZE: %d",
                    attributes.isRegularFile(),
                    dateFormat.format(attributes.creationTime().toMillis()),
                    dateFormat.format(attributes.lastAccessTime().toMillis()),
                    dateFormat.format(attributes.lastModifiedTime().toMillis()),
                    attributes.size()));
        }
    }

    public void setTimes(Path file, long lastMod, long lastAccess, long create) {
        BasicFileAttributeView attributeView = Files.getFileAttributeView(file, BasicFileAttributeView.class);
        try {
            attributeView.setTimes(FileTime.fromMillis(lastMod),
                    FileTime.fromMillis(lastAccess), FileTime.fromMillis(create));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPosixAttributes(Path file) {
        PosixFileAttributeView attributeView = Files.getFileAttributeView(file, PosixFileAttributeView.class);
        PosixFileAttributes attributes = null;
        try {
            if (attributeView != null) {
                attributes = attributeView.readAttributes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (attributes != null) {
            Formatter formatter = new Formatter();
            System.out.println(
                    formatter.format("OWNER: %s%nPERMISSIONS: %s",
                            attributes.owner().getName(), attributes.permissions().toString()));
        }
    }

    public void setLastModification(Path path, long time) {
        try {
            Files.setLastModifiedTime(path, FileTime.fromMillis(time));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBasicAttribute(Path path, String attribute, Object value) {
        try {
            if (path.toFile().exists()) {
                Files.setAttribute(path, attribute, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPermissions(Path file, String adminName) {
        AclFileAttributeView attributeView = Files.getFileAttributeView(file, AclFileAttributeView.class);
        try {
            UserPrincipal user = FileSystems.getDefault()
                    .getUserPrincipalLookupService()
                    .lookupPrincipalByName(adminName);
            AclEntry entry = AclEntry.newBuilder()
                    .setType(AclEntryType.ALLOW)
                    .setPrincipal(user)
                    .setPermissions(AclEntryPermission.DELETE)
                    .setPermissions(AclEntryPermission.READ_ATTRIBUTES)
                    .setPermissions(AclEntryPermission.READ_DATA)
                    .setPermissions(AclEntryPermission.READ_ACL)
                    .setPermissions(AclEntryPermission.WRITE_OWNER)
                    .setPermissions(AclEntryPermission.WRITE_DATA)
                    .build();
            List<AclEntry> entries = attributeView.getAcl();
            entries.clear();
            entries.add(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOwner(Path file, String ownerName) {
        FileOwnerAttributeView attributeView = Files.getFileAttributeView(file, FileOwnerAttributeView.class);
        try {
            attributeView.setOwner(FileSystems.getDefault()
                    .getUserPrincipalLookupService()
                    .lookupPrincipalByName(ownerName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOwner(Path file) {
        FileOwnerAttributeView attributeView = Files.getFileAttributeView(file, FileOwnerAttributeView.class);
        try {
            System.out.println(String.format("OWNER OF %s IS: %s",
                    file.toFile().getName(), attributeView.getOwner().getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setReadOnly(Path file, boolean value) {
        DosFileAttributeView fileAttributeView =
                Files.getFileAttributeView(file, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        try {
            if (fileAttributeView != null) {
                fileAttributeView.setReadOnly(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHidden(Path file, boolean value) {
        DosFileAttributeView fileAttributeView =
                Files.getFileAttributeView(file, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        try {
            if (fileAttributeView != null) {
                fileAttributeView.setHidden(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSystem(Path file, boolean value) {
        DosFileAttributeView fileAttributeView =
                Files.getFileAttributeView(file, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        try {
            if (fileAttributeView != null) {
                fileAttributeView.setSystem(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setArchive(Path file, boolean value) {
        DosFileAttributeView fileAttributeView =
                Files.getFileAttributeView(file, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        try {
            if (fileAttributeView != null) {
                fileAttributeView.setArchive(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        FileAttributes fa = new FileAttributes();
        Path path = Paths.get(
                ".\\src\\main\\java\\ru\\job4j\\chapter1\\examples\\filemanipulations\\src\\new_file.txt");

        fa.setPermissions(path, "N");

        fa.showAttributes(path);

        fa.setBasicAttribute(path, "creationTime", FileTime.from(Instant.now().minusSeconds(10000 * 24 * 60 * 60)));

        fa.setLastModification(path, new Date().getTime());

        fa.showAttributes(path);
        fa.showPosixAttributes(path);

        fa.showOwner(path);
    }
}
