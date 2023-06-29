import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class TestProxy {
    public static void main(String[] args) {
        // 创建代理服务器地址和端口
        String proxyHost = "your_proxy_host";
        int proxyPort = 1;
        // 创建代理对象
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));

        // 创建 OkHttpClient，并设置代理
        OkHttpClient client = new OkHttpClient.Builder()
                .proxy(proxy)
                .build();

        // 创建请求对象
        Request request = new Request.Builder()
                .url("https://www.example.com")
                .build();

        try {
            // 发送请求并获取响应
            Response response = client.newCall(request).execute();

            // 处理响应...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
