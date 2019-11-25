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

使用了 docker-tools 里的 debian-nginx 容器。
测试步骤：
1. 运行 build-docker-container.sh 创建docker服务容器，如果没有打包会调用 mvn 命令，请保证 mvn 命令能正常运行
2. 运行 run-nginx.sh、 run-server.sh，nginx映射了80端口，如果在 linux 下面运行要使用 sudo。
3. 访问 http://localhost/index.html 和 http://localhost/index2.html 进行测试，其中index2.html中去除ajax请求的xhrFields: {withCredentials: true}参数。可以发现index.html能保证验证码验证成功，而index2.html不能。