### 前后端分离验证码处理说明

此代码为前后端分离，nginx没有反向代理服务端的处理。

注意两点：

1. html页面中ajax请求要增加参数xhrFields: {withCredentials: true}，保持session的传递性。
2. 后端服务要处理跨域

脚本文件说明如下：

|脚本名|说明|
|----|----|
|build-docker-container.sh|创建docker服务容器|
|run-nginx.sh|使用当前目录下的www,运行nginx容器|
|run-server.sh|运行新建的docker服务容器|
|startup.sh|docker服务容器启动脚本|
