package com.liang.learn;

import javax.swing.*;
import java.awt.*;

/**
 * @author liangyuchun
 * @date 2022/9/14 16:32
 */
public class DesktopClient {
    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame=new JFrame();
        jFrame.setTitle("窗口标题");
        JFrame.setDefaultLookAndFeelDecorated(true);
        jFrame.setSize(2000,800);

        JPanel panel=new JPanel();
        panel.setSize(2000,80);
        panel.setVisible(true);

        Label label1=new Label();
        label1.setText("这是第一个标签！！！");
        label1.setSize(2000,80);
        label1.setFont(new Font("Serif",0,24));

        JButton inbutton=new JButton("进入");
        JButton exitButton=new JButton("离开");
        panel.add(label1);
        panel.add(inbutton);
        panel.add(exitButton);

        jFrame.setContentPane(panel);

//        jFrame.pack();
        jFrame.setVisible(true);
//        Thread.sleep(10*1000L);
    }
}
