package com.netty.http;

import com.sun.jndi.toolkit.url.Uri;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import sun.applet.resources.MsgAppletViewer;

import java.net.URI;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {

        //判断 httpObject 是不是 httpRequest
        if (httpObject instanceof HttpRequest){
            System.out.println("msg 类型" + httpObject.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest) httpObject;
            //获取uri过滤指定资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了 favicon.ico，不做响应");
                return;
            }

            //回复信息给浏览器【http协议】
            ByteBuf content = Unpooled.copiedBuffer("Hello,我是服务器", CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //将构建好 response 返回
            ctx.writeAndFlush(response);
        }
    }
}
