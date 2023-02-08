### ffmpeg学习

- 抓取桌面生成视频~
- 抓取桌面生成直播
- 抓取声音 音视频同时进行

- srs流服务器
 .\objs\srs -c .\conf\srs.conf
 转webrtc
 .\objs\srs -c conf/rtmp2rtc.conf
 
 webrtc://localhost/live/livestream?eip=10.13.0.1

- 抓取桌面生成视频 
显示抓取工具
ffmpeg -list_devices true -f dshow -i dummy

./ffmpeg 

桌面录屏设备
-- windows gdigrab
./ffmpeg -f gdigrab -i desktop out.mpg
./ffmpeg -f gdigrab -framerate 30 -i desktop out.mpg

./ffmpeg -f gdigrab -framerate 24 -i desktop -vcodec mpeg4 -q:v 2 out13.mp4 7310~15728 高fps
./ffmpeg -f gdigrab -framerate 12 -i desktop -vcodec mpeg4 -q:v 2 out14.mp4  3400~18000 超清
./ffmpeg -f gdigrab -framerate 12 -i desktop -vcodec mpeg4 -q:v 5 out15.mp4  2000~6000 高清
./ffmpeg -f gdigrab -framerate 12 -i desktop -vcodec mpeg4 -q:v 10 out16.mp4  1700~3400 标清
-- ./ffmpeg -f gdigrab -framerate 12 -i desktop -vcodec mpeg4 -q:v 30 out17.mp4  900~1200

./ffmpeg -f gdigrab -framerate 24 -i desktop  -c:v libx264 -crf 12 D:\software\nginx-1.22.1\html\out151.mp4 高清
./ffmpeg -f gdigrab -framerate 24 -i desktop  -c:v libx264 -crf 28 D:\software\nginx-1.22.1\html\out152.mp4 标清

./ffmpeg -f gdigrab  -framerate 24 -offset_x -1920 -video_size 1920*1080 -i desktop  -c:v libx264 -crf 12 D:\software\nginx-1.22.1\html\out151.mp4

./ffmpeg -f gdigrab -framerate 20 -i title="Windows PowerShell" out2.mpg
./ffmpeg -f gdigrab -framerate 20 -i title="Windows PowerShell"  -vcodec mpeg4 -q:v 10 out11.mp4
-- linux x11grab 
ffmpeg -f x11grab -s 1600x900 -r 50 -vcodec libx264 –preset:v ultrafast –tune:v zerolatency -crf 18 -f mpegts udp://localhost:1234

- 抓取桌面生成直播
./ffmpeg -f gdigrab -framerate 24 -offset_x -1920 -video_size 1920*1080 -i desktop  -c:v libx264 -preset:v ultrafast -tune:v zerolatency -crf 18 -f flv rtmp://127.0.0.1:1935/live/livestream 高清
./ffmpeg -f gdigrab -framerate 12 -offset_x -1920 -video_size 1920*1080 -i desktop  -c:v libx264 -preset:v ultrafast -tune:v zerolatency -crf 36 -f flv rtmp://127.0.0.1:1935/live/livestream 标清
ffplay播放
./ffplay rtmp://127.0.0.1:1935/live/livestream