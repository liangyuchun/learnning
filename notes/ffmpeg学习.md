### ffmpegѧϰ

- ץȡ����������Ƶ~
- ץȡ��������ֱ��
- ץȡ���� ����Ƶͬʱ����

- srs��������
 .\objs\srs -c .\conf\srs.conf
 תwebrtc
 .\objs\srs -c conf/rtmp2rtc.conf
 
 webrtc://localhost/live/livestream?eip=10.13.0.1

- ץȡ����������Ƶ 
��ʾץȡ����
ffmpeg -list_devices true -f dshow -i dummy

./ffmpeg 

����¼���豸
-- windows gdigrab
./ffmpeg -f gdigrab -i desktop out.mpg
./ffmpeg -f gdigrab -framerate 30 -i desktop out.mpg

./ffmpeg -f gdigrab -framerate 24 -i desktop -vcodec mpeg4 -q:v 2 out13.mp4 7310~15728 ��fps
./ffmpeg -f gdigrab -framerate 12 -i desktop -vcodec mpeg4 -q:v 2 out14.mp4  3400~18000 ����
./ffmpeg -f gdigrab -framerate 12 -i desktop -vcodec mpeg4 -q:v 5 out15.mp4  2000~6000 ����
./ffmpeg -f gdigrab -framerate 12 -i desktop -vcodec mpeg4 -q:v 10 out16.mp4  1700~3400 ����
-- ./ffmpeg -f gdigrab -framerate 12 -i desktop -vcodec mpeg4 -q:v 30 out17.mp4  900~1200

./ffmpeg -f gdigrab -framerate 24 -i desktop  -c:v libx264 -crf 12 D:\software\nginx-1.22.1\html\out151.mp4 ����
./ffmpeg -f gdigrab -framerate 24 -i desktop  -c:v libx264 -crf 28 D:\software\nginx-1.22.1\html\out152.mp4 ����

./ffmpeg -f gdigrab  -framerate 24 -offset_x -1920 -video_size 1920*1080 -i desktop  -c:v libx264 -crf 12 D:\software\nginx-1.22.1\html\out151.mp4

./ffmpeg -f gdigrab -framerate 20 -i title="Windows PowerShell" out2.mpg
./ffmpeg -f gdigrab -framerate 20 -i title="Windows PowerShell"  -vcodec mpeg4 -q:v 10 out11.mp4
-- linux x11grab 
ffmpeg -f x11grab -s 1600x900 -r 50 -vcodec libx264 �Cpreset:v ultrafast �Ctune:v zerolatency -crf 18 -f mpegts udp://localhost:1234

- ץȡ��������ֱ��
./ffmpeg -f gdigrab -framerate 24 -offset_x -1920 -video_size 1920*1080 -i desktop  -c:v libx264 -preset:v ultrafast -tune:v zerolatency -crf 18 -f flv rtmp://127.0.0.1:1935/live/livestream ����
./ffmpeg -f gdigrab -framerate 12 -offset_x -1920 -video_size 1920*1080 -i desktop  -c:v libx264 -preset:v ultrafast -tune:v zerolatency -crf 36 -f flv rtmp://127.0.0.1:1935/live/livestream ����
ffplay����
./ffplay rtmp://127.0.0.1:1935/live/livestream