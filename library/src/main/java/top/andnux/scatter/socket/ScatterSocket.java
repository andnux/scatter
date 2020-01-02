package top.andnux.scatter.socket;

import top.andnux.scatter.Scatter;
import top.andnux.scatter.ScatterClient;

public class ScatterSocket extends Scatter {

    private ScatterWebSocket webSocket;

    public ScatterSocket(ScatterClient scatterClient) {
        super(scatterClient);
        this.webSocket = new ScatterWebSocket(scatterClient);
        webSocket.setReuseAddr(true);
    }

    @Override
    public void start() {
        try {
            webSocket.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            webSocket.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
