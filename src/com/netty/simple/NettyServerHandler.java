package com.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据事件（读取客户端发送的消息）
     * @param ctx 上下文对象，含有 管道pipeline 通道channel 地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx =" + ctx);

        //当有需要花费长时间的任务时，可以使用异步执行，提交该Channel对应的NioEventLoop 的 taskQueue 中

        //解决方案1 用户程序自定义的普通任务
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000);
                //处理后回消息给客户端
                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端2", CharsetUtil.UTF_8));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //解决方案2 用户自定义定时任务，将该任务提交到 scheduleTaskQueue 中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端3", CharsetUtil.UTF_8));
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println("go on ....");
        //ByteBuf 是netty提供的，不是 nio 的 ByteBuffer，性能更高
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的信息：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址：" + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端1", CharsetUtil.UTF_8));
    }

    /**
     * 异常处理，一般需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
