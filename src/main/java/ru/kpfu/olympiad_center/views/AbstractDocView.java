package ru.kpfu.olympiad_center.views;


import org.springframework.web.servlet.view.AbstractView;
import ru.kpfu.olympiad_center.util.BookmarkReplacement;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 19.05.2018
 *
 * @author Robert Bagramov.
 */
public abstract class AbstractDocView extends AbstractView {

    public AbstractDocView() {
        setContentType("application/msword");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String filepath = (String) model.get("filepath");

        BookmarkReplacement bookmarkReplacement = createBookmarkReplacement(filepath, model, request);

        buildDocDocument(model, bookmarkReplacement, request, response);

        response.setContentType(getContentType());

        renderDocument(bookmarkReplacement, response);
    }

    protected abstract void buildDocDocument(
            Map<String, Object> model, BookmarkReplacement bookmarkReplacement, HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    protected BookmarkReplacement createBookmarkReplacement(final String filepath, Map<String, Object> model, HttpServletRequest request) {
        return new BookmarkReplacement(filepath);
    }

    protected void renderDocument(BookmarkReplacement bookmarkReplacement, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        bookmarkReplacement.writeToOutputStream(out);
        out.flush();
    }

}
