import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public final class PiggybackServlet extends HttpServlet {
    private int eventNum = 0;
    private final BlockingQueue<String> messages = new LinkedBlockingQueue<String>();
    private final Thread generator = new Thread("Event generator") {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                messages.offer("PiggybackServlet, at " + new Date() + " , the event" + (eventNum ++) );
            }
        }
    };

    @Override
    public void init() throws ServletException {
        generator.start();
    }

    @Override
    public void destroy() {
        generator.interrupt();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FORM POSTED !");
        List<String> messages = new LinkedList<String>();
        this.messages.drainTo(messages);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        try {
            resp.getWriter().write(new JSONObject().put("events", new JSONArray(messages)).put("formValid", true).toString());
        } catch (JSONException e) {
            throw new ServletException(e.getMessage(), e);
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> messages = new LinkedList<String>();
        this.messages.drainTo(messages);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write(new JSONArray(messages).toString());
        resp.getWriter().flush();
    }
}
