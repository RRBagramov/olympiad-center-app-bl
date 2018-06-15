package ru.kpfu.olympiad_center.views;

import ru.kpfu.olympiad_center.util.BookmarkReplacement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 20.05.2018
 *
 * @author Robert Bagramov.
 */
public class WordView extends AbstractDocView {

    @Override
    protected void buildDocDocument(Map<String, Object> model,
                                    BookmarkReplacement bookmarkReplacement,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=" + "report" + new Date().getTime() + ".doc");

        Map<String, String> bookmarks = (Map<String, String>) model.get("bookmarks");

        bookmarkReplacement.replaceBookmarks(bookmarks);
    }
}
