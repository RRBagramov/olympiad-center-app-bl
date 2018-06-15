package ru.kpfu.olympiad_center.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Bookmark;
import org.apache.poi.hwpf.usermodel.Bookmarks;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * 17.05.2018
 *
 * @author Robert Bagramov.
 */
public class BookmarkReplacement {
    private HWPFDocument hwpfDocument;

    public BookmarkReplacement(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);
            hwpfDocument = new HWPFDocument(poifsFileSystem);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void setTextInBookmark(Bookmark bookmark, HWPFDocument document, String text) {
        final Range range = new Range(bookmark.getStart(), bookmark.getEnd(), document);
        if (range.text().length() > 0) {
            range.replaceText(text, false);
        } else {
            range.insertBefore(text);
        }
    }

    public void replaceBookmarks(Map<String, String> params) {
        Bookmarks bookmarks = hwpfDocument.getBookmarks();
        for (int i = 0; i < bookmarks.getBookmarksCount(); i++) {
            Bookmark bookmark = bookmarks.getBookmark(i);
            String text = params.get(bookmark.getName());
            if (isBlank(text)) {
                continue;
            }
            setTextInBookmark(bookmark, hwpfDocument, text);
        }
    }

    public void writeToOutputStream(OutputStream outputStream) {
        try {
            hwpfDocument.write(outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } finally {
            close();
        }
    }

    private void close() {
        try {
            hwpfDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
