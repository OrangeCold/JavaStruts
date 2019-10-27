package com.map.title;

import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MapTitle {

    /**
     * 批量推送数据
     */
    public void pushData(){
        List<Integer> list = new ArrayList<>(16);
        list.add(1);
        list.add(2);
        list.add(3);

        // 数据总量
        int size = list.size();
        // 每次推送的数据量
        int pushNum = 2;

        List<Integer> container = new ArrayList<>(16);
        for (int i = 0; i<size; i++) {
            container.add(list.get(i));
            if (container.size()==pushNum || (i+1)==size){
                System.out.println("pushData");
                container.clear();
            }
        }

    }

    /**
     * 测试 HashMap 是否可以存储重复的 key
     */
    @Test
    public void testHashMapKey(){

        Map<String,String> map = new HashMap<>();
        map.put("name","mike");
        map.put("name","lala");

        String name = map.get("name");
        System.out.println("name = " + name);

    }

    /**
     * 遍历没有泛型的Map
     */
    @Test
    public void testMap(){

        Map map1 = new HashMap();
        map1.put("tt","12");
        map1.put("name","mike");

        Set set = map1.entrySet();
        for (Object o : set) {
            Map.Entry r = (Map.Entry) o;
            String key = (String)r.getKey();
            System.out.println("key = " + key);
            String value = (String) r.getValue();
            System.out.println("value = " + value);
        }

    }

    @Test
    public void testDebug(){
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
        }
    }

    @Test
    public void testHttp(){
        String path = "http://127.0.0.1:8080/myfirstmaven/home/hello";
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;

            /**设置URLConnection的参数和普通的请求属性****start***/

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

            /**设置URLConnection的参数和普通的请求属性****end***/

            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("GET");

            /**GET方法请求*****start*/
            /**
             * 如果只是发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可；
             * 如果发送POST方式的请求，需要获取URLConnection实例对应的输出流来发送请求参数。
             * */
            conn.connect();

            /**GET方法请求*****end*/

            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                str=new String(str.getBytes(),"UTF-8");//解决中文乱码问题
                System.out.println(str);
            }

            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
